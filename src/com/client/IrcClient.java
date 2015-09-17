package com.client;

import if4031.UserService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class IrcClient {

    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 9090;
    private static final long POLLING_PERIOD = 3000;

    private final UserService.Client client;
    private final TTransport transport;
    private final String token;
    private final AtomicBoolean isTerminated = new AtomicBoolean(false);
    private final Set<String> channels = new HashSet<>();
    private String nickname;

    public IrcClient(UserService.Client client, TTransport transport) {
        this.client = client;
        this.transport = transport;
        try {
            this.token = client.getToken();
            this.nickname = client.getNickname(token);
        } catch (TException e) {
            throw new RuntimeException("Unable to create ClientLauncher", e);
        }
    }

    public static IrcClient create(String host, int port) {
        try {
            TTransport transport = new TSocket(host, port);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            UserService.Client client = new UserService.Client(protocol);
            return new IrcClient(client, transport);
        } catch (TException e) {
            throw new RuntimeException("Unable to create ClientLauncher", e);
        }
    }

    public static IrcClient create() {
        return create(DEFAULT_HOST, DEFAULT_PORT);
    }

    public void launch() {
        Thread inputHandler = getInputHandler();
        inputHandler.start();
        Thread messageFetcher = getMessageFetcher();
        messageFetcher.start();
        try {
            inputHandler.join();
            messageFetcher.join();
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        } finally {
            transport.close();
        }
    }

    private Thread getMessageFetcher() {
        return new Thread() {
            @Override
            public void run() {
                try {
                    while (!isTerminated.get()) {
                        try {
                            List<String> messages = client.getMessage(token);
                            for (String message : messages) {
                                showMessage(message);
                            }
                        } catch (TException e) {
                            showMessage("ERROR: Something happens when trying to fetch message: " + e.getMessage());
                            showMessage("       Please press <Enter> to terminate ...");
                            terminate();
                        }
                        Thread.sleep(POLLING_PERIOD);
                    }
                } catch (InterruptedException e) {
                    showMessage("ERROR: messageFetcher was interrupted: " + e.getMessage());
                }
            }
        };
    }

    private void terminate() {
        isTerminated.set(true);
    }

    private void showMessage(String message) {
        System.out.println(message);
    }

    private Thread getInputHandler() {
        final Scanner scanner = new Scanner(System.in);
        return new Thread() {
            @Override
            public void run() {
                while (!isTerminated.get()) {
                    String line = scanner.nextLine();
                    handleInput(line);
                }
            }
        };
    }

    private void handleInput(String input) {
        List<String> groups;
        try {
            if ((groups = CommandRegexes.NICK.match(input)) != null) {
                nickname = groups.get(0);
                if (nickname == null) {
                    nickname = client.getNickname(token);
                } else {
                    if (!client.setNickname(token, nickname)) {
                        showMessage("ERROR: nickname " + nickname + " has already taken");
                        nickname = null;
                    }
                }
                if (nickname != null) {
                    showMessage("Welcome " + nickname + "!");
                }
            } else if ((groups = CommandRegexes.JOIN.match(input)) != null) {
                String channelName = groups.get(0);
                if (channels.contains(channelName)) {
                    showMessage("WARNING: You have already joined channel " + channelName);
                } else {
                    client.joinChannel(token, channelName);
                    channels.add(channelName);
                    showMessage("Joined channel " + channelName);
                }
            } else if ((groups = CommandRegexes.LEAVE.match(input)) != null) {
                String channelName = groups.get(0);
                if (channels.contains(channelName)) {
                    client.leaveChannel(token, channelName);
                    channels.remove(channelName);
                    showMessage("Leaving channel " + channelName);
                } else {
                    showMessage("WARNING: You haven't joined channel " + channelName);
                }
            } else if (CommandRegexes.EXIT.match(input) != null) {
                client.exit(token);
                terminate();
                showMessage("Bye bye!");
            } else if ((groups = CommandRegexes.MESSAGE_CHANNEL.match(input)) != null) {
                String channelName = groups.get(0);
                String message = groups.get(1);
                client.sendMessageToChannel(token, message, channelName);
            } else { // Assuming broadcast
                client.sendMessage(token, input);
            }
        } catch (TException e) {
            showMessage("ERROR: error while handling input: " + e.getMessage());
        }
    }
}

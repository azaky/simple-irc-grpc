package com.client;

import if4031.Interface;
import if4031.Interface.*;
import if4031.UserServiceGrpc;
import if4031.UserServiceGrpc.UserServiceBlockingStub;
import if4031.UserServiceGrpc.UserServiceStub;
import io.grpc.ChannelImpl;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class IrcClient {

    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 9090;
    private static final long POLLING_PERIOD = 3000;

    private final ChannelImpl channel;
    private final UserServiceBlockingStub blockingStub;
    private final UserServiceStub asyncStub;
    private final String token;
    private final AtomicBoolean isTerminated = new AtomicBoolean(false);
    private final Set<String> channels = new HashSet<>();
    private String nickname;

    public IrcClient(ChannelImpl channel) {
        this.channel = channel;
        this.blockingStub = UserServiceGrpc.newBlockingStub(channel);
        this.asyncStub = UserServiceGrpc.newStub(channel);
        this.token = blockingStub.getToken(Interface.GrpcVoid.newBuilder().build()).getToken();
    }

    public static IrcClient create(String host, int port) {
        ChannelImpl channel = NettyChannelBuilder.forAddress(host, port)
                .negotiationType(NegotiationType.PLAINTEXT)
                .build();
        return new IrcClient(channel);
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
            channel.shutdown();
        }
    }

    private Thread getMessageFetcher() {
        return new Thread() {
            @Override
            public void run() {
                try {
                    while (!isTerminated.get()) {
                        Iterator<GrpcString> messages = blockingStub.getMessage(
                                Token.newBuilder().setToken(token).build());
                        while (messages.hasNext()) {
                            showMessage(messages.next().getValue());
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
        if ((groups = CommandRegexes.NICK.match(input)) != null) {
            nickname = groups.get(0);
            if (nickname == null) {
                nickname = blockingStub.getNickname(Token.newBuilder().setToken(token).build()).getValue();
            } else {
                TokenedString request = TokenedString.newBuilder()
                        .setToken(token)
                        .setValue(nickname)
                        .build();
                if (!blockingStub.setNickname(request).getValue()) {
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
                TokenedString request = TokenedString.newBuilder()
                        .setToken(token)
                        .setValue(channelName)
                        .build();
                blockingStub.joinChannel(request);
                channels.add(channelName);
                showMessage("Joined channel " + channelName);
            }
        } else if ((groups = CommandRegexes.LEAVE.match(input)) != null) {
            String channelName = groups.get(0);
            if (channels.contains(channelName)) {
                TokenedString request = TokenedString.newBuilder()
                        .setToken(token)
                        .setValue(channelName)
                        .build();
                blockingStub.leaveChannel(request);
                channels.remove(channelName);
                showMessage("Leaving channel " + channelName);
            } else {
                showMessage("WARNING: You haven't joined channel " + channelName);
            }
        } else if (CommandRegexes.EXIT.match(input) != null) {
            blockingStub.exit(Token.newBuilder().setToken(token).build());
            terminate();
            showMessage("Bye bye!");
        } else if ((groups = CommandRegexes.MESSAGE_CHANNEL.match(input)) != null) {
            String channelName = groups.get(0);
            String message = groups.get(1);
            TokenedMessageAndChannel request = TokenedMessageAndChannel.newBuilder()
                    .setToken(token)
                    .setMessage(message)
                    .setChannel(channelName)
                    .build();
            blockingStub.sendMessageToChannel(request);
        } else { // Assuming broadcast
            TokenedString request = TokenedString.newBuilder()
                    .setToken(token)
                    .setValue(input)
                    .build();
            blockingStub.sendMessage(request);
        }
    }
}

package com.client;

public class ClientLauncher {

    public static void main(String[] args) {
        String host = IrcClient.DEFAULT_HOST;
        int port = IrcClient.DEFAULT_PORT;
        if (args.length >= 2) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        }
        try {
            IrcClient launcher = IrcClient.create(host, port);
            launcher.launch();
        } catch (Exception e) {
            throw new RuntimeException("Something bad happened when trying to start client", e);
        }
    }

}

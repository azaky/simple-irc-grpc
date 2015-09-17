package com.server;

import if4031.UserHandler;
import if4031.UserService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class Main {

    public static void main(String[] args) {
        UserHandler handler = new UserHandler();
        UserService.Processor processor = new UserService.Processor(handler);

        Runnable simple = () -> simple(processor);
        new Thread(simple).start();
    }

    public static void simple(UserService.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor)
                    .protocolFactory(new TBinaryProtocol.Factory()));

            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

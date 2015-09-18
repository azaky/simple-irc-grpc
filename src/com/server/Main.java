package com.server;

import if4031.UserServiceGrpc;
import io.grpc.ServerImpl;
import io.grpc.netty.NettyServerBuilder;

public class Main {

    public static final int DEFAULT_PORT = 9090;

    public static void main(String[] args) {
        Runnable simple = () -> simple();
        new Thread(simple).start();
    }

    public static void simple() {
        try {
            ServerImpl gRpcServer = NettyServerBuilder.forPort(DEFAULT_PORT)
                    .addService(UserServiceGrpc.bindService(new UserServiceHandler()))
                    .build().start();
            System.out.println("Server started on port " + DEFAULT_PORT);
            gRpcServer.awaitTermination();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

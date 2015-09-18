package com.server;

import if4031.Interface.*;
import if4031.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Willy on 9/16/2015.
 */
public class UserServiceHandler implements UserServiceGrpc.UserService {

    private final HashMap<String, User> session = new HashMap<>();

    @Override
    public void getToken(GrpcVoid request, StreamObserver<Token> responseObserver) {
        String token = RandomStringUtils.random(20);
        session.put(token, new User());

        responseObserver.onValue(Token.newBuilder().setToken(token).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getNickname(Token request, StreamObserver<GrpcString> responseObserver) {
        User user = session.get(request.getToken());

        responseObserver.onValue(GrpcString.newBuilder().setValue(user.getNickname()).build());
        responseObserver.onCompleted();
    }

    @Override
    public void setNickname(TokenedString request, StreamObserver<GrpcBool> responseObserver) {
        User user = session.get(request.getToken());
        String nickname = request.getValue();
        boolean success = user.setNickname(nickname);

        responseObserver.onValue(GrpcBool.newBuilder().setValue(success).build());
        responseObserver.onCompleted();
    }

    @Override
    public void joinChannel(TokenedString request, StreamObserver<GrpcVoid> responseObserver) {
        User user = session.get(request.getToken());
        user.joinChannel(request.getValue());

        responseObserver.onValue(GrpcVoid.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void leaveChannel(TokenedString request, StreamObserver<GrpcVoid> responseObserver) {
        User user = session.get(request.getToken());
        user.leaveChannel(request.getValue());

        responseObserver.onValue(GrpcVoid.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void sendMessage(TokenedString request, StreamObserver<GrpcVoid> responseObserver) {
        User user = session.get(request.getToken());
        user.sendMessage(request.getValue());

        responseObserver.onValue(GrpcVoid.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void sendMessageToChannel(TokenedMessageAndChannel request, StreamObserver<GrpcVoid> responseObserver) {
        User user = session.get(request.getToken());
        user.sendMessageToChannel(request.getMessage(), request.getChannel());

        responseObserver.onValue(GrpcVoid.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void getMessage(Token request, StreamObserver<GrpcString> responseObserver) {
        User user = session.get(request.getToken());
        List<String> messages = user.getMessage();
        for (String message : messages) {
            responseObserver.onValue(GrpcString.newBuilder().setValue(message).build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void exit(Token request, StreamObserver<GrpcVoid> responseObserver) {
        String token = request.getToken();
        User user = session.get(token);
        user.exit();
        session.remove(token);

        responseObserver.onValue(GrpcVoid.getDefaultInstance());
        responseObserver.onCompleted();
    }
}
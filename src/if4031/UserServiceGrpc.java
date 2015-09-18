package if4031;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@javax.annotation.Generated("by gRPC proto compiler")
public class UserServiceGrpc {

  // Static method descriptors that strictly reflect the proto.
  public static final io.grpc.MethodDescriptor<if4031.Interface.GrpcVoid,
      if4031.Interface.Token> METHOD_GET_TOKEN =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "if4031.UserService", "getToken",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.Interface.GrpcVoid.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.Interface.Token.parser()));
  public static final io.grpc.MethodDescriptor<if4031.Interface.Token,
      if4031.Interface.GrpcString> METHOD_GET_NICKNAME =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "if4031.UserService", "getNickname",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.Interface.Token.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.Interface.GrpcString.parser()));
  public static final io.grpc.MethodDescriptor<if4031.Interface.TokenedString,
      if4031.Interface.GrpcBool> METHOD_SET_NICKNAME =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "if4031.UserService", "setNickname",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.Interface.TokenedString.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.Interface.GrpcBool.parser()));
  public static final io.grpc.MethodDescriptor<if4031.Interface.TokenedString,
      if4031.Interface.GrpcVoid> METHOD_JOIN_CHANNEL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "if4031.UserService", "joinChannel",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.Interface.TokenedString.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.Interface.GrpcVoid.parser()));
  public static final io.grpc.MethodDescriptor<if4031.Interface.TokenedString,
      if4031.Interface.GrpcVoid> METHOD_LEAVE_CHANNEL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "if4031.UserService", "leaveChannel",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.Interface.TokenedString.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.Interface.GrpcVoid.parser()));
  public static final io.grpc.MethodDescriptor<if4031.Interface.TokenedString,
      if4031.Interface.GrpcVoid> METHOD_SEND_MESSAGE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "if4031.UserService", "sendMessage",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.Interface.TokenedString.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.Interface.GrpcVoid.parser()));
  public static final io.grpc.MethodDescriptor<if4031.Interface.TokenedMessageAndChannel,
      if4031.Interface.GrpcVoid> METHOD_SEND_MESSAGE_TO_CHANNEL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "if4031.UserService", "sendMessageToChannel",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.Interface.TokenedMessageAndChannel.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.Interface.GrpcVoid.parser()));
  public static final io.grpc.MethodDescriptor<if4031.Interface.Token,
      if4031.Interface.GrpcString> METHOD_GET_MESSAGE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING,
          "if4031.UserService", "getMessage",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.Interface.Token.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.Interface.GrpcString.parser()));
  public static final io.grpc.MethodDescriptor<if4031.Interface.Token,
      if4031.Interface.GrpcVoid> METHOD_EXIT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "if4031.UserService", "exit",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.Interface.Token.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.Interface.GrpcVoid.parser()));

  public static UserServiceStub newStub(io.grpc.Channel channel) {
    return new UserServiceStub(channel);
  }

  public static UserServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UserServiceBlockingStub(channel);
  }

  public static UserServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UserServiceFutureStub(channel);
  }

  public static interface UserService {

    public void getToken(if4031.Interface.GrpcVoid request,
        io.grpc.stub.StreamObserver<if4031.Interface.Token> responseObserver);

    public void getNickname(if4031.Interface.Token request,
        io.grpc.stub.StreamObserver<if4031.Interface.GrpcString> responseObserver);

    public void setNickname(if4031.Interface.TokenedString request,
        io.grpc.stub.StreamObserver<if4031.Interface.GrpcBool> responseObserver);

    public void joinChannel(if4031.Interface.TokenedString request,
        io.grpc.stub.StreamObserver<if4031.Interface.GrpcVoid> responseObserver);

    public void leaveChannel(if4031.Interface.TokenedString request,
        io.grpc.stub.StreamObserver<if4031.Interface.GrpcVoid> responseObserver);

    public void sendMessage(if4031.Interface.TokenedString request,
        io.grpc.stub.StreamObserver<if4031.Interface.GrpcVoid> responseObserver);

    public void sendMessageToChannel(if4031.Interface.TokenedMessageAndChannel request,
        io.grpc.stub.StreamObserver<if4031.Interface.GrpcVoid> responseObserver);

    public void getMessage(if4031.Interface.Token request,
        io.grpc.stub.StreamObserver<if4031.Interface.GrpcString> responseObserver);

    public void exit(if4031.Interface.Token request,
        io.grpc.stub.StreamObserver<if4031.Interface.GrpcVoid> responseObserver);
  }

  public static interface UserServiceBlockingClient {

    public if4031.Interface.Token getToken(if4031.Interface.GrpcVoid request);

    public if4031.Interface.GrpcString getNickname(if4031.Interface.Token request);

    public if4031.Interface.GrpcBool setNickname(if4031.Interface.TokenedString request);

    public if4031.Interface.GrpcVoid joinChannel(if4031.Interface.TokenedString request);

    public if4031.Interface.GrpcVoid leaveChannel(if4031.Interface.TokenedString request);

    public if4031.Interface.GrpcVoid sendMessage(if4031.Interface.TokenedString request);

    public if4031.Interface.GrpcVoid sendMessageToChannel(if4031.Interface.TokenedMessageAndChannel request);

    public java.util.Iterator<if4031.Interface.GrpcString> getMessage(
        if4031.Interface.Token request);

    public if4031.Interface.GrpcVoid exit(if4031.Interface.Token request);
  }

  public static interface UserServiceFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<if4031.Interface.Token> getToken(
        if4031.Interface.GrpcVoid request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.Interface.GrpcString> getNickname(
        if4031.Interface.Token request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.Interface.GrpcBool> setNickname(
        if4031.Interface.TokenedString request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.Interface.GrpcVoid> joinChannel(
        if4031.Interface.TokenedString request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.Interface.GrpcVoid> leaveChannel(
        if4031.Interface.TokenedString request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.Interface.GrpcVoid> sendMessage(
        if4031.Interface.TokenedString request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.Interface.GrpcVoid> sendMessageToChannel(
        if4031.Interface.TokenedMessageAndChannel request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.Interface.GrpcVoid> exit(
        if4031.Interface.Token request);
  }

  public static class UserServiceStub extends io.grpc.stub.AbstractStub<UserServiceStub>
      implements UserService {
    private UserServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceStub(channel, callOptions);
    }

    @java.lang.Override
    public void getToken(if4031.Interface.GrpcVoid request,
        io.grpc.stub.StreamObserver<if4031.Interface.Token> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_GET_TOKEN, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void getNickname(if4031.Interface.Token request,
        io.grpc.stub.StreamObserver<if4031.Interface.GrpcString> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_GET_NICKNAME, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void setNickname(if4031.Interface.TokenedString request,
        io.grpc.stub.StreamObserver<if4031.Interface.GrpcBool> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_SET_NICKNAME, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void joinChannel(if4031.Interface.TokenedString request,
        io.grpc.stub.StreamObserver<if4031.Interface.GrpcVoid> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_JOIN_CHANNEL, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void leaveChannel(if4031.Interface.TokenedString request,
        io.grpc.stub.StreamObserver<if4031.Interface.GrpcVoid> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_LEAVE_CHANNEL, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void sendMessage(if4031.Interface.TokenedString request,
        io.grpc.stub.StreamObserver<if4031.Interface.GrpcVoid> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void sendMessageToChannel(if4031.Interface.TokenedMessageAndChannel request,
        io.grpc.stub.StreamObserver<if4031.Interface.GrpcVoid> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE_TO_CHANNEL, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void getMessage(if4031.Interface.Token request,
        io.grpc.stub.StreamObserver<if4031.Interface.GrpcString> responseObserver) {
      asyncServerStreamingCall(
          channel.newCall(METHOD_GET_MESSAGE, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void exit(if4031.Interface.Token request,
        io.grpc.stub.StreamObserver<if4031.Interface.GrpcVoid> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_EXIT, callOptions), request, responseObserver);
    }
  }

  public static class UserServiceBlockingStub extends io.grpc.stub.AbstractStub<UserServiceBlockingStub>
      implements UserServiceBlockingClient {
    private UserServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public if4031.Interface.Token getToken(if4031.Interface.GrpcVoid request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_GET_TOKEN, callOptions), request);
    }

    @java.lang.Override
    public if4031.Interface.GrpcString getNickname(if4031.Interface.Token request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_GET_NICKNAME, callOptions), request);
    }

    @java.lang.Override
    public if4031.Interface.GrpcBool setNickname(if4031.Interface.TokenedString request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_SET_NICKNAME, callOptions), request);
    }

    @java.lang.Override
    public if4031.Interface.GrpcVoid joinChannel(if4031.Interface.TokenedString request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_JOIN_CHANNEL, callOptions), request);
    }

    @java.lang.Override
    public if4031.Interface.GrpcVoid leaveChannel(if4031.Interface.TokenedString request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_LEAVE_CHANNEL, callOptions), request);
    }

    @java.lang.Override
    public if4031.Interface.GrpcVoid sendMessage(if4031.Interface.TokenedString request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE, callOptions), request);
    }

    @java.lang.Override
    public if4031.Interface.GrpcVoid sendMessageToChannel(if4031.Interface.TokenedMessageAndChannel request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE_TO_CHANNEL, callOptions), request);
    }

    @java.lang.Override
    public java.util.Iterator<if4031.Interface.GrpcString> getMessage(
        if4031.Interface.Token request) {
      return blockingServerStreamingCall(
          channel.newCall(METHOD_GET_MESSAGE, callOptions), request);
    }

    @java.lang.Override
    public if4031.Interface.GrpcVoid exit(if4031.Interface.Token request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_EXIT, callOptions), request);
    }
  }

  public static class UserServiceFutureStub extends io.grpc.stub.AbstractStub<UserServiceFutureStub>
      implements UserServiceFutureClient {
    private UserServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.Interface.Token> getToken(
        if4031.Interface.GrpcVoid request) {
      return futureUnaryCall(
          channel.newCall(METHOD_GET_TOKEN, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.Interface.GrpcString> getNickname(
        if4031.Interface.Token request) {
      return futureUnaryCall(
          channel.newCall(METHOD_GET_NICKNAME, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.Interface.GrpcBool> setNickname(
        if4031.Interface.TokenedString request) {
      return futureUnaryCall(
          channel.newCall(METHOD_SET_NICKNAME, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.Interface.GrpcVoid> joinChannel(
        if4031.Interface.TokenedString request) {
      return futureUnaryCall(
          channel.newCall(METHOD_JOIN_CHANNEL, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.Interface.GrpcVoid> leaveChannel(
        if4031.Interface.TokenedString request) {
      return futureUnaryCall(
          channel.newCall(METHOD_LEAVE_CHANNEL, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.Interface.GrpcVoid> sendMessage(
        if4031.Interface.TokenedString request) {
      return futureUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.Interface.GrpcVoid> sendMessageToChannel(
        if4031.Interface.TokenedMessageAndChannel request) {
      return futureUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE_TO_CHANNEL, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.Interface.GrpcVoid> exit(
        if4031.Interface.Token request) {
      return futureUnaryCall(
          channel.newCall(METHOD_EXIT, callOptions), request);
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final UserService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder("if4031.UserService")
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_GET_TOKEN,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                if4031.Interface.GrpcVoid,
                if4031.Interface.Token>() {
              @java.lang.Override
              public void invoke(
                  if4031.Interface.GrpcVoid request,
                  io.grpc.stub.StreamObserver<if4031.Interface.Token> responseObserver) {
                serviceImpl.getToken(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_GET_NICKNAME,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                if4031.Interface.Token,
                if4031.Interface.GrpcString>() {
              @java.lang.Override
              public void invoke(
                  if4031.Interface.Token request,
                  io.grpc.stub.StreamObserver<if4031.Interface.GrpcString> responseObserver) {
                serviceImpl.getNickname(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_SET_NICKNAME,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                if4031.Interface.TokenedString,
                if4031.Interface.GrpcBool>() {
              @java.lang.Override
              public void invoke(
                  if4031.Interface.TokenedString request,
                  io.grpc.stub.StreamObserver<if4031.Interface.GrpcBool> responseObserver) {
                serviceImpl.setNickname(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_JOIN_CHANNEL,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                if4031.Interface.TokenedString,
                if4031.Interface.GrpcVoid>() {
              @java.lang.Override
              public void invoke(
                  if4031.Interface.TokenedString request,
                  io.grpc.stub.StreamObserver<if4031.Interface.GrpcVoid> responseObserver) {
                serviceImpl.joinChannel(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_LEAVE_CHANNEL,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                if4031.Interface.TokenedString,
                if4031.Interface.GrpcVoid>() {
              @java.lang.Override
              public void invoke(
                  if4031.Interface.TokenedString request,
                  io.grpc.stub.StreamObserver<if4031.Interface.GrpcVoid> responseObserver) {
                serviceImpl.leaveChannel(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_SEND_MESSAGE,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                if4031.Interface.TokenedString,
                if4031.Interface.GrpcVoid>() {
              @java.lang.Override
              public void invoke(
                  if4031.Interface.TokenedString request,
                  io.grpc.stub.StreamObserver<if4031.Interface.GrpcVoid> responseObserver) {
                serviceImpl.sendMessage(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_SEND_MESSAGE_TO_CHANNEL,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                if4031.Interface.TokenedMessageAndChannel,
                if4031.Interface.GrpcVoid>() {
              @java.lang.Override
              public void invoke(
                  if4031.Interface.TokenedMessageAndChannel request,
                  io.grpc.stub.StreamObserver<if4031.Interface.GrpcVoid> responseObserver) {
                serviceImpl.sendMessageToChannel(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_GET_MESSAGE,
          asyncServerStreamingCall(
            new io.grpc.stub.ServerCalls.ServerStreamingMethod<
                if4031.Interface.Token,
                if4031.Interface.GrpcString>() {
              @java.lang.Override
              public void invoke(
                  if4031.Interface.Token request,
                  io.grpc.stub.StreamObserver<if4031.Interface.GrpcString> responseObserver) {
                serviceImpl.getMessage(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_EXIT,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                if4031.Interface.Token,
                if4031.Interface.GrpcVoid>() {
              @java.lang.Override
              public void invoke(
                  if4031.Interface.Token request,
                  io.grpc.stub.StreamObserver<if4031.Interface.GrpcVoid> responseObserver) {
                serviceImpl.exit(request, responseObserver);
              }
            }))).build();
  }
}

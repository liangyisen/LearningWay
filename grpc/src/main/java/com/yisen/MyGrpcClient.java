package com.yisen;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class MyGrpcClient {
    public static void main(String[] args) {
        ManagedChannelBuilder<?> localhost = ManagedChannelBuilder.forAddress("localhost", 9999);

        ManagedChannel channel = localhost.usePlaintext().build();

        HelloWorldRpcGrpc.HelloWorldRpcBlockingStub helloWorldRpcBlockingStub = HelloWorldRpcGrpc.newBlockingStub(channel);

        HelloWorldRespones yisen = helloWorldRpcBlockingStub.sayhello(HelloWorldRequest.newBuilder().setName("yisen").build());
        System.out.println("yisen = " + yisen.getCode());
    }
}

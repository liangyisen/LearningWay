package com.yisen;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class MyGrpcSerive {

    public static void main(String[] args) throws InterruptedException, IOException {
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(9999);
        Server build = serverBuilder.addService(new HelloWorldServiceImpl()).build();

        build.start();
        build.awaitTermination();
    }
}

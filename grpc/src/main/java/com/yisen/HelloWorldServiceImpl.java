//package com.yisen;
//
//import com.yisen.HelloWorldRpcGrpc.HelloWorldRpcImplBase;
//import io.grpc.stub.StreamObserver;
//
//public class HelloWorldServiceImpl extends HelloWorldRpcImplBase {
//
//    @Override
//    public void sayhello(HelloWorldRequest request, StreamObserver<HelloWorldRespones> responseObserver) {
//        responseObserver.onNext(HelloWorldRespones.newBuilder().setCode("222").build());
//        responseObserver.onCompleted();
//    }
//}

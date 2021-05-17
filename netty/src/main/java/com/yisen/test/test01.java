package com.yisen.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class test01 {
    public static void main(String[] args) {
        bind(8080);
    }

    public static void bind(int port) {
        EventLoopGroup boss = new NioEventLoopGroup();

        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap server = new ServerBootstrap();

            server.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new MessageHandler());

                        }

                    });

            ChannelFuture sync = server.bind(port).sync();

            sync.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();

        } finally {
            boss.shutdownGracefully();

            worker.shutdownGracefully();

        }

    }
}

class MessageHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        int readableBytes = buf.readableBytes();

        byte[] bytes = new byte[readableBytes];

        buf.readBytes(bytes);

        System.out.println(new String(bytes));

    }

    @Override

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("connection");

    }

}
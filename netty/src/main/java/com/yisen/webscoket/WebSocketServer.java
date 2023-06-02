package com.yisen.webscoket;

/**
 * @Author : yisen
 * @Date : 2023/6/2 12:03
 * @Description :
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class WebSocketServer {

	private static final int PORT = 8080;

	public static void main(String[] args) throws Exception {
		// 创建两个EventLoopGroup，一个用于接收连接，一个用于处理连接的IO事件
		NioEventLoopGroup bossGroup = new NioEventLoopGroup();
		NioEventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			// 创建ServerBootstrap实例，并配置参数
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) {
							ch.pipeline().addLast(new HttpServerCodec())
									.addLast(new HttpObjectAggregator(65536))
									.addLast(new WebSocketServerProtocolHandler("/websocket"))
									.addLast(new SimpleChannelInboundHandler<WebSocketFrame>() {
										@Override
										protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
											// 处理WebSocket帧
											System.out.println("Received WebSocket frame: " + frame);
										}
									});
						}
					});

			// 绑定端口，启动服务
			ChannelFuture f = b.bind(PORT).sync();
			System.out.println("WebSocket server started on port " + PORT);

			// 添加关闭事件监听器
			f.channel().closeFuture().addListener(future -> {
				bossGroup.shutdownGracefully();
				workerGroup.shutdownGracefully();
				System.out.println("WebSocket server stopped");
			}).sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
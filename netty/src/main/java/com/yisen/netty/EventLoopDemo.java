package com.yisen.netty;

import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;
import org.junit.Test;

public class EventLoopDemo {


    @Test
    public void aVoid() throws InterruptedException {
        //1.事件循环组 创建.入参 线程数 默认:NettyRuntime.availableProcessors()*2
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup(2);
        //第一次
        System.out.println(eventExecutors.next().hashCode());
        ////第二次
        System.out.println(eventExecutors.next().hashCode());
        //和第一次相同
        System.out.println(eventExecutors.next().hashCode());

        eventExecutors.submit(() -> {
            System.out.println("eventExecutors = " + eventExecutors.next().hashCode());
        });
        eventExecutors.submit(() -> {
            System.out.println("eventExecutors = " + eventExecutors.next().hashCode());
        });
        eventExecutors.submit(() -> {
            System.out.println("eventExecutors = " + eventExecutors.next().hashCode());
        });

        Thread.sleep(111111);
    }
}

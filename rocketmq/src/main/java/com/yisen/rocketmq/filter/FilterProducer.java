package com.yisen.rocketmq.filter;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;

public class FilterProducer {
    //nameserver地址
    private final static String NAMESRV_ADDR = "localhost:9876";

    private static final DefaultMQProducer PRODUCER = new DefaultMQProducer("PRODUCER-FILTER");

    private final static String TOPIC = "TOPIC_FILTER";


    @Test
    public void filterProducer1() throws Exception {

        System.out.println("MQ：启动ProducerTest生产者");
        PRODUCER.setNamesrvAddr(NAMESRV_ADDR);
        PRODUCER.start();
        System.out.println("MQ：ProducerTest生产者开启");

        //延迟发送消息
        Thread.sleep(100);
        ArrayList<Message> messageArrayList = new ArrayList<>();

        Message message1 = new Message(TOPIC, "tagA", "批量消息1".getBytes());
        Message message2 = new Message(TOPIC, "tagB", "批量消息2".getBytes());
        Message message3 = new Message(TOPIC, "tagC", "批量消息3".getBytes());

        messageArrayList.add(message1);
        messageArrayList.add(message2);
        messageArrayList.add(message3);
        SendResult send = PRODUCER.send(messageArrayList, 10000);
        System.out.println("MQ: 生产者 延迟消息已经发出 响应结果   " + send);

    }

    @After
    public void stop() {
        PRODUCER.shutdown();
        System.out.println("MQ：关闭ProducerTest生产者");
    }
}

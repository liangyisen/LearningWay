package com.yisen.rocketmq.order;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.After;
import org.junit.Test;

public class OrderProducer {
    //nameserver地址
    private final static String NAMESRV_ADDR = "localhost:9876";

    private static final DefaultMQProducer PRODUCER = new DefaultMQProducer("ProducerTest");

    private final static String TOPIC = "TOPIC_ORDER";


    @Test
    public void orderProducer1() throws Exception {

        System.out.println("MQ：启动ProducerTest生产者");
        PRODUCER.setNamesrvAddr(NAMESRV_ADDR);
        PRODUCER.start();
        System.out.println("MQ：ProducerTest生产者开启");

        //顺序发送消息
        String openDoor = "开门";
        String tuoxie = "脱鞋";
        String tuoyifu = "脱衣服";
        String shangChuang = "上床";
        Thread.sleep(100);
        Message openDoorMessage = new Message(TOPIC, openDoor.getBytes());
        SendResult send = PRODUCER.send(openDoorMessage, (mqs, msg, arg) -> mqs.get((Integer) arg), 5,10000);
        System.out.println("MQ: 生产者 消息已经发出 响应结果  " + send);
        Message tuoxieMessage = new Message(TOPIC, tuoxie.getBytes());
        SendResult send1 = PRODUCER.send(tuoxieMessage, (mqs, msg, arg) -> mqs.get((Integer) arg), 8,10000);
        System.out.println("MQ: 生产者 消息已经发出 响应结果   " + send1);
        Message tuoyifuMessage = new Message(TOPIC, tuoyifu.getBytes());
        SendResult send2 = PRODUCER.send(tuoyifuMessage, (mqs, msg, arg) -> mqs.get((Integer) arg), 5,10000);
        System.out.println("MQ: 生产者 消息已经发出 响应结果   " + send2);
        Message shangChuangMessage = new Message(TOPIC, shangChuang.getBytes());
        SendResult send3 = PRODUCER.send(shangChuangMessage, (mqs, msg, arg) -> mqs.get((Integer) arg), 8,10000);
        System.out.println("MQ: 生产者 消息已经发出 响应结果   " + send3);

    }

    @After
    public void stop() {
        PRODUCER.shutdown();
        System.out.println("MQ：关闭ProducerTest生产者");
    }
}

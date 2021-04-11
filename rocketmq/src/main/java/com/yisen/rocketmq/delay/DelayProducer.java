package com.yisen.rocketmq.delay;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.store.config.MessageStoreConfig;
import org.junit.After;
import org.junit.Test;

public class DelayProducer {
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

        //延迟发送消息
        Thread.sleep(100);
        Message delayMessage = new Message(TOPIC, "延迟消息".getBytes());
        //org.apache.rocketmq.store.config.MessageStoreConfig.messageDelayLevel
        //private String messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";
        delayMessage.setDelayTimeLevel(3);
        SendResult send = PRODUCER.send(delayMessage, 10000);
        System.out.println("MQ: 生产者 延迟消息已经发出 响应结果   " + send);

    }

    @After
    public void stop() {
        PRODUCER.shutdown();
        System.out.println("MQ：关闭ProducerTest生产者");
    }
}

package com.yisen.rocketmq.filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class FilterConsumer {

    private static final String NAMESRV_ADDR = "localhost:9876";

    private final static String TOPIC_TEST = "TOPIC_FILTER";


    private static final DefaultMQPushConsumer PUSH_CONSUMER = new DefaultMQPushConsumer("filter-consumer");


    @Test
    public void filterConsumer1() throws Exception {
        PUSH_CONSUMER.setNamesrvAddr(NAMESRV_ADDR);
        PUSH_CONSUMER.setMessageModel(MessageModel.CLUSTERING);
        PUSH_CONSUMER.subscribe(TOPIC_TEST, "tagA");
        //注册消息监听器
        PUSH_CONSUMER.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            for (MessageExt msg : msgs) {
                String messageBody = null;
                try {
                    messageBody = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                System.out.println("当前线程:" + Thread.currentThread().getName() + "  消费延迟队列Queue = " + messageBody + " 延迟时间 " + (System.currentTimeMillis() - msg.getStoreTimestamp()));
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        //启动消费端
        PUSH_CONSUMER.start();
        System.out.println("MQ: 消费者1开启 ! ");
        Thread.sleep(11111111111L);
    }
}

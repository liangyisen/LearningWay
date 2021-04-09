package com.yisen.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;

public class RocketMqConsumerPush {

    private static final String namesrvAddr = "localhost:9876";

    private final static String TOPIC_TEST = "TOPIC_TEST";

    private final static String TAG_TEST = "TAG_TEST";

    private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer1");

    public static void main(String[] args) throws MQClientException {
        RocketMqConsumerPush rrokcetMqConsumer = new RocketMqConsumerPush();

        rrokcetMqConsumer.consumer.setNamesrvAddr(namesrvAddr);
        rrokcetMqConsumer.consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        //设置广播消费模式
        rrokcetMqConsumer.consumer.setMessageModel(MessageModel.BROADCASTING);

        //订阅主题
        rrokcetMqConsumer.consumer.subscribe(TOPIC_TEST, TAG_TEST);

        //注册消息监听器
        rrokcetMqConsumer.consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            for (MessageExt msg : msgs) {
                byte[] body = msg.getBody();
                String messageBody = null;
                try {
                    messageBody = new String(body, RemotingHelper.DEFAULT_CHARSET);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                System.out.println("消费Queue = " + messageBody);
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        //启动消费端
        rrokcetMqConsumer.consumer.start();

    }
}


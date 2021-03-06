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

    private static final String NAMESRV_ADDR = "localhost:9876";

    private final static String TOPIC_TEST = "TOPIC_TEST";

    private final static String TAG_TEST = "TAG_TEST";

    private static final DefaultMQPushConsumer PUSH_CONSUMER = new DefaultMQPushConsumer("PUSH-consumer");

    public static void main(String[] args) throws MQClientException {

        PUSH_CONSUMER.setNamesrvAddr(NAMESRV_ADDR);
//        PUSH_CONSUMER.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        PUSH_CONSUMER.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        //设置广播消费模式
//        PUSH_CONSUMER.setMessageModel(MessageModel.BROADCASTING);
        //设置集群消费
        PUSH_CONSUMER.setMessageModel(MessageModel.CLUSTERING);


        //订阅主题
        PUSH_CONSUMER.subscribe(TOPIC_TEST, TAG_TEST);

        //注册消息监听器
        PUSH_CONSUMER.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            for (MessageExt msg : msgs) {
                byte[] body = msg.getBody();
                String messageBody = null;
                try {
                    messageBody = new String(body, RemotingHelper.DEFAULT_CHARSET);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                System.out.println("消费Queue = " + messageBody + "        >>> " + msg);
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        //启动消费端
        PUSH_CONSUMER.start();

    }
}


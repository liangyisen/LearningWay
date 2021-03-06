package com.yisen.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.consumer.store.OffsetStore;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class RocketMqConsumerPull {
    private static final Map<MessageQueue, Long> OFFSE_TABLE = new HashMap<>();

    private static final String NAMESRV_ADDR = "localhost:9876";

    private final static String TOPIC_TEST = "TOPIC_TEST";

    private final static String TAG_TEST = "TAG_TEST";

    private static final DefaultMQPullConsumer PULL_CONSUMER = new DefaultMQPullConsumer("PULL-consumer");


    public static void main(String[] args) throws MQClientException {

        PULL_CONSUMER.setNamesrvAddr(NAMESRV_ADDR);

        //设置广播消费模式
//        PULL_CONSUMER.setMessageModel(MessageModel.BROADCASTING);
        //设置集群消费
        PULL_CONSUMER.setMessageModel(MessageModel.CLUSTERING);


        //启动消费端
        PULL_CONSUMER.start();
        //订阅主题
        Set<MessageQueue> messageQueues = PULL_CONSUMER.fetchSubscribeMessageQueues(TOPIC_TEST);

        for (MessageQueue mq : messageQueues) {
            System.out.printf("Consume from the queue: %s%n", mq);
            SINGLE_MQ:
            while (true) {
                try {
                    PullResult pullResult = PULL_CONSUMER.pullBlockIfNotFound(mq, TAG_TEST, getMessageQueueOffset(mq), 32);
                    System.out.printf("每次拉取的的结果%s%n", pullResult);
                    System.out.println("                    ");
                    putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                    List<MessageExt> msgFoundList = pullResult.getMsgFoundList();
                    if (!msgFoundList.isEmpty()) {
                        for (MessageExt messageExt : msgFoundList) {
                            byte[] body = messageExt.getBody();
                            try {
                                String messageBody = new String(body, RemotingHelper.DEFAULT_CHARSET);
                                System.out.println("消费的消息= " + messageBody);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    switch (pullResult.getPullStatus()) {
                        case FOUND:
                            break;
                        case NO_MATCHED_MSG:
                            break;
                        case NO_NEW_MSG:
                            break SINGLE_MQ;
                        case OFFSET_ILLEGAL:
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = OFFSE_TABLE.get(mq);
        if (offset != null)
            return offset;

        return 0;
    }

    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        OFFSE_TABLE.put(mq, offset);
    }

}

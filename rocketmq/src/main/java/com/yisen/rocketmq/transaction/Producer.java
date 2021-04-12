package com.yisen.rocketmq.transaction;

import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.After;
import org.junit.Test;

public class Producer {
    //nameserver地址
    private final static String NAMESRV_ADDR = "localhost:9876";

    private static final TransactionMQProducer PRODUCER = new TransactionMQProducer();

    private final static String TOPIC = "TOPIC_TRANSACTION";


    @Test
    public void producer1() throws Exception {

        System.out.println("MQ：启动ProducerTest生产者");
        PRODUCER.setNamesrvAddr(NAMESRV_ADDR);
        PRODUCER.setProducerGroup("transaction-producer");
        PRODUCER.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                if ("tag0".equals(msg.getTags())) {
                    return LocalTransactionState.COMMIT_MESSAGE;
                } else if ("tag1".equals(msg.getTags())) {
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }
                return null;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                System.out.println("mq回查消息msg = " + msg);
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });
        PRODUCER.start();
        System.out.println("MQ：ProducerTest生产者开启");


        Thread.sleep(100);


        for (int i = 0; i < 3; i++) {
            Message message = new Message(TOPIC, "tag" + i, "事务消息1".getBytes());
            //事务发送消息
            TransactionSendResult transactionSendResult = PRODUCER.sendMessageInTransaction(message, null);
            System.out.println("MQ: 生产者 事务消息已经发出 响应结果   " + transactionSendResult);
        }

        Thread.sleep(111111L);
    }

    @After
    public void stop() {
        PRODUCER.shutdown();
        System.out.println("MQ：关闭ProducerTest生产者");
    }
}

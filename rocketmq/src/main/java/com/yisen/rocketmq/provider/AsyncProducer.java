package com.yisen.rocketmq.provider;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.After;
import org.junit.Test;

import javax.annotation.PreDestroy;

public class AsyncProducer {
    //nameserver地址
    private final static String NAMESRV_ADDR = "localhost:9876";

    private static final DefaultMQProducer PRODUCER = new DefaultMQProducer("ProducerTest");

    private final static String TOPIC_TEST = "TOPIC_TEST";

    private final static String TAG_TEST = "TAG_TEST";

    @Test
    public void asyncProducter1() throws Exception {

        System.out.println("MQ：启动ProducerTest生产者");
        PRODUCER.setNamesrvAddr(NAMESRV_ADDR);
        PRODUCER.start();
        System.out.println("MQ：ProducerTest生产者开启");

        //发送消息
        for (int i = 0; i < 10; i++) {
            String data = ("hello mq " + i);
            System.out.print("MQ: 生产者 发送消息 :%s%n" + data);

            try {
                //转换成字符数组
                byte[] messageBody = data.getBytes(RemotingHelper.DEFAULT_CHARSET);
                Message message = new Message(TOPIC_TEST, TAG_TEST, String.valueOf(System.currentTimeMillis()), messageBody);
                //异步发送消息
                PRODUCER.send(message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println("MQ: 生产者 异步回调监听回调结果 : " + sendResult);
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        System.out.println(throwable.getMessage() + throwable);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(5);
        }
    }

    @After
    public void stop() {
        PRODUCER.shutdown();
        System.out.println("MQ：关闭ProducerTest生产者");
    }

}

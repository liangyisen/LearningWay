package com.yisen.rocketmq.provider;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import javax.annotation.PreDestroy;

public class RocketmqProviderDemo {
    //nameserver地址
    private final static String NAMESRV_ADDR = "localhost:9876";

    private final DefaultMQProducer producer = new DefaultMQProducer("ProducerTest");

    private final static String TOPIC_TEST = "TOPIC_TEST";

    private final static String TAG_TEST = "TAG_TEST";


    public static void main(String[] args) {
        RocketmqProviderDemo rocketmqDemo = new RocketmqProviderDemo();
        rocketmqDemo.start();
    }

    /**
     * 初始化
     */
    public void start() {
        try {
            System.out.println("MQ：启动ProducerTest生产者");
            producer.setNamesrvAddr(NAMESRV_ADDR);
            producer.start();
            //发送消息
            for (int i = 0; i < 100; i++) {
                sendMessage("hello mq " + i);
                Thread.sleep(5);
            }
//            producer.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String data) {
        System.out.println("MQ: 生产者发送消息 :{}" + data);
        Message message = null;
        try {
            //转换成字符数组
            byte[] messageBody = data.getBytes(RemotingHelper.DEFAULT_CHARSET);
            //创建消息对象
            message = new Message(TOPIC_TEST, TAG_TEST, String.valueOf(System.currentTimeMillis()), messageBody);
            //同步发送消息
            SendResult sendResult = producer.send(message);
            System.out.println("topic: " + TOPIC_TEST + " | tag:" + TAG_TEST + "| sendResult: " + sendResult);
            //异步发送消息
            /*producer.send(message, new SendCallback() {
                public void onSuccess(SendResult sendResult) {
                    System.out.println("MQ: CouponProducer生产者发送消息" + sendResult);
                }

                public void onException(Throwable throwable) {
                    System.out.println(throwable.getMessage() +  throwable);
                }
            });*/
            //单向发送 只发送消息，不等待服务器响应，只发送请求不等待应答。
            //producer.sendOneway(message);
        } catch (Exception e) {
            if (message != null) {
                System.out.println("producerGroup:ProducerTest,Message:{}" + JSON.toJSON(message));
            }
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void stop() {
        if (producer != null) {
            producer.shutdown();
            System.out.println("MQ：关闭ProducerTest生产者");
        }
    }


}

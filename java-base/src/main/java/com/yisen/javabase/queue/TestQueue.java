package com.yisen.javabase.queue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestQueue {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strings = new ArrayBlockingQueue<String>(10);

        strings.add("1");

        System.out.println("strings = " + strings);
        strings.put("2");
        System.out.println("strings = " + strings);
        strings.offer("3");
        System.out.println("strings = " + strings);
        int i = strings.remainingCapacity();
        System.out.println(i);
        String take = strings.take();

    }

    /**
     * 测试LinkedBlockingQueue
     */
    @Test
    public void testLinkedBlockingQueue() {
        BlockingQueue<Object> objects = new LinkedBlockingQueue<>();

        new Thread(() -> {
            try {
                while (true) {
                    Object take = objects.take();
                    System.out.println("take = " + take);
                    //
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("e = " + e);
            }
        }).start();

        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000);
                    objects.put("1");
                    System.out.println("put = " + 1);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //主线程阻塞
        try {
            Thread.sleep(1011100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

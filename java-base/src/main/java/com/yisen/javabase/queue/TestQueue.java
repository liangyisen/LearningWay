package com.yisen.javabase.queue;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

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
}

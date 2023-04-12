package com.yisen.javabase.JUCpack;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderDemo {

    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();

        longAdder.add(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                longAdder.add(2);
            }
        }).start();

        System.out.println("longAdder = " + longAdder);


        AtomicLong atomicLong = new AtomicLong(1);
        long l = atomicLong.addAndGet(1);
        System.out.println("l = " + l);
    }
}

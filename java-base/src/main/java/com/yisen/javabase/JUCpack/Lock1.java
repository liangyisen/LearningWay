package com.yisen.javabase.JUCpack;

import org.junit.Test;

import java.sql.Time;
import java.util.concurrent.locks.ReentrantLock;

public class Lock1 {

    static int i = 0;
    ReentrantLock lock = new ReentrantLock();

    @Test
    public void alock() throws InterruptedException {

        for (int j = 0; j < 1010; j++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        lock.lock();
                        i++;
                    } finally {
                        lock.unlock();
                    }
                }
            }).start();
        }
        Thread.sleep(1111);
        System.out.println("i = " + i);
    }
}

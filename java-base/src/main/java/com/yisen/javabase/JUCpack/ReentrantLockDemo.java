package com.yisen.javabase.JUCpack;

import org.junit.Test;

public class ReentrantLockDemo {

    static int i = 0;
    java.util.concurrent.locks.ReentrantLock lock = new java.util.concurrent.locks.ReentrantLock();

    @Test
    public void alock() throws InterruptedException {

        for (int j = 0; j < 1010; j++) {
            new Thread(() -> {
                try {
                    lock.lock();
                    i++;
                } finally {
                    lock.unlock();
                }
            }).start();
        }
        Thread.sleep(1111);
        System.out.println("i = " + i);
    }
}

package com.yisen.javabase.Algorithm.tokenBucket;

import com.google.common.util.concurrent.RateLimiter;

import java.sql.SQLOutput;
import java.util.concurrent.Semaphore;

public class GuavaDemo {

    public static void main(String[] args) throws InterruptedException {
        // 代码1
        RateLimiter limiter = RateLimiter.create(10);
        //步骤1
//        Thread.sleep(1000);
        //代码2
        if (limiter.tryAcquire(20)) {
            System.out.println("======== Time1:" + System.currentTimeMillis() / 1000);
        }


        Thread.sleep(1001);
        //代码3
        if (limiter.tryAcquire(1)) {
            System.out.println("======== Time2:" + System.currentTimeMillis() / 1000);
        }

        if (limiter.tryAcquire(5)) {
            System.out.println("======== Time3:" + System.currentTimeMillis() / 1000);
        }


        Semaphore semaphore = new Semaphore(2);

        semaphore.acquire();
        System.out.println("semaphore = " + semaphore);
        semaphore.acquire();
        System.out.println("semaphore1 = " + semaphore);
        semaphore.acquire();
        System.out.println("semaphore 2= " + semaphore);
        semaphore.release();
        semaphore.acquire();


    }
}

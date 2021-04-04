package com.yisen.javabase.Algorithm.tokenBucket;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

public class Demo {


    private static RateLimiter rateLimiter = new RateLimiter(20 );


    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
//                int nextInt = new Random(1000L).nextInt();
//                try {
//                    Thread.sleep(nextInt);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                boolean acquire = rateLimiter.acquire();
                if (acquire) {
                    System.out.println("acquire1 = " + acquire);
                    System.out.println("anInt");
                    try {
                        int anInt = new Random(10000L).nextInt();
                        Thread.sleep(anInt);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("acquire2 = " + acquire);
                }
            }).start();
        }

        Thread.sleep(10000000L);
    }


}


/**
 * 限流器
 */
class RateLimiter {

    private volatile int token;
    private final int originToken;

    private static Unsafe unsafe = null;
    private static final long valueOffset;
    private final Object lock = new Object();

    static {
        try {
            // 应用开发中使用unsafe对象必须通过反射获取
            Class<?> clazz = Unsafe.class;
            Field f = clazz.getDeclaredField("theUnsafe");
            f.setAccessible(true);

            unsafe = (Unsafe) f.get(clazz);
            valueOffset = unsafe.objectFieldOffset(RateLimiter.class.getDeclaredField("token"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    public RateLimiter(int token) {
        this.originToken = token;
        this.token = token;
    }

    /**
     * 获取一个令牌
     */
    public boolean acquire() {
        int current = token;
        if (current <= 0) {
            // 保证在token已经用光的情况下依然有获取竞争的能力
            current = originToken;
        }

        long expect = 1000;// max wait 1s
        long future = System.currentTimeMillis() + expect;
        while (current > 0) {
            if (compareAndSet(current, current - 1)) {
                return true;
            }
            current = token;
            if (current <= 0 && expect > 0) {
                // 在有效期内等待通知
                synchronized (lock) {
                    try {
                        lock.wait(expect);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                current = token;
                if (current <= 0) {
                    current = originToken;
                }
                expect = future - System.currentTimeMillis();
            }
        }
        return false;
    }

    private boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }

    /**
     * 补充令牌
     */
    public void supplement(final ExecutorService executorService) {
        this.token = originToken;
        executorService.execute(() -> {
            synchronized (lock) {
                lock.notifyAll();
            }
        });
    }

}

/**
 * 限流管理器
 */
class ConfineManager {

    // 定时线程
    private final ScheduledThreadPoolExecutor scheduledCheck = new ScheduledThreadPoolExecutor(2);
    // 执行补充线程池
    private final ExecutorService executorService = new ThreadPoolExecutor(5, 200,
            60L, TimeUnit.SECONDS, new SynchronousQueue<>()
    );

    // 限流器容器
    private Map<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();


    {
        scheduledCheck.scheduleAtFixedRate(new SupplementRateLimiter(), 1, 1, TimeUnit.SECONDS);
    }


    /**
     * 通过key获取相应的限流器
     */
    public void acquire(String key, int tokenCount) {
        RateLimiter rateLimiter = rateLimiterMap.get(key);
        // 双检锁确保安全创建
        if (rateLimiter == null) {
            synchronized (this) {
                // init RateLimiter
                rateLimiter = rateLimiterMap.computeIfAbsent(key, k -> new RateLimiter(tokenCount));
            }
        }
        // 尝试获取令牌
        if (!rateLimiter.acquire()) {
            // 获取失败，根据实际情况进行处理，这里直接抛异常了
            throw new RuntimeException();
        }
    }

    /**
     * 补充相应的令牌数
     */
    private class SupplementRateLimiter implements Runnable {
        @Override
        public void run() {
            rateLimiterMap.values().forEach(rateLimiter -> rateLimiter.supplement(executorService));
        }
    }

}

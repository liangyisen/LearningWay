package com.yisen.ditributedLock.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * 使用redisson实现的分布式锁
 * redisson.lock() 底层使用的lua脚本实现的
 * https://zhuanlan.zhihu.com/p/111354065?from_voters_page=true 讲的明白
 */
public class RedissonDemo1 {

    private static RedissonClient redisson;

    static {
        Config config = new Config();
//        config.useClusterServers()
//                .setScanInterval(2000)
//                .addNodeAddress("redis://localhost:6379");

        config.useSingleServer().setAddress("redis://localhost:6379");
        redisson = Redisson.create(config);
    }

    public static void main(String[] args) throws InterruptedException {
        RLock lock = redisson.getLock("lock1");

        RLock redLock = redisson.getRedLock(lock);//红锁
        redLock.lock();

        try {
            System.out.println("处理业务");
            Thread.sleep(8);
        } finally {
            lock.unlock();
        }
        System.out.println("结束");
        redisson.shutdown();
    }
}

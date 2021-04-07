package com.yisen.ditributedLock.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class ZkLock {

    public static void main(String[] args) throws Exception {
        String zkAddr = "112.125.80.159:2181";////9
        String lockPath = "/distribute-lock";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(10000, 0);
        CuratorFramework cf = CuratorFrameworkFactory.builder()
                .connectString(zkAddr)
                .sessionTimeoutMs(2000)
                .retryPolicy(retryPolicy)
                .build();
        cf.start();
        InterProcessMutex interProcessMutex = new InterProcessMutex(cf, lockPath);

        interProcessMutex.acquire();
        System.out.println("interProcessMutex = " + interProcessMutex);
        Thread.sleep(11111);
        interProcessMutex.release();
    }
}


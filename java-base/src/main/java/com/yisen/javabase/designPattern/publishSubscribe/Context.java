package com.yisen.javabase.designPattern.publishSubscribe;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * by yisen
 */
// 职责1：维护订阅关系。
// 职责2：接受到事件消息时，完成分发
public class Context {
    private static final ConcurrentHashMap<String, ConcurrentLinkedQueue<User>> subscribeMap = new ConcurrentHashMap<>();

    // 订阅
    public boolean subscribe(String publishUserId, User subscribeUser) {
        if (!subscribeMap.containsKey(publishUserId)) {
            subscribeMap.putIfAbsent(publishUserId, new ConcurrentLinkedQueue<User>());
        }
        return subscribeMap.get(publishUserId).add(subscribeUser);
    }

    public boolean unsubscribe(String publishUserId, User subscribeUser) {
        return subscribeMap.get(publishUserId).remove(subscribeUser);
    }

    // 分发
    public void publish(String userId, String message) {
        User[] iUsers = subscribeMap.get(userId).toArray(new User[subscribeMap.get(userId).size()]);
        for (User user : iUsers) {
            user.subscribe(userId, message);
        }
    }
}
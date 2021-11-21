package com.yisen.javabase.designPattern.publishSubscribe;

/**
 * by yisen
 */
public class User {
    private String userId;
    private Context context;

    public User(String userId, Context context){
        this.userId = userId;
        this.context = context;
    }

    // 发布信息
    public void publish(String message){
        context.publish(userId,message);
    }

    // 订阅消息通知入口
    public void subscribe(String userId, String message) {
        System.out.println(this.userId + "： message from " + userId + " - " + message);
    }
}

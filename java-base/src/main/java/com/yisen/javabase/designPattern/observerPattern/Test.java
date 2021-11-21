package com.yisen.javabase.designPattern.observerPattern;

import com.yisen.javabase.designPattern.observerPattern.observer.Observer;
import com.yisen.javabase.designPattern.observerPattern.subject.Subject;

/**
 * by yisen
 */
// 观察者模式(发布订阅模式)：Observer需在Subject对象内进行注册，当发布消息时，Subject将消息提交给Observer。
// Subject对象中发布消息的功能，与维护Observer列表相耦合，可进一步拆分，实现单一职责。
public class Test {
    public static void main(String[] args) {
        Observer observerA = new Observer();
        Observer observerB = new Observer();

        Subject<String> subject = new Subject<>();

        subject.register(observerA);
        subject.register(observerB);

        subject.notify("字符串类型消息");

    }
}

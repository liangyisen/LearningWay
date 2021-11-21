package com.yisen.javabase.designPattern.observerPattern.subject;

import com.yisen.javabase.designPattern.observerPattern.observer.IObserver;

/**
 * 被观察者，提供注册和注销功能(维护该事件的观察者列表)，以及事件触发方法notify，在notify中通知所有观察者。
 * by yisen
 */
public interface ISubject<E> {

    boolean register(IObserver<E> observer);

    boolean deregister(IObserver<E> observer);

    void notify(E event);
}

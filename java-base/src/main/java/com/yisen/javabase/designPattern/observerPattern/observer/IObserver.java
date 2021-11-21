package com.yisen.javabase.designPattern.observerPattern.observer;

/**
 * 观察者，提供一个被通知方法(update - 回调方法)
 * by yisen
 */
public interface IObserver<E> {
    void update(E event);
}
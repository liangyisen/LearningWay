package com.yisen.javabase.designPattern.observerPattern.observer;

/**
 * by yisen
 */
public class Observer implements IObserver {
    @Override
    public void update(Object event) {
        System.out.println("Observer：receive message : " + event);
    }
}

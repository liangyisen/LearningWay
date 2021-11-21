package com.yisen.javabase.designPattern.observerPattern.subject;

import com.yisen.javabase.designPattern.observerPattern.observer.IObserver;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * by yisen
 */
public class Subject<E> implements ISubject<E> {
    private ConcurrentLinkedQueue<IObserver> observers = new ConcurrentLinkedQueue<>();

    @Override
    public boolean register(IObserver<E> observer) {
        return observers.add(observer);
    }

    @Override
    public boolean deregister(IObserver<E> observer) {
        return observers.remove(observer);
    }

    @Override
    public void notify(E event) {
        IObserver[] iObservers = observers.toArray(new IObserver[observers.size()]);
        for (IObserver iObserver : iObservers) {
            iObserver.update(event);
        }
    }
}

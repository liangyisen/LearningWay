package com.yisen.javabase.designPattern.proxy;

import org.junit.Test;

public class StaticProxy {

    private Action action;

    public StaticProxy(Action action) {
        this.action = action;
    }

    public void enhanceStudy(){
        System.out.println("test");
        System.out.println("学习前 喝杯水");

        action.study();

        System.out.println("学习结束");
    }
}

package com.yisen.javabase.designPattern;

import com.yisen.javabase.designPattern.proxy.GirlFirend;
import com.yisen.javabase.designPattern.proxy.StaticProxy;
import org.junit.Test;

public class aTest {


    @Test
    public void asdf() {
        GirlFirend girlFirend = new GirlFirend();
        StaticProxy staticProxy = new StaticProxy(girlFirend);
        staticProxy.enhanceStudy();
    }
}

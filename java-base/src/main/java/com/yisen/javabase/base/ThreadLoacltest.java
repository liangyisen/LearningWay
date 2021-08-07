package com.yisen.javabase.base;

public class ThreadLoacltest {


    ThreadLocal a = new ThreadLocal<String>();

    public void aaa() {
        a.set(1);
        c();
    }


    public void c() {
        System.out.println("a.get() = " + a.get());
    }

    public static void main(String[] args) {
        ThreadLoacltest threadLoacltest = new ThreadLoacltest();
        threadLoacltest.aaa();
    }
}

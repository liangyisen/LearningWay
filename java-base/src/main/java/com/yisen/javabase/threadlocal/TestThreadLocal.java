package com.yisen.javabase.threadlocal;

import org.junit.Test;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadLocal {

    /**
     * integer_thread_local
     */
    public static final ThreadLocal<Integer> INTEGER_THREAD_LOCAL = new ThreadLocal<>();
    /**
     * integer_inheritable_thread_local
     */
    public static final InheritableThreadLocal<Integer> INTEGER_INHERITABLE_THREAD_LOCAL = new InheritableThreadLocal<>();

    @Test
    public void test01() {
        INTEGER_THREAD_LOCAL.set(11);
        INTEGER_INHERITABLE_THREAD_LOCAL.set(111);
        new Thread(() -> run()).start();
        System.out.println(Thread.currentThread().getName() + " integer_thread_local = " + INTEGER_THREAD_LOCAL.get());
        System.out.println(Thread.currentThread().getName() + " integer_inheritable_thread_local = " + INTEGER_INHERITABLE_THREAD_LOCAL.get());
    }

    private void run() {
        INTEGER_THREAD_LOCAL.set(22);
        INTEGER_THREAD_LOCAL.set(33);
        INTEGER_THREAD_LOCAL.remove();
        System.out.println(Thread.currentThread().getName() + " integer_thread_local= " + INTEGER_THREAD_LOCAL.get());
        System.out.println(Thread.currentThread().getName() + " integer_inheritable_thread_local = " + INTEGER_INHERITABLE_THREAD_LOCAL.get());
    }


    public ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 1, TimeUnit.SECONDS, new LinkedBlockingDeque());

    @Test
    public void test02() {
        INTEGER_THREAD_LOCAL.set(888);
        INTEGER_INHERITABLE_THREAD_LOCAL.set(991);
        threadPoolExecutor.execute(this::run2);
        INTEGER_INHERITABLE_THREAD_LOCAL.set(992);
        threadPoolExecutor.execute(this::run2);
        INTEGER_INHERITABLE_THREAD_LOCAL.set(993);
        threadPoolExecutor.execute(this::run2);
        INTEGER_INHERITABLE_THREAD_LOCAL.set(994);
        threadPoolExecutor.execute(this::run2);
        INTEGER_INHERITABLE_THREAD_LOCAL.set(995);
        threadPoolExecutor.execute(this::run2);
        INTEGER_INHERITABLE_THREAD_LOCAL.set(996);
        threadPoolExecutor.execute(this::run2);
        INTEGER_INHERITABLE_THREAD_LOCAL.set(997);
        threadPoolExecutor.execute(this::run2);
        INTEGER_INHERITABLE_THREAD_LOCAL.set(998);
        threadPoolExecutor.execute(this::run2);
        while (true) {
        }
    }

    private void run2() {
        System.out.println(Thread.currentThread().getName() + " integer_inheritable_thread_local = " + INTEGER_INHERITABLE_THREAD_LOCAL.get());
    }
}

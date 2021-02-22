package com.yisen.javabase.base;

public class EffectivelyFinalDemo {

    public static void main(String[] args) {
        //局部内部类和匿名内部类访问的局部变量必须由final修饰，java8开始，可以不加final修饰符，由系统默认添加
        //因此下面两句的效果是一样的
        //final int age=99;
        int age = 99;

        //运行代码 <1>将会抛出以下错误
        //EffectivelyFinalDemo.java:14: 错误: 从内部类引用的本地变量必须是最终变量或实际上最终变量
        //age=11;
        A a = () -> System.out.println(age);
        a.test();
    }
}


/**
 * 接口
 */
interface A {
    void test();
}
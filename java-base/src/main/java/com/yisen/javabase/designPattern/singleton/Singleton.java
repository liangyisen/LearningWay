package com.yisen.javabase.designPattern.singleton;


public class Singleton {


    private static volatile Singleton singleton;

    private Singleton() {
    }

    public static Singleton getSingleton() {
        System.out.println(1);
        if (singleton == null) {
            System.out.println(2);
            synchronized (Singleton.class) {
                System.out.println(3);
                if (singleton == null) {
                    System.out.println(4);
                    singleton = new Singleton();
                    System.out.println(5);
                }
            }
        }
        return singleton;
    }


    public static void main(String[] args) {
        Singleton singleton = Singleton.getSingleton();

        Singleton singleton1 = Singleton.getSingleton();

        System.out.println(singleton1 == singleton);
    }

}

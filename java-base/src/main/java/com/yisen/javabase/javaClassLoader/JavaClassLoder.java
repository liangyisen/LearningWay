package com.yisen.javabase.javaClassLoader;

import sun.net.spi.nameservice.dns.DNSNameService;

public class JavaClassLoder {

    public static void main(String[] args) {
        //1.jvm 根加载器Bootstrap 是打不出来的 null
        System.out.println("Object.class.getClassLoader() = " + Object.class.getClassLoader());

        //2.扩展类加载器 extension
        System.out.println("DNSNameService.class.getClassLoader() = " + DNSNameService.class.getClassLoader());

        //3.系统类加载器 system
        ClassLoader classLoader = JavaClassLoder.class.getClassLoader();
        System.out.println("this.getClass().getClassLoader() = " + classLoader);


        // 根加载器Bootstrap <-扩展类加载器 extension <-系统类加载器 system
        System.out.println("" +
                "");
        System.out.println("------------------------------------");
        System.out.println("根加载器Bootstrap <-扩展类加载器 extension <-系统类加载器 system");
        while (classLoader != null) {
            System.out.println("JavaClassLoder.class.getClassLoader() = " + classLoader);
            classLoader = classLoader.getParent();
            System.out.println("JavaClassLoder.class.getClassLoader().getParent() = " + classLoader);
        }


        //类加载的过程 .
        /**
         * 1.加载
         * 2.连接
         *    2.1验证
         *    2.2准备 -->静态常量赋默认值
         *    2.3解析
         * 3.初始化 --> 静态常量的初始化值
         */
    }
}

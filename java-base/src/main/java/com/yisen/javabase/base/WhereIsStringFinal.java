package com.yisen.javabase.base;

import java.util.ArrayList;

/**
 *
 * 字符串常量池在哪儿
 *
 * error
 *
 * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
 * 	at java.lang.Integer.toString(Integer.java:401)
 * 	at java.lang.String.valueOf(String.java:3099)
 * 	at com.yisen.javabase.字符串常量池在哪儿.main(字符串常量池在哪儿.java:18)
 */
public class WhereIsStringFinal {

    public static void main(String[] args) {
        String str = "abc";
        char[] array = {'a', 'b', 'c'};
        String str2 = new String(array);
        //使用intern()将str2字符串内容放入常量池
        str2 = str2.intern();
        //这个比較用来说明字符串字面常量和我们使用intern处理后的字符串是在同一个地方
        System.out.println(str == str2);
        //那好，以下我们就拼命的intern吧
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 10000000; i++) {
            String temp = String.valueOf(i).intern();
            list.add(temp);
        }
    }
}

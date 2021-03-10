package com.yisen.javabase.failFast;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 快速失败示例：
 */
public class Demo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a1");
        list.add("a2");
        list.add("a3");
        list.add("a4");
        Iterator<String> it = list.iterator();
        System.out.println("list1:" + list);
        while (it.hasNext()) {
            String str = it.next();            //此处引发 java.util.ConcurrentModificationException异常
            if (str.equals("a3")) {
                list.remove("a3");
            }
        }
        //
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("a3")) {
                list.remove("a3");
            }
        }


        System.out.println("list2:" + list);
    }


    @Test
    public void FailSafe(){
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("不只Java-1", 1);
        concurrentHashMap.put("不只Java-2", 2);
        concurrentHashMap.put("不只Java-3", 3);

        Set set = concurrentHashMap.entrySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            concurrentHashMap.put("下次循环正常执行", 4);
        }
        System.out.println("程序结束");

    }
}

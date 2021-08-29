package com.yisen.javabase.mock;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;


public class TestMock {

    /**
     * 1、使用 Mockito.mock(clazz) 方式
     */
    @Test
    public void test01() {


        ArrayList mock = mock(ArrayList.class);
        System.out.println("mock = " + mock);
    }

    /**
     * 2、使用 @Mock 注解方式
     */
    @Test
    public void test02() {
        int a = 10;

        int count = 1;

        for (int i = 1; ; i++) {

            int counta = (int) (count + Math.pow(2, i));
            if (counta >= a) {
                break;
            } else {
                count = counta;
            }
        }
        int resulte = a - count;
        System.out.println("resulte = " + resulte);
    }

    @Test
    public void test03() throws IOException {


    }

}

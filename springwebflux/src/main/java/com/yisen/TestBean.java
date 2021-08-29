package com.yisen;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class TestBean {


    @Test
    public void testbean() {
        AnnotationConfigApplicationContext aac = new AnnotationConfigApplicationContext(IocBean.class);
        ApiHandler bean = aac.getBean(ApiHandler.class);
        ApiHandler bean2 = aac.getBean(ApiHandler.class);
        System.out.println("bean = " + bean);
        System.out.println("bean2 = " + bean2);


    }

    @Test
    public void testFile() throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("./test.log");
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer wrap = ByteBuffer.wrap("1111手动阀第三方".getBytes());

        channel.write(wrap);

        fileOutputStream.close();
    }
}

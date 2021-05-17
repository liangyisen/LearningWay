package com.yisen.nio;


import com.yisen.nio.util.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

@Slf4j
public class NioDemo {
    /**
     * 一.简单利用channel和buffer读文件
     */
    @Test
    public void test01() {
        //1.输入输出流.   2.RandomAccessFile
        try (FileChannel channel = new FileInputStream("H:\\IDEA_WORKSPACE\\New Core\\LearningWay\\netty\\a.txt").getChannel()) {
            //准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true) {
                //从channel读.向buffer写
                int read = channel.read(buffer);
                log.debug("读取到的字节-----{}", read);
                if (read == -1) {
                    break;
                }
                //打印buffer内容
                buffer.flip();//切换读模式
                //获取数据
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();//读一个字节
                    log.debug("读取到的字符:{}", ((char) b));

                }
                buffer.clear();//切换成写模式

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 二.利用工具类了解buffer内部结构
     */
    @Test
    public void test02() {
        System.out.println();
        System.out.println("1.********************");
        ByteBuffer allocate = ByteBuffer.allocate(10);
        allocate.put((byte) 0x61);//a
        ByteBufferUtil.debugAll(allocate);
        System.out.println();
        System.out.println("2.********************");
        //
        allocate.put(new byte[]{0x62, 0x63, 0x64, 0x65, 0x66});
        ByteBufferUtil.debugAll(allocate);
        log.debug("在写模式 读 :{} ", (char) allocate.get());
        System.out.println();
        System.out.println("3.********************");
        //切换读模式读
        allocate.flip();
        log.debug("切换读模式 读 :{} ", (char) allocate.get());
        log.debug("切换读模式 读 :{} ", (char) allocate.get());
        System.out.println();
        System.out.println("4.********************");
        //使用compact 压缩模式
        allocate.compact();
        ByteBufferUtil.debugAll(allocate);
    }

    /**
     * 三.buffer 内存分配
     */
    @Test
    public void test03() {
        System.out.println("ByteBuffer.allocate(16).getClass() = " + ByteBuffer.allocate(16).getClass());
        System.out.println("ByteBuffer.allocate(16).getClass() = " + ByteBuffer.allocateDirect(16).getClass());

        //class java.nio.HeapByteBuffer   -堆内存    (读写效率较低)                              受gc影响
        //class java.nio.DirectByteBuffer -直接内存(读写效率较高 , 少一次数据的copy  零copy技术)     系统内存.(分配速度较慢,使用不当会产生内存泄漏)

    }

    /**
     * 四.buffer 读
     */
    @Test
    public void test04() {
        ByteBuffer allocate = ByteBuffer.allocate(10);

        allocate.put(new byte[]{'a', 'b', 'c'});

        allocate.flip();


        //从头开始读 get()有参数方法 不会移动position
        allocate.get(new byte[3]);
        ByteBufferUtil.debugAll(allocate);

        //倒带此缓冲区。 位置设置为零，标记被丢弃
        allocate.rewind();


        //从头开始读 get()无参数方法 会移动到下一个position
        allocate.get();
        ByteBufferUtil.debugAll(allocate);


        //mark & reset
        //mark 做标记,纪律position
        //reset 重置到mark点
        allocate.mark();
        allocate.get();
        allocate.get();
        allocate.reset();
        ByteBufferUtil.debugAll(allocate);
    }

    /**
     * 五.字符串转字节
     */
    @Test
    public void test05() {
        //1.字符直接转 不会将buffer转成读模式
        byte[] bytes = "hello".getBytes();
        ByteBuffer allocate = ByteBuffer.allocate(5);
        allocate.put(bytes);
        ByteBufferUtil.debugAll(allocate);


        //2StandardCharsets 转   会直接讲buffer转成读模式
        ByteBuffer hello = StandardCharsets.UTF_8.encode("hello");
        ByteBufferUtil.debugAll(allocate);

        //3.warp 会直接讲buffer转成读模式
        ByteBuffer wrap = ByteBuffer.wrap(bytes);
        ByteBufferUtil.debugAll(allocate);
    }


    /**
     * 六.分散度: 通过channel 一次性读多个buffer中
     */
    @Test
    public void test06() throws IOException {

        RandomAccessFile accessFile = new RandomAccessFile("H:\\IDEA_WORKSPACE\\New Core\\LearningWay\\netty\\a.txt", "r");

        ByteBuffer allocate1 = ByteBuffer.allocate(3);
        ByteBuffer allocate2 = ByteBuffer.allocate(3);
        ByteBuffer allocate3 = ByteBuffer.allocate(3);

        accessFile.getChannel().read(new ByteBuffer[]{allocate1, allocate2, allocate3});
        ByteBufferUtil.debugAll(allocate1);
        ByteBufferUtil.debugAll(allocate2);
        ByteBufferUtil.debugAll(allocate3);
    }
    /**
     * 七.集中写: 多个buffer 一次性写到一个channel中
     */
    @Test
    public void test07() throws IOException {

        RandomAccessFile accessFile = new RandomAccessFile("H:\\IDEA_WORKSPACE\\New Core\\LearningWay\\netty\\a1.txt", "rw");

        ByteBuffer allocate1 = StandardCharsets.UTF_8.encode("hello");
        ByteBuffer allocate2 = StandardCharsets.UTF_8.encode("world");
        ByteBuffer allocate3 = StandardCharsets.UTF_8.encode("你好");

        accessFile.getChannel().write(new ByteBuffer[]{allocate1, allocate2, allocate3});

    }
}


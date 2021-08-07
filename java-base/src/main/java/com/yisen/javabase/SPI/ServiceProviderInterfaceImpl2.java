package com.yisen.javabase.SPI;

public class ServiceProviderInterfaceImpl2 implements ServiceProviderInterface {
    @Override
    public void hellow() {
        System.out.println("spi 加载的实现类 2 ");
    }
}

package com.yisen.javabase.SPI;

public class ServiceProviderInterfaceImpl implements ServiceProviderInterface {

    public ServiceProviderInterfaceImpl() {
        System.out.println("ServiceProviderInterfaceImpl");
    }

    @Override
    public void hellow() {
        System.out.println("hellow ServiceProviderInterfaceImpl!");
    }
}

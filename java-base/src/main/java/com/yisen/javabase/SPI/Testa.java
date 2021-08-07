package com.yisen.javabase.SPI;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Testa {

    public static void main(String[] args) {
        ServiceLoader<ServiceProviderInterface> load = ServiceLoader.load(ServiceProviderInterface.class);
        for (ServiceProviderInterface next : load) {
            next.hellow();
        }
    }
}

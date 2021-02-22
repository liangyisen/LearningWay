package com.yisen.javabase.SPI;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Testa {

    public static void main(String[] args) {
        ServiceLoader<ServiceProviderInterface> load = ServiceLoader.load(ServiceProviderInterface.class);

        Iterator<ServiceProviderInterface> iterator = load.iterator();
        if (iterator.hasNext()) {
            iterator.next().hellow();
        }
    }
}

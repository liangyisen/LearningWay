package com.yisen.javabase.base;

import org.omg.PortableInterceptor.Interceptor;

public class OutterInterClazz {
    private int b = 2;

    class InterClazz {
        private Integer a = 1;

        public Integer getNumber() {
            return b;
        }
    }
}

class SysOut{
    public static void main(String[] args) {


        OutterInterClazz outterInterClazz = new OutterInterClazz();
        OutterInterClazz.InterClazz interClazz = outterInterClazz.new InterClazz();
        System.out.println("interClazz.getNumber() = " + interClazz.getNumber());
    }
}

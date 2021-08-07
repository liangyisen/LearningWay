package com.yisen.javaweb;

public class SpringSPITestInterfaceImpl implements SpringSPITestInterface {
    @Override
    public String test() {
        System.out.println(this);
        return this.toString();
    }

}

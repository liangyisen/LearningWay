package com.yisen.javabase.designPattern.event;

public class Demo {

    public static void main(String[] args) {
        // 门
        Door door = new Door();
        // 注册监听器
        door.addDoorListener(new DoorListener() {
            @Override
            public void doOpen(DoorEvent doorEvent) {
                System.out.println(doorEvent + " bomb~~~");
            }

            @Override
            public void doClose(DoorEvent doorEvent) {
                System.out.println(doorEvent + " bomb2~~~");
            }
        });
        door.open();
        door.close();
    }
}
package com.yisen.javabase.designPattern.event;

// 事件源
class Door {

    private DoorListener doorListener;

    public void addDoorListener(DoorListener doorListener) {
        this.doorListener = doorListener;
    }

    public void open() {
        if (doorListener != null) {
            doorListener.doOpen(new DoorEvent(this));
        }
        System.out.println("门打开了。。。");
    }

    public void close() {
        if (doorListener != null) {
            doorListener.doClose(new DoorEvent(this));
        }
        System.out.println("门关上了。。。");
    }
}
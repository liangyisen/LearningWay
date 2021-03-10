package com.yisen.javabase.designPattern.event;

public interface DoorListener {
    void doOpen(DoorEvent doorEvent);

    void doClose(DoorEvent doorEvent);
}

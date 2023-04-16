package com.yisen.javabase.guava.evenbus;

import com.google.common.eventbus.EventBus;

/**
 * @Author : yisen
 * @Date : 2023/4/16 14:18
 * @Description :
 */
public class Main {

	/**
	 * 事件总线（EventBus）对象
	 */
	private static EventBus eventBus = new EventBus("yisen");

	public static void main(String[] args) {
		EventListener eventListener = new EventListener();
		//注册事件监听器
		eventBus.register(eventListener);

		//构建消息
		MyEvent event = new MyEvent("Hello, world!");
		//发送消息
		eventBus.post(event);
	}

}

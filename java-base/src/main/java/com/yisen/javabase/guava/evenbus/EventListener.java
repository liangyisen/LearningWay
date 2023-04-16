package com.yisen.javabase.guava.evenbus;

import com.google.common.eventbus.Subscribe;

/**
 * @Author : yisen
 * @Date : 2023/4/16 14:17
 * @Description : 事件监听器类（EventListener）。这个类需要实现Guava EventBus的订阅者接口Subscriber，以及其中的onEvent方法
 */
public class EventListener {
	/**
	 * @param event onEvent方法必须被注解@Subscribe进行标识，表示这是一个订阅者方法
	 */
	@Subscribe
	public void onEvent(MyEvent event) {
		System.out.println("Received message: " + event.getMessage());
	}
}


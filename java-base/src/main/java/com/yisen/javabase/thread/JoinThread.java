package com.yisen.javabase.thread;

import lombok.SneakyThrows;

/**
 * @Author : yisen
 * @Date : 2023/4/4 10:53
 * @Description :
 */
public class JoinThread {

	@SneakyThrows
	public static void main(String[] args) {
		Thread thread = new Thread(() -> {

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			System.out.println("线程1执行");

		}, "线程1");
		thread.start();

		System.out.println("thread = " + thread.getState());
		thread.join();
		System.out.println("thread = " + thread.getState());
		while (true) {
			Thread.sleep(1000);
			System.out.println("thread = " + thread);
		}


	}
}

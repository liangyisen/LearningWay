package com.yisen.javabase.JUCpack;

/**
 * @Author : yisen
 * @Date : 2023/4/12 10:42
 * @Description :
 */

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

	public static void main(String[] args) {
		int numberOfThreads = 3; // 线程数量为3

		CyclicBarrier cyclicBarrier = new CyclicBarrier(numberOfThreads, () -> System.out.println("所有线程已经完成工作"));

		for (int i = 0; i < numberOfThreads; i++) {
			Thread thread = new Thread(new Worker(i + 1, cyclicBarrier));
			thread.start();
		}
	}

	private static class Worker implements Runnable {
		private final int id;
		private final CyclicBarrier cyclicBarrier;

		public Worker(int id, CyclicBarrier cyclicBarrier) {
			this.id = id;
			this.cyclicBarrier = cyclicBarrier;
		}

		@Override
		public void run() {
			try {
				System.out.println("线程" + id + "正在工作");
				Thread.sleep(2000); // 模拟工作时间
				System.out.println("线程" + id + "工作完成");
				cyclicBarrier.await(); // 等待其他线程完成工作
				System.out.println("线程" + id + "继续执行");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

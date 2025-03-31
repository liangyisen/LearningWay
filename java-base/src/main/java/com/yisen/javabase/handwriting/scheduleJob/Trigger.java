package com.yisen.javabase.handwriting.scheduleJob;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * 触发器
 *
 * @Author : yisen
 * @Date : 2025/3/30 21:50
 * @Description :
 */

@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Trigger {

	private PriorityBlockingQueue<Job> queue = new PriorityBlockingQueue<>();

	private ExecutorService executorService = Executors.newFixedThreadPool(10);

	Thread thread = new Thread(() -> {
		while (true) {
			while (queue.isEmpty()) {
				LockSupport.park();
			}

			Job peek = queue.peek();
			if (peek.getStartTime() < System.currentTimeMillis()) {
				Job poll = queue.poll();
				assert poll != null;
				executorService.execute(poll.getRunnable());
				Job nextJob = new Job(poll.getRunnable(), poll.getStartTime() + poll.getDelay(), poll.getDelay());
				queue.offer(nextJob);
			} else {
				System.out.println("等待中" + peek.getStartTime());
				LockSupport.parkUntil(peek.getStartTime());
			}
		}
	});


	{
		System.out.println("任务Trigger启动");
		thread.start();
	}

	void wakeUp() {
		LockSupport.unpark(thread);
	}
}

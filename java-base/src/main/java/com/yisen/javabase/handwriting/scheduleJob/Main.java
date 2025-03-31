package com.yisen.javabase.handwriting.scheduleJob;

/**
 * @Author : yisen
 * @Date : 2025/3/30 21:45
 * @Description :
 */
public class Main {

	public static void main(String[] args) {
		ScheduleJobService scheduleJobService = new ScheduleJobService();
		Runnable runnable = () -> System.out.println("hello");
		scheduleJobService.run(runnable, System.currentTimeMillis(), 1000L);
	}
}

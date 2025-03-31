package com.yisen.javabase.handwriting.scheduleJob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *
 * @Author : yisen
 * @Date : 2025/3/30 21:45
 * @Description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleJobService {

	private Trigger trigger = new Trigger();

	public void run(Runnable runnable, Long startTime, Long delay) {
		Job job = new Job(runnable, startTime, delay);
		trigger.getQueue().offer(job);
		trigger.wakeUp();
	}


}

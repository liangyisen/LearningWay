package com.yisen.javabase.handwriting.scheduleJob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : yisen
 * @Date : 2025/3/30 21:44
 * @Description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job implements Comparable<Job> {

	private Runnable runnable;

	private Long startTime;

	private Long delay;

	@Override
	public int compareTo(Job job) {
		return job.startTime.compareTo(this.startTime);
	}
}

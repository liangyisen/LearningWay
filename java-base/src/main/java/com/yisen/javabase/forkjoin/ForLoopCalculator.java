package com.yisen.javabase.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.stream.LongStream;

public class ForLoopCalculator implements Calculator {

    @Override
    public long sumUp(long[] numbers) {
        return Arrays.stream(numbers).sum();
    }

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 10000000).toArray();

        Instant start = Instant.now();
        Calculator calculator = new ForLoopCalculator();
        long result = calculator.sumUp(numbers);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");

        System.out.println("结果为：" + result);
    }
}

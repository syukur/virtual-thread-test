package com.virtualthread.demo.test;

import com.virtualthread.demo.helper.SimpleForkJoinTask;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestForkJoin {

    public static void main(String[] args) throws InterruptedException {
        var pool = ForkJoinPool.commonPool();
        List<Integer> integers = IntStream.range(0,1000).boxed().collect(Collectors.toList());

        var task = new SimpleForkJoinTask(integers);
        pool.execute(task);


        Thread.sleep(Duration.ofDays(1));

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.DAYS);
    }


}

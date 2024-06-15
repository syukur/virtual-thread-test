package com.virtualthread.demo.test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestPararelStreamLimit {
    public static void main(String[] args) {
        Stream<Integer> stream = IntStream.range(0, 1000).boxed();

        ForkJoinPool pool = new ForkJoinPool(2);

        ForkJoinTask<?> task = pool.submit(() -> {
            stream.parallel().forEach(integer -> {
                System.out.println(Thread.currentThread().getName() + ":" + integer);
            });
        });

        task.join();
    }
}

package com.virtualthread.demo.test;

import java.time.Duration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {

    public static void main(String[] args) {

        var coreThread = 10;
        var maxThread = 100;
        var keepAlive = 1;
        var timeUnit = TimeUnit.MINUTES;
        var queue = new ArrayBlockingQueue<Runnable>(100);

        var executor = new ThreadPoolExecutor(
                coreThread,
                maxThread,
                keepAlive,
                timeUnit,
                queue
        );

        for (int i = 0; i < 100; i++) {
            final var task = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(Duration.ofSeconds(1));
                    String text = String.format("Task %d from ThreadPool: %s", task, Thread.currentThread().getName() );
                    System.out.println( text );
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

    }
}

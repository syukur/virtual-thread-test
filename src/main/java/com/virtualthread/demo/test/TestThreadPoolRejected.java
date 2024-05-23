package com.virtualthread.demo.test;

import java.time.Duration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPoolRejected {

    public static void main(String[] args) throws InterruptedException {

        var coreThread = 10;
        var maxThread = 100;
        var keepAlive = 1;
        var timeUnit = TimeUnit.MINUTES;
        var queue = new ArrayBlockingQueue<Runnable>(10);

        LogRejectedHandler logRejectedHandler = new LogRejectedHandler();

        var executor = new ThreadPoolExecutor(
                coreThread,
                maxThread,
                keepAlive,
                timeUnit,
                queue,
                logRejectedHandler
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

       // executor.shutdown();
        executor.shutdownNow();
        executor.awaitTermination(1, TimeUnit.DAYS);

    }

    private static class LogRejectedHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            //String text = String.format( "Task: %s is rejected", r );
            //System.out.println( text );
            System.out.println( "Task Was Rejected" );
        }
    }
}

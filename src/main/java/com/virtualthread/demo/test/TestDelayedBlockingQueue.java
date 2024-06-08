package com.virtualthread.demo.test;

import java.time.Duration;
import java.util.Comparator;
import java.util.concurrent.*;

public class TestDelayedBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<ScheduledFuture<String>> queue = new DelayQueue<ScheduledFuture<String>>();
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

        for (int i = 1; i <= 10; i++) {
            final var index = i;
            queue.put(scheduledExecutor.schedule(
                                    () -> "Data" + index,
                                    i,
                                    TimeUnit.SECONDS
                    )
            );
        }

        executor.execute(() -> {
            while (true){
                try {
                    var data = queue.take();
                    System.out.println("take data: " + data.get());
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);

    }
}

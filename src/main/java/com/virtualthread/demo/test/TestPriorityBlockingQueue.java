package com.virtualthread.demo.test;

import java.time.Duration;
import java.util.concurrent.*;

public class TestPriorityBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        for (int i = 0; i < 10; i++) {
            final var index = i;
            executor.execute(() -> {
                queue.put( index );
                System.out.println("put data" + index);
            });
        }

        executor.execute(() -> {
            while (true){
                try {
                    Thread.sleep(Duration.ofSeconds(1));
                    var data = queue.take();
                    System.out.println("take data: " + data);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);

    }
}

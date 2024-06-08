package com.virtualthread.demo.test;

import java.time.Duration;
import java.util.concurrent.*;

public class TestLinkedBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        for (int i = 0; i < 10; i++) {
            final String data = String.format("data-" + i);
            executor.execute(() -> {
                try {
                    queue.put( data );
                    System.out.println("put data" + data);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.execute(() -> {
            while (true){
                try {
                    Thread.sleep(Duration.ofSeconds(1));
                    String data = queue.take();
                    System.out.println("take data: " + data);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);

    }
}

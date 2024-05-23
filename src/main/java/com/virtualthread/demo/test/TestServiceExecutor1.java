package com.virtualthread.demo.test;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestServiceExecutor1 {
    public static void main(String[] args) throws Exception {

        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        // ExecutorService executorService = Executors.newCachedThreadPool();

        try (ExecutorService executorService = Executors.newFixedThreadPool(10)) {
            for (int i = 0; i < 100; i++) {
                executorService.execute(() -> {
                    try {
                        Thread.sleep(Duration.ofSeconds(1));

                        String text = String.format("Runnable in Thread: %s", Thread.currentThread().getName() );
                        System.out.println( text );
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }catch ( Exception e ){
            throw new Exception(e);
        }

    }
}

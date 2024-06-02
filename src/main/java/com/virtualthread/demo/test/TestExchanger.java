package com.virtualthread.demo.test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestExchanger {

    public static void main(String[] args) throws InterruptedException {
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        var exchanger = new Exchanger<String>();

        // First Thread
        executor.execute(() -> {
            try {
                var data = "First Data";
                System.out.println("Thread 1 mengirim data: " + data);
                var result = exchanger.exchange("First Data");
                System.out.println("Thread 1 menerima data: " + result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // Second Thread
        executor.execute(() -> {

            try {
                var data = "Second Data";
                System.out.println("Thread 2 mengirim data: " + data);
                var result = exchanger.exchange("Second Data");
                System.out.println("Thread 2 menerima data: " + result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

}

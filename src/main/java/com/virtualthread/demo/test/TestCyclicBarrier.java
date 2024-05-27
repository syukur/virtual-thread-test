package com.virtualthread.demo.test;

import com.virtualthread.demo.helper.Helper;

import java.time.Duration;
import java.util.concurrent.*;

public class TestCyclicBarrier {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

        for (int i = 0; i < 5; i ++){

            Runnable runnable = () -> {
                try {
                    System.out.println(
                            Helper.getCurrentDateTime() +
                            Thread.currentThread() + " is waiting to be executed" +
                            " sisa antrian: " + cyclicBarrier.getNumberWaiting()
                    );

                    cyclicBarrier.await();
                    Thread.sleep(Duration.ofSeconds(1));

                    System.out.println(
                            Helper.getCurrentDateTime() +
                            Thread.currentThread() + " Was Done Executed." +
                            " sisa antrian: " + cyclicBarrier.getNumberWaiting()
                    );

                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            };

            executor.execute( runnable );
        }

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}

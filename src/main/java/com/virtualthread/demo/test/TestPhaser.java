package com.virtualthread.demo.test;

import com.virtualthread.demo.helper.Helper;

import java.time.Duration;
import java.util.concurrent.*;

public class TestPhaser {

    private static void testPhaserAsCountDownLatch() throws InterruptedException {
        Phaser phaser = new Phaser();

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();


        phaser.bulkRegister(5);
        for (int i = 0; i < 5; i++) {

            final var index = i + 1;

            Runnable runnable = () -> {
                try {

                    System.out.println( Helper.getCurrentDateTime() + "Thread " + index + ", di mulai.");
                    Thread.sleep(Duration.ofSeconds(1));
                    System.out.println( Helper.getCurrentDateTime() +  "Thread " + index + ", selesai.");

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    phaser.arrive();
                }
            };

            executor.execute( runnable );
        }

        executor.execute(() -> {

            phaser.awaitAdvance(0);
            System.out.println( Helper.getCurrentDateTime() + "thread ke 6 di jalankan...");

        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }


    private static void testPhaserAsCyclicBarrier() throws InterruptedException {
        Phaser phaser = new Phaser();

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();


        phaser.bulkRegister(5);
        for (int i = 0; i < 5; i++) {

            final var index = i + 1;

            Runnable runnable = () -> {
                try {

                    System.out.println( Helper.getCurrentDateTime() + "Thread " + index + " menunggu.");
                    phaser.arriveAndAwaitAdvance();
                    Thread.sleep(Duration.ofSeconds(5));
                    System.out.println( Helper.getCurrentDateTime() +  "Thread " + index + " selesai.");

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            };

            executor.execute( runnable );
        }

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    public static void main(String[] args) throws InterruptedException {
        //testPhaserAsCountDownLatch();
        testPhaserAsCyclicBarrier();
    }
}

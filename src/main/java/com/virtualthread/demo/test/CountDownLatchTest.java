package com.virtualthread.demo.test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

    static DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSS");
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        for (int i = 1; i <= 5; i ++){

            Runnable runnable = () -> {
                try {
                    System.out.println( getCurrentDateTime() + "Thread: " + Thread.currentThread() + " Started ");
                    Thread.sleep(Duration.ofSeconds(2));
                    System.out.println( getCurrentDateTime() + "Thread: " + Thread.currentThread() + " Finish");
                    System.out.println();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    countDownLatch.countDown();
                }
            };

            executor.execute( runnable );
        }

        executor.execute(() -> {

            try {
                System.out.println( getCurrentDateTime() + "Thread: " + Thread.currentThread() + " Is Waiting");
                countDownLatch.await();
                System.out.println(getCurrentDateTime() + "Thread: " + Thread.currentThread() + " All Task Finished And Thread 6 can RUn");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        });


        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    private static String getCurrentDateTime(){
        var now = LocalDateTime.now();
        var nowOnText = now.format(dateTimeFormatter);

        return "[" + nowOnText + "]: ";
    }
}

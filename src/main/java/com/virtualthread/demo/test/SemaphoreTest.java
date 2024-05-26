package com.virtualthread.demo.test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {

    static DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSS");
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(10);
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

        for( int i=0; i < 1000; i++ ){
            executorService.execute(() -> {
                try {

                    semaphore.acquire();
                    Thread.sleep(Duration.ofSeconds(1));
                    String text = String.format(" %s %s finish ", getCurrentDateTime(), Thread.currentThread());
                    System.out.println( text );
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    semaphore.release();
                }
            });
        }

        executorService.awaitTermination(1, TimeUnit.DAYS);
    }

    private static String getCurrentDateTime(){
        var now = LocalDateTime.now();
        var nowOnText = now.format(dateTimeFormatter);

        return "[" + nowOnText + "]: ";
    }
}

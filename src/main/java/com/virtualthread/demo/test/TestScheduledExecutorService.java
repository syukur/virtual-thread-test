package com.virtualthread.demo.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TestScheduledExecutorService {

    static DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSS");
    public static void main(String[] args) throws InterruptedException {
            var scheduledExecutor = Executors.newSingleThreadScheduledExecutor(Thread.ofVirtual().factory());

        ScheduledFuture<?> future = scheduledExecutor.scheduleAtFixedRate(
                () -> {
                    System.out.println("["+getCurrentDateTime()+"] [" + Thread.currentThread() + "]Schedule Executed... ");
                },
                1,
                1,
                TimeUnit.SECONDS
        );


        System.out.println("["+getCurrentDateTime()+"] waiting for executed... (" + future.getDelay( TimeUnit.MILLISECONDS ) + " milis)");

        scheduledExecutor.awaitTermination(1, TimeUnit.DAYS);

    }


    private static String getCurrentDateTime(){
        var now = LocalDateTime.now();
        var nowOnText = now.format(dateTimeFormatter);

        return nowOnText + ": ";
    }
}

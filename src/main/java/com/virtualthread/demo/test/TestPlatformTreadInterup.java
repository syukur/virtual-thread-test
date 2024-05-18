package com.virtualthread.demo.test;

import java.time.Duration;

public class TestPlatformTreadInterup {
    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> {
            for (int i = 0; i < 10 ; i++) {

                if( Thread.interrupted() ){
                    System.out.println("Thread was interupted");
                    return;
                }

                try {
                    Thread.sleep(Duration.ofSeconds(2));
                    var  text = String.format("Loop ke: %d", i);
                    System.out.println( text  );
                } catch (InterruptedException e) {
                    System.out.println("Thread stoped...cause interupted");
                    return;
                    //throw new RuntimeException(e);
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        Thread.sleep(Duration.ofSeconds(8));
        thread.interrupt();
        System.out.println("Menunggu Thread Selasai");
        thread.join();
        System.out.println("Program Selesai");

    }
}

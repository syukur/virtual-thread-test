package com.virtualthread.demo.test;

import java.time.Duration;

public class DaemonThreadTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(Duration.ofSeconds(3));
                System.out.println("Run the runnable");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread.setDaemon(false); // setting thread menjadi user-thread, sehingga thread akan di tunggu oleh JVM
        //thread.setDaemon(true); // setting tread menjadi daemon-thread, sehingga thread tidak akan di tunnggu oleh JVM

        thread.start();
    }
}

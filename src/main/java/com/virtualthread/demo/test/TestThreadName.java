package com.virtualthread.demo.test;

import java.time.Duration;

public class TestThreadName {
    public static void main(String[] args) throws InterruptedException {
        var thread = new Thread(() -> {
            String text = String.format("thread name : %s", Thread.currentThread().getName() );
            System.out.println( text );
        });

        thread.setName("azka.thread");
        thread.start();
        thread.join();
    }
}

package com.virtualthread.demo.test;

import java.time.Duration;

public class RaceConditionTest {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Runnable runnable = () -> {
            for (int i = 0; i < 100; i++){
                try {
                    Thread.sleep(Duration.ofSeconds(2));
                    counter.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        };

//        Thread thread1 = Thread.ofVirtual().start(runnable);
//        Thread thread2 = Thread.ofVirtual().start(runnable);
//        Thread thread3 = Thread.ofVirtual().start(runnable);

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);

        thread1.setName("thread.01");
        thread2.setName("thread.02");
        thread3.setName("thread.03");

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        String text = String.format("value of counter: %d", counter.getValue());
        System.out.println( text );

    }


}

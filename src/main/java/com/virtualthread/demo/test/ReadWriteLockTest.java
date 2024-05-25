package com.virtualthread.demo.test;

public class ReadWriteLockTest {

    public static void main(String[] args) throws InterruptedException {
        CounterReadWriteLock counter = new CounterReadWriteLock();
        //PlainCounter counter = new PlainCounter();

        Runnable runnable = () -> {
            for (int i = 0; i < 1000000; i++){
                    counter.increment();
            }
        };

        Thread thread1 = Thread.ofVirtual().start(runnable);
        Thread thread2 = Thread.ofVirtual().start(runnable);
        Thread thread3 = Thread.ofVirtual().start(runnable);

        thread1.join();
        thread2.join();
        thread3.join();


        String text = String.format("value of counter: %d", counter.getValue());
        System.out.println( text );

    }


}

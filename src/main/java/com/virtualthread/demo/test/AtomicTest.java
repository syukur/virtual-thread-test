package com.virtualthread.demo.test;

public class AtomicTest {
    public static void main(String[] args) throws InterruptedException {

        var counter = new CounterAtomic();
        //var counter = new PlainCounter();

        Runnable runnable =() -> {
            for ( int i = 0; i < 100_000; i++  ){
                counter.increment();
            }
        };

        Thread thread1 = Thread.ofVirtual().start(runnable);
        Thread thread2 = Thread.ofVirtual().start(runnable);
        Thread thread3 = Thread.ofVirtual().start(runnable);

        thread1.join();
        thread2.join();
        thread3.join();

        String text = String.format("latest value: %s", counter.getValue() );
        System.out.println( text );

    }
}

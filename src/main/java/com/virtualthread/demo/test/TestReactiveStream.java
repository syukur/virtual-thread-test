package com.virtualthread.demo.test;

import com.virtualthread.demo.helper.PrintSubscriber;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class TestReactiveStream {
    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        PrintSubscriber subscriber1 = new PrintSubscriber("Azka");
        PrintSubscriber subscriber2 = new PrintSubscriber("Elfathan");
        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);

        //ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(() -> {
            for (int i = 0; i < 5; i++) {
                publisher.submit("Data-" + i);
                System.out.println(Thread.currentThread() + ", Send Data-" + i);
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}

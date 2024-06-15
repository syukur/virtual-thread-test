package com.virtualthread.demo.test;

import com.virtualthread.demo.helper.PrintProcessor;
import com.virtualthread.demo.helper.PrintSubscriber;

import java.time.chrono.ThaiBuddhistEra;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class TestProcessor {
    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        PrintProcessor printProcessor = new PrintProcessor("Azka.1");
        publisher.subscribe(printProcessor);

        PrintSubscriber subscriber = new PrintSubscriber("Elfathan.2");
        printProcessor.subscribe(subscriber);

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        executor.execute(() -> {
            for (int i = 0; i < 10; i++) {

                publisher.submit("Data-"+i);
                System.out.println("data sent : Data-"+i );
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}

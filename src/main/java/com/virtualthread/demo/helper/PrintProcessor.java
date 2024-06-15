package com.virtualthread.demo.helper;

import java.time.Duration;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class PrintProcessor
        extends SubmissionPublisher<String>
        implements Flow.Subscriber<String>{

    Flow.Subscription subscription;

    String name;

    public PrintProcessor(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(String item) {
        try {
            Thread.sleep(Duration.ofSeconds(1));

            System.out.println(Thread.currentThread().getName() + " Receive : " + item + ", On Processor: " + name );
            submit(item + ".1" );
            this.subscription.request(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        close();
    }
}

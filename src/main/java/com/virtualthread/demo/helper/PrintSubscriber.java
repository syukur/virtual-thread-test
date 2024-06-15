package com.virtualthread.demo.helper;

import java.time.Duration;
import java.time.chrono.ThaiBuddhistEra;
import java.util.concurrent.Flow;

public class PrintSubscriber implements Flow.Subscriber<String>{

    Flow.Subscription subscription;

    String name;

    public PrintSubscriber(String name) {
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
            System.out.println(Thread.currentThread().getName() + " Receive : " + item + ", On Subcriber: " + name );
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
        System.out.println(Thread.currentThread().getName() + " : DONE");
    }
}

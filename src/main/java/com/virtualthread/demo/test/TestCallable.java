package com.virtualthread.demo.test;

import java.time.Duration;
import java.util.concurrent.*;

public class TestCallable {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Callable<String> callable = () -> {
            Thread.sleep(Duration.ofSeconds(5));
            return "Hai Nama ku Azka";
        };


        Future<String> future;

        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {

            future = executor.submit(callable);
            while ( !future.isDone() ){
                Thread.sleep(Duration.ofSeconds(1));
                System.out.println("Thread still waiting to done...");
            }

        }

        String text = String.format("Finally Thread is done, result of callable is: %s", future.get());
        System.out.println( text );

    }
}

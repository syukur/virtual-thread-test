package com.virtualthread.demo.test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestCallableInvokeAll {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        List<Callable<String>> callables =
                IntStream.range(1, 11)
                        .mapToObj(value -> (Callable<String>) () -> {
                                    Thread.sleep( value * 500L );
                                    return String.valueOf( value );
                        })
                        .collect(Collectors.toList());


        var executor = Executors.newFixedThreadPool(10);

        List<Future<String>> futures = executor.invokeAll(callables);

        for (Future<String> future : futures)
            System.out.println(future.get());

        executor.shutdown();

    }
}

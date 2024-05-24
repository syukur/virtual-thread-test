package com.virtualthread.demo.test;

import java.time.Duration;
import java.util.concurrent.*;

public class TestCallable {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 1. Define the callable
        Callable<String> callable = () -> {
            Thread.sleep(Duration.ofSeconds(5));
            return "Hai Nama ku Azka";
        };

        // 2. Create executorService
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 3. Execute the execitorService, then put the result into Future
        Future<String> future = executor.submit(callable);

        // 4. Loop Until callable done
        while ( !future.isDone() ){
                Thread.sleep(Duration.ofSeconds(1));
                System.out.println("Waiting The Callable done...");
        }

        // 5. Print the result if callable done
        String text = String.format("Finally Callable is done, result of callable is: %s", future.get());
        System.out.println( text );

        // 6. shutdown the executor
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}

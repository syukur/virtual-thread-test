package com.virtualthread.demo.test;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.*;

public class TestCompletable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //System.out.println(getValue().get()); // test getValue
        System.out.println(getFastest().get());
    }

    /** Contoh Penggunaan Completable
     * */
   private static Future<String> getValue(){
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        var completable = new CompletableFuture<String>();

        executor.execute(() -> {
            try {
                Thread.sleep(Duration.ofSeconds(1));
                completable.complete("SUCCESS CONTENT");
            } catch (InterruptedException e) {
                completable.completeExceptionally(e);
            }
        });

        return completable;
    }

    private static void execute( CompletableFuture<String> completable, String value ){

       var executor = Executors.newVirtualThreadPerTaskExecutor();

        Random random = new Random();

        executor.execute(() -> {
            try {
                Thread.sleep( 1000 + random.nextInt(5000) );
                completable.complete(value);
            } catch (InterruptedException e) {
                completable.completeExceptionally(e);
                throw new RuntimeException(e);
            }
        });
    }

    private static Future<String> getFastest(){
        CompletableFuture<String> completable = new CompletableFuture<>();

        execute(completable, "Thread 1");
        execute(completable, "Thread 2");
        execute(completable, "Thread 3");

        return completable;
    }



}

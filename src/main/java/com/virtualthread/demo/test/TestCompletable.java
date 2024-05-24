package com.virtualthread.demo.test;

import java.time.Duration;
import java.util.concurrent.*;

public class TestCompletable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(getValue().get());
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

}

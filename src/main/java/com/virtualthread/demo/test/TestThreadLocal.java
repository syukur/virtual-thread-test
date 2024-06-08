package com.virtualthread.demo.test;

import com.virtualthread.demo.service.UserService;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestThreadLocal {
    public static void main(String[] args) throws InterruptedException {

        var random = new Random();
        var userService = new UserService();
        var executor = Executors.newVirtualThreadPerTaskExecutor();

        for (int i = 0; i < 50; i++) {
            final var index = i;
            executor.execute(() -> {
                try {
                    userService.setUser("User-" + index);
                    Thread.sleep(100 + random.nextInt(3000));
                    userService.doAction();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            });
        }

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}

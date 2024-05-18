package com.virtualthread.demo.test;

import java.time.Duration;

public class TestPlatformThread {
    public static void main(String[] args) {

        for ( int i=0; i<= 10; i ++ ){

            Thread.ofPlatform()
                   .name("Elfathan.Thread: " + i)
                    .daemon(false)
                    .start(() -> {
                        try {
                            Thread.sleep(Duration.ofSeconds(2));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }
                        System.out.println("Hai " + Thread.currentThread() + "!");
                    });

        }

    }
}

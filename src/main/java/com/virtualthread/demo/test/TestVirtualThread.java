package com.virtualthread.demo.test;

import java.io.IOException;
import java.time.Duration;

public class TestVirtualThread {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i <= 10_000; i++) {

            Thread
                    .ofVirtual()
                    .name("Azka." + i )
                    .start(() -> {
                        try {
                            Thread.sleep(Duration.ofSeconds(2));
                            System.out.println("Hello " + Thread.currentThread() + "!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }

                    });

        }

        System.in.read();
    }
}

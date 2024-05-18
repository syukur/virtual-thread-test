package com.virtualthread.demo.test;

public class VirtualThreadExample {

    public static void main(String[] args) {

        try {

            Thread.Builder builder = Thread.ofVirtual().name("syukur.thread");

            Runnable task = () -> {
                System.out.println("Running thread...");
            };

            Thread t = builder.start(task);

            System.out.println("Thread t name: " + t.getName());

            Thread.sleep(1000);

            t.join();

        } catch (InterruptedException e) {
           e.printStackTrace();
        }
    }


}

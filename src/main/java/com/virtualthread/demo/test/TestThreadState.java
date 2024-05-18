package com.virtualthread.demo.test;

public class TestThreadState {
    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> {
            var state0 = String.format("Thread State 0 : %s", Thread.currentThread().getState());
            System.out.println( state0 );
        };

        var thread = new Thread(runnable);

        var state1 = String.format("Thread State 1 : %s", thread.getState());
        System.out.println(state1);

        thread.start();

        thread.join();

        var state2 = String.format("Thread State 2 : %s", thread.getState());
        System.out.println(state2);



    }
}

/**
 * Java program for Creating and executing
 * Virtual Thread with the
 * Executor.newVirtualThreadPerTaskExecutor
* */

package com.virtualthread.demo.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class VirtualThreadExecutorExample {
    public static void main(String[] args) {
        try (ExecutorService myExecutorService = Executors.newVirtualThreadPerTaskExecutor()){
            //Submit a task that print a message
            Future<?> future = myExecutorService.submit(() ->{
                System.out.println("Running thread");
            } );

            //Wait for the tas to complete
            future.get();
            System.out.println("Program Complete !!");
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }
}

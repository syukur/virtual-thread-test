package com.virtualthread.demo.helper;

import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class SimpleForkJoinTask extends RecursiveAction {

    List<Integer> integers;

    public SimpleForkJoinTask(List<Integer> integers) {
        System.out.println("hai");
        this.integers = integers;
    }

    @Override
    protected void compute() {
        if( integers.size() <= 10 ){
            // execute the job
            doExecute();
        }else{
            // fork the jobs
            forkCompute();
        }
    }

    private void doExecute() {
        System.out.println("doExecute...");
        this.integers.forEach(integer -> {
            System.out.println( "list_item: " + integer + ", executed by : " + Thread.currentThread().getName()  );
        });
    }

    private void forkCompute() {
        System.out.println("fork...");
        System.out.println("list.size..." + integers.size());

        var half = this.integers.size() / 2;

        List<Integer> integers1 = this.integers.subList(0, half);
        List<Integer> integers2 = integers1.subList(half, this.integers.size());

        SimpleForkJoinTask task1 = new SimpleForkJoinTask(integers1);
        SimpleForkJoinTask task2 = new SimpleForkJoinTask(integers2);

        ForkJoinTask.invokeAll( task1, task2 );

    }
}

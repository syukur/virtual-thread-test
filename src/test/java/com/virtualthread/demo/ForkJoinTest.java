package com.virtualthread.demo;

import com.virtualthread.demo.helper.SimpleForkJoinTask;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ForkJoinTest {

    @Test
    void recursiveTask() throws InterruptedException {
        var pool = ForkJoinPool.commonPool();
        List<Integer> integers = IntStream.range(0,1000).boxed().collect(Collectors.toList());

        var task = new com.virtualthread.demo.helper.SimpleForkJoinTask(integers);
        pool.execute(task);

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.DAYS);
    }

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

            com.virtualthread.demo.helper.SimpleForkJoinTask task1 = new com.virtualthread.demo.helper.SimpleForkJoinTask(integers1);
            com.virtualthread.demo.helper.SimpleForkJoinTask task2 = new com.virtualthread.demo.helper.SimpleForkJoinTask(integers2);

            ForkJoinTask.invokeAll( task1, task2 );

        }
    }
}

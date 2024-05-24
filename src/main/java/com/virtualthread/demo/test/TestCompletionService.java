package com.virtualthread.demo.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.*;

public class TestCompletionService {

    static DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSS");

    public static void main(String[] args) throws InterruptedException {



        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        ExecutorCompletionService completionService  = new ExecutorCompletionService<String>(executorService);


        Random random = new Random();

        //1. Submit 100 callable into completionService
        Executors.newVirtualThreadPerTaskExecutor().execute(() -> {
            for (int i =0; i < 100; i++) {
                final var index = i;

                Callable<String> callable = () -> {
                  Thread.sleep(random.nextInt(200));

                  String taskName = String.format("[task:%s]", index);
                  System.out.println( getCurrentDateTime() + taskName + ", submited to completion");
                  return taskName;
                };

                completionService.submit(callable);
            }
        });

        // 2 poll the callable ( task )

        Executors.newVirtualThreadPerTaskExecutor().execute(() -> {
            while( true ){

                System.out.println( getCurrentDateTime() + "try to get task");

                try {
                    Future<String> future = completionService.poll(5, TimeUnit.SECONDS);
                    if( future == null ){
                        System.out.println( getCurrentDateTime() + "completion is empty");
                        break;
                    }else{
                        System.out.println( getCurrentDateTime() +  future.get() + ", pool from completion");
                    }
                } catch (InterruptedException | ExecutionException e) {
                    //throw new RuntimeException(e);
                    e.printStackTrace();
                    break;
                }
            }
        });

        //executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }


    private static String getCurrentDateTime(){
        var now = LocalDateTime.now();
        var nowOnText = now.format(dateTimeFormatter);

        return nowOnText + ": ";
    }

}

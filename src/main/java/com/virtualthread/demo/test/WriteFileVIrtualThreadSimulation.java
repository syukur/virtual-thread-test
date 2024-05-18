package com.virtualthread.demo.test;

import com.virtualthread.demo.test.writer.Writer;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WriteFileVIrtualThreadSimulation {
    public static void main(String[] args) throws IOException {
        var rootPath = System.getProperty("user.dir");

        String [] filesName = new String[]{
          "text-output/Thread_01.txt",
          "text-output/Thread_02.txt",
//          "text-output/Thread_03.txt",
//          "text-output/Thread_04.txt",
//          "text-output/Thread_05.txt"
        };

        var executor = Executors.newVirtualThreadPerTaskExecutor();

        for ( String fileName : filesName ){

            executor.execute(() -> {
                Writer writer = new Writer( fileName );

                for (int i = 0; i < 10; i++) {

                    try {
                        Thread.sleep(Duration.ofSeconds(2));
                        String content =  String.format("fileName: %s Testing Write... %d ", fileName, i);
                        writer.write( content );
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }


                }


            });

        }

        System.in.read();


    }




}

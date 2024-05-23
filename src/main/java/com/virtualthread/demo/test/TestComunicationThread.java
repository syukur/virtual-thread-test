package com.virtualthread.demo.test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestComunicationThread {
    public static void main(String[] args) throws InterruptedException {


        String[] prospects = {
                "Mark",
                "Elon",
                "Steve",
                "Satya",
                "Bill"
        };

        List<String> members = new ArrayList<>();

        Object lock = new Object();

        // waiting and print
        Thread thread1 = new Thread(() -> {
            synchronized ( lock ){
                try {
                    lock.wait();

                    String text = String.format("%s: add member done here is the members: %s", LocalDateTime.now().toString() ,members.toString());
                    System.out.println( text );
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // add member
        Thread thread2 = new Thread(() ->{
            try {
                synchronized ( lock ){
                    for ( String prospect :  prospects ){
                        Thread.sleep(Duration.ofSeconds(1));
                        members.add( prospect );
                        String text = String.format("%s: %s added as member", LocalDateTime.now().toString(), prospect);
                        System.out.println( text );
                    }

                    lock.notify();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } );

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }
}

package com.virtualthread.demo.test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ConditionLockTest {
    public static void main(String[] args) throws InterruptedException {


        String[] prospects = {
                "Mark",
                "Elon",
                "Steve",
                "Satya",
                "Bill"
        };

        List<String> members = new ArrayList<>();

        //Object lock = new Object();

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        // waiting and print
        Thread thread1 = new Thread(() -> {
            try {
                lock.lock();
                condition.await();
                String text = String.format("%s: add member done here is the members: %s", LocalDateTime.now().toString() ,members.toString());
                System.out.println( text );
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        // add member
        Thread thread2 = new Thread(() ->{
            try {
                lock.lock();
                for ( String prospect :  prospects ){
                    Thread.sleep(Duration.ofSeconds(1));
                    members.add( prospect );
                    String text = String.format("%s: %s added as member", LocalDateTime.now().toString(), prospect);
                    System.out.println( text );
                }

                condition.signal();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                lock.unlock();
            }
        } );

        thread1.start();
        thread2.start();


    }
}

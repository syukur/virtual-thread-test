package com.virtualthread.demo.test;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterLock {

    DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss SSSSSSSSS");
    private Long value = 0L;

    private final Lock lock = new ReentrantLock();

    public synchronized void increment(){

        try {
            lock.lock();
            value++;
        }finally {
            lock.unlock();
        }

    }
    public Long getValue(){
        return value;
    }
}

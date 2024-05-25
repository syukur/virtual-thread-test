package com.virtualthread.demo.test;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CounterReadWriteLock {

    DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss SSSSSSSSS");
    private Long value = 0L;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public synchronized void increment(){

        try {
            lock.writeLock().lock();
            value++;
        }finally {
            lock.writeLock().unlock();
        }

    }
    public Long getValue(){
        try {
            lock.readLock().lock();
            return value;
        }finally {
            lock.readLock().unlock();
        }
    }
}

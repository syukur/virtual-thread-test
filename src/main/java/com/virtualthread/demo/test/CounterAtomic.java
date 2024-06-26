package com.virtualthread.demo.test;

import java.util.concurrent.atomic.AtomicLong;

public class CounterAtomic {

    AtomicLong value = new AtomicLong(0L);

    public void increment(){
        value.incrementAndGet();
    }

    public Long getValue(){
        return value.get();
    }

}

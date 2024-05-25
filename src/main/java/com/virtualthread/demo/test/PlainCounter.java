package com.virtualthread.demo.test;

import java.util.concurrent.atomic.AtomicLong;

public class PlainCounter {

    Long value = 0L;

    public void increment(){
        value++;
    }

    public Long getValue(){
        return value;
    }

}

package com.virtualthread.demo.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Counter{

    DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss SSSSSSSSS");
    private Long value = 0L;

    Object lock = new Object();

//    public synchronized void increment(){
//        value++;
//    }

    public  void increment(){
        synchronized (this){
            value++;
        }
    }

//    public void increment(){
//
//        var now = LocalDateTime.now();
//        var nowOnText = now.format(dateTimeFormatter);
//
//        System.out.println( nowOnText + " before lock: " + Thread.currentThread().getName() );
//
//        synchronized (this){
//
//             now = LocalDateTime.now();
//             nowOnText = now.format(dateTimeFormatter);
//
//            System.out.println( nowOnText + " on lock block: " + Thread.currentThread().getName() );
//            value++;
//        }
//
//
//        now = LocalDateTime.now();
//        nowOnText = now.format(dateTimeFormatter);
//
//        System.out.println( nowOnText + " after lock: " + Thread.currentThread().getName() );
//    }

    public Long getValue(){
        return value;
    }
}

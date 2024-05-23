package com.virtualthread.demo.test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
    public static void main(String[] args) {

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Periodic Job");
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 2000L, 2000L);
    }
}

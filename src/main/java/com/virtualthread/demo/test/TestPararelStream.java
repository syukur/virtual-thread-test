package com.virtualthread.demo.test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestPararelStream {
    public static void main(String[] args) {
        Stream<Integer> stream = IntStream.range(0, 1000).boxed();

        stream.parallel().forEach(integer -> {
            System.out.println(Thread.currentThread().getName() + " : " + integer);
        });
    }
}

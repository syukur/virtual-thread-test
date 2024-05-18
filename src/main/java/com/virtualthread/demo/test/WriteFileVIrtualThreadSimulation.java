package com.virtualthread.demo.test;

import com.virtualthread.demo.test.writer.Writer;

import java.util.Properties;

public class WriteFileVIrtualThreadSimulation {
    public static void main(String[] args) {
        var rootPath = System.getProperty("user.dir");
        System.out.println(rootPath);
        Writer writer = new Writer(rootPath + "\\test002.txt");
//        writer.write("coba tulis file test 1");
//        writer.write("coba tulis file test 2");
//        writer.write("coba tulis file test 3");
    }




}

package com.virtualthread.demo.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Helper {

    private static DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSS");
    public static String getCurrentDateTime(){
        var now = LocalDateTime.now();
        var nowOnText = now.format(dateTimeFormatter);

        return "[" + nowOnText + "]: ";
    }
}

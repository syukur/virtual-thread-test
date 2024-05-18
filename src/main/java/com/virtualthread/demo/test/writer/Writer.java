package com.virtualthread.demo.test.writer;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Writer {

    BufferedWriter bufferedWriter;

    File file;

    DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    PrintWriter printWriter;

    public Writer( String fileName ){

        System.out.println( "file_name: " + fileName );

        this.file = new File( fileName );

        if( !this.file.exists() ) {
            try {
                this.file.createNewFile();
                System.out.println("file di buat");
            } catch (IOException e) {
                System.out.println("Error 01");
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        try {
            printWriter = new PrintWriter(fileName);
            printWriter.println("test 123");
            printWriter.println("\n");
            printWriter.println("ok mantap");
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error 02");
            e.printStackTrace();
            throw new RuntimeException(e);
        }




    }

    public void write( String text ){
        try {

            var now = LocalDateTime.now();
            var nowOnText = now.format(dateTimeFormatter);

            String content = String.format("%s %s", nowOnText, text);

            System.out.println( "content: " + content );

            bufferedWriter.write( content );
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println( "Error On Write" );
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}

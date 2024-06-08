package com.virtualthread.demo.service;

public class UserService {

    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private String user;

    public void setUser( String user ){
       threadLocal.set(user);
        //this.user = user;
    }

    public void doAction(){
        String user = threadLocal.get();
        System.out.println(user + " do action");


       // System.out.println(this.user + " do action");
    }
}

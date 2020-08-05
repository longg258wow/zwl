package com.反射;

public class User {
    public String userName;
    private String age;
    String bbb;
    protected String  ccc;


    public User(){

    }
    public User(String userName){
        this.userName = userName;
        System.out.println("userName = "+userName);
    }
    public void setUserName(String userName){
        System.out.println("exe this ="+userName);
    }
}

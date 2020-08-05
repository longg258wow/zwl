package com.远程方法;

import com.远程方法.jar.HelloInterface;

import java.rmi.Naming;

public class HelloClient {
    public static void main(String[] argv) throws Exception{
        HelloInterface hello = (HelloInterface) Naming.lookup("rmi://localhost:8888/Hello");
        System.out.println(hello.sayHello());
    }
}

package com.设计模式.回调模式;

public class Test {
    public static void main(String[] args) throws Exception {
        int a = 1;int b = 1;
        Student s = new Student("小明");
        System.out.println(11111);
        s.callHelp2(a, b,s);
        System.out.println(2222);

    }
}

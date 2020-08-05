package com.设计模式.回调模式;

public class SuperCalculator {
    public void add(int a, int b, Student  xiaoming) throws InterruptedException {
        int result = a + b;
        Thread.sleep(5000);
        xiaoming.fillBlank(a, b, result);
    }


}

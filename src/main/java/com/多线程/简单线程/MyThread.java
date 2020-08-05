package com.多线程.简单线程;

public class MyThread extends Thread{

    @Override
    public void run() {
        System.out.println("MyThread 执行");
        try {
            Thread.sleep(3000);
            System.out.println("MyThread OK");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //创建两个线程对象
        MyThread my1 = new MyThread();
        MyThread my2 = new MyThread();

        my1.start();
        my2.start();
    }
}

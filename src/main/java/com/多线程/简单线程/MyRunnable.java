package com.多线程.简单线程;

public class MyRunnable implements Runnable  {
    @Override
    public void run() {
        System.out.println("MyRunnable 执行");
        try {
            Thread.sleep(3000);
            System.out.println("MyRunnable OK");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyRunnable my = new MyRunnable();
        Thread t1 = new Thread(my);
        Thread t2 = new Thread(my, "线程二");//给线程起一个名字
        t1.start();
        t2.start();
    }
}

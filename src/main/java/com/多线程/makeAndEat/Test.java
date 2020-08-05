package com.多线程.makeAndEat;

public class Test {


    public static void makeAndEat() {
        System.out.println(" main线程1： id = " + Thread.currentThread().getId() + " name = " + Thread.currentThread().getName());
        Desk desk = new Desk();
        desk.breadCount = 0;

        MyRunable myRunable = new MyRunable();
        myRunable.desk = desk;
        Thread thread = new Thread(myRunable);
        thread.start();
        MyThread thread2 = new MyThread();
        thread2.desk = desk;
        thread2.start();
    }

    public static void main(String[] args) throws Exception {
        Test.makeAndEat(); //生产者消费者 包含sleep synchronized wait notify


    }


}

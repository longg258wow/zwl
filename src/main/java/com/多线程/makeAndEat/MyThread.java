package com.多线程.makeAndEat;

public class MyThread extends Thread {
    public Desk desk;

    @Override
    public void run() {
        while (true) {
            try {
               desk.eat();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

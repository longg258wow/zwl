package com.多线程.makeAndEat;

public class MyRunable implements Runnable {

    public Desk desk;
    @Override
    public void run() {
        try {
            while (true) {
                desk.make();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

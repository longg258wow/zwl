package com.多线程.threadpool;

/**
 *
 * 用户线程
 */

class Task extends Thread {
    public int exeTime;
    public String tName;

    public Task(String tName, int exeTime) {
        this.tName = tName;
        this.exeTime = exeTime;
    }

    @Override
    public void run() {
        try {
            System.out.println("start Executing : " + Thread.currentThread().getId() + " tName = " );
            Thread.sleep(exeTime);
            //   user.wait();
            System.out.println("end Executing : " + Thread.currentThread().getId() + " tName = " );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

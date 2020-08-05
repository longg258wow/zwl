package com.多线程.makeAndEat;

public class Desk {
    public Integer breadCount = 0;

    public synchronized void    make()throws Exception{
        System.out.println("生产前："+breadCount);
        Thread.sleep(1000);
        System.out.println("增加前："+breadCount);
        breadCount++;
        System.out.println(" MyRunable线程 id = "+ Thread.currentThread().getId()+ " desk.breadCount = "+breadCount);
        if(breadCount==1){
            System.out.println("又做了一个 通知白痴来吃");
             this.notify();
        }
    }

    public synchronized void  eat()throws Exception{
        Thread.sleep(1000);
        if(breadCount>=1){
            System.out.println("开始吃了");
            breadCount--;
            System.out.println(" MyThread线程： id = "+ Thread.currentThread().getId()+" desk.breadCount = "+  breadCount);
        }else{
            System.out.println("已经吃完了 等白痴厨子做好再吃");
            this.wait();
        }
    }
}

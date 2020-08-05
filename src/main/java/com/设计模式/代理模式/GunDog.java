package com.设计模式.代理模式;

public class GunDog implements IDog {
    @Override
    public void run() {
        System.out.println("猎狗在跑");
    }

    @Override
    public void dump(Integer h) {
        System.out.println("猎狗跳了"+h);
    }
}

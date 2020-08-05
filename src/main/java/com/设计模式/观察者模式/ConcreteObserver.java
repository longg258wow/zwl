package com.设计模式.观察者模式;

public class ConcreteObserver implements Observer {

    @Override
    public void update() {
        System.out.println("收到消息，进行处理");
    }

}
package com.设计模式.观察者模式;

import java.util.Vector;

/**
 * 主题
 */
public class Subject {

    //观察者数组
    private Vector<Observer> oVector = new Vector<>();

    //增加一个观察者
    public void addObserver(Observer observer) {
        this.oVector.add(observer);
    }

    //删除一个观察者
    public void deleteObserver(Observer observer) {
        this.oVector.remove(observer);
    }

    //通知所有观察者
    public void notifyObserver() {
        for(Observer observer : this.oVector) {
            observer.update();
        }
    }

}

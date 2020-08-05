package com.设计模式.观察者模式;

public class ConcreteSubject extends  Subject  {

    //具体业务
    public void doSomething() {
               //...
              super.notifyObserver();
         }
}

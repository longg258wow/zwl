package com.设计模式.单例模式;

public class Singleton {

    private static class SingletonHolder{
        private static final Singleton singleton = new Singleton();
    }

    private Singleton(){}

    public  static final Singleton getInstance(){
        return SingletonHolder.singleton;
    }
}

package com.设计模式.单例模式;

/**
 * 懒汉式  线程不安全
 */
public class Lhan {
    private static Lhan lhan;
    private Lhan(){

    }
    public static Lhan getInstance(){
        if (lhan == null){
            lhan = new Lhan();
        }
        return lhan;
    }
}

package com.设计模式.单例模式;

/**
 * 双检索  懒汉式的线程安全版本
 */
public class DoubleCheck {
    private static DoubleCheck doubleCheck;
    private DoubleCheck() {

    }
    public static DoubleCheck getInstance(){
        if (doubleCheck == null){
            synchronized (DoubleCheck.class){
                if (doubleCheck == null){
                    doubleCheck = new DoubleCheck();
                }
            }
        }
        return doubleCheck;
    }
}

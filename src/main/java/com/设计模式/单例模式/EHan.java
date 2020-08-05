package com.设计模式.单例模式;

/**
 *  饿汉式
 */

public class EHan {
    private static EHan eHan = new EHan();

    private EHan() {

    }

    public static EHan getInstance() {

        return eHan;
    }
}

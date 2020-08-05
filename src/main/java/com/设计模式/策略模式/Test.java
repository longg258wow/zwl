package com.设计模式.策略模式;

public class Test {
    public static void main(String[] args) {
        Context context = new Context();
        context.factory("WIN");
        context.contextInterface();
    }
}

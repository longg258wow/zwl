package com.设计模式.策略模式;

public class Context {

    private Strategy strategy;

    //和简单工厂结合一下  可以消除客户端对具体策略的依赖
    public void factory(String strategyType) {
        if (strategyType.equals("WIN")) {
            strategy = new ConcreteStrategyB();
        } else if (strategyType.equals("LOSE")) {
            strategy = new ConcreteStrategyA();
        }
    }

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public Context() {

    }

    public void contextInterface() {
        strategy.algorithmLogic();
    }

}

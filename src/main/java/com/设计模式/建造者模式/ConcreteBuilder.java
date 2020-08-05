package com.设计模式.建造者模式;

class ConcreteBuilder extends Builder
{
    public void buildPartA()
    {
        product.setPartA("A产品");
        System.out.println("创建A产品...");
    }

    public void buildPartB()
    {
        product.setPartB("B产品");
        System.out.println("创建B产品...");
    }

    public void buildPartC()
    {
        product.setPartC("C产品");
        System.out.println("创建C产品...");
    }
}
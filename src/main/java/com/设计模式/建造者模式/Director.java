package com.设计模式.建造者模式;

public class Director {
    private Builder builder;
    public Director(Builder builder)
    {
        this.builder = builder;
    }
    public void setBuilder(Builder builder)
    {
        this.builder = builder;
    }

    public Product make()
    {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getResult();
    }

}

package com.设计模式.建造者模式;

/**
 *  1、定义一个产品类 Product    这个产品类里面维护一系列产品部件
 *  2、定义一个建造者类Builder 这个建造者维护一个产品 并提供建造产品部件的方法
 *  3、定义一个建造者具体类 这个类使用父类接口维护的产品建造各个产品部件
 * 4、 定义一个执行者Director  这个制定者维护一个建造者 对外提供一个制造方法 此方法调用建造者的建造方法S 并返回对象
 *
 * 一句话概括：执行者管理建造者 建造者根据产品部件设计建造方法
 *
 */
public class Test {
    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        Product product = director.make();
        System.out.println(product);
    }
}

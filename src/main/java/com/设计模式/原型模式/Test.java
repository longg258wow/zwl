package com.设计模式.原型模式;

/**
 *  1、先定义一个抽象原型  提供拷贝对象的方法 这里的抽象原型就是object
 *  2、再顶一个具体原型  实现原型克隆方法
 *  3、克隆
 *  浅拷贝 不改变对象引用的地址 所以两个对象共享一个引用对象
 *  深拷贝:对每一个引用的对象 继续调用其对象的clone方法 感觉很2B
 *  深拷贝：对象继承Serializable 同时对象依赖的对象也继承Serializable 可以用序列化方式返回深拷贝对象
 *  依赖对象如果没有Serializable 序列化报错  这就是为什么要用Serializable 这个空接口  深拷贝必备
 *
 *  提高性能。不用new对象，消耗的资源少。
 *  一句话：通过clone方法实现浅拷贝和深拷贝  浅拷贝很简单 深拷贝两种方式 倾向于第二种
 */
public class Test {
    public static void main(String[] args)throws Exception {
        Area area = new Area();
        area.setUnit("RMB");
        Money money = new Money(100,area);

//        for (int i = 1; i <= 3; i++) {
//            Money cloneMoney = money.clone();
//            cloneMoney.setFaceValue(i * 100);
//            System.out.println("这张是" + cloneMoney.getFaceValue() +   "的钞票");
//        }
        Money cloneMoney = (Money)money.clone();   //浅拷贝 不改变对象引用的地址 所以两个对象共享一个引用对象
        System.out.println(cloneMoney.getArea().getUnit()+" "+cloneMoney.getFaceValue());
        cloneMoney.setFaceValue(200);
        System.out.println(cloneMoney.getFaceValue()+" "+money.getFaceValue());
        cloneMoney.getArea().setUnit("美元");
        System.out.println(cloneMoney.getArea().getUnit()+" "+money.getArea().getUnit());

    }
}

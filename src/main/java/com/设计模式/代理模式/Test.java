package com.设计模式.代理模式;

/**
 *   业务对象接口 IDog
 *   业务对象  GunDog
 *   代理者 MyProxy
 *   代理所提供的的服务 ProxyService
 *   代理者工厂 MyProxyFactory
 *
 *   首先代理者MyProxy设置业务对象 在invoke方法中对业务对象进行AOP
 *   然后通过Proxy.newProxyInstance方法 带入被代理者GunDog，IDog MyProxy
 *   JDK生成新的业务对象代理者  这个类继承Proxy类 和 业务对象接口
 *   在业务对象代理者里 继承所有业务对象接口方法  这些方法 run: this.h.invoke(this, m3, null); （h就是MyProxy）
 *   这些方法代理者执行代理者的invoke方法  并将原业务对象的方法作为参数m 传入
 *   this.h.invoke 这段代码是死代码  所以代理者 MyProxy 要继承  InvocationHandler 重写invoke方法  这个invoke是调用而不是反射
 *   一句话概括 将通用代理MyProxy 和业务对象GunDog 组合成新的业务对象代理 实现AOP功能
 */



public class Test   {
    public static void main(String[] args) throws Exception{
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IDog dog = new GunDog();
        IDog proxy =(IDog) MyProxyFactory.getProxy(dog);
        proxy.run();
        proxy.dump(2);


    }
}

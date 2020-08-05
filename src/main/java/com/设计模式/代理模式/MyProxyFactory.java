package com.设计模式.代理模式;

import java.lang.reflect.Proxy;

public class MyProxyFactory {
    public static Object getProxy(Object target) {
        MyProxy proxy = new MyProxy();
        proxy.setTarget(target);
        //被代理对象类型，被代理对象接口  具体代理(InvocationHandler)
        //通过被代理类信息 创建他的代理类 代理类创建所继承接口下所有方法 并且该方法将执行代理者proxy的invoke方法
        Object beProxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), proxy);
        return beProxy;
    }
}

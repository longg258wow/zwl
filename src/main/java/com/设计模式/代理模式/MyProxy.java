package com.设计模式.代理模式;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxy  implements InvocationHandler {
    private Object target; //要代理的对象
    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ProxyService.method1();
        method.invoke(target, args);
        ProxyService.method2();
        return null;
    }


}

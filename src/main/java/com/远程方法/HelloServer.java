package com.远程方法;

import com.远程方法.jar.HelloImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class HelloServer {
    public static void main(String[] args) throws Exception{
        // 启动RMI注册服务，指定端口为1099　（1099为默认端口）
        // 也可以通过命令 ＄java_home/bin/rmiregistry 1099启动
        // 这里用这种方式避免了再打开一个DOS窗口
        // 而且用命令rmiregistry启动注册服务还必须事先用RMIC生成一个stub类为它所用
        LocateRegistry.createRegistry(8888);
        // 创建远程对象的一个或多个实例
        // 可以用不同名字注册不同的实例
        HelloImpl service = new HelloImpl("hello,world!");
        // 如果要把hello实例注册到另一台启动了RMI注册服务的机器上
        // Naming.rebind("//192.168.1.105:1099/Hello",hello);
      //  Naming.rebind("Hello", service);
        Naming.bind("rmi://localhost:8888/Hello", service);
        System.out.println("Hello Server is ready.");
    }
}

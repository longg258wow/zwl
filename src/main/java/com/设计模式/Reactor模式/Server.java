package com.设计模式.Reactor模式;

/**
 * 负责启动reactor服务并启动相关服务接收请求
 * InputSource 待处理的源对象
 * Acceptor：源对象分发器 这里维护一个选择器以及一个源对象等待队列 一个循环方法从等待队列取出源对象
 *           创建事件(源对象,事件类型) 随后把改事件放入选择器的事件队列
 * Selector：选择器 这里维护一个事件队列 提供一个查询事件的select方法 和add事件的方法
 * Dispatcher:事件分发器 这里维护一个<事件类型,事件处理对象>的map 提供对map增删改的方法
 *             同时提供一个dispatch方法 该方法会循环执行Selector的select方法
 *             取出待处理的事件 交给对应的事件处理对象处理
 * EventHandler：事件处理抽象类
 * AcceptEventHandler：具体处理类 维护Selector对象 处理完成后可创建新的事件对象
 *                      比如使用相同的事件源 指定新的事件类型
 * EventType：这里定义集中事件类型
 * Event： 事件，这里维护一个源对象和一个事件类型  一个事件三种类型 将来就会有三个事件
 *
 *
 *
 *
 */
public class Server {
    Selector selector = new Selector();
    Dispatcher eventLooper = new Dispatcher(selector);
    Acceptor acceptor;

    Server(int port) {
        acceptor = new Acceptor(selector, port);
    }

    public void start() {
        eventLooper.registEventHandler(EventType.ACCEPT, new AcceptEventHandler(selector));
        new Thread(acceptor, "Acceptor-" + acceptor.getPort()).start();

        eventLooper.handleEvents();
    }
}

package com.设计模式.Reactor模式;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 事件轮循选择器
 *  维护一个事件队列  提供一个addEvent 负责接收acceptor发过来的事件
 *  提供一个轮循队列选择事件的select方法
 *
 *
 */
public class Selector {
    //定义一个链表阻塞queue实现缓冲队列，用于保证线程安全
    private BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>();
    //定义一个object用于synchronize方法块上锁
    private Object lock = new Object();

      List<Event> select() {
        return select(0);
    }

      List<Event> select(long timeout) {
        if (timeout > 0) {
            if (eventQueue.isEmpty()) {
                synchronized (lock) {
                    if (eventQueue.isEmpty()) {
                        try {
                            lock.wait(timeout);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
        }
        //TODO 例子中只是简单的将event列表全部返回，可以在此处增加业务逻辑，选出符合条件的event进行返回
        List<Event> events = new ArrayList<Event>();
        eventQueue.drainTo(events);
        return events;
    }

    public void addEvent(Event e) {
        //将event事件加入队列
        boolean success = eventQueue.offer(e);
        if (success) {
            synchronized (lock) {
                //如果有新增事件则对lock对象解锁
                lock.notify();
            }
        }
    }
}

package com.设计模式.Reactor模式;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 事件分发器
 * 关联事件类型和事件处理类 用一个map维护
 * 提供一个dispatch 循环调用选择器的select方法 查到需要处理的事件源
 * 根据事件源找到处理器 然后处理器调用自己的handle方法处理
 *
 *
 */
public class Dispatcher {
    //通过ConcurrentHashMap来维护不同事件处理器
    Map<EventType, EventHandler> eventHandlerMap = new ConcurrentHashMap<EventType, EventHandler>();
    //本例只维护一个selector负责事件选择，netty为了保证性能实现了多个selector来保证循环处理性能，不同事件加入不同的selector的事件缓冲队列
    Selector selector;

    Dispatcher(Selector selector) {
        this.selector = selector;
    }

    //在Dispatcher中注册eventHandler
    public void registEventHandler(EventType eventType, EventHandler eventHandler) {
        eventHandlerMap.put(eventType, eventHandler);

    }

    public void removeEventHandler(EventType eventType) {
        eventHandlerMap.remove(eventType);
    }

    public void handleEvents() {
        dispatch();
    }

    //此例只是实现了简单的事件分发给相应的处理器处理，例子中的处理器都是同步，
    // 在reactor模式的典型实现NIO中都是在handle异步处理，来保证非阻塞
    //已启动一个线程
    private void dispatch() {
        while (true) {
            List<Event> events = selector.select();
            for (Event event : events) {
                EventHandler eventHandler = eventHandlerMap.get(event.getType());
                eventHandler.handle(event);
            }
        }
    }
}

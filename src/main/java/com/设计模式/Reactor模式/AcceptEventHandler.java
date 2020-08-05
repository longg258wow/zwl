package com.设计模式.Reactor模式;

/**
 * 具体的事件处理类
 *
 * handle  处理具体事件
 * 处理完之后 在handle里面创建新的事件 放入选择器事件队列
 *
 */
public class AcceptEventHandler extends EventHandler{
    private Selector selector;

    public AcceptEventHandler(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void handle(Event event) {
        //处理Accept的event事件
        if (event.getType() == EventType.ACCEPT) {

            //TODO 处理ACCEPT状态的事件
            //将事件状态改为下一个READ状态，并放入selector的缓冲队列中
            Event readEvent = new Event();
            readEvent.setSource(event.getSource());
            readEvent.setType(EventType.READ);
            selector.addEvent(readEvent);
        }
    }

}

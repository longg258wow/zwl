package com.设计模式.Reactor模式;

/**
 * 将需要处理的输入对象和他的不同状态组成一个事件对象
 */
public class Event{
    private InputSource source;
    private EventType type;

    public InputSource getSource() {
        return source;
    }

    public void setSource(InputSource source) {
        this.source = source;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }
}

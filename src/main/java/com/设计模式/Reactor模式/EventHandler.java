package com.设计模式.Reactor模式;

/**
 *  事件处理的抽象类
 */
public abstract  class EventHandler {
  //  private InputSource source;
    public abstract void handle(Event event);

//    public InputSource getSource() {
//        return source;
//    }
//
//    public void setSource(InputSource source) {
//        this.source = source;
//    }
}

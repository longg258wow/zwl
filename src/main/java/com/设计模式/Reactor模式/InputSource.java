package com.设计模式.Reactor模式;

/**
 * 需要处理的输入对象
 */
public class InputSource{
    private Object data;
    private long id;
    public InputSource(Object data, long id) {
        this.data = data;
        this.id = id;
    }
    @Override
    public String toString() {
        return "InputSource{" +
                "data=" + data +
                ", id=" + id +
                '}';
    }

}

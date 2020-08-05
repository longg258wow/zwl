package com.设计模式.观察者模式;

public class Test{
    /**
     *  Subject 主题父类
     *  ConcreteSubject 主题类
     *  Observer 观察者接口
     *  ConcreteObserver 观察者类
     *
     *  先定义一个Subject及其子类SubjectSun
     *  Subject维护一个观察者列表 提供一个向所有观察者发送消息的方法
     *  SubjectSun里定义一个厨房消息通知的方法 比如ConcreteSubject。doSomething
     *  再定义一个Observer  提供一个接受消息的方法  Subject调用此方法向Observer发送消息
     *  再定义一个ObserverSun  这里定义观察者收到消息后的处理
     *
     *  一句话概括   主题负责触发事件 维护观察者 向观察发送消息   观察者负责告诉主题通知他的方法
     * @param args
     */
    public static void main(String[] args) {
        //创建一个主题
        ConcreteSubject subject = new ConcreteSubject();
        //定义一个观察者
        Observer observer = new ConcreteObserver();
        //观察
        subject.addObserver(observer);
        //开始活动
        subject.doSomething();
    }

}

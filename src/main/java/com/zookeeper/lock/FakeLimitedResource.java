package com.zookeeper.lock;

public class FakeLimitedResource {
    private Integer ticket = 250;

    public void use() throws InterruptedException {
        try {
            System.out.println("火车票还剩" + (--ticket) + "张！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

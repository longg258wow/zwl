package com.zookeeper.Barrier;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class DistributedDoubleBarrierExample {

    private static final String PATH = "/examples/barrier";

    /** 客户端数量 */
    private static final int CLIENT_COUNT = 5;

    public static void main(String[] args) throws Exception {

        for(int i=0;i<CLIENT_COUNT;i++){
            final int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        CuratorFramework client = CuratorFrameworkFactory.newClient("172.20.10.9:2181",3000,3000, new ExponentialBackoffRetry(1000, 3,Integer.MAX_VALUE));
                        client.start();

                        //获取DistributedDoubleBarrier
                        DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(client, PATH,CLIENT_COUNT);
                        System.out.println("线程" +index+" 等待");
                        //调用enter阻塞,直到所有线程都到达之后执行,执行完毕之后，调用leave阻塞,直到所有线程都调用leave
                        barrier.enter();
                        System.out.println("线程" +index+" 已执行");
                        barrier.leave();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        Thread.sleep(Integer.MAX_VALUE);
    }

}
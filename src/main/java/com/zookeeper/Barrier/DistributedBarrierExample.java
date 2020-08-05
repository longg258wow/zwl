package com.zookeeper.Barrier;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class DistributedBarrierExample {

    private static final String PATH = "/examples/barrier";

    /** 客户端数量 */
    private static final int CLIENT_COUNT = 5;

    private static DistributedBarrier barrier;

    public static void main(String[] args) throws Exception {

        for(int i=0;i<CLIENT_COUNT;i++){
            final int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        CuratorFramework client = CuratorFrameworkFactory.newClient("172.20.10.9:2181",3000,3000, new ExponentialBackoffRetry(1000, 3,Integer.MAX_VALUE));
                        client.start();

                        //获取DistributedBarrier
                        barrier = new DistributedBarrier(client, PATH);
                        System.out.println("线程" +index+" 等待");
                        barrier.setBarrier();//设置屏障 阻塞到此的线程
                        barrier.waitOnBarrier(); //等到放行阻塞 再往下执行
                         System.out.println("线程" +index+" 已执行");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        Thread.sleep(20*1000);

        if(barrier != null){
            System.out.println("所有线程都已到达,准备启动");
            barrier.removeBarrier(); //放行阻塞
        }
    }

}
package com.zookeeper.leader;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestLeaderLatch {

    private static final String PATH = "/demo/leader";
    /** 5个客户端 */
    private static final Integer CLIENT_COUNT = 5;

    public static void main(String[] args) throws Exception {
        //5个线程,5个客户端
        ExecutorService service = Executors.newFixedThreadPool(CLIENT_COUNT);
        for (int i = 0; i < CLIENT_COUNT ; i++) {
            final int index = i;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        new TestLeaderLatch().schedule(index);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        //休眠50秒之后结束main方法
        Thread.sleep(30 * 1000);
        service.shutdownNow();
    }

    private void schedule(int thread) throws Exception {

        //获取一个client
        CuratorFramework client = this.getClient(thread);
        //获取一个latch
        LeaderLatch latch = new LeaderLatch(client, PATH,String.valueOf(thread));

        //给latch添加监听，在
        latch.addListener(new LeaderLatchListener() {

            @Override
            public void notLeader() {
                //如果不是leader
                System.out.println("Client [" + thread + "] I am the follower !");
            }

            @Override
            public void isLeader() {
                //如果是leader
                System.out.println("Client [" + thread + "] I am the leader !");
            }
        });

        //开始选取 leader
        latch.start();

        //每个线程 休眠时间不一样,但是最大不能超过 main方法中的那个休眠时间,那个是50秒 到时候main方法结束 会中断休眠时间
        Thread.sleep(2 * (thread + 5) * 1000);
        if (latch != null) {
            //释放leadership
            //CloseMode.NOTIFY_LEADER 节点状态改变时,通知LeaderLatchListener
            latch.close(LeaderLatch.CloseMode.NOTIFY_LEADER);
        }
        if (client != null) {
            client.close();
        }
        System.out.println("Client [" + latch.getId() + "] Server closed...");
    }

    private CuratorFramework getClient(final int thread) {
        RetryPolicy rp = new ExponentialBackoffRetry(1000, 3);
        // Fluent风格创建
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.58.42:2181")
                .sessionTimeoutMs(1000000)
                .connectionTimeoutMs(3000)
                .retryPolicy(rp)
                .build();
        client.start();
        System.out.println("Client [" + thread + "] Server connected...");
        return client;
    }

}
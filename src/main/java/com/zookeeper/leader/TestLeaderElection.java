package com.zookeeper.leader;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestLeaderElection {

    private static final String PATH = "/demo/leader";
    /** 3个客户端 */
    private static final Integer CLIENT_COUNT = 3;

    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(CLIENT_COUNT);

        for (int i = 0; i < CLIENT_COUNT; i++) {
            final int index = i;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        new TestLeaderElection().schedule(index);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        Thread.sleep(30 * 1000);
        service.shutdownNow();
    }

    private void schedule(final int thread) throws Exception {
        CuratorFramework client = this.getClient(thread);
        CustomLeaderSelectorListenerAdapter leaderSelectorListener =
                new CustomLeaderSelectorListenerAdapter(client, PATH, "Client #" + thread);
        leaderSelectorListener.start();
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

package com.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class PathChildrenCacheSample {
    static String path = "/zk-book2";
    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.58.42:2181")
            .sessionTimeoutMs(60000)
            .connectionTimeoutMs(15000)
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static void main(String[] args) throws Exception {
        client.start();
        PathChildrenCache cache = new PathChildrenCache(client,path,true);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                switch (event.getType()) {
                    case CHILD_ADDED:
                        System.out.println("=====> CHILD_ADDED : "+ event.getData().getPath() +"  数据:"+ new String(event.getData().getData()));
                        break;
                    case CHILD_REMOVED:
                        System.out.println("=====> CHILD_REMOVED : "+ event.getData().getPath() +"  数据:"+ new String(event.getData().getData()));
                        break;
                    case CHILD_UPDATED:
                        System.out.println("=====> CHILD_UPDATED : "+ event.getData().getPath() +"  数据:"+ new String(event.getData().getData()));
                        break;
                    default:
                        break;
                }
            }
        });
        client.create().withMode(CreateMode.PERSISTENT).forPath(path,"init".getBytes());
        Thread.sleep(1000);
        client.create().withMode(CreateMode.PERSISTENT).forPath(path+"/c1");
        Thread.sleep(1000);
        client.setData().forPath(path+"/c1","I love you".getBytes());
        Thread.sleep(1000);
        client.delete().forPath(path+"/c1");
        Thread.sleep(1000);
        client.delete().forPath(path);
        Thread.sleep(10000);
        cache.close();
        client.close();
    }
}
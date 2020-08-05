package com.zookeeper.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class MyConnectionStateListener implements ConnectionStateListener {

    /** 节点路径 */
    private String zkRegPathPrefix;
    /** 节点内容 */
    private String regContent;

    public MyConnectionStateListener(String zkRegPathPrefix, String regContent) {
        this.zkRegPathPrefix = zkRegPathPrefix;
        this.regContent = regContent;
    }

    @Override
    public void stateChanged(CuratorFramework client, ConnectionState newState) {
        if (newState == ConnectionState.LOST) {
            //连接丢失
            System.out.println("lost session with zookeeper");
            System.out.println("锁已经释放,不再拥有该锁");
            while(true){
                try {
                    System.err.println("尝试重新连接......");
                    if(client.getZookeeperClient().blockUntilConnectedOrTimedOut()){
                        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(zkRegPathPrefix, regContent.getBytes("UTF-8"));
                        break;
                    }
                } catch (InterruptedException e) {
                    break;
                } catch (Exception e){
                    //TODO: log something
                }
            }
        } else if (newState == ConnectionState.CONNECTED) {
            //连接新建
            System.out.println("connected with zookeeper");
        } else if (newState == ConnectionState.RECONNECTED) {
            //重新连接
            System.out.println("reconnected with zookeeper");
        }
    }

    public static void main(String[] args) {
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.58.42:2181",3000,3000, new ExponentialBackoffRetry(1000, 3,Integer.MAX_VALUE));
        client.start();
// todo 在此可添加ConnectionStateListener监听
        MyConnectionStateListener connectionStateListener = new MyConnectionStateListener("ass","123456");
        client.getConnectionStateListenable().addListener(connectionStateListener);
        System.out.println("Server connected...");
    }

}

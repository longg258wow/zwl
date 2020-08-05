package com.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.transaction.CuratorOp;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Collection;

public class TransactionExamples {
    public static void main(String[] args) throws Exception {
        String connectionString = "192.168.58.42:2181";
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3,Integer.MAX_VALUE);
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
        client.start();

        //开始事务操作
        CuratorOp createParentNode = client.transactionOp().create().forPath("/a", "some data".getBytes());
        CuratorOp createChildNode = client.transactionOp().create().forPath("/a/path", "other data".getBytes());
        CuratorOp setParentNode = client.transactionOp().setData().forPath("/a", "other data".getBytes());
        CuratorOp deleteParent = client.transactionOp().delete().forPath("/a");

        Collection<CuratorTransactionResult> results = client.transaction().forOperations(createParentNode, createChildNode, setParentNode,deleteParent);

        for ( CuratorTransactionResult result : results ){
            System.out.println(result.getForPath() + " - " + result.getType());
        }
    }
}

package com.zookeeper.Counter;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.shared.SharedCount;
import org.apache.curator.framework.recipes.shared.SharedCountListener;
import org.apache.curator.framework.recipes.shared.SharedCountReader;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SharedCounterExample implements SharedCountListener {
    private static final int CLIENT_COUNT = 5;
    private static final String PATH = "/examples/counter";

    @Override
    public void stateChanged(CuratorFramework arg0, ConnectionState arg1) {
        System.out.println("State changed: " + arg1.toString());
    }
    @Override
    public void countHasChanged(SharedCountReader sharedCount, int newCount) throws Exception {
        System.out.println("Counter's value is changed to " + newCount);
    }

    public static void main(String[] args) throws Exception {
        final Random rand = new Random();
        SharedCounterExample example = new SharedCounterExample();
        try{
            CuratorFramework client = CuratorFrameworkFactory.newClient("172.20.10.9:2181",3000,3000, new ExponentialBackoffRetry(1000, 3,Integer.MAX_VALUE));
            client.start();

            SharedCount baseCount = new SharedCount(client, PATH, 0);
            baseCount.addListener(example);
            baseCount.start();

            List<SharedCount> examples = Lists.newArrayList();
            ExecutorService service = Executors.newFixedThreadPool(CLIENT_COUNT);
            for (int i = 0; i < CLIENT_COUNT; i++) {
                final SharedCount count = new SharedCount(client, PATH, 0);
                examples.add(count);
                Callable<Void> task = new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        count.start();
                        Thread.sleep(rand.nextInt(10000));
                        int add = count.getCount() + rand.nextInt(10);
                        System.out.println("要更改的值为: "+add);
                        boolean b = count.trySetCount(count.getVersionedValue(), add);
                        System.out.println("更改结果为: " + b);
                        return null;
                    }
                };
                service.submit(task);
            }

            service.shutdown();
            service.awaitTermination(50, TimeUnit.MINUTES);


                for (int i = 0; i < CLIENT_COUNT; i++) {
                    examples.get(i).close();
                }
            baseCount.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

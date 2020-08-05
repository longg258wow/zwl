package com.zookeeper.Barrier;

import java.io.IOException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCyclicBarrier {

    /** 参赛人数 */
    public static Integer RUNNER_COUNT = 3;

    /**
     * 所有线程进入就绪后启动
     */
    public static CyclicBarrier barrier = new CyclicBarrier(RUNNER_COUNT);
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for(int i=1; i<=RUNNER_COUNT; i++){
            final int index = i;
            executor.submit(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("参赛者"  + index+ "准备好了.");
                    try {
                        TestCyclicBarrier.barrier.await();
                    } catch (Exception e) {}
                    System.out.println("参赛者"  + index+" 开跑！");
                }
            }));
        }
        System.out.println("XXXXXXXXXXXX");
        executor.shutdown();
        System.out.println("YYYYYYY");
    }

}
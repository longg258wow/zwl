package com.测试工具;

import java.util.concurrent.CountDownLatch;

class Worker implements Runnable{

    CountDownLatch countDownLatch;
    Integer threadNo;

    Worker(CountDownLatch countDownLatch,Integer threadNo){
        this.countDownLatch = countDownLatch;
        this.threadNo = threadNo;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await(); // 等待其它线程
            System.out.println(Thread.currentThread().getName() + "启动@" + System.currentTimeMillis());
                 HttpUtil.testPost(threadNo);
           //    JdbcUtil.jdbcTest(threadNo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


public class ThreadUtil {
    public static void doTest() throws InterruptedException {
        final int N = 1000; // 线程数
        CountDownLatch countDownLatch = new CountDownLatch(N);
        for(int i=0;i<N;i++){
            new Thread(new Worker(countDownLatch,i)).start();
            countDownLatch.countDown();
        }
    }
    public static void main(String[] args)throws Exception {
        ThreadUtil.doTest();
    }
}

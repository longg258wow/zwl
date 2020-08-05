package com.多线程.高级线程;

import java.io.FileInputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * await 让线程暂时阻塞 调用countDown后 计数器-1 计数器==0时  次计数器造成的线程阻塞被唤醒 线程继续运行
 * 本质就是控制线程运行和停止 作为构造函数传递给线程
 */
public class CountDownLatchTest {
    private static final int RUNNER_COUNT = 5;
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch begin = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(RUNNER_COUNT);
        final ExecutorService exec = Executors.newFixedThreadPool(10);

        for (int i = 0; i < RUNNER_COUNT; i++) {
            final int NO = i + 1;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {

                        begin.await();
                        System.out.println("No." + NO + " arrived");


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        end.countDown();
                    }
                }
            };
            exec.submit(run);
        }


        System.out.println("Game Start ...");
        begin.countDown();
        System.out.println("现在等待最后一个白痴");
        end.await();
        System.out.println("最后一个白痴终于到了");
//        end.await(30, TimeUnit.SECONDS);
//        System.out.println("Game Over.");
//
        exec.shutdown();
    }
}

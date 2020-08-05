package com.多线程.threadpool;

/**
 * 工作线程
 */
class Worker extends Thread {
    Thread thread; //要执行的用户线程
    ThreadPool threadPool; //所在线程池
    Object object = new Object(); //用于缓刑等待状态的同步锁

    public Worker(ThreadPool threadPool){
        this.threadPool = threadPool;
    }

    @Override
    public void run() {
        try {
            synchronized (object){
                while (true){
                    thread.run();
                    while(threadPool.waitThreadQueue.size()>0){
                        Thread waitThread = (Thread) threadPool.waitThreadQueue.poll();
                        thread = waitThread;
                        thread.run();
                    }
                    object.wait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程彻底结束"+Thread.currentThread().getId());
        }
    }
}
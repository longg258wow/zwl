package com.多线程.threadpool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 线程池
 */
public class ThreadPool {
    public Integer size;  //线程大小
    List<Worker> workerList = new ArrayList();  //线程列表
    Queue<Thread> waitThreadQueue = new LinkedList();   //等待队列

    public ThreadPool(Integer size) {   //初始化线程池里的工作线程
        this.size = size;
        for (int i = 0; i < size; i++) {
            Worker taskThread = new Worker(this);
            workerList.add(taskThread);
        }
    }

    public static void main(String[] args) throws Exception {
        //  jdkThreadPool();
        getAllThread();
        myThreadPool();
    }


    /**
     * 自己写的线程池
     */
    public static void myThreadPool() throws Exception {
        ThreadPool threadPool = new ThreadPool(2);  //创建有2个工作线程的线程池
        for (int i = 0; i < 3; i++) {   //创建3个用户线程
            Task task = new Task("taskX_" + i, 1000);
            System.out.println("Created : " + task.getId() + " name = " + task.tName);
            threadPool.exe(task); //开始执行用户线程
        }


        System.out.println("第一波结束");
        Thread.sleep(5000);  //模拟5秒之后等待队列里有新的用户线程等待处理
        System.out.println("5秒后又有白痴来了");
        Task task = new Task("taskX_10",2000); //创建新的用户线程并放入等待对了
        threadPool.waitThreadQueue.offer(task);
        Worker exeTaskThread = null;
        for(Worker taskThread:threadPool.workerList){  //寻找线程池中的等待线程
            System.out.println("id = "+ taskThread.getId() +  "state = "+ taskThread.getState());
            if(taskThread.getState().equals(Thread.State.WAITING)){
                exeTaskThread = taskThread;
                break;
            }
        }

        synchronized (exeTaskThread.object){ //缓刑等待线程并处理
            System.out.println("又开始了 真牛逼");
            exeTaskThread.thread = threadPool.waitThreadQueue.poll();
            exeTaskThread.object.notify();
        }

    }



    public void exe(Thread thread) { //执行某个用户线程  讲用户线程交给工作线程
        Worker exeThread = null;
        for (Worker taskThread : workerList) {
            System.out.println("taskThread : " + taskThread.getId() + " state = " + taskThread.getState());
            if (!taskThread.isAlive()) {
                exeThread = taskThread;
                break;
            }
        }
        if (exeThread != null) {
            exeThread.thread = thread;
            System.out.println("exeThread : " + exeThread.getId() + " isAlive = " + exeThread.isAlive());
            exeThread.start();
        } else {//所有线程繁忙
            waitThreadQueue.offer(thread);
            System.out.println("加入" + thread.getId());
        }
    }

    /**
     * jdk自带线程池
     */
    public static void jdkThreadPool() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        for (int i = 0; i < 3; i++) {
            Task task = new Task("taskAA_", 3);
            //    System.out.println("Created : " + task.getId() + " tName = " + task.tName);
            executor.execute(task);
        }
        //   System.out.println("全部执行完毕");
        executor.shutdown();
    }

    /**
     * 当前进程下所有线程
     */
    public static void getAllThread() {
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = group;
        // 遍历线程组树，获取根线程组
        while (group != null) {
            topGroup = group;
            group = group.getParent();
        }
        // 激活的线程数再加一倍，防止枚举时有可能刚好有动态线程生成
        int slackSize = topGroup.activeCount() * 2;
        System.out.println("slackSize = " + slackSize);
        Thread[] slackThreads = new Thread[slackSize];
        // 获取根线程组下的所有线程，返回的actualSize便是最终的线程数
        int actualSize = topGroup.enumerate(slackThreads);
        Thread[] atualThreads = new Thread[actualSize];
        // 复制slackThreads中有效的值到atualThreads
        System.arraycopy(slackThreads, 0, atualThreads, 0, actualSize);
        System.out.println("Threads size is " + atualThreads.length);
        for (Thread thread : atualThreads) {
            System.out.println("Thread name : " + thread.getName());
        }
    }


}


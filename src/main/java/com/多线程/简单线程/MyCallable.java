package com.多线程.简单线程;

import lombok.Data;

import java.util.concurrent.*;

/**
 * 基本同 Runnable     Callable有返回值 Runnable  没有 call可以抛异常
 * callable和runnable都可以应用于executors。而thread类只支持runnable call也是同步方法
 */
@Data
public class MyCallable implements Callable<Integer> {


    public MyCallable(){

    }
    public MyCallable(Integer index){
        this.index = index;

    }
    private Integer index;
    @Override
    public Integer call() throws Exception {
        System.out.println("MyCallable 执行"+index);
        Thread.sleep(3000);
        System.out.println("MyCallable OK"+index);
        return  3;
    }

    public static void main(String[] args)throws Exception {
        int corePoolSize =2; // 线程池长期维持的线程数，即使线程处于Idle状态，也不会回收。
        int maximumPoolSize=2;// 线程数的上限
        long keepAliveTime=2L ;
        TimeUnit unit  =TimeUnit.DAYS; //超过corePoolSize的线程的idle时长  超过这个时间，多余的线程会被回收。
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(512) ;// 任务的排队队列
        //以上五个是通用参数
        ThreadFactory threadFactory ;// 新线程的产生方式
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy(); // 拒绝策略

        ExecutorService pool = Executors.newFixedThreadPool(1);
        ThreadPoolExecutor service = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,handler);
        //submit将同步执行   execute 和 submit 区别同Callable和Runnable 区别 返回对象Future
        service.execute(new MyRunnable());
        service.execute(new MyThread());
         Future future =   service.submit(new MyCallable());


        service.shutdown();
    }
}

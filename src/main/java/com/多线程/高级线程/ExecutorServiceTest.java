package com.多线程.高级线程;

import com.多线程.简单线程.MyCallable;
import io.netty.handler.codec.http.HttpContentEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceTest {
    public static void main(String[] args) throws  Exception{
        //线程池
        ExecutorService pool = Executors.newFixedThreadPool(1);


        int corePoolSize =2; // 线程池长期维持的线程数，即使线程处于Idle状态，也不会回收。
        int maximumPoolSize=2;// 线程数的上限
        long keepAliveTime=2L ;
        TimeUnit unit  =TimeUnit.DAYS; //超过corePoolSize的线程的idle时长  超过这个时间，多余的线程会被回收。
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(512) ;// 任务的排队队列
        //以上五个是通用参数
        ThreadFactory threadFactory ;// 新线程的产生方式
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy(); // 拒绝策略
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,handler);
        Future<Integer>   future = threadPoolExecutor.submit(new MyCallable(0));
        future.get();

        Collection<Callable<Integer>> solvers = new ArrayList<>();
        solvers.add(new MyCallable(0));
        solvers.add(new MyCallable(1));
        CompletionService<Integer> ecs = new ExecutorCompletionService<Integer>(threadPoolExecutor);// 构造器
        int n = solvers.size();
        for (int i = 0; i < n; ++i) {// 获取每一个完成的任务
            Integer r = ecs.take().get();
        }


//        //线程返回结果集合
//        List<Future<Integer>> list=new ArrayList<Future<Integer>>();
//        for (int i = 0; i < 3; i++) {
//            Future<Integer>  future=pool.submit(new MyCallable(i));
//            list.add(future);
//        }
//        pool.shutdown();
//        for (int i=0;i<list.size();i++){
//            Future<Integer> future =list.get(i);
//            System.out.println(i+" = "+future.get());
//        }


    }

}

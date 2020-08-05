package com.多线程.高级线程;

import com.多线程.简单线程.MyCallable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *     start方法后不同步 之后通过get方法获得执行结果 get方法是阻塞方法 is方法非阻塞
 * 只能传递MyCallable作为构造函数
 *
 */
public class FutureTaskTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long starttime = System.currentTimeMillis();
        FutureTask<Integer> data1 = new FutureTask(new MyCallable());
        new Thread(data1).start();
        System.out.println(11111);
        FutureTask<Integer> data2 = new FutureTask<>(new MyCallable());
        new Thread(data2).start();
        System.out.println(22222);

        Integer integer1 = data1.get();
        System.out.println("integer1"+integer1);
        Integer integer2 = data1.get();
        System.out.println("integer2"+integer2);
        System.out.println(algorithm(integer1, integer2));
        long endtime = System.currentTimeMillis();
        System.out.println("用时：" + String.valueOf(endtime - starttime));
    }

    public static int algorithm(int input, int input2) {
        return input + input2;
    }
}

package com.设计模式.回调模式;

import javafx.util.Callback;
import lombok.Data;
import org.apache.curator.framework.recipes.atomic.AtomicValue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Data
public class Student  {

    private String name = null;
    public Student(String name){
        this.name = name;
    }

    private int calcADD(int a, int b){
        return a + b;
    }

    public void fillBlank(int a, int b,int result){
//        int result = calcADD(a, b);
//        System.out.println(name + "心算:" + a + " + " + b + " = " + result);

      //  int result = useCalculator(a, b);
     //   System.out.println(name + "使用计算器:" + a + " + " + b + " = " + result);

        System.out.println(name + "求助小红算账:" + a + " + " + b + " = " + result + "元");
    }

    private int useCalculator(int a, int b){
        return new Calculator().add(a, b);
    }

    //同步回调
    public void callHelp (int a, int b)throws Exception{
        new SuperCalculator().add(a, b, this);
    }
    //异步回调
    public void callHelp2 (int a, int b,Student stu)throws Exception{
        ExecutorService service = Executors.newFixedThreadPool(1);
        Callable<Void> task = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                try {
                    new SuperCalculator().add(a, b, stu);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        service.submit(task);
        service.shutdown();
    }
}

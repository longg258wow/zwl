package com.lambda;

import java.util.function.*;

public class Test {
    public static void main(String[] args) {

        //part 1
        //匿名内部类
        Comparable<Integer> comparable1 = new Comparable<Integer>() {
            @Override
            public int compareTo(Integer o) {
                return 0;
            }
        };
        comparable1.compareTo(1);
        //Lambda表达式： 正常格式(x,y) -> {} 如果接口里只有一个方法 就是这样
        Comparable<Integer> comparable2 = (x) -> Integer.compare(x, 100);
        comparable2.compareTo(2);

        //part2
        //消费型接口Consumer，输入一个参数，对其进行打印输出
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("hehe");


        //供给型接口Supplier，返回指定字符串
        Supplier<String> supplier = () -> "Hello world!";
        //获取字符串
        supplier.get();

        //函数型接口Function，输入字符串，返回字符串长度
        Function<String, Integer> function = (x) -> x.length();
        //获取字符串长度
        function.apply("Hello world!");

        //断言型接口Predicate，输入数字，判断是否大于0
        Predicate<Integer> predicate = (x) -> x > 0;
        //获取判断结果
        predicate.test(10);

        Consumer<String> consumer1 = System.out::println;
        consumer1.accept("hehe");

        //lambda表达式常用方式
        BiPredicate<String, String> bp1 = (x, y) -> x.equals(y);
        //方法引用：类::实例方法（方法传入参数是两个参数，且第一个参数作为方法调用对象，第二个参数作为调用的方法的参数）
        BiPredicate<String, String> bp2 = String::equals;

    }
}

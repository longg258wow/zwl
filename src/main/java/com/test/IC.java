package com.test;

public interface IC extends IA ,IB{
    @Override
    default void   exeA(){
        System.out.println("aaaaaaa");
    }




}

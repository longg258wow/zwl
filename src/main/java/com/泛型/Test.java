package com.泛型;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private Integer a;
    public static void main(String[] args) {
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();
        System.out.println(classStringArrayList);
        System.out.println(classIntegerArrayList);
        if(classStringArrayList.equals(classIntegerArrayList)){
            System.out.println("1111");
        }
    }
}

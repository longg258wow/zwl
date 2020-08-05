package com.反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {

    //1、Declared  私有
    // 反射拿到类对象的三种方式
    //1、  Class userClass=  Class.forName("com.反射.User");
    //2、   Class userClass=  User.class;
        //3、   Class userClass= new User("aa").getClass();

    //拿到类的两种方式
    // 1、 Object obj = userClass.newInstance();
    // 2、Constructor constructor = userClass.getConstructor(String.class);
    //    Object obj = constructor.newInstance("xxxx");

    //拿到类的属性
    //Field[] fields = userClass.getFields();  只包含public
    // Field[] fields = userClass。getDeclaredFields()  包含全部



    public static void main(String[] args) throws Exception {
        Class userClass = Class.forName("com.反射.User");

        //   Class userClass=  User.class;
        //   Class userClass= new User("aa").getClass();

        //       Object obj = userClass.newInstance();
        Constructor constructor = userClass.getConstructor(String.class);

        Object obj = constructor.newInstance("xxxx");

               Field[] fields = userClass.getFields();
        for(Field field:fields){

            System.out.println("field = "+field.getName());
        }

        Method[] methods = userClass.getMethods();
        for(Method method:methods){
            System.out.println(method.getDeclaringClass());
        }

        Method method = userClass.getMethod("setUserName", String.class);
        method.invoke(obj, "aaa");
    }
}

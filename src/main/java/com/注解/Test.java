package com.注解;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) throws  Exception{
      Class userClass = User.class;
      Object object = userClass.newInstance();
        Field field =  userClass.getDeclaredField("userName");
        MyAnno myAnno =  field.getAnnotation(MyAnno.class);
        System.out.println(myAnno.value1()+"   "+myAnno.value2());
        AnnotatedType annotatedType = field.getAnnotatedType();


        System.out.println("annotatedType = "+annotatedType.toString());
      //for(Annotation annotation:annotations){

    }
}

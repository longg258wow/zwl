package com.注解;

import com.枚举.PlateEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyAnno {
    int value1();
    String value2();
    PlateEnum value3();
    Class<User> value4();

}

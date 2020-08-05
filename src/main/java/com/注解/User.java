package com.注解;

import com.枚举.PlateEnum;

public class User {

    @MyAnno(value1 = 1,value2 = "3",value3 = PlateEnum.audioAdv,value4= User.class)
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

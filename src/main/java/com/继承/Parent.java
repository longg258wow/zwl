package com.继承;

/**
 *  父类
 */
public class Parent {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void huahua(){
        System.out.println("她喜欢画画");
    }

    public Parent getP(){
        System.out.println("p getp exe");
        return  new Son();
    }
}

package com.继承;

public class Son extends Parent{
    public String name;

    public String age;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Parent getP() {
        System.out.println("s getp exe");
        return new Son();
    }

    public Integer getX(Integer aa){
        return 1;
    }

    public Parent getX(String age){
        return  new Son();
    }
}

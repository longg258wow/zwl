package com.test;

public class AA implements IC {
    @Override
    public void exeA() {
        IC.super.exeA();
    }

}

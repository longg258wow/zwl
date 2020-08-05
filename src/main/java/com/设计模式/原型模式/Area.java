package com.设计模式.原型模式;

import java.io.Serializable;

/**
 * 如果不继承Cloneable接口就是浅拷贝
 */
public class Area  implements Cloneable, Serializable {
    private String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

//    @Override
//    protected Area clone() throws CloneNotSupportedException {
//        Area cloneArea;
//        cloneArea = (Area) super.clone();
//        return cloneArea;
//    }
}

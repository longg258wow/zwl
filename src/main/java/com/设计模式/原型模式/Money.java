package com.设计模式.原型模式;

import java.io.*;

public class Money implements Cloneable , Serializable {

    private int faceValue;
    private Area area;

    public Money(int faceValue , Area area) {
        this.faceValue = faceValue;
        this.area = area;
    }

    @Override
    protected Money clone()   {
        Money cloneMoney  = null;
        try {
            // 调用deepClone，而不是Object的clone方法
          //  cloneMoney = (Money) super.clone();
         //   cloneMoney.area = this.area.clone();  // 不适用序列化深拷贝时必须每个依赖对象都靠背一次比较脑残
            cloneMoney = (Money) deepClone();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cloneMoney;
    }

    /**
     * 使用序列化达到深拷贝
     */
    public Object deepClone () throws IOException, ClassNotFoundException{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }


    public int getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(int faceValue) {
        this.faceValue = faceValue;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}

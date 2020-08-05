package com.设计模式.建造者模式;

/**
 * 需要间杂的商品
 */
public class Product {
    private String partA; //可以是任意类型  通常可以是一个接口
    private String partB;
    private String partC;

    public String getPartA()
    {
        return partA;
    }

    public void setPartA(String partA)
    {
        this.partA = partA;
    }

    public String getPartB()
    {
        return partB;
    }

    public void setPartB(String partB)
    {
        this.partB = partB;
    }

    public String getPartC()
    {
        return partC;
    }

    public void setPartC(String partC)
    {
        this.partC = partC;
    }

    @Override
    public String toString()
    {
        return "Product [partA=" + partA + ", partB=" + partB + ", partC=" + partC + "]";
    }
}

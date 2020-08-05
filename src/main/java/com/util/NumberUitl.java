package com.util;

import java.math.BigDecimal;

public class NumberUitl {

    /**
     * 保留n位小数
     * BigDecimal.ROUND_UP : 先舍掉末尾 再加一
     * BigDecimal.ROUND_DOWN ：舍掉末尾
     * BigDecimal.ROUND_CEILING   正数时同1 负数时同2
     * @return.ROUND_FLOOR   正数时同2负数时同1
     * BigDecimal.ROUND_HALF_UP    四舍五入
     * BigDecimal.ROUND_HALF_DOWN 五舍六入
     */
    public static Double retainDecimal(Double num,Integer digit ,Integer opt){
        BigDecimal   b   =   new BigDecimal(num);
        double   f1   =   b.setScale(digit,  opt ).doubleValue();
        return f1;
    }

    /**
     * 得到 min 和max 之间的随机正数  区间范围是[ )
     * @param min
     * @param max
     * @return
     */
    public static Integer getRandomInt(Integer min,Integer max){
        return (int) (Math.random() * (max - min) + min);
    }

    public static String getRandomNumber(Integer length){
        return ""+(int)((Math.random()*9+1)*Math.pow(10,length-1));
    }


    public static boolean isNumeric(String str){
        for (int i = 0; i < str.length(); i++){
            System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(getRandomNumber(6));

    }
}

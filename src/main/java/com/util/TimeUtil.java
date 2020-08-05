package com.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {


    public static String TimeFormat_0 =  "yyyy-MM-dd HH:mm:ss";

    /**
     * 返回两个时间中的随机时间
     * @param start
     * @param end
     * @return
     */
    public static Date randomDate(Date start,Date end){
        try {

            System.out.println(start + " xxxxx  "+end);
            if(start.getTime() >= end.getTime()){
                return null;
            }

            long date = start.getTime() + (long)(Math.random() * (end.getTime() - start.getTime()));
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到某天凌晨的日期
     * @param targetDate
     * @return
     */
    public static Date getTargetDayZero(Date targetDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(targetDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        return zero;
    }


    /**
     * 得到目标日期
     * @param offset
     * @return
     */
    public static Date getTargetDay(Integer offset){
        Long dayTime =  1000 * 60 * 60 * 24L;
        Date targetDate = new Date(System.currentTimeMillis() +  offset * dayTime);
        return targetDate;
    }

    /**
     * 得到两个日期间隔毫秒数
     * @param startDate
     * @param endDate
     * @return
     */
    public static  Long  getTimeInterval(Date startDate,Date endDate){
        Long interval = endDate.getTime() - startDate.getTime();
        return interval;
    }


    public static String formatDate(Date date,String formatType){
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatType);
        return   dateFormat.format(date);
    }

}

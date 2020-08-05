package com.util;

import java.util.Date;

public class DateUtil {

    public enum timeTypeEnum {
        second, minute,   hour,  day,month, year,
        front,back




    }

    public static Integer getTimeInterval(Date startTime, Date endTime, DateUtil.timeTypeEnum timeType) {
        Integer timeInterval = 0;
        if (timeType.equals(timeTypeEnum.second)) {
            Long timeIntervalLong = ((endTime.getTime() - startTime.getTime()) / 1000);
            timeInterval = timeIntervalLong.intValue();
        }

        return timeInterval;
    }

    public static Date getTimeIntervalDate(Date date, Integer timeInterval, DateUtil.timeTypeEnum timeType) {
        Date otherDate = null;
        if (timeType.equals(timeTypeEnum.front)) {
            otherDate = new Date(date.getTime() - timeInterval*1000);
        }else if (timeType.equals(timeTypeEnum.back)){
            otherDate = new Date(date.getTime() + timeInterval*1000);
        }
        return otherDate;
    }

}

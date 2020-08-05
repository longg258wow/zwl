package com.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.Set;

public class CriteriaUtil {
    /**
     * 根据排序JSONObject拼接排序sql
     * @param orderCriteria
     * @return
     */
    public static String getOrderSql(JSONObject orderCriteria){
        Set<String> keySet = orderCriteria.keySet();
        Iterator keySetIterator = keySet.iterator();
        StringBuffer orderStr = new StringBuffer();
        while (keySetIterator.hasNext()) {
            String key = (String) keySetIterator.next();
            JSONObject valueJson = orderCriteria.getJSONObject(key);
            System.out.println(  key+" : "+valueJson);
            String dbKey = StringUtil.humpToLine(key);
            String orderType = valueJson.getString("type");
            orderStr.append(dbKey+" "+orderType +",");
        }
        orderStr.deleteCharAt(orderStr.length() - 1);
        return orderStr.toString();
    }
}

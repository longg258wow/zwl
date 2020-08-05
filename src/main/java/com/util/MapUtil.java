package com.util;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MapUtil {

    /**
     * 递归将JSONObject转换成map
     * @param jsonObject
     * @return
     */
    public static HashMap parseJSONObjectToMap(JSONObject jsonObject){
        HashMap map = new HashMap();
        Set<String> keySet = jsonObject.keySet();
        Iterator keySetIterator = keySet.iterator();
        while (keySetIterator.hasNext()) {
            String itemKey = (String) keySetIterator.next();
            Object itemVal = jsonObject.get(itemKey);
            if(itemVal instanceof String){
                map.put(itemKey,itemVal);
            }else if(itemVal instanceof Integer){
                map.put(itemKey,itemVal);
            } else if(itemVal instanceof JSONObject){
                HashMap itemFieldValMap =  parseJSONObjectToMap((JSONObject)itemVal);
                map.put(itemKey,itemFieldValMap);
            }
        }
        return map;

    }
}

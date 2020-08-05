package com.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class SqlUtil {

    public  static void addSqlParam(JSONObject jsonObject,HashMap sqlMap)throws Exception{
        JSONObject searcherJson = jsonObject.getJSONObject("searcher");
        JSONObject filterJson = jsonObject.getJSONObject("filter");
        JSONObject sorterJson = jsonObject.getJSONObject("sorter");
        SqlUtil.addSearcher(sqlMap,searcherJson);
        SqlUtil.addfilter(sqlMap,filterJson);
        SqlUtil.addSorter(sqlMap,sorterJson);

    }


    public static void addSearcher(HashMap sqlMap, JSONObject searcherJson) throws Exception {
        HashMap searchMap = new HashMap();
         if(searcherJson!=null){
             Set<String> keySet = searcherJson.keySet();
             Iterator keySetIterator = keySet.iterator();
             StringBuffer sql = new StringBuffer();
             while (keySetIterator.hasNext()) {
                 String searcherKey = (String) keySetIterator.next();
                 Object searcherVal = searcherJson.get(searcherKey);;
                 searchMap.put(searcherKey,"'%"+searcherVal+"%'");
             }
         }
        sqlMap.put("searcher",searchMap);
    }

    public static void addfilter( HashMap sqlMap,JSONObject filterJson) throws Exception {
        HashMap filter = new HashMap();
        if(filterJson!=null){
            Set<String> keySet = filterJson.keySet();
            Iterator keySetIterator = keySet.iterator();
            while (keySetIterator.hasNext()) {
                String filterKey = (String) keySetIterator.next();
                JSONArray valArray = filterJson.getJSONArray(filterKey);
                if(valArray!=null ){
                    if(valArray.size()==1){
                        filter.put(filterKey,"('" +valArray.get(0)+"')");
                    }else{
                        StringBuffer str = new StringBuffer();
                        str.append("(");
                        for(int i=0;i<valArray.size();i++){
                            if(i!=valArray.size()-1){
                                str.append("'" +valArray.get(i)+"'," );
                            }else{
                                str.append( "'"+valArray.get(i)+"'" );
                            }
                        }
                        str.append(")");
                        filter.put(filterKey,str);
                    }
                }
            }
        }
        sqlMap.put("filter",filter);
    }

    public static void addSorter(HashMap sqlMap ,JSONObject sorterJson) throws Exception {
        String sorterKey = null;
        String sorterMode= null;
        HashMap sorter = new HashMap();
        if(sorterJson!=null){
            Set<String> keySet = sorterJson.keySet();
            Iterator keySetIterator = keySet.iterator();
            while (keySetIterator.hasNext()) {
                sorterKey = (String) keySetIterator.next();
                sorterMode = (String) sorterJson.get(sorterKey);
            }
        }
        String orderStr = null;
        if(sorterKey!=null && !sorterKey.equals("")){
            if(sorterKey.indexOf("TimeLong")>-1){
                sorterKey = sorterKey.substring(0,sorterKey.length()-4);
            }
            orderStr = StringUtil.humpToLine(sorterKey)+" "+sorterMode.substring(0,sorterMode.indexOf("end"));
        }
        sorter.put("orderStr",orderStr);
        sqlMap.put("sorter",sorter);
    }
}
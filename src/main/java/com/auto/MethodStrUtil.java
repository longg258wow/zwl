package com.auto;

import java.util.ArrayList;
import java.util.List;

public class MethodStrUtil {

    public static  List<String> getMapperMethod(String entityName) {
        List methodStrList = new ArrayList();
        String selectEntity = "List<" + entityName + "> select" + entityName + "(@Param(\"sqlMap\") HashMap sqlMap)";
        methodStrList.add(selectEntity);
        return  methodStrList;
    }
}

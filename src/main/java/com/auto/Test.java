package com.auto;

import com.util.StringUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    private static final String SQL = "SELECT * FROM ";// 数据库操作
    private static final String tableName = "en";


    public static void main(String[] args) throws Exception {
        //生成实体类
        String projectPath = "C:/hf/workspace/zwl/src/main/java";
        String basePath = "com/hf";
        String importClassPath = "com/auto/importClass.txt";
        String classAnnoPath = "com/auto/classAnno.txt";
        BuildUtil.setPathMap(projectPath ,basePath,importClassPath,classAnnoPath);
        BuildUtil.build(tableName ,BuildUtil.PATH_ENTITY,BuildUtil.PATH_MAPPER);





 //       BuildUtil.build(projectPagh,bathPath,"mapper",entityName,tableName, fieldList);
//        BuildUtil.build(bathPath,"service",entityName,tableName);
//        BuildUtil.build(bathPath,"serviceImpl",entityName,tableName);
//        BuildUtil.build(bathPath,"controller",entityName,tableName);


    }




}

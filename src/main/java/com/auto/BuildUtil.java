package com.auto;

import com.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Setter
@Getter
public class BuildUtil {

    public static final String driver = "com.mysql.cj.jdbc.Driver";
    public static final String url = "jdbc:mysql://localhost:3306/en?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8";
    public static final String userName = "root";
    public static final String password = "root";


//    public static final String SIGN_SEMICOLON_END = ";";
//    public static final String SIGN_BRACE_BEG = "{";
//    public static final String SIGN_BRACE_END = "}";
//    public static final String SIGN_NEXT_LINE = "\r\n";
//    public static final String SIGN_TAB = "   ";

    private static   String bathPath = null ;
    private static   String entityPath = null;
    private static   String mapperPath =  null;
    private static   String servicePath =  null;
    private static   String serviceImpPath =  null;
    private static   String controllerPath =  null;
    public static   String projectPath =  null;
    public static   String importClassPath =  null;
    public static   String classAnnoPath =  null;

    private static HashMap<String,String> PATH_MAP =  new HashMap();

    public static   String PATH_ENTITY =  "entity";
    public static   String PATH_MAPPER =  "mapper";
    public static   String PATH_SERVICE=  "service";
    public static   String PATH_SERVICEIMPL =  "serviceImp";
    public static   String PATH_CONTROLLER =  "controller";
    public static   String PATH_PROJECT =  "project";
    public static   String PATH_IMPORT_CLASS =  "importClass";
    public static   String PATH_CLASS_ANNO =  "classAnno";


    public static void   setPathMap(String pPath,String bPath,String iClassPath,String caPath){
        projectPath = pPath;
        bathPath = bPath;
        importClassPath = iClassPath;
        classAnnoPath = caPath;
        entityPath = bathPath + "/entity";
        mapperPath = bathPath + "/mapper";
        servicePath = bathPath + "/service";
        serviceImpPath = bathPath + "/serviceImp";
        controllerPath = bathPath + "/controller";

        System.out.println("projectPath = "+projectPath);

        PATH_MAP.put(PATH_ENTITY,entityPath);
        PATH_MAP.put(PATH_MAPPER,mapperPath);
        PATH_MAP.put(PATH_SERVICE,servicePath);
        PATH_MAP.put(PATH_SERVICEIMPL,serviceImpPath);
        PATH_MAP.put(PATH_CONTROLLER,controllerPath);
        PATH_MAP.put(PATH_PROJECT,projectPath);
        PATH_MAP.put(PATH_IMPORT_CLASS,importClassPath);
        PATH_MAP.put(PATH_CLASS_ANNO,classAnnoPath);
    }


    public static void build(String tableName,String ... createTypeList)throws Exception{

        String entityName = StringUtil.lineToHump(tableName, true);
        List<DBField> fieldList = getFieldList(tableName);
        for(String createType:createTypeList){
            System.out.println("开始生成");
            ClassUtil.createClass(createType,PATH_MAP, entityName,fieldList);
        }
    }




//    public static String getPackageStr(String basePath,String type){
//        String packagePath = basePath + "/"+ type;
//        String packageStr = "package "+ packagePath.replaceAll("/",".");
//        return packageStr;
//    }
//
//    public static void createController(String entityName)throws Exception{
//        StringBuffer stringBuffer = new StringBuffer();
//
//        String packageStr = PackageStrUtil.getPackageStr(controllerPath);
//        stringBuffer.append(packageStr +SIGN_SEMICOLON_END +  SIGN_NEXT_LINE);
//
//        List<String> importStrList = ImportStrUtil.getControllerStrList(entityName);
//        for (String importStr : importStrList) {
//            stringBuffer.append(importStr + SIGN_SEMICOLON_END+SIGN_NEXT_LINE);
//        }
//
//        List<String> annoStrList = AnnoStrUtil.getControllerAnnoList(tableName);
//        for (String annoStr : annoStrList) {
//            stringBuffer.append(annoStr + SIGN_NEXT_LINE);
//        }
//
//        String classStr = ClassStrUtil.getControllerClassStr(entityName);
//        stringBuffer.append(classStr + SIGN_BRACE_BEG + SIGN_NEXT_LINE);
//
//
//
//
//        stringBuffer.append(SIGN_BRACE_END);
//        String filePath = projectPagh + "/" + controllerPath + "/" + entityName + "Controller.java";
//        writeFile(filePath,stringBuffer.toString());
//
//    }
//    public static void createServiceImpl(String entityName)throws Exception{
//        StringBuffer stringBuffer = new StringBuffer();
//
//        String packageStr = PackageStrUtil.getPackageStr(serviceImpPath);
//        stringBuffer.append(packageStr +SIGN_SEMICOLON_END +  SIGN_NEXT_LINE);
//
//        List<String> importStrList = ImportStrUtil.getServiceImplStrList(entityName);
//        for (String importStr : importStrList) {
//            stringBuffer.append(importStr + SIGN_SEMICOLON_END+SIGN_NEXT_LINE);
//        }
//
//        List<String> annoStrList = AnnoStrUtil.getServiceImplAnnoList();
//        for (String annoStr : annoStrList) {
//            stringBuffer.append(annoStr + SIGN_NEXT_LINE);
//        }
//
//        String classStr = ClassStrUtil.getServiceImplClassStr(entityName);
//        stringBuffer.append(classStr + SIGN_BRACE_BEG + SIGN_NEXT_LINE);
//
//        stringBuffer.append(SIGN_BRACE_END);
//        String filePath = projectPagh + "/" + serviceImpPath + "/" + entityName + "ServiceImpl.java";
//        writeFile(filePath,stringBuffer.toString());
//    }
//
//    public static void createService(String entityName)throws Exception{
//        StringBuffer stringBuffer = new StringBuffer();
//
//        String packageStr = PackageStrUtil.getPackageStr(servicePath);
//        stringBuffer.append(packageStr +SIGN_SEMICOLON_END +  SIGN_NEXT_LINE);
//
//        List<String> importStrList = ImportStrUtil.getMapperServiceStrList(entityName);
//        for (String importStr : importStrList) {
//            stringBuffer.append(importStr + SIGN_SEMICOLON_END+SIGN_NEXT_LINE);
//        }
//
//        List<String> annoStrList = AnnoStrUtil.getServiceAnnoList();
//        for (String annoStr : annoStrList) {
//            stringBuffer.append(annoStr + SIGN_NEXT_LINE);
//        }
//
//        String classStr = ClassStrUtil.getServiceClassStr(entityName);
//        stringBuffer.append(classStr + SIGN_BRACE_BEG + SIGN_NEXT_LINE);
//
//        stringBuffer.append(SIGN_BRACE_END);
//        String filePath = projectPagh + "/" + servicePath + "/" + entityName + "IService.java";
//        writeFile(filePath,stringBuffer.toString());
//    }
//
//    public static void createMapper(String entityName) throws Exception {
//        StringBuffer stringBuffer = new StringBuffer();
//
//        String packageStr = PackageStrUtil.getPackageStr(mapperPath);
//        stringBuffer.append(packageStr +SIGN_SEMICOLON_END +  SIGN_NEXT_LINE);
//
//        List<String> importStrList = ImportStrUtil.getMapperImportStrList(entityName);
//        for (String importStr : importStrList) {
//            stringBuffer.append(importStr + SIGN_SEMICOLON_END+SIGN_NEXT_LINE);
//        }
//
//        List<String> annoStrList = AnnoStrUtil.getMapperAnnoList();
//        for (String annoStr : annoStrList) {
//            stringBuffer.append(annoStr + SIGN_NEXT_LINE);
//        }
//
//        String classStr = ClassStrUtil.getMapperClassStr(entityName);
//        stringBuffer.append(classStr + SIGN_BRACE_BEG + SIGN_NEXT_LINE);
//
//        List<String> methodStrList = MethodStrUtil.getMapperMethod(entityName);
//        for (String methodStr : methodStrList) {
//            stringBuffer.append( SIGN_TAB + methodStr + SIGN_SEMICOLON_END+SIGN_NEXT_LINE);
//        }
//        stringBuffer.append(SIGN_BRACE_END);
//
//        String filePath = projectPagh + "/" + mapperPath + "/" + entityName + "Mapper.java";
//        writeFile(filePath,stringBuffer.toString());
//    }
//
//

//
//


    public static List<DBField> getFieldList(String tableName) throws Exception {
       String sql = "SELECT * FROM  ";
        List<DBField> dbFieldList = new ArrayList();

        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, userName, password);
        PreparedStatement pStemt = null;
        String tableSql = sql + tableName;

        pStemt = conn.prepareStatement(tableSql);
        ResultSetMetaData rsmd = pStemt.getMetaData();
        int size = rsmd.getColumnCount();
        for (int i = 0; i < size; i++) {
            System.out.println(rsmd.getColumnName(i + 1) + ":" + rsmd.getColumnType(i + 1));
            String fileType = "";
            if (rsmd.getColumnType(i + 1) == Types.VARCHAR) {
                fileType = "String";
            } else if (rsmd.getColumnType(i + 1) == Types.INTEGER) {
                fileType = "Integer";
            } else if (rsmd.getColumnType(i + 1) == Types.TIMESTAMP) {
                fileType = "Date";
            } else if (rsmd.getColumnType(i + 1) == Types.DECIMAL) {
                fileType = "Double";
            } else if (rsmd.getColumnType(i + 1) == Types.LONGVARCHAR) {
                fileType = "String";
            }
            DBField dbField = new DBField();
            dbField.setFieldName(StringUtil.lineToHump(rsmd.getColumnName(i + 1), false));
            dbField.setFieldType(fileType);
            dbFieldList.add(dbField);
        }

        ResultSet rs = pStemt.executeQuery("show full columns from " + tableName);
        int index = 0;
        while (rs.next()) {
            DBField dbField = dbFieldList.get(index);
            dbField.setFieldComment(rs.getString("Comment"));
            index++;

        }
        pStemt.close();
        conn.close();
        return dbFieldList;
    }
}

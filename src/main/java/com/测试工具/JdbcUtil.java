package com.测试工具;

import com.auto.*;
import com.util.StringUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtil {
    private static final String SQL = "SELECT * FROM ";// 数据库操作
    private static final String tableName = "en";

    private static final String projectPagh = "C:/hf/workspace/zwl/src/main/java";


    private static final String entityPath = "com/hf/entity";
    private static final String mapperPath = "com/hf/mapper";
    private static final String servicePath = "com/hf/service";
    private static final String serviceImpPath = "com/hf/serviceImpl";
    private static final String controllerPath = "com/hf/controller";



    public static final String SIGN_SEMICOLON_END = ";";
    public static final String SIGN_BRACE_BEG = "{";
    public static final String SIGN_BRACE_END = "}";
    public static final String SIGN_NEXT_LINE = "\r\n";
    public static final String SIGN_TAB = "   ";

//    public static void main(String[] args) throws Exception {
//        //生成实体类
//
//        List<DBField> fieldList = jdbcTest(1);
//        String entityName = StringUtil.lineToHump(tableName, true);
//
//        createEntity(entityName, fieldList);
//        createMapper(entityName);
//        createService(entityName);
//        createServiceImpl(entityName);
//        createController(entityName);
//
//    }
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
//        String classStr = ClassStrUtil.getClassStr(entityName);
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
//        String classStr = ClassStrUtil.getClassStr(entityName);
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
//        String classStr = ClassStrUtil.getClassStr(entityName);
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
//        String classStr = ClassStrUtil.getClassStr(entityName);
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
//    public static void createEntity(String entityName, List<DBField> fieldList) throws Exception {
//        StringBuffer stringBuffer = new StringBuffer();
//
//        String packageStr = PackageStrUtil.getPackageStr(entityPath) ;
//        stringBuffer.append(packageStr+SIGN_SEMICOLON_END + SIGN_NEXT_LINE);
//
//        List<String> importStrList = ImportStrUtil.getEntityImportStrList();
//        for (String str : importStrList) {
//            stringBuffer.append(str + SIGN_SEMICOLON_END + SIGN_NEXT_LINE);
//        }
//
//        List<String> annoStrList = AnnoStrUtil.getEntityAnnoList();
//        for (String str : annoStrList) {
//            stringBuffer.append(str + SIGN_NEXT_LINE);
//        }
//
//        String classStr = ClassStrUtil.getEntityClassStr(entityName);
//        stringBuffer.append(classStr + SIGN_BRACE_BEG + SIGN_NEXT_LINE);
//
//        for (DBField dbField : fieldList) {
//            stringBuffer.append(SIGN_TAB + "@ApiModelProperty(\"" + dbField.getFieldComment() + "\")" + SIGN_NEXT_LINE);
//            stringBuffer.append(SIGN_TAB + "private " + dbField.getFieldType() + " " + dbField.getFieldName() + SIGN_SEMICOLON_END + SIGN_NEXT_LINE);
//        }
//        stringBuffer.append(SIGN_BRACE_END);
//
//        String filePath = projectPagh + "/" + entityPath + "/" + entityName + ".java";
//        writeFile(filePath, stringBuffer.toString());
//    }
//
//
//    public static void writeFile(String filePath, String str) throws Exception {
//        File file = new File(filePath);
//        BufferedWriter out = new BufferedWriter(new FileWriter(file));
//        out.write(str);
//        out.close();
//    }
//
//    public static List<DBField> jdbcTest(Integer threadNo) throws Exception {
//        List<DBField> dbFieldList = new ArrayList();
//        String driver = "com.mysql.cj.jdbc.Driver";
//        String url = "jdbc:mysql://localhost:3306/en?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8";
//        String userName = "root";
//        String password = "root";
//
//        Class.forName(driver);
//        Connection conn = DriverManager.getConnection(url, userName, password);
//        PreparedStatement pStemt = null;
//        String tableSql = SQL + tableName;
//
//        pStemt = conn.prepareStatement(tableSql);
//        ResultSetMetaData rsmd = pStemt.getMetaData();
//        int size = rsmd.getColumnCount();
//        for (int i = 0; i < size; i++) {
//            System.out.println(rsmd.getColumnName(i + 1) + ":" + rsmd.getColumnType(i + 1));
//            String fileType = "";
//            if (rsmd.getColumnType(i + 1) == Types.VARCHAR) {
//                fileType = "String";
//            } else if (rsmd.getColumnType(i + 1) == Types.INTEGER) {
//                fileType = "Integer";
//            } else if (rsmd.getColumnType(i + 1) == Types.TIMESTAMP) {
//                fileType = "Date";
//            } else if (rsmd.getColumnType(i + 1) == Types.DECIMAL) {
//                fileType = "Double";
//            } else if (rsmd.getColumnType(i + 1) == Types.LONGVARCHAR) {
//                fileType = "String";
//            }
//            DBField dbField = new DBField();
//            dbField.setFieldName(StringUtil.lineToHump(rsmd.getColumnName(i + 1), false));
//            dbField.setFieldType(fileType);
//            dbFieldList.add(dbField);
//        }
//
//        ResultSet rs = pStemt.executeQuery("show full columns from " + tableName);
//        int index = 0;
//        while (rs.next()) {
//            DBField dbField = dbFieldList.get(index);
//            dbField.setFieldComment(rs.getString("Comment"));
//            index++;
//
//        }
//        pStemt.close();
//        conn.close();
//        return dbFieldList;
//    }

}

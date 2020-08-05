package com.auto;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassUtil {

    public static final String SIGN_SEMICOLON_END = ";";
    public static final String SIGN_BRACE_BEG = "{";
    public static final String SIGN_BRACE_END = "}";
    public static final String SIGN_NEXT_LINE = "\r\n";
    public static final String SIGN_TAB = "   ";


    public static List<String> getImportStrList( String filePath, String createType) {
        List<String> importStrList = new ArrayList<>();
        File file = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;

            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                if (tempString.indexOf(createType) > -1) {
                    importStrList.add(tempString.substring(createType.length() + 1));
                }
            }
            reader.close();
            return importStrList;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return null;
    }


    public static void createClass(String createType, HashMap<String, String> pathMap, String entityName, List<DBField> fieldList) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        //packageStr
        String packageStr = PackageStrUtil.getPackageStr(pathMap.get(createType));
        stringBuffer.append(packageStr + SIGN_SEMICOLON_END + SIGN_NEXT_LINE);

        //importStr
        String importStrFilePath = pathMap.get(BuildUtil.PATH_PROJECT) + "/" + pathMap.get(BuildUtil.PATH_IMPORT_CLASS);
        List<String> importStrList = getImportStrList(importStrFilePath, createType);
        for (String str : importStrList) {
            stringBuffer.append("import "+str + SIGN_SEMICOLON_END + SIGN_NEXT_LINE);
        }
        if(createType.equals(BuildUtil.PATH_MAPPER)){
            stringBuffer.append("import "+ pathMap.get(BuildUtil.PATH_ENTITY).replaceAll("/",".") + "."+ entityName+ SIGN_SEMICOLON_END + SIGN_NEXT_LINE);
        }

        //classAnno
        String classAnoStrFilePath = pathMap.get(BuildUtil.PATH_PROJECT) + "/" + pathMap.get(BuildUtil.PATH_CLASS_ANNO);
        List<String> classAnnoStrList = getImportStrList(classAnoStrFilePath, createType);
        for (String str : classAnnoStrList) {
            stringBuffer.append(str  + SIGN_NEXT_LINE);
        }

        //classStr
        String classStr = ClassStrUtil.getClassStr(entityName,createType);
        stringBuffer.append(classStr + SIGN_BRACE_BEG + SIGN_NEXT_LINE);

        //fieldStr
        if(createType.equals(BuildUtil.PATH_ENTITY)){
            for (DBField dbField : fieldList) {
                stringBuffer.append(SIGN_TAB + "@ApiModelProperty(\"" + dbField.getFieldComment() + "\")" + SIGN_NEXT_LINE);
                stringBuffer.append(SIGN_TAB + "private " + dbField.getFieldType() + " " + dbField.getFieldName() + SIGN_SEMICOLON_END + SIGN_NEXT_LINE);
            }
        }

        //methodStr

        //end
        stringBuffer.append(  SIGN_BRACE_END );

        String fileName = "";
        if(createType.equals(BuildUtil.PATH_ENTITY)){
            fileName = ".java";
        }else if(createType.equals(BuildUtil.PATH_MAPPER)){
            fileName = "Mapper.java";
        }
        String filePath = pathMap.get(BuildUtil.PATH_PROJECT) + "/" + pathMap.get(createType) + "/" + entityName + fileName;
        writeFile(filePath, stringBuffer.toString());


//        if (createType.equals(BuildUtil.PATH_ENTITY)) {
//            for (DBField dbField : fieldList) {
//                stringBuffer.append(SIGN_TAB + "@ApiModelProperty(\"" + dbField.getFieldComment() + "\")" + SIGN_NEXT_LINE);
//                stringBuffer.append(SIGN_TAB + "private " + dbField.getFieldType() + " " + dbField.getFieldName() + SIGN_SEMICOLON_END + SIGN_NEXT_LINE);
//            }
//            stringBuffer.append(SIGN_BRACE_END);
//
//            String filePath = pathMap.get(BuildUtil.PATH_PROJECT) + "/" + pathMap.get(BuildUtil.PATH_ENTITY) + "/" + entityName + ".java";
//            writeFile(filePath, stringBuffer.toString());
//        } else if (createType.equals(BuildUtil.PATH_MAPPER)) {
//
//
////            List<String> annoStrList = AnnoStrUtil.getMapperAnnoList();
////            for (String annoStr : annoStrList) {
////                stringBuffer.append(annoStr + SIGN_NEXT_LINE);
////            }
////
////            String classStr = ClassStrUtil.getMapperClassStr(entityName);
////            stringBuffer.append(classStr + SIGN_BRACE_BEG + SIGN_NEXT_LINE);
////
////            List<String> methodStrList = MethodStrUtil.getMapperMethod(entityName);
////            for (String methodStr : methodStrList) {
////                stringBuffer.append(SIGN_TAB + methodStr + SIGN_SEMICOLON_END + SIGN_NEXT_LINE);
////            }
////            stringBuffer.append(SIGN_BRACE_END);
////
//            String filePath = pathMap.get(BuildUtil.PATH_PROJECT) + "/" + pathMap.get(BuildUtil.PATH_MAPPER) + "/" + entityName + "Mapper.java";
//            writeFile(filePath, stringBuffer.toString());
//        }

    }


    private static void writeFile(String filePath, String str) throws Exception {
        File file = new File(filePath);
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(str);
        out.close();
    }


}

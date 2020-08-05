package com.auto;

public class PackageStrUtil {
    public static String getPackageStr(String packagePath){
        String packageStr = "package "+ packagePath.replaceAll("/",".");
        return packageStr;
    }
}

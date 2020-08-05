package com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    /** 下划线转驼峰 */
    public static String lineToHump(String str,boolean isFirstChatToUpper) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        if(isFirstChatToUpper){
            String first = sb.toString().substring(0, 1).toUpperCase();
            String after = sb.toString().substring(1);
            return first+after;
        }
        return sb.toString();
    }



    /** 驼峰转下划线,效率比上面高 */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public boolean isNotEmpty(String str){
        if(str!=null && !str.equals("")){
            return true;
        }
        return false;
    }
}

package com.util;



import java.io.*;
import java.util.HashMap;



public class FileUtil {

    /**
     * 递归删除目录
     *
     * @param path
     */
    public static void deleteAllFilesOfDir(File path) {
        if (null != path) {
            if (!path.exists())
                return;
            if (path.isFile()) {
                boolean result = path.delete();
                int tryCount = 0;
                while (!result && tryCount++ < 10) {
                    System.gc(); // 回收资源
                    result = path.delete();
                }
            }
            File[] files = path.listFiles();
            if (null != files) {
                for (int i = 0; i < files.length; i++) {
                    deleteAllFilesOfDir(files[i]);
                }
            }
            path.delete();
        }
    }

    public static void updateSingleLine(String path, String oldLine, String newLine) {
        BufferedReader br = null;
        String line = null;
        StringBuffer bufAll = new StringBuffer(); //保存修改过后的所有内容，不断增加
        try {
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                //修改内容核心代码
                if (line.indexOf(oldLine) > -1) {
                    bufAll.append(newLine + "\n");
                } else {
                    bufAll.append(line + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                }
            }
        }

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path));
            bw.write(bufAll.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    bw = null;
                }
            }
        }
    }

    public static void updateMultiLine(String path, HashMap<String ,String> dataMap) {
        BufferedReader br = null;
        String line = null;
        StringBuffer bufAll = new StringBuffer(); //保存修改过后的所有内容，不断增加
        try {
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                //修改内容核心代码
                for (String key  : dataMap.keySet()) {
                    if(line.indexOf(key)>-1){
                        line = dataMap.get(key);
                        break;
                    }
                }
                bufAll.append(line+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                }
            }
        }

        writeStrToFile(path,bufAll.toString());
    }

    public static String readStrFromFile(String path){
        BufferedReader br = null;
        String line = null;
        StringBuffer bufAll = new StringBuffer(); //保存修改过后的所有内容，不断增加
        try {
            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                //修改内容核心代码
                bufAll.append(line+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                }
            }
        }
        return bufAll.toString();
    }

    public static void writeStrToFile(String path,String str){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path));
            bw.write(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    bw = null;
                }
            }
        }
    }


}

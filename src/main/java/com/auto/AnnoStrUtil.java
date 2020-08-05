package com.auto;

import java.util.ArrayList;
import java.util.List;

public class AnnoStrUtil {



    public static  List<String> getControllerAnnoList(String tableName){
        List<String> annoStrList = new ArrayList<>();
        annoStrList.add("@RestController");
        annoStrList.add("@RequestMapping(\"/"+tableName+"\")");
        return annoStrList;
    }

    public static  List<String> getServiceImplAnnoList(){
        List<String> annoStrList = new ArrayList<>();
        annoStrList.add("@Service");
        return annoStrList;
    }

    public static  List<String> getServiceAnnoList(){
        List<String> annoStrList = new ArrayList<>();
        annoStrList.add("@Service");
        return annoStrList;
    }

    public static  List<String> getMapperAnnoList(){
        List<String> annoStrList = new ArrayList<>();
        annoStrList.add("@Repository");
        annoStrList.add("@Mapper");
        return annoStrList;
    }

    public static  List<String> getEntityAnnoList(){
        List<String> annoStrList = new ArrayList<>();
        annoStrList.add("@Setter");
        annoStrList.add("@Getter");
        return annoStrList;
    }
}

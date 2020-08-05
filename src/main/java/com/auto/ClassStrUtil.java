package com.auto;

public class ClassStrUtil {

    public static String getClassStr(String entityName,String createType){
        if(createType.equals(BuildUtil.PATH_ENTITY)){
            return "public class "+entityName;
        }else if(createType.equals(BuildUtil.PATH_MAPPER)){
            return "public interface " + entityName + "Mapper extends BaseMapper<" + entityName + ">";
        }else if(createType.equals(BuildUtil.PATH_SERVICE)){
            return "public interface " + entityName + "IService extends IService<" + entityName + ">";
        }else if(createType.equals(BuildUtil.PATH_SERVICEIMPL)){
            return "public class " + entityName + "ServiceImpl extends ServiceImpl<" + entityName + "Mapper," + entityName + "> implements " + entityName + "IService";
        }else if(createType.equals(BuildUtil.PATH_SERVICE)){
            return "public class "+ entityName+"Controller";
        }
        return null;
    }
}

package com.auto;

import java.util.ArrayList;
import java.util.List;

public class ImportStrUtil {

    public static List getControllerStrList(String entityName){
        List<String> importStrList = new ArrayList<>();
        importStrList.add("import io.swagger.annotations.ApiOperation");
        importStrList.add("import org.springframework.beans.factory.annotation.Autowired");
        importStrList.add("import org.springframework.web.bind.annotation.*");
        importStrList.add("import java.util.HashMap");
        importStrList.add("import java.util.List");
        importStrList.add("import com.hf.entity."+entityName);
        importStrList.add("import com.hf.mapper."+entityName+"Mapper");
        importStrList.add("import com.hf.service."+entityName+"IService");
        return importStrList;
    }


    public static List getServiceImplStrList(String entityName){
        List<String> importStrList = new ArrayList<>();
        importStrList.add("import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper");
        importStrList.add("import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl");
        importStrList.add("import org.springframework.beans.factory.annotation.Autowired");
        importStrList.add("import org.springframework.stereotype.Service");
        importStrList.add("import java.util.HashMap");
        importStrList.add("import java.util.List");
        importStrList.add("import com.hf.entity."+entityName);
        importStrList.add("import com.hf.mapper."+entityName+"Mapper");
        importStrList.add("import com.hf.service."+entityName+"IService");
        return importStrList;
    }


    public static List getMapperServiceStrList(String entityName){
        List<String> importStrList = new ArrayList<>();
        importStrList.add("import com.baomidou.mybatisplus.extension.service.IService");
        importStrList.add("import org.springframework.stereotype.Service");
        importStrList.add("import java.util.HashMap");
        importStrList.add("import java.util.List");
        importStrList.add("import com.hf.entity."+entityName);
        return importStrList;
    }

    public static List getMapperImportStrList(String entityName){
        List<String> importStrList = new ArrayList<>();
        importStrList.add("import org.apache.ibatis.annotations.Mapper");
        importStrList.add("import org.apache.ibatis.annotations.Param");
        importStrList.add("import org.springframework.stereotype.Repository");
        importStrList.add("import com.baomidou.mybatisplus.core.mapper.BaseMapper");
        importStrList.add("import java.util.HashMap");
        importStrList.add("import java.util.List");
        importStrList.add("import com.hf.entity."+entityName);
        return importStrList;
    }

    public static List getEntityImportStrList(){
        List<String> importStrList = new ArrayList<>();
        importStrList.add("import lombok.Getter");
        importStrList.add("import lombok.Setter");
        importStrList.add("import io.swagger.annotations.ApiModelProperty");
        importStrList.add("import com.baomidou.mybatisplus.annotation.TableId");
        return importStrList;

    }



}

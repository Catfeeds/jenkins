package com.hfocean.common.util;

import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;

import java.util.Collection;
import java.util.Objects;

/**
* 判空验证工具
* @author Leslie.Lam
* @create 2017-06-22 9:47
**/
public class VerdictUtil {

    public static boolean gtZero(Integer integer){
        return null!=integer&&integer>0;
    }

    public static boolean gtOrEtoZero(Integer integer){
        return null!=integer&&integer>=0;
    }

    public static boolean isNotBlank(String str){
        return null!=str&&str.trim().length()>0;
    }

    public static boolean isBlank(String str){
        return null==str||str.trim().length()==0;
    }

    public static void assertNotNull(Object object){
        if(null==object) throw new BaseBusinessException(BaseBusinessError.NOT_FOUND);
    }

    public static void assertNotBlank(String str){
        if(null==str) throw new BaseBusinessException(BaseBusinessError.PARAMETER_ERROR);
    }

    public static void validArray(Objects[] arrays){
        if(null==arrays||arrays.length==0) throw new BaseBusinessException(BaseBusinessError.PARAMETER_ERROR);
    }

    public static boolean isValidArray(Objects[] arrays){
        return null!=arrays &&arrays.length>0;
    }

    public static boolean isValidArray(String[] arrays){
        return null!=arrays &&arrays.length>0;
    }

    public static boolean isValidArray(Integer[] arrays){
        return null!=arrays &&arrays.length>0;
    }

    public static boolean isValidCollection(Collection collection){
        return null!=collection &&collection.size()>0;
    }
}

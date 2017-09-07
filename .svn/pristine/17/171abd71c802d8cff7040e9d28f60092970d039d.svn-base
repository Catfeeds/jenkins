package com.hfocean.uavportal.core.airapply.enumerate;

import java.util.Objects;

/**
 * 空域申请表字段类型枚举
 *
 * @author Leslie.Lam
 * @create 2017-06-20 16:23
 **/
public enum AirApplyEnum {

    审核中(0,"status"),审核通过(1,"status"),未通过审核(2,"status"),撤销申请(3,"status"),已关闭(7,"status");

    private Integer code;

    private String field;

    public Integer code() {
        return code;
    }

    public static String name(Integer code,String field){
        AirApplyEnum[] values = values();
        for (AirApplyEnum value:values){
            if(Objects.equals(field,value.field())){
                if(Objects.equals(code,value.code()))return value.name();
            }
        }
        return null;
    }

    AirApplyEnum(Integer code, String field) {
        this.code = code;
        this.field = field;
    }

    public String field() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}

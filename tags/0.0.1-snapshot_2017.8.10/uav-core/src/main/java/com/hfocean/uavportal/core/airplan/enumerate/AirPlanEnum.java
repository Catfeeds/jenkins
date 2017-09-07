package com.hfocean.uavportal.core.airplan.enumerate;

import java.util.Objects;

/**
 * 空域申请表字段类型枚举
 *
 * @author Leslie.Lam
 * @create 2017-06-20 16:23
 **/
public enum AirPlanEnum {

    管制飞行计划(0,"type"),一类飞行计划(1,"type"),二类飞行计划(2,"type"),

    审核中(0,"status"),审核通过(1,"status"),未通过审核(2,"status"),执行中(3,"status"),已完成(4,"status"),计划超时(5,"status"),撤销申请(6,"status"),已关闭(7,"status"),

    申请动态(new Integer[]{0,1,2}),执行动态(new Integer[]{1,3,4,5});

    private Integer code;

    private String field;

    private Integer[] status;

    public Integer code() {
        return code;
    }

    public static String name(Integer code,String field){
        AirPlanEnum[] values = values();
        for (AirPlanEnum value:values){
            if(Objects.equals(field,value.field())){
                if(Objects.equals(code,value.code()))return value.name();
            }
        }
        return null;
    }

    AirPlanEnum(Integer code, String field) {
        this.code = code;
        this.field = field;
    }

    AirPlanEnum(Integer[] status) {
        this.status = status;
    }

    public Integer[] status() {
        return status;
    }

    public String field() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}

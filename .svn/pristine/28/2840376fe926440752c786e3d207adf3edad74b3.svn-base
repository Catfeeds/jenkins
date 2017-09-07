package com.hfocean.uavportal.core.airplan.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
* 
* @author Leslie.Lam
* @create 2017-06-27 17:43
**/
@Entity
@Table(name = "tb_air_plan_temp")
public class AirPlanTemps {

    @Id
    private String id;//:模板id

    private Integer type;//:报告类型 0为管制飞行计划，1为一类飞行计划，2为二类飞行计划

    private String name;//:模板名称

    private String depict;//:模板描述

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "user_id")
    private String userId;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

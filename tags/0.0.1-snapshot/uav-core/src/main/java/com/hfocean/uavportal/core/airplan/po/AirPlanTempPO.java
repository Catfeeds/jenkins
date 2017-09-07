package com.hfocean.uavportal.core.airplan.po;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
@Entity
@Table(name="tb_air_plan_temp")
public class AirPlanTempPO  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "objectIdGenerator")
    private String id;

    private String name;//:模板名称

    private String depict;//:模板描述

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 飞行计划申请类型，0为管制飞行计划，1为一类飞行计划，2为二类飞行计划
     */
    private Integer type;

    /**
     * 使用机型信息，以json存储，格式为{"name":"直升机","number":3}
     */
    private String planes;

    /**
     * 用户类型：1个人，2企业
     */
    @Column(name = "user_type")
    private Integer userType;

    /**
     * 个人id或企业id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 计划类型：0其他，1技能培训，2产品试飞
     */
    @Column(name = "plan_type")
    private String planType;

    /**
     * 起降场信息
     */
    @Column(name = "landing_info")
    private String landingInfo;

    /**
     * 空域信息
     */
    @Column(name = "air_info")
    private String airInfo;

    /**
     * 联系人信息
     */
    @Column(name = "contact_info")
    private String contactInfo;

    /**
     * 气象条件
     */
    @Column(name = "weather_condition")
    private String weatherCondition;

    /**
     * 备注
     */
    private String remark;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }

    public String getPlanes() {
        return planes;
    }

    public void setPlanes(String planes) {
        this.planes = planes;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLandingInfo() {
        return landingInfo;
    }

    public void setLandingInfo(String landingInfo) {
        this.landingInfo = landingInfo;
    }

    public String getAirInfo() {
        return airInfo;
    }

    public void setAirInfo(String airInfo) {
        this.airInfo = airInfo;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }
}
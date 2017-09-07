package com.hfocean.uavportal.console.airplan.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
* 飞行计划列表
* @author Leslie.Lam
* @create 2017-06-24 16:20
**/
@Entity
@Table(name = "tb_air_plan")
public class AirPlans {

    @Id
    private String id;

    private String number;//计划编号

    private Integer type;//报告类型 0为管制飞行计划，1为一类飞行计划，2为二类飞行计划

    private String planes;

    @Column(name = "plan_type")
    private String planType;//计划类型

    @JsonFormat(pattern = "yyyyMMdd")
    @Column(name = "create_time")
    private Date createTime;//申请时间

    @JsonFormat(pattern = "yyyyMMdd HH:mm")
    @Column(name = "begin_time")
    private Date beginTime;//计划开始时间

    @JsonFormat(pattern = "yyyyMMdd HH:mm")
    @Column(name = "end_time")
    private Date endTime;//计划结束时间

    @JsonFormat(pattern = "yyyyMMdd HH:mm")
    @Column(name = "start_time")
    private Date startTime;//实际开始时间

    @JsonFormat(pattern = "yyyyMMdd HH:mm")
    @Column(name = "finish_time")
    private Date finishTime;//实际结束时间

    @JsonFormat(pattern = "yyyyMMdd HH:mm")
    @Column(name = "audit_time")
    private Date auditTime;//审批时间

    @Column(name = "landing_info")
    private String landingInfo;

    @Column(name = "air_info")
    private String airInfo;

    @Column(name = "contact_info")
    private String contactInfo;

    @Column(name = "weather_condition")
    private String weatherCondition;//天气情况

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;//个人姓名/单位名称

    @Column(name = "user_type")
    private Integer userType;//用户类型 1-个人 2单位

    private Integer status;//状态

    private String remark;//备注

    private String reason;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPlanes() {
        return planes;
    }

    public void setPlanes(String planes) {
        this.planes = planes;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
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

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

package com.hfocean.uavportal.core.airapply.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
* 个人空域申请列表
* @author Leslie.Lam
* @create 2017-06-21 18:23
**/
@Entity
@Table(name = "tb_air_apply")
public class MyAirApply {

    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;

    private String number;

    private String planes;

    @Column(name = "plan_type")
    private String planType;

    @Column(name = "landing_info")
    private String landingInfo;//起降场信息

    @Column(name = "air_info")
    private String airInfo;//空域信息

    @Column(name = "contact_info")
    private String contactInfo;//联系人信息

    private String remark;//备注

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "create_time")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "begin_time")
    private Date beginTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "end_time")
    private Date endTime;

    private Integer status;//状态

    private String reason;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
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

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

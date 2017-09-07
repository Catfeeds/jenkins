package com.hfocean.uavportal.console.airapply.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
* 用户空域申请列表
* @author Leslie.Lam
* @create 2017-06-22 16:11
**/
@Entity
@Table(name = "tb_air_apply")
public class AirApplys{

    @Id
    private String id;

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

    private Integer status;//状态

    @Column(name = "user_id")
    private String userId;//用户ID

    @Column(name = "user_name")
    private String userName;//个人姓名/单位名称

    @Column(name = "user_type")
    private Integer userType;//用户类型 1-个人 2单位

    @JsonFormat(pattern = "yyyyMMdd HH:mm")
    @Column(name = "begin_time")
    private Date beginTime;

    @JsonFormat(pattern = "yyyyMMdd HH:mm")
    @Column(name = "end_time")
    private Date endTime;

    @JsonFormat(pattern = "yyyyMMdd")
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "fly_rule")
    private String flyRule;

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


    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }


    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFlyRule() {
        return flyRule;
    }

    public void setFlyRule(String flyRule) {
        this.flyRule = flyRule;
    }
}

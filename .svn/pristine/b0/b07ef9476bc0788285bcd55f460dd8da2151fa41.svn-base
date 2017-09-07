package com.hfocean.uavportal.core.airapply.po;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
@Entity
@Table(name="tb_air_apply")
public class AirApplyPO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "objectIdGenerator")
    private String id;

    /**
     * 说明：该编号由系统自动生成，如2017年6月12日递交的空域使用申请编号为KY170612001,即KY表示空域申请，170612表示日期，001表示该申请的编号
     */
    private String number;

    /**
     * 计划类型：0其他，1技能培训，2产品试飞
     */
    @Column(name = "plan_type")
    private String planType;

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
     * 个人姓名或企业名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 使用机型
     */
    private String planes;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyyMMdd HH:mm")
    @Column(name = "begin_time")
    private Date beginTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyyMMdd HH:mm")
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 空域信息
     */
    @Column(name = "air_info")
    private String airInfo;

    /**
     * 联系人信息
     */
    @Column(name = "contact_info")
    private String contatInfo;

    /**
     * 起降场信息
     */
    @Column(name = "landing_info")
    private String landingInfo;

    /**
     * 飞行规则
     */
    @Column(name = "fly_rule")
    private String flyRule;

    /**
     * 备注
     */
    private String remark;

    /**
     * 申请时间
     */
    @JsonFormat(pattern = "yyyyMMdd HH:mm")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyyMMdd HH:mm")
    @Column(name = "audit_time")
    private Date auditTime;

    /**
     * 状态：0审核中，1审核通过，2未通过审核
     */
    private Integer status;

    /**
     * 审核失败原因
     */
    private String reason;

    public AirApplyPO() {
    }

    public AirApplyPO(String number) {
        this.number = number;
    }

    public AirApplyPO(String number, String userId) {
        this.number = number;
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getPlanes() {
        return planes;
    }

    public void setPlanes(String planes) {
        this.planes = planes;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getAirInfo() {
        return airInfo;
    }

    public void setAirInfo(String airInfo) {
        this.airInfo = airInfo;
    }

    public String getContatInfo() {
        return contatInfo;
    }

    public void setContatInfo(String contatInfo) {
        this.contatInfo = contatInfo;
    }

    public String getLandingInfo() {
        return landingInfo;
    }

    public void setLandingInfo(String landingInfo) {
        this.landingInfo = landingInfo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFlyRule() {
        return flyRule;
    }

    public void setFlyRule(String flyRule) {
        this.flyRule = flyRule;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
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

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
}
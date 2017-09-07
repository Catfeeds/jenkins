package com.hfocean.uavportal.core.airplan.po;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
@Entity
@Table(name="tb_air_plan")
public class AirPlanPO  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "objectIdGenerator")
    private String id;

    /**
     * 编号，该编号由系统自动生成，如2017年6月12日广州大学城一类报告空域的计划编号为170612gzA01001,即170612表示日期，GZ代表区域，A代表一类报告空域，01表示一类报告空域编号，001表示计划编号
     */
    private String number;

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
     * 个人姓名或企业名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 计划类型：0其他，1技能培训，2产品试飞
     */
    @Column(name = "plan_type")
    private String planType;

    /**
     * 申请时间
     */
    @JsonFormat(pattern = "yyyyMMdd")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 计划开始时间
     */
    @JsonFormat(pattern = "yyyyMMdd HH:mm")
    @Column(name = "begin_time")
    private Date beginTime;

    /**
     * 计划结束时间
     */
    @JsonFormat(pattern = "yyyyMMdd HH:mm")
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 实际开始时间
     */
    @JsonFormat(pattern = "yyyyMMdd HH:mm")
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 实际结束时间
     */
    @JsonFormat(pattern = "yyyyMMdd HH:mm")
    @Column(name = "finish_time")
    private Date finishTime;

    /**
     * 审批时间
     */
    @JsonFormat(pattern = "yyyyMMdd HH:mm")
    @Column(name = "audit_time")
    private Date auditTime;

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
     * 空域批文编号
     */
    @Column(name = "apply_official_no")
    private String applyOfficialNo;

    /**
     * 气象条件
     */
    @Column(name = "weather_condition")
    private String weatherCondition;

    /**
     * 备注
     */
    private String remark;

    /**
     * 计划状态：0审核中，1通过审核，2未通过审核，3执行中，4已完成
     */
    private Integer status;

    /**
     * 审核失败原因
     */
    private String reason;

    public AirPlanPO() {
    }

    public AirPlanPO(String number) {
        this.number = number;
    }

    public AirPlanPO(String number, String userId) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
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

    public String getPlanes() {
        return planes;
    }

    public void setPlanes(String planes) {
        this.planes = planes;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApplyOfficialNo() {
        return applyOfficialNo;
    }

    public void setApplyOfficialNo(String applyOfficialNo) {
        this.applyOfficialNo = applyOfficialNo;
    }
}
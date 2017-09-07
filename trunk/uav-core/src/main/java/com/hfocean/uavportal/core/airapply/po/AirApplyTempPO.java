package com.hfocean.uavportal.core.airapply.po;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 
 */
@Entity
@Table(name="tb_air_apply_temp")
public class AirApplyTempPO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "objectIdGenerator")
    private String id;

    /**
     * 计划类型：0其他，1技能培训，2产品试飞
     */
    @NotEmpty
    @Column(name = "plan_type")
    private Integer planType;

    /**
     * 用户类型：1个人，2企业
     */
    @NotEmpty
    @Column(name = "user_type")
    private Integer userType;

    /**
     * 个人id或企业id
     */
    @NotEmpty
    @Column(name = "user_id")
    private String userId;

    /**
     * 使用机型
     */
    @NotEmpty
    private String planes;

    /**
     * 空域信息
     */
    @Column(name = "air_info")
    private String airInfo;

    /**
     * 联系人信息
     */
    @Column(name = "concat_info")
    private String concatInfo;

    @Column(name = "land_info")
    private String landInfo;

    /**
     * 飞行规则
     */
    @Column(name = "fly_rule")
    private Integer flyRule;

    private String remark;

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

    public String getAirInfo() {
        return airInfo;
    }

    public void setAirInfo(String airInfo) {
        this.airInfo = airInfo;
    }

    public String getConcatInfo() {
        return concatInfo;
    }

    public void setConcatInfo(String concatInfo) {
        this.concatInfo = concatInfo;
    }

    public String getLandInfo() {
        return landInfo;
    }

    public void setLandInfo(String landInfo) {
        this.landInfo = landInfo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getFlyRule() {
        return flyRule;
    }

    public void setFlyRule(Integer flyRule) {
        this.flyRule = flyRule;
    }

    public Integer getPlanType() {
        return planType;
    }

    public void setPlanType(Integer planType) {
        this.planType = planType;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
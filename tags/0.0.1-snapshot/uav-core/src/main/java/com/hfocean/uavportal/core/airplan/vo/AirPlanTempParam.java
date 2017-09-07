package com.hfocean.uavportal.core.airplan.vo;

import com.hfocean.uavportal.core.airapply.vo.AirspaceVo;
import com.hfocean.uavportal.core.airapply.vo.LandingVo;
import com.hfocean.uavportal.core.airapply.vo.PlaneInfo;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
* 接收创建飞行计划所需参数
* @author Leslie.Lam
* @create 2017-06-24 9:56
**/
public class AirPlanTempParam {

    @NotBlank
    private String name;

    @NotBlank
    private String depict;

    @NotNull
    private Integer type;//报告类型 0为管制飞行计划，1为一类飞行计划，2为二类飞行计划

    @NotBlank
    private String planType;//计划类型

    @NotNull
    @Valid
    private List<PlaneInfo> planes;//使用机型

    @NotBlank
    private String beginTime;//开始时间

    @NotBlank
    private String endTime;//结束时间

    @Valid
    @NotNull
    private List<AirspaceVo> airInfo;//空域信息

    @Valid
    @NotNull
    private List<LandingVo> landingInfo;//起降场信息

    @NotNull
    private Map<String,Object> contactInfo;//联系人信息

    private String remark;//备注

    private String weatherCondition;//天气情况

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public List<PlaneInfo> getPlanes() {
        return planes;
    }

    public void setPlanes(List<PlaneInfo> planes) {
        this.planes = planes;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<AirspaceVo> getAirInfo() {
        return airInfo;
    }

    public void setAirInfo(List<AirspaceVo> airInfo) {
        this.airInfo = airInfo;
    }

    public List<LandingVo> getLandingInfo() {
        return landingInfo;
    }

    public void setLandingInfo(List<LandingVo> landingInfo) {
        this.landingInfo = landingInfo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public Map<String, Object> getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(Map<String, Object> contactInfo) {
        this.contactInfo = contactInfo;
    }

}

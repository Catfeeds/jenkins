package com.hfocean.uavportal.core.airapply.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 接受空域申请表单参数
 *
 * @author Leslie.Lam
 * @create 2017-06-20 15:57
 **/
public class AirApplyParam {

    @NotBlank
    private String planType;//计划类型

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
    private LandingInfo landingInfo;//起降场信息

    @NotBlank
    private String flyRule;//飞行规则

    private String remark;//备注

    @Valid
    @NotNull
    private AttachInfo attachInfo;//附件信息

    @Valid
    @NotNull
    private ContactInfo contactInfo;//联系人信息

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

    public String getFlyRule() {
        return flyRule;
    }

    public void setFlyRule(String flyRule) {
        this.flyRule = flyRule;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public AttachInfo getAttachInfo() {
        return attachInfo;
    }

    public void setAttachInfo(AttachInfo attachInfo) {
        this.attachInfo = attachInfo;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public LandingInfo getLandingInfo() {
        return landingInfo;
    }

    public void setLandingInfo(LandingInfo landingInfo) {
        this.landingInfo = landingInfo;
    }

    public static class ContactInfo{
        @NotBlank
        private String unitContactName;//单位联系人

        @NotBlank
        private String unitContact;//单位联系方式

        private String emergencyContactName;//紧急联系人

        private String emergencyContactPhone;//紧急联系人手机

        public String getUnitContactName() {
            return unitContactName;
        }

        public void setUnitContactName(String unitContactName) {
            this.unitContactName = unitContactName;
        }

        public String getUnitContact() {
            return unitContact;
        }

        public void setUnitContact(String unitContact) {
            this.unitContact = unitContact;
        }

        public String getEmergencyContactName() {
            return emergencyContactName;
        }

        public void setEmergencyContactName(String emergencyContactName) {
            this.emergencyContactName = emergencyContactName;
        }

        public String getEmergencyContactPhone() {
            return emergencyContactPhone;
        }

        public void setEmergencyContactPhone(String emergencyContactPhone) {
            this.emergencyContactPhone = emergencyContactPhone;
        }
    }


    public static class AttachInfo{
        @NotBlank
        private String name;

        @NotBlank
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

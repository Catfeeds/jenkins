package com.hfocean.uavportal.core.airplan.vo;

/**
* 一类飞行计划联系人所需参数
* @author Leslie.Lam
* @create 2017-06-24 14:57
**/
public class ContactInfoA extends ContactInfo{
    private String trainer;//教练员姓名

    private String traineeCount;//学员数量

    private String emergencyContactName;//紧急联系人

    private String emergencyContactPhone;//紧急联系人手机

    private String fieldContactName;//现场联系人

    private String fieldContactPhone;//现场联系人手机

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getTraineeCount() {
        return traineeCount;
    }

    public void setTraineeCount(String traineeCount) {
        this.traineeCount = traineeCount;
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

    public String getFieldContactName() {
        return fieldContactName;
    }

    public void setFieldContactName(String fieldContactName) {
        this.fieldContactName = fieldContactName;
    }

    public String getFieldContactPhone() {
        return fieldContactPhone;
    }

    public void setFieldContactPhone(String fieldContactPhone) {
        this.fieldContactPhone = fieldContactPhone;
    }

}

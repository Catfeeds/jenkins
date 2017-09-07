package com.hfocean.uavportal.console.airplan.vo;

/**
* 飞行计划列表
* @author Leslie.Lam
* @create 2017-06-24 16:20
**/
public class AirPlansExcel {

    private String applyTime;//申请时间

    private String userName;//申请单位

    private String number;//计划编号

    private String airTerritorial;//所属空域辖区

    private String arae;//所属行政辖区

    private String planType;//计划类型(任务性质)

    private String planes;//任务机型

    private String type;//报告类型（空域类别） 0为管制飞行计划，1为一类飞行计划，2为二类飞行计划

    private String spaceName;//空域名称

    private String high;//飞行高度

    private String contactNameAndPhone;//联系人及电话

    private String planTime;//计划时间 20160613 06：00至 18：00

    private String status;//状态

    private String situation;//计划执行动态

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getPlanes() {
        return planes;
    }

    public void setPlanes(String planes) {
        this.planes = planes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getArae() {
        return arae;
    }

    public void setArae(String arae) {
        this.arae = arae;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getAirTerritorial() {
        return airTerritorial;
    }

    public void setAirTerritorial(String airTerritorial) {
        this.airTerritorial = airTerritorial;
    }

    public String getContactNameAndPhone() {
        return contactNameAndPhone;
    }

    public void setContactNameAndPhone(String contactNameAndPhone) {
        this.contactNameAndPhone = contactNameAndPhone;
    }
}

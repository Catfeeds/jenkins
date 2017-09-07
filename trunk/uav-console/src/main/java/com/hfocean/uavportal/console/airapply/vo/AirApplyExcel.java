package com.hfocean.uavportal.console.airapply.vo;

/**
* 
* @author Leslie.Lam
* @create 2017-07-13 9:55
**/
public class AirApplyExcel {

    private String applyTime;//申请时间

    private String userName;//申请单位

    private String number;//申请任务编号

    private String planType;//计划类型(任务性质)

    private String planes;//任务机型

    private String spaceName;//空域名称

    private String airTerritorial;//所属空域辖区

    private String high;//飞行高度

    private String planTime;//计划时间 20160613 06：00至 18：00

    private String status;//状态

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
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

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getAirTerritorial() {
        return airTerritorial;
    }

    public void setAirTerritorial(String airTerritorial) {
        this.airTerritorial = airTerritorial;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }
}

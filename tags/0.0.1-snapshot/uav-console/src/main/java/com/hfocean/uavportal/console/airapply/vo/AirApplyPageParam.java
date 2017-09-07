package com.hfocean.uavportal.console.airapply.vo;

/**
* 接收分页查询空域申请参数
* @author Leslie.Lam
* @create 2017-06-22 17:17
**/
public class AirApplyPageParam {

    private Integer period;//查询天数

    private String type;//计划类型

    private String territorial;//所属空域辖区

    private String landing;//起降场类关键字

    private String air;//空域关键字

    private String sort;//排序字段多个字段间以逗号隔开如sort=type,landing默认使用时间排序

    private String code;//空域申请号

    private String unit;//个人或单位名称

    private String startDate;//计划开始时间 格式：yyyy-MM-dd

    private String endDate;

    private Integer status;//状态

    private String map;

    public String getTerritorial() {
        return territorial;
    }

    public void setTerritorial(String territorial) {
        this.territorial = territorial;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanding() {
        return landing;
    }

    public void setLanding(String landing) {
        this.landing = landing;
    }

    public String getAir() {
        return air;
    }

    public void setAir(String air) {
        this.air = air;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }
}

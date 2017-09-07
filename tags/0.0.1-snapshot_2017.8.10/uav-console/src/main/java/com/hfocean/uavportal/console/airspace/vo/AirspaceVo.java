package com.hfocean.uavportal.console.airspace.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
* 
* @author Leslie.Lam
* @create 2017-07-31 14:37
**/
public class AirspaceVo {

    @NotBlank(message = "空域编号不能为空")
    private String id;

    @NotNull(message = "空域类型不能为空")
    private Integer type;//0-管制 1-一类 2二类

    /**
     * 空域名称
     */
    @NotBlank(message = "空域名称不能为空")
    private String name;

    /**
     * 省
     */
    @NotBlank(message = "provinceCode不能为空")
    private String provinceCode;

    @NotBlank(message = "provinceName不能为空")
    private String provinceName;

    /**
     * 市
     */
    @NotBlank(message = "cityCode不能为空")
    private String cityCode;

    @NotBlank(message = "cityName不能为空")
    private String cityName;

    /**
     * 区
     */
    @NotBlank(message = "areaCode不能为空")
    private String areaCode;

    @NotBlank(message = "areaName不能为空")
    private String areaName;

    /**
     * 位置描述
     */
    private String location;

    /**
     * 所属辖区
     */
    @NotBlank(message = "所属辖区不能为空")
    private String territorial;

    /**
     * 示意图路径
     */
    @NotBlank(message = "示意图路径不能为空")
    private String sketchMap;

    /**
     * 空域范围类型
     */
    @NotNull(message = "空域范围类型不能为空")
    private Integer scopeType;

    /**
     * 空域范围信息
     */
    @NotBlank(message = "空域范围信息不能为空")
    private String scopeInfo;

    /**
     * 真高，单位为米
     */
    @NotBlank(message = "真高不能为空")
    private String high;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTerritorial() {
        return territorial;
    }

    public void setTerritorial(String territorial) {
        this.territorial = territorial;
    }

    public String getSketchMap() {
        return sketchMap;
    }

    public void setSketchMap(String sketchMap) {
        this.sketchMap = sketchMap;
    }

    public Integer getScopeType() {
        return scopeType;
    }

    public void setScopeType(Integer scopeType) {
        this.scopeType = scopeType;
    }

    public String getScopeInfo() {
        return scopeInfo;
    }

    public void setScopeInfo(String scopeInfo) {
        this.scopeInfo = scopeInfo;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }
}

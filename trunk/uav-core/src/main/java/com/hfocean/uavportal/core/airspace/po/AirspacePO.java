package com.hfocean.uavportal.core.airspace.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 
 */
@Entity
@Table(name="tb_airspace")
public class AirspacePO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    private Integer type;//0-管制 1-一类 2二类

    /**
     * 空域名称
     */
    private String name;

    /**
     * 省
     */
    @Column(name = "province_code")
    private String provinceCode;

    @Column(name = "province_name")
    private String provinceName;

    /**
     * 市
     */
    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "city_name")
    private String cityName;

    /**
     * 区
     */
    @Column(name = "area_code")
    private String areaCode;

    @Column(name = "area_name")
    private String areaName;

    /**
     * 位置描述
     */
    private String location;

    /**
     * 所属辖区
     */
    private String territorial;

    /**
     * 示意图路径
     */
    @Column(name = "sketch_map")
    private String sketchMap;

    /**
     * 空域范围类型
     */
    @Column(name = "scope_type")
    private Integer scopeType;

    /**
     * 空域范围信息
     */
    @Column(name = "scope_info")
    private String scopeInfo;

    /**
     * 真高，单位为米
     */
    private String high;

    public AirspacePO() {
    }

    public AirspacePO(Integer type) {
        this.type = type;
    }

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
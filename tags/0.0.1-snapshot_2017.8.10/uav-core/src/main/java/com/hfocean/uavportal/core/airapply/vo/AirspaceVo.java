package com.hfocean.uavportal.core.airapply.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class AirspaceVo implements Serializable {

    private String id;

    private String airnumber;//空域批件号，仅管制空域

    private Integer type;//0-管制 1-一类 2二类

    /**
     * 空域名称
     */
    private String name;

    /**
     * 省
     */
    private String provinceCode;

    private String provinceName;

    /**
     * 市
     */
    private String cityCode;

    private String cityName;

    /**
     * 区
     */
    private String areaCode;

    private String areaName;

    /**
     * 位置描述
     */
    @NotBlank(message = "空域位置描述不能为空")
    private String location;

    /**
     * 所属辖区
     */
    private String territorial;

    /**
     * 示意图路径
     */
    private String sketchMap;

    /**
     * 空域范围类型
     *为1时，
     *     @see CircleAirScope
     *
     * 为2时，为List<Position>
     *     @see Positions
     */
    @NotNull(message = "空域范围类型不能为空")
    private Integer scopeType;

    private CircleAirScope circleScope;

    private List<Position> centers;

    /**
     * 真高，单位为米
     */
    @NotBlank
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

    public String getAirnumber() {
        return airnumber;
    }

    public void setAirnumber(String airnumber) {
        this.airnumber = airnumber;
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

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public Integer getScopeType() {
        return scopeType;
    }

    public void setScopeType(Integer scopeType) {
        this.scopeType = scopeType;
    }

    public CircleAirScope getCircleScope() {
        return circleScope;
    }

    public void setCircleScope(CircleAirScope circleScope) {
        this.circleScope = circleScope;
    }

    public List<Position> getCenters() {
        return centers;
    }

    public void setCenters(List<Position> centers) {
        this.centers = centers;
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
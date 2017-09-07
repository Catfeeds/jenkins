package com.hfocean.uavportal.core.airapply.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
* 
* @author Leslie.Lam
* @create 2017-06-24 9:59
**/
public class PlaneInfo {
    @NotBlank
    private String name;

    @NotNull
    private Integer number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}

package com.hfocean.uavportal.core.airplan.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
* 飞行统计
* @author Leslie.Lam
* @create 2017-07-03 16:48
**/
@Entity
public class FlyCountVo {

    @Id
    private String userId;//'用户id',

    private Integer flyCount;// '飞行次数',

    private Integer flyTime;//'飞行时间，小时为单位',

    private Integer outCount;//'违规次数',

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getFlyCount() {
        return flyCount;
    }

    public void setFlyCount(Integer flyCount) {
        this.flyCount = flyCount;
    }

    public Integer getFlyTime() {
        return flyTime;
    }

    public void setFlyTime(Integer flyTime) {
        this.flyTime = flyTime;
    }

    public Integer getOutCount() {
        return outCount;
    }

    public void setOutCount(Integer outCount) {
        this.outCount = outCount;
    }
}

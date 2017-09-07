package com.hfocean.uavportal.core.airplan.po;

import javax.persistence.*;

/**
* 飞行统计
* @author Leslie.Lam
* @create 2017-07-03 16:48
**/
@Entity
@Table(name = "tb_fly_count")
public class FlyCount {

    @Id
    @GeneratedValue(generator = "objectIdGenerator")
    private String id;

    @Column(name = "user_id")
    private String userId;//'用户id',

    @Column(name = "fly_count")
    private Integer flyCount;// '飞行次数',

    @Column(name = "fly_time")
    private Integer flyTime;//'飞行时间，小时为单位',

    @Column(name = "out_count")
    private Integer outCount;//'违规次数',

    public FlyCount() {
    }

    public FlyCount(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

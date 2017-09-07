package com.hfocean.uavportal.core.landing.po;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 
 */
@Entity
@Table(name="tb_landing")
public class LandingPO  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "objectIdGenerator")
    private String id;

    /**
     * 起降场名称
     */
    @NotEmpty
    private String name;

    /**
     * 经度
     */
    @NotEmpty
    private String lng;

    /**
     * 纬度
     */
    @NotEmpty
    private String lat;

    /**
     * 位置描述
     */
    @NotEmpty
    private String location;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
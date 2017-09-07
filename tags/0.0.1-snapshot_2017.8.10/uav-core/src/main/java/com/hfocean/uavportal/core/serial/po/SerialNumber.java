package com.hfocean.uavportal.core.serial.po;

import javax.persistence.*;

/**
 * 流水号实体类
 *
 * @author Leslie.Lam
 * @create 2017-06-21 16:22
 **/
@Entity
@Table(name="tb_serial_number")
public class SerialNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="max_serial_number")
    private Integer serialNo;

    @Column(name="name")
    private String serialName;

    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public SerialNumber() {
    }

    public SerialNumber(String serialName) {
        this.serialName = serialName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public String getSerialName() {
        return serialName;
    }

    public void setSerialName(String serialName) {
        this.serialName = serialName;
    }
}

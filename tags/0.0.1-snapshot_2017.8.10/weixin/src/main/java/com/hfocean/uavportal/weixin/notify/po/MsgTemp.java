package com.hfocean.uavportal.weixin.notify.po;


import javax.persistence.*;

/**
* 
* @author Leslie.Lam
* @create 2017-06-29 15:37
**/
@Entity
@Table(name = "tb_wechat_temp")
public class MsgTemp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer type;//模板类型

    @Column(name = "temp_id")
    private String tempId;//

    private String content;//内容

    @Column(name = "content_value")
    private String contentValue;

    private Integer status;//0不可用,可用

    private String remark;//备注

    public MsgTemp() {
    }

    public MsgTemp(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentValue() {
        return contentValue;
    }

    public void setContentValue(String contentValue) {
        this.contentValue = contentValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

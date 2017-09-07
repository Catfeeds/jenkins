package com.hfocean.uavportal.core.document.po;

import javax.persistence.*;
import java.util.Date;

/**
* 合同表
* @author Leslie.Lam
* @create 2017-06-27 15:46
**/
@Entity
@Table(name = "tb_document")
public class Document {

    @Id
    @GeneratedValue(generator = "objectIdGenerator")
    private String id;

    private String name;

    private String url;

    @Column(name = "create_time")
    private Date createTime;

    private String creator;

    @Column(name = "update_time")
    private Date updateTime;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

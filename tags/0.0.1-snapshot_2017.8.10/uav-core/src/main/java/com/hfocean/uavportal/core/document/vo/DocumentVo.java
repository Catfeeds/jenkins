package com.hfocean.uavportal.core.document.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
* 
* @author Leslie.Lam
* @create 2017-06-27 15:54
**/
@Entity
@Table(name = "tb_document")
public class DocumentVo {

    @Id
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String url;

    @JsonFormat(pattern = "yyyyMMdd HH:mm")
    @Column(name = "create_time")
    private Date createTime;

    @NotBlank
    private String creator;

    @JsonFormat(pattern = "yyyyMMdd HH:mm")
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


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

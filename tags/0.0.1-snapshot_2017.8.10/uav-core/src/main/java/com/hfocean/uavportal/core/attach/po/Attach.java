package com.hfocean.uavportal.core.attach.po;/**
 * Created by msi- on 2017/6/20.
 */

import javax.persistence.*;
import java.util.Date;

/**
 * 附件表
 *
 * @author Leslie.Lam
 * @create 2017-06-20 15:16
 **/
@Entity
@Table(name = "tb_attach")
public class Attach {

    @Id
    @GeneratedValue(generator = "objectIdGenerator")
    private String id;

    private String name;

    private String url;

    @Column(name = "upload_time")
    private Date uploadTime;

    @Column(name = "master_id")
    private String masterId;

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

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }
}

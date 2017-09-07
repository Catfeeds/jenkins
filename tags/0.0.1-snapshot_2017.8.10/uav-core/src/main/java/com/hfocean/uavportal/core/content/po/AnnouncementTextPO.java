package com.hfocean.uavportal.core.content.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author guan.sj
 */
@Entity
@Table(name="tb_announcement_text")
public class AnnouncementTextPO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "tb_announcement_id")
    private String tbAnnouncementId;
    
    private String content;

    public String getTbAnnouncementId() {
        return tbAnnouncementId;
    }

    public void setTbAnnouncementId(String tbAnnouncementId) {
        this.tbAnnouncementId = tbAnnouncementId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

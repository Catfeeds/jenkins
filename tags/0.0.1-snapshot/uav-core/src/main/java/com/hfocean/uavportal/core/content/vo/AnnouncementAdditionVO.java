package com.hfocean.uavportal.core.content.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="tb_announcement_addition")
public class AnnouncementAdditionVO {
	@Id
	private String id;//附件IDw
    private String tbAnnouncementId;
    private String contentDesc;//内容描述
    private String url;//附件地址
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;//创建时间
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTbAnnouncementId() {
		return tbAnnouncementId;
	}
	public void setTbAnnouncementId(String tbAnnouncementId) {
		this.tbAnnouncementId = tbAnnouncementId;
	}
	public String getContentDesc() {
		return contentDesc;
	}
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
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
    
}

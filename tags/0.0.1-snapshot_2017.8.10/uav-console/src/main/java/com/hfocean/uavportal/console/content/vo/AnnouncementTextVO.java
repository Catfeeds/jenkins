package com.hfocean.uavportal.console.content.vo;

import java.util.Date;
import java.util.List;
public class AnnouncementTextVO {
	private String id;
	private String title;
	private String subTitle;
	private String content;
	private Date createTime;
	private Integer status;//0-禁用 1-启用
	private List<AnnouncementAdditionVO> attachments;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<AnnouncementAdditionVO> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<AnnouncementAdditionVO> attachments) {
		this.attachments = attachments;
	}
	
	
}

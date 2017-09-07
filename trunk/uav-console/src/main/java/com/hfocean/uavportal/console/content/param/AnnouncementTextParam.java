package com.hfocean.uavportal.console.content.param;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
public class AnnouncementTextParam {
	@NotBlank(message="标题不能为空")
	private String title;
	private String subTitle;
	@NotBlank(message="内容不能为空")
	private String content;
	private Integer status;//0-禁用 1-启用
	private List<AnnouncementAdditionParam> attachments;
	private Integer isToAll=1;//0-不推送，1-推送，默认推送
	
	public Integer getIsToAll() {
		return isToAll;
	}
	public void setIsToAll(Integer isToAll) {
		this.isToAll = isToAll;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<AnnouncementAdditionParam> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<AnnouncementAdditionParam> attachments) {
		this.attachments = attachments;
	}
	
	
}

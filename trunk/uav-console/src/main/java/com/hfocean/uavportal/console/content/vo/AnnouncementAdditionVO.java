package com.hfocean.uavportal.console.content.vo;

import java.util.Date;
public class AnnouncementAdditionVO {
	private String id;// 附件ID
	private String contentDesc;// 内容描述
	private String url;// 附件地址
	private Date createTime;//

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

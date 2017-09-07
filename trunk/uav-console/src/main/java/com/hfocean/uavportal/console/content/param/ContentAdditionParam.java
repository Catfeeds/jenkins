package com.hfocean.uavportal.console.content.param;

public class ContentAdditionParam {
	private String id;//id
	private String contentDesc;// 内容描述
	private String url;//附件路径
	private Integer type;// 类型(1图片2附件)
	
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}

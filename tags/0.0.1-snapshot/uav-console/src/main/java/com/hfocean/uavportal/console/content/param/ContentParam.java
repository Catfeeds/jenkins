package com.hfocean.uavportal.console.content.param;//

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

public class ContentParam {
	private String categoryId;//类目id
	@NotBlank(message="标题不能为空")
	private String title;// 标题
	private String subTitle;// 子标题
	private String titleDesc;// 标题描述
	private Integer sortOrder;// 排序
	private Integer status;// 状态
	@NotBlank(message="内容不能为空")
	private String content;// HTML内容，注意使用UTF-8编码
	private List<ContentAdditionParam> attachments;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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

	public String getTitleDesc() {
		return titleDesc;
	}

	public void setTitleDesc(String titleDesc) {
		this.titleDesc = titleDesc;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<ContentAdditionParam> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<ContentAdditionParam> attachments) {
		this.attachments = attachments;
	}

}

package com.hfocean.uavportal.core.content.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="tb_content")
public class ContentVO {
	@Id
	private String id;//稿件ID
	@Column(name="tbContentCategoryId")
	private String    categoryId;//父级菜单ID
	private String    title;//标题
	private String     subTitle;//子标题
	private String    titleDesc;//标题描述
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date     createTime;//创建时间
	private String creator;//作者
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date    updateTime;//更新时间
	private Integer   sortOrder;//排序
	private Integer     status;//状态
	private Integer     checkNumber;//查看次数
	
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
	public Integer getCheckNumber() {
		return checkNumber;
	}
	public void setCheckNumber(Integer checkNumber) {
		this.checkNumber = checkNumber;
	}
	
}

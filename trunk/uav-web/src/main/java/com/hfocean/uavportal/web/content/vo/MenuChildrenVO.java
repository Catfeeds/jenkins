package com.hfocean.uavportal.web.content.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author guan.sj
 */
@Entity
@Table(name="tb_content_category")
public class MenuChildrenVO {
	@Id
	private String id;//菜单ID
	private String parentId;//
	private String name;//菜单名称
	private Integer status;//菜单状态
	private Integer sortOrder;//排序
	private Integer isParent;//是否为父菜单
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTime;//创建时间
	private String creator;//创建者 
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updateTime;
	private Integer type;//0菜单1稿件列表2稿件内容
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Integer getIsParent() {
		return isParent;
	}
	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}
	public Date getCreateTime() {
		return createTime;
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	

}

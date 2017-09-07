package com.hfocean.uavportal.auth.system.permission.vo;

import java.util.Date;

import com.hfocean.uavportal.auth.system.permission.annotation.MjConvertPermission;
import com.hfocean.uavportal.auth.system.permission.eumn.PermissionFieldTypeEumn;

public class SysPermissionVO {
	
	private String resId;
	
	@MjConvertPermission(type = PermissionFieldTypeEumn.NAME, key = "resId")
	private String resName;
	
	@MjConvertPermission(type = PermissionFieldTypeEumn.COMPONENT, key = "resId")
	private String groupName;
	
	private Integer resAddDele;
	
	@MjConvertPermission(type = PermissionFieldTypeEumn.DESC, key = "resId")
	private String resDesc;
	
	private String creater;
	
	//TODO add
//	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getResAddDele() {
		return resAddDele;
	}

	public void setResAddDele(Integer resAddDele) {
		this.resAddDele = resAddDele;
	}

	public String getResDesc() {
		return resDesc;
	}

	public void setResDesc(String resDesc) {
		this.resDesc = resDesc;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



}

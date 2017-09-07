package com.hfocean.uavportal.auth.system.role.vo;

public class SysRoleResourceRefVO {
	
	private String roleId;
	
	private String resId;
	
	// 资源的“加”、“减”字段，0——加；1——减
	private int resAddDele;
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public int getResAddDele() {
		return resAddDele;
	}

	public void setResAddDele(int resAddDele) {
		this.resAddDele = resAddDele;
	}

}

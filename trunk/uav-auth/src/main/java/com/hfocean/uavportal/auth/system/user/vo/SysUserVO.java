package com.hfocean.uavportal.auth.system.user.vo;

import java.io.Serializable;
import java.util.Date;

public class SysUserVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String userName;// 账号

	private String password;// 密码

	private String nickName;// 昵称

	private String email;// 电子邮件

	private String phone;

	private String creater;// 创建者

	//TODO add
//	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date createTime;// 创建时间

	private Integer status;// 状态 1正常 0不可用

	private String orgId; // 所属组织

	private String orgName;// 组织名称
	
	private int type;//0 普通,1管理,2超级管理
	
	private int hasChildOrg; //0 没有子组织,1 有子组织

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getHasChildOrg() {
		return hasChildOrg;
	}

	public void setHasChildOrg(int hasChildOrg) {
		this.hasChildOrg = hasChildOrg;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}

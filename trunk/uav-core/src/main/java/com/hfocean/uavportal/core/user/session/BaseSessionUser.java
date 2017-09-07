package com.hfocean.uavportal.core.user.session;

import java.io.Serializable;

public class BaseSessionUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String userName;//用户名
	private Integer type;;//1个人用户,2企业用户
	private String name;//用户姓名或者企业名
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

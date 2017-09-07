package com.hfocean.uavportal.core.user.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.hfocean.uavportal.core.common.constant.Constant;

public class UserLoginParam {
	
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String username;
	
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String password;
	
	@NotNull(message=Constant.DEFAULT_MSG)
	private Integer type;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}

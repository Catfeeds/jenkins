package com.hfocean.uavportal.auth.system.user.param;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class UserAdd {
	
	@NotBlank(message="用户名不能为空")
	private String userName;// 账号

	@NotEmpty(message="密码不能为空")
	private String password;// 密码

	private String nickName;// 昵称

	private String email;// 电子邮件
	
	@NotBlank(message="组织ID不能为空")
	private String orgId;//组织id

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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}

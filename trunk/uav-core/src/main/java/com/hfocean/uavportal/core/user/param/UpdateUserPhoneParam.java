package com.hfocean.uavportal.core.user.param;

import org.hibernate.validator.constraints.NotBlank;

public class UpdateUserPhoneParam {
	@NotBlank(message="验证码为空")
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}

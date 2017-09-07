package com.hfocean.uavportal.core.user.param;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.hfocean.uavportal.core.common.constant.Constant;

public class ResetPwdParam {
	@Pattern(regexp=Constant.REG_PASSWORD,message=Constant.REG_PASSWORD_MSG)
	@NotBlank(message=Constant.RESET_NEWPWS_MSG)
	private String newPassword;
	@NotBlank(message=Constant.CODE_MSG)
	private String code;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}

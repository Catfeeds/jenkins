package com.hfocean.uavportal.core.content.param;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.hfocean.common.validation.custom.ConstantRegex;
import com.hfocean.uavportal.core.common.constant.Constant;

public class LeaveMessageParam {
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String name;
	
	@NotBlank(message=Constant.DEFAULT_MSG)
	@Pattern(regexp=ConstantRegex.EMAIL,message=ConstantRegex.EMAIL_MSG)
	private String email;
	
	@NotBlank(message=Constant.DEFAULT_MSG)
	@Pattern(regexp=ConstantRegex.PHONE,message=ConstantRegex.PHONE_MSG)
	private String phone;
	
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String content;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}

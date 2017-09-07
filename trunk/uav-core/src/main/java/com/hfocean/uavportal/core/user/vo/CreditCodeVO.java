package com.hfocean.uavportal.core.user.vo;

import com.hfocean.common.validation.custom.CreditCode;

public class CreditCodeVO {
	@CreditCode
	private String creditCode;

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	
	
}

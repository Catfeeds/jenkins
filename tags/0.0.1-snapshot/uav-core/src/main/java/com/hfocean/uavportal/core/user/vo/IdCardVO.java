package com.hfocean.uavportal.core.user.vo;

import com.hfocean.common.validation.custom.IdCard;

public class IdCardVO {
	@IdCard
	private String idCard;

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	
}

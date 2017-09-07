package com.hfocean.uavportal.core.user.session;

public class WxSessionUser extends BaseSessionUser {
	private static final long serialVersionUID = 1L;
	private String openId;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	
}

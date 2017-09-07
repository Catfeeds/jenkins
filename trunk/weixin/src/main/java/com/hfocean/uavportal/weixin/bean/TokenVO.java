package com.hfocean.uavportal.weixin.bean;

import java.io.Serializable;

/**
 * @author jw.lin
 * 2016年08月18日
 * 微信公众号token，有效期两个小时
 */
public class TokenVO extends VerificationVO implements Serializable {
	
	private static final long serialVersionUID = 7197111771014128693L;
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}

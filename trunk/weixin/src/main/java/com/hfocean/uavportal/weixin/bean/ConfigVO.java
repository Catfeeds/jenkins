package com.hfocean.uavportal.weixin.bean;

import java.io.Serializable;


/**
 * @author jw.lin
 * 2016年08月18日
 * 微信js-sdk验证，每个需要调用该sdk的界面都需要进行验证
 */
public class ConfigVO extends VerificationVO implements Serializable {
	
	private String appId;     //公众号唯一标识
	private String nonceStr;  //生成签名的随机串
	private String token;
	private String ticket;
	private String timestamp;   //生成签名的时间戳
	private String signature;   //签名
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	

}

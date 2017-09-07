package com.hfocean.uavportal.weixin.notify.enm;

public enum WechatTempType {

	飞行动态上报提醒(1),飞行超时提醒(2),飞行计划审核通过(3),飞行计划审核不通过(4);
	
	private Integer code;
	
	WechatTempType(Integer code){
		this.code = code;
	}
	
	public Integer code(){return this.code;}
}

package com.hfocean.uavportal.core.content.enm;

public enum BannerEnum {
	
	/** is_hidden 是否隐藏(0显示,1隐藏) */
	notHidden(0),isHidden(1),
	
	/** is_delete 是否删除(0未删除,1已删除) */
	notDelete(0),isDelete(1)
	
	;
	
	private int code;
	
	private BannerEnum(int code){
		this.code = code;
	}
	public int getCode(){
		return this.code;
	}
	
}

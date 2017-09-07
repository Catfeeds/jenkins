package com.hfocean.uavportal.core.content.enm;

public enum ReaderEnum {
	稿件(1),公告(2)
	;
	
	private int code;
	
	private ReaderEnum(int code){
		this.code = code;
	}
	public int getCode(){
		return this.code;
	}
	
}

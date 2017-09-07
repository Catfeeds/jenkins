package com.hfocean.uavportal.core.content.enm;

public enum LeaveMessageEnum {
	
	/** status 是否已读 0-已读 1-未读  2 删除*/
	已读(0),未读(1),删除(2),
	
	/** type 类型(1留言2举报) */
	留言(1),举报(2),
	
	/** source  来源(1PC,2微信) */
	PC(1),微信(2)
	
	
	;
	
	private int code;
	
	private LeaveMessageEnum(int code){
		this.code = code;
	}
	public int getCode(){
		return this.code;
	}
	
}

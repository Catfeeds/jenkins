package com.hfocean.uavportal.core.audit.enm;

public enum AuditEnum {
	/** 审核结果 */
	认证结果失败(0),认证结果成功(1),
	
	
	/** 审核类型 */
	个人用户认证(1),企业用户认证(2)
	
	
	;
	
	private int code;

	AuditEnum(int code){
		this.code = code;
	}

	public int getCode(){
		return this.code;
	}
	
}

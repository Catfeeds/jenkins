package com.hfocean.uavportal.core.user.enm;

import java.util.Objects;

public enum UserEnum {
	
	个人用户(1,"userType"),企业用户(2,"userType"),
	
	/** status 状态(1可用,2不可用) */
	可用(1),不可用(2),
	
	/** auth_status 认证状态(1未认证,2待认证,3已认证,4认证失败) */
	未认证(1),待认证(2),已认证(3),认证失败(4),
	
	男(1,"M"),女(2,"F"),
	;
	
	private int code;

	private String field;

	UserEnum(int code){
		this.code = code;
	}

	UserEnum(int code, String field) {
		this.code = code;
		this.field = field;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String field() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getCode(){
		return this.code;
	}

	public static String name(Integer code,String field){
		UserEnum[] values = values();
		for (UserEnum value:values){
			if(Objects.equals(field,value.field())){
				if(Objects.equals(code,value.getCode()))return value.name();
			}
		}
		return null;
	}
	
}

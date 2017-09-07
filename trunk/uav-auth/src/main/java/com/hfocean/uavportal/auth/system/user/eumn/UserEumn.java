package com.hfocean.uavportal.auth.system.user.eumn;

public enum UserEumn {

	正常(1),不可用(0);

	private int code;

	UserEumn(int code){
		this.code = code;
	}
	
	
	public int code(){
		return this.code;
	}

}

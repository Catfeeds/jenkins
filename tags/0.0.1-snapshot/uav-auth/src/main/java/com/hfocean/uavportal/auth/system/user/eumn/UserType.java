package com.hfocean.uavportal.auth.system.user.eumn;

import com.hfocean.uavportal.auth.base.utils.ConstantHelper;

public enum UserType {
	
	普通用户(ConstantHelper.COMMON_USER),管理员(ConstantHelper.ADMIN_USER),超级管理员(ConstantHelper.SUPERADMIN_USER);
	
	private int type;
	
	UserType(int type){
		this.type = type;
	}
	
	
	public int type(){
		return this.type;
	}

}

package com.hfocean.uavportal.console.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hfocean.uavportal.auth.system.user.param.UserList;
import com.hfocean.uavportal.console.ConsoleMainTest;
import com.hfocean.uavportal.console.system.service.ISysUserService;

public class SysUserServiceTest extends ConsoleMainTest{

	@Autowired
	private ISysUserService sysUserService;
	
	@Test
	public void testFindUser(){
		UserList user = new UserList();
		user.setUserName("123");
		this.sysUserService.findUser(user);
	}
	
}

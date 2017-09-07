package com.hfocean.uavportal.auth.system.permission.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hfocean.uavportal.auth.base.utils.AuthAppContextHelper;
import com.hfocean.uavportal.auth.system.permission.dao.ISysPermissionDao;
import com.hfocean.uavportal.auth.system.permission.service.ISysPermissionService;
import com.hfocean.uavportal.auth.system.permission.utils.PermissionHelper;
import com.hfocean.uavportal.auth.system.permission.vo.SysPermissionVO;

@Service("sysPermissionService")
public class SysPermissionServiceImpl implements ISysPermissionService {

	@Resource
	private ISysPermissionDao sysPermissionDao;
	
	public List<SysPermissionVO> listMyPermission(String userName)  {
		if(AuthAppContextHelper.isSupperAdmin(userName)) return PermissionHelper.getAllPermission();
		return this.sysPermissionDao.listMyPermission(userName);
	}

	public List<SysPermissionVO> listPermissionByRoleId(String roleId)  {
		return this.sysPermissionDao.listPermissionByRoleId(roleId);
	}


}

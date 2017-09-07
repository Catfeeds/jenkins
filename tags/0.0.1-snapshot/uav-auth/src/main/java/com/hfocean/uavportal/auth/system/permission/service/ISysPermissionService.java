package com.hfocean.uavportal.auth.system.permission.service;

import java.util.List;

import com.hfocean.uavportal.auth.system.permission.vo.SysPermissionVO;

public interface ISysPermissionService {

	List<SysPermissionVO> listMyPermission(String userName) ;

	List<SysPermissionVO> listPermissionByRoleId(String roleId) ;


}

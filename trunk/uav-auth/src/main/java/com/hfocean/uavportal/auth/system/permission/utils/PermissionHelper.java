package com.hfocean.uavportal.auth.system.permission.utils;

import java.util.ArrayList;
import java.util.List;

import com.hfocean.uavportal.auth.base.utils.AuthAppContextHelper;
import com.hfocean.uavportal.auth.system.permission.eumn.PermissionEumn;
import com.hfocean.uavportal.auth.system.permission.eumn.PermissionFieldTypeEumn;
import com.hfocean.uavportal.auth.system.permission.service.ISysPermissionService;
import com.hfocean.uavportal.auth.system.permission.vo.SysPermissionVO;

public class PermissionHelper {
	
	
	static List<SysPermissionVO> list = new ArrayList<SysPermissionVO>();
	
	public static String find(String key, PermissionFieldTypeEumn type) {
		for (PermissionEumn pe : PermissionEumn.values()) {
			if (pe.value.equals(key)) {
				switch (type) {
				case COMPONENT: return pe.component;
				case DESC: return pe.desc;
				case NAME: return pe.name();
				default:
					break;
				}
			}
		}
		return null;
	}

	public static String findName(String key){
		for(PermissionEumn pe :PermissionEumn.values()){
			if(pe.value.equals(key)) return pe.name();
		}
		return null;
	}
	
	public static String findComponent(String key){
		for(PermissionEumn pe :PermissionEumn.values()){
			if(pe.value.equals(key)) return pe.component();
		}
		return null;
	}
	
	public static String findDesc(String key){
		for(PermissionEumn pe :PermissionEumn.values()){
			if(pe.value.equals(key)) return pe.desc();
		}
		return null;
	}
	
	public static List<SysPermissionVO> getAllPermission(){
		if(list.size()>0) return list;
		for(PermissionEumn pe :PermissionEumn.values()){
			SysPermissionVO vo = new SysPermissionVO();
			vo.setResAddDele(0);
			vo.setGroupName(pe.component);
			vo.setResId(pe.value);
			vo.setResName(pe.name());
			vo.setResDesc(pe.desc);
			list.add(vo);
		}
		return list;
	}
	
	public static boolean hasPermission(PermissionEumn permission){
		
		ISysPermissionService ps = AuthAppContextHelper.getBean(ISysPermissionService.class);
		
		List<SysPermissionVO> list = null;
		try {
			list = ps.listMyPermission(AuthAppContextHelper.getSysUser().getUserName());
		} catch (Exception e) { }
		if(list == null) return false;
		for (SysPermissionVO sv : list) {
			if(sv.getResId().equals(permission.value)) return true;
		}
		return false;
	}

}

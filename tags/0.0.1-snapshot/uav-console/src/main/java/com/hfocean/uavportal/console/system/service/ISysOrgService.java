package com.hfocean.uavportal.console.system.service;

import java.util.List;

import com.hfocean.uavportal.auth.system.org.vo.SysOrgVO;

public interface ISysOrgService {

	SysOrgVO addOrg(SysOrgVO vo) ;

	void delOrg(String orgId,boolean delChild) ;

	List<SysOrgVO> listCurrentUserOrg() ;

	SysOrgVO updateOrg(SysOrgVO org) ;

	SysOrgVO findOrg(String orgId) ;
	
}

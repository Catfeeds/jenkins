package com.hfocean.uavportal.core.content.repository;

import java.util.List;

import com.hfocean.uavportal.core.common.repository.MyRepository;
import com.hfocean.uavportal.core.content.po.MenuColumnPO;

public interface MenuColumnRepository extends MyRepository<MenuColumnPO,String> {
	
	List<MenuColumnPO> findByStatusOrderBySortOrderAsc(Integer status);
}
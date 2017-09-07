package com.hfocean.uavportal.core.content.repository;


import java.util.List;

import com.hfocean.uavportal.core.common.repository.MyRepository;
import com.hfocean.uavportal.core.content.po.ContentCategoryPO;

public interface ContentCategoryRepository extends MyRepository<ContentCategoryPO,String> {
	List<ContentCategoryPO> findByStatusOrderBySortOrder(Integer status);
}
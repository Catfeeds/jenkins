package com.hfocean.uavportal.core.content.repository;


import java.util.List;

import com.hfocean.uavportal.core.common.repository.MyRepository;
import com.hfocean.uavportal.core.content.po.BannerPO;

public interface BannerRepository extends MyRepository<BannerPO,String> {
	List<BannerPO> findByIsDeleteAndIsHiddenOrderBySortOrderAsc(Integer isDelete, Integer isHidden);
}
package com.hfocean.uavportal.core.content.repository;

import java.util.List;

import com.hfocean.uavportal.core.common.repository.MyRepository;
import com.hfocean.uavportal.core.content.po.AnnouncementPO;

public interface AnnouncementRepository extends MyRepository<AnnouncementPO,String> {
	List<AnnouncementPO> findByStatusOrderByCreateTimeDesc(Integer status);
}
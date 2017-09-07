package com.hfocean.uavportal.core.content.repository;

import java.util.List;

import com.hfocean.uavportal.core.common.repository.MyRepository;
import com.hfocean.uavportal.core.content.po.ContentAdditionPO;

public interface ContentAdditionRepository extends MyRepository<ContentAdditionPO,String> {
	List<ContentAdditionPO> findByTbContentId(String contentId);
}
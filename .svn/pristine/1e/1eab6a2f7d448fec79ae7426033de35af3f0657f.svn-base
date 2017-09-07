package com.hfocean.uavportal.core.user.repository;

import java.util.List;

import com.hfocean.uavportal.core.common.repository.MyRepository;
import com.hfocean.uavportal.core.user.po.UserWxPO;

public interface UserWxRepository extends MyRepository<UserWxPO,String> {
	UserWxPO findOneByUserIdAndType(String userId, Integer type);
	List<UserWxPO> findByUserIdAndType(String userId, Integer type);
}
package com.hfocean.uavportal.core.user.repository;

import com.hfocean.uavportal.core.common.repository.MyRepository;
import com.hfocean.uavportal.core.user.po.UserPersonalPO;

public interface UserPersonalRepository extends MyRepository<UserPersonalPO,String> {
	UserPersonalPO findByUserName(String username);
	UserPersonalPO findByPhone(String phone);
	UserPersonalPO findByIdCard(String idCard);
}
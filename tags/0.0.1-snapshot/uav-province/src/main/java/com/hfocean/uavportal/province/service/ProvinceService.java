package com.hfocean.uavportal.province.service;

import java.util.List;

import com.hfocean.uavportal.province.po.ProvincePO;

public interface ProvinceService {
	List<ProvincePO> findProvinces(Integer id)throws Exception;
}

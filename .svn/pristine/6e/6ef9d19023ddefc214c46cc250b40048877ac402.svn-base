package com.hfocean.uavportal.province.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hfocean.uavportal.province.po.ProvincePO;
import com.hfocean.uavportal.province.repository.ProvinceRepository;
import com.hfocean.uavportal.province.service.ProvinceService;


/**
 * @author guan.sj
 */
@Service
public class ProvinceServiceImpl  implements ProvinceService {

	@Autowired
	private ProvinceRepository provinceRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<ProvincePO> findProvinces(Integer id) throws Exception {
		if(id==null)id=0;
		return provinceRepository.findByPid(id);
	}
	
	
}

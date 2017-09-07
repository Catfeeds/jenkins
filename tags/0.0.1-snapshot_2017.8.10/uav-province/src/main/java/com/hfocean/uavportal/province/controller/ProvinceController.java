package com.hfocean.uavportal.province.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hfocean.uavportal.province.po.ProvincePO;
import com.hfocean.uavportal.province.service.ProvinceService;

@Controller
public class ProvinceController{

	@Autowired
	private ProvinceService provinceService;
	
	@RequestMapping(value = "/province", method = RequestMethod.GET)
	@ResponseBody
	public List<ProvincePO> userPerRegister(Integer id)throws Exception {
		return provinceService.findProvinces(id);
	}
}

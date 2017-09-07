package com.hfocean.uavportal.web.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 主控制器
 * @author Gene
 *
 */
@Controller
public class IndexController {

	@RequestMapping(value="/",method=RequestMethod.GET)
	public String index(){
		return "index";
	}
	
}

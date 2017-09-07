package com.hfocean.uavportal.weixin.web.controller;

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
	
	/**
	 * 前端界面入口
	 */
	@RequestMapping(value={"efly/**","/"},method=RequestMethod.GET)
	public String wc(){
		return "index";
	}
	
}

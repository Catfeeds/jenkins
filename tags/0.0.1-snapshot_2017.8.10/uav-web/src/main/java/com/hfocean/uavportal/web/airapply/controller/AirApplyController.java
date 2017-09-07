package com.hfocean.uavportal.web.airapply.controller;

import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.core.airapply.po.AirApplyPO;
import com.hfocean.uavportal.core.airapply.service.AirApplyService;
import com.hfocean.uavportal.core.annotation.Login;
import com.hfocean.uavportal.web.util.WebAppContextHelper;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Controller
public class AirApplyController {

	@Resource
	private AirApplyService airApplyService;

	/**
	 * 创建空域申请
	 * @param param
	 * @return
	 * @throws Exception
     */
	@Login(authStatus=true)
	@RequestMapping(method = RequestMethod.POST,value = "/console/airplan")
	@ResponseBody
	public ResponseEntity<WebResponse> addAirApply(@RequestBody String param) throws Exception {
		airApplyService.addAirApply(param, WebAppContextHelper.getCurrentUser());
		return ResponseEntity.ok().body(new WebResponse());
	}

	/**
	 * 查询空域申请列表
	 * @param period
	 * @param type
	 * @return
	 * @throws Exception
     */
	@Login
	@RequestMapping(method = RequestMethod.GET,value = "/console/airplans")
	@ResponseBody
	public ResponseEntity<WebResponse> queryAirApplys(Integer period,String type) throws Exception {
		return ResponseEntity.ok().body(new WebResponse(airApplyService.queryAirApplys(period,type,WebAppContextHelper.getCurrentUser())));
	}

	/**
	 * 获取空域申请
	 * @param airplanCode
	 * @return
	 * @throws Exception
     */
	@Login
	@RequestMapping(method = RequestMethod.GET,value = "/console/airplan/{airplanCode}")
	@ResponseBody
	public WebResponse queryAirApplyByCode(@PathVariable("airplanCode") String airplanCode) throws Exception {
		return new WebResponse(airApplyService.findOne(Example.of(new AirApplyPO(airplanCode,WebAppContextHelper.getCurrentUser().getUserId()))));
	}

	/**
	 * 撤销空域申请
	 * @param aircode
	 * @return
	 * @throws Exception
     */
	@Login(authStatus=true)
	@RequestMapping(method = RequestMethod.DELETE,value = "console/airplan/{aircode}")
	@ResponseBody
	public ResponseEntity<WebResponse> delAirApply(@PathVariable("aircode") String aircode) throws Exception {
		airApplyService.revokeAirApply(aircode);
		return ResponseEntity.ok().body(new WebResponse());
	}


}

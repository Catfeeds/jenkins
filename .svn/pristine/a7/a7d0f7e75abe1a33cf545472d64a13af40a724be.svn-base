package com.hfocean.uavportal.console.airplan.controller;

import com.hfocean.common.util.VerdictUtil;
import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.auth.system.permission.annotation.MjPermission;
import com.hfocean.uavportal.auth.system.permission.eumn.PermissionEumn;
import com.hfocean.uavportal.console.airplan.service.AirPlanService;
import com.hfocean.uavportal.console.airplan.vo.AirPlanPageParam;
import com.hfocean.uavportal.core.common.bean.Pager;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
public class AirPlanController {

	@Resource
	private AirPlanService airPlanService;

	/**
	 * 分页查询飞行计划申请
	 * @param param
	 * @return
	 * @throws Exception
     */
	@MjPermission(values={PermissionEumn.飞行计划列表})
	@RequestMapping(method = RequestMethod.GET,value = "flyplans")
	@ResponseBody
	public WebResponse queryAirplansByPage(@Validated AirPlanPageParam param, HttpServletRequest request) throws Exception {
		return new WebResponse(airPlanService.queryAirplansByPage(param,new Pager(request)));
	}

	/**
	 * 导出飞行计划申请列表
	 * @param param
	 * @return
	 * @throws Exception
     */
	@MjPermission(values={PermissionEumn.导出飞行计划列表})
	@RequestMapping(method = RequestMethod.GET,value = "/excel/flyplans")
	@ResponseBody
	public WebResponse exportAirplansExcel(@Validated AirPlanPageParam param) throws Exception {
		airPlanService.exportAirplansExcel(param);
		return new WebResponse();
	}

	/**
	 * @param flyplanId
	 * 	status 0拒绝 1-通过   reason:拒绝原因，仅当拒绝时填写
	 * @return
	 * @throws Exception
	 */
	@MjPermission(values={PermissionEumn.飞行计划审核})
	@RequestMapping(method = RequestMethod.PUT,value = "/flyplan/{flyplanId}/status")
	@ResponseBody
	public ResponseEntity<WebResponse> auditAirplan(@PathVariable("flyplanId") String flyplanId , @RequestBody String param) throws Exception {
		VerdictUtil.assertNotNull(flyplanId);
		airPlanService.auditAirPlan(flyplanId,param);
		return ResponseEntity.ok().body(new WebResponse());
	}
}

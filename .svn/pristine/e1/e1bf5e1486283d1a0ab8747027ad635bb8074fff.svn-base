package com.hfocean.uavportal.console.airapply.controller;

import com.hfocean.common.util.VerdictUtil;
import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.auth.system.permission.annotation.MjPermission;
import com.hfocean.uavportal.auth.system.permission.eumn.PermissionEumn;
import com.hfocean.uavportal.console.airapply.service.AirApplyService;
import com.hfocean.uavportal.console.airapply.vo.AirApplyPageParam;
import com.hfocean.uavportal.core.common.bean.Pager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
public class AirApplyController {

	@Resource
	private AirApplyService airApplyService;

	/**
	 * 分页查询空域申请
	 * @param param
	 * @return
	 * @throws Exception
     */
	@RequestMapping(method = RequestMethod.GET,value = "airplan")
	@MjPermission(values={PermissionEumn.空域申请列表})
	@ResponseBody
	public WebResponse queryAirApplyByPage(@Validated AirApplyPageParam param, HttpServletRequest request) throws Exception {
		return new WebResponse(airApplyService.queryAirApplyByPage(param,new Pager(request)));
	}

	/**
	 * 导出空域申请申请列表
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@MjPermission
	@RequestMapping(method = RequestMethod.GET,value = "/excel/airplans")
	@ResponseBody
	public WebResponse exportAirApplyExcel(@Validated AirApplyPageParam param) throws Exception {
		airApplyService.exportAirApplyExcel(param);
		return new WebResponse();
	}

	/**
	 * @param airplanId
	 * status: 0 //0拒绝 1-通过   reason:拒绝原因，仅当拒绝时填写
	 * @return
	 * @throws Exception
     */
	@MjPermission(values={PermissionEumn.空域申请审核})
	@RequestMapping(method = RequestMethod.PUT,value = "/airplan/{airplanId}/status")
	@ResponseBody
	public ResponseEntity<WebResponse> auditAirplan(@PathVariable("airplanId") String airplanId , @RequestBody String param) throws Exception {
		VerdictUtil.assertNotNull(airplanId);
		airApplyService.auditAirApply(airplanId,param);
		return ResponseEntity.ok().body(new WebResponse());
	}
}

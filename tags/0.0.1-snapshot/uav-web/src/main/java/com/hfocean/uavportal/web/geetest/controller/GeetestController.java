package com.hfocean.uavportal.web.geetest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hfocean.uavportal.web.geetest.sdk.GeetestHelper;


@Controller
public class GeetestController{

	/**极验验证码获取
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/gt/get", method = RequestMethod.GET)
	@ResponseBody
	public void varifyOne(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		GeetestHelper.geetestGet(request, response);
	}
}

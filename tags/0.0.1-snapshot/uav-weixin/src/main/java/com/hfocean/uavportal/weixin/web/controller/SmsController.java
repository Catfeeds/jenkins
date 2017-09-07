package com.hfocean.uavportal.weixin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.core.sms.enm.SmsTemplate;
import com.hfocean.uavportal.core.sms.service.ISmsService;


@Controller
public class SmsController{

	@Autowired
	private ISmsService iSmsService;
	
	/**发送验证码(检查手机号码未注册)
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/auth/code", method = RequestMethod.GET)
	@ResponseBody
	public WebResponse code(String phone)throws Exception {
		iSmsService.sendVerifyCodeByPhoneNotExist(phone, SmsTemplate.注册账号.stemp());
		return new WebResponse();
	}
	
	/**验证验证码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/auth/code/vertify", method = RequestMethod.POST)
	@ResponseBody
	public WebResponse vertify(String code)throws Exception {
		iSmsService.verifyCode(code);
		return new WebResponse();
	}
	
	/**发送验证码(检查手机号码已注册)
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/auth/pcode", method = RequestMethod.GET)
	@ResponseBody
	public WebResponse pcode(String phone)throws Exception {
		iSmsService.sendVerifyCodeByPhoneIsExist(phone, SmsTemplate.注册账号.stemp());
		return new WebResponse();
	}
	
	
}

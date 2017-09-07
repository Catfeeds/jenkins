package com.hfocean.uavportal.weixin.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;
import com.hfocean.common.exception.type.LoginException;
import com.hfocean.common.exception.type.RequestParamException;
import com.hfocean.common.util.ValidateUtil;
import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.core.annotation.Login;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.sms.enm.SmsTemplate;
import com.hfocean.uavportal.core.sms.service.ISmsService;
import com.hfocean.uavportal.core.user.param.UpdateUserComParam;
import com.hfocean.uavportal.core.user.param.UpdateUserPerParam;
import com.hfocean.uavportal.core.user.param.UserComRegisterParam;
import com.hfocean.uavportal.core.user.param.UserPerRegisterParam;
import com.hfocean.uavportal.core.user.service.IUserServiceCore;
import com.hfocean.uavportal.core.user.vo.CreditCodeVO;
import com.hfocean.uavportal.core.user.vo.IdCardVO;
import com.hfocean.uavportal.weixin.user.param.LoginParam;
import com.hfocean.uavportal.weixin.user.service.IUserService;
import com.hfocean.uavportal.weixin.util.WechatAppContextHelper;
import com.hfocean.uavportal.weixin.verification.WechatService;


@Controller
public class UserController{

	@Autowired
	private IUserService iUserService;
	
	@Autowired
	private	WechatService wechatService;
	
	@Autowired
	private IUserServiceCore iUserServiceCore;
	
	@Autowired
	private ISmsService iSmsService;
	
	/**自动登录和获取openId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/auth/autologin", method = RequestMethod.GET)
	@ResponseBody
	public WebResponse getOpenId(String code,String openId)throws Exception {
		WebResponse result = new WebResponse();
		if(code!=null){
			if(code==null||code.equals(""))throw new RequestParamException();
			openId = wechatService.getOpenId(code);
		}
		if(StringUtils.isEmpty(openId))throw new RequestParamException();
		WechatAppContextHelper.setOpenId(openId);
		result = iUserService.autoLogin(openId);
		return result;
	}
	
	/**验证手机号码或者用户名是否已经注册
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/auth/register/verify", method = RequestMethod.POST)
	@ResponseBody
	public WebResponse verifyPhone(String data, Integer type)throws Exception {
		if(data==null||type==null||data.equals(""))throw new RequestParamException();
		switch (type) {
		case 1:// 验证手机号码
			iUserServiceCore.verifyPhone(data);
			break;
		case 2:// 验证用户名
			iUserServiceCore.verifyName(data);
			break;
		case 3:// 验证身份证
			IdCardVO idCard = new IdCardVO();
			idCard.setIdCard(data);
			ValidateUtil.validate(idCard);
			iUserServiceCore.verifyIdCard(data);
			break;
		case 4:// 验证公司名称
			iUserServiceCore.verifyCompanyName(data);
			break;
		case 5:// 验证社会信用代码
			CreditCodeVO creditCode = new CreditCodeVO();
			creditCode.setCreditCode(data);
			ValidateUtil.validate(creditCode);
			iUserServiceCore.verifyLicenseNumber(data);
			break;
		default:
			throw new RequestParamException();
		}
		return new WebResponse();
	}
	
	@RequestMapping(value = "/auth/register/per", method = RequestMethod.POST)
	@ResponseBody
	public WebResponse userPerRegister(UserPerRegisterParam param)throws Exception {
		String openId = WechatAppContextHelper.getOpenId();
		if(openId==null||openId.equals(""))throw new LoginException();
		String phone = iSmsService.verifyStatus();
		param.setPhone(phone);
		ValidateUtil.validate(param);
		iUserService.saveUserPerWxRgister(param,openId);
		iSmsService.removeCode();
		iSmsService.sendSMS(phone, SmsTemplate.注册成功.stemp(), param.getUserName());
		return new WebResponse(openId);
	}

	@RequestMapping(value = "/auth/register/com", method = RequestMethod.POST)
	@ResponseBody
	public WebResponse userComRegister(UserComRegisterParam param)throws Exception {
		String openId = WechatAppContextHelper.getOpenId();
		if(openId==null||openId.equals(""))throw new LoginException();
		String phone = iSmsService.verifyStatus();
		param.setContactPhone(phone);
		ValidateUtil.validate(param);
		iUserService.saveUserComWxRgister(param, openId);
		iSmsService.removeCode();
		iSmsService.sendSMS(phone, SmsTemplate.注册成功.stemp(), param.getUserName());
		return new WebResponse(openId);
	}
	
	/**首次登录
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/auth/login", method = RequestMethod.POST)
	@ResponseBody
	public WebResponse login(LoginParam param)throws Exception {
		String openId = WechatAppContextHelper.getOpenId();
		if(openId==null||openId.equals(""))throw new LoginException();
		ValidateUtil.validate(param);
		String phone = iSmsService.verifyCode(param.getCode());
		param.setPhone(phone);
		WebResponse login = iUserService.login(param,openId);
		iSmsService.removeCode();
		return login;
	}
	
	/**用户信息获取接口
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/info", method = RequestMethod.GET)
	@ResponseBody
	@Login
	public WebResponse userInfo()throws Exception {
		String openId = WechatAppContextHelper.getOpenId();
		if(openId==null||openId.equals(""))throw new LoginException();
		return iUserService.findUserInfo(openId);
	}
	
	/**获取个人消息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/notices", method = RequestMethod.GET)
	@ResponseBody
	@Login
	public WebResponse notices(HttpServletRequest request)throws Exception {
		String userId = WechatAppContextHelper.getCurrentUser().getUserId();
		return new WebResponse(iUserService.findNotices(new Pager(request),userId));
	}
	
	/**个人用户资料更新
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/per/update", method = RequestMethod.POST)
	@ResponseBody
	@Login
	public WebResponse userPerUpdate(UpdateUserPerParam param)throws Exception {
		ValidateUtil.validate(param);
		String userId = WechatAppContextHelper.getCurrentUser().getUserId();
		if(userId==null||userId.equals(""))throw new BaseBusinessException(BaseBusinessError.UNAUTHORIZED.getCode(), BaseBusinessError.UNAUTHORIZED.getMessage());
		iUserServiceCore.updateUserPer(userId, param);
		return new WebResponse();
	}
	
	/**企业用户资料更新
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/com/update", method = RequestMethod.POST)
	@ResponseBody
	@Login
	public WebResponse userComUpdate(UpdateUserComParam param)throws Exception {
		ValidateUtil.validate(param);
		String userId = WechatAppContextHelper.getCurrentUser().getUserId();
		if(userId==null||userId.equals(""))throw new BaseBusinessException(BaseBusinessError.UNAUTHORIZED.getCode(), BaseBusinessError.UNAUTHORIZED.getMessage());
		iUserServiceCore.updateUserCom(userId, param);
		return new WebResponse();
	}
	
}

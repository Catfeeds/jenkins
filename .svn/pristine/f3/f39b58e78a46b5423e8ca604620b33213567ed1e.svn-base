package com.hfocean.uavportal.web.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;
import com.hfocean.common.exception.type.RequestParamException;
import com.hfocean.common.util.ValidateUtil;
import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.core.annotation.Login;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.sms.enm.SmsTemplate;
import com.hfocean.uavportal.core.sms.service.ISmsService;
import com.hfocean.uavportal.core.user.enm.UserEnum;
import com.hfocean.uavportal.core.user.param.ResetPwdParam;
import com.hfocean.uavportal.core.user.param.UpdateUserComParam;
import com.hfocean.uavportal.core.user.param.UpdateUserPerParam;
import com.hfocean.uavportal.core.user.param.UpdateUserPhoneParam;
import com.hfocean.uavportal.core.user.param.UserComRegisterParam;
import com.hfocean.uavportal.core.user.param.UserLoginParam;
import com.hfocean.uavportal.core.user.param.UserPerRegisterParam;
import com.hfocean.uavportal.core.user.service.IUserServiceCore;
import com.hfocean.uavportal.core.user.session.WebSessionUser;
import com.hfocean.uavportal.core.user.vo.CreditCodeVO;
import com.hfocean.uavportal.core.user.vo.IdCardVO;
import com.hfocean.uavportal.core.util.HttpRequestUtils;
import com.hfocean.uavportal.web.geetest.sdk.GeetestHelper;
import com.hfocean.uavportal.web.user.service.IUserService;
import com.hfocean.uavportal.web.util.WebAppContextHelper;


@Controller
public class UserController{

	@Autowired
	private IUserServiceCore iUserServiceCore;
	
	@Autowired
	private IUserService iUserService;
	
	@Autowired
	private ISmsService iSmsService;
	
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
		String phone = iSmsService.verifyStatus();
		if(phone==null||phone.equals(""))throw new BaseBusinessException(BaseBusinessError.PARAMETER_ERROR.getCode(),"手机号码为空或未认证");
		String password = param.getPassword();
		if(password==null||password.equals(""))throw new BaseBusinessException(BaseBusinessError.PARAMETER_ERROR.getCode(),"密码为空");
		param.setPhone(phone);
		ValidateUtil.validate(param);
		iUserServiceCore.saveUserPerRgister(param);
		iSmsService.removeCode();
		iSmsService.sendSMS(phone, SmsTemplate.注册成功.stemp(), param.getUserName());
		return new WebResponse();
	}

	@RequestMapping(value = "/auth/register/com", method = RequestMethod.POST)
	@ResponseBody
	public WebResponse userComRegister(UserComRegisterParam param)throws Exception {
		String phone = iSmsService.verifyStatus();
		if(phone==null||phone.equals(""))throw new BaseBusinessException(BaseBusinessError.PARAMETER_ERROR.getCode(),"手机号码为空或未认证");
		String password = param.getPassword();
		if(password==null||password.equals(""))throw new BaseBusinessException(BaseBusinessError.PARAMETER_ERROR.getCode(),"密码为空");
		param.setContactPhone(phone);
		ValidateUtil.validate(param);
		iUserServiceCore.saveUserComRgister(param);
		iSmsService.removeCode();
		iSmsService.sendSMS(phone, SmsTemplate.注册成功.stemp(), param.getUserName());
		return new WebResponse();
	}
	
	@RequestMapping(value = "/auth/login", method = RequestMethod.POST)
	@ResponseBody
	public WebResponse login(UserLoginParam param, HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		GeetestHelper.geetestPost(request, response);
		ValidateUtil.validate(param);
		Integer type = param.getType();
		WebResponse result = new WebResponse();
		String ip = HttpRequestUtils.getIp(request);
		if(type==UserEnum.个人用户.getCode()){
			result.setData(iUserService.userPerLogin(param, ip));
		}else{
			result.setData(iUserService.userComLogin(param, ip));
		}
		return result;
	}
	
	@RequestMapping(value = "/auth/logout", method = RequestMethod.GET)
	@ResponseBody
	public WebResponse userLogout()throws Exception {
		WebAppContextHelper.removeCurrentUser();
		return new WebResponse();
	}
	
	/**重置密码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/auth/reset", method = RequestMethod.POST)
	@ResponseBody
	public WebResponse resetPassword(ResetPwdParam param)throws Exception {
		ValidateUtil.validate(param);
		iUserServiceCore.updateResetPassword(param);
		iSmsService.removeCode();
		return new WebResponse();
	}
	
	/**获取个人消息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/portal/notices", method = RequestMethod.GET)
	@ResponseBody
	@Login
	public WebResponse notices(HttpServletRequest request)throws Exception {
		return new WebResponse(iUserServiceCore.findNotices(new Pager(request),WebAppContextHelper.getCurrentUser().getUserId()));
	}
	
	/**个人用户资料更新
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/per/update", method = RequestMethod.PUT)
	@ResponseBody
	@Login
	public WebResponse userPerUpdate(UpdateUserPerParam param)throws Exception {
		ValidateUtil.validate(param);
		String userId = WebAppContextHelper.getCurrentUser().getUserId();
		if(userId==null||userId.equals(""))throw new BaseBusinessException(BaseBusinessError.UNAUTHORIZED.getCode(), BaseBusinessError.UNAUTHORIZED.getMessage());
		iUserServiceCore.updateUserPer(userId, param);
		return new WebResponse();
	}
	
	/**企业用户资料更新
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/com/update", method = RequestMethod.PUT)
	@ResponseBody
	@Login
	public WebResponse userComUpdate(UpdateUserComParam param)throws Exception {
		ValidateUtil.validate(param);
		String userId = WebAppContextHelper.getCurrentUser().getUserId();
		if(userId==null||userId.equals(""))throw new BaseBusinessException(BaseBusinessError.UNAUTHORIZED.getCode(), BaseBusinessError.UNAUTHORIZED.getMessage());
		iUserServiceCore.updateUserCom(userId, param);
		return new WebResponse();
	}
	
	/**用户信息获取接口
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/info", method = RequestMethod.GET)
	@ResponseBody
	@Login
	public WebResponse userInfo()throws Exception {
		return iUserService.findUserInfo();
	}
	
	/**获取用户审核状态
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/auth/status", method = RequestMethod.GET)
	@ResponseBody
	@Login
	public WebResponse userAuthStatus()throws Exception {
		WebSessionUser<?> currentUser = WebAppContextHelper.getCurrentUser();
		return new WebResponse(iUserServiceCore.findUserAuthStatus(currentUser.getUserId(), currentUser.getType()));
	}
	
	/**获取用户审核状态
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/phone", method = RequestMethod.PUT)
	@ResponseBody
	@Login
	public WebResponse updateUserPhone(UpdateUserPhoneParam param)throws Exception {
		String phone = iSmsService.verifyCode(param.getCode());
		WebSessionUser<?> currentUser = WebAppContextHelper.getCurrentUser();
		return new WebResponse(iUserServiceCore.updateUserPhone(currentUser.getUserId(), currentUser.getType(), phone));
	}
}

package com.hfocean.uavportal.weixin.user.service;

import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.service.BaseService;
import com.hfocean.uavportal.core.user.param.UserComRegisterParam;
import com.hfocean.uavportal.core.user.param.UserPerRegisterParam;
import com.hfocean.uavportal.core.user.po.UserWxPO;
import com.hfocean.uavportal.core.user.session.WxSessionUser;
import com.hfocean.uavportal.weixin.user.param.LoginParam;

public interface IUserService extends BaseService<UserWxPO,String> {
	
	WebResponse autoLogin(String openId)throws Exception;
	
	String saveUserPerWxRgister(UserPerRegisterParam param, String openId)throws Exception;
	
	String saveUserComWxRgister(UserComRegisterParam param, String openId)throws Exception;
	
	WebResponse login(LoginParam param, String openId)throws Exception;
	
	WebResponse findUserInfo(String openId)throws Exception;
	
	/**根据openId获取微信绑定的用户id和帐号
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	WxSessionUser getCurrentUser(String openId)throws Exception;
	
	Pager findNotices(Pager pager, String userId)throws Exception;
}

package com.hfocean.uavportal.web.user.service;

import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.core.common.service.BaseService;
import com.hfocean.uavportal.core.user.param.UserLoginParam;
import com.hfocean.uavportal.core.user.po.UserPersonalPO;
import com.hfocean.uavportal.web.user.vo.UserComVO;
import com.hfocean.uavportal.web.user.vo.UserPerVO;

public interface IUserService extends BaseService<UserPersonalPO,String> {
	
	UserPerVO userPerLogin(UserLoginParam param, String ip)throws Exception;
	
	UserComVO userComLogin(UserLoginParam param, String ip)throws Exception;
	
	WebResponse findUserInfo()throws Exception;
}

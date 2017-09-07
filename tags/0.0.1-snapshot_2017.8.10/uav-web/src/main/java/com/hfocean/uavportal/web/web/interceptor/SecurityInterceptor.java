package com.hfocean.uavportal.web.web.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;
import com.hfocean.common.exception.type.LoginException;
import com.hfocean.uavportal.core.annotation.Login;
import com.hfocean.uavportal.core.user.enm.UserEnum;
import com.hfocean.uavportal.core.user.po.UserCompanyPO;
import com.hfocean.uavportal.core.user.po.UserPersonalPO;
import com.hfocean.uavportal.core.user.repository.UserCompanyRepository;
import com.hfocean.uavportal.core.user.repository.UserPersonalRepository;
import com.hfocean.uavportal.core.user.session.WebSessionUser;
import com.hfocean.uavportal.web.util.WebAppContextHelper;

/**
 * 用户安全验证拦截器
 * @author Administrator
 *
 */
@Component
public class SecurityInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserPersonalRepository userPersonalRepository;
	
	@Autowired
	private UserCompanyRepository userCompanyRepository;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			// 用户权限拦截
			Login login = method.getMethodAnnotation(Login.class);
			if (login != null){
				//登录拦截
				loginValidate();
				//权限拦截
				if(login.authStatus())validateUserAuthStatus();
			}
		}
		return true;
	}

	private void loginValidate() {
		//验证登录
		if (WebAppContextHelper.getCurrentUser() == null) {
			throw new LoginException("请先登录");
		}
	}
	
	private void validateUserAuthStatus() {
		Integer authStatus = null;
		WebSessionUser<?> currentUser = WebAppContextHelper.getCurrentUser();
		if(currentUser!=null){
			String userId = currentUser.getUserId();
			Integer type = currentUser.getType();
			if(type==UserEnum.个人用户.getCode()){
				UserPersonalPO user = userPersonalRepository.findOne(userId);
				if(user!=null)
					authStatus = user.getAuthStatus();
			}else{
				UserCompanyPO user = userCompanyRepository.findOne(userId);
				if(user!=null)
					authStatus = user.getAuthStatus();
			}
		}
		if(authStatus==null||!(authStatus==UserEnum.已认证.getCode()))
			throw new BaseBusinessException(BaseBusinessError.USER_AUTHENTICATION_ERROR.getCode(), BaseBusinessError.USER_AUTHENTICATION_ERROR.getMessage());
	}
}


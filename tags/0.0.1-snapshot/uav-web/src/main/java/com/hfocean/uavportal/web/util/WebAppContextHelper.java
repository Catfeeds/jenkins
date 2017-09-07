package com.hfocean.uavportal.web.util;

import javax.servlet.http.HttpSession;

import com.hfocean.common.util.AppContextHelper;
import org.springframework.stereotype.Component;

import com.hfocean.uavportal.core.user.session.WebSessionUser;

/**
 * @author guan.sj
 */
@Component
public class WebAppContextHelper extends AppContextHelper {

	public static final String currentUserKey = "_web_currentUser";

	
	/**
	 * 退出登录
	 */
	public static void removeCurrentUser(){
		HttpSession session = getSession();
		session.removeAttribute(currentUserKey);
	}
	
	
	/**
	 * 获取当前session用户
	 * @return
	 */
	public static WebSessionUser<?> getCurrentUser(){
		HttpSession session = getSession();
		Object obj = session.getAttribute(currentUserKey);
		return null==obj?null:(WebSessionUser<?>)obj;
	}
	
	/**
	 * 设置当前session用户
	 * @param user
	 */
	public static void setCurrentUser(WebSessionUser<?> obj){
		HttpSession session = getSession();
		session.setAttribute(currentUserKey, obj);
	}
}

package com.hfocean.uavportal.weixin.util;

import javax.servlet.http.HttpSession;

import com.hfocean.common.util.AppContextHelper;
import org.springframework.stereotype.Component;

import com.hfocean.uavportal.core.user.session.WxSessionUser;

/**
 * @author guan.sj
 */
@Component
public class WechatAppContextHelper extends AppContextHelper {

	public static final String currentUserKey = "_wechat_currentUser";
	public static final String OpenIdKey = "_wechat_openId";

	
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
	public static WxSessionUser getCurrentUser(){
		HttpSession session = getSession();
		Object obj = session.getAttribute(currentUserKey);
		return null==obj?null:(WxSessionUser)obj;
	}
	
	/**
	 * 设置当前session用户
	 * @param user
	 */
	public static void setCurrentUser(WxSessionUser obj){
		HttpSession session = getSession();
		session.setAttribute(currentUserKey, obj);
	}
	
	/**
	 * 获取OpenId
	 * @return
	 */
	public static String getOpenId(){
		HttpSession session = getSession();
		Object obj = session.getAttribute(OpenIdKey);
		return null==obj?null:(String)obj;
	}
	
	/**
	 * 设置OpenId
	 * @param openId
	 */
	public static void setOpenId(String openId){
		HttpSession session = getSession();
		session.setAttribute(OpenIdKey, openId);
	}
}

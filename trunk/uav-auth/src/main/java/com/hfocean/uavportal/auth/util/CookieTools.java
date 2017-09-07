package com.hfocean.uavportal.auth.util;

import javax.servlet.http.Cookie;

import com.hfocean.uavportal.auth.base.utils.AuthAppContextHelper;

/**
 * 操作cookie工具类
 */
public class CookieTools {
	/**
	 * 添加Cookie
	 * @param cookieName 名称
	 * @param cookieValue 值
	 * @param maxAge 存活时间(按秒算)
	 */
	public static void addCookie(String cookieName, String cookieValue, int maxAge){
		/** 获取Cookie */
		Cookie cookie = getCookie(cookieName);
		if (cookie == null){
			cookie = new Cookie(cookieName, cookieValue);
		}else{
			cookie.setValue(cookieValue);
		}
		/** 设置有效时间 */
		cookie.setMaxAge(maxAge);
		/** http://localhost:8080/ 设置访问路径 */
		cookie.setPath("/");
		/** 添加cookie到用户浏览器 */
		AuthAppContextHelper.getResponse().addCookie(cookie);
	}
	
	/**
	 * 重新设置cookie的生命周期
	 * @param cookieName
	 * @param maxAge
	 */
	public static void expire(String cookieName,int maxAge){
		/** 获取Cookie */
		Cookie cookie = getCookie(cookieName);
		if (null!=cookie){
			cookie.setPath("/");
			/** 设置有效时间 */
			cookie.setMaxAge(maxAge);
			/** 添加cookie到用户浏览器 */
			AuthAppContextHelper.getResponse().addCookie(cookie);
		}
	}
	
	/**
	 * 获取Cookie
	 * @param cookieName 名称
	 * @return Cookie
	 */
	public static Cookie getCookie(String cookieName){
		/** 获取当前浏览器中所有的Cookie */
		Cookie[] cookies = AuthAppContextHelper.getRequest().getCookies();
		/** 判断 */
		if (cookies != null && cookies.length > 0){
			for (Cookie cookie : cookies){
				if (cookie.getName().equals(cookieName)){
					return cookie;
				}
			}
		}
		return null;
	}
	/**
	 * 删除Cookie
	 * @param cookieName 名称
	 */
	public static void removeCookie(String cookieName){
		/** 获取Cookie */
		Cookie cookie = getCookie(cookieName);
		if (cookie != null){
			cookie.setPath("/");
			cookie.setMaxAge(0); // 设置cookie立即失效
			AuthAppContextHelper.getResponse().addCookie(cookie);
		}
	}
}

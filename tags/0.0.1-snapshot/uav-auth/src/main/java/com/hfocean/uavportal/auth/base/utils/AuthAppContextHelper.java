package com.hfocean.uavportal.auth.base.utils;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hfocean.uavportal.auth.system.user.vo.SysUserVO;

@Component
public class AuthAppContextHelper implements ApplicationContextAware  {
	
	public static final String HAILE_A_KEY="HAILE_A_KEY";

	public static final String SYS_USER_SESSION = "_sys_user_session";//后台session key
	
	public static ApplicationContext springContext = null;
	
	public static HttpSession getSession(){
		return getRequest().getSession();
	}
	
	public static String getSysUserName(){
		SysUserVO sysUser = getSysUser();
		return sysUser==null?null:sysUser.getUserName();
	}
	
	
	public static HttpServletResponse getResponse(){
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		return sra.getResponse();
	}
	
	public static HttpServletRequest getRequest(){
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		return sra.getRequest();
	}
	

	/**
	 * 获取bean实例对象
	 * @param beanName
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(String beanName,Class<T> clazz){
		return springContext.getBean(beanName, clazz);
	}
	
	public static <T> T getBean(Class<T> clazz){
		return springContext.getBean(clazz);
	}
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		springContext = context;
	}


	public static void setSysUser(SysUserVO user) {
		getSession().setAttribute(SYS_USER_SESSION, user);
	}
	
	
	public static SysUserVO getSysUser(){
		Object obj = getSession().getAttribute(SYS_USER_SESSION);
		return obj==null?null:(SysUserVO)obj;
	}
	
//	public static void isLogin(){
//		if(getSysUser()==null) throw new LoginException(ConstantHelper.LOGIN_MESSAGE);
//	}
	
	public static void removeSysUser(){
		getSession().removeAttribute(SYS_USER_SESSION);
	}
	
	
	public static boolean isSupperAdmin(SysUserVO user){
		return user!=null?(user.getType()==ConstantHelper.SUPERADMIN_USER?true:false):false;
	}
	
	public static boolean isSupperAdmin(String userName){
		return userName!=null?(userName.equals(ConstantHelper.SUPER_ADMIN_NAME)?true:false):false;
	}
	
	
	
	public static <T> Map<String, T> getBeans(Class<T> clazz){
		return BeanFactoryUtils.beansOfTypeIncludingAncestors(springContext,clazz);
	}
	
	
	public static <T> Collection<T> getBeanCollections(Class<T> clazz){
		Map<String, T> map = getBeans(clazz);
		if(!map.isEmpty()){
			return  map.values();
		}
		return null;
	}
	
	
	
	
	
	
	

}

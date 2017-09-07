package com.hfocean.uavportal.auth.base.utils;

public class ConstantHelper {
	
	public final static String SUPER_ADMIN_NAME = PropertiesHelper.getValue("admin_name");
	public final static String SUPER_ADMIN_PASSWORD = PropertiesHelper.getValue("admin_password");
	
	public final static String ADMIN_NICK_NAME = "超级管理员";
	public final static int AVAILABLE = 1;//可用
	public final static int UNAVAILABLE = 0;//不可用
	public final static int COMMON_USER = 0;
	public final static int ADMIN_USER = 1;
	public final static int SUPERADMIN_USER = 2;

	public final static String FORBIDDEN_MESSAGE="你没有权限";
	public final static String FORBIDDEN_PERMISSION_MESSAGE="你没有%s权限";
	public final static String FORBIDDEN_USER_MESSAGE="只允许%s或以上级别的用户访问";
	public final static String LOGIN_MESSAGE="请先登录";
	public final static String LOGIN_MESSAGE_RE="您的账号在其它地方登录或被禁用，请重新登录";
	public final static String LOGIN_ERROR="用户名或密码错误";
	
	
	//area display
	public final static int TYPEOPTION_MODE = 1;//模式
	public final static int TYPEOPTION_SOIL = 2;//土壤信息
	public final static int TYPEOPTION_WEATHER = 3;//日+年气候信息
	public final static int CODE_NO_LOGIN = -1;//未登录
	public final static int CODE_NO_PERMISSION = -2;//无权限
	public final static int CODE_NO_DISPLAY = -3;//未选择查询条件
	public final static int CODE_NORMAL = 0;//正常
	//area display
	

}

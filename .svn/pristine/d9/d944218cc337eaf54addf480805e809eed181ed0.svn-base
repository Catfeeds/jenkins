package com.hfocean.common.exception;

import java.util.Locale;

/**
 * 基础错误接口默认实现
 * @author Administrator
 *
 */
public enum BaseBusinessError implements IBaseBusinessError{

	INTER_ERROR(500,"系统内部发生错误"),
	
	NOT_FOUND(404,"没有找到内容"),
	
	PARAMETER_ERROR(400, "请求参数缺失或格式不正确"),
	
	UNAUTHORIZED(401,"用户未登录"),
	USER_LOGIN_ERROR(401001,"用户名或密码错误"),
	USER_AUTHENTICATION_ERROR(401002,"用户未通过认证"),
	USER_FREEZE(401003,"用户被冻洁"),
	CODE_VERIFY_ERROR(401090,"验证码错误"),
	SMS_CODE_ERROR(401091,"短信验证码错误"),
	VERIFY_NOT_CODE(401092,"验证码超时,请重新获取"),
	
	RESET_PWD_ERROR(401010,"重置密码失败"),
	OLD_PWD_ERROR(401011,"旧密码错误"),
	
	
	FORBIDDEN(403,"权限不足"),
	
	VERIFY_CODE_TIME(403,"1分钟后才能获取验证码"),
	VERIFY_CODE_NUMBER(403,"验证错误次数太多，请重新获取验证码"),
	SEND_CODE_ERROR(403,"获取验证码次数过多"),
	CODE_EXIST_PHONE(403001,"该手机号码已注册"),
	CODE_NOT_PHONE(403002,"该手机号码未注册"),
	
	USER_ALREADY_EXISTS(409,"用户名已经存在"),
	PHONE_ALREADY_EXISTS(409001,"手机号码已经存在"),
	USER_NOT_EXISTS(409002,"用户不存在"),
	WECHAT_USER_EXISTS(409003,"微信openId已经绑定用户"),
	WECHAT_PHONE_EXISTS(409004,"该手机号已经绑定其他微信用户"),
	SAME_PHONE_NAME(409005,"紧急联系人信息不能与用户手机号或姓名相同"),
	IDCARD_ALREADY_EXISTS(409006,"身份证已被注册"),
	COMPANYNAME_ALREADY_EXISTS(409007,"单位/企业名称已被注册"),
	LICENSENUMBER_ALREADY_EXISTS(409008,"社会信用代码已被注册")
	
	;
	
	int code;
	
	String message;
	
	private BaseBusinessError(int code,String message){
		this.code = code;
		this.message = message;
	}
	
	
	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String getMessage(Locale locale) {
		return message;
	}

}

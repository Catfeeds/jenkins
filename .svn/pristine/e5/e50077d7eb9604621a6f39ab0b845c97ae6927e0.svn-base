package com.hfocean.common.validation.custom;

/**
 * 正则表达式常量
 * @author guan.sj
 */
public interface ConstantRegex {
	/** 手机正则表达式
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 
     * 此方法中前三位格式有： 
     * 13+任意数 
     * 15+除4的任意数 
     * 18+除4的任意数 
     * 17+除9的任意数 
     * 147 
     */
    String PHONE = "^((13[0-9])|(15[^4])|(18[^4])|(17[0-8])|(147))\\d{8}$";
    String PHONE_MSG = "手机号码格式错误";
	
    /**
     * 邮箱验证
     */
    String EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    String EMAIL_MSG = "邮箱格式错误";
	
}

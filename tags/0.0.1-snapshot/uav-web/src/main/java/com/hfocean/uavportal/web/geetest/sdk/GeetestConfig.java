package com.hfocean.uavportal.web.geetest.sdk;

/**
 * GeetestWeb配置文件
 * 
 *
 */
public class GeetestConfig {

	// 填入自己的captcha_id和private_key
	private static final String geetest_id = "833d8bf3816c9732bf3e1ee9389f2225";
	private static final String geetest_key = "ba14ce814038e7ad3ce468e2edbec0bc";
	private static final boolean newfailback = true;
	public  static final String SESSION_KEY = "_geetest";
	public  static final String SESSION_VALUE = "_uvaportal";

	public static final String getGeetest_id() {
		return geetest_id;
	}

	public static final String getGeetest_key() {
		return geetest_key;
	}
	
	public static final boolean isnewfailback() {
		return newfailback;
	}

}

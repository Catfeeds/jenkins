package com.hfocean.common.web;

/**
 * 通用返回结果类
 * @author Gene
 *
 */
public class WebResponse {

	//状态码
	private int code = 0;
	
	//返回信息
	private String message = "";
	
	//返回数据
	private Object data = null;

	public WebResponse() {
	}

	public WebResponse(int code, String message){
		this.code = code;
		this.message = message;
	}
	
	public WebResponse(Object data){
		this.data = data;
	}
	
	public WebResponse(int code, String message,Object data){
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}

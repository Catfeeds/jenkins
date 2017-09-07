package com.hfocean.uavportal.console.system.exception;

import com.hfocean.common.exception.BaseBusinessException;

/**
 * 业务异常
 * 
 */
public class BusinessException extends BaseBusinessException {
	
	private static final long serialVersionUID = 1L;
	
	private final static int DEFAULT_CODE = 599;
	
	private final static String DEFAULT_MSG = "业务异常";

	public BusinessException() {
		super(DEFAULT_CODE, DEFAULT_MSG);
	}

	public BusinessException(String msg) {
		super(DEFAULT_CODE, msg);
	}
	
	public BusinessException(int code, String msg) {
		super(code, msg);
	}

}

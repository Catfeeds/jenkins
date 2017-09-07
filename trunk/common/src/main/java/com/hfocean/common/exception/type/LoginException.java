package com.hfocean.common.exception.type;

import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;

/**
 * 登录异常
 * 
 */
public class LoginException extends BaseBusinessException {
	
	private static final long serialVersionUID = 1L;
	
	public LoginException() {
		super(BaseBusinessError.UNAUTHORIZED.getCode(), BaseBusinessError.UNAUTHORIZED.getMessage());
	}

	public LoginException(String msg) {
		super(BaseBusinessError.UNAUTHORIZED.getCode(), msg);
	}
	
}

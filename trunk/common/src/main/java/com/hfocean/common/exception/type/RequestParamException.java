package com.hfocean.common.exception.type;

import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;

/**
 * 请求参数异常
 * 
 */
public class RequestParamException extends BaseBusinessException {
	
	private static final long serialVersionUID = 1L;
	
	public RequestParamException() {
		super(BaseBusinessError.PARAMETER_ERROR.getCode(), BaseBusinessError.PARAMETER_ERROR.getMessage());
	}

	public RequestParamException(String msg) {
		super(BaseBusinessError.PARAMETER_ERROR.getCode(), msg);
	}
	
	

}

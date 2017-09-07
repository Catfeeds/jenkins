package com.hfocean.common.notify;

import java.io.Serializable;


public interface NotifyManager extends Serializable {
	

	/**
	 * 具体推送实现
	 */
	String doNotify() throws Exception;
	
}

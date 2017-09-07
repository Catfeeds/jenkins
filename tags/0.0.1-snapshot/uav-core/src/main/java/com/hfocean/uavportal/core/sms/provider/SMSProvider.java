package com.hfocean.uavportal.core.sms.provider;


import com.hfocean.common.notify.NotifyManager;
import com.hfocean.uavportal.core.sms.service.ISmsService;
import com.hfocean.common.util.AppContextHelper;

/**
 * <p>Title: SMSProvider</p>
 * <p>Description: 阿里大于短信</p>
 * @author guan.sj
 * @since 2017年4月13日
 */
public class SMSProvider implements NotifyManager {

	private static ISmsService iSmsService= AppContextHelper.getBean(ISmsService.class); 

	private static final long serialVersionUID = 1L;
	
	private String phone;
	private String stemp;
	private String[] params;
	
	public SMSProvider(String phone,String stemp,String... params){
		this.phone=phone;
		this.stemp=stemp;
		this.params=params;
	}
	
	@Override
	public String doNotify() throws Exception {
		return String.valueOf(iSmsService.sendSMS(phone,stemp,params));
	}

}

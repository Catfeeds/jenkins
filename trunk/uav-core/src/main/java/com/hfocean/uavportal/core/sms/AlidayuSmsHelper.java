package com.hfocean.uavportal.core.sms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Map;


/**
 * Oss帮助类
 * @author guang.sj
 *
 */
public class AlidayuSmsHelper {

	//oss配置类
	private AlidayuProperties alidayuProperties;

	private final Logger logger = LoggerFactory.getLogger(AlidayuSmsHelper.class);
	

	public AlidayuSmsHelper(AlidayuProperties alidayuProperties){
		Assert.notNull(alidayuProperties, "alidayuProperties must be null");
		this.alidayuProperties = alidayuProperties;
	}

	/**
	 * 发送短信
	 * @param templateId
	 *     模板ID
	 * @param phones
	 *     手机号码
	 * @param paramMap
	 * @return
	 */
	public int sentSMS(String templateId, String phones, Map<String,String> paramMap) {
		TaobaoClient client = new DefaultTaobaoClient(alidayuProperties.getUrl(), alidayuProperties.getAppkey(), alidayuProperties.getAppsecret());
		
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend(alidayuProperties.getExtend());
		req.setSmsType(alidayuProperties.getType());
		req.setSmsFreeSignName(alidayuProperties.getSignname());
		ObjectMapper om = new ObjectMapper();
		String paramJson;
		try {
			paramJson = om.writeValueAsString(paramMap);
			req.setSmsParamString(paramJson);
			req.setRecNum(phones);
			req.setSmsTemplateCode(templateId);
		    AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		    logger.info(rsp.getBody());
		    return rsp.isSuccess()==true?1:0;
		} catch (Exception e) {
		    return 0;  
		}  
	}
    

}

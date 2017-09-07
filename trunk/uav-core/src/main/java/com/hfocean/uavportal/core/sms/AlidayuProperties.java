package com.hfocean.uavportal.core.sms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里大于SMS配置类
 * @author Gene
 *
 */
@Configuration
@ConfigurationProperties(prefix="sms.alidayu")
@ConditionalOnProperty(prefix="sms.alidayu",value="signname")
public class AlidayuProperties {

	//API 访问地址
	@NotNull
    @Valid
	private String url;
	
	//appkey
	@NotNull
    @Valid
	private String appkey;
	
	//appsecret
	@NotNull
    @Valid
	private String appsecret;
	
	//签名
	@NotNull
    @Valid
	private String signname;
	
	//默认123456
	private String extend="123456";
	
	//默认normal
	private String type="normal";
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getSignname() {
		return signname;
	}

	public void setSignname(String signname) {
		this.signname = signname;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

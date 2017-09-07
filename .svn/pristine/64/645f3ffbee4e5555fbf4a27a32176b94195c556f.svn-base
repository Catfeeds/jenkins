package com.hfocean.uavportal.core.oss;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * OSS配置类
 * @author Gene
 *
 */
@Configuration
@ConfigurationProperties(prefix="oss")
@ConditionalOnProperty(prefix="oss",value="endpoint")
public class OssProperties {

	//请求路径
	private String endpoint;
	
	//准入ID
	private String accessId;
	
	//准 入KEY
	private String accessKey;
	
	//上下文名称 
	private String bucket;

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getAccessId() {
		return accessId;
	}

	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

}

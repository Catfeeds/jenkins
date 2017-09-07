package com.hfocean.uavportal.core.sms;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Oss自动配置类，如果带有OssProperties，再初始化OssHelper
 * @author Gene
 *
 */
@Configuration
@ConditionalOnBean(AlidayuProperties.class)
public class AlidayuAutoConfiguration {

	@Bean
	public AlidayuSmsHelper alidayuSmsHelper(AlidayuProperties ossProperties){
		return new AlidayuSmsHelper(ossProperties);
	}
	
}

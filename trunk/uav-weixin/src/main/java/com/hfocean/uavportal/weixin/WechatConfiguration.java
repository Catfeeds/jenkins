package com.hfocean.uavportal.weixin;

import com.hfocean.uavportal.weixin.web.interceptor.WeixinSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 主配置类
 * @author Administrator
 *
 */
@Configuration
@EntityScan
@ComponentScan
@EnableJpaRepositories
public class WechatConfiguration extends WebMvcConfigurerAdapter{
	
	@Autowired
	WeixinSecurityInterceptor securityInterceptor; 

	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(securityInterceptor)
		.addPathPatterns("/**");
	}

}

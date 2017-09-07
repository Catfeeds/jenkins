package com.hfocean.uavportal.web;

import com.hfocean.uavportal.web.web.interceptor.SecurityInterceptor;
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
@ComponentScan
@EntityScan
@EnableJpaRepositories
public class WebConfiguration extends WebMvcConfigurerAdapter{
	
	@Autowired
	SecurityInterceptor securityInterceptor; 

	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(securityInterceptor)
		.addPathPatterns("/**");
	}

}

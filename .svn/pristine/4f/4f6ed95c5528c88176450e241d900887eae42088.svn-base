package com.hfocean.uavportal.console;

import com.hfocean.uavportal.console.system.resolver.AnnotationParameterResolver;
import com.hfocean.uavportal.console.web.interceptor.SecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * 主配置类
 * @author Administrator
 *
 */
@Configuration
@EntityScan
@ComponentScan
@EnableJpaRepositories
@PropertySource("classpath:wechatImgText.properties")
public class ConsoleConfiguration extends WebMvcConfigurerAdapter{

	
	@Autowired
	SecurityInterceptor securityInterceptor; 

	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(securityInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("auth/login");
	}
	
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new AnnotationParameterResolver());
    }

}

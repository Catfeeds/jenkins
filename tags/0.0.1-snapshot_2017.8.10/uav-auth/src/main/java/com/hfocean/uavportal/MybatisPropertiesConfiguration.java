package com.hfocean.uavportal;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * MyBatis配置类
 * @author Gene
 *
 */
@Configuration
@AutoConfigureBefore(MybatisAutoConfiguration.class)
@PropertySource("classpath:mybatis.properties")
public class MybatisPropertiesConfiguration {
	
	
}

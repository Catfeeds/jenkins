package com.hfocean.uavportal.auth.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(java.lang.annotation.ElementType.FIELD )    
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)    
@Documented
public @interface FullPath {

	//path 前缀
	public String prefix() default "https://haiyuexing.oss-cn-shenzhen.aliyuncs.com/";
	
	//关联字段
	public String ref();
	
	//oss 样式 包含分隔符 @!
	public String size() default "";
}

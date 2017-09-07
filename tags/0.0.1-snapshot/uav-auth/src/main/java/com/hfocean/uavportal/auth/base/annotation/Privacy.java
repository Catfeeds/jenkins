package com.hfocean.uavportal.auth.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 关键信息隐藏
 * 
 * @author canjie.mo 
 * @since 2016年9月22日
 */

@Target(java.lang.annotation.ElementType.FIELD )    
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)    
@Documented
public @interface Privacy {

	//显示
	String display() default "*";
	//开始位置 
	int beginIndex() default 1;//第二位开始
	//结束位置
	int endIndex() default -1;
	
}

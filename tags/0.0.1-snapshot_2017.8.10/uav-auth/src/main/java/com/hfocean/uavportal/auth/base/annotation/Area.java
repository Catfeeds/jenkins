package com.hfocean.uavportal.auth.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 省市区
 * @author canjie.mo
 *
 * @since 2016年9月26日 下午5:48:10
 */
@Target(java.lang.annotation.ElementType.FIELD )    
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)    
@Documented
public @interface Area {
	public String ref();
}

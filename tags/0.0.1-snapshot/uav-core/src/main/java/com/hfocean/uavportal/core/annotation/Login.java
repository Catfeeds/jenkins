package com.hfocean.uavportal.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(java.lang.annotation.ElementType.METHOD )    
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)    
@Documented
public @interface Login {
	boolean authStatus() default false;//用户认证权限
}

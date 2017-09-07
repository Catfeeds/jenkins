package com.hfocean.uavportal.auth.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Target(java.lang.annotation.ElementType.FIELD )    
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)    
@Documented
public @interface Dict {

	public String typeName() default "";
	
	public String ref() default "";
	
	public boolean children() default false;
	
}

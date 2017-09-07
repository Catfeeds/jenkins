package com.hfocean.uavportal.auth.base.annotation;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface MjPattern {
	//^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$
	String regexp();
	String message() default "";
}

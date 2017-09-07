package com.hfocean.uavportal.auth.system.permission.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.hfocean.uavportal.auth.system.permission.eumn.PermissionFieldTypeEumn;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface MjConvertPermission {

	public String key();
	
	public PermissionFieldTypeEumn type();
	
}

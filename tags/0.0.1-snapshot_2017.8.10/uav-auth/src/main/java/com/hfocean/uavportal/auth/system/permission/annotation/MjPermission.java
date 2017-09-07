package com.hfocean.uavportal.auth.system.permission.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.hfocean.uavportal.auth.system.permission.eumn.PermissionEumn;
import com.hfocean.uavportal.auth.system.user.eumn.UserType;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface MjPermission {
	PermissionEumn[] values() default {};
	UserType[] userTypes() default {};
}

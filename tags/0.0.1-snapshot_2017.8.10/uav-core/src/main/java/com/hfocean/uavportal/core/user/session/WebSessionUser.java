package com.hfocean.uavportal.core.user.session;

public class WebSessionUser<T> extends BaseSessionUser {
	private static final long serialVersionUID = 1L;
	private T object;
	public T getObject() {
		return object;
	}
	public void setObject(T object) {
		this.object = object;
	}
}

package com.hfocean.uavportal.core.user.vo;

public class AuditSeasonVO<T> {
	private Integer authStatus;
	private String reason;
	private T authInfo;
	public T getAuthInfo() {
		return authInfo;
	}
	public void setAuthInfo(T authInfo) {
		this.authInfo = authInfo;
	}
	public Integer getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}

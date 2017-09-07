package com.hfocean.uavportal.console.user.param;

public class UserListParam {
	private String name;//个人列表用-姓名
	private String companyName;//企业列表用-公司名
	private String userName;
	private Integer[] status;
	private Integer[] authStatus;
	private String startDate;//创建开始日期 格式：yyyy-MM-dd
    private String endDate;//创建日期 格式：yyyy-MM-dd
    
    
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer[] getStatus() {
		return status;
	}
	public void setStatus(Integer[] status) {
		this.status = status;
	}
	public Integer[] getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(Integer[] authStatus) {
		this.authStatus = authStatus;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}

package com.hfocean.uavportal.console.user.param;

public class UserPerExcelParam {
	//like
	private String userName;
	private String name;
	private String idCard;
//	private String certificateNumber;
	private String qq;
	private String email;
	private String phone;
	private String address;
	private String emergencyContactName;
	private String emergencyContactPhone;
//	private String openId;
	private String lastLoginIp;
	//equels
	private String sex;
	private Integer status;
	private Integer authStatus;
//	private Integer certificateType;
	//time
	private String birthdayStart;
	private String birthdayEnd;
	private String createTimeStart;
	private String createTimeEnd;
	private String lastLoginTimeStart;
	private String lastLoginTimeEnd;
	
	
	public String getLastLoginTimeStart() {
		return lastLoginTimeStart;
	}
	public void setLastLoginTimeStart(String lastLoginTimeStart) {
		this.lastLoginTimeStart = lastLoginTimeStart;
	}
	public String getLastLoginTimeEnd() {
		return lastLoginTimeEnd;
	}
	public void setLastLoginTimeEnd(String lastLoginTimeEnd) {
		this.lastLoginTimeEnd = lastLoginTimeEnd;
	}
	public String getUserName() {
		return userName;
	}
	public String getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public String getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public String getBirthdayStart() {
		return birthdayStart;
	}
	public void setBirthdayStart(String birthdayStart) {
		this.birthdayStart = birthdayStart;
	}
	public String getBirthdayEnd() {
		return birthdayEnd;
	}
	public void setBirthdayEnd(String birthdayEnd) {
		this.birthdayEnd = birthdayEnd;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmergencyContactName() {
		return emergencyContactName;
	}
	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}
	public String getEmergencyContactPhone() {
		return emergencyContactPhone;
	}
	public void setEmergencyContactPhone(String emergencyContactPhone) {
		this.emergencyContactPhone = emergencyContactPhone;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}
	
	

}

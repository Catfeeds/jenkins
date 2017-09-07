package com.hfocean.uavportal.weixin.user.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author guan.sj
 */
public class UserComVO {
	private String userName;//用户名
	private Integer status;//用户状态
	private Integer authStatus;//认证状态
	private String companyName;//公司名
	private String companyAddress;//公司地址
	private String email;//邮箱
	private String contactName;//联系人
	private String contactPhone;//联系人手机
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTime;//创建时间 
	private String headPic;//头像
	private Integer flyTimes=0;//飞行次数
	private Integer flyHour=0;//飞行时间（小时）
	private Integer flyOvertime=0;//飞行违规次数
	private String licenseNumber;
	private String licensePic;
	private String companyLegal;
	private String emergencyContactName;
	private String emergencyContactPhone;
	private String openId;
	private Integer type;//1个人2企业
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public String getLicensePic() {
		return licensePic;
	}
	public void setLicensePic(String licensePic) {
		this.licensePic = licensePic;
	}
	public String getCompanyLegal() {
		return companyLegal;
	}
	public void setCompanyLegal(String companyLegal) {
		this.companyLegal = companyLegal;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	public Integer getFlyTimes() {
		return flyTimes;
	}
	public void setFlyTimes(Integer flyTimes) {
		this.flyTimes = flyTimes;
	}
	public Integer getFlyHour() {
		return flyHour;
	}
	public void setFlyHour(Integer flyHour) {
		this.flyHour = flyHour;
	}
	public Integer getFlyOvertime() {
		return flyOvertime;
	}
	public void setFlyOvertime(Integer flyOvertime) {
		this.flyOvertime = flyOvertime;
	}


}

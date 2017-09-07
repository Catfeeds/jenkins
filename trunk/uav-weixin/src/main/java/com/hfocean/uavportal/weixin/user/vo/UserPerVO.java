package com.hfocean.uavportal.weixin.user.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author guan.sj
 */
public class UserPerVO {
	
	private String userName;//用户名
	private Integer status;//用户状态
	private Integer authStatus;//认证状态
	private String name;//姓名	
	private String headPic;//头像
	private String sex;//姓别
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTime;//创建时间

	private String idCard;

	private String idcardPic1;

	private String idcardPic2;
	
	private String idcardPic3;

	private Integer certificateType;

	private String certificateNumber;

	private String qq;

	private String email;

	private String phone;

	private String address;

	private String emergencyContactName;

	private String emergencyContactPhone;
	
	private Integer flyTimes=0;//飞行次数
	private Integer flyHour=0;//飞行时间（小时）
	private Integer flyOvertime=0;//飞行违规次数
	private String openId;
	private Integer type;//1个人，2企业
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
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
	public String getIdcardPic3() {
		return idcardPic3;
	}
	public void setIdcardPic3(String idcardPic3) {
		this.idcardPic3 = idcardPic3;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getIdcardPic1() {
		return idcardPic1;
	}
	public void setIdcardPic1(String idcardPic1) {
		this.idcardPic1 = idcardPic1;
	}
	public String getIdcardPic2() {
		return idcardPic2;
	}
	public void setIdcardPic2(String idcardPic2) {
		this.idcardPic2 = idcardPic2;
	}
	public Integer getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}
	public String getCertificateNumber() {
		return certificateNumber;
	}
	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	

}

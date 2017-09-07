package com.hfocean.uavportal.core.user.param;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.hfocean.common.validation.custom.ConstantRegex;
import com.hfocean.common.validation.custom.IdCard;
import com.hfocean.uavportal.core.common.constant.Constant;

/**
 * @author guan.sj
 */
public class UserPerRegisterParam {
	
	@Pattern(regexp=Constant.REG_USER_NAME,message=Constant.REG_USER_NAME_MSG)
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String userName;
	
	@Pattern(regexp=Constant.REG_PASSWORD,message=Constant.REG_PASSWORD_MSG)
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String password;
	
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String name;
	
//	private String sex;
	
	@NotBlank(message=Constant.DEFAULT_MSG)
	@IdCard
	private String idCard;
	
//	@NotBlank(message=Constant.DEFAULT_MSG)
	private String idcardPic1;
	
//	@NotBlank(message=Constant.DEFAULT_MSG)
	private String idcardPic2;
	
//	@NotBlank(message=Constant.DEFAULT_MSG)
	private String idcardPic3;
	
	private Integer certificateType;
	private String certificateNumber;
	private String qq;
	
	@Pattern(regexp=ConstantRegex.EMAIL,message=ConstantRegex.EMAIL_MSG)
	private String email;
	
	@Pattern(regexp=ConstantRegex.PHONE,message=ConstantRegex.PHONE_MSG)
	private String phone;//用验证手机号码拿
	private String address;
	
	private String emergencyContactName;
	
	@Pattern(regexp=ConstantRegex.PHONE,message=ConstantRegex.PHONE_MSG)
	private String emergencyContactPhone;
	
//	private String birthday;//出生日期
	
	
	
//	public String getBirthday() {
//		return birthday;
//	}
//	public void setBirthday(String birthday) {
//		this.birthday = birthday;
//	}
	public String getIdcardPic3() {
		return idcardPic3;
	}
	public void setIdcardPic3(String idcardPic3) {
		this.idcardPic3 = idcardPic3;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public String getSex() {
//		return sex;
//	}
//	public void setSex(String sex) {
//		this.sex = sex;
//	}
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
	
	
	

}

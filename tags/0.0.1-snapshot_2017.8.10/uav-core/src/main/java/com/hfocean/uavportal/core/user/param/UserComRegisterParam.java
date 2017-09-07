package com.hfocean.uavportal.core.user.param;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.hfocean.common.validation.custom.ConstantRegex;
import com.hfocean.common.validation.custom.CreditCode;
import com.hfocean.uavportal.core.common.constant.Constant;

/**
 * @author guan.sj
 */
public class UserComRegisterParam {
	
	@Pattern(regexp=Constant.REG_USER_NAME,message=Constant.REG_USER_NAME_MSG)
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String userName;
	
	@Pattern(regexp=Constant.REG_PASSWORD,message=Constant.REG_PASSWORD_MSG)
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String password;
	
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String companyName;
	
	@NotBlank(message=Constant.DEFAULT_MSG)
	@CreditCode
	private String licenseNumber;
	
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String licensePic;
	
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String companyAddress;
	
	@Pattern(regexp=ConstantRegex.EMAIL,message=ConstantRegex.EMAIL_MSG)
	private String email;
	
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String contactName;
	
	@Pattern(regexp=ConstantRegex.PHONE,message=ConstantRegex.PHONE_MSG)
	private String contactPhone;//用验证手机号码拿
	
	private String companyLegal;
	
	private String emergencyContactName;
	
	@Pattern(regexp=ConstantRegex.PHONE,message=ConstantRegex.PHONE_MSG)
	private String emergencyContactPhone;
	
	
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	
	

}

package com.hfocean.uavportal.core.user.param;

import org.hibernate.validator.constraints.NotBlank;

import com.hfocean.common.validation.custom.CreditCode;
import com.hfocean.common.validation.custom.First;
import com.hfocean.common.validation.custom.Second;
import com.hfocean.uavportal.core.common.constant.Constant;

/**
 * @author guan.sj
 */
public class UpdateUserComParam {
	//认证后不可修改的字段
	@NotBlank(message=Constant.DEFAULT_MSG,groups={Second.class})
	private String companyName;
	@NotBlank(message=Constant.DEFAULT_MSG,groups={Second.class})
	@CreditCode
	private String licenseNumber;
	@NotBlank(message=Constant.DEFAULT_MSG,groups={Second.class})
	private String licensePic;
	
	//添加的字段
	@NotBlank(message=Constant.DEFAULT_MSG,groups={Second.class})
	private String contactName;
	//
	
	
	@NotBlank(message=Constant.DEFAULT_MSG,groups={First.class})
	private String companyAddress;
	
	@NotBlank(message=Constant.DEFAULT_MSG,groups={First.class})
	private String email;
	
	private String companyLegal;
	
	private String emergencyContactName;
	
	private String emergencyContactPhone;
	
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

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
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
	
}

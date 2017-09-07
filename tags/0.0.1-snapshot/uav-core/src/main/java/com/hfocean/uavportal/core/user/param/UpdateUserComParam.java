package com.hfocean.uavportal.core.user.param;

import org.hibernate.validator.constraints.NotBlank;

import com.hfocean.uavportal.core.common.constant.Constant;

/**
 * @author guan.sj
 */
public class UpdateUserComParam {
	
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String companyAddress;
	
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String email;
	
	private String companyLegal;
	
	private String emergencyContactName;
	
	private String emergencyContactPhone;

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

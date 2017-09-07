package com.hfocean.uavportal.core.user.param;

import org.hibernate.validator.constraints.NotBlank;

import com.hfocean.common.validation.custom.CreditCode;
import com.hfocean.uavportal.core.common.constant.Constant;

public class UserComUpdateAuthParam {
	//企业
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String companyName;//公司名
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String companyLegal;
	@NotBlank(message=Constant.DEFAULT_MSG)
	@CreditCode
	private String licenseNumber;
	@NotBlank(message=Constant.DEFAULT_MSG)
    private String licensePic;
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyLegal() {
		return companyLegal;
	}
	public void setCompanyLegal(String companyLegal) {
		this.companyLegal = companyLegal;
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
}

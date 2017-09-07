package com.hfocean.uavportal.core.user.vo;

public class ComAuditInfoVO {
	private String companyName;//公司名
	private String companyLegal;
	private String licenseNumber;
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

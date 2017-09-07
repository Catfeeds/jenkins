package com.hfocean.uavportal.core.user.param;

import org.hibernate.validator.constraints.NotBlank;

import com.hfocean.common.validation.custom.First;
import com.hfocean.common.validation.custom.IdCard;
import com.hfocean.common.validation.custom.Second;
import com.hfocean.uavportal.core.common.constant.Constant;

/**
 * @author guan.sj
 */
public class UpdateUserPerParam {
	//认证后不可修改的字段
	@IdCard(groups={Second.class})
	private String idCard;
	@NotBlank(message=Constant.DEFAULT_MSG,groups={Second.class})
	private String idcardPic1;
	@NotBlank(message=Constant.DEFAULT_MSG,groups={Second.class})
	private String idcardPic2;
	@NotBlank(message=Constant.DEFAULT_MSG,groups={Second.class})
	private String idcardPic3;
	@NotBlank(message=Constant.DEFAULT_MSG,groups={Second.class})
	private String birthday;//yyyy-MM-dd
	@NotBlank(message=Constant.DEFAULT_MSG,groups={Second.class})
	private String sex;//姓别
	@NotBlank(message=Constant.DEFAULT_MSG,groups={Second.class})
	private String name;//真实姓名
	
	//
	private Integer certificateType;//证书类型
	private String certificateNumber;//证书编号
	private String qq;//
	@NotBlank(message=Constant.DEFAULT_MSG,groups={First.class})
	private String email;//
	private String address;//
	@NotBlank(message=Constant.DEFAULT_MSG,groups={First.class})
	private String emergencyContactName;//紧急联系人姓名
	@NotBlank(message=Constant.DEFAULT_MSG,groups={First.class})
	private String emergencyContactPhone;//紧急联系人手机号码
	
	
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
	public String getIdcardPic3() {
		return idcardPic3;
	}
	public void setIdcardPic3(String idcardPic3) {
		this.idcardPic3 = idcardPic3;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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

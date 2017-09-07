package com.hfocean.uavportal.core.user.param;

import org.hibernate.validator.constraints.NotBlank;

import com.hfocean.common.validation.custom.IdCard;
import com.hfocean.uavportal.core.common.constant.Constant;

public class UserPerUpdateAuthParam {
	//个人
	@IdCard
	private String idCard;
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String idcardPic1;
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String idcardPic2;
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String idcardPic3;
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String birthday;//yyyy-MM-dd
	@NotBlank(message=Constant.DEFAULT_MSG)
	private String sex;//姓别
	
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
}

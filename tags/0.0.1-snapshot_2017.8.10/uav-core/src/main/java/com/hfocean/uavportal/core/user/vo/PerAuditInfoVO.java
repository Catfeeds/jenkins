package com.hfocean.uavportal.core.user.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PerAuditInfoVO {
	private String idCard;
	private String idcardPic1;
	private String idcardPic2;
	private String idcardPic3;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}

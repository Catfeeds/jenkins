package com.hfocean.uavportal.weixin.bean;

import java.io.Serializable;
import java.util.Date;

public class VerificationVO implements Serializable {
	
	private static final long serialVersionUID = -5748045329973298785L;
	
	private Date creatDate;   //创建时间
	private Date expirationDate;  //过期时间
	private Integer validity;   //有效期
	
	public Date getCreatDate() {
		return creatDate;
	}
	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public Integer getValidity() {
		return validity;
	}
	public void setValidity(Integer validity) {
		this.validity = validity;
	}
	
	

}

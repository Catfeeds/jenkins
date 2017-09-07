package com.hfocean.uavportal.weixin.bean;

import java.io.Serializable;

/**
 * @author jw.lin
 * 2016年08月18日
 * 微信公众号ticket，有效期两个小时
 */
public class TicketVO extends VerificationVO implements Serializable {
	
	private static final long serialVersionUID = -6372254357245151066L;
	
	private String ticket;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
}

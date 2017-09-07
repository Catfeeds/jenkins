package com.hfocean.uavportal.auth.fishsea.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_sys_user")
public class SysUserPO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_name")
	private String userName;// 账号

	private String password;// 密码

	@Column(name = "nick_name")
	private String nickName;// 昵称

	private String email;// 电子邮件

	private String creater;// 创建者

	@Column(name="create_time")
	private Date createTime;// 创建时间
	
	private String phone;

	private Integer status;// 状态 1正常 0不可用

	public SysUserPO() {
	}

	public SysUserPO(Integer status) {
		this.status = status;
	}

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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}

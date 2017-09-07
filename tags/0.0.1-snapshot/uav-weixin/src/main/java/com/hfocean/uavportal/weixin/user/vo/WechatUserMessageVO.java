package com.hfocean.uavportal.weixin.user.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author guan.sj
 */
@Entity
@Table(name="tb_user_message")
public class WechatUserMessageVO {
	@Id
	private String id;
	private String title;// '标题',
	private String subTitle;// '子标题',
	private String content;// '内容',
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTime;// '创建时间',
	private String read;// '0-未查看，1-已查看',
	private String userId;// '用户id',
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRead() {
		return read;
	}
	public void setRead(String read) {
		this.read = read;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}

package com.hfocean.uavportal.core.user.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author guan.sj
 */
@Entity
@Table(name="tb_user_message")
public class UserMessagePO  implements Serializable {
    private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "objectIdGenerator")
	private String id;
	private String title;// '标题',
	private String subTitle;// '子标题',
	private String content;// '内容',
	private String createTime;// '创建时间',
	private String read;// '0-未查看，1-已查看',
	private String userId;// '用户id',
	private String userType;// '1个人用户2企业用户',
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
    
	
	
}
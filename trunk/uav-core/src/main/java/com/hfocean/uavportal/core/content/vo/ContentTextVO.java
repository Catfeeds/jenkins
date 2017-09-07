package com.hfocean.uavportal.core.content.vo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_content_text")
public class ContentTextVO {
	@Id
	@Column(name="tbContentId")
	private String id;//稿件ID
	@Column(name="tbContentCategoryId")
	private String categoryId;
	private String content;//HTML内容
	
	@OneToMany(cascade = { CascadeType.REFRESH},targetEntity=ContentAdditionVO.class)
	@JoinColumn(name = "tb_content_id",updatable=false,insertable=false)
	private List<ContentAdditionVO> attachments;//附件
	@OneToOne(cascade = { CascadeType.REFRESH},targetEntity=ContentVO.class)
	@JoinColumn(name = "tb_content_id",updatable=false,insertable=false)
	private ContentVO attribute;
	
	public ContentVO getAttribute() {
		return attribute;
	}
	public void setAttribute(ContentVO attribute) {
		this.attribute = attribute;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<ContentAdditionVO> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<ContentAdditionVO> attachments) {
		this.attachments = attachments;
	}
	
    

}

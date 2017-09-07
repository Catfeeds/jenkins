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
@Table(name="tb_announcement_text")
public class AnnouncementTextVO {
	@Id
	@Column(name="tb_announcement_id")
	private String id;//公告ID
	
    private String content;//HTML内容
    
    @OneToMany(cascade = { CascadeType.REFRESH},targetEntity=AnnouncementAdditionVO.class)
	@JoinColumn(name = "tbAnnouncementId",updatable=false,insertable=false)
    private List<AnnouncementAdditionVO> attachments;
    
    
    @OneToOne(cascade = { CascadeType.REFRESH},targetEntity=AnnouncementVO.class)
	@JoinColumn(name = "tb_announcement_id",updatable=false,insertable=false)
	private AnnouncementVO attribute;
    
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

	public List<AnnouncementAdditionVO> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<AnnouncementAdditionVO> attachments) {
		this.attachments = attachments;
	}

	public AnnouncementVO getAttribute() {
		return attribute;
	}

	public void setAttribute(AnnouncementVO attribute) {
		this.attribute = attribute;
	}
    
    

}

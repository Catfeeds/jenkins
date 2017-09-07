package com.hfocean.uavportal.core.content.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by guan.sj on 2017/6/20.
 */
@Entity
@Table(name="tb_content_text")
public class ContentTextPO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String tbContentId;
    
    private String tbContentCategoryId;

    /**
     * 内容
     * */
    private String content;


    public String getTbContentId() {
		return tbContentId;
	}

	public void setTbContentId(String tbContentId) {
		this.tbContentId = tbContentId;
	}

	public String getTbContentCategoryId() {
		return tbContentCategoryId;
	}

	public void setTbContentCategoryId(String tbContentCategoryId) {
		this.tbContentCategoryId = tbContentCategoryId;
	}

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

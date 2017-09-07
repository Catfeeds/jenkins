package com.hfocean.uavportal.console.content.vo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

/**
 * @author 
 */
@Entity
@Table(name="tb_menu_column")
public class MenuColumnVO {
    @Id
    private String id;
    private String parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;
    private Integer isParent;
    private String url;
    private Date createTime;
    private String creator;
    private Date updateTime;
    private String updatePeople;
    @OneToMany(cascade = { CascadeType.REFRESH},targetEntity=MenuColumnVO.class)
	@JoinColumn(name = "parentId",updatable=false,insertable=false)
    @Where(clause="status=1")
    private List<MenuColumnVO> children;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdatePeople() {
        return updatePeople;
    }

    public void setUpdatePeople(String updatePeople) {
        this.updatePeople = updatePeople;
    }

	public List<MenuColumnVO> getChildren() {
		return children;
	}

	public void setChildren(List<MenuColumnVO> children) {
		this.children = children;
	}

}
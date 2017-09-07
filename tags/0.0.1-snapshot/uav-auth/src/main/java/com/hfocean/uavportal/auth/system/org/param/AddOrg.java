package com.hfocean.uavportal.auth.system.org.param;

import org.hibernate.validator.constraints.NotBlank;

public class AddOrg {

	@NotBlank(message="组织名称不能为空")
	private String orgName;

	private String parentId;

	private Integer seq;

	private Integer status;

	private String orgDescription;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOrgDescription() {
		return orgDescription;
	}

	public void setOrgDescription(String orgDescription) {
		this.orgDescription = orgDescription;
	}

}

package com.hfocean.uavportal.core.audit.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author guan.sj
 */
@Entity
@Table(name="tb_audit_record")
public class AuditPO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger id;
	
	/**
	 * 审核类型：用户认证(1)
	 */
	private Integer type;
	
	/**
	 * 认证结果：0.失败，1.成功
	 */
	private Integer result;
	
	/**
	 * 原因
	 */
	private String reason;
	
	/**
	 * 审核时间
	 */
	private Date auditTime;
	
	/**
	 * 审核人id
	 */
	private String adminId;
	
	/**
	 * 申请时间
	 */
	private Date createTime;
	
	/**
	 * 审核目标
	 */
	private String targetId;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
}
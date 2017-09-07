package com.hfocean.uavportal.core.audit.repository;

import java.math.BigInteger;

import com.hfocean.uavportal.core.audit.po.AuditPO;
import com.hfocean.uavportal.core.common.repository.MyRepository;

public interface AuditRepository extends MyRepository<AuditPO,BigInteger> {
	AuditPO findFirstByTargetIdAndTypeAndResultOrderByIdDesc(String targetId, Integer type, Integer result);
}
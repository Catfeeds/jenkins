package com.hfocean.uavportal.core.audit.service.impl;

import java.math.BigInteger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hfocean.uavportal.core.audit.po.AuditPO;
import com.hfocean.uavportal.core.audit.repository.AuditRepository;
import com.hfocean.uavportal.core.audit.service.IAuditServiceCore;
import com.hfocean.uavportal.core.audit.vo.AuditVO;
import com.hfocean.uavportal.core.common.service.impl.BaseServiceImpl;

/**
 * @author guan.sj
 */
@Service
@Transactional
public class AuditServiceCoreImpl extends BaseServiceImpl<AuditPO,BigInteger> implements IAuditServiceCore {
	
	@Autowired
	private AuditRepository auditRepository;
	
	@Override
	public AuditVO findAuditReason(String targetId, Integer type, Integer result) throws Exception {
		AuditVO vo = null;
		AuditPO po = auditRepository.findFirstByTargetIdAndTypeAndResultOrderByIdDesc(targetId, type, result);
		if(po!=null){
			vo = new AuditVO();
			BeanUtils.copyProperties(po, vo);
		}
		return vo;
	}
	
}

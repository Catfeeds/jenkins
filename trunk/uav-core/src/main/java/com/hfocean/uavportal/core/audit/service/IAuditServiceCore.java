package com.hfocean.uavportal.core.audit.service;

import java.math.BigInteger;

import com.hfocean.uavportal.core.audit.po.AuditPO;
import com.hfocean.uavportal.core.audit.vo.AuditVO;
import com.hfocean.uavportal.core.common.service.BaseService;

/**审核记录
 * @author guan.sj
 */
public interface IAuditServiceCore extends BaseService<AuditPO,BigInteger> {
	/**获取最后一次审核结果
	 * @param targetId
	 * @param type
	 * @param result
	 * @return
	 * @throws Exception
	 */
	AuditVO findAuditReason(String targetId, Integer type, Integer result)throws Exception;
}

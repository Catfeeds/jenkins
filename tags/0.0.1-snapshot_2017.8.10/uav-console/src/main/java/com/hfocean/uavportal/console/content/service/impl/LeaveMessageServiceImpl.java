package com.hfocean.uavportal.console.content.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hfocean.common.util.DateHandler;
import com.hfocean.common.util.VerdictUtil;
import com.hfocean.uavportal.console.content.param.LeaveMessageSearchParam;
import com.hfocean.uavportal.console.content.service.ILeaveMessageService;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.service.impl.BaseServiceImpl;
import com.hfocean.uavportal.core.content.enm.LeaveMessageEnum;
import com.hfocean.uavportal.core.content.po.LeaveMessagePO;


/**
 * @author guan.sj
 */
@Service
@Transactional
public class LeaveMessageServiceImpl extends BaseServiceImpl<LeaveMessagePO,BigInteger> implements ILeaveMessageService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Pager findMessages(LeaveMessageSearchParam param, Pager page) throws Exception {
		StringBuilder hql = new StringBuilder("from LeaveMessageVO t where 1=1");
		List<Object> params = getLeaveListHql(param, hql);
		return findByPage(hql.toString(), page, params);
	}
	
	 private List<Object> getLeaveListHql(LeaveMessageSearchParam param, StringBuilder hql) throws Exception {
	        List<Object> params = new ArrayList<>();
	        hql.append(" and t.status <> ?");
            params.add(LeaveMessageEnum.删除.getCode());
            
	        if(VerdictUtil.gtOrEtoZero(param.getStatus())){
	            hql.append(" and t.status = ?");
	            params.add(param.getStatus());
	        }
	        if(VerdictUtil.isNotBlank(param.getStartDate())){
	            hql.append(" and t.createTime >= ?");
	            params.add(DateHandler.parse(param.getStartDate(),"yyyy-MM-dd"));
	        }
	        if(VerdictUtil.isNotBlank(param.getEndDate())){
	            hql.append(" and t.createTime <= ?");
	            params.add(DateHandler.parse(param.getEndDate(),"yyyy-MM-dd"));
	        }
	        hql.append(" order by t.createTime desc");
	        return params;
	    }

	@Override
	public int updateMessage(String messageId,Integer status) throws Exception {
		BigInteger id = new BigInteger(messageId);
		LeaveMessagePO message = findOne(id);
		if(message!=null){
			message.setStatus(status);
			save(message);
		}
		return 1;
	}

	@Override
	public LeaveMessagePO findMessage(String messageId) throws Exception {
		BigInteger id = new BigInteger(messageId);
		return findOne(id);
	}
	
}
	



	


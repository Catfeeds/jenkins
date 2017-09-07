package com.hfocean.uavportal.console.content.service;

import java.math.BigInteger;

import com.hfocean.uavportal.console.content.param.LeaveMessageSearchParam;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.service.BaseService;
import com.hfocean.uavportal.core.content.po.LeaveMessagePO;

public interface ILeaveMessageService extends BaseService<LeaveMessagePO,BigInteger> {
	Pager findMessages(LeaveMessageSearchParam param, Pager page)throws Exception;
	int updateMessage(String messageId, Integer status)throws Exception;
	LeaveMessagePO findMessage(String messageId)throws Exception;
}

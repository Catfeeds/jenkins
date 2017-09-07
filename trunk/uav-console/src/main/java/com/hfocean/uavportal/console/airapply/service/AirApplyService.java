package com.hfocean.uavportal.console.airapply.service;

import com.hfocean.uavportal.console.airapply.vo.AirApplyPageParam;
import com.hfocean.uavportal.core.airapply.po.AirApplyPO;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.service.BaseService;

import java.io.IOException;

/**
 *
 * @author Leslie.Lam
 * @create 2017-06-22 16:01
 **/
public interface AirApplyService extends BaseService<AirApplyPO,String>{

    /**
     * 分页查询空域申请
     * @param param
     * @param pager
     * @return
     */
    Pager queryAirApplyByPage(AirApplyPageParam param, Pager pager) throws Exception;

    /**
     * 导出空域申请列表
     * @param param
     * @throws Exception
     */
    void exportAirApplyExcel(AirApplyPageParam param)throws Exception;

    /**
     * 审核空域申请
     * @param airplanId
     */
    void auditAirApply(String airplanId,String param) throws Exception;
}

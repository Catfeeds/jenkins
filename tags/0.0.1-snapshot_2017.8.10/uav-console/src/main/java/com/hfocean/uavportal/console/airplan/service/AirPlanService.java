package com.hfocean.uavportal.console.airplan.service;

import com.hfocean.uavportal.console.airplan.vo.AirPlanPageParam;
import com.hfocean.uavportal.core.airplan.po.AirPlanPO;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.service.BaseService;

import java.io.IOException;

/**
 * @author Leslie.Lam
 * @create 2017-06-25 21:00
 **/
public interface AirPlanService extends BaseService<AirPlanPO,String> {

    /**
     * 分页查询飞行计划
     * @param param
     * @param pager
     * @return
     */
    Pager queryAirplansByPage(AirPlanPageParam param, Pager pager) throws Exception;

    /**
     * 导出飞行计划列表
     * @param param
     * @throws Exception
     */
    void exportAirplansExcel(AirPlanPageParam param)throws Exception;

    /**
     * 审核飞行计划
     * @param flyplanId
     */
    void auditAirPlan(String flyplanId,String param) throws Exception;
}

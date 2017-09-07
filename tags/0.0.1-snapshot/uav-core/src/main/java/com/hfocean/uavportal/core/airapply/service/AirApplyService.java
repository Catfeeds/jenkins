package com.hfocean.uavportal.core.airapply.service;/**
 * Created by msi- on 2017/6/20.
 */

import com.hfocean.uavportal.core.airapply.po.AirApplyPO;
import com.hfocean.uavportal.core.airapply.vo.MyAirApply;
import com.hfocean.uavportal.core.common.service.BaseService;
import com.hfocean.uavportal.core.user.session.BaseSessionUser;

import java.util.List;

/**
 * 处理空域申请业务
 *
 * @author Leslie.Lam
 * @create 2017-06-20 15:45
 **/
public interface AirApplyService extends BaseService<AirApplyPO,String> {

    /**
     * 创建空域申请
     * @param param
     * @throws Exception
     */
    void addAirApply(String param, BaseSessionUser user)throws Exception;

    /**
     * 查询个人空域申请列表
     * @param period 从当前时间开始推迟天数
     * @param type 飞行计划
     * @return
     */
    List<MyAirApply> queryAirApplys(Integer period, String type, BaseSessionUser user) throws Exception;

    /**
     * 撤销空域申请
     * @param aircode 空域申请编号
     * @throws Exception
     */
    void revokeAirApply(String aircode)throws Exception;
}

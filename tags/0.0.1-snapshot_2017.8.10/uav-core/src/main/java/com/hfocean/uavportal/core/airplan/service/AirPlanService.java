package com.hfocean.uavportal.core.airplan.service;

import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.core.airplan.po.AirPlanPO;
import com.hfocean.uavportal.core.airplan.po.AirPlanTempPO;
import com.hfocean.uavportal.core.airplan.vo.MyAirPlan;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.service.BaseService;
import com.hfocean.uavportal.core.user.session.BaseSessionUser;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

/**
 * @author Leslie.Lam
 * @create 2017-06-24 9:54
 **/
public interface AirPlanService extends BaseService<AirPlanPO,String>{

    /**
     * 创建飞行计划
     * @param param
     * @throws Exception
     */
    void addAirPlan(String param, BaseSessionUser user)throws Exception;

    /**
     * 查询飞行计划列表
     * @param period
     * @param type
     * @return
     */
    List<MyAirPlan> queryAirplans(Integer period, String type, BaseSessionUser user);

    /**
     * 启动飞行计划
     * @param flycode 飞行计划编号
     */
    ResponseEntity<WebResponse> startAirplan(String flycode);

    /**
     * 停止飞行计划
     * @param flycode 飞行计划编号
     */
    ResponseEntity<WebResponse> stopAirplan(String flycode);

    /**
     * 撤销飞行计划申请
     * @param flycode 飞行计划编号
     */
    ResponseEntity<WebResponse> revokeAirPlan(String flycode);

    /**
     * 分页查询个人飞行计划
     * @param pager
     * @return
     */
    Pager queryAirplansByPage(Pager pager,BaseSessionUser user,Integer...status);

    /**
     * 创建飞行计划模板
     * @param param
     * @throws Exception
     */
    void addAirPlanTemp(String param, BaseSessionUser user)throws Exception;

    /**
     * 获取个人飞行计划模板
     * @return
     */
    List queryAirplanTemps(BaseSessionUser user);

    /**
     * 获取飞行凭证
     * @param code 编号
     * @return
     */
    ResponseEntity<WebResponse> getAirPlanCertificate(String code) throws IOException;

    AirPlanTempPO queryAirPlanTempById(String id);

    /**
     * 提醒用户确认是否开始管制飞行计划
     * @return
     * @throws Exception
     */
    void notifyControlAirPlanStart()throws Exception;

    /**
     * 提醒用户开始一类、二类飞行计划
     * @return
     * @throws Exception
     */
    void notifyAirPlanStart()throws Exception;

    /**
     * 处理没有开始的飞行计划
     * @throws Exception
     */
    void processUnStartAirPlan()throws Exception;

    /**
     * 提醒用户飞行计划超时
     * @return
     * @throws Exception
     */
    void notifyAirPlanOverTime()throws Exception;

    /**
     * 改变飞行计划为超时状态
     * @return
     * @throws Exception
     */
    void updateAirPlanOverTime()throws Exception;

    /**
     * 用户飞行计划统计
     * @throws Exception
     */
    void userFlyCount()throws Exception;

    /**
     * 自动关闭未审核计划
     * @throws Exception
     */
    void autoCloseUnAuditedPlan()throws Exception;

    /**
     * 自动审核通过管制、一类飞行计划
     */
    void autoAuditContolAndFirstTypePlan()throws Exception;

    /**
     * 自动审核不通过一类、管制飞行计划
     * @throws Exception
     */
    void autoAuditPlan()throws Exception;

    /**
     * 自动审核通过二类计划
     * @throws Exception
     */
    void autoAuditSecondTypePlan()throws Exception;
}

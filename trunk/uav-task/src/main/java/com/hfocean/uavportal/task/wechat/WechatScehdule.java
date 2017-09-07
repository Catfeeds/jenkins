package com.hfocean.uavportal.task.wechat;

import com.hfocean.uavportal.core.airplan.service.AirPlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
* 
* @author Leslie.Lam
* @create 2017-06-30 16:51
**/
@Component
@Configurable
@EnableScheduling
public class WechatScehdule {

    @Resource
    private AirPlanService airPlanService;

    private final static transient Logger log = LoggerFactory.getLogger(WechatScehdule.class);

    /**
     * 提醒用户确认是否开始管制飞行计划
     * @throws Exception
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void notifyControlAirPlanStart() throws Exception {
        log.info("==========提醒用户确认是否开始管制飞行计划 任务开始============");
        airPlanService.notifyControlAirPlanStart();
        log.info("==========提醒用户确认是否开始管制飞行计划 任务结束============");
    }

    /**
     * 提醒用户开始一类、二类飞行计划
     * @throws Exception
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void notifyAirPlanStart() throws Exception {
        log.info("==========提醒用户开始一类、二类飞行计划 任务开始============");
        airPlanService.notifyAirPlanStart();
        log.info("==========提醒用户开始一类、二类飞行计划 任务结束============");
    }

    /**
     * 处理没有开始的飞行计划
     * @throws Exception
     */
    @Scheduled(cron = "0 */15 * * * ?")
    public void processUnStartAirPlan()throws Exception{
        log.info("==========处理没有开始的飞行计划 任务开始============");
        airPlanService.processUnStartAirPlan();
        log.info("==========处理没有开始的飞行计划 任务结束============");
    }

    /**
     * 提醒用户飞行计划超时
     * @throws Exception
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void notifyAirPlanOverTime()throws Exception {
        log.info("==========提醒用户飞行计划超时 任务开始============");
        airPlanService.notifyAirPlanOverTime();
        log.info("==========提醒用户飞行计划超时 任务结束============");
    }

    /**
     * 改变飞行计划为超时状态
     * @throws Exception
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void updateAirPlanOverTime()throws Exception {
        log.info("==========改变飞行计划为超时状态 任务开始============");
        airPlanService.updateAirPlanOverTime();
        log.info("==========改变飞行计划为超时状态 任务结束============");
    }

    /**
     * 自动关闭未审核计划
     * @throws Exception
     */
    @Scheduled(cron = "0 */15 * * * ?")
    public void autoCloseUnAuditedPlan()throws Exception {
        log.info("==========自动关闭未审核计划 任务开始============");
        airPlanService.autoCloseUnAuditedPlan();
        log.info("==========自动关闭未审核计划 任务结束============");
    }

    /**
     * 自动审核通过管制、一类飞行计划
     * @throws Exception
     */
    @Scheduled(cron = "0 0 21 * * ?")
    public void autoAuditContolAndFirstTypePlan()throws Exception{
        log.info("==========自动审核通过管制、一类飞行计划 任务开始============");
        airPlanService.autoAuditContolAndFirstTypePlan();
        log.info("==========自动审核通过管制、一类飞行计划 任务结束============");
    }

//    /**
//     * 自动审核不通过一类、管制飞行计划 每天23时0分执行一次
//     * @throws Exception
//     */
//    @Scheduled(cron = "0 0 21 * * ?")
//    public void autoAuditPlan()throws Exception{
//        log.info("==========自动审核不通过一类、管制飞行计划 任务开始============");
//        airPlanService.autoAuditPlan();
//        log.info("==========自动审核不通过一类、管制飞行计划 任务结束============");
//    }

    /**
     * 自动审核通过二类计划
     * @throws Exception
     */
    @Scheduled(cron = "0 */15 * * * ?")
    public void autoAuditSecondTypePlan()throws Exception {
        log.info("==========自动审核通过二类计划 任务开始============");
        airPlanService.autoAuditSecondTypePlan();
        log.info("==========自动审核通过二类计划 任务结束============");
    }

    /**
     * 用户飞行计划统计 每天0时0分执行一次
     * @throws Exception
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void userFlyCount()throws Exception {
        log.info("==========用户飞行计划统计 任务开始============");
        airPlanService.userFlyCount();
        log.info("==========用户飞行计划统计 任务结束============");
    }
}

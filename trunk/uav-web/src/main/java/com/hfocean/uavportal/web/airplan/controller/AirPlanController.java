package com.hfocean.uavportal.web.airplan.controller;

import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.core.airplan.po.AirPlanPO;
import com.hfocean.uavportal.core.airplan.service.AirPlanService;
import com.hfocean.uavportal.core.annotation.Login;
import com.hfocean.uavportal.web.util.WebAppContextHelper;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
* 
* @author Leslie.Lam
* @create 2017-06-24 13:44
**/
@Controller
public class AirPlanController {

    @Resource
    private AirPlanService airPlanService;

    /**
     * 创建飞行计划
     * @param param
     * @return
     * @throws Exception
     */
    @Login(authStatus=true)
    @RequestMapping(method = RequestMethod.POST,value = "console/flyplan")
    @ResponseBody
    public ResponseEntity<WebResponse> addAirApply(@RequestBody String param) throws Exception {
        airPlanService.addAirPlan(param, WebAppContextHelper.getCurrentUser());
        return ResponseEntity.ok().body(new WebResponse());
    }

    /**
     * 查询飞行计划
     * @param period
     * @param type
     * @return
     * @throws Exception
     */
    @Login
    @RequestMapping(method = RequestMethod.GET,value = "console/flyplans")
    @ResponseBody
    public WebResponse queryAirplans(Integer period,String type) throws Exception {
        return new WebResponse(airPlanService.queryAirplans(period,type,WebAppContextHelper.getCurrentUser()));
    }

    /**
     * 获取飞行计划
     * @param flyCode
     * @return
     * @throws Exception
     */
    @Login
    @RequestMapping(method = RequestMethod.GET,value = "console/flyplan/{flyCode}")
    @ResponseBody
    public WebResponse queryAirplanByCode(@PathVariable("flyCode") String flyCode) throws Exception {
        return new WebResponse(airPlanService.findOne(Example.of(new AirPlanPO(flyCode,WebAppContextHelper.getCurrentUser().getUserId()))));
    }

    /**
     * 启动飞行计划
     * @param flycode
     * @return
     * @throws Exception
     */
    @Login(authStatus=true)
    @RequestMapping(method = RequestMethod.PUT,value = "console/flyplan/{flycode}/status/start")
    @ResponseBody
    public ResponseEntity<WebResponse> startAirplan(@PathVariable("flycode") String flycode) throws Exception {
        return airPlanService.startAirplan(flycode);
    }

    /**
     * 停止飞行计划
     * @param flycode
     * @return
     * @throws Exception
     */
    @Login(authStatus=true)
    @RequestMapping(method = RequestMethod.PUT,value = "console/flyplan/{flycode}/status/stop")
    @ResponseBody
    public ResponseEntity<WebResponse> stopAirplan(@PathVariable("flycode") String flycode) throws Exception {
        return airPlanService.stopAirplan(flycode);
    }

    /**
     * 撤销飞行计划
     * @param flycode
     * @return
     * @throws Exception
     */
    @Login(authStatus=true)
    @RequestMapping(method = RequestMethod.DELETE ,value = "console/flyplan/{flycode}")
    @ResponseBody
    public ResponseEntity<WebResponse> delAirApply(@PathVariable("flycode") String flycode) throws Exception {
        return airPlanService.revokeAirPlan(flycode);
    }

    /**
     * 创建飞行计划模板
     * @param param
     * @throws Exception
     */
    @Login
    @RequestMapping(method = RequestMethod.POST ,value = "console/flyplan/template")
    @ResponseBody
    public WebResponse addAirPlanTemp(@RequestBody String param) throws Exception {
        airPlanService.addAirPlanTemp(param,WebAppContextHelper.getCurrentUser());
        return new WebResponse();
    }

    /**
     * 获取个人飞行计划模板列表
     * @return
     */
    @Login
    @RequestMapping(method = RequestMethod.GET ,value = "console/flyplan/templates")
    @ResponseBody
    public WebResponse queryAirplanTemps() throws Exception {
        return new WebResponse(airPlanService.queryAirplanTemps(WebAppContextHelper.getCurrentUser()));
    }

    /**
     * 获取个人飞行计划模板详情
     * @return
     */
    @Login
    @RequestMapping(method = RequestMethod.GET ,value = "console/flyplan/template/{templateId}")
    @ResponseBody
    public WebResponse queryAirPlanTemp(@PathVariable("templateId")String templateId) throws Exception {
        return new WebResponse(airPlanService.queryAirPlanTempById(templateId));
    }

    /**
     *手动触发用户飞行统计 临时测试接口
     */
    @RequestMapping(method = RequestMethod.GET ,value = "userFlyCount")
    @ResponseBody
    public void userFlyCount(String key) throws Exception {
        if(Objects.equals(key,"zhushuishishabi")) airPlanService.userFlyCount();
    }

}

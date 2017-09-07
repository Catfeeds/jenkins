package com.hfocean.uavportal.weixin.airplan.controller;

import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;
import com.hfocean.common.util.VerdictUtil;
import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.core.airplan.enumerate.AirPlanEnum;
import com.hfocean.uavportal.core.airplan.po.AirPlanPO;
import com.hfocean.uavportal.core.airplan.service.AirPlanService;
import com.hfocean.uavportal.core.annotation.Login;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.constant.Constant;
import com.hfocean.uavportal.weixin.util.WechatAppContextHelper;
import io.jsonwebtoken.*;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    @RequestMapping(method = RequestMethod.POST,value = "flyplan")
    @ResponseBody
    public ResponseEntity<WebResponse> addAirApply(@RequestBody String param) throws Exception {
        airPlanService.addAirPlan(param, WechatAppContextHelper.getCurrentUser());
        return ResponseEntity.ok().body(new WebResponse());
    }

    /**
     * 分页查询飞行计划
     * @return
     * @throws Exception
     */
    @Login
    @RequestMapping(method = RequestMethod.GET,value = "flyplans")
    @ResponseBody
    public WebResponse queryAirplansByPage(HttpServletRequest request) throws Exception {
        return new WebResponse(airPlanService.queryAirplansByPage(new Pager(request),WechatAppContextHelper.getCurrentUser()));
    }

    /**
     * 分页获取处于执行状态的飞行计划列表
     * @return
     * @throws Exception
     */
    @Login
    @RequestMapping(method = RequestMethod.GET,value = "flyplans/active")
    @ResponseBody
    public WebResponse queryAirActivePlansByPage(HttpServletRequest request) throws Exception {
        return new WebResponse(airPlanService.queryAirplansByPage(new Pager(request),WechatAppContextHelper.getCurrentUser(), AirPlanEnum.执行动态.status()));
    }

    /**
     * 分页获取处于申请状态的飞行计划列表
     * @return
     * @throws Exception
     */
    @Login
    @RequestMapping(method = RequestMethod.GET,value = "flyplans/apply")
    @ResponseBody
    public WebResponse queryAirApplyPlansByPage(HttpServletRequest request) throws Exception {
        return new WebResponse(airPlanService.queryAirplansByPage(new Pager(request),WechatAppContextHelper.getCurrentUser(), AirPlanEnum.申请动态.status()));
    }

    /**
     * 启动飞行计划
     * @param flycode
     * @return
     * @throws Exception
     */
    @Login(authStatus=true)
    @RequestMapping(method = RequestMethod.PUT,value = "flyplan/{flycode}/status/start")
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
    @RequestMapping(method = RequestMethod.PUT,value = "flyplan/{flycode}/status/stop")
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
    @RequestMapping(method = RequestMethod.DELETE ,value = "flyplan/{flycode}")
    @ResponseBody
    public ResponseEntity<WebResponse> delAirplan(@PathVariable("flycode") String flycode) throws Exception {
        return airPlanService.revokeAirPlan(flycode);
    }

    /**
     * 获取飞行计划凭证
     * @param flycode
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET ,value = "flyplan/{flycode}/qrcode")
    @ResponseBody
    public ResponseEntity<WebResponse> getAirPlanCode(@PathVariable("flycode") String flycode) throws Exception {
        VerdictUtil.assertNotNull(flycode);
        return airPlanService.getAirPlanCertificate(flycode);
    }

    /**
     * 根据编号查询飞行计划详情
     * @param flycode
     * @throws Exception
     */
    @Login
    @RequestMapping(method = RequestMethod.GET ,value = "flyplan/{flycode}")
    @ResponseBody
    public WebResponse getAirPlanByCode(@PathVariable("flycode") String flycode) throws Exception {
        VerdictUtil.assertNotNull(flycode);
        return new WebResponse(airPlanService.findOne(Example.of(new AirPlanPO(flycode,WechatAppContextHelper.getCurrentUser().getUserId()))));
    }

    /**
     * 根据编号查询飞行计划详情
     * @param token (此处token为jwt加密的flycode)
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET ,value = "flyplan/token")
    @ResponseBody
    public WebResponse getAirPlanByCodeInJWT(@RequestParam("token") String token) throws Exception {
        VerdictUtil.assertNotNull(token);
        String subject;
        try {
            subject = Jwts.parser().setSigningKey(Constant.JWTSECRETKEY).parseClaimsJws(token).getBody().getSubject();
            if(null==subject)throw new BaseBusinessException(BaseBusinessError.FORBIDDEN);
        } catch (ExpiredJwtException e) {
            throw new BaseBusinessException(BaseBusinessError.FORBIDDEN.getCode(),"飞行凭证已过期");
        }  catch (BaseBusinessException e) {
            throw e;
        }
        return new WebResponse(airPlanService.findOne(Example.of(new AirPlanPO(subject))));
    }
}

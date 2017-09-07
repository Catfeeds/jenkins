package com.hfocean.uavportal.console.airspace.controller;

import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.auth.system.permission.annotation.MjPermission;
import com.hfocean.uavportal.console.airspace.service.AirspaceService;
import com.hfocean.uavportal.console.airspace.vo.AirspaceVo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
* 
* @author Leslie.Lam
* @create 2017-06-24 17:43
**/
@Controller
public class AirspaceController {

    @Resource
    private AirspaceService airspaceService;

    /**
     * 获取空域列表
     * @return
     * @throws Exception
     */
    @MjPermission
    @RequestMapping(method = RequestMethod.GET,value = "metadata/airspace")
    @ResponseBody
    public WebResponse queryAllAirspace() throws Exception {
        return new WebResponse(airspaceService.findAll());
    }

    /**
     * 查询空域详情
     * @return
     * @throws Exception
     */
    @MjPermission
    @RequestMapping(method = RequestMethod.GET,value = "getAirspace")
    @ResponseBody
    public WebResponse getAirspace(@RequestParam String id) throws Exception {
        return new WebResponse(airspaceService.findOne(id));
    }

    /**
     * 添加空域
     * @return
     * @throws Exception
     */
    @MjPermission
    @RequestMapping(method = RequestMethod.POST,value = "addAirspace")
    @ResponseBody
    public WebResponse addAirspace(@Validated AirspaceVo param) throws Exception {
        return new WebResponse(airspaceService.addAirspace(param));
    }

    /**
     * 修改空域
     * @return
     * @throws Exception
     */
    @MjPermission
    @RequestMapping(method = RequestMethod.PUT,value = "updateAirspace")
    @ResponseBody
    public WebResponse updateAirspace(@Validated AirspaceVo param) throws Exception {
        return new WebResponse(airspaceService.updateAirspace(param));
    }

    /**
     * 删除空域
     * @return
     * @throws Exception
     */
    @MjPermission
    @RequestMapping(method = RequestMethod.DELETE,value = "delAirspace")
    @ResponseBody
    public WebResponse delAirspace(@RequestParam String id) throws Exception {
        airspaceService.delete(id);
        return new WebResponse("删除成功");
    }
}

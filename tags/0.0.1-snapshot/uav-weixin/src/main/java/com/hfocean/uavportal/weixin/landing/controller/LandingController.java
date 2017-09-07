package com.hfocean.uavportal.weixin.landing.controller;

import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.core.annotation.Login;
import com.hfocean.uavportal.core.landing.service.LandingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
* 
* @author Leslie.Lam
* @create 2017-06-24 17:43
**/
@Controller
public class LandingController {

    @Resource
    private LandingService landingService;

    @Login(authStatus = true)
    @RequestMapping(method = RequestMethod.GET,value = "metadata/landings")
    @ResponseBody
    public ResponseEntity<WebResponse> queryAllLandings() throws Exception {
        return ResponseEntity.ok().body(new WebResponse(landingService.findAll()));
    }
}

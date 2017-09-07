package com.hfocean.uavportal.web.airspace.controller;

import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.core.airspace.po.AirspacePO;
import com.hfocean.uavportal.core.airspace.service.AirspaceService;
import com.hfocean.uavportal.core.annotation.Login;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
* 
* @author Leslie.Lam
* @create 2017-06-24 17:43
**/
@Controller
public class AirspaceController {

    @Resource
    private AirspaceService airspaceService;

    @Login(authStatus = true)
    @RequestMapping(method = RequestMethod.GET,value = "metadata/airspace")
    @ResponseBody
    public ResponseEntity<WebResponse> queryAllAirspace(Integer type) throws Exception {
        List<AirspacePO> all;
        if(null!=type){
            all = airspaceService.findAll(Example.of(new AirspacePO(type)));
        }else {
            all= airspaceService.findAll();
        }
        return ResponseEntity.ok().body(new WebResponse(all));
    }
}

package com.hfocean.uavportal.weixin.web.controller;

import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.weixin.verification.WechatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
* 
* @author Leslie.Lam
* @create 2017-06-25 14:11
**/
@Controller
@RequestMapping("wechat")
public class WechatController {

    @Resource
    private WechatService wechatService;

    @ResponseBody
    @RequestMapping("getOpenid")
    public WebResponse getOpenId(String code) throws Exception{
        return new WebResponse(wechatService.getOpenId(code));
    }

}

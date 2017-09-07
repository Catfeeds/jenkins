package com.hfocean.uavportal.weixin.web.controller;

import com.hfocean.common.qcode.QrcodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
* 
* @author Leslie.Lam
* @create 2017-07-03 11:43
**/
@Controller
public class QrcodeController {

    /**
     * 生成二维码
     * @param content
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/create/qrcode")
    public void createQrcode(String content,HttpServletResponse response) throws Exception{
        QrcodeUtil.createQrcodeByStr(content,response.getOutputStream());
    }
}

package com.hfocean.uavportal.console.web.controller;

import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.auth.system.permission.annotation.MjPermission;
import com.hfocean.uavportal.core.dictionary.service.DictionaryService;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
* 
* @author Leslie.Lam
* @create 2017-08-03 18:43
**/
@Controller
@RequestMapping("rest/dic")
public class DictionaryController {

    @Resource
    private DictionaryService dictionaryService;

    /**
     * 查询数据字典(带子类级别)
     * @param name
     * @return
     * @throws Exception
     */
    @ResponseBody
//    @MjPermission
    @RequestMapping(value = "child",method = RequestMethod.GET)
    public WebResponse selectChildrenByName(String name) throws Exception {
        Assert.notNull(name,"name不能为空");
        return new WebResponse(dictionaryService.selectChildrenByName(name));
    }

    /**
     * 查询数据字典备注
     * @param name
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "remark",method = RequestMethod.GET)
    public WebResponse getRemarkByNameAndCode(String name, Integer code) throws Exception {
        Assert.notNull(name,"name不能为空");
        return new WebResponse(dictionaryService.getRemarkByNameAndCode(name,code));
    }
}

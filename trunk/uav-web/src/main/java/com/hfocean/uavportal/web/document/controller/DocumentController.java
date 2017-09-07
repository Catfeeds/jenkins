package com.hfocean.uavportal.web.document.controller;

import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.document.service.DocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
* 
* @author Leslie.Lam
* @create 2017-06-24 17:43
**/
@Controller
public class DocumentController {

    @Resource
    private DocumentService documentService;

    /**
     * 分页查询合同列表
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET,value = "console/documents")
    @ResponseBody
    public WebResponse queryAllAirspace(HttpServletRequest request) throws Exception {
        return new WebResponse(documentService.queryDocummentByPage(new Pager(request)));
    }
}

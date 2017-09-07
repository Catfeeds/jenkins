package com.hfocean.uavportal.console.document.controller;

import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.auth.base.utils.AuthAppContextHelper;
import com.hfocean.uavportal.auth.system.permission.annotation.MjPermission;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.document.service.DocumentService;
import com.hfocean.uavportal.core.document.vo.DocumentVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
     * 上传合同
     * @param param
     * @return
     * @throws Exception
     */
    @MjPermission
    @RequestMapping(method = RequestMethod.POST,value = "/document")
    @ResponseBody
    public WebResponse uploadDocumment(@RequestBody DocumentVo param) throws Exception {
        param.setCreator(AuthAppContextHelper.getSysUserName());
        documentService.uploadDocumment(param);
        return new WebResponse();
    }

    /**
     * 分页查询合同列表
     * @param request
     * @return
     * @throws Exception
     */
    @MjPermission
    @RequestMapping(method = RequestMethod.GET,value = "documents")
    @ResponseBody
    public WebResponse queryAllAirspace(HttpServletRequest request) throws Exception {
        return new WebResponse(documentService.queryDocummentByPage(new Pager(request)));
    }

    /**
     * 更新合同
     * @param documentId
     * @param param
     * @return
     * @throws Exception
     */
    @MjPermission
    @RequestMapping(method = RequestMethod.PUT,value = "/documents/{documentId}")
    @ResponseBody
    public WebResponse updateDocumment(@PathVariable("documentId")String documentId, @RequestBody DocumentVo param) throws Exception {
        param.setId(documentId);
        documentService.updateDocumment(param);
        return new WebResponse();
    }

    /**
     * 删除合同
     * @param documentId
     * @return
     * @throws Exception
     */
    @MjPermission
    @RequestMapping(method = RequestMethod.DELETE,value = "/documents/{documentId}")
    @ResponseBody
    public WebResponse delDocumment(@PathVariable("documentId")String documentId) throws Exception {
        documentService.delDocumment(documentId);
        return new WebResponse();
    }
}

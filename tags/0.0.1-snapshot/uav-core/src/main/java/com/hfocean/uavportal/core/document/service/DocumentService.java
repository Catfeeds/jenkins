package com.hfocean.uavportal.core.document.service;

import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.service.BaseService;
import com.hfocean.uavportal.core.document.po.Document;
import com.hfocean.uavportal.core.document.vo.DocumentVo;

/**
 * @author Leslie.Lam
 * @create 2017-06-27 15:50
 **/
public interface DocumentService extends BaseService<Document,String> {

    /**
     * 上传合同
     * @param param
     */
    void uploadDocumment(DocumentVo param);

    /**
     * 更新合同
     * @param param
     */
    void updateDocumment(DocumentVo param);

    /**
     * 删除合同
     * @param id
     */
    void delDocumment(String id);

    /**
     * 分页查询
     * @param pager
     */
    Pager queryDocummentByPage(Pager pager);
}

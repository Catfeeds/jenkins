package com.hfocean.uavportal.core.document.service.impl;

import com.hfocean.common.util.ValidateUtil;
import com.hfocean.common.util.VerdictUtil;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.service.impl.BaseServiceImpl;
import com.hfocean.uavportal.core.document.po.Document;
import com.hfocean.uavportal.core.document.service.DocumentService;
import com.hfocean.uavportal.core.document.vo.DocumentVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
* 
* @author Leslie.Lam
* @create 2017-06-27 15:51
**/
@Service
public class DocumentServiceImpl extends BaseServiceImpl<Document,String> implements DocumentService {

    @Override
    @Transactional
    public void uploadDocumment(DocumentVo param) {
        ValidateUtil.validate(param);
        Document document = new Document();
        document.setCreateTime(new Date());
        document.setCreator(param.getCreator());
        document.setName(param.getName());
        document.setUrl(param.getUrl());
        save(document);
    }

    @Override
    @Transactional
    public void updateDocumment(DocumentVo param) {
        String id = param.getId();
        VerdictUtil.assertNotNull(id);
        ValidateUtil.validate(param);
        Document document = findOne(id);
        VerdictUtil.assertNotNull(document);
        document.setUrl(param.getUrl());
        document.setName(param.getName());
        document.setUpdateTime(new Date());
        save(document);
    }

    @Override
    @Transactional
    public void delDocumment(String id) {
        Document document = findOne(id);
        VerdictUtil.assertNotNull(document);
        delete(document);
    }

    @Override
    public Pager queryDocummentByPage(Pager pager) {
        StringBuffer hql = new StringBuffer("FROM DocumentVo d WHERE 1=1 order by d.createTime desc");
        return findByPage(hql.toString(),pager,null);
    }
}

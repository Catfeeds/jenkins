package com.hfocean.uavportal.console.airspace.impl;

import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;
import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.console.airspace.service.AirspaceService;
import com.hfocean.uavportal.console.airspace.vo.AirspaceVo;
import com.hfocean.uavportal.core.airspace.po.AirspacePO;
import com.hfocean.uavportal.core.common.service.impl.BaseServiceImpl;
import com.hfocean.uavportal.core.dictionary.po.Dictionary;
import com.hfocean.uavportal.core.dictionary.repository.DictionaryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.Query;

/**
* 
* @author Leslie.Lam
* @create 2017-07-31 14:15
**/
@Service("consoleAirspaceService")
public class AirspaceServiceImpl extends BaseServiceImpl<AirspacePO,String> implements AirspaceService {

    @Resource
    private DictionaryRepository dictionaryRepository;

    @Override
    @Transactional
    public WebResponse addAirspace(AirspaceVo param) {
        AirspacePO exist = findOne(param.getId());
        if(null!=exist)throw new BaseBusinessException(410,"空域编号已被使用");
        AirspacePO po = new AirspacePO();
        BeanUtils.copyProperties(param,po);
        po.setLocation(new StringBuffer(po.getProvinceName()).append(po.getCityName()).append(po.getAreaName()).toString());
        save(po);
        Dictionary dictionary = new Dictionary();
        dictionary.setName("territorial");
        dictionary.setRemark(param.getTerritorial());
        if(null==dictionaryRepository.findOne(Example.of(dictionary))){
            dictionary.setIsParent(0);
            dictionary.setParentCode(0);
            Query query = entityManager.createNativeQuery("SELECT MAX(`code`) FROM `tb_dictionary` WHERE `name` LIKE 'territorial%'");
            Integer maxCode = (Integer) query.getResultList().get(0);
            dictionary.setCode(maxCode);
            dictionaryRepository.save(dictionary);
        }
        return new WebResponse("添加成功！");
    }

    @Override
    @Transactional
    public WebResponse updateAirspace(AirspaceVo param) {
        AirspacePO exist = findOne(param.getId());
        if(null==exist)throw new BaseBusinessException(BaseBusinessError.NOT_FOUND);
        AirspacePO po = new AirspacePO();
        BeanUtils.copyProperties(param,po);
        po.setLocation(new StringBuffer(po.getProvinceName()).append(po.getCityName()).append(po.getAreaName()).toString());
        save(po);
        return new WebResponse("修改成功！");
    }
}

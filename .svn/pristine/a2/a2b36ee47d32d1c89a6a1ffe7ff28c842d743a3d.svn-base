package com.hfocean.uavportal.console.airspace.impl;

import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;
import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.console.airspace.service.AirspaceService;
import com.hfocean.uavportal.console.airspace.vo.AirspaceVo;
import com.hfocean.uavportal.core.airspace.po.AirspacePO;
import com.hfocean.uavportal.core.common.service.impl.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
* 
* @author Leslie.Lam
* @create 2017-07-31 14:15
**/
@Service("consoleAirspaceService")
public class AirspaceServiceImpl extends BaseServiceImpl<AirspacePO,String> implements AirspaceService {

    @Override
    public WebResponse addAirspace(AirspaceVo param) {
        AirspacePO exist = findOne(param.getId());
        if(null!=exist)throw new BaseBusinessException(BaseBusinessError.FORBIDDEN);
        AirspacePO po = new AirspacePO();
        BeanUtils.copyProperties(param,po);
        po.setLocation(new StringBuffer(po.getProvinceName()).append(po.getCityName()).append(po.getAreaName()).toString());
        save(po);
        return new WebResponse("添加成功！");
    }

    @Override
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

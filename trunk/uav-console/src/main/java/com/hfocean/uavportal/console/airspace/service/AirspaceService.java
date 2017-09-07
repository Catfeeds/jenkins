package com.hfocean.uavportal.console.airspace.service;

import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.console.airspace.vo.AirspaceVo;
import com.hfocean.uavportal.core.airspace.po.AirspacePO;
import com.hfocean.uavportal.core.common.service.BaseService;

/**
 * @author Leslie.Lam
 * @create 2017-07-31 14:15
 **/
public interface AirspaceService extends BaseService<AirspacePO,String> {

    /**
     * 添加空域
     * @param param
     * @return
     */
    WebResponse addAirspace(AirspaceVo param);

    /**
     * 修改空域
     * @param param
     * @return
     */
    WebResponse updateAirspace(AirspaceVo param);
}

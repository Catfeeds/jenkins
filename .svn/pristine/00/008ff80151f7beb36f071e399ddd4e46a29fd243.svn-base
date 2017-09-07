package com.hfocean.uavportal.console.content.service;

import com.hfocean.uavportal.console.content.param.BannerParam;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.service.BaseService;
import com.hfocean.uavportal.core.content.po.BannerPO;

public interface IBannerService extends BaseService<BannerPO,String> {
	Pager findBanners(Pager pager)throws Exception;
	int saveBanner(BannerParam param)throws Exception;
	int updateBanner(String bannerid, BannerParam param)throws Exception;
	int deleteBanner(String bannerid)throws Exception;
}

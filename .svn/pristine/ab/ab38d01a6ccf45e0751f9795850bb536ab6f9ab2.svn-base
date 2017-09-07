package com.hfocean.uavportal.console.content.service.impl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hfocean.uavportal.auth.base.utils.AuthAppContextHelper;
import com.hfocean.uavportal.console.content.param.BannerParam;
import com.hfocean.uavportal.console.content.service.IBannerService;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.service.impl.BaseServiceImpl;
import com.hfocean.uavportal.core.content.enm.BannerEnum;
import com.hfocean.uavportal.core.content.po.BannerPO;


/**
 * @author guan.sj
 */
@Service
@Transactional
public class BannerServiceImpl extends BaseServiceImpl<BannerPO,String> implements IBannerService {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Pager findBanners(Pager pager) throws Exception {
		String hql = "select t "
				+ " from BannerVO t"
				+ " where t.isDelete="+BannerEnum.notDelete.getCode()
				+ " order by t.sortOrder ASC";
		return findByPage(hql, pager, null);
	}

	@Override
	public int saveBanner(BannerParam param) throws Exception {
		BannerPO banner = new BannerPO();
		banner.setImgUrl(param.getImgUrl());
		banner.setImgLink(param.getImgLink());
		banner.setTitleDesc(param.getTitleDesc());
		Integer sortOrder = param.getSortOrder();
		if(sortOrder==null)sortOrder=0;
		banner.setSortOrder(sortOrder);
		banner.setIsHidden(param.getIsHidden());
		banner.setIsDelete(BannerEnum.notDelete.getCode());
		banner.setCreateTime(new Date(System.currentTimeMillis()));
		String creator = AuthAppContextHelper.getSysUserName();
		banner.setCreator(creator);
		save(banner);
		return 1;
	}

	@Override
	public int updateBanner(String bannerid, BannerParam param) throws Exception {
		BannerPO banner = findOne(bannerid);
		if(banner!=null){
			banner.setImgUrl(param.getImgUrl());
			banner.setImgLink(param.getImgLink());
			banner.setTitleDesc(param.getTitleDesc());
			Integer sortOrder = param.getSortOrder();
			if(sortOrder==null)sortOrder=0;
			banner.setSortOrder(sortOrder);
			banner.setIsHidden(param.getIsHidden());
			save(banner);
		}
		return 1;
	}

	@Override
	public int deleteBanner(String bannerid) throws Exception {
		BannerPO banner = findOne(bannerid);
		if(banner!=null){
			banner.setIsDelete(BannerEnum.isDelete.getCode());
			save(banner);
		}
		return 1;
	}
	
}
	



	


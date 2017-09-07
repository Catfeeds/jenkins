package com.hfocean.uavportal.web.content.service;

import java.util.List;

import com.hfocean.uavportal.core.common.service.BaseService;
import com.hfocean.uavportal.core.content.po.ContentPO;
import com.hfocean.uavportal.web.content.vo.BannerVO;
import com.hfocean.uavportal.web.content.vo.MenuChildrenVO;
import com.hfocean.uavportal.web.content.vo.MenuVO;

public interface IContentService extends BaseService<ContentPO,String> {

	List<MenuVO> findMenus()throws Exception;
	
	List<MenuChildrenVO> findSecondMenus(String menuid)throws Exception;
	
	List<BannerVO> findBanners()throws Exception;
}

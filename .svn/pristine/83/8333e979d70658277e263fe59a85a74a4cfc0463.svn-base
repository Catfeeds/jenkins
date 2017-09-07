package com.hfocean.uavportal.console.content.service;

import java.util.List;

import com.hfocean.uavportal.console.content.param.ContentParam;
import com.hfocean.uavportal.console.content.vo.ContentTextVO;
import com.hfocean.uavportal.console.content.vo.MenuChildrenVO;
import com.hfocean.uavportal.console.content.vo.MenuColumnVO;
import com.hfocean.uavportal.console.content.vo.MenuVO;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.service.BaseService;
import com.hfocean.uavportal.core.content.po.ContentPO;

public interface IContentService extends BaseService<ContentPO,String> {

	List<MenuColumnVO> findSysmenus()throws Exception;
	
	List<MenuVO> findMenus()throws Exception;
	
	List<MenuChildrenVO> findSecondMenus(String menuid)throws Exception;
	
	Pager findContent(String menuid, Integer page, Integer size)throws Exception;
	
	int saveContent(ContentParam param)throws Exception;
	
	int updateContent(String pageId, ContentParam param)throws Exception;
	
	int deleteContent(String pageId)throws Exception;
	
	ContentTextVO findContentText(String pageid, Integer type)throws Exception;
	
}

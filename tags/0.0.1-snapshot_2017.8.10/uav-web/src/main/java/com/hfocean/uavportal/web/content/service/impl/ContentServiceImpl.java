package com.hfocean.uavportal.web.content.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hfocean.uavportal.core.common.service.impl.BaseServiceImpl;
import com.hfocean.uavportal.core.content.enm.BannerEnum;
import com.hfocean.uavportal.core.content.enm.ContentEnum;
import com.hfocean.uavportal.core.content.po.BannerPO;
import com.hfocean.uavportal.core.content.po.ContentPO;
import com.hfocean.uavportal.core.content.repository.BannerRepository;
import com.hfocean.uavportal.web.content.service.IContentService;
import com.hfocean.uavportal.web.content.vo.BannerVO;
import com.hfocean.uavportal.web.content.vo.MenuChildrenVO;
import com.hfocean.uavportal.web.content.vo.MenuVO;


/**
 * @author guan.sj
 */
@Service
@Transactional
public class ContentServiceImpl extends BaseServiceImpl<ContentPO,String> implements IContentService {
	
	@Autowired
	private BannerRepository bannerRepository;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<MenuVO> findMenus() throws Exception {
		String sql = "select t from MenuVO t where t.parentId is null and t.status=?1 order by t.sortOrder,t.createTime";
		TypedQuery<MenuVO> query = em.createQuery(sql,MenuVO.class);
		query.setParameter(1, ContentEnum.正常.getCode());
		List<MenuVO> resultList = query.getResultList();
		return resultList;
	}
	
	@Override
	public List<MenuChildrenVO> findSecondMenus(String menuid) throws Exception {
		String sql = "select t from MenuChildrenVO t where t.parentId=?1 and t.status=?2 order by t.sortOrder,t.createTime";
		TypedQuery<MenuChildrenVO> query = em.createQuery(sql,MenuChildrenVO.class);
		query.setParameter(1, menuid);
		query.setParameter(2, ContentEnum.正常.getCode());
		List<MenuChildrenVO> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<BannerVO> findBanners() throws Exception {
		List<BannerPO> bannerPOList = bannerRepository.findByIsDeleteAndIsHiddenOrderBySortOrderAsc(BannerEnum.notDelete.getCode(), BannerEnum.notHidden.getCode());
		List<BannerVO> result = new ArrayList<BannerVO>();
		BannerVO banner;
			for(BannerPO bannerPO : bannerPOList){
				banner = new BannerVO();
				BeanUtils.copyProperties(bannerPO, banner);
				result.add(banner);
			}
		return result;
	}


	
	
}

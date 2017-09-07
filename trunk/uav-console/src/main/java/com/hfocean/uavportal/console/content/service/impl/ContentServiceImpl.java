package com.hfocean.uavportal.console.content.service.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;
import com.hfocean.uavportal.auth.base.utils.AuthAppContextHelper;
import com.hfocean.uavportal.console.content.param.ContentAdditionParam;
import com.hfocean.uavportal.console.content.param.ContentParam;
import com.hfocean.uavportal.console.content.service.IContentService;
import com.hfocean.uavportal.console.content.vo.ContentTextVO;
import com.hfocean.uavportal.console.content.vo.MenuChildrenVO;
import com.hfocean.uavportal.console.content.vo.MenuColumnVO;
import com.hfocean.uavportal.console.content.vo.MenuVO;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.service.impl.BaseServiceImpl;
import com.hfocean.uavportal.core.content.enm.ContentEnum;
import com.hfocean.uavportal.core.content.po.ContentAdditionPO;
import com.hfocean.uavportal.core.content.po.ContentPO;
import com.hfocean.uavportal.core.content.po.ContentTextPO;
import com.hfocean.uavportal.core.content.repository.ContentAdditionRepository;
import com.hfocean.uavportal.core.content.repository.ContentRepository;
import com.hfocean.uavportal.core.content.repository.ContentTextRepository;


/**
 * @author guan.sj
 */
@Service
@Transactional
public class ContentServiceImpl extends BaseServiceImpl<ContentPO,String> implements IContentService {
	
	@Autowired
	private ContentRepository contentRepository;
	
	@Autowired
	private ContentTextRepository contentTextRepository;
	
	@Autowired
	private ContentAdditionRepository contentAdditionRepository;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<MenuColumnVO> findSysmenus() throws Exception {
		String sql = "select t "
				+ "from MenuColumnVO t "
				+ "where t.parentId is null "
				+ "and t.status in ("+ContentEnum.正常.getCode()+","+ContentEnum.禁用.getCode()+")";
		List<MenuColumnVO> resultList = em.createQuery(sql,MenuColumnVO.class).getResultList();
		return resultList;
	}



	@Override
	public List<MenuVO> findMenus() throws Exception {
		String sql = "select t "
				+ "from MenuVO t "
				+ "where t.parentId is null "
				+ "and t.status in ("+ContentEnum.正常.getCode()+","+ContentEnum.禁用.getCode()+")";
		List<MenuVO> resultList = em.createQuery(sql,MenuVO.class).getResultList();
		return resultList;
	}
	
	@Override
	public List<MenuChildrenVO> findSecondMenus(String menuid) throws Exception {
		String sql = "select t "
				+ "from MenuChildrenVO t "
				+ "where t.parentId='"+menuid+"' "
				+ "and t.status in ("+ContentEnum.正常.getCode()+","+ContentEnum.禁用.getCode()+")";
		List<MenuChildrenVO> resultList = em.createQuery(sql,MenuChildrenVO.class).getResultList();
		return resultList;
	}

	@Override
	public Pager findContent(String menuid, Integer page, Integer size) throws Exception {
		StringBuilder hql = new StringBuilder("from ContentVO t where 1=1");
		hql.append(" and t.status in ("+ContentEnum.正常.getCode()+","+ContentEnum.禁用.getCode()+")");
		hql.append(" and t.categoryId = '"+menuid+"'");
		hql.append(" order by t.id desc");
        return findByPage(hql.toString(), new Pager(page, size),null);
	}
	
	@Override
	public int saveContent(ContentParam param) throws Exception {
		String categoryId = param.getCategoryId();
		ContentPO contentPO = new ContentPO();
		contentPO.setTbContentCategoryId(categoryId);
		contentPO.setTitle(param.getTitle());
		contentPO.setSubTitle(param.getSubTitle());
		contentPO.setTitleDesc(param.getTitleDesc());
		contentPO.setCreateTime(new Date(System.currentTimeMillis()));
		contentPO.setCreator(AuthAppContextHelper.getSysUserName());
		contentPO.setSortOrder(param.getSortOrder());
		contentPO.setStatus(param.getStatus());
		contentPO.setCheckNumber(0);
		ContentPO save = contentRepository.save(contentPO);
		String id = save.getId();
		
		String content = param.getContent();
		ContentTextPO text = new ContentTextPO();
		text.setTbContentId(id);
		text.setTbContentCategoryId(categoryId);
		text.setContent(content);
		contentTextRepository.save(text);
		
		List<ContentAdditionParam> attachments = param.getAttachments();
		if(attachments!=null){
			for(ContentAdditionParam att : attachments){
				ContentAdditionPO addtionPO = new ContentAdditionPO();
				addtionPO.setTbContentId(id);
				addtionPO.setContentDesc(att.getContentDesc());
				addtionPO.setUrl(att.getUrl());
				addtionPO.setType(att.getType());
				addtionPO.setCreateTime(new Date(System.currentTimeMillis()));
				addtionPO.setCreator(AuthAppContextHelper.getSysUserName());
				addtionPO.setStatus(ContentEnum.正常.getCode());
				contentAdditionRepository.save(addtionPO);
			}
		}
		return 1;
	}
	
	@Override
	public int updateContent(String pageId, ContentParam param) throws Exception {
		ContentPO contentPO = contentRepository.findOne(pageId);
		if(contentPO!=null){
			contentPO.setTitle(param.getTitle());
			contentPO.setSubTitle(param.getSubTitle());
			contentPO.setTitleDesc(param.getTitleDesc());
			contentPO.setUpdateTime(new Date(System.currentTimeMillis()));
			contentPO.setUpdatePeople(AuthAppContextHelper.getSysUserName());
			contentPO.setSortOrder(param.getSortOrder());
			contentPO.setStatus(param.getStatus());
			ContentPO save = contentRepository.save(contentPO);
			if(save!=null){
				String id = save.getId();
				ContentTextPO text = new ContentTextPO();
				text.setTbContentId(id);
				text.setContent(param.getContent());
				text.setTbContentCategoryId(save.getTbContentCategoryId());
				contentTextRepository.save(text);
				
				List<ContentAdditionParam> attachments = param.getAttachments();
				if(attachments!=null){
					for(ContentAdditionParam att : attachments){
						ContentAdditionPO additionPO = new ContentAdditionPO();
						if(!StringUtils.isEmpty(att.getId())){
							additionPO.setId(att.getId());
						}else{
							additionPO.setCreateTime(new Date(System.currentTimeMillis()));
							additionPO.setCreator(AuthAppContextHelper.getSysUserName());
						}
						additionPO.setTbContentId(id);
						additionPO.setContentDesc(att.getContentDesc());
						additionPO.setUrl(att.getUrl());
						additionPO.setType(att.getType());
						additionPO.setStatus(ContentEnum.正常.getCode());
						contentAdditionRepository.save(additionPO);
					}
				}
			}
		}else{
			throw new BaseBusinessException(BaseBusinessError.INTER_ERROR.getCode(), "没有找到稿件id的内容");
		}
		return 1;
	}
	
	@Override
	public int deleteContent(String pageId) throws Exception {
		List<ContentAdditionPO> additonList = contentAdditionRepository.findByTbContentId(pageId);
		for(ContentAdditionPO addition : additonList){
			addition.setStatus(ContentEnum.删除.getCode());
			contentAdditionRepository.save(addition);
		}
		ContentPO content = contentRepository.findOne(pageId);
		if(content!=null){
			content.setStatus(ContentEnum.删除.getCode());
			contentRepository.save(content);
		}
		return 1;
	}

	@Override
	public ContentTextVO findContentText(String pageid, Integer type) throws Exception {
		ContentTextVO result = null;
		String sql;
		if(type==ContentEnum.稿件内容.getCode()){
			sql = "select t from ContentTextVO t where t.categoryId='"+pageid+"'";
		}else{
			sql = "select t from ContentTextVO t where t.id='"+pageid+"'";
		}
		List<ContentTextVO> resultList = em.createQuery(sql,ContentTextVO.class).getResultList();
		if(resultList!=null&&resultList.size()>0)result = resultList.get(0); 
		return result;
	}
	
}

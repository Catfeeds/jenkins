package com.hfocean.uavportal.core.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.service.impl.BaseServiceImpl;
import com.hfocean.uavportal.core.content.enm.ContentEnum;
import com.hfocean.uavportal.core.content.enm.LeaveMessageEnum;
import com.hfocean.uavportal.core.content.enm.ReaderEnum;
import com.hfocean.uavportal.core.content.param.LeaveMessageParam;
import com.hfocean.uavportal.core.content.po.AnnouncementPO;
import com.hfocean.uavportal.core.content.po.ContentPO;
import com.hfocean.uavportal.core.content.po.LeaveMessagePO;
import com.hfocean.uavportal.core.content.repository.AnnouncementRepository;
import com.hfocean.uavportal.core.content.repository.LeaveMessageRepository;
import com.hfocean.uavportal.core.content.service.IContentServiceCore;
import com.hfocean.uavportal.core.content.vo.AnnouncementTextVO;
import com.hfocean.uavportal.core.content.vo.AnnouncementVO;
import com.hfocean.uavportal.core.content.vo.ContentTextVO;


/**
 * @author guan.sj
 */
@Service
@Transactional
public class ContentServiceCoreImpl extends BaseServiceImpl<ContentPO,String> implements IContentServiceCore {
	
	@Autowired
	private LeaveMessageRepository leaveMessageRepository;
	
	@Autowired
	private AnnouncementRepository announcementRepository;
	
	
	@PersistenceContext
	private EntityManager em;


	@Override
	public LeaveMessagePO saveLeaveMessage(LeaveMessageParam param, Integer source, Integer type) throws Exception {
		LeaveMessagePO message = new LeaveMessagePO();
		BeanUtils.copyProperties(param, message);
		message.setCreateTime(new Date(System.currentTimeMillis()));
		message.setSource(source);
		message.setStatus(LeaveMessageEnum.未读.getCode());
		message.setType(type);
		leaveMessageRepository.save(message);
		return message;
	}
	
	@Override
	public List<AnnouncementVO> findAnnouncements() throws Exception {
		List<AnnouncementPO> announcementList = announcementRepository.findByStatusOrderByCreateTimeDesc(ContentEnum.正常.getCode());
		List<AnnouncementVO> result = new ArrayList<AnnouncementVO>();
		AnnouncementVO anVO;
		for(AnnouncementPO an : announcementList){
			anVO = new AnnouncementVO();
			BeanUtils.copyProperties(an, anVO);
			result.add(anVO);
		}
		return result;
	}

	@Override
	public AnnouncementTextVO findAnnouncementText(String announcementid) throws Exception {
		AnnouncementTextVO result = null;
		TypedQuery<AnnouncementTextVO> query = em.createQuery("select t from AnnouncementTextVO t where t.id='"+announcementid+"'",AnnouncementTextVO.class);
		List<AnnouncementTextVO> resultList = query.getResultList();
		if(resultList!=null&&resultList.size()>0)result = resultList.get(0);
		try {
			if(result!=null&&result.getId()!=null)
				updateReaderNumber(result.getId(), ReaderEnum.公告.getCode());
		} catch (Exception e) {
		}
		return result;
	}

	@Override
	public void updateReaderNumber(String id, Integer type) throws Exception {
		Query query = em.createNativeQuery("{call p_reader_update(?,?)}");
		query.setParameter(1, id);
		if(type==ReaderEnum.稿件.getCode()){
			query.setParameter(2, ReaderEnum.稿件.getCode());
			query.executeUpdate();
		}
		if(type==ReaderEnum.公告.getCode()){
			query.setParameter(2, ReaderEnum.公告.getCode());
			query.executeUpdate();
		}
	}
	
	@Override
	public Pager findContent(String menuid, Pager pager) throws Exception {
		StringBuilder hql = new StringBuilder("from ContentVO t where 1=1");
		hql.append(" and t.status="+ContentEnum.正常.getCode());
		hql.append(" and t.categoryId='"+menuid+"'");
		hql.append(" order by t.createTime desc");
        return findByPage(hql.toString(), pager,null);
	}

	@Override
	public ContentTextVO findContentText(String pageid, Integer type) throws Exception {
		ContentTextVO result=null;
		String sql;
		if(type==ContentEnum.稿件内容.getCode()){
			sql = "select t from ContentTextVO t where t.categoryId='"+pageid+"'";
		}else{
			sql = "select t from ContentTextVO t where t.id='"+pageid+"'";
		}
		TypedQuery<ContentTextVO> createQuery = em.createQuery(sql,ContentTextVO.class);
		List<ContentTextVO> resultList = createQuery.getResultList();
		if(resultList!=null&&resultList.size()>0)result = resultList.get(0);
		try {
			if(result!=null&&result.getId()!=null)
				updateReaderNumber(result.getId(), ReaderEnum.稿件.getCode());
		} catch (Exception e) {
		}
		return result;
	}
	
	
}

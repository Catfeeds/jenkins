package com.hfocean.uavportal.console.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;
import com.hfocean.common.notify.NotifyHelper;
import com.hfocean.uavportal.auth.base.utils.AuthAppContextHelper;
import com.hfocean.uavportal.console.content.param.AnnouncementAdditionParam;
import com.hfocean.uavportal.console.content.param.AnnouncementTextParam;
import com.hfocean.uavportal.console.content.service.IAnnouncementService;
import com.hfocean.uavportal.console.content.vo.AnnouncementAdditionVO;
import com.hfocean.uavportal.console.content.vo.AnnouncementTextVO;
import com.hfocean.uavportal.console.notify.provider.WechatImgTextProvider;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.service.impl.BaseServiceImpl;
import com.hfocean.uavportal.core.content.enm.ContentEnum;
import com.hfocean.uavportal.core.content.po.AnnouncementAdditionPO;
import com.hfocean.uavportal.core.content.po.AnnouncementPO;
import com.hfocean.uavportal.core.content.po.AnnouncementTextPO;
import com.hfocean.uavportal.core.content.repository.AnnouncementAdditionRepository;
import com.hfocean.uavportal.core.content.repository.AnnouncementRepository;
import com.hfocean.uavportal.core.content.repository.AnnouncementTextRepository;


/**
 * @author guan.sj
 */
@Service
@Transactional
public class AnnouncementServiceImpl extends BaseServiceImpl<AnnouncementPO,String> implements IAnnouncementService {
	
	@Autowired
	private AnnouncementRepository announcementRepository;
	
	@Autowired
	private AnnouncementAdditionRepository announcementAdditionRepository;
	
	@Autowired
	private AnnouncementTextRepository announcementTextRepository;
	
	@Override
	public Pager findAnnouncements(Pager page) throws Exception {
		StringBuilder hql = new StringBuilder("from AnnouncementVO t where 1=1");
		hql.append(" and t.status in ("+ContentEnum.正常.getCode()+","+ContentEnum.禁用.getCode()+")");
		hql.append(" order by t.createTime desc");
        return findByPage(hql.toString(), page,null);
	}

	@Override
	public AnnouncementTextVO findAnnouncementText(String announcementId) throws Exception {
		AnnouncementPO announ = announcementRepository.findOne(announcementId);
		AnnouncementTextVO result = new AnnouncementTextVO();
		if(announ!=null){
			BeanUtils.copyProperties(announ, result);
			AnnouncementTextPO text = announcementTextRepository.findOne(announcementId);
			if(text!=null)
				result.setContent(text.getContent());
			List<AnnouncementAdditionPO> additionList = announcementAdditionRepository.findByTbAnnouncementId(announcementId);
			List<AnnouncementAdditionVO> additionVOList = new ArrayList<AnnouncementAdditionVO>();
			AnnouncementAdditionVO additionVO;
			for(AnnouncementAdditionPO addition : additionList){
				additionVO = new AnnouncementAdditionVO();
				BeanUtils.copyProperties(addition, additionVO);
				additionVOList.add(additionVO);
			}
			if(additionVOList.size()>0)
				result.setAttachments(additionVOList);
			
		}
		return result;
	}

	@Override
	public int saveAnnouncementText(AnnouncementTextParam param) throws Exception {
		AnnouncementPO announ = new AnnouncementPO();
		announ.setTitle(param.getTitle());
		announ.setSubTitle(param.getSubTitle());
		Date createTime = new Date(System.currentTimeMillis());
		announ.setCreateTime(createTime);
		String creator = AuthAppContextHelper.getSysUserName();
		announ.setCreator(creator);
		announ.setCheckNumber(0);
		announ.setIsRecommend(ContentEnum.不推荐.getCode());
		announ.setStatus(param.getStatus());
		AnnouncementPO save = announcementRepository.save(announ);
		if(save!=null){
			String id = save.getId();
			AnnouncementTextPO text = new AnnouncementTextPO();
			text.setContent(param.getContent());
			text.setTbAnnouncementId(id);
			announcementTextRepository.save(text);
			List<AnnouncementAdditionParam> attachments = param.getAttachments();
			AnnouncementAdditionPO additionPO;
			if(attachments!=null){
				for(AnnouncementAdditionParam addition : attachments){
					additionPO = new AnnouncementAdditionPO();
					additionPO.setTbAnnouncementId(id);
					additionPO.setContentDesc(addition.getContentDesc());
					additionPO.setUrl(addition.getUrl());
					additionPO.setType(addition.getType());
					additionPO.setCreateTime(createTime);
					additionPO.setCreator(creator);
					additionPO.setStatus(ContentEnum.正常.getCode());
					announcementAdditionRepository.save(additionPO);
				}
			}
			//微信推送
			if(param.getIsToAll()==1)
				NotifyHelper.push(new WechatImgTextProvider(id));
		}
		return 1;
	}

	@Override
	public int updateAnnouncementText(String announcementId, AnnouncementTextParam param) throws Exception {
		AnnouncementPO announ = announcementRepository.findOne(announcementId);
		if(announ!=null){
			announ.setTitle(param.getTitle());
			announ.setSubTitle(param.getSubTitle());
			announ.setStatus(param.getStatus());
			AnnouncementPO save = announcementRepository.save(announ);
			if(save!=null){
				String content = param.getContent();
				AnnouncementTextPO textPO = new AnnouncementTextPO();
				textPO.setTbAnnouncementId(announcementId);
				textPO.setContent(content);
				announcementTextRepository.save(textPO);
				
				List<AnnouncementAdditionParam> attachments = param.getAttachments();
				if(attachments!=null&&attachments.size()>0){
					AnnouncementAdditionPO additionPO;
					for(AnnouncementAdditionParam addition : attachments){
						additionPO = new AnnouncementAdditionPO();
						if(addition.getId()!=null){
							additionPO.setId(addition.getId());
						}else{
							additionPO.setCreateTime(new Date(System.currentTimeMillis()));
							additionPO.setCreator(AuthAppContextHelper.getSysUserName());
						}
						additionPO.setTbAnnouncementId(announcementId);
						additionPO.setContentDesc(addition.getContentDesc());
						additionPO.setUrl(addition.getUrl());
						additionPO.setType(addition.getType());
						additionPO.setStatus(ContentEnum.正常.getCode());
						announcementAdditionRepository.save(additionPO);
					}
				}
			}
		}else{
			throw new BaseBusinessException(BaseBusinessError.INTER_ERROR.getCode(), "没有找到公告id的内容");
		}
		return 1;
	}

	@Override
	public int deleteAnnouncementText(String announcementId) throws Exception {
		List<AnnouncementAdditionPO> additonList = announcementAdditionRepository.findByTbAnnouncementId(announcementId);
		for(AnnouncementAdditionPO addition : additonList){
			addition.setStatus(ContentEnum.删除.getCode());
			announcementAdditionRepository.save(addition);
		}
		AnnouncementPO announ = announcementRepository.findOne(announcementId);
		if(announ!=null){
			announ.setStatus(ContentEnum.删除.getCode());
			announcementRepository.save(announ);
		}
		return 1;
	}
	
	
}
	



	


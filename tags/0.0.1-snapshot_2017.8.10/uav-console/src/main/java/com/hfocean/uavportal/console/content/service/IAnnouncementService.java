package com.hfocean.uavportal.console.content.service;

import com.hfocean.uavportal.console.content.param.AnnouncementTextParam;
import com.hfocean.uavportal.console.content.vo.AnnouncementTextVO;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.service.BaseService;
import com.hfocean.uavportal.core.content.po.AnnouncementPO;

public interface IAnnouncementService extends BaseService<AnnouncementPO,String> {
	Pager findAnnouncements(Pager page)throws Exception;
	AnnouncementTextVO findAnnouncementText(String announcementId)throws Exception;
	int saveAnnouncementText(AnnouncementTextParam param)throws Exception;
	int updateAnnouncementText(String announcementId, AnnouncementTextParam param)throws Exception;
	int deleteAnnouncementText(String announcementId)throws Exception;
}

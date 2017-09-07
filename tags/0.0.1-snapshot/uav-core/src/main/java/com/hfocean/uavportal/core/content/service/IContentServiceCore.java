package com.hfocean.uavportal.core.content.service;

import java.util.List;

import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.service.BaseService;
import com.hfocean.uavportal.core.content.param.LeaveMessageParam;
import com.hfocean.uavportal.core.content.po.ContentPO;
import com.hfocean.uavportal.core.content.po.LeaveMessagePO;
import com.hfocean.uavportal.core.content.vo.AnnouncementTextVO;
import com.hfocean.uavportal.core.content.vo.AnnouncementVO;
import com.hfocean.uavportal.core.content.vo.ContentTextVO;

public interface IContentServiceCore extends BaseService<ContentPO,String> {

	/**在线留言
	 * @param param
	 * @param source 来源  @see LeaveMessageEnum
	 * @param type 留言类型  @see LeaveMessageEnum
	 * @return
	 * @throws Exception
	 */
	LeaveMessagePO saveLeaveMessage(LeaveMessageParam param, Integer source, Integer type)throws Exception;
	
	List<AnnouncementVO> findAnnouncements()throws Exception;
	
	AnnouncementTextVO findAnnouncementText(String announcementid)throws Exception;
	
	void updateReaderNumber(String id,Integer type)throws Exception;
	
	Pager findContent(String menuid, Pager pager)throws Exception;
	
	ContentTextVO findContentText(String pageid, Integer type)throws Exception;
}

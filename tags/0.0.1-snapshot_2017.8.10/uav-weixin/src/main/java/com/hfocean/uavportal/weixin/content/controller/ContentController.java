package com.hfocean.uavportal.weixin.content.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hfocean.common.exception.type.RequestParamException;
import com.hfocean.common.util.ValidateUtil;
import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.content.enm.ContentEnum;
import com.hfocean.uavportal.core.content.enm.LeaveMessageEnum;
import com.hfocean.uavportal.core.content.enm.ReaderEnum;
import com.hfocean.uavportal.core.content.param.LeaveMessageParam;
import com.hfocean.uavportal.core.content.service.IContentServiceCore;
import com.hfocean.uavportal.core.content.vo.AnnouncementTextVO;
import com.hfocean.uavportal.core.content.vo.ContentTextVO;


@Controller
public class ContentController{

	@Autowired
	private IContentServiceCore iContentServiceCore;
	
	/**获取系统公告
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/announcements", method = RequestMethod.GET)
	@ResponseBody
	public WebResponse announcements()throws Exception {
		return new WebResponse(iContentServiceCore.findAnnouncements());
	}
	
	/**获取公告内容
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/announcement/{announcementid}", method = RequestMethod.GET)
	@ResponseBody
	public WebResponse announcementText(@PathVariable String announcementid)throws Exception {
		return new WebResponse(iContentServiceCore.findAnnouncementText(announcementid));
	}
	
	/**新增在线留言
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	@ResponseBody
	public WebResponse leaveMessage(LeaveMessageParam param)throws Exception {
		ValidateUtil.validate(param);
		iContentServiceCore.saveLeaveMessage(param, LeaveMessageEnum.微信.getCode(), LeaveMessageEnum.留言.getCode());
		return new WebResponse();
	}
	
	/**获取行业资讯列表
	 * @param menuid
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/portal/menu/{menuid}/content", method = RequestMethod.GET)
	@ResponseBody
	public WebResponse contents(@PathVariable("menuid")String menuid, HttpServletRequest requesst)throws Exception {
		return new WebResponse(iContentServiceCore.findContent(menuid, new Pager(requesst)));
	}
	
	/**获取行业资讯内容
	 * @param pageid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/portal/page/{pageid}", method = RequestMethod.GET)
	@ResponseBody
	public WebResponse contentText(@PathVariable("pageid")String pageid,Integer type)throws Exception {
		if(pageid==null)throw new RequestParamException();
		type=ContentEnum.稿件列表.getCode();
		return new WebResponse(iContentServiceCore.findContentText(pageid, type));
	}
	
}

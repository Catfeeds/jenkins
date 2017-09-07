package com.hfocean.uavportal.web.content.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.hfocean.uavportal.core.content.enm.LeaveMessageEnum;
import com.hfocean.uavportal.core.content.enm.ReaderEnum;
import com.hfocean.uavportal.core.content.param.LeaveMessageParam;
import com.hfocean.uavportal.core.content.service.IContentServiceCore;
import com.hfocean.uavportal.core.content.vo.AnnouncementTextVO;
import com.hfocean.uavportal.core.content.vo.ContentTextVO;
import com.hfocean.uavportal.web.content.service.IContentService;
import com.hfocean.uavportal.web.geetest.sdk.GeetestHelper;


@Controller
public class ContentController{

	@Autowired
	private IContentService contentService;
	
	@Autowired
	private IContentServiceCore iContentServiceCore;
	
	/**获取一级菜单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/portal/menus", method = RequestMethod.GET)
	@ResponseBody
	public WebResponse menus()throws Exception {
		return new WebResponse(contentService.findMenus());
	}
	
	/**获取二级菜单以及一下菜单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/portal/menus/{menuid}", method = RequestMethod.GET)
	@ResponseBody
	public WebResponse secondMenus(@PathVariable("menuid")String menuid)throws Exception {
		return new WebResponse(contentService.findSecondMenus(menuid));
	}

	/**获取稿件列表
	 * @param menuid
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/portal/menu/{menuid}/content", method = RequestMethod.GET)
	@ResponseBody
	public WebResponse contents(@PathVariable("menuid")String menuid, HttpServletRequest request)throws Exception {
		return new WebResponse(iContentServiceCore.findContent(menuid, new Pager(request)));
	}
	
	/**获取稿件内容
	 * @param pageid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/portal/page/{pageid}", method = RequestMethod.GET)
	@ResponseBody
	public WebResponse contentText(@PathVariable("pageid")String pageid,Integer type)throws Exception {
		if(pageid==null)throw new RequestParamException();
		return new WebResponse(iContentServiceCore.findContentText(pageid, type));
	}
	
	/**获取系统公告
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/portal/announcements", method = RequestMethod.GET)
	@ResponseBody
	public WebResponse announcements()throws Exception {
		return new WebResponse(iContentServiceCore.findAnnouncements());
	}
	
	/**获取公共内容
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/portal/announcement/{announcementid}", method = RequestMethod.GET)
	@ResponseBody
	public WebResponse announcementText(@PathVariable String announcementid)throws Exception {
		return new WebResponse(iContentServiceCore.findAnnouncementText(announcementid));
	}
	
	/**新增在线留言
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/portal/message", method = RequestMethod.POST)
	@ResponseBody
	public WebResponse leaveMessage(LeaveMessageParam param, HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		GeetestHelper.geetestPost(request, response);
		ValidateUtil.validate(param);
		iContentServiceCore.saveLeaveMessage(param, LeaveMessageEnum.PC.getCode(), LeaveMessageEnum.留言.getCode());
		return new WebResponse();
	}
	
	/**获取轮播图
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/banners", method = RequestMethod.GET)
	@ResponseBody
	public WebResponse banners()throws Exception {
		return new WebResponse(contentService.findBanners());
	}
}

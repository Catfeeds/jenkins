package com.hfocean.uavportal.console.content.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hfocean.common.util.ValidateUtil;
import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.auth.system.permission.annotation.MjPermission;
import com.hfocean.uavportal.auth.system.permission.eumn.PermissionEumn;
import com.hfocean.uavportal.console.content.param.AnnouncementTextParam;
import com.hfocean.uavportal.console.content.service.IAnnouncementService;
import com.hfocean.uavportal.core.common.bean.Pager;


@Controller
public class AnnouncementController{

	@Autowired
	private IAnnouncementService iAnnouncementService;
	
	/**获取系统公告
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/announcements", method = RequestMethod.GET)
	@ResponseBody
	@MjPermission(values={PermissionEumn.查询系统公告列表})
	public WebResponse announcements(HttpServletRequest request)throws Exception {
		return new WebResponse(iAnnouncementService.findAnnouncements(new Pager(request)));
	}
	
	/**获取公共内容
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/announcement/{announcementid}", method = RequestMethod.GET)
	@ResponseBody
	@MjPermission(values={PermissionEumn.查询公告详情})
	public WebResponse announcementText(@PathVariable(name="announcementid") String announcementId)throws Exception {
		return new WebResponse(iAnnouncementService.findAnnouncementText(announcementId));
	}
	
	/**创建公告
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/announcement", method = RequestMethod.POST)
	@ResponseBody
	@MjPermission(values={PermissionEumn.新增系统公告})
	public WebResponse announcementText(@RequestBody AnnouncementTextParam param)throws Exception {
		ValidateUtil.validate(param);
		return new WebResponse(iAnnouncementService.saveAnnouncementText(param));
	}
	
	/**更新公告
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/announcement/{announcementid}", method = RequestMethod.PUT)
	@ResponseBody
	@MjPermission(values={PermissionEumn.修改系统公告})
	public WebResponse updateAnnouncementText(@PathVariable(name="announcementid")String announcementId,@RequestBody AnnouncementTextParam param)throws Exception {
		ValidateUtil.validate(param);
		return new WebResponse(iAnnouncementService.updateAnnouncementText(announcementId,param));
	}
	
	/**删除公告
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/announcement/{announcementid}", method = RequestMethod.DELETE)
	@ResponseBody
	@MjPermission(values={PermissionEumn.删除系统公告})
	public WebResponse deleteAnnouncementText(@PathVariable(name="announcementid")String announcementId)throws Exception {
		return new WebResponse(iAnnouncementService.deleteAnnouncementText(announcementId));
	}
	
}

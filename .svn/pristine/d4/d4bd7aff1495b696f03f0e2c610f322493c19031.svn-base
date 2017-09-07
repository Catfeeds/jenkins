package com.hfocean.uavportal.console.content.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.auth.system.permission.annotation.MjPermission;
import com.hfocean.uavportal.auth.system.permission.eumn.PermissionEumn;
import com.hfocean.uavportal.console.content.param.LeaveMessageSearchParam;
import com.hfocean.uavportal.console.content.service.ILeaveMessageService;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.content.enm.LeaveMessageEnum;


@Controller
public class LeaveMessageController{

	@Autowired
	private ILeaveMessageService iLeaveMessageService;
	
	/**获取所有留言
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	@ResponseBody
	@MjPermission(values={PermissionEumn.查询留言列表})
	public WebResponse messages(LeaveMessageSearchParam param, HttpServletRequest request)throws Exception {
		return new WebResponse(iLeaveMessageService.findMessages(param, new Pager(request)));
	}
	
	/**删除留言
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/message/{messageid}", method = RequestMethod.DELETE)
	@ResponseBody
	@MjPermission(values={PermissionEumn.删除留言})
	public WebResponse deleteMessage(@PathVariable("messageid") String messageId)throws Exception {
		return new WebResponse(iLeaveMessageService.updateMessage(messageId, LeaveMessageEnum.删除.getCode()));
	}
	
	/**已读留言
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/message/{messageid}", method = RequestMethod.PUT)
	@ResponseBody
	@MjPermission(values={PermissionEumn.已读留言})
	public WebResponse updateMessage(@PathVariable("messageid") String messageId)throws Exception {
		return new WebResponse(iLeaveMessageService.updateMessage(messageId, LeaveMessageEnum.已读.getCode()));
	}
	
	/**获取留言详情
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/message/{messageid}", method = RequestMethod.GET)
	@ResponseBody
	@MjPermission
	public WebResponse findMessageInfo(@PathVariable("messageid") String messageId)throws Exception {
		return new WebResponse(iLeaveMessageService.findMessage(messageId));
	}

}

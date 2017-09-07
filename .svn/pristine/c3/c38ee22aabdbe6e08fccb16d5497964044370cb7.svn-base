package com.hfocean.uavportal.console.content.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;
import com.hfocean.common.exception.type.RequestParamException;
import com.hfocean.common.util.ValidateUtil;
import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.auth.system.permission.annotation.MjPermission;
import com.hfocean.uavportal.auth.system.permission.eumn.PermissionEumn;
import com.hfocean.uavportal.console.content.param.ContentParam;
import com.hfocean.uavportal.console.content.service.IContentService;
import com.hfocean.uavportal.console.content.vo.ContentTextVO;


@Controller
public class ContentController{

	@Autowired
	private IContentService contentService;
	
	/**获取系统菜单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sysmenus", method = RequestMethod.GET)
	@ResponseBody
	@MjPermission
	public WebResponse sysmenus()throws Exception {
		return new WebResponse(contentService.findSysmenus());
	}
	
	/**获取一级菜单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/portal/menus", method = RequestMethod.GET)
	@ResponseBody
	@MjPermission(values={PermissionEumn.获取门户一级菜单树})
	public WebResponse menus()throws Exception {
		return new WebResponse(contentService.findMenus());
	}
	
	/**获取稿件列表
	 * @param menuid
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/portal/menus/{menuid}/content", method = RequestMethod.GET)
	@ResponseBody
	@MjPermission(values={PermissionEumn.查询稿件列表})
	public WebResponse contents(@PathVariable("menuid")String menuid, Integer page, Integer size)throws Exception {
		if(menuid==null|page==null||size==null)throw new RequestParamException();
		return new WebResponse(contentService.findContent(menuid, page, size));
	}
	
	/**获取稿件内容
	 * @param pageid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/portal/page/{pageid}", method = RequestMethod.GET)
	@ResponseBody
	//@MjPermission(values={PermissionEumn.查询稿件详情})
	public WebResponse contentText(@PathVariable("pageid")String pageid,Integer type)throws Exception {
		if(pageid==null)throw new RequestParamException();
		ContentTextVO findContentText = contentService.findContentText(pageid, type);
		return new WebResponse(findContentText);
	}
	
	/**创建稿件
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/portal/page", method = RequestMethod.POST)
	@ResponseBody
	@MjPermission(values={PermissionEumn.新增稿件})
	public WebResponse saveContent(@RequestBody ContentParam param)throws Exception {
		String categoryId = param.getCategoryId();
		if(categoryId==null||categoryId.equals(""))throw new BaseBusinessException(BaseBusinessError.PARAMETER_ERROR.getCode(),"菜单id不能为空");
		ValidateUtil.validate(param);
		contentService.saveContent(param);
		return new WebResponse();
	}
	
	/**更新稿件
	 * @param pageId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/portal/page/{pageid}", method = RequestMethod.PUT)
	@ResponseBody
	@MjPermission(values={PermissionEumn.修改稿件})
	public WebResponse updateContent(@PathVariable("pageid")String pageId, @RequestBody ContentParam param)throws Exception {
		ValidateUtil.validate(param);
		contentService.updateContent(pageId,param);
		return new WebResponse();
	}
	
	/**删除稿件
	 * @param pageId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/portal/page/{pageid}", method = RequestMethod.DELETE)
	@ResponseBody
	@MjPermission(values={PermissionEumn.删除稿件})
	public WebResponse deleteContent(@PathVariable("pageid")String pageId)throws Exception {
		contentService.deleteContent(pageId);
		return new WebResponse();
	}
	

}

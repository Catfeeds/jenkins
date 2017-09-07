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
import com.hfocean.uavportal.console.content.param.BannerParam;
import com.hfocean.uavportal.console.content.param.ContentParam;
import com.hfocean.uavportal.console.content.service.IBannerService;
import com.hfocean.uavportal.core.common.bean.Pager;


@Controller
public class BannerController{

	@Autowired
	private IBannerService iBannerService;
	
	/**获取轮播图
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/banners", method = RequestMethod.GET)
	@ResponseBody
	@MjPermission(values={PermissionEumn.分页查询轮播图列表})
	public WebResponse banners(HttpServletRequest request)throws Exception {
		return new WebResponse(iBannerService.findBanners(new Pager(request)));
	}
	
	/**创建轮播图
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/banners", method = RequestMethod.POST)
	@ResponseBody
	@MjPermission(values={PermissionEumn.新增轮播图})
	public WebResponse saveBanner(@RequestBody BannerParam param)throws Exception {
		ValidateUtil.validate(param);
		iBannerService.saveBanner(param);
		return new WebResponse();
	}
	
	/**更新轮播图
	 * @param pageId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/banner/{bannerid}", method = RequestMethod.PUT)
	@ResponseBody
	@MjPermission(values={PermissionEumn.修改轮播图})
	public WebResponse updateBanner(@PathVariable("bannerid")String bannerid, @RequestBody BannerParam param)throws Exception {
		ValidateUtil.validate(param);
		iBannerService.updateBanner(bannerid,param);
		return new WebResponse();
	}
	
	/**删除轮播图
	 * @param pageId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/banner/{bannerId}", method = RequestMethod.DELETE)
	@ResponseBody
	@MjPermission(values={PermissionEumn.删除轮播图})
	public WebResponse deleteBanner(@PathVariable("bannerId")String bannerId)throws Exception {
		iBannerService.deleteBanner(bannerId);
		return new WebResponse();
	}
}

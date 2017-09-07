package com.hfocean.uavportal.console.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;
import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.auth.system.permission.annotation.MjPermission;
import com.hfocean.uavportal.auth.system.permission.eumn.PermissionEumn;
import com.hfocean.uavportal.console.user.param.UserComExcelParam;
import com.hfocean.uavportal.console.user.param.UserListParam;
import com.hfocean.uavportal.console.user.param.UserPerExcelParam;
import com.hfocean.uavportal.console.user.service.IUserService;
import com.hfocean.uavportal.core.audit.enm.AuditEnum;
import com.hfocean.uavportal.core.audit.service.IAuditServiceCore;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.user.enm.UserEnum;


@Controller
public class UserController{

	@Autowired
	private IUserService iUserService;
	
	@Autowired
	private IAuditServiceCore iAuditServiceCore;
	
	/**获取个人用户列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	@MjPermission(values={PermissionEumn.个人用户列表})
	public WebResponse userPers(UserListParam param, HttpServletRequest request)throws Exception {
		return new WebResponse(iUserService.findUserPers(param, new Pager(request)));
	}
	
	/**获取企业用户列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/companys", method = RequestMethod.GET)
	@ResponseBody
	@MjPermission(values={PermissionEumn.企业用户列表})
	public WebResponse userComs(UserListParam param, HttpServletRequest request)throws Exception {
		return new WebResponse(iUserService.findUserComs(param,new Pager(request)));
	}
	
	/**审批个人用户
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/{userId}/authStatus", method = RequestMethod.PUT)
	@ResponseBody
	@MjPermission(values={PermissionEumn.个人用户审核})
	public WebResponse authUserPer(@PathVariable("userId")String userId, Integer authStatus, String reason)throws Exception {
		if(authStatus==null)throw new BaseBusinessException(BaseBusinessError.INTER_ERROR.getCode(), "认证参数错误"+authStatus);
		if(authStatus==0){
			authStatus=UserEnum.认证失败.getCode();
		}else if(authStatus==1){
			authStatus=UserEnum.已认证.getCode();
		}else{
			throw new BaseBusinessException(BaseBusinessError.INTER_ERROR.getCode(), "认证参数错误"+authStatus);
		}
		iUserService.updateAuthUserPer(userId, authStatus, reason);
		return new WebResponse();
	}
	
	/**审批企业用户
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/company/{companyId}/authStatus", method = RequestMethod.PUT)
	@ResponseBody
	@MjPermission(values={PermissionEumn.企业用户审核})
	public WebResponse authUserCom(@PathVariable("companyId")String companyId, Integer authStatus, String reason)throws Exception {
		if(authStatus==null)throw new BaseBusinessException(BaseBusinessError.INTER_ERROR.getCode(), "认证参数错误"+authStatus);
		if(authStatus==0){
			authStatus=UserEnum.认证失败.getCode();
		}else if(authStatus==1){
			authStatus=UserEnum.已认证.getCode();
		}else{
			throw new BaseBusinessException(BaseBusinessError.INTER_ERROR.getCode(), "认证参数错误"+authStatus);
		}
		iUserService.updateAuthUserCom(companyId, authStatus, reason);
		return new WebResponse();
	}
	
	/**导出个人用户信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/excel/user/pers", method = RequestMethod.GET)
	@ResponseBody
	@MjPermission
	public WebResponse exportUserPersExcel(UserPerExcelParam param)throws Exception {
		iUserService.exportUserPersExcel(param);
		return new WebResponse();
	}
	
	/**导出企业用户信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/excel/user/coms", method = RequestMethod.GET)
	@ResponseBody
	@MjPermission
	public WebResponse exportUserComsExcel(UserComExcelParam param)throws Exception {
		iUserService.exportUserComsExcel(param);
		return new WebResponse();
	}
	
	
	/**禁用/启用个人用户
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/{userId}/status", method = RequestMethod.PUT)
	@ResponseBody
	@MjPermission
	public WebResponse updateUserPerStatus(@PathVariable("userId")String userId, Integer status)throws Exception {
		if(status==null)throw new BaseBusinessException(BaseBusinessError.INTER_ERROR.getCode(), "参数错误"+status);
		if(status==0){//禁用
			status=UserEnum.不可用.getCode();
		}else if(status==1){//启动
			status=UserEnum.可用.getCode();
		}else{
			throw new BaseBusinessException(BaseBusinessError.INTER_ERROR.getCode(), "认证参数错误"+status);
		}
		iUserService.updateStatusUserPer(userId, status);
		return new WebResponse();
	}
	
	/**禁用/启用企业用户
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/company/{companyId}/status", method = RequestMethod.PUT)
	@ResponseBody
	@MjPermission
	public WebResponse updateUserComStatus(@PathVariable("companyId")String companyId, Integer status)throws Exception {
		if(status==null)throw new BaseBusinessException(BaseBusinessError.INTER_ERROR.getCode(), "参数错误"+status);
		if(status==0){//禁用
			status=UserEnum.不可用.getCode();
		}else if(status==1){//启动
			status=UserEnum.可用.getCode();
		}else{
			throw new BaseBusinessException(BaseBusinessError.INTER_ERROR.getCode(), "认证参数错误"+status);
		}
		iUserService.updateStatusUserCom(companyId, status);
		return new WebResponse();
	}
	
	/**重置个人用户密码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/{userId}/reset", method = RequestMethod.PUT)
	@ResponseBody
	@MjPermission
	public WebResponse updateResetUserPer(@PathVariable("userId")String userId, String password)throws Exception {
		if(password==null||password.equals(""))throw new BaseBusinessException(BaseBusinessError.INTER_ERROR.getCode(), "参数错误"+password);
		iUserService.updateResetUserPer(userId, password);
		return new WebResponse();
	}
	
	/**重置企业用户密码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/company/{companyId}/reset", method = RequestMethod.PUT)
	@ResponseBody
	@MjPermission
	public WebResponse updateResetUserCom(@PathVariable("companyId")String companyId, String password)throws Exception {
		if(password==null||password.equals(""))throw new BaseBusinessException(BaseBusinessError.INTER_ERROR.getCode(), "参数错误"+password);
		iUserService.updateResetUserCom(companyId, password);
		return new WebResponse();
	}
	
	/**获取审核失败原因
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/audit/reason", method = RequestMethod.GET)
	@ResponseBody
	@MjPermission
	public WebResponse findAuditreason(String userId, Integer type)throws Exception {
		if(StringUtils.isEmpty(userId)||StringUtils.isEmpty(type))throw new BaseBusinessException(BaseBusinessError.INTER_ERROR.getCode(), "参数错误"+userId+","+type);
		if(type==1){
			type = UserEnum.个人用户.getCode();
		}else if(type==2){
			type = UserEnum.企业用户.getCode();
		}else{
			throw new BaseBusinessException(BaseBusinessError.INTER_ERROR.getCode(), "用户类型错误"+type);
		}
		return new WebResponse(iAuditServiceCore.findAuditReason(userId, type, AuditEnum.认证结果失败.getCode()));
	}
}

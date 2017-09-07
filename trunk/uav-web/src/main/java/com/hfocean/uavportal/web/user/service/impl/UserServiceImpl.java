package com.hfocean.uavportal.web.user.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;
import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.core.airplan.po.FlyCount;
import com.hfocean.uavportal.core.airplan.repository.FlyCountRepository;
import com.hfocean.uavportal.core.audit.enm.AuditEnum;
import com.hfocean.uavportal.core.audit.po.AuditPO;
import com.hfocean.uavportal.core.audit.repository.AuditRepository;
import com.hfocean.uavportal.core.common.service.impl.BaseServiceImpl;
import com.hfocean.uavportal.core.user.enm.UserEnum;
import com.hfocean.uavportal.core.user.param.UserLoginParam;
import com.hfocean.uavportal.core.user.po.UserCompanyPO;
import com.hfocean.uavportal.core.user.po.UserPersonalPO;
import com.hfocean.uavportal.core.user.repository.UserCompanyRepository;
import com.hfocean.uavportal.core.user.repository.UserPersonalRepository;
import com.hfocean.uavportal.core.user.session.WebSessionUser;
import com.hfocean.uavportal.web.user.service.IUserService;
import com.hfocean.uavportal.web.user.vo.UserComVO;
import com.hfocean.uavportal.web.user.vo.UserPerVO;
import com.hfocean.uavportal.web.util.WebAppContextHelper;


/**
 * @author guan.sj
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserPersonalPO,String> implements IUserService {
	
	@Autowired
	private UserPersonalRepository userPersonalRepository;
	
	@Autowired
	private UserCompanyRepository userCompanyRepository;
	
	@Autowired
	private FlyCountRepository flyCountRepository;
	
	@Autowired
	private AuditRepository auditRepository;
	
	@Override
	public UserPerVO userPerLogin(UserLoginParam param, String ip) throws Exception {
		UserPerVO result = new UserPerVO();
		UserPersonalPO u = userPersonalRepository.findByUserName(param.getUsername());
		if(u==null)
			throw new BaseBusinessException(BaseBusinessError.USER_LOGIN_ERROR.getCode(),BaseBusinessError.USER_LOGIN_ERROR.getMessage());
		String md5DigestAsHex = DigestUtils.md5DigestAsHex(param.getPassword().getBytes());
		String password = u.getPassword();
		if(!md5DigestAsHex.equals(password))
			throw new BaseBusinessException(BaseBusinessError.USER_LOGIN_ERROR.getCode(),BaseBusinessError.USER_LOGIN_ERROR.getMessage());
		Integer status = u.getStatus();
		if(status!=null&&status==UserEnum.不可用.getCode())
			throw new BaseBusinessException(BaseBusinessError.USER_FREEZE.getCode(),BaseBusinessError.USER_FREEZE.getMessage());
		WebSessionUser<UserPersonalPO> sessionUser = new WebSessionUser<UserPersonalPO>();
		sessionUser.setObject(u);
		sessionUser.setType(UserEnum.个人用户.getCode());
		sessionUser.setUserId(u.getId());
		sessionUser.setUserName(u.getUserName());
		sessionUser.setName(u.getName());
		WebAppContextHelper.setCurrentUser(sessionUser);
		BeanUtils.copyProperties(u, result);
		//设置个人用户
		result.setType(UserEnum.个人用户.getCode());
		//获取飞行统计次数
		try {
			FlyCount flyCount = flyCountRepository.findByUserId(u.getId());
			if(flyCount!=null){
				result.setFlyTimes(flyCount.getFlyCount());
				result.setFlyHour(flyCount.getFlyTime());
				result.setFlyOvertime(flyCount.getOutCount());
			}
		} catch (Exception e) {
		}
		//更新用户最后登录资料
		try {
			u.setLastLoginIp(ip);
			u.setLastLoginTime(new Date(System.currentTimeMillis()));
			userPersonalRepository.save(u);
		} catch (Exception e) {
		}
		return result;
	}

	@Override
	public UserComVO userComLogin(UserLoginParam param, String ip) throws Exception {
		UserComVO result = new UserComVO();
		UserCompanyPO u = userCompanyRepository.findByUserName(param.getUsername());
		if(u==null)throw new BaseBusinessException(BaseBusinessError.USER_LOGIN_ERROR.getCode(),BaseBusinessError.USER_LOGIN_ERROR.getMessage());
		String md5DigestAsHex = DigestUtils.md5DigestAsHex(param.getPassword().getBytes());
		String password = u.getPassword();
		if(!md5DigestAsHex.equals(password))throw new BaseBusinessException(BaseBusinessError.USER_LOGIN_ERROR.getCode(),BaseBusinessError.USER_LOGIN_ERROR.getMessage());
		Integer status = u.getStatus();
		if(status!=null&&status==UserEnum.不可用.getCode())
			throw new BaseBusinessException(BaseBusinessError.USER_FREEZE.getCode(),BaseBusinessError.USER_FREEZE.getMessage());
		WebSessionUser<UserCompanyPO> sessionUser = new WebSessionUser<UserCompanyPO>();
		sessionUser.setObject(u);
		sessionUser.setType(UserEnum.企业用户.getCode());
		sessionUser.setUserId(u.getId());
		sessionUser.setUserName(u.getUserName());
		sessionUser.setName(u.getCompanyName());
		WebAppContextHelper.setCurrentUser(sessionUser);
		BeanUtils.copyProperties(u, result);
		//设置企业用户
		result.setType(UserEnum.企业用户.getCode());
		//获取飞行统计次数
		try {
			FlyCount flyCount = flyCountRepository.findByUserId(u.getId());
			if(flyCount!=null){
				result.setFlyTimes(flyCount.getFlyCount());
				result.setFlyHour(flyCount.getFlyTime());
				result.setFlyOvertime(flyCount.getOutCount());
			}
		} catch (Exception e) {
		}
		//更新用户最后登录资料
		try {
			u.setLastLoginIp(ip);
			u.setLastLoginTime(new Date(System.currentTimeMillis()));
			userCompanyRepository.save(u);
		} catch (Exception e) {
		}
		return result;
	}

	@Override
	public WebResponse findUserInfo() throws Exception {
		WebResponse result = new WebResponse();
		String userId = WebAppContextHelper.getCurrentUser().getUserId();
		Integer type = WebAppContextHelper.getCurrentUser().getType();
		Integer authStatus = null;
		if(type==UserEnum.个人用户.getCode()){
			UserPerVO resultData = new UserPerVO();
			UserPersonalPO per = userPersonalRepository.findOne(userId);
			authStatus = per.getAuthStatus();
			BeanUtils.copyProperties(per, resultData);
			//设置个人用户
			resultData.setType(UserEnum.个人用户.getCode());
			//获取飞行统计次数
			try {
				FlyCount flyCount = flyCountRepository.findByUserId(per.getId());
				if(flyCount!=null){
					resultData.setFlyTimes(flyCount.getFlyCount());
					resultData.setFlyHour(flyCount.getFlyTime());
					resultData.setFlyOvertime(flyCount.getOutCount());
				}
			} catch (Exception e) {
			}
			//获取用户认证失败理由
			if(authStatus!=null){
				if(authStatus==UserEnum.认证失败.getCode()){
					//获取失败原因
					AuditPO auditPO = auditRepository.findFirstByTargetIdAndTypeAndResultOrderByIdDesc(userId, type, AuditEnum.认证结果失败.getCode());
					if(auditPO!=null)resultData.setReason(auditPO.getReason());
				}
			}
			result.setData(resultData);
		}else{
			UserComVO resultData = new UserComVO();
			UserCompanyPO com = userCompanyRepository.findOne(userId);
			authStatus = com.getAuthStatus();
			BeanUtils.copyProperties(com, resultData);
			resultData.setType(UserEnum.企业用户.getCode());
			//获取飞行统计次数
			try {
				FlyCount flyCount = flyCountRepository.findByUserId(com.getId());
				if(flyCount!=null){
					resultData.setFlyTimes(flyCount.getFlyCount());
					resultData.setFlyHour(flyCount.getFlyTime());
					resultData.setFlyOvertime(flyCount.getOutCount());
				}
			} catch (Exception e) {
			}
			//获取用户认证失败理由
			if(authStatus!=null){
				if(authStatus==UserEnum.认证失败.getCode()){
					//获取失败原因
					AuditPO auditPO = auditRepository.findFirstByTargetIdAndTypeAndResultOrderByIdDesc(userId, type, AuditEnum.认证结果失败.getCode());
					if(auditPO!=null)resultData.setReason(auditPO.getReason());
				}
			}
			result.setData(resultData);
		}
		if(result.getData()==null)throw new BaseBusinessException(BaseBusinessError.USER_NOT_EXISTS.getCode(), BaseBusinessError.USER_NOT_EXISTS.getMessage());
		
		return result;
	}
	
}

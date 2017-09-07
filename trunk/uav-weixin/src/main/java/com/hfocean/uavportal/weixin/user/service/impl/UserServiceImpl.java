package com.hfocean.uavportal.weixin.user.service.impl;

import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;
import com.hfocean.common.web.WebResponse;
import com.hfocean.uavportal.core.airplan.po.FlyCount;
import com.hfocean.uavportal.core.airplan.repository.FlyCountRepository;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.service.impl.BaseServiceImpl;
import com.hfocean.uavportal.core.user.enm.UserEnum;
import com.hfocean.uavportal.core.user.param.UserComRegisterParam;
import com.hfocean.uavportal.core.user.param.UserPerRegisterParam;
import com.hfocean.uavportal.core.user.po.UserCompanyPO;
import com.hfocean.uavportal.core.user.po.UserPersonalPO;
import com.hfocean.uavportal.core.user.po.UserWxPO;
import com.hfocean.uavportal.core.user.repository.UserCompanyRepository;
import com.hfocean.uavportal.core.user.repository.UserPersonalRepository;
import com.hfocean.uavportal.core.user.repository.UserWxRepository;
import com.hfocean.uavportal.core.user.service.IUserServiceCore;
import com.hfocean.uavportal.core.user.session.WxSessionUser;
import com.hfocean.uavportal.weixin.user.param.LoginParam;
import com.hfocean.uavportal.weixin.user.service.IUserService;
import com.hfocean.uavportal.weixin.user.vo.UserComVO;
import com.hfocean.uavportal.weixin.user.vo.UserPerVO;
import com.hfocean.uavportal.weixin.user.vo.WechatUserVO;
import com.hfocean.uavportal.weixin.util.WechatAppContextHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * @author guan.sj
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserWxPO,String> implements IUserService {
	
	@Autowired
	private UserWxRepository userWxRepository;
	
	@Autowired
	private UserCompanyRepository userCompanyRepository;
	
	@Autowired
	private UserPersonalRepository userPersonalRepository;
	
	@Autowired
	private IUserServiceCore iUserServiceCore;
	
	@Autowired
	private FlyCountRepository flyCountRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public WebResponse autoLogin(String openId) throws Exception {
		WebResponse result = new WebResponse();
		UserWxPO userWx = userWxRepository.findOne(openId);
		if(userWx!=null){
			Integer type = userWx.getType();
			String userId = userWx.getUserId();
			if(type!=null&&userId!=null){
				if(type==UserEnum.个人用户.getCode()){
					UserPersonalPO user = userPersonalRepository.findOne(userId);
					//登录前判断用户是否不可用
					validateUserStatus(user.getStatus());
					//设缓存
					setWechatCurrentUser(openId, user.getId(), user.getUserName(),UserEnum.个人用户.getCode(),user.getName());
					//设VO
					WechatUserVO userVO = getWechatUserVo(openId, user, null);
					result.setData(userVO);
				}else{
					UserCompanyPO user = userCompanyRepository.findOne(userId);
					//登录前判断用户是否不可用
					validateUserStatus(user.getStatus());
					//设缓存
					setWechatCurrentUser(openId, user.getId(), user.getUserName(),UserEnum.企业用户.getCode(),user.getCompanyName());
					//设VO
					WechatUserVO userVO = getWechatUserVo(openId, null, user);
					result.setData(userVO);
				}
			}
		}
		return result;
	}

	private WechatUserVO getWechatUserVo(String openId, UserPersonalPO user, UserCompanyPO user2) {
		WechatUserVO userVO = new WechatUserVO();
		if(user!=null){
			userVO.setUserId(user.getId());
			userVO.setName(user.getName());
			userVO.setPhone(user.getPhone());
			userVO.setType(UserEnum.个人用户.getCode());
			userVO.setUserName(user.getUserName());
			userVO.setAuthStatus(user.getAuthStatus());
		}else{
			userVO.setUserId(user2.getId());
			userVO.setName(user2.getCompanyName());
			userVO.setPhone(user2.getContactPhone());
			userVO.setType(UserEnum.企业用户.getCode());
			userVO.setUserName(user2.getUserName());
			userVO.setAuthStatus(user2.getAuthStatus());
		}
		userVO.setOpenId(openId);
		return userVO;
	}
	
	@Override
	public String saveUserPerWxRgister(UserPerRegisterParam param, String openId) throws Exception {
		//检查该微信号是否已经注册
		UserWxPO wx = userWxRepository.findOne(openId);
		if(wx!=null)throw new BaseBusinessException(BaseBusinessError.WECHAT_USER_EXISTS.getCode(),BaseBusinessError.WECHAT_USER_EXISTS.getMessage());
		//注册
		UserPersonalPO save = iUserServiceCore.saveUserPerRgister(param);
		//绑定微信号
		wx = new UserWxPO();
		wx.setId(openId);
		wx.setUserId(save.getId());
		wx.setType(UserEnum.个人用户.getCode());
		wx = userWxRepository.save(wx);
		if(wx!=null)
			setWechatCurrentUser(openId, save.getId(), save.getUserName(),UserEnum.个人用户.getCode(),save.getName());
		return openId;
	}

	private void setWechatCurrentUser(String openId, String userId, String userName, Integer type,String name) {
		WxSessionUser session = new WxSessionUser();
		session.setOpenId(openId);
		session.setType(type);
		session.setUserId(userId);
		session.setUserName(userName);
		session.setName(name);
		WechatAppContextHelper.setCurrentUser(session);
	}

	@Override
	public String saveUserComWxRgister(UserComRegisterParam param, String openId) throws Exception {
		//检查该微信号是否已经注册
		UserWxPO wx = userWxRepository.findOne(openId);
		if(wx!=null)throw new BaseBusinessException(BaseBusinessError.WECHAT_USER_EXISTS.getCode(),BaseBusinessError.WECHAT_USER_EXISTS.getMessage());
		//注册
		UserCompanyPO save = iUserServiceCore.saveUserComRgister(param);
		//绑定微信号
		wx = new UserWxPO();
		wx.setId(openId);
		wx.setUserId(save.getId());
		wx.setType(UserEnum.企业用户.getCode());
		wx = userWxRepository.save(wx);
		if(wx!=null)
			setWechatCurrentUser(openId, save.getId(), save.getUserName(),UserEnum.企业用户.getCode(),save.getCompanyName());
		return openId;
	}
	
	@Override
	public WebResponse login(LoginParam param, String openId) throws Exception {
		WebResponse result = new WebResponse();
		//验证openId是否已经绑定
		UserWxPO wx = userWxRepository.findOne(openId);
		if(wx!=null)throw new BaseBusinessException(BaseBusinessError.WECHAT_USER_EXISTS.getCode(),BaseBusinessError.WECHAT_USER_EXISTS.getMessage());
		//判断该手机号是否已经绑定用户
		//1.判断个人
		UserPersonalPO user = userPersonalRepository.findByPhone(param.getPhone());
		UserCompanyPO com = userCompanyRepository.findByContactPhone(param.getPhone());
		if(user!=null&&com==null){//绑定个人用户
			result = savePerWx(openId, user);
		}else if(user==null&&com!=null){
			result = saveComWx(openId, com);
		}else if(user==null&&com==null){
			throw new BaseBusinessException(BaseBusinessError.USER_NOT_EXISTS.getCode(), BaseBusinessError.USER_NOT_EXISTS.getMessage());
		}else{
			throw new BaseBusinessException(BaseBusinessError.INTER_ERROR.getCode(), "该手机号码同时注册了个人用户和企业用户，请联系客服处理");
		}
		return result;
	}

	private WebResponse saveComWx(String openId, UserCompanyPO com) {
		WebResponse result = new WebResponse();
		UserWxPO wx;
		//2.判断企业
		if(com!=null){
			//绑定前判断用户是否不可用
			validateUserStatus(com.getStatus());
			
			wx = userWxRepository.findOneByUserIdAndType(com.getId(), UserEnum.企业用户.getCode());
			if(wx!=null)throw new BaseBusinessException(BaseBusinessError.WECHAT_PHONE_EXISTS.getCode(),BaseBusinessError.WECHAT_PHONE_EXISTS.getMessage());
			//绑定企业用户
			wx = new UserWxPO();
			wx.setId(openId);
			wx.setType(UserEnum.企业用户.getCode());
			wx.setUserId(com.getId());
			UserWxPO save = userWxRepository.save(wx);
			if(save!=null){
				//设置session和返回VO
				setWechatCurrentUser(openId, com.getId(),com.getUserName(),UserEnum.企业用户.getCode(),com.getCompanyName());
				UserComVO userVO = new UserComVO();
				BeanUtils.copyProperties(com, userVO);
				userVO.setOpenId(openId);
				//设置企业用户
				userVO.setType(UserEnum.企业用户.getCode());
				//获取飞行统计次数
				try {
					FlyCount flyCount = flyCountRepository.findByUserId(com.getId());
					if(flyCount!=null){
						userVO.setFlyTimes(flyCount.getFlyCount());
						userVO.setFlyHour(flyCount.getFlyTime());
						userVO.setFlyOvertime(flyCount.getOutCount());
					}
				} catch (Exception e) {
				}
				result.setData(userVO);
			}
			
		}
		return result;
	}

	private WebResponse savePerWx(String openId, UserPersonalPO user) {
		WebResponse result = new WebResponse();
		UserWxPO wx;
		//绑定前判断用户是否不可用
		validateUserStatus(user.getStatus());
		
		UserWxPO userWx = userWxRepository.findOneByUserIdAndType(user.getId(),UserEnum.个人用户.getCode());
		if(userWx!=null)throw new BaseBusinessException(BaseBusinessError.WECHAT_PHONE_EXISTS.getCode(),BaseBusinessError.WECHAT_PHONE_EXISTS.getMessage());
		//开始绑定个人
		wx = new UserWxPO();
		wx.setId(openId);
		wx.setType(UserEnum.个人用户.getCode());
		wx.setUserId(user.getId());
		UserWxPO save = userWxRepository.save(wx);
		if(save!=null){
			setWechatCurrentUser(openId, user.getId(),user.getUserName(),UserEnum.个人用户.getCode(),user.getName());
			UserPerVO userVO = new UserPerVO();
			BeanUtils.copyProperties(user, userVO);
			userVO.setOpenId(openId);
			//设置个人用户
			userVO.setType(UserEnum.个人用户.getCode());
			//获取飞行统计次数
			try {
				FlyCount flyCount = flyCountRepository.findByUserId(user.getId());
				if(flyCount!=null){
					userVO.setFlyTimes(flyCount.getFlyCount());
					userVO.setFlyHour(flyCount.getFlyTime());
					userVO.setFlyOvertime(flyCount.getOutCount());
				}
			} catch (Exception e) {
			}
			result.setData(userVO);
		}
		return result;
	}

	private void validateUserStatus(Integer status) {
		if(status!=null&&status==UserEnum.不可用.getCode())throw new BaseBusinessException(BaseBusinessError.USER_FREEZE.getCode(),BaseBusinessError.USER_FREEZE.getMessage());
	}
	
	@Override
	public WebResponse findUserInfo(String openId) throws Exception {
		WebResponse result = new WebResponse();
		UserWxPO userWx = userWxRepository.findOne(openId);
		if(userWx==null)throw new BaseBusinessException(BaseBusinessError.USER_NOT_EXISTS.getCode(),BaseBusinessError.USER_NOT_EXISTS.getMessage());
		Integer type = userWx.getType();
		String userId = userWx.getUserId();
		if(type!=null&&userId!=null){
			if(type==UserEnum.个人用户.getCode()){
				UserPersonalPO findOne = userPersonalRepository.findOne(userId);
				UserPerVO vo = new UserPerVO();
				BeanUtils.copyProperties(findOne, vo);
				//设置个人用户
				vo.setType(UserEnum.个人用户.getCode());
				//获取飞行统计次数
				try {
					FlyCount flyCount = flyCountRepository.findByUserId(findOne.getId());
					if(flyCount!=null){
						vo.setFlyTimes(flyCount.getFlyCount());
						vo.setFlyHour(flyCount.getFlyTime());
						vo.setFlyOvertime(flyCount.getOutCount());
					}
				} catch (Exception e) {
				}
				result.setData(vo);
			}else{
				UserCompanyPO findOne = userCompanyRepository.findOne(userId);
				UserComVO vo = new UserComVO();
				BeanUtils.copyProperties(findOne, vo);
				//设置企业用户
				vo.setType(UserEnum.企业用户.getCode());
				//获取飞行统计次数
				try {
					FlyCount flyCount = flyCountRepository.findByUserId(findOne.getId());
					if(flyCount!=null){
						vo.setFlyTimes(flyCount.getFlyCount());
						vo.setFlyHour(flyCount.getFlyTime());
						vo.setFlyOvertime(flyCount.getOutCount());
					}
				} catch (Exception e) {
				}
				result.setData(vo);
			}
		}
		return result;
	}

	@Override
	public WxSessionUser getCurrentUser(String openId) throws Exception {
		WxSessionUser result = null;
		UserWxPO wx = userWxRepository.findOne(openId);
		if(wx!=null){
			Integer type = wx.getType();
			String userId = wx.getUserId();
			result = new WxSessionUser();
			result.setOpenId(openId);
			result.setType(type);
			result.setUserId(userId);
			if(type!=null&&type==UserEnum.个人用户.getCode()){
				UserPersonalPO user = userPersonalRepository.findOne(userId);
				result.setUserName(user.getUserName());
			}else{
				UserCompanyPO user = userCompanyRepository.findOne(userId);
				result.setUserName(user.getUserName());
			}
		}
		return result;
	}

	

	
	@Override
	public Pager findNotices(Pager pager, String userId) throws Exception {
		StringBuilder hql = new StringBuilder("from WechatUserMessageVO t where 1=1");
		hql.append(" and t.userId='").append(userId).append("'")
		.append(" order by t.createTime desc");
		return findByPage(hql.toString(), pager,null);
	}
	

	

	
	
}

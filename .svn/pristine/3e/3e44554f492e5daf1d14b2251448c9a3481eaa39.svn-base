package com.hfocean.uavportal.core.user.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;
import com.hfocean.common.util.ValidateUtil;
import com.hfocean.common.validation.custom.CreditCode;
import com.hfocean.common.validation.custom.Second;
import com.hfocean.uavportal.core.audit.enm.AuditEnum;
import com.hfocean.uavportal.core.audit.po.AuditPO;
import com.hfocean.uavportal.core.audit.repository.AuditRepository;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.constant.Constant;
import com.hfocean.uavportal.core.common.service.impl.BaseServiceImpl;
import com.hfocean.uavportal.core.sms.service.ISmsService;
import com.hfocean.uavportal.core.user.enm.UserEnum;
import com.hfocean.uavportal.core.user.param.ResetPwdParam;
import com.hfocean.uavportal.core.user.param.UpdateUserComParam;
import com.hfocean.uavportal.core.user.param.UpdateUserPerParam;
import com.hfocean.uavportal.core.user.param.UserComRegisterParam;
import com.hfocean.uavportal.core.user.param.UserComUpdateAuthParam;
import com.hfocean.uavportal.core.user.param.UserPerRegisterParam;
import com.hfocean.uavportal.core.user.param.UserPerUpdateAuthParam;
import com.hfocean.uavportal.core.user.po.UserCompanyPO;
import com.hfocean.uavportal.core.user.po.UserPersonalPO;
import com.hfocean.uavportal.core.user.repository.UserCompanyRepository;
import com.hfocean.uavportal.core.user.repository.UserPersonalRepository;
import com.hfocean.uavportal.core.user.service.IUserServiceCore;
import com.hfocean.uavportal.core.user.vo.AuditSeasonVO;
import com.hfocean.uavportal.core.user.vo.ComAuditInfoVO;
import com.hfocean.uavportal.core.user.vo.PerAuditInfoVO;

/**
 * @author guan.sj
 */
@Service
@Transactional
public class UserServiceCoreImpl extends BaseServiceImpl<UserPersonalPO,String> implements IUserServiceCore {
	
	@Autowired
	private UserPersonalRepository userPersonalRepository;
	
	@Autowired
	private UserCompanyRepository userCompanyRepository;
	
	@Autowired
	private ISmsService iSmsService;
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private AuditRepository auditRepository;
	
	@Override
	public UserPersonalPO saveUserPerRgister(UserPerRegisterParam param)throws Exception {
		//检查注册信息
		verifyPhone(param.getPhone());
		verifyName(param.getUserName());
		verifyIdCard(param.getIdCard());
		//判断紧急联系人与联系人的信息是否相同
		verifyEmeInfo(param);
		//save
		UserPersonalPO user = new UserPersonalPO();
		user.setUserName(param.getUserName());
		user.setName(param.getName());
		user.setIdCard(param.getIdCard());
		user.setIdcardPic1(param.getIdcardPic1());
		user.setIdcardPic2(param.getIdcardPic2());
		user.setIdcardPic3(param.getIdcardPic3());
		user.setCertificateType(param.getCertificateType());
		user.setCertificateNumber(param.getCertificateNumber());
		user.setQq(param.getQq());
		user.setEmail(param.getEmail());
		user.setPhone(param.getPhone());
		user.setAddress(param.getAddress());
		user.setEmergencyContactName(param.getEmergencyContactName());
		user.setEmergencyContactPhone(param.getEmergencyContactPhone());		
		//转化出生日期
		Date birthDay = getBirthDay(param.getBirthday());
		user.setBirthday(birthDay);
		//设置密码
		user.setPassword(DigestUtils.md5DigestAsHex(param.getPassword().getBytes()));
		//设置默认字段
		user.setStatus(UserEnum.可用.getCode());
		user.setAuthStatus(UserEnum.待认证.getCode());
		user.setCreateTime(new Date(System.currentTimeMillis()));
		//判断性别是否传入M,F
		String sex = getSex(param.getSex());
		user.setSex(sex);
		UserPersonalPO save = userPersonalRepository.save(user);
		return save;
	}
	
	private void verifyEmeInfo(UserPerRegisterParam param){
		String phone = param.getPhone();
		String name = param.getName();
		String emergencyContactPhone = param.getEmergencyContactPhone();
		String emergencyContactName = param.getEmergencyContactName();
		if(phone!=null&&phone.equals(emergencyContactPhone))throw new BaseBusinessException(BaseBusinessError.SAME_PHONE_NAME.getCode(), BaseBusinessError.SAME_PHONE_NAME.getMessage());
		if(name!=null&&name.equals(emergencyContactName))throw new BaseBusinessException(BaseBusinessError.SAME_PHONE_NAME.getCode(), BaseBusinessError.SAME_PHONE_NAME.getMessage());
	}
	
	private Date getBirthDay(String birthday) {
		Date birth = null;
		if(birthday!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				birth = sdf.parse(birthday);
			} catch (ParseException e) {
				throw new BaseBusinessException(BaseBusinessError.PARAMETER_ERROR.getCode(), "出生日期格式错误");
			}
		}
		return birth;
	}

	private String getSex(String sex) {
		if(sex!=null)if((!sex.equals(UserEnum.男.field()))&&(!sex.equals(UserEnum.女.field())))sex=null;
		return sex;
	}
	
	@Override
	public UserPersonalPO updateUserPer(String userId, UpdateUserPerParam param) throws Exception {
		//update
		UserPersonalPO user = userPersonalRepository.findOne(userId);
		Integer authStatus = user.getAuthStatus();
		if(user!=null){
			//已认证则不可修改的字段
			if(authStatus==null||!authStatus.equals(UserEnum.已认证.getCode())){
				ValidateUtil.validateGroup(param,Second.class);
				user.setName(param.getName());
				//判断性别是否传入M,F
				String sex = getSex(param.getSex());
				user.setSex(sex);
				//转化出生日期
				Date birthDay = getBirthDay(param.getBirthday());
				user.setBirthday(birthDay);
				user.setIdCard(param.getIdCard());
				user.setIdcardPic1(param.getIdcardPic1());;
				user.setIdcardPic2(param.getIdcardPic2());;
				user.setIdcardPic3(param.getIdcardPic3());;
			}
			user.setCertificateType(param.getCertificateType());
			user.setCertificateNumber(param.getCertificateNumber());
			user.setQq(param.getQq());
			user.setEmail(param.getEmail());
			user.setAddress(param.getAddress());
			user.setEmergencyContactName(param.getEmergencyContactName());
			user.setEmergencyContactPhone(param.getEmergencyContactPhone());
			user = userPersonalRepository.save(user);
		}
		return user;
	}

	@Override
	public UserCompanyPO saveUserComRgister(UserComRegisterParam param)throws Exception{
		//检查注册信息
		verifyPhone(param.getContactPhone());
		verifyName(param.getUserName());
		verifyCompanyName(param.getCompanyName());
		verifyLicenseNumber(param.getLicenseNumber());
		//检查企业注册的联系人信息和紧急联系人信息
		verifyEmeInfo(param);
		UserCompanyPO user = new UserCompanyPO();
		BeanUtils.copyProperties(param, user);
		user.setPassword(DigestUtils.md5DigestAsHex(param.getPassword().getBytes()));
		user.setStatus(UserEnum.可用.getCode());
		user.setAuthStatus(UserEnum.待认证.getCode());
		user.setCreateTime(new Date(System.currentTimeMillis()));
		UserCompanyPO save = userCompanyRepository.save(user);
		return save;
	}
	
	private void verifyEmeInfo(UserComRegisterParam param){
		String phone = param.getContactPhone();
		String name = param.getContactName();
		String emergencyContactPhone = param.getEmergencyContactPhone();
		String emergencyContactName = param.getEmergencyContactName();
		if(phone!=null&&phone.equals(emergencyContactPhone))throw new BaseBusinessException(BaseBusinessError.SAME_PHONE_NAME.getCode(), BaseBusinessError.SAME_PHONE_NAME.getMessage());
		if(name!=null&&name.equals(emergencyContactName))throw new BaseBusinessException(BaseBusinessError.SAME_PHONE_NAME.getCode(), BaseBusinessError.SAME_PHONE_NAME.getMessage());
	}
	
	@Override
	public UserCompanyPO updateUserCom(String userId, UpdateUserComParam param) throws Exception {
		//update
		UserCompanyPO user = userCompanyRepository.findOne(userId);
		Integer authStatus = user.getAuthStatus();
		if(user!=null){
			//已认证则不可修改的字段
			if(authStatus==null||!authStatus.equals(UserEnum.已认证.getCode())){
				ValidateUtil.validateGroup(param, Second.class);
				user.setCompanyName(param.getCompanyName());;
				user.setLicenseNumber(param.getLicenseNumber());
				user.setLicensePic(param.getLicensePic());;
				user.setCompanyLegal(param.getCompanyLegal());
			}
			user.setContactName(param.getContactName());
			user.setCompanyAddress(param.getCompanyAddress());
			user.setEmail(param.getEmail());
			user.setEmergencyContactName(param.getEmergencyContactName());
			user.setEmergencyContactPhone(param.getEmergencyContactPhone());
			user = userCompanyRepository.save(user);
		}
		return user;
	}
	
	@Override
	public void verifyPhone(String phone) throws Exception {
		//验证手机号码
		UserPersonalPO user = userPersonalRepository.findByPhone(phone);
		if(user!=null){
			throw new BaseBusinessException(BaseBusinessError.PHONE_ALREADY_EXISTS.getCode(),BaseBusinessError.PHONE_ALREADY_EXISTS.getMessage());
		}else{
			UserCompanyPO userCom = userCompanyRepository.findByContactPhone(phone);
			if(userCom!=null)throw new BaseBusinessException(BaseBusinessError.PHONE_ALREADY_EXISTS.getCode(),BaseBusinessError.PHONE_ALREADY_EXISTS.getMessage());
		}
	}

	@Override
	public void verifyName(String userName) throws Exception {
		// 验证用户名
		UserPersonalPO user = userPersonalRepository.findByUserName(userName);
		if(user!=null){
			throw new BaseBusinessException(BaseBusinessError.USER_ALREADY_EXISTS.getCode(),BaseBusinessError.USER_ALREADY_EXISTS.getMessage());
		}else{
			UserCompanyPO userCom = userCompanyRepository.findByUserName(userName);
			if (userCom != null)throw new BaseBusinessException(BaseBusinessError.USER_ALREADY_EXISTS.getCode(),BaseBusinessError.USER_ALREADY_EXISTS.getMessage());
		}
	}
	
	@Override
	public void verifyIdCard(String idCard) throws Exception {
		//验证身份证
		UserPersonalPO user = userPersonalRepository.findByIdCard(idCard);
		if(user!=null)throw new BaseBusinessException(BaseBusinessError.IDCARD_ALREADY_EXISTS.getCode(),BaseBusinessError.IDCARD_ALREADY_EXISTS.getMessage());
	}

	@Override
	public void verifyCompanyName(String companyName) throws Exception {
		UserCompanyPO com = userCompanyRepository.findByCompanyName(companyName);
		if(com!=null)throw new BaseBusinessException(BaseBusinessError.COMPANYNAME_ALREADY_EXISTS.getCode(),BaseBusinessError.COMPANYNAME_ALREADY_EXISTS.getMessage());
	}

	@Override
	public void verifyLicenseNumber(String licenseNumber) throws Exception {
		//判断是否已经被使用
		UserCompanyPO com = userCompanyRepository.findByLicenseNumber(licenseNumber);
		if(com!=null)throw new BaseBusinessException(BaseBusinessError.LICENSENUMBER_ALREADY_EXISTS.getCode(),BaseBusinessError.LICENSENUMBER_ALREADY_EXISTS.getMessage());
	}

	
	@Override
	public Pager findNotices(Pager pager, String userId) throws Exception {
		StringBuilder hql = new StringBuilder("from UserMessageVO t where 1=1");
		hql.append(" and t.userId='").append(userId).append("'")
		.append(" order by t.createTime desc");
		return findByPage(hql.toString(), pager,null);
	}

	@Override
	public int updateResetPassword(ResetPwdParam param) throws Exception {
		int result = 0;
		String code = param.getCode();
		String newPassword = param.getNewPassword();
		//验证验证码，并获取通过验证的手机号，与重置用户绑定的手机号比较
		String verifyPhone = iSmsService.verifyCode(code);
		//获取登录用户
		UserPersonalPO per = userPersonalRepository.findByPhone(verifyPhone);
		UserCompanyPO com = userCompanyRepository.findByContactPhone(verifyPhone);
		if(per!=null&&com==null){//个人用户
			//通过验证，重置密码
			per.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
			UserPersonalPO save = userPersonalRepository.save(per);
			if(save!=null)result = 1;
		}else if(per==null&&com!=null){//企业用户 
			//通过验证，重置密码
			com.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
			UserCompanyPO save = userCompanyRepository.save(com);
			if(save!=null)result = 1;
		}else if(per!=null&&com!=null){//该手机号码绑定两个用户
			throw new BaseBusinessException(BaseBusinessError.INTER_ERROR.getCode(), "该手机号码绑定个人和企业用户，请联系客服处理");
		}else{//用户不存在
			throw new BaseBusinessException(BaseBusinessError.USER_NOT_EXISTS.getCode(), BaseBusinessError.USER_NOT_EXISTS.getMessage());
		}
		if(result!=1)throw new BaseBusinessException(BaseBusinessError.RESET_PWD_ERROR.getCode(), BaseBusinessError.RESET_PWD_ERROR.getMessage());
		return result;
	}
	
	
	@Override
	public AuditSeasonVO findUserAuthStatus(String userId, Integer type) throws Exception {
		AuditSeasonVO result;
		Integer authStatus = null;
		if(type==UserEnum.个人用户.getCode()){
			result = new AuditSeasonVO<PerAuditInfoVO>();
			UserPersonalPO perUser = userPersonalRepository.findOne(userId);
			if(perUser!=null){
				authStatus = perUser.getAuthStatus();
				PerAuditInfoVO perAudit = new PerAuditInfoVO();
				BeanUtils.copyProperties(perUser, perAudit);
				result.setAuthInfo(perAudit);
			}
		}else{
			result = new AuditSeasonVO<ComAuditInfoVO>();
			UserCompanyPO comUser = userCompanyRepository.findOne(userId);
			if(comUser!=null){
				authStatus = comUser.getAuthStatus();
				ComAuditInfoVO comAudit = new ComAuditInfoVO();
				BeanUtils.copyProperties(comUser, comAudit);
				result.setAuthInfo(comAudit);
			}
		}
		if(authStatus==null)throw new BaseBusinessException(BaseBusinessError.NOT_FOUND.getCode(), BaseBusinessError.NOT_FOUND.getMessage());
		if(authStatus==UserEnum.认证失败.getCode()){
			//获取失败原因
			AuditPO auditPO = auditRepository.findFirstByTargetIdAndTypeAndResultOrderByIdDesc(userId, type, AuditEnum.认证结果失败.getCode());
			if(auditPO!=null)result.setReason(auditPO.getReason());
		}
		result.setAuthStatus(authStatus);
		
		return result;
	}

	@Override
	public int updateUserPhone(String userId, Integer type, String phone) {
		if(type==UserEnum.个人用户.getCode()){
			UserPersonalPO perUser = userPersonalRepository.findOne(userId);
			perUser.setPhone(phone);
			userPersonalRepository.save(perUser);
		}else{
			UserCompanyPO comUser = userCompanyRepository.findOne(userId);
			comUser.setContactPhone(phone);
			userCompanyRepository.save(comUser);
		}
		return 1;
	}

	@Override
	public int updateUserPerAuth(String userId, UserPerUpdateAuthParam param) throws Exception {
		UserPersonalPO perUser = userPersonalRepository.findOne(userId);
		Integer authStatus = perUser.getAuthStatus();
		if(authStatus==UserEnum.已认证.getCode())throw new BaseBusinessException(BaseBusinessError.USER_AUTHENTICATION_ERROR.getCode(), "已通过认证");
		perUser.setIdCard(param.getIdCard());
		perUser.setIdcardPic1(param.getIdcardPic1());
		perUser.setIdcardPic2(param.getIdcardPic2());
		perUser.setIdcardPic3(param.getIdcardPic3());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		perUser.setBirthday(sdf.parse(param.getBirthday()));
		perUser.setSex(getSex(param.getSex()));
		perUser.setAuthStatus(UserEnum.待认证.getCode());
		userPersonalRepository.save(perUser);
		return 1;
	}

	@Override
	public int updateUserComAuth(String userId, UserComUpdateAuthParam param) throws Exception {
		UserCompanyPO comUser = userCompanyRepository.findOne(userId);
		Integer authStatus = comUser.getAuthStatus();
		if(authStatus==UserEnum.已认证.getCode())throw new BaseBusinessException(BaseBusinessError.USER_AUTHENTICATION_ERROR.getCode(), "已通过认证");
		comUser.setCompanyName(param.getCompanyName());
		comUser.setCompanyLegal(param.getCompanyLegal());
		comUser.setLicenseNumber(param.getLicenseNumber());
		comUser.setLicensePic(param.getLicensePic());
		comUser.setAuthStatus(UserEnum.待认证.getCode());
		userCompanyRepository.save(comUser);
		return 1;
	}
}

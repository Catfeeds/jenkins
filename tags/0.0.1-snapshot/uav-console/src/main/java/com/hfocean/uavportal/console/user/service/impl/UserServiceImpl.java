package com.hfocean.uavportal.console.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.hfocean.common.excel.XSSFExcelTool;
import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;
import com.hfocean.common.util.AppContextHelper;
import com.hfocean.common.util.DateHandler;
import com.hfocean.common.util.VerdictUtil;
import com.hfocean.uavportal.auth.base.utils.AuthAppContextHelper;
import com.hfocean.uavportal.console.user.entity.ComWxEntity;
import com.hfocean.uavportal.console.user.entity.PerWxEntity;
import com.hfocean.uavportal.console.user.entity.UserCompanyEntity;
import com.hfocean.uavportal.console.user.entity.UserPersonalEntity;
import com.hfocean.uavportal.console.user.param.UserComExcelParam;
import com.hfocean.uavportal.console.user.param.UserListParam;
import com.hfocean.uavportal.console.user.param.UserPerExcelParam;
import com.hfocean.uavportal.console.user.service.IUserService;
import com.hfocean.uavportal.console.user.vo.UserCompanyExcel;
import com.hfocean.uavportal.console.user.vo.UserPersonalExcel;
import com.hfocean.uavportal.core.audit.enm.AuditEnum;
import com.hfocean.uavportal.core.audit.po.AuditPO;
import com.hfocean.uavportal.core.audit.repository.AuditRepository;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.service.impl.BaseServiceImpl;
import com.hfocean.uavportal.core.user.enm.UserEnum;
import com.hfocean.uavportal.core.user.po.UserCompanyPO;
import com.hfocean.uavportal.core.user.po.UserPersonalPO;
import com.hfocean.uavportal.core.user.repository.UserCompanyRepository;
import com.hfocean.uavportal.core.user.repository.UserPersonalRepository;


/**
 * @author guan.sj
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserPersonalPO,String> implements IUserService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UserPersonalRepository userPersonalRepository;
	
	@Autowired
	private UserCompanyRepository userCompanyRepository;
	
	@Autowired
	private AuditRepository auditRepository;
	
	@Override
	public Pager findUserPers(UserListParam param, Pager pager) throws Exception {
		StringBuilder hql = new StringBuilder("from UserPersonalPO t where 1=1");
		List<Object> params = getPerListHql(param, hql);
        return findByPage(hql.toString(), pager,params);
	}

	@Override
	public Pager findUserComs(UserListParam param, Pager pager) throws Exception {
		StringBuilder hql = new StringBuilder("from UserCompanyPO t where 1=1");
		List<Object> params = getComListHql(param, hql);
        return findByPage(hql.toString(), pager,params);
	}
	

    private List<Object> getPerListHql(UserListParam param, StringBuilder hql) throws Exception {
        List<Object> params = new ArrayList<>();
        if(VerdictUtil.isNotBlank(param.getName())){
            hql.append(" and t.name like ?");
            params.add(param.getName()+"%");
        }
        if(VerdictUtil.isNotBlank(param.getUserName())){
            hql.append(" and t.userName like ?");
            params.add(param.getUserName()+"%");
        }
        if(VerdictUtil.isValidArray(param.getStatus())){
        	Integer[] statusList = param.getStatus();
        	hql.append(" and t.status in ").append(getIn(statusList.length));
            for(Integer status : statusList){
            	params.add(status);
            }
        }
        if(VerdictUtil.isValidArray(param.getAuthStatus())){
        	Integer[] authStatus = param.getAuthStatus();
        	hql.append(" and t.authStatus in ").append(getIn(authStatus.length));
            for(Integer auth : authStatus){
            	params.add(auth);
            }
        }
        if(VerdictUtil.isNotBlank(param.getStartDate())){
            hql.append(" and t.createTime >= ?");
            params.add(DateHandler.parse(param.getStartDate(),"yyyy-MM-dd"));
        }
        if(VerdictUtil.isNotBlank(param.getEndDate())){
            hql.append(" and t.createTime <= ?");
            params.add(DateHandler.parse(param.getEndDate(),"yyyy-MM-dd"));
        }
        hql.append(" order by t.createTime desc");
        return params;
    }
    
    private List<Object> getComListHql(UserListParam param, StringBuilder hql) throws Exception {
        List<Object> params = new ArrayList<>();
        if(VerdictUtil.isNotBlank(param.getCompanyName())){
            hql.append(" and t.companyName like ?");
            params.add(param.getCompanyName()+"%");
        }
        if(VerdictUtil.isNotBlank(param.getUserName())){
            hql.append(" and t.userName like ?");
            params.add(param.getUserName()+"%");
        }
        if(VerdictUtil.isValidArray(param.getStatus())){
        	Integer[] statusList = param.getStatus();
        	hql.append(" and t.status in ").append(getIn(statusList.length));
            for(Integer status : statusList){
            	params.add(status);
            }
        }
        if(VerdictUtil.isValidArray(param.getAuthStatus())){
        	Integer[] authStatus = param.getAuthStatus();
        	hql.append(" and t.authStatus in ").append(getIn(authStatus.length));
            for(Integer auth : authStatus){
            	params.add(auth);
            }
        }
        if(VerdictUtil.isNotBlank(param.getStartDate())){
            hql.append(" and t.createTime >= ?");
            params.add(DateHandler.parse(param.getStartDate(),"yyyy-MM-dd"));
        }
        if(VerdictUtil.isNotBlank(param.getEndDate())){
            hql.append(" and t.createTime <= ?");
            params.add(DateHandler.parse(param.getEndDate(),"yyyy-MM-dd"));
        }
        hql.append(" order by t.createTime desc");
        return params;
    }

	private String getIn(int length) {
		StringBuilder in = new StringBuilder("(");
		for(int i=0;i<length;i++){
			if(i==length-1){
				in.append("?)");
				break;
			}
			in.append("?,");
		}
		return in.toString();
	}

	@Override
	public int updateAuthUserPer(String userId, Integer authStatus, String reason) throws Exception {
		UserPersonalPO findOne = userPersonalRepository.findOne(userId);
		if(findOne==null)throw new BaseBusinessException(BaseBusinessError.USER_NOT_EXISTS.getCode(), BaseBusinessError.USER_NOT_EXISTS.getMessage());
		Integer userAuthStatus = findOne.getAuthStatus();
		if(userAuthStatus==UserEnum.已认证.getCode())
			throw new BaseBusinessException(BaseBusinessError.INTER_ERROR.getCode(), "用户已通过认证，请勿重复认证");
		findOne.setAuthStatus(authStatus);
		UserPersonalPO save = userPersonalRepository.save(findOne);
		if(save!=null){
			//推送短信-待加
//			iSmsService.sendSMS(findOne.getPhone(), SmsTemplate, params)
			//追加审核记录
			saveAuditPO(userId, authStatus, reason, AuditEnum.个人用户认证.getCode());
		}
		
		return 1;
	}

	private void saveAuditPO(String userId, Integer authStatus, String reason, Integer type) {
		if(authStatus==UserEnum.认证失败.getCode()&&!StringUtils.isEmpty(reason)){
			AuditPO audit = new AuditPO();
			audit.setAdminId(AuthAppContextHelper.getSysUserName());
			audit.setAuditTime(new Date(System.currentTimeMillis()));
			audit.setCreateTime(new Date(System.currentTimeMillis()));
			audit.setReason(reason);
			audit.setResult(AuditEnum.认证结果失败.getCode());
			audit.setTargetId(userId);
			audit.setType(type);
			audit = auditRepository.save(audit);
			if(audit==null)throw new BaseBusinessException(BaseBusinessError.INTER_ERROR.getCode(), "保存审核记录失败");
		}
	}

	@Override
	public int updateAuthUserCom(String companyId, Integer authStatus, String reason) throws Exception {
		UserCompanyPO findOne = userCompanyRepository.findOne(companyId);
		if(findOne==null)throw new BaseBusinessException(BaseBusinessError.USER_NOT_EXISTS.getCode(), BaseBusinessError.USER_NOT_EXISTS.getMessage());
		Integer userAuthStatus = findOne.getAuthStatus();
		if(userAuthStatus==UserEnum.已认证.getCode())
			throw new BaseBusinessException(BaseBusinessError.INTER_ERROR.getCode(), "用户已通过认证，请勿重复认证");
		findOne.setAuthStatus(authStatus);
		UserCompanyPO save = userCompanyRepository.save(findOne);
		if(save!=null){
			//推送短信-待加
//			iSmsService.sendSMS(findOne.getPhone(), SmsTemplate, params)
			//追加审核记录
			saveAuditPO(companyId, authStatus, reason, AuditEnum.企业用户认证.getCode());
		}
		return 1;
	}

	@Override
	public void exportUserPersExcel(UserPerExcelParam param) throws Exception {
		StringBuilder hql = new StringBuilder("from UserPersonalEntity t where 1=1 ");
		List<Object> params = getUserPersHql(param, hql);
		List list = findAll(hql.toString(), params);
		List<UserPersonalExcel> excels = new ArrayList<>();
		if (VerdictUtil.isValidCollection(list)) {
			List<UserPersonalEntity> userPers = (ArrayList<UserPersonalEntity>) list;
			UserPersonalExcel excel;
			for (UserPersonalEntity per : userPers) {
				excel = new UserPersonalExcel();
				excel.setUserName(per.getUserName());
				excel.setSex(per.getSex());
				excel.setHeadPic(per.getHeadPic());
				excel.setStatus(per.getStatus());
				excel.setName(per.getName());
				excel.setIdCard(per.getIdCard());
				excel.setIdcardPic1(per.getIdcardPic1());
				excel.setIdcardPic2(per.getIdcardPic2());
				excel.setIdcardPic3(per.getIdcardPic3());
				excel.setAuthStatus(per.getAuthStatus());
				excel.setCertificateType(per.getCertificateType());
				excel.setCertificateNumber(per.getCertificateNumber());
				excel.setQq(per.getQq());
				excel.setEmail(per.getEmail());
				excel.setPhone(per.getPhone());
				excel.setAddress(per.getAddress());
				excel.setEmergencyContactName(per.getEmergencyContactName());
				excel.setEmergencyContactPhone(per.getEmergencyContactPhone());
				excel.setBirthday(DateHandler.dateToString(per.getBirthday(), "yyyy/MM/dd HH:mm"));
				PerWxEntity userWxEntity = per.getPerWxEntity();
				if (userWxEntity != null)
					excel.setOpenId(userWxEntity.getId());
				excel.setCreateTime(DateHandler.dateToString(per.getCreateTime(), "yyyy/MM/dd HH:mm"));
				excel.setLastLoginTime(DateHandler.dateToString(per.getLastLoginTime(), "yyyy/MM/dd HH:mm"));
				excel.setLastLoginIp(per.getLastLoginIp());
				excels.add(excel);
			}
			Map<String, String> maps = new LinkedHashMap<>();
			maps.put("用户名", "userName");
			maps.put("性别(F男M女)", "sex");
			maps.put("头像", "headPic");
			maps.put("状态(1可用,2不可用)", "status");
			maps.put("真实姓名", "name");
			maps.put("身份证号码", "idCard");
			maps.put("身份证正面图", "idcardPic1");
			maps.put("身份证反面图", "idcardPic2");
			maps.put("身份证手持自拍图", "idcardPic3");
			maps.put("认证状态(1未认证,2待认证,3已认证,4认证失败)", "authStatus");
			maps.put("证书类型", "certificateType");
			maps.put("证书编号", "certificateNumber");
			maps.put("QQ", "qq");
			maps.put("邮箱", "email");
			maps.put("手机号码", "phone");
			maps.put("住址", "address");
			maps.put("紧急联系人姓名", "emergencyContactName");
			maps.put("紧急联系人手机号码", "emergencyContactPhone");
			maps.put("出生日期", "birthday");
			maps.put("微信openId", "openId");
			maps.put("创建时间", "createTime");
			maps.put("最后登录时间", "lastLoginTime");
			maps.put("最后登录ip", "lastLoginIp");
			XSSFExcelTool.exportExcel(maps, "sheet1", "个人用户信息", excels, AppContextHelper.getRequest(),
					AppContextHelper.getResponse());
		}
	}
	
	 private List<Object> getUserPersHql(UserPerExcelParam param, StringBuilder hql) throws Exception {
		List<Object> params = new ArrayList<>();
		if (VerdictUtil.isNotBlank(param.getUserName())) {
			hql.append(" and t.userName like ?");
			params.add("%" + param.getUserName() + "%");
		}
		if (VerdictUtil.isNotBlank(param.getName())) {
			hql.append(" and t.name like ?");
			params.add("%" + param.getName() + "%");
		}
		if (VerdictUtil.isNotBlank(param.getIdCard())) {
			hql.append(" and t.idCard like ?");
			params.add("%" + param.getIdCard() + "%");
		}
		if (VerdictUtil.isNotBlank(param.getQq())) {
			hql.append(" and t.qq like ?");
			params.add("%" + param.getQq() + "%");
		}
		if (VerdictUtil.isNotBlank(param.getEmail())) {
			hql.append(" and t.email like ?");
			params.add("%" + param.getEmail() + "%");
		}
		if (VerdictUtil.isNotBlank(param.getPhone())) {
			hql.append(" and t.phone like ?");
			params.add("%" + param.getPhone() + "%");
		}
		if (VerdictUtil.isNotBlank(param.getAddress())) {
			hql.append(" and t.address like ?");
			params.add("%" + param.getAddress() + "%");
		}
		if (VerdictUtil.isNotBlank(param.getEmergencyContactName())) {
			hql.append(" and t.emergencyContactName like ?");
			params.add("%" + param.getEmergencyContactName() + "%");
		}
		if (VerdictUtil.isNotBlank(param.getEmergencyContactPhone())) {
			hql.append(" and t.emergencyContactPhone like ?");
			params.add("%" + param.getEmergencyContactPhone() + "%");
		}
		if (VerdictUtil.isNotBlank(param.getLastLoginIp())) {
			hql.append(" and t.lastLoginIp like ?");
			params.add("%" + param.getLastLoginIp() + "%");
		}
		if (VerdictUtil.isNotBlank(param.getSex())) {
			hql.append(" and t.sex = ?");
			params.add(param.getSex());
		}
		if (VerdictUtil.gtOrEtoZero(param.getStatus())) {
			hql.append(" and t.status = ?");
			params.add(param.getStatus());
		}
		if (VerdictUtil.gtOrEtoZero(param.getAuthStatus())) {
			hql.append(" and t.authStatus = ?");
			params.add(param.getAuthStatus());
		}
		if (VerdictUtil.isNotBlank(param.getBirthdayStart())) {
			hql.append(" and t.birthday >= ?");
			params.add(DateHandler.parse(param.getBirthdayStart(), "yyyy-MM-dd"));
		}
		if (VerdictUtil.isNotBlank(param.getBirthdayEnd())) {
			hql.append(" and t.birthday <= ?");
			params.add(DateHandler.parse(param.getBirthdayEnd(), "yyyy-MM-dd"));
		}
		if (VerdictUtil.isNotBlank(param.getCreateTimeStart())) {
			hql.append(" and t.createTime >= ?");
			params.add(DateHandler.parse(param.getCreateTimeStart(), "yyyy-MM-dd"));
		}
		if (VerdictUtil.isNotBlank(param.getCreateTimeEnd())) {
			hql.append(" and t.createTime <= ?");
			params.add(DateHandler.parse(param.getCreateTimeEnd(), "yyyy-MM-dd"));
		}
		if (VerdictUtil.isNotBlank(param.getLastLoginTimeStart())) {
			hql.append(" and t.lastLoginTime >= ?");
			params.add(DateHandler.parse(param.getLastLoginTimeStart(), "yyyy-MM-dd"));
		}
		if (VerdictUtil.isNotBlank(param.getLastLoginTimeEnd())) {
			hql.append(" and t.lastLoginTime <= ?");
			params.add(DateHandler.parse(param.getLastLoginTimeEnd(), "yyyy-MM-dd"));
		}
		hql.append(" order by t.createTime desc");
		return params;
	}

	@Override
	public void exportUserComsExcel(UserComExcelParam param) throws Exception {
		 StringBuilder hql = new StringBuilder("from UserCompanyEntity t where 1=1 ");
	        List<Object> params = getUserComsHql(param, hql);
	        List list = findAll(hql.toString(), params);
	        List<UserCompanyExcel> excels = new ArrayList<>();
	        if(VerdictUtil.isValidCollection(list)){
	            List<UserCompanyEntity> userPers = (ArrayList<UserCompanyEntity>)list;
	            UserCompanyExcel excel;
	            for (UserCompanyEntity com:userPers){
	                excel=new UserCompanyExcel();
	                excel.setUserName(com.getUserName());
	                excel.setStatus(com.getStatus());
	                excel.setCompanyName(com.getCompanyName());
	                excel.setLicenseNumber(com.getLicenseNumber());
	                excel.setLicensePic(com.getLicensePic());
	                excel.setCompanyAddress(com.getCompanyAddress());
	                excel.setAuthStatus(com.getAuthStatus());
	                excel.setEmail(com.getEmail());
	                excel.setContactName(com.getContactName());
	                excel.setContactPhone(com.getContactPhone());
	                excel.setCompanyLegal(com.getCompanyLegal());
	                excel.setEmergencyContactName(com.getEmergencyContactName());
	                excel.setEmergencyContactPhone(com.getEmergencyContactPhone());
	                excel.setHeadPic(com.getHeadPic());
	                ComWxEntity comWxEntity = com.getComWxEntity();
	                if(comWxEntity!=null)excel.setOpenId(comWxEntity.getId());
	                excel.setCreateTime(DateHandler.dateToString(com.getCreateTime(),"yyyy/MM/dd HH:mm"));
	                excel.setLastLoginTime(DateHandler.dateToString(com.getLastLoginTime(),"yyyy/MM/dd HH:mm"));
	                excel.setLastLoginIp(com.getLastLoginIp());
	                excels.add(excel);
	            }
	            Map<String,String> maps= new LinkedHashMap<>();
	            maps.put("用户名","userName");
	            maps.put("状态(1可用2不可用)","status");
	            maps.put("单位名称","companyName");
	            maps.put("营业执照编号","licenseNumber");
	            maps.put("营业执照图片","licensePic");
	            maps.put("单位地址","companyAddress");
	            maps.put("认证状态(1未认证,2待认证,3已认证,4认证失败)","authStatus");
	            maps.put("邮箱","email");
	            maps.put("联系人姓名","contactName");
	            maps.put("联系人电话","contactPhone");
	            maps.put("公司法人","companyLegal");
	            maps.put("紧急联系人姓名","emergencyContactName");
	            maps.put("紧急联系人电话","emergencyContactPhone");
	            maps.put("头像url","headPic");
	            maps.put("微信openId","openId");
	            maps.put("创建时间","createTime");
	            maps.put("最后登录时间","lastLoginTime");
	            maps.put("最后登录ip","lastLoginIp");
	            XSSFExcelTool.exportExcel(maps, "sheet1", "企业用户信息", excels, AppContextHelper.getRequest(), AppContextHelper.getResponse());
	        }
		
	}
	
	 private List<Object> getUserComsHql(UserComExcelParam param, StringBuilder hql) throws Exception {
			List<Object> params = new ArrayList<>();
			if (VerdictUtil.isNotBlank(param.getUserName())) {
				hql.append(" and t.userName like ?");
				params.add("%" + param.getUserName() + "%");
			}
			if (VerdictUtil.isNotBlank(param.getCompanyName())) {
				hql.append(" and t.companyName like ?");
				params.add("%" + param.getCompanyName() + "%");
			}
			if (VerdictUtil.isNotBlank(param.getLicenseNumber())) {
				hql.append(" and t.licenseNumber like ?");
				params.add("%" + param.getLicenseNumber() + "%");
			}
			if (VerdictUtil.isNotBlank(param.getCompanyAddress())) {
				hql.append(" and t.companyAddress like ?");
				params.add("%" + param.getCompanyAddress() + "%");
			}
			if (VerdictUtil.isNotBlank(param.getEmail())) {
				hql.append(" and t.email like ?");
				params.add("%" + param.getEmail() + "%");
			}
			if (VerdictUtil.isNotBlank(param.getContactName())) {
				hql.append(" and t.contactName like ?");
				params.add("%" + param.getContactName() + "%");
			}
			if (VerdictUtil.isNotBlank(param.getContactPhone())) {
				hql.append(" and t.contactPhone like ?");
				params.add("%" + param.getContactPhone() + "%");
			}
			if (VerdictUtil.isNotBlank(param.getCompanyLegal())) {
				hql.append(" and t.companyLegal like ?");
				params.add("%" + param.getCompanyLegal() + "%");
			}
			if (VerdictUtil.isNotBlank(param.getEmergencyContactName())) {
				hql.append(" and t.emergencyContactName like ?");
				params.add("%" + param.getEmergencyContactName() + "%");
			}
			if (VerdictUtil.isNotBlank(param.getEmergencyContactPhone())) {
				hql.append(" and t.emergencyContactPhone like ?");
				params.add("%" + param.getEmergencyContactPhone() + "%");
			}
			if (VerdictUtil.isNotBlank(param.getLastLoginIp())) {
				hql.append(" and t.lastLoginIp like ?");
				params.add("%" + param.getLastLoginIp() + "%");
			}
			// equels
			if (VerdictUtil.gtOrEtoZero(param.getStatus())) {
				hql.append(" and t.status = ?");
				params.add(param.getStatus());
			}
			if (VerdictUtil.gtOrEtoZero(param.getAuthStatus())) {
				hql.append(" and t.authStatus = ?");
				params.add(param.getAuthStatus());
			}
			// time
			if (VerdictUtil.isNotBlank(param.getCreateTimeStart())) {
				hql.append(" and t.createTime >= ?");
				params.add(DateHandler.parse(param.getCreateTimeStart(), "yyyy-MM-dd"));
			}
			if (VerdictUtil.isNotBlank(param.getCreateTimeEnd())) {
				hql.append(" and t.createTime <= ?");
				params.add(DateHandler.parse(param.getCreateTimeEnd(), "yyyy-MM-dd"));
			}
			
			if (VerdictUtil.isNotBlank(param.getLastLoginTimeStart())) {
				hql.append(" and t.lastLoginTime >= ?");
				params.add(DateHandler.parse(param.getLastLoginTimeStart(), "yyyy-MM-dd"));
			}
			if (VerdictUtil.isNotBlank(param.getLastLoginTimeEnd())) {
				hql.append(" and t.lastLoginTime <= ?");
				params.add(DateHandler.parse(param.getLastLoginTimeEnd(), "yyyy-MM-dd"));
			}
			hql.append(" order by t.createTime desc");
			return params;
		}

	@Override
	public int updateStatusUserPer(String userId, Integer status) throws Exception {
		UserPersonalPO user = userPersonalRepository.findOne(userId);
		if(user==null)throw new BaseBusinessException(BaseBusinessError.USER_NOT_EXISTS.getCode(), BaseBusinessError.USER_NOT_EXISTS.getMessage());
		user.setStatus(status);
		user = userPersonalRepository.save(user);
		if(user!=null){
			//推送短信-待加
//			SmsService.sendSMS(findOne.getPhone(), SmsTemplate, params)
		}
		return 1;
	}

	@Override
	public int updateStatusUserCom(String companyId, Integer status) throws Exception {
		UserCompanyPO com = userCompanyRepository.findOne(companyId);
		if(com==null)throw new BaseBusinessException(BaseBusinessError.USER_NOT_EXISTS.getCode(), BaseBusinessError.USER_NOT_EXISTS.getMessage());
		com.setStatus(status);
		com = userCompanyRepository.save(com);
		if(com!=null){
			//推送短信-待加
//			iSmsService.sendSMS(findOne.getPhone(), SmsTemplate, params)
		}
		return 1;
	}

	@Override
	public int updateResetUserPer(String userId, String password) throws Exception {
		UserPersonalPO user = userPersonalRepository.findOne(userId);
		if(user==null)throw new BaseBusinessException(BaseBusinessError.USER_NOT_EXISTS.getCode(), BaseBusinessError.USER_NOT_EXISTS.getMessage());
		user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
		user = userPersonalRepository.save(user);
		if(user!=null){
			//推送短信-待加
//			iSmsService.sendSMS(findOne.getPhone(), SmsTemplate, params)
		}
		return 1;
	}

	@Override
	public int updateResetUserCom(String companyId, String password) throws Exception {
		UserCompanyPO com = userCompanyRepository.findOne(companyId);
		if(com==null)throw new BaseBusinessException(BaseBusinessError.USER_NOT_EXISTS.getCode(), BaseBusinessError.USER_NOT_EXISTS.getMessage());
		com.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
		com = userCompanyRepository.save(com);
		if(com!=null){
			//推送短信-待加
//			iSmsService.sendSMS(findOne.getPhone(), SmsTemplate, params)
		}
		return 1;
	}


	
}
	



	


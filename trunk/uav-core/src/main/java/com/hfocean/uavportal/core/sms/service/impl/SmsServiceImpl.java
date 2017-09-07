package com.hfocean.uavportal.core.sms.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hfocean.common.exception.BaseBusinessError;
import com.hfocean.common.exception.BaseBusinessException;
import com.hfocean.common.redis.CacheObj;
import com.hfocean.common.redis.RedisObjectService;
import com.hfocean.uavportal.core.sms.AlidayuSmsHelper;
import com.hfocean.uavportal.core.sms.enm.SmsTemplate;
import com.hfocean.uavportal.core.sms.service.ISmsService;
import com.hfocean.uavportal.core.user.po.UserCompanyPO;
import com.hfocean.uavportal.core.user.po.UserPersonalPO;
import com.hfocean.uavportal.core.user.repository.UserCompanyRepository;
import com.hfocean.uavportal.core.user.repository.UserPersonalRepository;
import com.hfocean.common.util.AppContextHelper;
@Service
public class SmsServiceImpl implements ISmsService {
	
	@Autowired
	private AlidayuSmsHelper alidayuSmsHelper;
	
	@Autowired
	private  RedisObjectService redisObjectService;
	
	@Autowired
	private UserPersonalRepository userPersonalRepository;
	
	@Autowired
	private UserCompanyRepository userCompanyRepository;
	
	private static final long TIME_OUT_PHONE=60L;//60秒限制
	private static final long CODE_TIME=900L;//15分钟验证码缓存
	private static final String VERIFICATION_CODE = "_verifyCode:";
	
	@Override
	public int sendSMS(String phone,String stemp,String... params){
		int result = 0;//0 失败，参数不全 1成功
		String[] keyArr = SmsTemplate.getKeyArr(stemp);
		Map<String, String> json = null;
		if(keyArr!=null){
			if(keyArr.length==params.length&&keyArr.length>0){
				json = new HashMap<String, String>();
				for(int i=0; i<keyArr.length; i++){
					json.put(keyArr[i], params[i]);
				}
				result = alidayuSmsHelper.sentSMS(stemp, phone, json);
			}
		}
		return result;
	}
	
	@Override
	public int sendVerifyCode(String phone, String stemp) throws Exception {
		Object ob = redisObjectService.getObject(VERIFICATION_CODE+AppContextHelper.getSession().getId());
		CacheObj co = null;
		if(ob != null) {
			co = (CacheObj) ob;
			long currentTimeMillis = System.currentTimeMillis();
			long codeTime = co.getCodeTime();
			long time = (currentTimeMillis-codeTime)/1000;
			//1分钟内重复获取
			if(time<TIME_OUT_PHONE)
				throw new BaseBusinessException(BaseBusinessError.VERIFY_CODE_TIME.getCode(),BaseBusinessError.VERIFY_CODE_TIME.getMessage());
		}
		String randomNumber = getRandomNumber();
		Map<String,String> param = new HashMap<String,String>();
		param.put("code", randomNumber);
		int result = sendVerifySMS(stemp, phone, param);
		if(result==0)throw new BaseBusinessException(BaseBusinessError.SEND_CODE_ERROR.getCode(), BaseBusinessError.SEND_CODE_ERROR.getMessage());
		return result;
	}
	
	@Override
	public int sendVerifyCodeByPhoneNotExist(String phone, String stemp) throws Exception {
		//验证手机号码是否未注册-已注册抛异常-注册用
		UserPersonalPO user = userPersonalRepository.findByPhone(phone);
		if(user!=null)throw new BaseBusinessException(BaseBusinessError.CODE_EXIST_PHONE.getCode(),BaseBusinessError.CODE_EXIST_PHONE.getMessage());
		UserCompanyPO userCom = userCompanyRepository.findByContactPhone(phone);
		if(userCom!=null)throw new BaseBusinessException(BaseBusinessError.CODE_EXIST_PHONE.getCode(),BaseBusinessError.CODE_EXIST_PHONE.getMessage());
		int result = sendVerifyCode(phone, stemp);
		return result;
	}
	
	@Override
	public int sendVerifyCodeByPhoneIsExist(String phone, String stemp) throws Exception {
		//验证手机号码是否已经注册-未注册抛异常-找回密码用
		UserPersonalPO user = userPersonalRepository.findByPhone(phone);
		if(user==null){
			UserCompanyPO userCom = userCompanyRepository.findByContactPhone(phone);
			if(userCom==null)throw new BaseBusinessException(BaseBusinessError.CODE_NOT_PHONE.getCode(),BaseBusinessError.CODE_NOT_PHONE.getMessage());
		}
		int result = sendVerifyCode(phone, stemp);
		return result;
	}

	/**
	* 生成随机数
	*
	* */
	private int randomInt(int from, int to) {
		Random r = new Random();
		return from + r.nextInt(to - from);
	}
	
	
	private String getRandomNumber() {
		
		//生成六位验证码
		String charValue = "";
		for (int i = 0; i < 6; i++) {
			char c = (char) (randomInt(0, 9) + '0');
			charValue += String.valueOf(c);
		}
		return charValue;
	}
	
	private int sendVerifySMS(String stemp, String phone, Map<String,String> param) {
		int success = 0;
		// 给目标手机号发送短信
		success = alidayuSmsHelper.sentSMS(stemp, phone, param);
		if(success == 1) {
			//将生成的六位验证码和传进来的手机号码存入缓存
			CacheObj co = new CacheObj(phone,param.get("code"));
			redisObjectService.putObject(VERIFICATION_CODE+AppContextHelper.getSession().getId(), co, CODE_TIME);
			return success;
		}
		return success;
		
	}
	
	@Override
	public String verifyCode(String verifyCode)throws Exception {
		CacheObj cacheObj = getCacheObj();
		if(cacheObj==null){
			throw new BaseBusinessException(BaseBusinessError.VERIFY_NOT_CODE.getCode(),BaseBusinessError.VERIFY_NOT_CODE.getMessage());
		}else{
			int frqNo = cacheObj.getFrqNo();
			if(frqNo>5)throw new BaseBusinessException(BaseBusinessError.VERIFY_CODE_NUMBER.getCode(),BaseBusinessError.VERIFY_CODE_NUMBER.getMessage());
		}
		boolean result = verify(verifyCode);
		if(!result)throw new BaseBusinessException(BaseBusinessError.SMS_CODE_ERROR.getCode(),BaseBusinessError.SMS_CODE_ERROR.getMessage());
		String phone = cacheObj.getPhone();
		if(phone==null||phone.equals(""))throw new BaseBusinessException(BaseBusinessError.VERIFY_NOT_CODE.getCode(),BaseBusinessError.VERIFY_NOT_CODE.getMessage());
		return phone;
	}
	
	private boolean verify(String verifyCode){
		CacheObj co = getCacheObj();
		// 缓存中验证码
		String cacheVerifyCode =null;
		cacheVerifyCode = String.valueOf(co.getVerifyNo());
		//如果短信中的验证码和缓存中的验证码一致，则验证成功
		if(StringUtils.hasText(verifyCode) && verifyCode.equals(cacheVerifyCode)) {
			co.setStatus(true);
			redisObjectService.putObject(VERIFICATION_CODE+AppContextHelper.getSession().getId(), co, CODE_TIME);
			return true;
		} else {
			co.setStatus(false);
			co.setFrqNo(co.getFrqNo()+1);
			redisObjectService.putObject(VERIFICATION_CODE+AppContextHelper.getSession().getId(), co, CODE_TIME);
			return false;
		}
	}

	private CacheObj getCacheObj() {
		Object ob = redisObjectService.getObject(VERIFICATION_CODE+AppContextHelper.getSession().getId());
		CacheObj co = null;
		if(ob == null) {
			return null;
		} else {
			co = (CacheObj) ob;
		}
		return co;
	}

	@Override
	public String verifyStatus()throws Exception {
		CacheObj cacheObj = getCacheObj();
		if(cacheObj==null)
			throw new BaseBusinessException(BaseBusinessError.VERIFY_NOT_CODE.getCode(),BaseBusinessError.VERIFY_NOT_CODE.getMessage());
		boolean status = cacheObj.getStatus();
		if(!status)throw new BaseBusinessException(BaseBusinessError.SMS_CODE_ERROR.getCode(),BaseBusinessError.SMS_CODE_ERROR.getMessage());
		String phone = cacheObj.getPhone();
		if(StringUtils.isEmpty(phone))throw new BaseBusinessException(BaseBusinessError.VERIFY_NOT_CODE.getCode(),BaseBusinessError.VERIFY_NOT_CODE.getMessage());
		return phone;
	}

	@Override
	public void removeCode() throws Exception {
		redisObjectService.delObject(VERIFICATION_CODE+AppContextHelper.getSession().getId());
	}

}

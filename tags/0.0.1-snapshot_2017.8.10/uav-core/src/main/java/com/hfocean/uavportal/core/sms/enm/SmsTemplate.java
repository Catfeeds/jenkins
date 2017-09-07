package com.hfocean.uavportal.core.sms.enm;


/**
 * @author guan.sj
 */
public enum SmsTemplate {
	
    /** 触发条件:验证手机号码  模板:尊敬的用户，您在e飞服务注册请求的验证码是${code}(请勿泄露)。15分钟内输入有效，如非本人操作，请忽略本短信。*/
	注册账号("SMS_72925005",new String[]{"code"}),
	
    /** 触发条件:验证手机号码  模板:尊敬的用户，您找回e飞服务账号密码的验证码是${code}(请勿泄露)。15分钟内输入有效，如非本人操作，请忽略本短信。*/
	找回密码("SMS_72755010",new String[]{"code"}),
	
    /** 触发条件:用户成功注册  模板:亲爱的${username}，您已成功注册，欢迎使用e飞服务畅享飞行。如有疑问题请拨020-82258167。*/
	注册成功("SMS_72770004",new String[]{"username"}),
	
    /** 触发条件:申请通过    模板:尊敬的用户，您编号为${code}的飞行计划备案申请已通过审核，请遵守规则，安全飞行。如有疑问题请拨020-82258167。*/
	申请通过("SMS_72865005",new String[]{"code"}),
	
    /** 触发条件:申请不通过   模板:尊敬的用户，您编号为${code}的飞行计划备案申请未通过审核，具体原因请登录服务平台查阅，如有疑问题请拨020-82258167。*/
	申请不通过("SMS_72830005",new String[]{"code"}),
	
    /** 触发条件:未报开飞动态  模板:尊敬的用户，您编号为${code}的飞行活动是否开始？请按规定及时上报飞行动态。*/
	未报开飞动态("SMS_72750011",new String[]{"code"}),
	
    /** 触发条件:飞行超时    模板:尊敬的用户，您编号为${code}的飞行活动已超过所备案的计划时间，请马上降落，否则您的用户权限将受到影响。*/
	飞行超时("SMS_72930004",new String[]{"code"}),
	
    /** 触发条件:支付成功    模板:尊敬的用户，您编号为${code}的飞行计划备案申请已支付成功，正在为您办理备案手续，如有如有疑问题请拨020-82258167，感谢您的使用。*/
	支付提醒("SMS_72820005",new String[]{"code"}),
	
    /** 触发条件:充值成功    模板:尊敬的用户，您的e飞服务账户于${time}日充值${charge}元已到帐，目前账户余额为${account}元，感谢您的使用。*/
	充值提醒("SMS_72805007",new String[]{"time","charge","account"}),

    /** 触发条件：管制飞行计划开始前一小时   模板:尊敬的用户，您编码号为${code}的飞行活动是否开始？请于起飞前1小时再次向管制部门提出申请，并按规定及时上报飞行动态。*/
    飞行计划确认提醒("SMS_77590069",new String[]{"code"})
    ;
    private String stemp;
    private String[] keyArr;
    
    private SmsTemplate(String stemp,String[] keyArr){
        this.stemp = stemp;
        this.keyArr = keyArr;
    }
    public String stemp(){
        return this.stemp;
    }
    
    public String[] keyArr(){
        return this.keyArr;
    }
    
    
    public static String[] getKeyArr(String stemp) {
        for (SmsTemplate sms : SmsTemplate.values()) {
            if (sms.stemp.equals(stemp)) {
                return sms.keyArr();
            }
        }
        return null;
    }
    
}

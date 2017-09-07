package com.hfocean.uavportal.core.user.service;

import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.service.BaseService;
import com.hfocean.uavportal.core.user.param.ResetPwdParam;
import com.hfocean.uavportal.core.user.param.UpdateUserComParam;
import com.hfocean.uavportal.core.user.param.UpdateUserPerParam;
import com.hfocean.uavportal.core.user.param.UserComRegisterParam;
import com.hfocean.uavportal.core.user.param.UserPerRegisterParam;
import com.hfocean.uavportal.core.user.po.UserCompanyPO;
import com.hfocean.uavportal.core.user.po.UserPersonalPO;
import com.hfocean.uavportal.core.user.vo.AuditSeasonVO;

/**个人用户
 * @author guan.sj
 */
public interface IUserServiceCore extends BaseService<UserPersonalPO,String> {

	UserPersonalPO saveUserPerRgister(UserPerRegisterParam param)throws Exception;
	
	UserPersonalPO updateUserPer(String userId, UpdateUserPerParam param)throws Exception;
	
	UserCompanyPO saveUserComRgister(UserComRegisterParam param)throws Exception;
	
	UserCompanyPO updateUserCom(String userId, UpdateUserComParam param)throws Exception;
	
	/**检查手机号码是否被注册
	 * @param phone
	 * @throws Exception 被注册抛出异常
	 */
	void verifyPhone(String phone)throws Exception;
	
	/**检查用户名是否被注册
	 * @param userName
	 * @throws Exception 被注册抛出异常
	 */
	void verifyName(String userName)throws Exception;
	
	/**检查身份证是否被注册
	 * @param userName
	 * @throws Exception 被注册抛出异常
	 */
	void verifyIdCard(String idCard)throws Exception;
	
	/**检查公司名称是否被注册
	 * @param userName
	 * @throws Exception 被注册抛出异常
	 */
	void verifyCompanyName(String companyName)throws Exception;
	
	/**检查社会信用代码是否被注册
	 * @param userName
	 * @throws Exception 被注册抛出异常
	 */
	void verifyLicenseNumber(String licenseNumber)throws Exception;
	
	Pager findNotices(Pager pager, String userId)throws Exception;
	
	/**重置用户密码
	 * @param param 重置参数
	 * @return
	 * @throws Exception
	 */
	int updateResetPassword(ResetPwdParam param)throws Exception;
	
	AuditSeasonVO findUserAuthStatus(String userId, Integer type)throws Exception;
	
	int updateUserPhone(String userId, Integer type, String phone)throws Exception;
}

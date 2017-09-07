package com.hfocean.uavportal.console.user.service;

import com.hfocean.uavportal.console.user.param.UserComExcelParam;
import com.hfocean.uavportal.console.user.param.UserListParam;
import com.hfocean.uavportal.console.user.param.UserPerExcelParam;
import com.hfocean.uavportal.core.common.bean.Pager;
import com.hfocean.uavportal.core.common.service.BaseService;
import com.hfocean.uavportal.core.user.po.UserPersonalPO;

public interface IUserService extends BaseService<UserPersonalPO,String> {
	/**查询用户列表
	 * @param param
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	Pager findUserPers(UserListParam param, Pager pager)throws Exception;
	
	/**查询企业列表
	 * @param param
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	Pager findUserComs(UserListParam param, Pager pager)throws Exception;
	
	/**审核个人用户
	 * @param userId
	 * @param authStatus
	 * @param reason
	 * @return
	 * @throws Exception
	 */
	int updateAuthUserPer(String userId,Integer authStatus, String reason)throws Exception;
	
	/**审核企业用户
	 * @param companyId
	 * @param authStatus
	 * @param reason
	 * @return
	 * @throws Exception
	 */
	int updateAuthUserCom(String companyId,Integer authStatus, String reason)throws Exception;
	
	/**导出个人信息excle
	 * @param param
	 * @throws Exception
	 */
	void exportUserPersExcel(UserPerExcelParam param)throws Exception;
	
	/**导出企业信息excle
	 * @param param
	 * @throws Exception
	 */
	void exportUserComsExcel(UserComExcelParam param)throws Exception;
	
	/**禁用启用个人用户
	 * @param userId
	 * @param status
	 * @return
	 * @throws Exception
	 */
	int updateStatusUserPer(String userId,Integer status)throws Exception;
	
	/**禁用启用企业用户
	 * @param companyId
	 * @param status
	 * @return
	 * @throws Exception
	 */
	int updateStatusUserCom(String companyId,Integer status)throws Exception;
	
	/**重置个人用户
	 * @param userId
	 * @param password
	 * @return
	 * @throws Exception
	 */
	int updateResetUserPer(String userId, String password)throws Exception;
	
	/**重置企业用户
	 * @param companyId
	 * @param password
	 * @return
	 * @throws Exception
	 */
	int updateResetUserCom(String companyId, String password)throws Exception;
}

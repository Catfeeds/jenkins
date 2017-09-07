package com.hfocean.uavportal.auth.system.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.hfocean.uavportal.auth.system.permission.vo.SysPermissionVO;
@Mapper
public interface ISysPermissionDao {

	
	@Select("select * from ("
			+ "select t1.*from tb_sys_user_role_ref t inner join tb_sys_role_resource_ref t1 on t.role_id = t1.role_id and t1.res_add_dele = 0 inner join tb_sys_role t2 on t2.role_id = t.role_id and t2.status = 1 where t.user_name = #{userName}"+
			" union " +
			"select r2.* from tb_sys_user_organization_ref r " +
			"inner join tb_sys_organization_role_ref r1 on r1.org_id = r.org_id " +
			"inner join tb_sys_role_resource_ref r2 on r1.role_id = r2.role_id and r2.res_add_dele = 0 "
			+ "inner join tb_sys_role t2 on t2.role_id = r2.role_id and t2.status = 1 "+
			"where r.user_name = #{userName}"
			+ ") t where not exists(select 1 from ("
			+ "select distinct t1.res_id from tb_sys_user_role_ref t inner join tb_sys_role_resource_ref t1 on t.role_id = t1.role_id and t1.res_add_dele = 1 inner join tb_sys_role t2 on t2.role_id = t.role_id and t2.status = 1 where t.user_name = #{userName}"
			+ " union "
			+ "select distinct r2.res_id from tb_sys_user_organization_ref r " +
			"inner join tb_sys_organization_role_ref r1 on r1.org_id = r.org_id " +
			"inner join tb_sys_role_resource_ref r2 on r1.role_id = r2.role_id and r2.res_add_dele = 1 " +
			"inner join tb_sys_role t2 on t2.role_id = r2.role_id and t2.status = 1 " +
			"where r.user_name = #{userName}"
			+ ") t2 where t2.res_id = t.res_id )"
			)
	List<SysPermissionVO> listMyPermission(String userName) ;

	@Select("select * from tb_sys_role_resource_ref t where t.role_id = #{roleId}")
	List<SysPermissionVO> listPermissionByRoleId(String roleId) ;


}

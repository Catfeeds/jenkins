package com.hfocean.uavportal.auth.system.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hfocean.uavportal.auth.base.Pager;
import com.hfocean.uavportal.auth.fishsea.pojo.SysRolePO;
import com.hfocean.uavportal.auth.system.role.vo.SysRoleResourceRefVO;
import com.hfocean.uavportal.auth.system.role.vo.SysRoleVO;
@Mapper
public interface ISysRoleDao {

	
	List<SysRoleVO> listRoles(@Param("roleName") String roleName,@Param("pager") Pager<SysRoleVO> pager) ;

	@Insert("insert into tb_sys_role(role_id,role_name,creater,create_time,status) values(uuid(),#{roleName},#{creater},now(),#{status})")
	void addRole(SysRolePO po) ;

	@Select("select * from tb_sys_role t where t.role_id = #{id} limit 1 ")
	SysRoleVO findRole(String id) ;

	void updateRole(SysRolePO po) ;

	@Delete("delete r1,r2,r3 from tb_sys_role r1 left join tb_sys_organization_role_ref r2 on r1.role_id = r2.role_id left join tb_sys_role_resource_ref r3 on r1.role_id = r3.role_id where r1.role_id = #{1}")
	void delRole(String id) ;

	@Select("select sum(tb.t) from "
			+ "((select count(1) as t from tb_sys_organization_role_ref r1 where r1.role_id = #{id})"
			+ " UNION "
			+ "(select count(1) as t from tb_sys_user_role_ref r2 where r2.role_id = #{id})"
			+ ") tb")
	long isRoleRef(String id) ;
	
	
	@Select("select group_concat(t.org_name) from tb_sys_organization t inner join tb_sys_organization_role_ref r on t.org_id = r.org_id where r.role_id = #{roleId}")
	String listRoleRefOrg(String roleId);
	
	@Select("select group_concat(t.user_name) from tb_sys_user t inner join tb_sys_user_role_ref r on t.user_name = r.user_name where r.role_id = #{roleId}")
	String listRoleRefUser(String roleId);
	

	@Select("select * from tb_sys_role_resource_ref t where t.role_id = #{roleId}")
	List<SysRoleResourceRefVO> listRoleResourceRef(String roleId) ;

	@Update("update tb_sys_role_resource_ref set res_add_dele = #{delOrAdd} where role_id = #{roleId} and find_in_set(res_id,#{resIds})")
	void updateRoleResourceRef(@Param("roleId") String roleId, @Param("resIds") String existRes, @Param("delOrAdd") String delOrAdd) ;

	void addRoleResourceRef(@Param("roleId") String roleId, @Param("resIds") List<String> newRes, @Param("delOrAdd") String delOrAdd,@Param("creater")String creater) ;

	@Delete("delete from tb_sys_role_resource_ref where role_id = #{roleId} and find_in_set(res_id,#{resIds})")
	void delRoleResourceRef(@Param("roleId") String id,@Param("resIds") String resIds) ;

	void addRoleUserRef(@Param("roleIds") String[] roleIds,@Param("userName") String userName,@Param("creater") String creater) ;

	@Delete("delete from tb_sys_user_role_ref where user_name = #{userName} and find_in_set(role_id,#{roleIds})")
	void delRoleUserRef(@Param("roleIds") String roleIds,@Param("userName") String userName) ;

	@Select("select count(1) from tb_sys_role t where find_in_set(t.role_id,#{roleIds})")
	long countRole(String roleIds) ;

	List<SysRoleVO> list4Roles(@Param("roleName")String roleName,@Param("status")Integer status) ;

	@Select("select t1.role_id,t1.role_name,t1.status,t.creater,t.create_time,null as org_id,null as org_name from tb_sys_user_role_ref t inner join tb_sys_role t1 on t1.role_id = t.role_id where t.user_name = #{userName} and t1.status = 1" + 
			" union " +
			"select t1.role_id,t1.role_name,t1.status,r1.creater,r1.create_time,o1.org_id,o1.org_name from tb_sys_user_organization_ref r inner join tb_sys_user u on r.user_name = u.user_name " +
			"inner join tb_sys_organization_role_ref r1 on r1.org_id = r.org_id "+ 
			"inner join tb_sys_organization o1 on o1.org_id = r1.org_id "+ 
			"inner join tb_sys_role t1 on t1.role_id = r1.role_id where r.user_name = #{userName} and t1.status = 1"
			)
	List<SysRoleVO> listUserRole(@Param("userName") String userName, @Param("pager") Pager<SysRoleVO> pager);

	@Select("select t1.role_id,t1.role_name,t1.status,t.creater,t.create_time from tb_sys_organization_role_ref t inner join tb_sys_role t1 on t1.role_id = t.role_id where t.org_id = #{orgId} and t1.status = 1")
	List<SysRoleVO> listOrgRole(@Param("orgId") String orgId, Pager<SysRoleVO> pager);

	void addRoleOrgRef(@Param("roleIds")String[] array, @Param("orgId")String orgId, @Param("creater")String userName);

	@Delete("delete from tb_sys_organization_role_ref where org_id = #{orgId} and find_in_set(role_id,#{roleIds})")
	void delRoleOrgRef(@Param("roleIds") String roleIds,@Param("orgId") String orgId);



}

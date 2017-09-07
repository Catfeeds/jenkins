package com.hfocean.uavportal.auth.system.org.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hfocean.uavportal.auth.fishsea.pojo.SysOrgPO;
import com.hfocean.uavportal.auth.system.org.vo.SysOrgVO;
@Mapper
public interface ISysOrgDao {

	@Insert("insert into tb_sys_organization values(#{orgId},#{orgName},#{orgDescription},#{parentId},#{seq},#{status},#{creater},now())")
	void addOrg(SysOrgPO po) ;

	void delOrg(@Param("orgId") String orgId, @Param("userName") String userName, @Param("delChild") boolean delChild) ;

	@Select("select t1.org_name as parent_name,t.* from tb_sys_organization t left join tb_sys_organization t1 on t.parent_id = t1.org_id where FIND_IN_SET(t.org_id,sys_fn_getOrgChildren(#{orgId})) order by seq")
	List<SysOrgVO> listCurrentUserOrg(String orgId) ;

	void updateOrg(SysOrgPO po) ;

	@Select("select t1.org_name as parent_name,t.* from tb_sys_organization t left join tb_sys_organization t1 on t.parent_id = t1.org_id where t.org_id = #{orgId} limit 1")
	SysOrgVO findOrg(String orgId) ;

	@Insert("insert into tb_sys_user_organization_ref values(#{userName},#{orgId},#{creater},now())")
	void addUserOrgRef(@Param("userName")String userName, @Param("orgId") String orgId,@Param("creater")String creater) ;

	@Update("update tb_sys_user_organization_ref set org_id = #{orgId} where user_name = #{userName}")
	void updateUserOrgRef(@Param("userName")String userName, @Param("orgId") String orgId) ;

	@Select("select t1.org_name as parent_name,t.* from tb_sys_organization t left join tb_sys_organization t1 on t.parent_id = t1.org_id order by seq")
	List<SysOrgVO> listAllOrg() ;

}

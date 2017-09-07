package com.hfocean.uavportal.auth.fishsea.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.hfocean.uavportal.auth.fishsea.pojo.UserOrgRef;
@Mapper
public interface UserOrgRefMapper {
	@Select("select * from tb_sys_user_organization_ref t where t.user_name = #{userName} ")
	List<UserOrgRef> findUserOrgRefList(String userName);
	
	@Insert("insert into tb_sys_user_organization_ref(user_name,org_id,creater,create_time) values(#{userName},#{orgId},#{creater},now())")
	void addUserOrgRef(UserOrgRef po) ;
}

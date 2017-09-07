package com.hfocean.uavportal.auth.system.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.hfocean.uavportal.auth.base.Pager;
import com.hfocean.uavportal.auth.fishsea.pojo.SysUserPO;
import com.hfocean.uavportal.auth.system.user.param.UserList;
import com.hfocean.uavportal.auth.system.user.vo.SysUserVO;
@Mapper
public interface ISysUserDao {

	List<SysUserVO> listUsers(@Param("param") UserList param, @Param("pager") Pager<SysUserVO> pager) ;

	@Insert("insert into tb_sys_user(user_name,password,nick_name,create_time,email,creater) values(#{userName},#{password},#{nickName},now(),#{email},#{creater})")
	void addUser(SysUserPO po) ;

	void updateUser(SysUserPO po) ;

	@Delete("delete from tb_sys_user where user_name = #{userName}")
	void delUser(String userName) ;

	SysUserVO findUser(UserList user) ;

	@Update("update tb_sys_user set password =#{password} where user_name = #{userName}")
	void updatePassword(@Param("userName") String userName, @Param("password") String password);

}

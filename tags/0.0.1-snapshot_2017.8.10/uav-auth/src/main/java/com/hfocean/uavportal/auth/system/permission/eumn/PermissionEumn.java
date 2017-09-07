package com.hfocean.uavportal.auth.system.permission.eumn;

public enum PermissionEumn {
	
	/*用户1管理*/
	查找用户列表("admin_user_findList","后台用户管理","查询用户列表"),
	新增用户("admin_user_add","后台用户管理","新增用户"),
	查找用户("admin_user_find","后台用户管理","查看用户详情"),
	删除用户("admin_user_del","后台用户管理","删除用户"),
	//重置密码("admin_user_resetPassword","用户管理","重置密码"),
	//修改密码("admin_user_updatePassword","用户管理","修改密码"),
	修改用户("admin_user_update","后台用户管理","修改用户信息"),
	/*用户角色管理*/
	查找用户角色("admin_role_listUserRole","后台用户角色管理","查询用户角色列表"),
	新增用户_角色("admin_role_addRoleUser","后台用户角色管理","新增用户的角色"),
	删除用户_角色("admin_role_delRoleUser","后台用户角色管理","删除用户的角色"),
	
	/*组织管理*/
	新增组织("admin_org_add","后台组织管理","新增组织"),
	删除组织("admin_org_del","后台组织管理","删除组织"),
	修改组织("admin_org_update","后台组织管理","修改组织"),
	查找组织("admin_org_find","后台组织管理","获得组织信息"),
	
	
	/*组织角色管理*/
	查找组织角色("admin_role_listOrgRole","后台组织角色管理","查询组织角色列表"),
	新增组织_角色("admin_role_addRoleOrg","后台组织角色管理","新增组织的角色"),
	删除组织_角色("admin_role_delRoleOrg","后台组织角色管理","删除组织的角色"),
	
	/*角色管理*/
	新增角色("admin_role_add","后台角色管理","新增角色"),
	修改角色("admin_role_update","后台角色管理","修改角色"),
	删除角色("admin_role_del","后台角色管理","删除角色"),
	查找角色("admin_role_find","后台角色管理","查找角色信息"),
	查找角色列表("admin_role_findList","后台角色权限管理","查询角色列表"),
	
	/*角色资源管理*/
	新增角色_权限("admin_role_addRoleResource","后台角色权限管理","给角色添加权限"),
	删除角色_权限("admin_role_delRoleResource","后台角色权限管理","给角色删除权限"),

	//用户
	个人用户列表("users","门户用户","分页查询个人用户列表"),
	企业用户列表("companys","门户用户","分页查询企业用户列表"),
	个人用户详情("users_detail","门户用户","查询个人用户详情"),
	企业用户详情("companys_detail","门户用户","查询企业用户详情"),
	个人用户审核("user_userId_status","门户用户","执行个人用户审核"),
	企业用户审核("company_company_status","门户用户","执行企业用户审核"),
	个人用户修改密码("user_reset","门户用户","修改个人用户密码"),
	企业用户修改密码("company_reset","门户用户","修改企业用户密码"),
	
	//飞行
	飞行计划列表("flyplans","飞行计划","分页查询飞行计划列表"),
	导出飞行计划列表("excel_flyplans","飞行计划","导出飞行计划列表"),
	飞行计划审核("flyplan_flyplanId_status","飞行计划","执行飞行计划审核"),
	
	//空域申请
	空域申请列表("airplan","空域申请","分页查询空域申请列表"),
	空域申请审核("airplan_airplanId_status","空域申请","执行空域申请审核"),
	
	//轮播图
	分页查询轮播图列表("banners","图片轮播","分页查询图片轮播列表"),
	新增轮播图("Putbanners","图片轮播","新增轮播图"),
	查询轮播图详情("banners_detail","图片轮播","查询轮播图详情"),
	修改轮播图("Updbanner_bannerid","图片轮播","修改轮播图详情"),
	删除轮播图("banner_bannerId","图片轮播","删除轮播图"),
	
	//门户
	获取门户一级菜单树("portal_menus","门户内容","获取门户一级菜单树"),
	查询稿件列表("portal_menus_menuid_content","门户内容","分页查询稿件列表"),
	查询稿件详情("portal_page_pageid","门户内容","查询稿件详情"),
	新增稿件("portal_page","门户内容","新增稿件"),
	修改稿件("portal_page_update","门户内容","修改稿件"),
	删除稿件("portal_page_delete","门户内容","删除稿件"),
	
	
	//留言
	查询留言列表("messages","留言反馈","分页查询留言列表"),
	删除留言("message_messageid","留言反馈","执行删除留言"),
	已读留言("Putmessage_messageid","留言反馈","执行已读留言"),
	
	//消息
	查询系统公告列表("announcements","消息管理","分页查询系统公告列表"),
	查询公告详情("announcement_announcementid","消息管理","查询系统公告详情"),
	修改系统公告("Putannouncement_announcementid","消息管理","修改系统公告详情"),
	新增系统公告("Addannouncement","消息管理","新增系统公告"),
	删除系统公告("Delannouncement_announcementid","消息管理","删除系统公告"),
	
	//资料
	查询资料列表("documents","资料管理","分页查询资料列表"),
	新增资料文档("put_documents","资料管理","新增资料文档"),
	删除资料文档("del_documents","资料管理","删除资料文档")
	
	;
	
	
	public String value;public String component;public String desc;
	
	PermissionEumn(String value,String component,String desc){
		this.value = value;this.component = component;this.desc = desc;
	}

	public String value() {
		return value;
	}


	public String component() {
		return component;
	}
	
	public String desc(){
		return desc;
	}

	

}

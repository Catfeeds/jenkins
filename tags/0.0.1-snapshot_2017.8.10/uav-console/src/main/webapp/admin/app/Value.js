Ext.define('Rich.Value',{
	singleton: true,
	all:'all',
	vistor:'vistor',
	owner:'owner',
	captain:'captain',
	boatman:'boatman',
	online:'online',
	history:'history',
	store:'store',
	route:'route',
	ship:'ship',
	fish:'fish',
	
	admin_user:["admin_user_findList","admin_user_add","admin_user_find","admin_user_del","admin_user_update"],
	admin_user_findList:"admin_user_findList",//"权限管理-用户管理","查询用户列表"),
	admin_user_add:"admin_user_add",//"权限管理-用户管理","新增用户"),
	admin_user_find:"admin_user_find",//"权限管理-用户管理","查找用户"),
	admin_user_del:"admin_user_del",//"权限管理-用户管理","删除用户"),
	admin_user_resetPassword:"admin_user_resetPassword",//"权限管理-用户管理","重置密码"),
	admin_user_updatePassword:"admin_user_updatePassword",//"权限管理-用户管理","修改密码"),
	admin_user_update:"admin_user_update",//"权限管理-用户管理","修改用户"),
	/*用户角色管理*/
	admin_userrole:["admin_role_listUserRole","admin_role_addRoleUser","admin_role_delRoleUser"],
	admin_role_listUserRole:"admin_role_listUserRole",//"权限管理-用户角色管理","查找用户角色"),
	admin_role_addRoleUser:"admin_role_addRoleUser",//"权限管理-用户角色管理","新增用户的角色"),
	admin_role_delRoleUser:"admin_role_delRoleUser",//"权限管理-用户角色管理","删除用户的角色"),
	/*组织管理*/
	admin_org:["admin_org_add","admin_org_del","admin_org_update","admin_org_find"],
	admin_org_add:"admin_org_add",//"权限管理-组织管理","新增组织"),
	admin_org_del:"admin_org_del",//"权限管理-组织管理","删除组织"),
	admin_org_update:"admin_org_update",//"权限管理-组织管理","修改组织"),
	admin_org_find:"admin_org_find",//"权限管理-组织管理","查找组织"),
	/*组织角色管理*/
	admin_orgrole:["admin_role_listOrgRole","admin_role_addRoleOrg","admin_role_delRoleOrg"],
	admin_role_listOrgRole:"admin_role_listOrgRole",//"权限管理-组织角色管理","查找组织角色"),
	admin_role_addRoleOrg:"admin_role_addRoleOrg",//"权限管理-组织角色管理","新增组织的角色"),
	admin_role_delRoleOrg:"admin_role_delRoleOrg",//"权限管理-组织角色管理","删除组织的角色"),
	/*角色管理       角色资源管理*/
	
	admin_role:["admin_role_add","admin_role_update","admin_role_del","admin_role_find","admin_role_findList","admin_role_addRoleResource","admin_role_delRoleResource"],
	admin_role_add:"admin_role_add",//"权限管理-角色管理","新增角色"),
	admin_role_update:"admin_role_update",//"权限管理-角色管理","修改角色"),
	admin_role_del:"admin_role_del",//"权限管理-角色管理","删除角色"),
	admin_role_find:"admin_role_find",//"权限管理-角色管理","查找角色"),
	admin_role_findList:"admin_role_findList",//"权限管理-角色资源管理","查找角色列表"),
	
	admin_role_addRoleResource:"admin_role_addRoleResource",//"权限管理-角色资源管理","给角色添加资源"),
	admin_role_delRoleResource:"admin_role_delRoleResource",//"权限管理-角色资源管理","给角色删除资源"),
	
	power:["admin_user_findList","admin_user_add","admin_user_find","admin_user_del","admin_user_update","admin_role_listUserRole","admin_role_addRoleUser","admin_role_delRoleUser",
	"admin_role_listUserRole","admin_role_addRoleUser","admin_role_delRoleUser","admin_role_add","admin_role_update","admin_role_del","admin_role_find","admin_role_findList",
	"admin_role_addRoleResource","admin_role_delRoleResource"],
	
	//用户
	number:["users","companys","users_detail","companys_detail","user_userId_status","company_company_status","user_reset","company_reset"],
	personal:"users",//"个人用户","个人用户界面权限"
	companys:"companys",//"企业用户","企业用户权限"
	users_detail:"users_detail",//"个人用户详情","查看个人用户详情"
	companys_detail:"companys_detail",//"企业用户详情","查看企业用户详情"
	user_userId_status:"user_userId_status",//"个人用户审核","个人用户审核"
	company_company_status:"company_company_status",//"企业用户审核","企业用户审核"
	user_reset:"user_reset",//"门户用户","修改个人用户密码"
	company_reset:"company_reset",//"门户用户","修改企业用户密码"
	
	
	//飞行
	fly:["flyplans","flyplans","excel_flyplans"],
	flyplans:"flyplans",//"飞行计划","飞行计划列表权限"
	flyplan_flyplanId_status:"flyplan_flyplanId_status",//"飞行计划","飞行计划审核")
	excel_flyplans:"excel_flyplans",//"飞行计划","导出飞行计划列表"
	
	//空域申请
	apply:["airplan","airplan_airplanId_status"],
	airplan:"airplan",//"空域申请","分页查询空域申请列表"
	airplan_airplanId_status:"airplan_airplanId_status",//"空域申请","执行空域申请审核"
	
	content:["banners","banners","portal_menus"],
	
	//轮播图
	banner:["banners","Putbanners","banners_detail","banner_bannerId","Updbanner_bannerid"],
	banners:"banners",//"图片轮播","分页查询图片轮播列表"
	Putbanners:"Putbanners",//"图片轮播","新增轮播图"
	banners_detail:"banners_detail",//"图片轮播","查询轮播图详情"
	banner_bannerId:"banner_bannerId",//"图片轮播","删除轮播图"
	Updbanner_bannerid:"Updbanner_bannerid",//"修改轮播图","修改轮播图详情"
	
	//门户
	paper:["portal_menus","portal_menus_menuid_content","portal_page_pageid","portal_page","portal_page_update","portal_page_delete"],
	portal_menus:"portal_menus",//"门户内容","获取门户一级菜单树"
	portal_menus_menuid_content:"portal_menus_menuid_content",//"门户内容","分页查询稿件列表"
	portal_page_pageid:"portal_page_pageid",//"门户内容","查询稿件详情"
	portal_page:"portal_page",//"门户内容","新增稿件"
	portal_page_update:"portal_page_update",//"门户内容","修改稿件"
	portal_page_delete:"portal_page_delete",//",门户内容","删除稿件"

	
	//留言
	message:["messages","message_messageid","Putmessage_messageid"],
	messages:"messages",//"留言反馈","分页查询留言列表"
	message_messageid:"message_messageid",//"留言反馈","执行删除留言"
	Putmessage_messageid:"Putmessage_messageid",//"留言反馈","执行已读留言"
	
	
	//消息
	news:["announcements","announcement_announcementid","Putannouncement_announcementid","Addannouncement","Delannouncement_announcementid"],
	announcements:"announcements",//"消息管理","分页查询系统公告列表"
	announcement_announcementid:"announcement_announcementid",//"消息管理","查询系统公告详情"
	Putannouncement_announcementid:"Putannouncement_announcementid",//"消息管理","修改系统公告详情"
	Addannouncement:"Addannouncement",//"消息管理","新增系统公告"
	Delannouncement_announcementid:"Delannouncement_announcementid",//"消息管理","删除系统公告"
	
	//资料
	document:["documents","put_documents","del_documents"],
	documents:"documents",//"资料管理","分页查询资料列表"
	put_documents:"put_documents",//"资料管理","新增资料文档"
	del_documents:"del_documents"//"资料管理","删除资料文档"
	
	
},function(){
	var v = Rich.V = Rich.Value;
	
	
	Rich.V.admin_ = [].concat(v.admin_user,v.admin_userrole,v.admin_org,v.admin_orgrole,v.admin_role);
});
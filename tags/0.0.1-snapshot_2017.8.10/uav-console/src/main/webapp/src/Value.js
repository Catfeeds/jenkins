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
	
	/*
	ship:["ship_findList","ship_add","ship_del","ship_update","ship_find"],
	ship_findList:"ship_findList",
	ship_add:"ship_add",
	ship_del:"ship_del",
	ship_update:"ship_update",
	ship_find:"ship_find"*/
	
	
	/*审核*/
	check:["console_user_query_audit","console_user_audit","console_ship_query_audit","console_ship_query_audit","console_route_select_audit","console_route_audit","console_addition_auditPage",
	"console_addition_audit","console_record_selectCertificateAuditsByPage","console_record_audit_certificate"],
	user_query_audit:"console_user_query_audit",//"审核管理","实名认证审核列表"
	user_audit:"console_user_audit",//"审核管理","实名认证提交审核结果"
	ship_query_audit:"console_ship_query_audit",//"审核管理","b端提交新船舶审核"
	ship_audit:"console_ship_audit",//"审核管理","b端提交审核结果"
	route_select_audit:"console_route_select_audit",//"审核管理","查询船舶加入线路审核列表"
	route_audit:"console_route_audit",//"审核管理","船舶加入线路审核结果"
	addition_auditPage:"console_addition_auditPage",//"审核管理","查询船新增附加项目审核列表"
	addition_audit:"console_addition_audit",//"审核管理","船新增附加项目审核结果"
	record_selectCertificateAuditsByPage:"console_record_selectCertificateAuditsByPage",//"审核管理","分页查询船家资格认证列表"
	record_audit_certificate:"console_record_audit_certificate",//"审核管理","查询船家资格认证结果"
	
	
	/*首页*/
	firstPage:["console_advert_banner_page","console_advert_banner_add","console_advert_banner_del","console_advert_banner_update","console_advert_banner_acfo",
	"console_advert_spiral_page","console_advert_spiral_add","console_advert_spiral_del","console_advert_spiral_detail","console_advert_spiral_update","console_advert_spiral_acfo",
	"console_recommend_add","console_recommend_queryDetail","console_recommend_update","console_recommend_selectRouteByPage","console_recommend_acfo","console_recommend_del",
	"console_keyword_page","console_keyword_add","console_keyword_del","console_keyword_update","console_keyword_queryDetail","console_keyword_acfo"],
	//广告
	advert_banner:["console_advert_banner_page","console_advert_banner_add"],
	advert_banner_page:"console_advert_banner_page",//"首页管理","分页查询首页广告列表"
	advert_banner_add:"console_advert_banner_add",//"首页管理","新增首页广告"
	advert_banner_del:"console_advert_banner_del",//"首页管理","删除首页广告"
	advert_banner_update:"console_advert_banner_update",//"首页管理","修改首页广告"
	advert_banner_queryDetail:"console_advert_banner_page",//"首页管理","查询首页广告详情"
	advert_banner_acfor:"console_advert_banner_acfo",//"首页管理","禁用/启用首页广告"
	//螺旋广告
	advert_spiral:["console_advert_spiral_page","console_advert_spiral_add"],
	advert_spiral_page:"console_advert_spiral_page",//"首页管理","分页查询首页螺旋广告列表"
	advert_spiral_add:"console_advert_spiral_add",//"首页管理","新增首页螺旋广告"
	advert_spiral_del:"console_advert_spiral_del",//"首页管理","删除首页螺旋广告"
	advert_spiral_detail:"console_advert_spiral_detail",//"首页管理","查询首页螺旋广告详情"
	advert_spiral_update:"console_advert_spiral_update",//"首页管理","修改首页螺旋广告"
	advert_spiral_acfo:"console_advert_spiral_acfo",//"首页管理","启用禁用首页螺旋广告"
	//推荐
	page_recommend:["console_recommend_selectRouteByPage","console_recommend_add"],
	recommend_add:"console_recommend_add",//"首页管理","新增精选线路"
	recommend_queryDetail:"console_recommend_queryDetail",//"首页管理","查询精选线路详情"
	recommend_update:"console_recommend_update",//"首页管理","修改精选线路"
	recommend_selectRouteByPage:"console_recommend_selectRouteByPage",//"首页管理","分页查询精选线路"
	recommend_acfo:"console_recommend_acfo",//"首页管理","启用禁用精选线路"
	recommend_del:"console_recommend_del",//"首页管理","删除精选线路"
	
	//关键字
	page_keyword:["console_keyword_page","console_keyword_add"],
	keyword_page:"console_keyword_page",//"首页管理","分页查询首页关键字列表"
	keyword_add:"console_keyword_add",//"首页管理","新增首页关键字"
	keyword_del:"console_keyword_del",//"首页管理","删除首页关键字"
	keyword_update:"console_keyword_update",//"首页管理","修改首页关键字"
	keyword_queryDetail:"console_keyword_queryDetail",//"首页管理","查询首页关键字详情"
	keyword_acfo:"console_keyword_acfo",//"首页管理","启用禁用首页关键字"
	
	/*通话数据*/
	news:["console_notice_queryNoticeByPage","dictionary_group","console_channel_page","console_msgpush_selectConfByPage"],
	notice_queryNoticeByPage:"console_notice_queryNoticeByPage",//"通用数据","查询通用数据须知界面是显示"
	dictionary_group:"dictionary_group",//"通用数据","查询通用数据数据字典界面是否显示"
	channel_page:"console_channel_page",//"通用数据","查询通用数据渠道管理界面是否显示"
	msgpush_selectConfByPage:"console_msgpush_selectConfByPage"//"通用数据","查询通用数据推送管理界面是否显示"
	
	
},function(){
	var v = Rich.V = Rich.Value;
	
	
	Rich.V.admin_ = [].concat(v.admin_user,v.admin_userrole,v.admin_org,v.admin_orgrole,v.admin_role);
});
Ext.define('Rich.Url',{
	singleton: true,
	
	//内容管理
	portalMenusPath:'../portal/menus/',//获取一级菜单
	portalDetailPath:'../portal/page/',//稿件详情
	portalAddPath:'../portal/page',//新增稿件
	portaldeelPath:'../portal/page/',//删除稿件
	bannersPicPath:'../banners',//轮播图列表
	bannerDetailPath:'../banner/',//更新轮播图
	
	
	//飞行计划
	flyplansPath:'../flyplans',//飞行计划列表
	flyComboPath:'../metadata/airspace',//飞行计划下拉框
	airplanApplyPath:'../airplan',//分页f飞行申请列表
	flyPlansCheckPath:'../flyplan/',//审核飞行计划   {flyplanId}/status
	flyListPath:'../excel/flyplans',//导出飞行列表
	applyListPath:'../excel/airplans',//导出空预申请列表
	
	//空域申请
	airplaceListPath:'../airplan',
	airplaceCheckPath:'../airplan/',
	
	//用户
	personalPath:'../users',//个人用户
	companyPath:'../companys',//企业用户
	personChePath:'../user/',//审批个人用户
	companChePath:'../company/',//审批公司用户
	defaultReasonPath:'../user/audit/reason',//审核失败的原因
	
	//留言/系统公告
	leaveMessagePath:'../messages',//留言列表
	messageDeletePath:'../message/',//删除留言
	sysPagePath:'../announcements',//系统公告列表
	sysDetailPath:'../announcement/',//公告详情
	sysUpdatePath:'../announcement/',//跟新稿件
	
	//资源
	dataAddPath:'../document',
	dataListPath:'../documents',
	
	//空域管理
	airplacePath:'../metadata/airspace',//分页查询空域管理列表
	airplaceDetailPath:'../getAirspace',//空域管理详情
	airplaceUpdatePath:'../updateAirspace',//空域管理详情修改
	airplaceAddPath:'../addAirspace',//新增空域
	airplacedelPath:'../delAirspace/',//删除空域
	
	dataUploadPath:'../document',
	fileUploadPath:'../oss/uploadFile',//存放图片的地址
	dictionaryPath:'../dictionary/group',//字典节点查询
	dictionaryPagePath:'../dictionary/selectItemsByParentCode',//字典数据
	dictionaryUpdatePath:'../dictionary/update',//修改数据字典数据
	dictionaryAddPath:'../dictionary/add',//增加数据字典数据
	dictionaryDelPath:'../dictionary/del',//删除数据字典数据
	
	
	
	/**
	 * system 权限
	 */
	loginPath:'../auth/login',//登录系统
	logoutPath:'../auth/logout',//退出系统
	userInfoPath:'../sys/user/info',//当前登录用户信息
	updateMyInfoPath:'../sys/user/updateMyInfo',//修改当前登录用户信息
	checkCodeImgletPath:'../console/verify/code',//验证码路径	
	rightPath:'../sys/permission/myPermission',	
	auserAddPath:'../sys/admin/add',
	orgAuserListPath:'../sys/admin/list',
	auserUpdatePath:'../sys/admin/update',	
	orgTreePath:'../sys/org/listCurrentUserOrg',
	
	
    //根据组织id查询组织信息
    orgInfoPath:'../sys/org/find',//参数为id 值:组织id
    addOrgPath:'../sys/org/add',//参数为org.xx    
    editOrgPath:'../sys/org/update',//参数为org.xx   
    delOrgPath:'../sys/org/del',//参数为id 值:组织id
    
    //根据组织id查询用户
    searchUserByOrgIdPath:'../sys/user/list',//参数为orgId 值:组织id
    //根据用户userName/主键 查询用户资料
    searchUserByIdPath:'../sys/user/find',//参数为user.userName 
    //修改用户信息
    editUserPath:'../sys/user/update',//参数为user.xx
    //删除用户
    delUserPath:'../sys/user/del',//参数为user.userName 
    //新增用户
    addUserPath:'../sys/user/add',//参数为user.xx 
    
    
    //查询用户角色
    searchRoleByUserName:'../sys/role/listUserRole',//参数:userName
    //添加角色给用户
    appendRoleToUser:'../sys/role/addRoleUser',// userName,roleIds
    //移除用户的角色
    removeRoleFromUser:'../sys/role/delRoleUser',
    
    //查询组织的角色
    searchRoleByOrgId:'../sys/role/listOrgRole',//参数:orgId
    //添加组织的角色
    appendRoleToOrg:'../sys/role/addRoleOrg',//参数:orgId ,roleIds
    //移除组织的角色
    removeRoleFromOrg:'../sys/role/delRoleOrg',//参数:orgId ,roleIds
	
	//所有角色列表
    searchAllRoles:'../sys/role/list',
    //添加角色
    addRole:'../sys/role/add',//参数: role.roleName ....
    //删除角色
    delRole:'../sys/role/del',//参数: roleId
    //修改角色
    editRole:'../sys/role/update',//参数:role.roleName .....
    //添加 角色的资源
    addRoleResourceRef:'../sys/role/addRoleResource', //参数:roleId(角色id),resIds(资源id,格式为: 11,22,33),resAddDele(说明: 增 0 /减 1 操作)
    //移除角色的资源
    delRoleResourceRef:'../sys/role/delRoleResource', //参数:roleId(角色id),resIds(资源id,格式为: 11,22,33)
    //获得角色的资源
    searchRoleResourceRef:'../sys/permission/listByRoleId', //参数:roleId(角色id)
    //获取所有资源列表
    searchAllResources:'../sys/permission/listAll',
	
	
	/**
	 * 图标
	 * 可以这么访问   Rich.Url.icons_19 (resources/images/icons目录下查找想用的图标)
	 */
	iconsPath:'resources/images/icons'
	/*
    skinPaths:[{rightId:'skin-blue',path:'skin/skin-blue.css'},{rightId:'skin-green',path:'skin/skin-green.css'},{rightId:'skin-black',path:'skin/skin-black.css'},{rightId:'skin-orange',path:'skin/skin-orange.css'}],
    getSkinPath:function(index,nocache){
    	var res = this.skinPaths[index].path;
    	if(res){
    		res = Ext.PATH_APP_RESOURCES + res;
    		if(nocache){
        		res = res +'?_dc='+ new Date().getTime();
        	}
    	}
    	return res;
    },
    getFullPath:function(){
    	
    }
    */
},function(me){
	for(var i=1;i<50;i++){
		me['icons_'+i] = me.iconsPath + '/'+i+'.png';
	}
});
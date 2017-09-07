Ext.define('Rich.model.Tuser',{
    extend: 'Ext.data.Model',
    idProperty: 'id',
    fields:[
    {name:'id',type:'int',defaultValue:0},//用户id
    
    {name:'auth_status',type:'int',defaultValue:0},//认证状态 0未认证 ,1已认证,2认证失败
    
    {name:'statu',type:'int',defaultValue:0},//认证状态 0未认证 ,1已认证,2认证失败
    
    'username',//用户名
    
    'name',//用户真实姓名
    
    'sex',//用户性别
    	
    'idCard',//用户身份证
    
    'idCardPic1',//用户身份证正面照片链接
    
    'idCardPic2',//用户身份证反面照片链接
    	
     'certificateType',//证书类型
     
     'certificateNumber',//证书号
     
     'qq',//qq号
     
     'email',//邮箱
     
     'phone',//联系电话
     
     'address',//住址
     
     'emergencyContactName',//紧急联系人名称
     
     'emergencyContactPhone',//紧急联系人电话
     
     'createTime',//注册时间
     
     'lastLoginTime',//最后登录时间 
     
     'lastLoginIp'//最后登录IP
    ]
    
});
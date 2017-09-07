Ext.define('Rich.model.Tickling',{
    extend: 'Ext.data.Model',
    idProperty: 'id',
    fields:[
    {name:'read',type:'int'},//留言是否有读
    
        'id',
    	'name',//留言人名,
    	'phone',//手机号
 		'email',//邮箱
    	'content',//留言内容
    	'status',//留言状态
		'createTime'//留言时间
		
    
    ]
    
});
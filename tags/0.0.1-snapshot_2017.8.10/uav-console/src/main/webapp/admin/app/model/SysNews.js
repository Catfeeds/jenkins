Ext.define('Rich.model.SysNews',{
    extend: 'Ext.data.Model',
    idProperty: 'id',
    fields:[
    	'checkNumber',//查看次数
    	
    	'createTime',//创建时间
    	
    	'creator',//创建人
    	
    	'id',
    	
    	{name:'status',type:'int'},//状态(0禁用1正常,2删除)
    
    	'subTitle',//子标题
    	
    	'title',//公告标题
    	
    	'titleDesc'//标题描述
    ]
});

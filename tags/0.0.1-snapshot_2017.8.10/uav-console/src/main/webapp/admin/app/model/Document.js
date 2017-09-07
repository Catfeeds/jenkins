Ext.define('Rich.model.Document',{
    extend: 'Ext.data.Model',
    idProperty: 'id',
    fields:[
    
		'createTime',//创建时间
		
		'creator',//创建人
		
		'id',
		
		'name',//文件名
		
		'updateTime',//修改时间
		
		'url'//地址
    ]
});
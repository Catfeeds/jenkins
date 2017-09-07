Ext.define('Rich.model.Content',{
    extend: 'Ext.data.Model',
    idProperty: 'id',
    fields:[
    
    	'createTime',//创建时间
    	
    	'creator',//创建人
    	
    	'id',//id
    	
    	'isParent',//是否是父节点
    	
    	'name',//名称
    	
    	'parentId',//父节点id
    	
    	'sortOrder',//排序
    	
    	'status',//状态(0禁用1正常,2删除)

    	'type',//类型0内容1列表
    	
    	'title',//标题
    	
    	'categoryId',//父节点id
    	
    	'updateTime'//修改时间
    ]
});
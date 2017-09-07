Ext.define('Rich.model.Picture',{
    extend: 'Ext.data.Model',
    idProperty: 'id',
    fields:[
	    'id',
	    {name:'sortOrder',type:'int'},//排序编号
	    'imgUrl',//图片地址
	    'imgLink',//图片链接
	    'titleDesc',//标题描述
	    'isHidden',//是否隐藏
	    'isDelete',//是否删除
	    'createTime',//创建时间
	    'isHidden',//是否隐藏(0显示,1隐藏)
	    'creator'//创建人
    ]
});
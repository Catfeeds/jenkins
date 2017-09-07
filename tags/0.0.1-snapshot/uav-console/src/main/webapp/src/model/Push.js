Ext.define('Rich.model.Push',{
    extend: 'Ext.data.Model',
    idProperty: 'id',
    fields:[
    
    {name:'id',type:'int'},

    {name:'confKey',type:'int',defaultValue:0},//配置键
    
    {name:'confValue',type:'int',defaultValue:0},//配置值0：关,1：开
    
    'confValueDesc',//配置描述

    'confName',//配置名称
    
    'confDesc',
    
    {name:'confType',type:'int',defaultValue:0},//配置类型n0：大于短信,1：极光推送,2：微信,3：A端客服
    
    'confTypeDesc'

    ]
});
Ext.define('Rich.model.Flight',{
    extend: 'Ext.data.Model',
    idProperty: 'id',
    fields:[
    
    
    'number',//空域申请编号
    
    'planes',//[{name:机型名称,number:机型数量}]
    
    'landInfo',//起降落位置
    
    'airInfo',//空域id
    
    'contactInfo',//单位联系人    
    
    'remark',//备注
    
    'userId',//用户ID
    
    'status',//审核状态
    
    'userName',//个人姓名/单位名称
    
    'userType',//用户类型 1-个人 2单位
    
    'planType',//计划类型
    
    'createTime',//申请日期
    
    'beginTime',//开始时间
    
    'startTime',//实际开始时间
    
    'finishTime',//实际结束时间
    
    'type',//type:报告类型 0为管制飞行计划，1为一类飞行计划，2为二类飞行计划
    
    'attaches',//附件地址
    
    'endTime'//结束时间
    
    
    ]
    
});
﻿/*
 * 钓点管理窗体
 * */
Ext.define('Rich.view.check.CheckMgrWindow',{
	requires:[
	'Ext.form.field.ComboBox'
	],
    extend: 'Rich.widget.Window',
    alias:'widget.checkmgrwindow',
    uses:['Rich.view.wharf.WharfForm'],
    width:0.5,
    height:0.8,
    minWidth:300,
    minHeight:200,
    autoScroll:true,
    title:'详细信息',
    
    fId:null,
    showById:function(fId){
    	this.fId = fId;
    	this.show();
    	/*
    	if(type == 1){
    		this.lookupI('bannerform').show();
    	}else if(type == 2){
    		this.lookupI('recommendform').show();
    	}
    	*/
    },
    initComponent:function(){
    	var me = this;
    	var tp = me.type;
    	Ext.apply(me,{
    		/*layout:'fit',
    		items:[{
				itemId:'tuserform',
				//title:'广告详情',
				xtype:'tuserform',
				//buttons:['->','u'],
				showFn:function(){
					var fid = this.up('window').fId;
					this.loadById(fid);
				}
			}]*/
    	});
    	me.callParent(arguments);
    }
});
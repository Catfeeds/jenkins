﻿/*
 * 船只审核窗体
 * */
Ext.define('Rich.view.check.ShipCheckWindow',{
	requires:[
	'Ext.form.Panel',
	'Rich.component.DicComboBox'
	],
    extend: 'Rich.widget.Window',
    alias:'widget.shipcheckwindow',
    uses:[],
    //resizable:false,
    width:500,
    height:300,
    autoScroll:true,
    title:'船舶审核',
    
    
    initComponent:function(){
    	var me = this;
    	
    	Ext.apply(me,{
    		layout:'fit',
    		items:[{
    			itemId:'form',
    			xtype:'form',
    			padding:'10 10 10 10',
    			layout:{
    				type:'vbox',
    				align:'stretch'
    			},
    			items:[{
			    	xtype:'diccombo',
			        fieldLabel: '认证结果',
			        itemId:'result',
			        name: 'result',
			        editable:false,
			        allowBlank:false,
			        typeName:'result'
			    },{
			    	flex:1,
			    	allowBlank:false,
    				fieldLabel:'原因',
    				xtype:'textarea',
    				name:'reason'
    			},{
    				xtype:'hiddenfield',
    				value:7,
    				name:'type'
    			}]
    		}],
    		buttons:[{
    			text:'重置',
    			handler:function(){
    				this.up('window').lookupI('form').getForm().reset();
    			}
    		},'->',{
    			text:'提交修改',
    			handler:function(){
    				this.up('window').submit();
    			}
    		}]
    	});
    	me.callParent(arguments);
    },
    submit:function(){
    	var fm = this.lookupI('form');
    	if(!fm.isValid())
		{
			Rich.Msg.error('错误','有不合法的输入.');
			return null;
		}
    	var vas = fm.getValues();
    	vas.targetId = this.ShipId.get('id');
    	/*
    	if(this.tuser.get('authStatus') == vas.authStatus){
    		Rich.Msg.alert('提示','认证状态并未发生改变.');
			return null;
    	}
    	*/
    	this.el.mask('提交中...');
    	Rich.JsonAjax.request({
			method:'post',
			getMethod:function(){return "post"},
			url:Rich.Url.shipCheckResultPath,       	
			params:vas,
			callback:this._submitBack,
			scope:this
		});
    },
    _submitBack:function(o,f,r){
    	this.el.unmask();
    	if(f){
    		this.close(true);
    		this.returnValue(f);
    		//Ext.getCmp('chuanboshenhe').getStore().reload();
    	}
    }
});
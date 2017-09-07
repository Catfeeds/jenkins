﻿/*
 * 附加项目审核窗体
 * */
Ext.define('Rich.view.check.ProjectCheckWindow',{
	requires:[
	'Ext.form.Panel',
	'Rich.component.DicComboBox'
	],
    extend: 'Rich.widget.Window',
    alias:'widget.projectcheckwindow',
    uses:[],
    //resizable:false,
    width:800,
    height:600,
    autoScroll:true,
    title:'附加服务审核',
    
    shipId: null,
    shipFee:null,
    showByShipId:function(shipId,shipFee){
    	this.shipId = shipId;
    	this.shipFee = shipFee;
    	this.down('numberfield').setValue(shipFee);
    	this.show();
    },
   
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
    				xtype:'numberfield',
    				item:'shipFee',
    				fieldLabel:'成本价',
    				disabled:true,
    				name:'shipFee'
    			},{
			    	xtype:'diccombo',
			        fieldLabel: '认证结果',
			        itemId:'result',
			        name: 'result',
			        editable:false,
			        allowBlank:false,
			        //value:zt,
			        typeName:'result'
			    },{
			    	fieldLabel:'原因',
			    	allowBlank:false,
    				xtype:'textarea',
    				name:'reason'
    			},{
    				xtype:'hiddenfield',
    				value:7,
    				name:'type'
    			},{
    				xtype:'textfield',
    				fieldLabel:'销售价',
    				name:'sale'
    			},{
    				
    				xtype:'textfield',
    				fieldLabel:'折扣价',
    				name:'discountFee'
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
			url:Rich.Url.shipProjectResultPath+'?targetId='+this.shipId,       	
			params:vas,
			callback:this._submitBack,
			scope:this
		});
    },
    _submitBack:function(o,f,r){
    	this.el.unmask();
    	if(f){
    		this.close(true);
    		//this.returnValue(f);
    		Ext.getCmp('fujiashenhe').getStore().reload();
    	}
    }
});
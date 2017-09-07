﻿/*
 * 用户认证窗体
 * */
Ext.define('Rich.view.check.RouteCheckWindow',{
	requires:[
	'Ext.form.Panel',
	'Rich.component.DicComboBox'
	],
    extend: 'Rich.widget.Window',
    alias:'widget.routecheckwindow',
    uses:[],
    //resizable:false,
    minWidth:300,
    minHeight:700,
    autoScroll:true,
    title:'线路审核',
    
    routeId:null,
    shipFee:null,
    shipNa:null,
    shipId:null,
    showByRouteId:function(routeId,shipFee,shipNa,shipId){
    	this.routeId = routeId;
    	this.shipFee = shipFee;
    	this.shipNa = shipNa;
    	this.shipId = shipId;
    	this.down('numberfield').setValue(shipFee);
    	this.down('triggerfield').setValue(shipNa);
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
    	            xtype      : 'fieldcontainer',
    	            fieldLabel : '船舶编号',	        	            
    	            layout: 'hbox',
    	            disabled:false,
	            	items: [{
						xtype: 'triggerfield',
						flex:1,
						editable:false,
						disabled:false,
					    name: '船舶编号',
					    onTriggerClick: function(e) {
					    	var ShipId = this.shipId;
					        Ext.create('Rich.view.ship.ShipMgrWindow').showByShipId(ShipId);
					    }
					}]
    	        },{
    				xtype:'numberfield',
			    	disabled:true,
    				fieldLabel:'成本价',
    				name:'shipFee'
    			},{
			    	xtype:'diccombo',
			        fieldLabel: '认证结果',
			        itemId:'result',
			        name: 'result',
			        editable:false,
			        allowBlank:false,
			        typeName:'result'
			    },{
			    	xtype:'textarea',
			    	allowBlank:false,
    				fieldLabel:'原因',
    				name:'reason'
    			},{
    				xtype:'textfield',
    				fieldLabel:'原价',
    				name:'routeFee'
    			},{
    				xtype:'textfield',
    				fieldLabel:'折扣价',
    				name:'discountFee'
    			},{
    				xtype:'textfield',
    				fieldLabel:'最大优惠金额',
    				name:'maxFree'
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
    	vas.targetId = this.routeId;
    	this.el.mask('提交中...');
    	Rich.JsonAjax.request({
			method:'post',
			getMethod:function(){return "post"},
			url:Rich.Url.routeCheckResultPath+'?type=7',       	
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
    	}
    }
});
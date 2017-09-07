﻿/*
 * 船只审核窗体
 * */
Ext.define('Rich.view.check.OwnerCheckWindow',{
	requires:[
	'Ext.form.Panel',
	'Rich.component.DicComboBox'
	],
    extend: 'Rich.widget.Window',
    alias:'widget.ownercheckwindow',
    uses:[],
    width:800,
    height:500,
    //autoScroll:true,
    title:'证书认证',
    
    id:null,
    cid:null,
    typeDesc:null,
    code:null,
    showById:function(cid,typeDesc,id){
    	this.cid = cid;
    	this.typeDesc = typeDesc;
    	this.id = id;
    	this.show()
    	this.lookupI('name').setValue(typeDesc);
    	this.lookupI('id').setValue(id);
    	Rich.JsonAjax.request({
			method:'get',
    		url:Rich.Url.OwnerPaperDetaPath,
    		params:{
    			id:cid
        	},
    		callback:function(o,f,r){
    			this.lookupI('number').setValue(r.responseJson.data.number);
    			this.lookupI('issuingDate').setValue(r.responseJson.data.issuingDate);
    			this.lookupI('code').setSrc(r.responseJson.data.picFull);
    		},
    		scope:this
		});
    },
    
    initComponent:function(){
    	var me = this;
    	
    	Ext.apply(me,{
    		layout:'fit',
    		items:[{
				xtype:'form',
				itemId:'form',
				//title:'订单信息',
				cls:'r-highlight-disabled',
		        defaults: {
		            border: false,
		            xtype: 'panel',
		            flex: 1,
		            layout: 'anchor',
		            defaults:{
		            	anchor: '80%'			            
		            }
		        },
		        layout: 'hbox',
		        margin:'10 0 0 0',
		        items: [{
		        	defaults:{anchor: '100%',margin:' 5 5 5 10'},
		            items: [{
		            	xtype:'hiddenfield',
		            	itemId:'id',
		            	name:'id'
		            },{
		                xtype:'textfield',
		                fieldLabel: '证件名',
		                itemId:'name',
		                disabled:true,
		                name: 'name'
		            },{
		                xtype:'textfield',
		                fieldLabel: '证件号',
		                disabled:true,
		                itemId:'number',
		                name: 'number'
		            },{
		                xtype:'textfield',
		                fieldLabel: '签发日期',
		                itemId:'issuingDate',
		              	disabled:true,
		                name: 'issuingDate'
		            },{
				    	xtype:'diccombo',
				        fieldLabel: '认证结果',
				        itemId:'result',
				        name: 'result',
				        editable:false,
				        allowBlank:false,
				        typeName:'result'
		            },{
				    	allowBlank:false,
	    				fieldLabel:'原因',
	    				xtype:'textarea',
	    				itemId:'reason',
	    				name:'reason'
		            }]
		        },{xtype:'box',flex:0.1},{
		        	defaults:{disabled:true,anchor: '100%',margin:'0 0 0 10'},
		            items: [{
		            	xtype:'image',
		            	name:'code',
		            	width:200,
		            	height:200,
		            	itemId:'code',
		            	fieldLabel: '照片'
		            }]
		        }]
    		}],
    		buttons:[{
    			text:'重置',
    			handler:function(){
    				this.up('window').lookupI('result').reset();
    				this.up('window').lookupI('reason').reset();
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
    	this.el.mask('提交中...');
    	Rich.JsonAjax.request({
			method:'post',
			getMethod:function(){return "post"},
			url:Rich.Url.OwnerPaperResuPath,       	
			params:vas,
			callback:this._submitBack,
			scope:this
		});
    },
    _submitBack:function(o,f,r){
    	this.el.unmask();
    	if(f){
    	}
    }
});
﻿/*
 * 订单详情窗体
 * */
Ext.define('Rich.view.tuser.CompanyDetailWindow',{
	requires:['Ext.form.Panel'
	,'Ext.form.field.ComboBox'
	],
    extend: 'Rich.widget.Window',
    alias:'widget.companydetailwindow',
    minWidth:600,
    minHeight:400,
   	width:0.8,
    height:0.8,
    autoScroll:true,
    title:'公司用户信息',
 
    setV:function(rec){
    	this.lookupI('form').getForm().setValues(rec);
    	var sta = this.lookupI('status');
    	if(rec.status == 1){
    		sta.setValue('可用')
    	}else{
    		sta.setValue('不可用')
    	};
    	var auth = this.lookupI('auth');
    	var authStatus = this.lookupI('authStatus');
    	switch(rec.authStatus){
    		case 1:
	    		auth.setValue('未认证');
	    		authStatus.setValue('未认证');
	    		auth.setDisabled(true);
	    		break;
    		case 2:
	    		auth.setValue('待认证');
	    		authStatus.setValue('待认证');
	    		this.down('button').setVisible(true)
	    		break;
    		case 3:
	    		auth.setValue('已认证');
	    		authStatus.setValue('已认证');
	    		auth.setDisabled(true);
	    		break;
    		case 4:
	    		auth.setValue('认证失败');
	    		authStatus.setValue('认证失败');
	    		this.down('button').setVisible(true)
	    		var reason = this.lookupI('reason');
	    		reason.show();
    			reason.setDisabled(true);
    			break;
    	};
    	if(rec.headPic){
    		var headPic = rec.headPic.replace('"','');
	    	this.lookupI('headPic').setSrc(headPic);
    	}
    	
    	if(rec.licensePic){
    		var licensePic = rec.licensePic.replace('"','');
    		this.lookupI('licensePic').setSrc(licensePic);
    	}
    	//this.lookupI('priceCt').setDisabled(true);
    	Rich.JsonAjax.request({
			url:Rich.Url.defaultReasonPath,
			method:'get',
			params:{
				userId:rec.id,
				type:2
			},
			callback:function(o,f,r){
				if(f){
					if(rec.authStatus == 4){
						var reason = r.responseJson.data.reason;
						this.lookupI('reason').setValue(reason);
					}
				}
			},
			scope:this
		});
    },
    
    initComponent:function(){
    	var me = this;   
    	
    	var states = Ext.create('Ext.data.Store', {
		    fields: ['abbr', 'name'],
		    data : [
		        {'abbr':"1", 'name':"通过"},
		        {'abbr':"0", 'name':"不通过"}
		    ]
		});
		
    	Ext.apply(me,{
    		//layout:'fit',
    		dockedItems:{
    			docked:'top',
    			itemId:'topToolbar',
    			xtype:'toolbar',
    			items:['->']
    		},
    		items:[{
				xtype:'form',
				itemId:'form',
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
		        items: [{
				 	margin:'0 0 5 0',
					padding:'5 5 5 5',
					cls:'x-fieldset',
					itemId:'priceCt',
				 	layout: {
				        type: 'vbox',
				        align: 'stretch'
				    },
			 		items:[{
		                xtype:'textfield',
		                fieldLabel: '用户名 ',
		                disabled:true,
		                itemId:'userName',
		                name: 'userName'
		            },{
		            	xtype:'textfield',
		            	fieldLabel: '单位名 ',
		            	disabled:true,
		            	name:'companyName',
		            	itemId:'companyName'
		            },{
						xtype:'textfield',
						fieldLabel : '营业执照码',
	            		disabled:true,
						flex:1,
						itemId:'licenseNumber',
					    name: 'licenseNumber'
					},{
						xtype:'textfield',
						fieldLabel : '邮箱',
	            		disabled:true,
						flex:1,
						itemId:'email',
					    name: 'email'
					},{
		                xtype:'textfield',
		                fieldLabel: '公司法人',
		                disabled:true,
		                itemId:'companyLegal',
		                name: 'companyLegal'
	            	},{
	            		xtype:'textfield',
		                fieldLabel: '社会统一信用代码',
		                lableWith:120,
		                disabled:true,
		                itemId:'licenseNumber',
		                name: 'licenseNumber'
	            		
	            	},{
		                xtype:'textfield',
		                fieldLabel: '联系人',
		                disabled:true,
		                itemId:'contactName',
		                name: 'contactName'
	            	},{
		                xtype:'textfield',
		                fieldLabel: '联系电话',
		                disabled:true,
		                itemId:'contactPhone',
		                name: 'contactPhone'
	            	},{
		                xtype:'textfield',
		                fieldLabel: '公司地址',
		                disabled:true,
		                itemId:'companyAddress',
		                name: 'companyAddress'
	            	},{
		                xtype:'textfield',
		                fieldLabel: '紧急联系人',
		                disabled:true,
		                itemId:'emergencyContactName',
		                name: 'emergencyContactName'
	            	},{
	            		xtype:'textfield',
		                fieldLabel: '紧急联系方式',
		                disabled:true,
		                itemId:'emergencyContactPhone',
		                name: 'emergencyContactPhone'
		           },{
		                xtype:'textfield',
		                fieldLabel: '认证状态',
		                disabled:true,
		                itemId:'authStatus',
		                name: 'authStatus'
	            	},{
		                xtype:'textfield',
		                fieldLabel: '用户状态',
		                disabled:true,
		                itemId:'status',
		                name: 'status'
	            	}]
		        },{
					itemId:'rightCt',
	    	   		margin:'10 10 10 10',
		            items: [{
			        	xtype:'panel',
			        	layout:{
			        		type:'vbox',
			        		align:'stretch'
			        	},
			        	items:[{
			        		layout:'hbox',
				        	items:[{
				            	xtype:'image',
				            	cls:'x-fieldset',
				            	width:290,
				            	height:250,
				            	padding:'5 5 5 5',
				            	name:'licensePic',
				            	title:'营业执照',
				            	itemId:'licensePic',
				            	listeners: {
				            		click: {
							           element: 'el',
							           fn: function(a,b,c){
							            	new Rich.Window({
							            		title:'营业执照',
							            		width:0.6,
								            	height:0.9,
							            		layout:"fit",
							            		items:[{
				            						xtype:'image',
				            						src:b.currentSrc
							            		}]
							            	}).show();
							           }
				            		}
				            	}
					        },{width:5},{
				            	xtype:'image',
				            	cls:'x-fieldset',
				            	width:295,
				            	height:250,
				            	hidden:true,
				            	padding:'5 5 5 5',
				            	name:'headPic',
				            	title:'头像',
				            	itemId:'headPic',
			            		listeners: {
				            		click: {
							            element: 'el',
							            fn: function(a,b,c){
							            	new Rich.Window({
							            		title:'营业执照',
							            		width:0.6,
								            	height:0.9,
							            		layout:"fit",
							            		items:[{
				            						xtype:'image',
				            						src:b.currentSrc
							            		}]
							            	}).show();
							        	}}
			            			}
				           		},{width:5}]
				       	},{
			                xtype:'hiddenfield',
			                itemId:'id',
			                name: 'id'
				        },{
					    	xtype:'combobox',
					    	fieldLabel:'审核结果',
					    	store: states,
					    	anchor:'50%',
					    	maxWidth:290,
					    	labelWidth:70,
					    	editable:false,
					    	allowBlank:false,
						    queryMode: 'local',
						    displayField: 'name',
						    valueField: 'abbr',
						    itemId:'auth',
					    	name:'authStatus',
					    	listeners:{
				               'select':function(me0, record, index, eOpts){
				               		var type = record['0'].data.abbr;
				               		if(type != 1){
				               			this.up('form').lookupI('reason').show();
				               			this.up('form').lookupI('reason').setDisabled(false);
				               		}else{
				               			this.up('form').lookupI('reason').hide();
				               		}
								}
		            		}
						},{
					    	xtype:'textareafield',
					    	fieldLabel: '原因',
					    	hidden:true,
					    	maxWidth:290,
					    	labelWidth:70,
					    	itemId:'reason',
					    	name:'reason'
						}]
			         }]
			     }]
			 }],
    		 buttons:['->',{
    			text:'提交审核',
    			itemId:'sae',
    			hidden:true,
    			handler:function(){
    				this.up('window').submit();
    			}
    		}]	
    	});
    	me.callParent(arguments);
    	this.setV(this.rec);
    },
     submit:function(){
    	var fm = this.lookupI('form');
    	if(!fm.isValid()){
			Rich.Msg.error('错误','有不合法的输入.');
			return null;
		}
    	var va = fm.getValues();
    	if(va.status != '待认证'){
	    	var id = va.id;
	    	var url = Rich.Url.companChePath+id+'/authStatus';
	    	Rich.JsonAjax.request({
				method:'put',
				getMethod:function(){return "put"},
				url:url,     	
				params:va,
				callback:this._submitBack,
				scope:this
			});
    	}else{
    		Rich.Msg.alert('消息','未做审核操作');
    	}
    },
    _submitBack:function(o,f,r){
    	if(f){
    		this.close(true);
    		//Rich.Msg.alert('消息','审核完成');
    		this.returnValue(o,f,r);
    	}
    }
});
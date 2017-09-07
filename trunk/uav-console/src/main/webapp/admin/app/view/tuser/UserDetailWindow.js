﻿/*
 * 订单详情窗体
 * */
Ext.define('Rich.view.tuser.UserDetailWindow',{
	requires:['Ext.form.Panel'
	,'Ext.form.field.ComboBox'
	],
    extend: 'Rich.widget.Window',
    alias:'widget.userdetailwindow',
    minWidth:600,
    minHight:600,
    width:0.8,
    height:0.8,
    autoScroll:true,
    title:'详细信息',
    
    setV:function(rec){
    	//debugger
    	this.lookupI('form').getForm().setValues(rec);
    	var sta = this.lookupI('status');
    	if(rec.status == 1){
    		sta.setValue('可用')
    	}else{
    		sta.setValue('不可用')
    	};
    	var auth = this.lookupI('auth');
    	var authStatus = this.lookupI('authStatus');
    	this.lookupI('sexs').setValue(rec.sex =='M'?'男':'女')
    	
    	var date = new Date(rec.birthday);
		var y = date.getFullYear() + '-';
		var m = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
		var d = date.getDate() + ' ';
		this.lookupI('birthday').setValue(y+m+d)
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
    	var certi = this.lookupI('certificateType');
    	
    	if(rec.idcardPic1){
    		var idcardPic1 = rec.idcardPic1.replace('"','');
    		this.lookupI('idCardPic1').setSrc(idcardPic1);
    		}
    	if(rec.idcardPic2){
    		var idcardPic2 = rec.idcardPic2.replace('"','');
    		this.lookupI('idCardPic2').setSrc(idcardPic2);
    	}
    	if(rec.idcardPic3){
    		var idcardPic3 = rec.idcardPic3.replace('"','');
    		this.lookupI('idCardPic3').setSrc(idcardPic3);
    	}
    	
    	switch(rec.certificateType){
    		case 0:
    			certi.setValue('未取得无人机相关证件');
    			break;
    		case 1:
    			certi.setValue('民用无人驾驶航空器系统驾驶员合格证');
    			break;
    		case 2:
    			certi.setValue('民用无人驾驶航空器系统操作手合格证');
    			break;
    		case 3:
    			certi.setValue('无人机专业技能证');
    			break;
    		case 4:
    			certi.setValue('无人机操作资格证');
    			break;
    		case 5:
    			certi.setValue('ASFC证书');
    			break;
    		default:
    			certi.setValue('未知');
    	};
    	//this.lookupI('priceCt').setDisabled(true);
    	Rich.JsonAjax.request({
			url:Rich.Url.defaultReasonPath,
			method:'get',
			params:{
				userId:rec.id,
				type:1
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
    		items:[{
				xtype:'form',
				itemId:'form',
				cls:'r-highlight-disabled',		        defaults: {
		            border: false,
		            xtype: 'panel',
		            flex: 1,
		            layout: 'anchor',
		            defaults:{
		            	anchor: '80%'			            
		            }
		        },
		        layout: 'hbox',
		        items: [{xtype:'box',flex:0.1},{
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
		                allowBlank:false,
		                disabled:true,
		                itemId:'userName',
		                name: 'userName'
		            },{
		            	xtype:'textfield',
		            	fieldLabel: '姓名 ',
		            	name:'name',
		            	disabled:true,
		            	itemId:'name'
		            
		            },{
		                xtype:'textfield',
		                fieldLabel: '手机号',
		                allowBlank:false,
		                disabled:true,
		                itemId:'phone',
		                name: 'phone'
	            	},{
		            	xtype:'textfield',
		            	name:'idCard',
		            	itemId:'idCard',
		            	disabled:true,
		            	fieldLabel: '身份证号 '
		            },{
		            	xtype:'textfield',
		            	name:'birthday',
		            	itemId:'birthday',
		            	disabled:true,
		            	fieldLabel: '出生日期'
		            },{
		                xtype:'textfield',
		                fieldLabel: '性别 ',
		                disabled:true,
		                itemId:'sexs',
		                name: 'sexs'
		            },{
						xtype:'textfield',
						fieldLabel : 'QQ号码',
	            		disabled:true,
						flex:1,
						itemId:'qq',
					    name: 'qq'
					},{
						xtype:'textfield',
						fieldLabel : '邮箱',
	            		disabled:true,
						flex:1,
						itemId:'email',
					    name: 'email'
					},{
		                xtype:'textfield',
		                fieldLabel: '地址',
		                disabled:true,
		                itemId:'address',
		                name: 'address'
	            	},{
	        	        xtype : 'fieldcontainer',
	        	        fieldLabel : '证书类型',
	        	        layout: 'hbox',
	    	            items: [{
							xtype:'textfield',
	                		disabled:true,
							flex:1,
							itemId:'certificateType',
						    name: 'certificateType'
						},{xtype:'label',text:'-',style:{'line-height': '25px;'}},{
							xtype:'textfield',
	            			minValue:0,
	                		disabled:true,
							flex:1,
							itemId:'certificateNumber',
						    name: 'certificateNumber'
						}]
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
		        },{xtype:'box',flex:0.1},{
					itemId:'rightCt',
	    	   		margin:'10 10 10 10',
		        	//defaults:{anchor: '95%'},
		            items: [{
			        	xtype:'panel',
			        	layout:{
			        		type:'vbox',
			        		align:'stretch'
			        	},
			        	items:[{
			        		layout:'hbox',
			        		align:'stretch',
			        		items:[{
				            	xtype:'image',
				            	//cls:'x-fieldset',
				            	padding:'0 5 0 0',
				            	flex:1,
				            	height:250,
				            	name:'idCardPic1',
				            	title:'身份证正面',
				            	itemId:'idCardPic1',
				            	listeners: {
				            		 click: {
							            element: 'el',
							            fn: function(a,b,c){
							            	new Rich.Window({
							            		title:'图片',
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
				            },{
				            	xtype:'image',
				            	//cls:'x-fieldset',
				            	padding:'0 5 0 0',
				            	flex:1,
				            	height:250,
				            	name:'idCardPic2',
				            	title:'身份证背面',
				            	itemId:'idCardPic2',
				            	listeners: {
				            		 click: {
							            element: 'el',
							            fn: function(a,b,c){
							            	new Rich.Window({
							            		title:'图片',
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
				            },{
						    	xtype:'image',
				            	//cls:'x-fieldset',
						    	title:'手持身份证',
						    	padding:'0 5 0 0',
						    	flex:1,
				            	height:250,
								name:'idCardPic3',
								itemId:'idCardPic3',
								listeners: {
				            		 click: {
							            element: 'el',
							            fn: function(a,b,c){
							            	new Rich.Window({
							            		title:'图片',
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
				            }]
			        	},{
					    	xtype:'combobox',
					    	fieldLabel:'审核结果',
					    	store: states,
					    	padding:'5 0 0 0',
					    	labelWidth:70,
					    	anchor:'80%',
					    	//hidden:true,
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
					    	labelWidth:60,
					    	itemId:'reason',
					    	name:'reason'
						},{
			                xtype:'hiddenfield',
			                itemId:'id',
			                name: 'id'
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
    	if(va.authStatus != '待认证'){
	    	var id = va.id;
	    	var url = Rich.Url.personChePath+id+'/authStatus';
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
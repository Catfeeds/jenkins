/**
 *详情
 */
Ext.define('Rich.view.flight.ApplyDetail',{
	requires:['Ext.form.Panel',
	'Ext.form.field.HtmlEditor',
	'Rich.view.tickling.TerraceImgWindow',
	'Ext.form.field.ComboBox'
	],
    extend: 'Rich.widget.Window',
    alias:'widget.applydetail',
    width:0.7,
    height:0.9,
    minWidth:400,
    minHeight:400,
    autoScroll:true,
    setV:function(rec){
    	this.lookupI('form').getForm().setValues(rec)
    	this.lookupI('time').setValue(rec.beginTime.substring(0,7)+' 至 '+rec.endTime.substring(0,7));
    	this.lookupI('its').applyValue(Ext.decode(rec.airInfo));
    	var airInfo = Ext.decode(rec.airInfo)['0'];
    	var landingInfo = Ext.decode(rec.landingInfo);
    	//debugger
    	if(landingInfo.master){//主降落机场
    		this.lookupI('masterair').setValue(landingInfo.master.name);
    		var lng = landingInfo.master.lng != null?landingInfo.master.lng:'-';
    		var lat = landingInfo.master.lat != null?landingInfo.master.lat:'-';
    		this.lookupI('masterlng').setValue('经度:'+lng+' 纬度:'+lat);
    	};
    	if(landingInfo.slave){
    		this.lookupI('slave').show();
    		var lng = landingInfo.slave.lng != null?landingInfo.slave.lng:'-';
    		var lat = landingInfo.slave.lat != null?landingInfo.slave.lat:'-';
    		this.lookupI('slaveair').setValue(landingInfo.slave.name);
    		this.lookupI('slavelng').setValue('经度:'+lng+' 纬度:'+lat);
    	}
    	var contact = Ext.decode(rec.contactInfo);
    	this.lookupI('danwei').setValue(contact.unitContactName);
    	this.lookupI('danweiph').setValue(contact.unitContact);
    	this.lookupI('emerg').setValue(contact.emergencyContactName);
    	this.lookupI('emergph').setValue(contact.emergencyContactPhone);
    	this.lookupI('items').applyValue(Ext.decode(rec.planes));
    	this.lookupI('remark').setValue(rec.remark);
    	switch(rec.status)
			{
			case 0:
				this.lookupI('status').show();
				this.lookupI('status').setValue('审核中');
				this.down('button').setVisible(true);
				break
			case 1:
				this.lookupI('status').show();
				this.lookupI('status').setValue('通过审核');
				this.lookupI('status').setDisabled(true);
				this.down('button').setVisible(false);
				break
			case 2:
				this.lookupI('status').show();
				this.lookupI('status').setValue('未通过审核');
				this.lookupI('status').setDisabled(true);
				this.down('button').setVisible(false);
				this.lookupI('reason').setValue(rec.reason);
				this.lookupI('reason').show();
				this.lookupI('reason').setDisabled(true);
				break
			}
			
		switch(rec.status)
			{
			case 0:
				this.lookupI('status').show();
				this.lookupI('status').setValue('审核中');
				this.down('button').setVisible(true);
				break
			case 1:
				this.lookupI('status').show();
				this.lookupI('status').setValue('通过审核');
				this.lookupI('status').setDisabled(true);
				this.down('button').setVisible(false);
				break
			case 2:
				this.lookupI('status').show();
				this.lookupI('status').setValue('未通过审核');
				this.lookupI('status').setDisabled(true);
				this.down('button').setVisible(false);
				this.lookupI('reason').setValue(rec.reason);
				this.lookupI('reason').show();
				this.lookupI('reason').setDisabled(true);
				break
			}
		this.lookupI('id').setValue(rec.id);
			
    },
    initComponent:function(){
    	var me = this;
    	var states = Ext.create('Ext.data.Store', {
		    fields: ['abbr', 'name'],
		    data : [
		        {'abbr':'1', 'name':'通过'},
		        {'abbr':'0', 'name':'不通过'}
		    ]
		});
		
    	Ext.apply(me,{
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
		            	padding:'2 0 0 0',
		            	anchor: '100%'			            
		            }
		        },
		        layout: 'hbox',
		        items: [{xtype:'box',flex:0.1},{
					itemId:'priceCt',
				 	layout: {
				        type: 'vbox',
				        align: 'stretch'
				    },
			 		items:[{
		                xtype:'textfield',
		                fieldLabel: '申请单位/个人 ',
		                disabled:true,
		                itemId:'userName',
		                name: 'userName'
		            },{
		                xtype:'textfield',
		                fieldLabel: '计划类型 ',
		                disabled:true,
		                itemId:'planType',
		                name: 'planType'
		            },{
						xtype:'textfield',
						fieldLabel : '使用时间',
	            		disabled:true,
						flex:1,
						itemId:'time',
					    name: 'time'
					},{
			        	xtype:'panel',
			        	cls:'x-fieldset',
			        	layout:{
					        type: 'vbox',
					        align: 'stretch'
					    },
			        	items:[{
						 	margin:'0 0 5 0',
							padding:'5 5 5 5',
						 	layout: {
						        type: 'vbox',
						        align: 'stretch'
						    },
						 	items:[{    
						 		itemId:'its',
						       	flex:1,
						       	applyValue:function(v,notClean){
									if(!notClean){
							 			this.removeAll();
							 		}
							 		if(Ext.isArray(v) && v.length > 0){
									 	var its = [],it;
									 	for(var i = 0;i < v.length;i++){
									 		its.push(this.createItemByValue(v[i]));
									 	}
									 	this.add(its);
							 		}
								},
								createItemByValue:function(v){
									var a = v.circleScope;
									var ce= v.centers;
									var value = (v?v.provinceName:'')+(v?v.cityName:'')+(v!=null?v.cityName:'')+(v?v.location:'')
									var hidd = a == null?true:false;
									var result = '';
									if(ce){
										for(var c = 0;c<ce.length;c++){
											result += '(经度:'+ce[c].lng+' ,纬度'+ce[c].lat+')';
										}
										
									}
									return {
				        	            xtype : 'fieldcontainer',
				        	            margin:'5 1 0 0',
				        	            flex:1,
				        	            layout: {
									        type: 'hbox'										       
									    },
			        	            	items:[{
							                xtype:'textfield',
							                width:150,
					                		disabled:true,
					                		value:value,
							                fieldLabel: '空域',
							                labelWidth:40
							            },{
							            	margin:'0 0 0 20',
							                xtype:'textfield',
							                width:170,
							                labelWidth:40,
							                disabled:true,
					                		value:((v?v.high:'')+'米(含以下)'),
							                fieldLabel: '真高'
							            },{
							            	margin:'0 0 0 20',
							                xtype:'textfield',
							                width:100,
							                labelWidth:40,
							                disabled:true,
					                		value:((a?a.radius:'-')+'米'),
					                		hidden:hidd,
					                		itemId:'radius',
							                fieldLabel: '半径'
							            },{
							            	margin:'0 0 0 20',
							                xtype:'textfield',
							                flex:0.1,
							                labelWidth:40,
							                disabled:true,
					                		value:result,
							                fieldLabel: '坐标'
							            }]
									}
								},
					        	layout: {
							        type: 'vbox',
							        align: 'stretch'
							    }
							}]
			            }]
		            },{
        	            xtype : 'fieldcontainer',
        	            margin:'5 1 0 0',
        	            flex:1,
        	            layout: {
					        type: 'hbox'										       
					    },
    	            	items:[{
			                xtype:'textfield',
			                flex:1,
	                		disabled:true,
	                		itemId:'masterair',
			                fieldLabel: '起降场'
			                //labelWidth:60
			            },{
			            	margin:'0 0 0 20',
			                xtype:'textfield',
			                flex:1,
			                labelWidth:50,
			                disabled:true,
	                		itemId:'masterlng',
			                fieldLabel: '坐标'
			            }]
					},{
        	            xtype : 'fieldcontainer',
        	            margin:'5 0 0 0',
        	            itemId:'slave',
        	            hidden:true,
        	            flex:1,
        	            layout: {
					        type: 'hbox'										       
					    },
    	            	items:[{
			                xtype:'textfield',
			                flex:1,
	                		disabled:true,
	                		itemId:'slaveair',
			                fieldLabel: '起降场'
			                //labelWidth:60
			            },{
			            	margin:'5 0 0 20',
			                xtype:'textfield',
			                flex:1,
			                labelWidth:50,
			                disabled:true,
	                		itemId:'slavelng',
			                fieldLabel: '坐标'
			            }]
					},{
						margin:'5 0 0 0',
		                xtype:'textfield',
		                fieldLabel: '单位联系人',
		                disabled:true,
		                itemId:'danwei',
		                name: 'danwei'
		            },{
		                xtype:'textfield',
		                fieldLabel: '单位联系方式',
		                disabled:true,
		                itemId:'danweiph',
		                name: 'danweiph'
		            },{
		                xtype:'textfield',
		                fieldLabel: '紧急联系人',
		                disabled:true,
		                itemId:'emerg',
		                name: 'emerg'
		        	},{
		                xtype:'textfield',
		                fieldLabel: '紧急联系方式',
		                itemId:'emergph',
		                disabled:true,
		                name: 'emergph'
		        	},{
			        	xtype:'panel',
			        	layout:{
					        type: 'vbox',
					        align: 'stretch'
					    },
			        	items:[{
						 	margin:'0 0 5 0',
							padding:'5 5 5 5',
							cls:'x-fieldset',
						 	layout: {
						        type: 'vbox',
						        align: 'stretch'
						    },
						 	items:[{    
						 		itemId:'items',
						       	flex:1,
						       	applyValue:function(v,notClean){
									if(!notClean){
							 			this.removeAll();
							 		}
							 		if(Ext.isArray(v) && v.length > 0){
									 	var its = [],it;
									 	for(var i = 0;i < v.length;i++){
									 		its.push(this.createItemByValue(v[i]));
									 	}
									 	this.add(its);
							 		}
								},
								createItemByValue:function(v){
									return {
				        	            xtype : 'fieldcontainer',
				        	            margin:'5 0 0 0',
				        	            layout: {
									        type: 'hbox'										       
									    },
			        	            	items:[{
							                xtype:'textfield',
							                flex:1,
							                minValue:0,
					                		disabled:true,
					                		value:(v?v.name:null),
							                fieldLabel: '机型'
							            },{width:20},{
							                xtype:'textfield',
							                flex:1,
							                disabled:true,
							                minValue:0,
					                		value:((v?v.number:null)+'架'),
							                fieldLabel: '数量'
							            }]
									}
								},
					        	layout: {
							        type: 'vbox',
							        align: 'stretch'
							    }
							}]
			            }]
		            },{
		                xtype:'textfield',
		                fieldLabel: '说明事项',
		                disabled:true,
		                itemId:'remark',
		                name: 'remark'
		        	},{
				    	xtype:'combobox',
				        fieldLabel: '认证结果',
				        labelWidth:60,
				        itemId:'status',
				        name: 'status',
				        hidden:true,
				        editable:false,
				        allowBlank:false,
				        store: states,
					    queryMode: 'local',
					    displayField: 'name',
					    valueField: 'abbr',
					    listeners:{
			               'select':function(me0, record, index, eOpts){
			               		var type = record['0'].data.abbr;
			               		if(type != 1){
			               			this.up('form').lookupI('reason').show();
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
				    	name:'id'
		            }]
		        },{xtype:'box',flex:0.1}]
    		}],
    		buttons:['->',{
    			text:'提交修改',
    			hidden:true,
    			itemId:'sae',
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
    	if(!fm.isValid())
		{
			Rich.Msg.error('错误','有不合法的输入.');
			return null;
		}
    	var vas = fm.getValues();
    	if(vas.status != '审核中'){
	    	var ul = Rich.Url.airplaceCheckPath+vas.id+'/status';
	    	var va = Ext.encode(vas);
	    	Rich.JsonAjax.request({
				method:'put',
				getMethod:function(){return "put"},
				url:ul,	
				params:va,
				headers:{'Content-Type':'application/json;charset=UTF-8'},
				callback:this._submitBack,
				scope:this
			});
    	}else{
    		Rich.Msg.alert('消息','未做审核操作');
    	}
    },
    _submitBack:function(o,f,r){
    	this.el.unmask();
    	if(f){
    		this.close(true);
    		Rich.Msg.alert('消息','审核完成');
    		this.returnValue(f);
    	}
    }
});
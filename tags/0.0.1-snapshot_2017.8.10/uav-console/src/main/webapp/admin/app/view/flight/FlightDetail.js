/**
 *详情
 */
Ext.define('Rich.view.flight.FlightDetail',{
	requires:['Ext.form.Panel',
	'Ext.form.field.HtmlEditor',
	'Rich.view.tickling.TerraceImgWindow',
	'Ext.form.field.ComboBox'
	],
    extend: 'Rich.widget.Window',
    alias:'widget.flightdetail',
    width:0.7,
    height:0.9,
    minWidth:400,
    minHeight:400,
    autoScroll:true,
   
    setV:function(rec){
    	this.lookupI('name').setValue(rec.userName);
    	this.lookupI('type').setValue(rec.type == '0'?'管制空域':(rec.type == '1'?'一类报告空域':'二类报告空域'));
    	this.lookupI('planType').setValue(rec.planType);
    	this.lookupI('time').setValue(rec.beginTime+' - '+rec.endTime.substring(rec.endTime.length-6));
    	this.lookupI('flyarea').setValue(Ext.decode(rec.landingInfo)['0'].location)
    	this.lookupI('its').applyValue(Ext.decode(rec.airInfo));
    	var contact = Ext.decode(rec.contactInfo);
    	var emergph =  this.lookupI('emergph');
    	var emerg = this.lookupI('emerg');
    	switch(rec.type){//现场的数据
    		case 0:
    			var danwei = this.lookupI('danwei');
    			danwei.setValue(contact.fieldContactName);
    			danwei.show();
	    		var danweiph = this.lookupI('danweiph');
	    		danweiph.setValue(contact.fieldContactPhone);
	    		danweiph.show();
	    		var person = this.lookupI('person');
	    		person.setValue(contact.unitContactName);
	    		person.show();
	    		var phone = this.lookupI('phone');
	    		phone.setValue(contact.unitContact);
	    		phone.show();
	    		break;
    		case 1:
	    		var num = contact.traineeCount;//学员数
	    		var who = this.lookupI('who')
	    		who.setValue(contact.trainer);
	    		who.show();
	    		var studen = this.lookupI('studen');
	    		studen.setValue(contact.traineeCount+' 人');
	    		studen.show();
	    		var person = this.lookupI('person');
	    		person.setValue(contact.fieldContactName);
	    		person.show();
	    		var phone = this.lookupI('phone');
	    		phone.setValue(contact.fieldContactPhone);
	    		phone.show();
	    		var weather = this.lookupI('weather');
	    		weather.setValue(rec.weatherCondition?rec.weatherCondition:'-');
	    		weather.show();
	    		emerg.setValue(contact.emergencyContactName);
	    		emerg.show();
	    		emergph.setValue(contact.emergencyContactPhone);
	    		emergph.show();
	    		var remark = this.lookupI('remark');
	    		remark.setValue(rec.remark?rec.remark:'-');
	    		remark.show();
	    		break;
    		case 2:
    			var who = this.lookupI('2who');
    			who.setValue(contact.controller);
    			who.show();
    			var ipp = this.lookupI('ipp');
    			ipp.setValue(contact.controllerContact);
    			ipp.show();
    			var idc = this.lookupI('idc');
    			idc.setValue(contact.idCardType+' '+contact.idCardNo)
    			idc.show();
    			emerg.setValue(contact.emergencyContactName);
	    		emerg.show();
				emergph.setValue(contact.emergencyContactPhone);
				emergph.show();
				break;
    	}
    	this.lookupI('items').applyValue(Ext.decode(rec.planes));
    	var status = this.lookupI('status');
    	var plant = this.lookupI('plant');
		switch(rec.status){
			case 0:
				status.show();
				status.setValue('审核中');
				this.down('button').setVisible(true);
				plant.setValue('审核中')
				break;
			case 1:
				status.show();
				status.setValue('通过审核');
				status.setDisabled(true);
				this.down('button').setVisible(false);
				plant.setValue('通过审核');
				break;
			case 2:
				status.show();
				status.setValue('未通过审核');
				status.setDisabled(true);
				this.down('button').setVisible(false);
				var reason = this.lookupI('reason')
				reason.setValue(rec.reason);
				reason.show();
				reason.setDisabled(true);
				plant.setValue('未通过审核');
				break;
			case 3:
				plant.setValue('执行中');
				break;
			case 4:
				plant.setValue('已完成');
				break;
			case 5:
				plant.setValue('计划超时');
				break;
			case 6:
				plant.setValue('撤销申请');
				break;
			case 7:
				plant.setValue('已关闭');
				break;
		};
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
		                fieldLabel: '空域类别 ',
		                disabled:true,
		                itemId:'type',
		                name: 'type'
		            },{
		                xtype:'textfield',
		                fieldLabel: '姓名/单位名称 ',
		                disabled:true,
		                itemId:'name',
		                name: 'name'
		            },{
		                xtype:'textfield',
		                fieldLabel: '计划类型 ',
		                disabled:true,
		                itemId:'planType',
		                name: 'planType'
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
							                labelWidth:40,
					                		value:((v?v.number:null)+' 架'),
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
						fieldLabel : '计划时间',
	            		//disabled:true,
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
							padding:'5 0 5 0',
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
									var name = (v?v.provinceName:'')+(v?v.cityName:'')+(v.areaName != null?v.areaName:'')+(v?v.location:'');
									var lng = '';
									if(v.scopeType == 1){//原形
										lng = '经度:'+v.circleScope.lng+', 纬度'+v.circleScope.lat;
									}else{//多边行
										for(var i = 0;i<v.centers.length;i++){
											var a = v.centers[i];
											lng += '(经度 :'+a.lng+' ,纬度:'+a.lat+')';
										}
									};
									return {
				        	            xtype : 'fieldcontainer',
				        	            margin:'5 0 0 0',
				        	            layout: {
									        type: 'form'										       
									    },
			        	            	items:[{
							                xtype:'textfield',
					                		disabled:true,
					                		flex:0.8,
					                		value:name,
							                fieldLabel: '计划空域'
							            },{width:20},{
							                xtype:'textfield',
							                disabled:true,
							                flex:0.4,
							                labelWidth:40,
					                		value:((v?v.high:'')+('米(含)以下')),
							                fieldLabel: '真高'
							            },{
							                xtype:'textfield',
							                disabled:true,
							                flex:1,
							                labelWidth:60,
					                		value:lng,
							                fieldLabel: '经纬度'
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
		                fieldLabel: '起降场',
		                disabled:true,
		                itemId:'flyarea',
		                name: 'flyarea'
	            	},{
		                xtype:'textfield',
		                fieldLabel: '起降场经纬度',
		                hidden:true,
		                disabled:true,
		                itemId:'lng',
		                name: 'lng'
	            	},{
						xtype:'textfield',
		                fieldLabel: '单位联系人',
		                hidden:true,
		                disabled:true,
		                itemId:'danwei',
		                flex:1,
		                name: 'danwei'
					},{
						xtype:'textfield',
		                fieldLabel: '单位联系方式',
		                hidden:true,
		                disabled:true,
		                flex:1,
		                itemId:'danweiph',
		                name: 'danweiph'
					},{
			            xtype: 'fieldcontainer',
			            layout: 'hbox',
			            items: [{
			                xtype:'textfield',
			                fieldLabel: '现场联系人',
			                hidden:true,
			                disabled:true,
			                flex:1,
			                itemId:'person',
			                name: 'person'
			        	},{
			        		margin:'0 0 0 10',
			                xtype:'textfield',
			                fieldLabel: '手机号',
			                hidden:true,
			                disabled:true,
			                labelWidth:50,
			                flex:1,
			                itemId:'phone',
			                name: 'phone'
			        	}]
			        },{
		                xtype:'textfield',
		                fieldLabel: '无人机操控员',
		                hidden:true,
		                disabled:true,
		                itemId:'2who',
		                name: '2who'
		            },{
		                xtype:'textfield',
		                fieldLabel: '教练员/飞行员姓名',
		                hidden:true,
		                disabled:true,
		                itemId:'who',
		                name: 'who'
		            },{
		                xtype:'textfield',
		                fieldLabel: '学员数量',
		                hidden:true,
		                disabled:true,
		                itemId:'studen',
		                name: 'studen'
		        	},{
		                xtype:'textfield',
		                fieldLabel: '证件类型及号码',
		                hidden:true,
		                disabled:true,
		                itemId:'idc',
		                name: 'idc'
		        	},{
		                xtype:'textfield',
		                fieldLabel: '手机号',
		                hidden:true,
		                disabled:true,
		                itemId:'ipp',
		                name: 'ipp'
		        	},{
			            xtype: 'fieldcontainer',
			            layout: 'hbox',
			            items: [{
			                xtype:'textfield',
			                fieldLabel: '紧急联系人',
			                hidden:true,
			                flex:1,
			                disabled:true,
			                itemId:'emerg',
			                name: 'emerg'
			        	},{
			        		margin:'0 0 0 10',
			                xtype:'textfield',
			                fieldLabel: '紧急联系方式',
			                labelWidth:90,
			                disabled:true,
			                hidden:true,
			                flex:1,
			                itemId:'emergph',
			                name: 'emergph'
			        	}]
			        },{
		                xtype:'textfield',
		                fieldLabel: '天气',
		                hidden:true,
		                disabled:true,
		                itemId:'weather',
		                name: 'weather'
		        	},{
		                xtype:'textfield',
		                fieldLabel: '备注',
		                hidden:true,
		                disabled:true,
		                itemId:'remark',
		                name: 'remark'
		        	},{
		                xtype:'textfield',
		                fieldLabel: '计划状态',
		                disabled:true,
		                itemId:'plant',
		                name: 'plant'
		        	},{
				    	xtype:'combobox',
				        fieldLabel: '认证结果',
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
	    	var ul = Rich.Url.flyPlansCheckPath+vas.id+'/status';
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
/**
 * 图片轮播form
 */
Ext.define('Rich.view.airplace.AirplaceForm',{
	requires:[
	'Ext.form.field.ComboBox',
	'Ext.form.FieldContainer',
	'Ext.form.CheckboxGroup',
	'Ext.form.field.Hidden',
	'Ext.form.field.Number',
	'Rich.component.FileUpload',
	'Ext.form.field.Text',
	'Rich.component.DicComboBox'
	],
    extend: 'Rich.widget.CrudForm',
    alias:'widget.airplaceform',
    
    minWidth:300,
    minHeight:200,
    tid:null,
    
    loadById:function(id){
    	this.userId = id;
		this.doRetrieve({id:id});
	},
    initComponent:function(){
    	var me = this;
    	var states = Ext.create('Ext.data.Store', {
		    fields: ['abbr', 'name'],
		    data : [
		        {'abbr':"1", 'name':"圆形"},
		        {'abbr':"2", 'name':"多边形"}
		    ]
		});
		
		var stat = Ext.create('Ext.data.Store', {
		    fields: ['abbr', 'name'],
		    data : [
		        {'abbr':"0", 'name':"管制空域"},
		        {'abbr':"1", 'name':"一类报告空域"},
		        {'abbr':"2", 'name':"二类报告空域"}
		    ]
		});
		
		var store = Ext.create('Ext.data.Store', {
		    fields: ['abbr', 'name'],
		    data : [
		        {'abbr':"广州分区", 'name':"广州分区"},
		        {'abbr':"岑村机场管制室", 'name':"岑村机场管制室"},
		        {'abbr':"佛山机场管制室", 'name':"佛山机场管制室"},
		        {'abbr':"惠州机场管制室", 'name':"惠州机场管制室"},
		        {'abbr':"长沙机场管制室", 'name':"长沙机场管制室"},
		        {'abbr':"邵东机场管制室", 'name':"邵东机场管制室"},
		        {'abbr':"耒阳机场管制室", 'name':"耒阳机场管制室"},
		        {'abbr':"零陵机场管制室", 'name':"零陵机场管制室"}
		    ]
		});
		
    	Ext.apply(me,{
	    	cls:'r-highlight-disabled',
	    	bodyStyle:'overflow-x:hidden;overflow-y:auto;',
	        fieldDefaults: {
	            labelAlign: 'left',		           
	            msgTarget: 'side'
	        },
	        layout: {
		        type: 'hbox',
		        align: 'stretchmax'
		    },
		    defaults: {
	            border: false,
	            xtype: 'panel',
	            flex: 1,
	            layout: 'anchor'
	        },
	        items: [{
	        	itemId:'leftCt',
	        	margin:'10 10 10 10',
	        	defaults:{anchor:'95%'},
	            items:[{
	            	xtype:'textfield',
	            	fieldLabel: '编号',
	                allowBlank:false,
	                itemId:'id',
	            	name:'id'
	            },{
	                xtype:'textfield',
	                fieldLabel: '空域名称',
	                allowBlank:false,
	                itemId:'name',
	                name: 'name'
	            },{
			        fieldLabel: '空域范围类型',
			        xtype:'combobox',
			        name: 'type',
			        itemId:'type',
			        store: stat,
				    queryMode: 'local',
				   	allowBlank:false,
				    editable:false,
				    displayField: 'name',
				    valueField: 'abbr'
	            },{
	                xtype:'textfield',
	                fieldLabel: '真高',
	                allowBlank:false,
	                itemId:'high',
	                name: 'high'
		        },{
	                fieldLabel: '所属空域辖区',
	                xtype:'combobox',
	                allowBlank:false,
	                store: store,
				    queryMode: 'local',
				    name:'territorial',
				    itemId:'territorial',
				    displayField: 'name',
				    valueField: 'abbr'
		        },{
		            xtype: 'fieldcontainer',
		            layout: 'hbox',
		            items: [{
						xtype: 'textfield',
						flex:1,
						fieldLabel : '所属省份',
					    name: 'provinceName'
					},{
						padding:'0 0 0 10',
						xtype: 'textfield',
						allowBlank:false,
						flex:1,
						fieldLabel : '省份ID',
						emptyText:'省份拼音首字母大写',
						labelWidth:60,
					    name: 'provinceCode'
					}]
		        },{
		            xtype: 'fieldcontainer',
		            layout: 'hbox',
		            items: [{
						xtype: 'textfield',
						flex:1,
						allowBlank:false,
						fieldLabel : '所属城市',
					    name: 'cityName'
					},{
						padding:'0 0 0 10',
						xtype: 'textfield',
						flex:1,
						fieldLabel : '城市ID',
						emptyText:'城市拼音首字母大写',
						labelWidth:60,
						allowBlank:false,
					    name: 'cityCode'
					}]
		        },{
		            xtype: 'fieldcontainer',
		            layout: 'hbox',
		            items: [{
						xtype: 'textfield',
						flex:1,
						allowBlank:false,
						fieldLabel : '所属区县',
					    name: 'areaName'
					},{
						padding:'0 0 0 10',
						xtype: 'textfield',
						flex:1,
						allowBlank:false,
						labelWidth:60,
						emptyText:'区县拼音首字母大写',
						fieldLabel : '区县ID',
					    name: 'areaCode'
					}]
		        },{
	                xtype:'textfield',
	                fieldLabel: '位置描述',
	                allowBlank:false,
	                itemId:'location',
	                name: 'location'
		        },{
			        fieldLabel: '空域范围类型',
			        xtype:'combobox',
			        name: 'scopeType',
			        itemId:'scopeType',
			        store: states,
				    queryMode: 'local',
				    editable:false,
				    allowBlank:true,
				    value:'圆形',
				    displayField: 'name',
				    valueField: 'abbr',
			    	listeners:{
		               'select':function(me0, record, index, eOpts){
		               		var type = record['0'].data.abbr;
		               		var fx = this.up('form').lookupI('yuanxing');
		               		var db = this.up('form').lookupI('duobianxing');
		               		if(type == 1){
		               			fx.show();
		               			db.hide();
		               			db.setDisabled(true);
		               			fx.setDisabled(false);
		               		}else{
		               			db.show();
		               			fx.hide();
		               			db.setDisabled(false);
		               			fx.setDisabled(true);
		               		}
						}
            		}
			    },{
		            xtype: 'fieldcontainer',
		            itemId:'yuanxing',
		            layout: 'hbox',
		            items: [{
						xtype: 'textfield',
						flex:1,
						fieldLabel : '经度',
						itemId:'lng',
					    name: 'lng'
					},{
						padding:'0 0 0 10',
						xtype: 'textfield',
						flex:1,
						labelWidth:40,
						fieldLabel : '纬度',
						itemId:'lat',
					    name: 'lat'
					},{
						xtype: 'textfield',
						flex:0.9,
						labelWidth:40,
						fieldLabel : '半径',
						itemId:'radius',
					    name: 'radius'
					}]
		        },{
					hidden:true,
					itemId:'duobianxing',
					layout: {
				        type: 'vbox',
				        align: 'stretch'
				    },
			        flex:1,
			        margin:'0 0 5 0',
					padding:'0 5 5 5',
					cls:'x-fieldset',
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
	        	            layout: {
						        type: 'hbox',
						        align: 'stretch'
						    },
	        	            items:[{
    							xtype:'textfield',
                				allowBlank:false,
    							flex:1,
    							//labelWidth:40,
    							fieldLabel: '经度',
    							value:(v?v.lng:''),
    							name:'lng'
        	            	},{
    							xtype:'textfield',
    							margin:'0 0 0 10',
    							minValue:0,
                				allowBlank:false,
    							flex:1,
    							labelWidth:40,
    							fieldLabel: '纬度',
    							value:(v?v.lat:''),
    							name:'lat'
        	            	},{
	    						xtype:'button',
	    						iconCls:'r-button-close',
	    						margin:'0 0 0 5',
	    						handler:function(btn){
	    							var pi = btn.up('fieldcontainer');
	    							pi.getOwnerCt().remove(pi);
	    						}
	    					}]
						};
					},
		        	dockedItems:{
		        		docked:'top',
		        		ui:'light',
		        		xtype:'toolbar',
		        		items:[{
		        			text:'添加经纬度',
		        			handler:function(btn){
		        				var ct = btn.getOwnerCt('duobianxing');
		        				ct.add(ct.createItemByValue());
		        			}
		        		}]
		        	}
		        },{
			    	xtype:'fileupload',
					allowBlank:false,
					fieldLabel: '空域示意图',
					maxWidth:250,
					height:250,
					itemId:'sketchMap',
					name:'sketchMap',
					labelHidden:true,
					buttonText: '上传图片'
	            }]
			}]
    	});
    	me.callParent(arguments);
    },
    getFormValues:function(){
    	if(!this.isValid())
		{
			Rich.Msg.error('错误','有不合法的输入.');
			return null;
		}
    	var va = this.getValues();
    	if(Ext.isArray(va.lng) && va.lng.length > 0 ){
    		var scopeInfo = [];
    		for(var i = 0; i<va.lng.length; i++){
    			if(va.lng[i]){
    				scopeInfo.push({lng:va.lng[i]?va.lng[i]:null,lat:va.lat[i]?va.lat[i]:null});
    			}else{
    				continue;
    			}
    		};
    	}else{
    		var scopeInfo = {};
    		scopeInfo.lng = va.lng;
    		scopeInfo.lat = va.lat;
    		scopeInfo.radius = va.radius;
    	}
    	va.sketchMap = this.lookupI('sketchMap').src;
    	va.scopeInfo = Ext.encode(scopeInfo)
    	if(va.scopeType == '圆形'){
    		va.scopeType = 1;
    	}else{
    		va.scopeType = 2;
    	};
    	switch(va.type){
    		case '管制空域':
    			va.type = 0;
    			break;
    		case '一类报告空域':
    			va.type = 1;
    			break;
    		case '二类报号空域':
    			va.type = 2;
    			break;
    	};
    	return Ext.encode(va);
    },
    cUrl:Rich.Url.airplaceAddPath,
    rUrl:Rich.Url.airplaceDetailPath,
    uUrl:Rich.Url.airplaceUpdatePath,
    toStatus:function(st){
    	this.callParent(arguments);
    	this.lookupI('leftCt').setDisabled(st=='r');
    },
    applyValue:function(va){
		this.getForm().setValues(va);
		this.lookupI('scopeType').setValue(va.scopeType==1?'圆形':'多边形');
		this.lookupI('type').setValue(va.type == 0?'管制空域':(va.type == 1?'一类报告空域':'二类报号空域'));
		var sco= Ext.decode(va.scopeInfo);
		if(va.scopeType == 1){
			this.lookupI('lng').setValue(sco.lng);
			this.lookupI('lat').setValue(sco.lat);
			this.lookupI('radius').setValue(sco.radius);
			this.lookupI('yuanxing').show();
		}else{
			this.lookupI('duobianxing').show();
			this.lookupI('yuanxing').hide();
			this.lookupI('duobianxing').applyValue(sco);
		}
		this.lookupI('sketchMap').setValue(va.src,va.sketchMap);
	},
    callback:function(o,f,r,s){
    	if(f){
	    	if(s == 'u'){
	    		Rich.Msg.alert('消息','修改成功！');//无需关闭的提示
	    		this.loadById(this.tid);
	    	}else if(s=='r'){
	    		if(r){
	    			this.applyValue(r.responseJson.data);
	    			this.toStatus(s);
	    		}else{
	    			this.toStatus('c');
	    		}
	    	}else if(s=='c'){
	    	}
    	}
    }
});
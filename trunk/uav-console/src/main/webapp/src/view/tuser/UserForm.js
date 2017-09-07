Ext.define('Rich.view.tuser.UserForm', {
	requires:[
		'Rich.widget.Message',
		'Rich.component.DicComboBox',
		'Ext.button.Split',
		'Ext.grid.Panel',
		'Ext.form.Panel',
		'Ext.form.field.Date',
		'Ext.form.FieldContainer',
        'Ext.form.field.Hidden',
        'Ext.form.field.Trigger',
        'Ext.form.field.ComboBox',
        'Ext.form.CheckboxGroup',
        'Ext.grid.column.Action',
        'Rich.component.FileUpload',
        'Ext.form.FieldSet',
        'Ext.form.field.HtmlEditor',
        'Ext.layout.component.FieldSet',
        'Ext.form.field.Checkbox',
        'Ext.form.field.File'
    ],
   
    uses:['Rich.view.ship.ShipSearchWindow','Rich.view.ship.ShipInfoWindow'],
    extend: 'Rich.widget.CrudForm',
    alias:'widget.userform',
   
    urls:null,
    loadById:function(uId){
		this.doRetrieve({id:uId});
		this.urls = url;
	},
	initComponent:function(){
		var me = this;
		Ext.apply(me,{
			cls:'r-highlight-disabled',
        	bodyStyle:'overflow-x:hidden;overflow-y:auto;',
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
	        	margin:'10 10 10 20',
	        	defaults:{anchor: '100%'},		        	
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
		                xtype:'hiddenfield',
		                itemId:'id',
		                name: 'id'
		            },{
		                xtype:'textfield',
		                fieldLabel: '用户名 ',
		                allowBlank:false,
		                itemId:'name',
		                name: 'name'
		            },{
		                xtype:'textfield',
		                fieldLabel: '性别 ',
		                allowBlank:false,
		                itemId:'sex',
		                name: 'sex'
		            },{
						xtype:'textfield',
						fieldLabel : 'QQ号码',
	            		allowBlank:false,
						flex:1,
						itemId:'qq',
					    name: 'qq'
					},{
						xtype:'textfield',
						fieldLabel : '邮箱',
	            		allowBlank:false,
						flex:1,
						itemId:'email',
					    name: 'email'
					},{
		                xtype:'textfield',
		                fieldLabel: '手机号',
		                allowBlank:false,
		                itemId:'phone',
		                name: 'phone'
	            	},{
		                xtype:'textfield',
		                fieldLabel: '地址',
		                allowBlank:false,
		                itemId:'address',
		                name: 'address'
	            	},{
	        	        xtype : 'fieldcontainer',
	        	        fieldLabel : '证书类型',
	        	        layout: 'hbox',
	    	            items: [{
							xtype:'textfield',
	                		allowBlank:false,
							flex:1,
							itemId:'certificateType',
						    name: 'certificateType'
						},{xtype:'label',text:'-',style:{'line-height': '25px;'}},{
							xtype:'textfield',
	            			minValue:0,
	                		allowBlank:false,
							flex:1,
							itemId:'certificateNumber',
						    name: 'certificateNumber'
						}]
	        	     },{
		                xtype:'textfield',
		                fieldLabel: '认证状态',
		                allowBlank:false,
		                itemId:'auth_status',
		                name: 'auth_status'
	            	},{
		                xtype:'textfield',
		                fieldLabel: '用户状态',
		                allowBlank:false,
		                itemId:'statu',
		                name: 'statu'
	            	},{
		                xtype:'textfield',
		                fieldLabel: '紧急联系人',
		                allowBlank:false,
		                itemId:'emergencyContactName',
		                name: 'emergencyContactName'
	            	},{
		                xtype:'textfield',
		                fieldLabel: '紧急联系方式',
		                allowBlank:false,
		                itemId:'emergencyContactPhone',
		                name: 'emergencyContactPhone'
	            	},{
		                xtype:'textfield',
		                fieldLabel: '最后登录时间',
		                allowBlank:false,
		                itemId:'lastLoginTime',
		                name: 'lastLoginTime'
	            	},{
		                xtype:'textfield',
		                fieldLabel: '最后登录IP',
		                allowBlank:false,
		                itemId:'lastLoginIp',
		                name: 'lastLoginIp'
			       }]
	            }]
    	   },{
				itemId:'rightCt',
    	   		margin:'10 30 10 10',
	        	defaults:{anchor: '100%'},
	            items: [{
		        	xtype:'panel',
		        	layout:{
		        		type:'hbox',
		        		align:'stretch'
		        	},
		        	items:[{
				    	xtype:'fileupload',
						flex:0.4,
						fieldLabel:'身份证正面',
						width:200,
						margin:'0 0 5 0',
						name:'idCardPic1',
						itemId:'idCardPic1',
						labelHidden:true,
						buttonText: '上传图片'
    	            },{width:5},{
				    	xtype:'fileupload',
				    	fieldLabel:'身份证背面',
						flex:0.4,
						width:200,
						margin:'0 0 5 0',
						name:'idCardPic2',
						itemId:'idCardPic2',
						labelHidden:true,
						buttonText: '上传图片'
		            }]
	            }]
	        }]
		});
		me.callParent(arguments);
	},
	valueFields:['id','name','sex','qq','email','phone','address','certificateType','certificateNumber',
	'auth_status','statu','emergencyContactName','emergencyContactPhone','lastLoginTime','lastLoginIp'],
	applyValue:function(va){
		var o,t,c,vf = this.valueFields;
		this.lookupI('idCardPic1').setValue(va.src,va.idCardPic1);
		this.lookupI('idCardPic2').setValue(va.src,va.idCardPic2);
		for(o in vf){
			t = vf[o];
			c = this.lookupI(t);
			if(c.applyValue){
				c.applyValue(va[t]);
			}else{
				c.setValue(va[t]);
			}
		}
	},
    getFormValues:function(s){
    	if(!this.isValid())
		{
			Rich.Msg.error('错误','有不合法的输入.');
			return null;
		}
    	var vas = this.getValues();
    	return vas;
    },
   
    cUrl:Rich.Url.routeAddPath,
    rUrl:Rich.Url.grDeaPath,
    uUrl:Rich.Url.routeUpdatePath,
    toStatus:function(st){
    	this.callParent(arguments);
    	this.lookupI('leftCt').setDisabled(st=='r');
		this.lookupI('rightCt').setDisabled(st=='r');
    },
    callback:function(o,f,r,s){
    	if(s == 'u'){
    		this.loadById(o.params.id);
    	}else if(s=='r'){
    		this.applyValue(r.responseJson.data);
			this.toStatus(s);
    	}else if(s=='c'){
    	}
    }
});

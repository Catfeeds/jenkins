Ext.define('Rich.view.tuser.CompanyForm', {
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
    alias:'widget.companyform',
   
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
		                itemId:'username',
		                name: 'username'
		            },{
		                xtype:'textfield',
		                fieldLabel: '性别',
		                allowBlank:false,
		                itemId:'sex',
		                name: 'sex'
		            },{
						xtype:'textfield',
						fieldLabel : '营业执照码',
	            		allowBlank:false,
						flex:1,
						itemId:'licenseNumber',
					    name: 'licenseNumber'
					},{
						xtype:'textfield',
						fieldLabel : '邮箱',
	            		allowBlank:false,
						flex:1,
						itemId:'email',
					    name: 'email'
					},{
		                xtype:'textfield',
		                fieldLabel: '公司法人',
		                allowBlank:false,
		                itemId:'companyLegal',
		                name: 'companyLegal'
	            	},{
		                xtype:'textfield',
		                fieldLabel: '联系人',
		                allowBlank:false,
		                itemId:'cntactName',
		                name: 'cntactName'
	            	},{
		                xtype:'textfield',
		                fieldLabel: '联系电话',
		                allowBlank:false,
		                itemId:'conactPhone',
		                name: 'conactPhone'
	            	},{
		                xtype:'textfield',
		                fieldLabel: '公司地址',
		                allowBlank:false,
		                itemId:'companyAddress',
		                name: 'companyAddress'
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
		        		type:'vbox',
		        		align:'stretch'
		        	},
		        	items:[{
				    	xtype:'fileupload',
						flex:0.4,
						fieldLabel:'营业执照照片',
						width:200,
						margin:'0 0 5 0',
						name:'licensePic',
						itemId:'licensePic',
						labelHidden:true,
						buttonText: '上传图片'
    	            }]
	            }]
	        }]
		});
		me.callParent(arguments);
	},
	valueFields:['id','username','sex','licenseNumber','email','companyLegal','cntactName','conactPhone','companyAddress',
	'auth_status','statu','emergencyContactName','emergencyContactPhone','lastLoginTime','lastLoginIp'],
	applyValue:function(va){
		var o,t,c,vf = this.valueFields;
		this.lookupI('licensePic').setValue(va.src,va.licensePic);
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
    rUrl:Rich.Url.compaPath,
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

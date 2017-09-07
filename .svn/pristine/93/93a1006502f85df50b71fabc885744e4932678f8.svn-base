/**
 * 图片轮播form
 */
Ext.define('Rich.view.content.PictureForm',{
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
    alias:'widget.pictureform',
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
		        {'abbr':"0", 'name':"显示"},
		        {'abbr':"1", 'name':"隐藏"}
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
	        	margin:'10 10 10 20',
	        	defaults:{anchor:'95%'},
	            items:[{
	            	xtype:'hiddenfield',
	            	name:'id'
	            },{
	                xtype:'textfield',
	                fieldLabel: '标题描述',
	                allowBlank:true,
	                itemId:'titleDesc',
	                name: 'titleDesc'
	            },{
	                xtype:'textfield',
	                fieldLabel: '排序编号',
	                allowBlank:true,
	                itemId:'sortOrder',
	                name: 'sortOrder'
	            },{
	                xtype:'textfield',
	                fieldLabel: '超链接',
	                allowBlank:true,
	                itemId:'imgLink',
	                name: 'imgLink'
	            },{
			    	xtype:'combobox',
			    	fieldLabel:'是否隐藏',
			    	store: states,
				    queryMode: 'local',
				    displayField: 'name',
				    valueField: 'abbr',
			    	name:'isHidden'
			    },{
			    	xtype:'fileupload',
					allowBlank:true,
					fieldLabel: '轮播图',
					maxWidth:300,
					height:280,
					itemId:'imgUrl',
					name:'imgUrl',
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
    	var aa = this.lookupI('imgUrl').src;
    	va.imgUrl = aa;
    	var vas = Ext.encode(va);
    	return vas;
    },
    cUrl:Rich.Url.bannersPicPath,
    rUrl:Rich.Url.tpDetPath,
    uUrl:Rich.Url.bannerUpdaPath,
    toStatus:function(st){
    	this.callParent(arguments);
    	this.lookupI('leftCt').setDisabled(st=='r');
    },
    applyValue:function(va){
		this.getForm().setValues(va);
		this.lookupI('imgUrl').setValue(va.src,va.imgUrl);
	},
    callback:function(o,f,r,s){
    	if(f){
	    	if(s == 'u'){
	    		this.loadById(this.userId);
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
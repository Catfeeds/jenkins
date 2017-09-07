/**
 *字典增加页面
 */
Ext.define('Rich.view.content.PictureDetail',{
	requires:['Ext.form.Panel',
	'Ext.form.field.ComboBox'
	],
    extend: 'Rich.widget.Window',
    alias:'widget.picturedetail',
    width:0.5,
    height:0.8,
    autoScroll:true,
    title:'轮播图详情',
 
    id:null,
    createTime:null,
    creator:null,
    imgLink:null,
    imgUrl:null,
    isDelete:null,
    isHidden:null,
    sortOrder:null,
    titleDesc:null,
    src:null,
    showById:function(rec){
    	this.imgUrl = rec.imgUrl;
    	var imgUrl = this.imgUrl.replace('"','');
    	this.lookupI('id').setValue(this.id = rec.id);
    	this.lookupI('titleDesc').setValue(this.titleDesc = rec.titleDesc);
    	this.lookupI('sortOrder').setValue(this.sortOrder = rec.sortOrder);
    	this.lookupI('isDelete').setValue(this.isDelete = rec.isDelete);
    	this.lookupI('isHidden').setValue(this.isHidden = rec.isHidden == 0?'显示':'隐藏');
    	this.lookupI('imgLink').setValue(this.imgLink = rec.imgLink);
    	this.lookupI('imgUrl').setValue(this.src,imgUrl);
    	this.lookupI('form').setDisabled(true);
    	this.show();
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
    		layout:'fit',
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
		            	padding:'10 5 5 10',
		            	anchor: '100%'			            
		            }
		        },
		        layout: 'hbox',
		        items: [{
			       items: [{
		            	xtype:'hiddenfield',
		            	itemId:'isDelete',
		            	name:'isDelete'
		            },{
		            	xtype:'hiddenfield',
		            	itemId:'id',
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
					    itemId:'isHidden',
				    	name:'isHidden',
				    	listener:{
				    		'boxready':function(width, height, eOpts){
				    			//debugger
				    		}
				    	}
				    },{
						itemId:'cover',
				    	xtype:'fileupload',
						allowBlank:true,
						margin:'10 10 10 10',
						fieldLabel: '轮播图',
						//maxWidth:600,
						//emptyText:'',
						height:240,
						itemId:'imgUrl',
						name:'imgUrl',
						labelHidden:true,
						buttonText: '上传图片'
		            },{
		            	xtype:'textfield',
		            	name:'aa',
		            	disabled:true,
		            	emptyText:'图片大小不超过10M,宽1920,高240'
		            }]
		        }]
    		}],
    		buttons:[{
    			text:'编辑',
    			itemId:'btnEdit',
    			handler:function(btn){
    				this.up('picturedetail').lookupI('form').setDisabled(false);
    				btn.ownerCt.getItem('btnEdit').setVisible(false);
    	    		btn.ownerCt.getItem('cancelEdit').setVisible(true);
    	    		btn.ownerCt.getItem('btnSave').setVisible(true);
    			}
    		},{
    			text:'取消编辑',
    			hidden:true,
    			itemId:'cancelEdit',
    			handler:function(btn){
    				btn.ownerCt.getItem('btnEdit').setVisible(true);
    	    		btn.ownerCt.getItem('cancelEdit').setVisible(false);
    	    		btn.ownerCt.getItem('btnSave').setVisible(false);
    			}
    		},{
    			text:'提交',
    			hidden:true,
    			itemId:'btnSave',
    			handler:function(btn){
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
    	var id = vas.id;
    	var a = fm.lookupI('imgUrl').src;
    	vas.imgUrl = a;
    	var ih = vas.isHidden;
    	if(ih == '隐藏'){
    		vas.isHidden = '1'
    	}else{
    		vas.isHidden = '0'
    	}
    	var va = Ext.encode(vas);
    	Rich.JsonAjax.request({
			method:'put',
			getMethod:function(){return "put"},
			url:Rich.Url.bannerDetailPath+id,      	
			params:va,
			callback:this._submitBack,
			binary:true,
			headers:{'Content-Type':'application/json;charset=UTF-8'},
			scope:this
		});
    },
    _submitBack:function(o,f,r){
    	if(f){
    		this.close(true);
    		Rich.Msg.alert('消息','修改成功!')
    		Ext.getCmp('pic').getStore().reload();
    	}
    }
});
/**
 *字典增加页面
 */
Ext.define('Rich.view.tickling.TerraceDetail',{
	requires:['Ext.form.Panel',
	'Ext.form.field.HtmlEditor',
	'Rich.view.tickling.TerraceImgWindow',
	'Ext.form.field.ComboBox'
	],
    extend: 'Rich.widget.Window',
    alias:'widget.terracedetail',
    width:0.8,
    height:0.8,
    minWidth:400,
    minHeight:500,
    autoScroll:true,
    title:'新闻详情',
 
    id:null,
    type:null,
    showById:function(id,type){
    	this.id = id;
    	this.type = type;
    	Rich.JsonAjax.request({
			url:Rich.Url.portalDetailPath+this.id+'?type='+this.type,
			method:'get',
			callback:function(o,f,r){
				if(f){
					if(r.responseJson.data){
						this.lookupI('form').getForm().setValues(r.responseJson.data);
						this.lookupI('form').getForm().setValues(r.responseJson.data.attribute);
						this.lookupI('form').setDisabled(true);
					}else{
						Rich.Msg.alert('提示','数据不全,请补充！');//无需关闭的提示
						var a = this.id;
						this.lookupI('categoryId').setValue(a);
					}
				}
			},
			scope:this
		});
		this.show();
    },
    
    initComponent:function(){
    	var me = this;   
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
		            	padding:'5 5 5 5',
		            	anchor: '100%'			            
		            }
		        },
		        layout: 'hbox',
		        items: [{
		            items: [{
		            	xtype:'hiddenfield',
		            	name:'id',
		            	itemId:'id'
		            },{
		            	xtype:'hiddenfield',
		            	name:'status',
		            	itemId:'status'
		            },{
		                xtype:'textfield',
		                itemId:'title',
		                fieldLabel:'标题',
		                name: 'title'
		            },{
		                xtype:'textfield',
		                fieldLabel: '子标题',
		                itemId:'subTitle',
		                name: 'subTitle'
		            },{
		            	xtype:'textfield',
		            	fieldLabel: '标题描述',
		            	itemId:'titleDesc',
		                name: 'titleDesc'
		            },{
		                xtype:'textfield',
		                fieldLabel: '排序',
		                itemId:'sortOrder',
		                name: 'sortOrder'
		            },{
						xtype:'htmleditor',
						name:'content',
						itemId:'content',
						height:300,
						padding:'5 5 5 5',
						flex:1,
						listeners:{
							'boxready':function(){
								this.toolbar.add({
									text:'上传图片',
									altText:'上传图片',
            						tooltip: '上传图片',
									icon: Rich.Url.icons_46,
									handler:function(){
										Ext.create('Rich.view.tickling.TerraceImgWindow').showFor(this.removeBack,this)
									},
									removeBack:function(src){
										var aa = this.up('terracedetail').lookupI('content').getValue();
										var bb = aa+'<img src="'+src+'"></img>';
										this.up('terracedetail').lookupI('content').setValue(bb);
									}
								});
							}
						}
					},{
		                xtype:'textfield',
		                fieldLabel: '字段描述',
		                itemId:'categoryId',
		                hidden:true,
		                name: 'categoryId'
		            }]
		        }]
    		}],
    		buttons:[{
    			text:'编辑',
    			itemId:'btnEdit',
    			handler:function(btn){
    				this.up('terracedetail').lookupI('form').setDisabled(false);
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
    	var va = Ext.encode(vas);
    	//debugger
    	Rich.JsonAjax.request({
			method:'put',
			getMethod:function(){return "put"},
			url:Rich.Url.portalDetailPath+id,      	
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
    		Rich.Msg.alert('提示','修改成功！');//无需关闭的提示\
    		Ext.getCmp('lt').getStore().reload();
    	}
    }
});
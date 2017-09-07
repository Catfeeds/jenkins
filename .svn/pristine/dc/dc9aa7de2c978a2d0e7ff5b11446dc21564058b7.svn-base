/**
 *新增稿件
 */
Ext.define('Rich.view.tickling.TerraceAddPlate',{
	requires:['Ext.form.Panel',
	'Ext.form.field.HtmlEditor',
	'Rich.view.tickling.TerraceImgWindow',
	'Ext.form.field.ComboBox'
	],
    extend: 'Rich.widget.Window',
    alias:'widget.terraceaddplate',
    width:0.8,
    height:0.8,
    autoScroll:true,
    title:'新增稿件',
 	
    dt:null,
    showById:function(dt){
    	this.dt = dt;
    	this.lookupI('categoryId').setValue(this.dt);
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
		            	name:'categoryId',
		            	itemId:'categoryId'
		            },{
		            	xtype:'hiddenfield',
		            	name:'status',
		            	value:1,
		            	itemId:'status'
		            },{
		                xtype:'textfield',
		                itemId:'title',
		                fieldLabel:'标题',
		                allowBlank:false,
		                name: 'title'
		            },{
		                xtype:'textfield',
		                fieldLabel: '子标题',
		                itemId:'subTitle',
		                allowBlank:false,
		                name: 'subTitle'
		            },{
		            	xtype:'textfield',
		            	fieldLabel: '标题描述',
		            	itemId:'titleDesc',
		            	allowBlank:false,
		                name: 'titleDesc'
		            },{
		                xtype:'textfield',
		                fieldLabel: '排序',
		                itemId:'sortOrder',
		                allowBlank:false,
		                name: 'sortOrder'
		            },{
						xtype:'htmleditor',
						name:'content',
						allowBlank:false,
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
										var aa = this.up('terraceaddplate').lookupI('content').getValue();
										var bb = aa+'<img src="'+src+'" width="200" height="200"/>';
										this.up('terraceaddplate').lookupI('content').setValue(bb);
									}
								});
							}
						}
					}]
		        }]
    		}],
    		buttons:[{
    			text:'提交',
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
    	var va = Ext.encode(vas);
    	Rich.JsonAjax.request({
			method:'post',
			getMethod:function(){return "post"},
			url:Rich.Url.portalAddPath,      	
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
    		Rich.Msg.alert('消息','新增成功');//无需关闭的提示
    		Ext.getCmp('lt').getStore().reload();
    	}
    }
});
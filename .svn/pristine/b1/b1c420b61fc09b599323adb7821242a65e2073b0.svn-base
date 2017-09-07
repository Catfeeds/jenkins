/**
 *资料详情界面
 */
Ext.define('Rich.view.data.DataDetail',{
	requires:['Ext.form.Panel',
	'Ext.form.field.HtmlEditor',
	'Rich.view.tickling.TerraceImgWindow',
	'Ext.form.field.ComboBox'
	],
    extend: 'Rich.widget.Window',
    alias:'widget.datadetail',
    mixWidth:400,
    mixHeight:500,
    width:0.3,
    height:0.3,
    autoScroll:true,
    title:'文档详情',
 
    showById:function(rec){
    	this.lookupI('id').setValue(rec.id);
    	this.lookupI('name').setValue(rec.name);
    	this.lookupI('url').setValue(rec.url)
    	this.lookupI('creator').setValue(rec.creator)
    	this.lookupI('form').setDisabled(true);
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
		            	name:'creator',
		            	itemId:'creator'
		            },{
		            	xtype:'hiddenfield',
		            	name:'id',
		            	itemId:'id'
		            },{
		                xtype:'hiddenfield',
		                itemId:'url',
		                name: 'url'
		            },{
						xtype:'form',
						layout: 'hbox',
						flex:1,
						items:[{
			                xtype:'textfield',
			                itemId:'name',
			                fieldLabel:'文档名',
			                flex:1,
			                name: 'name'
						},{
							xtype:'filefield',
							name:'srcName',
							buttonOnly:true,
							padding: '0 0 0 5',
							itemId:'srcName',
							buttonText: '选择文档',
							listeners:{
								change:function(){
									var fp =this.up('form').lookupI('srcName').getValue();
								    var type = fp ? fp.substring(fp.lastIndexOf('.')+1) : undefined;									
									if(type == 'pdf'){
										var form = this.up('form').getForm();
						                form.submit({
						                    url: Rich.Url.fileUploadPath,
						                    waitMsg: '上传中...',
						                    success: function(fm,act) {
						                    	var code = act.result.code;
						                    	if(code == 0){//成功
						                    		var data = act.result.data['0']
						                    		var src = data.fullUrl;
						                    		var name = data.srcName;
						                    		fm.owner.up('datadetail').lookupI('name').setValue(name);
						                    		fm.owner.up('datadetail').lookupI('src').setValue(src);
						                    	}else{//失败
						                    		var message = act.result.message;
						                    		Rich.Msg.error('错误',mes);//无需关闭的错误
						                    	}
						                    },
						                    failure:function(fm,act){
						                    	var code = act.result.code;
						                    	if(code == 0){//成功
						                    		var data = act.result.data['0']
						                    		var src = data.fullUrl;
						                    		var name = data.srcName;
						                    		fm.owner.up('datadetail').lookupI('name').setValue(name);
						                    		fm.owner.up('datadetail').lookupI('url').setValue(src);
						                    	}else{//失败
						                    		var message = act.result.message;
						                    		Rich.Msg.error('错误',mes);//无需关闭的错误
						                    	}
						                    }
						                });
									}else{
										Rich.Msg.error('错误','文档格式不正确！');//无需关闭的错误
									}
					            }
							}
						}]
		            }]
		        }]
    		}],
    		buttons:['->',{
    			text:'修改',
    			itemId:'btnEdit',
    			handler:function(btn){
    				this.up('datadetail').lookupI('form').setDisabled(false);
    				btn.ownerCt.getItem('btnEdit').setVisible(false);
    	    		btn.ownerCt.getItem('cancelEdit').setVisible(true);
    	    		btn.ownerCt.getItem('btnSave').setVisible(true);
    			}
    		},{
    			text:'取消',
    			itemId:'cancelEdit',
    			hidden:true,
    			handler:function(btn){
    				this.up('datadetail').lookupI('form').setDisabled(true);
    				btn.ownerCt.getItem('btnEdit').setVisible(true);
    	    		btn.ownerCt.getItem('cancelEdit').setVisible(false);
    	    		btn.ownerCt.getItem('btnSave').setVisible(false);
    			}
    		},{
    			text:'提交',
    			itemId:'btnSave',
    			hidden:true,
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
    	var url = Rich.Url.dataListPath+'/'+vas.id;
    	Rich.JsonAjax.request({
			method:'put',
			getMethod:function(){return "put"},
			url:url,      	
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
    		Rich.Msg.alert('消息','修改成功！');
    		Ext.getCmp('dat').getStore().reload();
    	}
    }
});
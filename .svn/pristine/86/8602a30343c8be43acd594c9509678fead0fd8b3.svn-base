/**
 *字典增加页面
 */
Ext.define('Rich.view.data.DataAdd',{
	requires:['Ext.form.Panel',
	'Ext.form.field.HtmlEditor',
	'Rich.view.tickling.TerraceImgWindow',
	'Ext.form.field.ComboBox'
	],
    extend: 'Rich.widget.Window',
    alias:'widget.dataadd',
    mixWidth:300,
    mixHeight:500,
    width:0.3,
    height:0.3,
    autoScroll:true,
    title:'添加文档',
 
    id:null,
    showById:function(id){
    	this.id = id;
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
					                    		fm.owner.up('dataadd').lookupI('name').setValue(name);
					                    		fm.owner.up('dataadd').lookupI('src').setValue(src);
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
					                    		fm.owner.up('dataadd').lookupI('name').setValue(name);
					                    		fm.owner.up('dataadd').lookupI('url').setValue(src);
					                    	}else{//失败
					                    		var message = act.result.message;
					                    		Rich.Msg.error('错误',mes);//无需关闭的错误
					                    	}
					                    }
					                });
					            }
							}
						}]
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
			method:'POST',
			getMethod:function(){return "POST"},
			url:Rich.Url.dataAddPath,      	
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
    		this.returnValue(o,f,r);
    	}
    }
});
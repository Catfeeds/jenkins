/**
 *字典增加页面
 */
Ext.define('Rich.view.leavemessage.SysAdd',{
	requires:['Ext.form.Panel',
	'Ext.form.field.HtmlEditor',
	'Rich.view.tickling.TerraceImgWindow',
	'Ext.form.field.ComboBox'
	],
    extend: 'Rich.widget.Window',
    alias:'widget.sysadd',
    mixWidth:400,
    mixHeight:600,
    width:0.5,
    autoScroll:true,
    title:'添加系统公告',
 
    id:null,
    showById:function(id){
    	this.id = id;
    	this.show();
    },
    
    initComponent:function(){
    	var me = this;   
    	var state1 = Ext.create('Ext.data.Store', {
		    fields: ['abbr', 'name'],
		    data : [
		        {'abbr':"0", 'name':"不推送"},
		        {'abbr':"1", 'name':"推送"}
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
		            	padding:'5 5 5 5',
		            	anchor: '100%'			            
		            }
		        },
		        layout: 'hbox',
		        items: [{
		            items: [{
		            	xtype:'hiddenfield',
		            	name:'status',
		            	value:1,
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
				    	xtype:'combobox',
				    	fieldLabel:'选择推送',
				    	store: state1,
					    queryMode: 'local',
					    displayField: 'name',
					    editable:false,
					    //emptyText:'',
					    allowBlank:false,
					    valueField: 'abbr',
				    	name:'isToAll',
				    	listeners:{
				    	   'select':function(me0, record, index, eOpts){
			               		var type = record['0'].data.abbr;
			               		if(type == 1){
			               			var aa = '选择推送会对所有微信用户推送消息且服务号提供每月4条群发权限！';
			               			this.up('form').lookupI('hint').show();
			               			this.up('form').lookupI('hint').setValue(aa);
			               		}else{
			               			this.up('form').lookupI('hint').hide();
			               		}
							}
	            		}
				    },{
				    	xtype:'textfield',
				    	name:'hint',
				    	itemId:'hint',
				    	disabled:true,
				    	hidden:true,
				    	style:'color:red;background:red;'
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
			url:Rich.Url.sysUpdatePath,      	
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
    		Rich.Msg.alert('消息','新增成功！');
    		Ext.getCmp('sys').getStore().reload();
    		/*this.looupI('btnEdit').show()
    		this.lookupI('cancelEdit').hide();
    		this.lookupI('cancebtnSavelEdit').hide();*/
    	}
    }
});
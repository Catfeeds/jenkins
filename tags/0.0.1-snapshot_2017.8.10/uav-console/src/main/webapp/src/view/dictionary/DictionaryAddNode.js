/**
 *字典增加页面
 */
Ext.define('Rich.view.dictionary.DictionaryAddNode',{
	requires:['Ext.form.Panel'
	,'Ext.form.field.ComboBox'
	],
    extend: 'Rich.widget.Window',
    alias:'widget.dictionaryaddnode',
    width:700,
    height:400,
    autoScroll:true,
    title:'增加节点',
    
    showById:function(type){
    	this.show();
    },
    initComponent:function(){
    	var me = this;   
    	Ext.apply(me,{
    		layout:'fit',
    		dockedItems:{
    			docked:'top',
    			itemId:'topToolbar',
    			xtype:'toolbar',
    			items:['->']
    		},
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
		            	anchor: '80%'			            
		            }
		        },
		        layout: 'hbox',
		        items: [{xtype:'box',flex:0.1},{
		            items: [{
		                xtype:'textfield',
		                fieldLabel: '名称描述',
		                itemId:'typeDesc',
		                allowblank:false,
		                name: 'typeDesc'
		            },{
		                xtype:'textfield',
		                fieldLabel: '名称类型',
		                allowblank:false,
		                itemId:'typeName',
		                name: 'typeName'
		            },{
		                xtype:'hiddenfield',
		                itemId:'isParent',
		                value:0,
		                name: 'isParent'
		            },{
		                xtype:'hiddenfield',
		                value:0,
		                itemId:'parentCode',
		                name: 'parentCode'
		            },{
		                xtype:'textfield',
		                fieldLabel: '对应字段',
		                allowblank:false,
		                itemId:'itemCode',
		                name: 'itemCode'
		            },{
		                xtype:'textfield',
		                fieldLabel: '字段描述',
		                allowblank:false,
		                itemId:'itemDesc',
		                name: 'itemDesc'
		            }]
		        },{xtype:'box',flex:0.1}]
    		}],
    		buttons:[{
    			text:'提交',
    			handler:function(){
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
    	Rich.JsonAjax.request({
			method:'post',
			getMethod:function(){return "post"},
			url:Rich.Url.dictionaryAddPath,      	
			params:vas,
			callback:this._submitBack,
			scope:this
		});
    },
    _submitBack:function(o,f,r){
    	if(f){
    		this.close(true);
    		Ext.getCmp('tree').reload();
    	}
    }
});
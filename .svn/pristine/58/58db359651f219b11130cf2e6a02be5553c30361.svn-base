/*
 * 用户实名认证窗体
 * */
Ext.define('Rich.view.tuser.AuthWindow',{
	requires:[
	'Ext.form.Panel',
	'Rich.component.DicComboBox'
	],
    extend: 'Rich.widget.Window',
    alias:'widget.authwindow',
    width:0.3,
    height:0.5,
    autoScroll:true,
    title:'用户认证',
    
    initComponent:function(){
    	var me = this;
    	
    	Ext.apply(me,{
    		layout:'fit',
    		items:[{
    			itemId:'form',
    			xtype:'form',
    			padding:'10 10 10 10',
    			layout:{
    				type:'vbox',
    				align:'stretch'
    			},
    			items:[{
			    	xtype:'textfield',
			        fieldLabel: '认证结果',
			        itemId:'result',
			        name: 'result',
			        editable:false,
			    },{
			    	flex:1,
			    	allowBlank:false,
    				fieldLabel:'原因',
    				xtype:'textarea',
    				name:'reason'
    			}]
    		}],
    		buttons:['->',{
    			text:'提交修改',
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
    	vas.targetId = this.tuser.get('id');
    	this.el.mask('提交中...');
    	Rich.JsonAjax.request({
			method:'post',
			getMethod:function(){return "post"},
			url:Rich.Url.tuserAuditPath+'?type=1',       	
			params:vas,
			callback:this._submitBack,
			scope:this
		});
    },
    _submitBack:function(o,f,r){
    	this.el.unmask();
    	if(f){
    		this.close(true);
    		this.returnValue(f);
    	}
    }
});
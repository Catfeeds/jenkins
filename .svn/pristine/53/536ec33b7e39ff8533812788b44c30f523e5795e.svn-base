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
    width:0.2,
    height:0.3,
    minWidth:100,
    minHeight:150,
    autoScroll:true,
    title:'用户认证',
    
    getId:function(){
    	return this.id
    },
    getUr:function(){
    	return this.type
    },
    initComponent:function(){
    	var me = this;
    	
    	var states = Ext.create('Ext.data.Store', {
		    fields: ['abbr', 'name'],
		    data : [
		        {'abbr':"1", 'name':"通过"},
		        {'abbr':"0", 'name':"不通过"}
		        
		    ]
		});
		
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
			    	xtype:'combobox',
			    	fieldLabel:'审核结果',
			    	store: states,
			    	labelWidth:70,
				    queryMode: 'local',
				    displayField: 'name',
				    valueField: 'abbr',
			    	name:'authStatus'
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
    	var va = fm.getValues();
    	if(this.getUr() == 1){
    		var url = Rich.Url.personChePath+this.getId()+'/status';
    	}else{
    		var url = Rich.Url.companChePath+this.getId()+'/status';
    	}
    	//var vas = Ext.encode(va);
    	this.el.mask('提交中...');
    	Rich.JsonAjax.request({
			method:'put',
			getMethod:function(){return "put"},
			url:url,     	
			params:va,
			//headers:{'Content-Type':'application/json;charset=UTF-8'},
			callback:this._submitBack,
			scope:this
		});
    },
    _submitBack:function(o,f,r){
    	this.el.unmask();
    	if(f){
    		this.close(true);
    		Rich.Msg.alert('消息','审核完成');//无需关闭的提示\
    		this.returnValue(f);
    	}
    }
});
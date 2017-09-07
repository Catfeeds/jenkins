/*
 * 飞行计划审核窗体
 * */
Ext.define('Rich.view.flight.AuthWindow',{
	requires:[
	'Ext.form.Panel',
	'Ext.form.ComboBox'
	],
    extend: 'Rich.widget.Window',
    alias:'widget.flightauthwindow',
    minWidth:350,
    minHeight:250,
    maxWidth:370,
    maxHeight:270,
    autoScroll:true,
    title:'飞行计划申请审核',
    
    getId:function(){
    	return this.id
    },
    initComponent:function(){
    	var me = this;
    	var states = Ext.create('Ext.data.Store', {
		    fields: ['abbr', 'name'],
		    data : [
		        {'abbr':'1', 'name':'通过'},
		        {'abbr':'0', 'name':'不通过'}
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
			        fieldLabel: '认证结果',
			        labelWidth:60,
			        itemId:'status',
			        name: 'status',
			        editable:false,
			        allowBlank:false,
			        store: states,
				    queryMode: 'local',
				    displayField: 'name',
				    valueField: 'abbr',
				    listeners:{
		               'select':function(me0, record, index, eOpts){
		               		var type = record['0'].data.abbr;
		               		if(type != 1){
		               			this.up('form').lookupI('reason').show();
		               		}else{
		               			this.up('form').lookupI('reason').hide();
		               		}
						}
            		}
			    },{
			    	xtype:'textareafield',
			    	fieldLabel: '原因',
			    	hidden:true,
			    	labelWidth:60,
			    	itemId:'reason',
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
    	var ul = Rich.Url.flyPlansCheckPath+this.getId()+'/status';
    	var va = Ext.encode(vas);
    	Rich.JsonAjax.request({
			method:'put',
			getMethod:function(){return "put"},
			url:ul,	
			params:va,
			headers:{'Content-Type':'application/json;charset=UTF-8'},
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
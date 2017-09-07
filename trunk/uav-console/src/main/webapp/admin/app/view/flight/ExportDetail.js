/**
 *字典增加页面
 */
Ext.define('Rich.view.flight.ExportDetail',{
	requires:['Ext.form.Panel',
	'Ext.form.field.ComboBox'
	],
    extend: 'Rich.widget.Window',
    alias:'widget.exportdetail',
    width:0.4,
    height:0.3,
    minWidth:200,
    minHeight:300,
    autoScroll:true,
    title:'选择导出项',
 
    id:null,
    type:null,
    getVs:function(){
    	return this.rec;
    },
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
	    			xtype: 'checkboxgroup',
	    			fieldLabel: '导出项',
	    			columns: 4,
	    			vertical: true,
	    			items: [
		    			{ boxLabel: '申请日期', name: '申请日期',inputValue: 'applyTime', checked: true },
		    			{ boxLabel: '申请单位', name: '申请单位', inputValue: 'userName', checked: true },
		    			{ boxLabel: '申请任务编号', name: '申请任务编号', inputValue: 'number', checked: true },
		    			{ boxLabel: '所属空域辖区', name: '所属空域辖区', inputValue: 'airTerritorial', checked: true },
		    			{ boxLabel: '任务性质', name: '任务性质', inputValue: 'planType', checked: true },
		    			{ boxLabel: '任务机型', name: '任务机型', inputValue: 'planes', checked: true },
		    			{ boxLabel: '空域类别', name: '空域类别', inputValue: 'type', checked: true },
		    			{ boxLabel: '空域名称', name: '空域名称', inputValue: 'spaceName', checked: true },
		    			{ boxLabel: '飞行高度', name: '飞行高度', inputValue: 'high', checked: true },
		    			{ boxLabel: '计划时间', name: '计划时间', inputValue: 'planTime', checked: true },
		    			{ boxLabel: '联系人', name: '联系人', inputValue: 'contactNameAndPhone',checked: true },
		    			{ boxLabel: '状态', name: '状态', inputValue: 'status', checked: true },
		    			{ boxLabel: '计划执行动态', name: '计划执行动态', inputValue: 'situation',checked: true }]
		    		}]
	    		}],
	    		buttons:[{
	    			text:'提交',
	    			handler:function(btn){
	    				this.up('window').submit();
	    			}
	    		}]
	    	});
    		me.callParent(arguments);
    },
    submit:function(){
    	var fm = this.lookupI('form');
    	var vas = fm.getValues();
    	var a = Ext.encode(vas);
    	var vs = this.getVs();
    	vs.map = a;
    	var v = Ext.Object.toQueryString(vs);
    	var url = Rich.Url.flyListPath+'?'+v;
    	var downHistoryHref = url;
		window.location = downHistoryHref;
		this.close(true);
    }
});
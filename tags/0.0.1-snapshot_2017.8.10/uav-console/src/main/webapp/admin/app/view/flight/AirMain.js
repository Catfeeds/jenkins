/**
 * 空域申请管理的main
 */
Ext.define('Rich.view.flight.AirMain', {
	requires:[
		'Rich.widget.Message',
		'Ext.grid.Panel',
		'Ext.form.Panel',
		'Ext.form.field.Date',
		'Ext.form.FieldContainer',
        'Ext.form.field.Hidden',
        'Ext.form.field.Trigger',
        'Ext.form.field.ComboBox',
        'Ext.form.CheckboxGroup',
        'Ext.grid.column.Action',
        'Ext.layout.component.FieldSet',
        'Ext.form.field.Checkbox',
        'Ext.toolbar.Paging',
        'Rich.model.Flight',
        'Rich.view.flight.ExportApplyDetail',
        'Rich.view.flight.ApplyDetail',
        'Rich.widget.CrudWindow',
        'Rich.component.DicComboBox'
    ],
    extend: 'Ext.panel.Panel',
    alias:'widget.flightairmain',
	initComponent:function(){
		var me = this;
		var al = Rich.RightManager.hasRight(Rich.V.airplan);//分页权限
		var check = Rich.RightManager.hasRight(Rich.V.airplan_airplanId_status);//分页权限
		var action = [{
            icon: Rich.Url.icons_6,
            iconCls:'actioncolumn-margin',
            altText:'查看详情',
            tooltip: '查看详情',
            handler: function(gridview, rowIndex, colIndex) {
                var rec = gridview.getStore().getAt(rowIndex);
                this.up('gridpanel').lookDetail(rec);
            }
       	}]
		
		var store = Ext.create('Ext.data.Store', {
			model:'Rich.model.Flight',
	        pageSize: 50,
	        autoLoad:al,
	        proxy: {
		        type: 'ajax',
		        getMethod:function(){return "get"},
		        url: Rich.Url.airplaceListPath,
		        reader: {
		            type: 'json',
		            root: 'data.content',
		            totalProperty: 'data.totalElements',
		            idProperty: 'id'
		        }
		    }
		});
		
		var state1 = Ext.create('Ext.data.Store', {
		    fields: ['abbr', 'name'],
		    data : [
		        {'abbr':"0", 'name':"审核中"},
		        {'abbr':"1", 'name':"通过审核"},
		        {'abbr':"2", 'name':"未通过审核"}
		    ]
		});
		
		var states = Ext.create('Ext.data.Store', {
		    fields: ['abbr', 'name'],
		    data : [
		        {'abbr':"0", 'name':"管制空域"},
		        {'abbr':"1", 'name':"一类报告空域"},
		        {'abbr':"2", 'name':"二类报告空域"}
		    ]
		});
		
		var stat = Ext.create('Ext.data.Store', {
		    fields: ['abbr', 'name'],
		    data : [
		        {'abbr':"南部战区航管保障队", 'name':"南部战区航管保障队"},
		        {'abbr':"岑村机场调度室", 'name':"岑村机场调度室"},
		        {'abbr':"佛山机场调度室", 'name':"佛山机场调度室"},
		        {'abbr':"惠州机场调度室", 'name':"惠州机场调度室"}
		    ]
		});
		
		
		var itms = [{
	    	xtype:'form',
	    	itemId:'form',
	    	style:{padding:"10px"},
	    	docked:'top',
	    	defaults:{ 
	    		xtype:'textfield',
	    		labelWidth:70,
	    		style:{float:"left",margin:"5px 0  5px 10px"}
	    	},
	    	layout:'auto',
		    items: [{
		        fieldLabel: '申请单位',
		        labelWidth:60,
		        name: 'unit'
		    },{
		        fieldLabel: '申请任务编号',
		        labelWidth:90,
		        name: 'code'
		    },{
		        fieldLabel: '所属空域辖区',
		        labelWidth:90,
		        xtype:'combobox',
		        name: 'territorial',
		        store: stat,
			    queryMode: 'local',
			    displayField: 'name',
			    valueField: 'abbr'
		    },{
		    	xtype:'combobox',
		    	fieldLabel:'任务状态',
		    	labelWidth:60,
		    	store: state1,
			    queryMode: 'local',
			    displayField: 'name',
			    valueField: 'abbr',
		    	name:'status'
		    },{
		        fieldLabel: '任务性质',
		        name: 'type'
		    },{
	            xtype: 'fieldcontainer',
	            fieldLabel : '申请日期',
	            width:300,
	            labelWidth:60,
	            layout: 'hbox',
	            items: [{
					xtype: 'datefield',
					flex:1,
				    anchor: '100%',
				    name: 'startDate',
				    format:'Y-m-d'
				},{xtype:'label',text:'-',style:{'line-height': '25px;'}},{
					xtype: 'datefield',
					flex:1,
				    anchor: '100%',
				    name: 'endDate',
				    format:'Y-m-d'
				}]
	        },{
		    	xtype:'button',
		    	text:'重置',
		    	rightId:Rich.V.flyplans,
		    	cls:'r-btn-font-normal',
		    	style:{float:"right",margin:"5px 10px 5px 0"},
		    	handler:function(btn){
		    		this.up('form').getForm().reset();
		    	}
		    },{
		    	xtype:'button',
		    	text:'搜索',
		    	rightId:Rich.V.flyplans,
		    	cls:'r-btn-font-normal',
		    	style:{float:"right",margin:"5px 10px 5px 10px"},
		    	handler:function(btn){
		    		var vs = this.up('form').getForm().getValues();
		    		var sto = this.up('gridpanel').getStore();
		    		sto.getProxy().extraParams = vs;
		    		sto.loadPage(1);
		    	}
		    }]
		},{
	    	xtype:'toolbar',
	    	docked:'top',
	    	items:[{
	    		text:'导出列表',
	    		handler:function(btn){
	    			var fm = this.up('gridpanel').lookupI('form',true);
		            var vs = fm.getForm().getValues();
	    			Ext.create('Rich.view.flight.ExportApplyDetail',{
	    				rec:vs
	    			}).show(this.choseBack,this);
	    		}
	    	},'->',{
	    		text:'审核中',
	    		style : 'background:#BFEFFF'
	    	},{
	    		text:'通过审核',
	    		style : 'background:#7FFF00'
	    	},{
	    		text:'未通过',
	    		style : 'background:#FF00FF' 
	    	}]
		}];
		Ext.apply(me,{
			layout:'fit',
			items:[{
				xtype:'gridpanel',
				itemId:'list',
				loadMask: true,
		    	forceFit:true,
		    	store: store,
			    bbar: Ext.create('Ext.PagingToolbar', {
		            store: store,
		            displayInfo: true,
		            displayMsg: '显示 {0} - {1} of {2}',
		            emptyMsg: "没有数据",
		            items:['-']
		        }),
		        viewConfig:{
				    stripeRows:true,//在表格中显示斑马
				    enableTextSelection:true, //可以复制单元格文字
				    getRowClass:function(record, index, rowParams, store){
	               		var at = record.get('status')
	                 	if(at == 0){
	                 		return "color-BFEFFF";
	                 	}else if(at == 1){
	                 		return 'color-7FFF00';	
	                 	}else if(at == 2){
	                 		return 'color-FF00FF';
	                 	}
	               }
				},
			   	columnLines: true,
			  	columns: {
				   	defaults:{
				        sortable:false,
				        enableTextSelection:true,//复制
				        draggable:false
				   	},
				   	items:[
				   		{ text: '申请日期', dataIndex: 'createTime',width: 120 },
				        { text: '申请单位', dataIndex: 'userName',flex: 1 },
				        { text: '申请任务编号', dataIndex: 'number',width: 120 },
				        { text: '任务性质', dataIndex: 'planType',width: 120 },
				       	{ text: '任务机型', dataIndex: 'planes',flex: 1,renderer:function(v){
				        	if(v){
				        		var aa = Ext.decode(v);
				        		var bb = aa['0'].name;
				        		return bb;
				        	}
				        }},
				        { text: '申请空域名称', dataIndex: 'airInfo',flex: 1,renderer:function(v){
				        	if(v){
				        		var aa = Ext.decode(v);
				        		var bb = aa['0'].location;
				        		return bb;
				        	}
				        }},
				        { text: '申请高度', dataIndex: 'airInfo',width: 110,hidden:true,renderer:function(v){
				        	if(v){
				        		var aa = Ext.decode(v);
				        		var bb = aa['0'].high+' 米(含)以下';
				        		return bb;
				        	}
				        }},
				        {text: '任务时间', dataIndex: 'beginTime',flex: 1,renderer:function(v,record){
				        	var begin = v;
				        	var end = record.record.data.endTime;
				        	var endtime = end.substring(end.length-6,end.length);
				        	if(begin != '' || end != '' || begin != null || end != null){
				        		return begin+'至'+endtime;
				        	}
				        }},
				        {text: '所属空域辖区', dataIndex: 'airInfo',flex: 1,hidden:true,renderer:function(v){
				        	var aa = Ext.decode(v);
				        	return aa['0'].cityName
				        }},
				        { text: '状态', dataIndex: 'status',flex: 1,renderer:function(v,record){
				        	if(v == 0){//0，1，2，3，4
				        		return '审核中';
				        	}else if(v == 1){
				        		return '通过审核'
				        	}else if(v == 2){
				        		return '未通过审核';
				        	}else if(v == 3){
				        		return '执行中';
				        	}else if(v == 4){
				        		return '已完成';
				        	}else if(v == 5){
				        		return '计划超时';
				        	}else{
				        		return '撤销申请';
				        	}
				        }},
				        {
				        	text:'操作',
				            xtype:'actioncolumn',
				            width:70,
				            items: action
				        }
				]},
				lookDetail:function(rec){
					Ext.create('Rich.view.flight.ApplyDetail',{
						title:'申请空域详情'+rec.raw.number,
			    		rec:rec.raw
			    	}).showFor(this.authBack,this);
				},
				
			    toAuth:function(record){
			    	Ext.create('Rich.view.flight.AirAuthWindow',{
			    		id:record.get('id')
			    	}).showFor(this.authBack,this);
				 },
				 authBack:function(f){
				 	if(f){
				 		this.getStore().reload();
				 	}
				 },
				 dockedItems:itms
			}]
		});
		this.callParent(arguments);
	}
});

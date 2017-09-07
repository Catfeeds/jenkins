/**
 * 飞行管理的main
 */
Ext.define('Rich.view.flight.Main', {
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
        'Rich.view.flight.FlightDetail',
        'Rich.view.flight.ExportDetail',
        'Rich.widget.CrudWindow',
        'Rich.component.DicComboBox'
    ],
    extend: 'Ext.panel.Panel',
    alias:'widget.flightmain',
	initComponent:function(){
		var me = this;
		var al = Rich.RightManager.hasRight(Rich.V.flyplans);//分页权限
		var check = Rich.RightManager.hasRight(Rich.V.flyplan_flyplanId_status);//审核权限
		var daochu = Rich.RightManager.hasRight(Rich.V.excel_flyplans);//导出权限
		var action = [{
            icon: 'resources/images/icons/6.png',
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
		        url: Rich.Url.flyplansPath,
		        reader: {
		            type: 'json',
		            root: 'data.content',
		            totalProperty: 'data.totalElements',
		            idProperty: 'id'
		        }
		    }
		});
		
		var stat = Ext.create('Ext.data.Store', {
		    fields: ['abbr', 'name'],
		    data : [
		        {'abbr':"广州分区", 'name':"广州分区"},
		        {'abbr':"岑村机场管制室", 'name':"岑村机场管制室"},
		        {'abbr':"佛山机场管制室", 'name':"佛山机场管制室"},
		        {'abbr':"惠州机场管制室", 'name':"惠州机场管制室"},
		        {'abbr':"长沙机场管制室", 'name':"长沙机场管制室"},
		        {'abbr':"邵东机场管制室", 'name':"邵东机场管制室"},
		        {'abbr':"耒阳机场管制室", 'name':"耒阳机场管制室"},
		        {'abbr':"零陵机场管制室", 'name':"零陵机场管制室"}
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
		
		var state1 = Ext.create('Ext.data.Store', {
		    fields: ['abbr', 'name'],
		    data : [
		        {'abbr':"0", 'name':"审核中"},
		        {'abbr':"1", 'name':"通过审核"},
		        {'abbr':"2", 'name':"未通过审核"},
		        {'abbr':"3", 'name':"执行中"},
		        {'abbr':"4", 'name':"已完成"},
		        {'abbr':"5", 'name':"计划超时"},
		        {'abbr':"6", 'name':"撤销申请"}
		    ]
		});
		var store1 = Ext.create('Ext.data.Store', {
			  proxy: {
		         type: 'ajax',
		         url: Rich.Url.flyComboPath,
		         reader: {
		             type: 'json',
		             root: 'data'
		         }
		     },
		      fields : ['id',{name:'value',convert:function(v, record){
					return record.raw.location;
				}},'location'] 
	  	});
		var itms = [{
	    	xtype:'form',
	    	itemId:'form',
	    	style:{padding:"10px"},
	    	docked:'top',
	    	defaults:{ 
	    		xtype:'textfield',
	    		//labelWidth:70,
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
		        name: 'planType'
		    },{
		        fieldLabel: '所属空域辖区',
		        xtype:'combobox',
		        name: 'territorial',
		        store: stat,
			    queryMode: 'local',
			    displayField: 'name',
			    valueField: 'abbr'
		    },{
	            xtype: 'fieldcontainer',
	            fieldLabel : '计划日期',
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
		        fieldLabel: '所属行政辖区',
		        //labelWidth:80,
		        name: 'airArea'
		    },{
		    	xtype:'combobox',
		    	fieldLabel:'空域类别',
		    	store: states,
			    queryMode: 'local',
			    displayField: 'name',
			    valueField: 'abbr',
		    	name:'type'
		    },{
	            fieldLabel : '计划空域名称',
				xtype:'combobox',
		        name: 'air',
		        //labelWidth:110,
		        store: store1,
			    queryMode:'remote',
			    displayField: 'value',
			    //valueField: 'cityName',
		        editable:false
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
	    		disabled:!daochu,
	    		handler:function(btn){
	    			var fm = this.up('gridpanel').lookupI('form',true);
		            var vs = fm.getForm().getValues();
		       	   /* var downHistoryHref = Ext.urlAppend(Rich.Url.flyListPath, Ext.Object.toQueryString(vs));
		       	    window.location = downHistoryHref;*/
	    			Ext.create('Rich.view.flight.ExportDetail',{
	    			rec:vs
	    			}).show(this.choseBack,this);
	    			
		            
	    		}
	    		
	    	},'->',{
	    		text:'审核中',
	    		style : 'background:#BFEFFF'
	    		//disabled:true
	    	},{
	    		text:'通过审核',
	    		style : 'background:#7FFF00' 
	    		//disabled:true
	    	},{
	    		text:'未通过',
	    		style : 'background:#FF00FF' 
	    		//disabled:true
	    	},{
	    		text:'执行中',
	    		style : 'background:#84BF96'//8B008B
	    		//disabled:true
	    	},{
	    		text:'已完成'
	    		//style : 'background:#7FFF00', 
	    		//disabled:true
	    	},{
	    		text:'撤销申请',
	    		style : 'background:#FAEBD7'
	    		//disabled:true
	    	},{
	    		text:'计划超时',
	    		style : 'background:#CCCCFF'
	    		//disabled:true
	    	}]
		}];
		
		Ext.apply(me,{
			layout:'fit',
			items:[{
				xtype:'gridpanel',
				itemId:'list',
				//id:'shenhe',
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
				    stripeRows:true,//在表格中显示斑马线
				    //enableTextSelection:true, //可以复制单元格文字
				    getRowClass:function(record, index, rowParams, store){
	               		var at = record.get('status')
	                 	if(at == 0){
	                 		return "color-BFEFFF";
	                 	}else if(at == 1){
	                 		return 'color-7FFF00';	
	                 	}else if(at == 2){
	                 		return 'color-FF00FF';
	                 	}else if(at == 3){
	                 		return 'color-84BF96';
	                 	}else if(at == 4){
	                 		return '';
	                 	}else if(at == 5){
	                 		return 'color-CCCCFF';
	                 	}else{
	                 		return 'color-FAEBD7';
	                 	}
	               }
				},
			   	columnLines: true,
			  	columns: {
				   	defaults:{
				       // sortable:false,
				        draggable:false
				   	},
				   	items:[
				   		{ text: '申请日期', dataIndex: 'createTime',width: 120 },
				        { text: '申请单位', dataIndex: 'userName',flex: 1 },
				        { text: '申请任务编号', dataIndex: 'number',width: 120 },
				        { text: '所属空域辖区', dataIndex: 'airInfo',flex: 1,hidden:true,renderer:function(v,record){
				        	if(record.record.data.type != 0){
					        	if(v){
					        		var data = Ext.decode(v);
					        		return data['0'].territorial;
					        	}
				        	}else{
				        		return '-';
				        	}
				        }},
				        { text: '任务性质', dataIndex: 'planType',hidden:true,width: 120 },
				       	{ text: '任务机型', dataIndex: 'planes',flex: 1,renderer:function(v){
				        	if(v){
				        		var aa = Ext.decode(v);
				        		return aa['0'].name;
				        	}
				        }},
				        { text: '空域类别', dataIndex: 'type',width: 120 ,renderer:function(v){
				        	if(v == 0){
				        		return '管制空域';
				        	}else if( v == 1){
				        		return '一类报告空域';
				        	}else{
				        		return '二类报告空域';
				        	}
				        }},
				        { text: '计划空域名称', dataIndex: 'airInfo',flex: 1,renderer:function(v){
				        	if(v){
				        		var aa = Ext.decode(v);
				        		var bb = aa['0'].location;
				        		return bb;
				        	}
				        }},
				        { text: '所属行政辖区', dataIndex: 'airInfo',hidden:true,width: 140 ,renderer:function(v,record){
				        	if(v){
				        		var type = record.record.data.type;
				        		var aa = Ext.decode(v);
				        		switch(type){
				        			case 0:
					        			return ' -'
					        			break;
				        			case 1:
				        				return aa['0'].provinceName+' '+aa['0'].cityName;
				        				break;
				        			case 2:
				        				return aa['0'].provinceName+' '+aa['0'].cityName+' '+aa['0'].areaName;
				        		}
				        	}
				        }},
				        { text: '飞行高度', dataIndex: 'airInfo',width: 110,hidden:true,renderer:function(v){
				        	if(v){
				        		var aa = Ext.decode(v);
				        		var bb = '真高'+aa['0'].high+'米(含)以下';
				        		return bb;
				        	}
				        }},
				        {text: '计划时间', dataIndex: 'beginTime',flex: 1,renderer:function(v,record){
				        	var begin = v;
				        	var end = record.record.data.endTime;
				        	var endtime = end.substring(end.length-6,end.length);
				        	if(begin != '' || end != '' || begin != null || end != null){
				        		return begin+'至'+endtime
				        	}
				        }},
				        { text: '联系人及联系方式', dataIndex: 'contactInfo',hidden:true,width: 120 ,renderer:function(v,record){
					        	var contact = Ext.decode(v);
					        	if(record.record.data.type == 0){
					        		return contact.fieldContactName+' 联系'+contact.fieldContactPhone
					        	}else if(record.record.data.type == 1){
					        		return contact.fieldContactName+' 联系'+contact.fieldContactPhone
					        	}else if(record.record.data.type == 2){
					        		return contact.controller+' 联系'+contact.controllerContact;
					        	}
					        	
				        	}
				        },
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
				        { text: '计划执行动态', dataIndex: 'startTime',flex: 1,hidden:true,renderer:function(v,record){
				        	var begin = v?v:'-';
				        	var end = record.record.raw.finishTime?record.record.raw.finishTime:'-';
				        	return '起飞:'+begin+'降落:'+end;
				        }},{
				        	text:'操作',
				            xtype:'actioncolumn',
				            width:70,
				            items: action
				        }
				]},
				lookDetail:function(rec){
					var type = rec.raw.type == '0'?'管制空域':(rec.raw.type == '1'?'一类报告空域':'二类报告空域');
					var number = rec.raw.number;
					Ext.create('Rich.view.flight.FlightDetail',{
						title:type+' '+'任务编号: '+number,
						rec:rec.raw
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

Ext.define('Rich.view.check.ShipCheckMain', {
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
        'Ext.form.field.File',
        'Ext.form.FieldSet',
        'Rich.model.ShipCheck',
        'Ext.layout.component.FieldSet',
        'Ext.form.field.Checkbox',
        'Ext.toolbar.Paging',
        'Rich.view.check.ShipCheckWindow',
        'Rich.component.DicComboBox',
        'Rich.Value',
        'Rich.Url'
    ],
   
    extend: 'Ext.panel.Panel',
    alias:'widget.shipcheckmain',
    
     getShipId:function(id){
    	return this.ship.get('id');
    },
     
	initComponent:function(){
		var me = this;
		var rs = Rich.RightManager.hasRight(Rich.V.ship_audit);
		var al = Rich.RightManager.hasRight(Rich.V.ship_query_audit);
		var actionItems = [{
            icon: 'resources/images/icons/6.png',
            iconCls:'actioncolumn-margin',
            altText:'审核结果',
            tooltip: '审核结果',
            disabled:!rs,
            handler: function(gridview, rowIndex, colIndex) {
                var rec = gridview.getStore().getAt(rowIndex);
                this.up('gridpanel').lookDetail(rec);
            }
        }];
		
		var itms = [{
	    	xtype:'form',
	    	style:{padding:"10px"},
	    	docked:'top',
	    	defaults:{
	    		xtype:'textfield',
	    		labelWidth:70,
	    		style:{float:"left",margin:"5px 0  5px 10px"}
	    	},
	    	layout:'auto',
		    items: [{
		        fieldLabel: '线路名称',
		        labelWidth:60,
		        name: 'name'
		    },{
		        fieldLabel: '线路编号',
		        name: 'routeNo'
		    },{
		        fieldLabel: '船只编号',
		        name: 'routeNo'
		    },{
		        fieldLabel: '船主名称',
		        name: 'routeNo'
		    },{
		    	xtype:'fieldcontainer',
		    	layout:'hbox',
		    	style:{float:"right",margin:"5px 0  5px 10px"},
		    	width:90,
		    	items:[{
			    	xtype:'button',
			    	text:'搜索',
			    	margin:'0 6 0 0',
			    	cls:'r-btn-font-normal',
			    	rightId:Rich.V.ship_query_audit,
			    	handler:function(btn){
			    		var vs = this.up('form').getForm().getValues();
			    		var sto = this.up('gridpanel').getStore();
			    		sto.getProxy().extraParams = vs;
			    		sto.loadPage(1);
			    	}
			    },{
			    	xtype:'button',
			    	text:'重置',
			    	cls:'r-btn-font-normal',
			    	rightId:Rich.V.ship_query_audit,
			    	handler:function(btn){
			    		this.up('form').getForm().reset();
			    	}
			    }]
		    }]
		}];
		
		var store = Ext.create('Ext.data.Store', {
			model:'Rich.model.ShipCheck',
	        pageSize: 50,
	        autoLoad:al,	        
	        proxy: {
		        type: 'ajax',
		        getMethod:function(){return "post"},		       
		       	url: Rich.Url.shipCheckPath,	
		        reader: {
		            type: 'json',
		            root: 'data.pageData',
		            totalProperty: 'data.totalRows',
		            idProperty: 'id'
		        }
		    }
		});
		Ext.apply(me,{
			layout:'fit',
			items:[{
					xtype:'gridpanel',
					itemId:'grid',
					id:'chuanboshenhe',
					loadMask: true,					
					padding:'0',
					selType: 'checkboxmodel',
			    	forceFit:true,
			    	store: store,
				   	bbar: Ext.create('Ext.PagingToolbar', {
			            store: store,
			            displayInfo: true,
			            displayMsg: '显示 {0} - {1} of {2}',
			            emptyMsg: "没有数据",
			            items:['-']
			        }),				  
				   columnLines: true,
				   columns: {
					   	defaults:{					       
					        sortable:false,
					        draggable:false,
					        enableColumnHide:false,
					        menuDisabled:true
					   	},
					   	items:[
					        { text: '船舶编号', dataIndex: 'number',flex: 1 },
					        { text: '线路名称',    dataIndex: 'name',flex: 1},	
					        { text: '船舶类型', dataIndex: 'typeDesc',width:140 },
					        { text: '船主名称',    dataIndex: 'ownerName',flex:1},
					        { text: '船主手机号', dataIndex: 'ownerPhone',flex: 1},
					        { text: '时间', dataIndex: 'applyTime',width:140 },	
					        { text: '状态', dataIndex: 'resultDesc',width:140 },	
					        {
					        	text:'操作',
					            xtype:'actioncolumn',
					            width:120,
					            items:actionItems
					        }
					]},
					
					lookDetail:function(record){
						Ext.create('Rich.view.check.ShipCheckWindow',{
				    		ShipId:record
				    	}).showFor(this.authBack,this);
				    },
				    authBack:function(f){
				    	this.lookupI('grid').getStore().reload();
				    },
				    dockedItems:itms
				}]
		});
		me.callParent(arguments);
	}
});

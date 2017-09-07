/**
 * 船家证书审核
 */
Ext.define('Rich.view.check.OwnerPaperMain', {
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
        'Rich.model.RouteCheck',
        'Ext.form.field.File',
        'Ext.form.FieldSet',
        'Ext.layout.component.FieldSet',
        'Ext.form.field.Checkbox',
        'Ext.toolbar.Paging',
       	'Rich.view.check.OwnerCheckWindow',
        'Rich.component.DicComboBox',
        'Rich.Value',
        'Rich.Url'
    ],
   
    extend: 'Ext.panel.Panel',
    alias:'widget.ownerpapermain',
    
     getShipId:function(id){
    	return this.ship.get('id');
    },
     
	initComponent:function(){
		var me = this;
		var rs = Rich.RightManager.hasRight(Rich.V.record_selectCertificateAuditsByPage);//详情权限
		var al = Rich.RightManager.hasRight(Rich.V.record_audit_certificate);//分页权限
		var actionItems = [{
            icon: 'resources/images/icons/6.png',
            iconCls:'actioncolumn-margin',
            altText:'审核结果',
            tooltip: '审核结果',
            isDisabled:!rs,
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
		        fieldLabel: '船只编号',
		        name: 'routeNo'
		    },{
		        fieldLabel: '船舶名称',
		        labelWidth:60,
		        name: 'name'
		    },{
		        fieldLabel: '船主手机号',
		        name: ''
		    },{
		    	xtype:'fieldcontainer',
		    	layout:'hbox',
		    	width:90,
		    	items:[{
			    	xtype:'button',
			    	text:'搜索',
			    	margin:'0 6 0 0',
			    	cls:'r-btn-font-normal',
			    	rightId:Rich.V.record_selectCertificateAuditsByPage,
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
			    	rightId:Rich.V.record_selectCertificateAuditsByPage,
			    	handler:function(btn){
			    		this.up('form').getForm().reset();
			    	}
			    }]
		    }]
		}];
		
		var store = Ext.create('Ext.data.Store', {
			model:'Rich.model.RouteCheck',
	        pageSize: 50,
	        autoLoad:al,	        
	        proxy: {
		        type: 'ajax',
		        getMethod:function(){return "post"},		       
		       	url: Rich.Url.OwnerPaperPath,	
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
					loadMask: true,					
					padding:'0',
					id:'fujiashenhe',
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
					        { text: 'ID', dataIndex: 'id',flex: 1 },
					        { text: '船主名', dataIndex: 'userNa',flex: 1 },
					        { text: '证书类型',    dataIndex: 'typeDesc',flex: 1},
					        { text: '证件号码', dataIndex: 'number',flex: 1 },	
					        { text: '申请时间', dataIndex: 'createTime',flex: 1 },
					        { text: '认证状态',    dataIndex: 'resultDesc',flex: 1},
					        {
					        	text:'操作',
					            xtype:'actioncolumn',
					            width:120,
					            items:actionItems
					    	}]
					 },
					
					lookDetail:function(record){
						Ext.create('Rich.view.check.OwnerCheckWindow').showById(record.get('certificateId'),record.get('typeDesc'),record.get('id'));
				    },
				    
				    listeners:{
				    	"itemdblclick0":function(gd,record, item, index, e, eOpts){
				    		this.lookupI('grid').getStore().reload();
				    	}
				    },
				    dockedItems:itms
				}]
		});
		me.callParent(arguments);
	}
});

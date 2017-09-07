/**
 * 实名认证的main方法
 */
Ext.define('Rich.view.check.Main', {
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
        'Ext.form.FieldSet',
        'Ext.layout.component.FieldSet',
        'Ext.form.field.Checkbox',
        'Ext.toolbar.Paging',
        'Rich.view.check.AuthWindow',
        'Rich.view.check.CheckMgrWindow',
        'Rich.component.DicComboBox',
        'Ext.grid.feature.Grouping',
        'Rich.model.Tuser',
		'Ext.form.field.ComboBox'
    ],
    uses:['Rich.widget.CrudWindow'],
    extend: 'Ext.panel.Panel',
    alias:'widget.checkmain',
   
	initComponent:function(){		
		var me = this;
		var al = Rich.RightManager.hasRight(Rich.V.user_query_audit);//列表的权限
		var detail = Rich.RightManager.hasRight(Rich.V.user_detail);//能否看审核人的详情权限
		var rs = Rich.RightManager.hasRight(Rich.V.user_detail);//审核按钮能不能点击的权限
		var actionItems = [{
                icon: Rich.Url.icons_6,
                iconCls:'actioncolumn-margin',
                altText:'查看详情',
                tooltip: '查看详情',
                disabled:!detail,
                handler: function(gridview, rowIndex, colIndex) {
                    var rec = gridview.getStore().getAt(rowIndex);
                    this.up('gridpanel').lookDetail(rec);
                }
            },{
                icon: Rich.Url.icons_8,
                iconCls:'actioncolumn-margin',
                altText:'认证',
                tooltip: '认证',
                disabled:!rs,
                handler: function(gridview, rowIndex, colIndex) {
                    var rec = gridview.getStore().getAt(rowIndex);
                    this.up('gridpanel').toAuth(rec);
                }
            }];
       
		var itms = [{
	    	xtype:'form',
	    	style:{padding:"10px"},
	    	docked:'top',
	    	defaults:{
	    		xtype:'textfield',
	    		labelWidth:70,
	    		anchor:'100%',
	    		style:{float:"left",margin:"5px 0  5px 10px"}
	    	},
	    	layout:'auto',
		    items: [{
			        fieldLabel: '姓名',
			        name: 'name'
			    },{
			        fieldLabel: '昵称',
			        name: 'nickName'
			    },{
			        fieldLabel: '身份证号码',
			        name: 'idCard'
			    },{
			    	fieldLabel:'登录名',
			    	name:'userName'
			    },{
			    	fieldLabel:'手机号码',
			    	name:'phone'
			    },{
    	            xtype : 'fieldcontainer',
    	            fieldLabel : '申请时间',
    	            width:300,
    	            labelWidth:60, 
    	            defaults: {
    	                hideLabel: true
    	            },
    	            layout: 'hbox',
    	            items: [{
						xtype: 'datefield',
						flex:1,
					    anchor: '100%',
					  
					    name: 'createBegin',
					    format:'Y-m-d'
					},{xtype:'label',text:'-',style:{'line-height': '25px;'}},{
						xtype: 'datefield',
						flex:1,
					    anchor: '100%',
					    name: 'createEnd',
					    format:'Y-m-d'
					}]
    	        },{
			    	xtype:'diccombo',
			        fieldLabel: '认证状态',
			        itemId:'status',
			        name: 'authStatus',
			        editable:false,
			        typeName:'auth_status'
			    },{
			    	xtype:'button',
			    	text:'搜索',
			    	cls:'r-btn-font-normal',
			    	rightId:Rich.V.user_query_audit,
			    	handler:function(btn){
			    		var vs = this.up('form').getForm().getValues();
			    		console.log(vs);
			    		var sto = this.up('gridpanel').getStore();
			    		sto.getProxy().extraParams = vs;
			    		sto.loadPage(1);
			    	}
			    },{
			    	xtype:'button',
			    	text:'重置',
			    	cls:'r-btn-font-normal',
			    	rightId:Rich.V.user_query_audit,
			    	handler:function(btn){
			    		this.up('form').getForm().reset();
			    	}
			    }]
		}];
		
		var store = Ext.create('Ext.data.Store', {
			model:'Rich.model.Tuser',
	        pageSize: 50,
	        autoLoad:al,
	        proxy: {
		        type: 'ajax',
		        getMethod:function(){return "post"},
		        url: Rich.Url.tuserAllSearchPath,
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
		    	forceFit:true,
		    	store:store,
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
				        { text: '姓名', dataIndex: 'name',flex: 1 },
				        { text: '昵称', dataIndex: 'nickName',flex: 1 },
				        { text: '身份证号码', dataIndex: 'idCard',flex: 1 },
				        { text: '登录名', dataIndex: 'userName',flex: 1 },
				        { text: '手机号码', dataIndex: 'phone',flex: 1 },
				        { text: '微信号', dataIndex: 'wxNo',flex: 1 },
				        { text: '申请时间', dataIndex: 'applyTime',flex: 1 },
				        { text: '认证状态', dataIndex: 'authStatusDesc',flex: 1 },
				        {
				        	text:'操作',
				            xtype:'actioncolumn',
				            width:110,
				            items: actionItems
					      }
					]},
					lookDetail:function(record){
						Ext.create('Rich.view.check.CheckMgrWindow').showById(record.get('id'));
				    },
					
					toAuth:function(record){
				    	Ext.create('Rich.view.check.AuthWindow',{
				    		tuser:record
				    	}).showFor(this.authBack,this);
				    },
					authBack:function(f){
				    	this.lookupI('grid').getStore().reload();
				    },
			    dockedItems:itms
			}]
		});
		this.callParent(arguments);
	}
});

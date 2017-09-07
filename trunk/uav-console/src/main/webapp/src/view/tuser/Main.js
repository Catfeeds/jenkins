﻿/**
 * 行程的main  行程的总入口
 */
Ext.define('Rich.view.tuser.Main', {
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
        'Rich.model.Tuser',
        'Ext.form.FieldSet',
        'Ext.layout.component.FieldSet',
        'Ext.form.field.Checkbox',
        'Ext.toolbar.Paging',
        'Rich.view.tuser.UserForm',
        'Rich.view.tuser.CompanyForm',
        'Rich.view.tuser.AuthWindow',
        'Rich.widget.CrudWindow',
        'Rich.component.DicComboBox'
    ],
    extend: 'Ext.panel.Panel',
    alias:'widget.tusermain',
    type:null,
	initComponent:function(){
		var me = this;
		var type = me.getItemId();
		type = type == 'personal'?1:2;
		this.type = type;
		//var detail = Rich.RightManager.hasRight(Rich.V.journey_querySchedulePriceDetail);//详情权限
		//var al = Rich.RightManager.hasRight(Rich.V.journey_queryMegagameJourney);//分页权限
		var action = [{
            icon: 'resources/images/icons/6.png',
            iconCls:'actioncolumn-margin',
            altText:'查看详情',
            tooltip: '查看详情',
            //disabled:!detail,
            handler: function(gridview, rowIndex, colIndex) {
                var rec = gridview.getStore().getAt(rowIndex);
                this.up('gridpanel').lookDetail(rec);
            }
        },{
            icon: 'resources/images/icons/45.png',
            iconCls:'actioncolumn-margin',
            altText:'审核',
            tooltip: '审核',
            //disabled:!detail,
            handler: function(gridview, rowIndex, colIndex) {
                var rec = gridview.getStore().getAt(rowIndex);
                this.up('gridpanel').toAuth(rec);
            }
        }]
		var store = Ext.create('Ext.data.Store', {
			model:'Rich.model.Tuser',
	        pageSize: 50,
	        autoLoad:true,
	        proxy: {
		        type: 'ajax',
		        getMethod:function(){return "get"},
		        url: Rich.Url.gerenPath,
		        reader: {
		            type: 'json',
		            root: 'data.pageData',
		            totalProperty: 'data.totalRows',
		            idProperty: 'id'
		        }
		    }
		});
		
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
		        fieldLabel: '序号',
		        name: 'id'
		    },{
		        fieldLabel: '用户名',
		        name: 'username'
		    },{
		        fieldLabel: '手机号',
		        name: 'phone'
		    },
		    /*{
	            fieldLabel : '用户状态',
				xtype:'diccombo',
				//hidden:true,
		       // url:Rich.Url.wharfAllPath,
		        itemId:'6',
		        flex:1,
		        name: 'wharf',
		        editable:false,
		        typeName:'wharf'
	        },{
	            fieldLabel : '认证状态',
				xtype:'diccombo',
				hidden:true,
		       // url:Rich.Url.wharfAllPath,
		        itemId:'wharf',
		        flex:1,
		        name: 'wharf',
		        editable:false,
		        typeName:'wharf'
		    },*/
		    	{
	            xtype      : 'fieldcontainer',
	            fieldLabel : '创建时间',
	            width:300,
	            labelWidth:60,
	            layout: 'hbox',
	            items: [{
					xtype: 'datefield',
					flex:1,
				    anchor: '100%',
				    name: 'createTime_begin',
				    format:'Y-m-d'
				},{xtype:'label',text:'-',style:{'line-height': '25px;'}},{
					xtype: 'datefield',
					flex:1,
				    anchor: '100%',
				    name: 'createTime_end',
				    format:'Y-m-d'
				}]
	        },{
		    	xtype:'button',
		    	text:'重置',
		    	cls:'r-btn-font-normal',
		    	style:{float:"right",margin:"5px 10px 5px 0"},
		    	rightId:Rich.V.journey_queryMegagameJourney,
		    	handler:function(btn){
		    		this.up('form').getForm().reset();
		    	}
		    },{
		    	xtype:'button',
		    	text:'搜索',
		    	cls:'r-btn-font-normal',
		    	style:{float:"right",margin:"5px 10px 5px 10px"},
		    	rightId:Rich.V.journey_queryMegagameJourney,
		    	handler:function(btn){
		    		var vs = this.up('form').getForm().getValues();
		    		var sto = this.up('gridpanel').getStore();
		    		sto.getProxy().extraParams = vs;
		    		sto.loadPage(1);
		    	}
		    }]
		}];
		
		if(type == 1){
			var its = [
				{ text: '序号', dataIndex: 'id',flex: 1 },
		        { text: '用户名', dataIndex: 'username',flex: 1 },
		        { text: '姓名', dataIndex: 'name',flex: 1 },
		        { text: '身份证号', dataIndex: 'idCard',flex: 1 },
		       	{ text: '电话号码', dataIndex: 'phone',flex: 1},
		        { text: '用户状态', dataIndex: 'statu',width:140 },
		        { text: '认证状态', dataIndex: 'auth_status',width:140 },
		        { text: '注册时间', dataIndex: 'createTime',width:140 }
		        ]
		}else{
			var its = [
				{ text: '序号', dataIndex: 'id',flex: 1 },
		        { text: '用户名', dataIndex: 'username',flex: 1 },
		        { text: '姓名', dataIndex: 'companyName',flex: 1 },
		        { text: '营业执照', dataIndex: 'licenseNumber',flex: 1 },
		       	{ text: '电话号码', dataIndex: 'conactPhone',flex: 1},
		        { text: '用户状态', dataIndex: 'statu',width:140 },
		        { text: '认证状态', dataIndex: 'auth_status',width:140 },
		        { text: '注册时间', dataIndex: 'createTime',width:140 }
		        ]
		}
		
		its.push({
        	text:'管理',
            xtype:'actioncolumn',
            items: action
		});
		
		Ext.apply(me,{
			layout:'fit',
			items:[{
				xtype:'gridpanel',
				itemId:'list',
				loadMask: true,					
				padding:'0',
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
				   	items:its
				   	},
				 lookDetail:function(record){
				 	var aa = this.up('tusermain').type;
				 	if(aa == 1){
				 		Ext.create('Rich.widget.CrudWindow',{
							title:'个人用户详情',
							width:0.6,
							height:0.8,
							crudForm:{
								xtype:'userform',
								buttons:['u']
							}
						}).showByParams({id:record.get('id')},this.upStateBack,this);
				 	}else{
				 		Ext.create('Rich.widget.CrudWindow',{
							title:'企业用户详情',
							width:0.6,
							height:0.8,
							crudForm:{
								xtype:'companyform',
								buttons:['u']
							}
						}).showByParams({id:record.get('id')},this.upStateBack,this);
				 	}
					/*Ext.create('Rich.view.game.GameDetWindow').showByJourneyId(
					record.get('id'),record.get('fromType'),record.get('state'),ty);
					*/
			    },
			    
			    upStateBack:function(o,f,r){
			    	if(f){
			    		this.getStore().reload();
			    	}
			    },
			    toAuth:function(record){
			    	Ext.create('Rich.view.tuser.AuthWindow',{
			    		tuser:record
			    	}).showFor(this.authBack,this);
				 },
				    
			    listeners:{
			    	"itemdblclick0":function(gd,record, item, index, e, eOpts){
			    		this.lookDetail(record);
			    	}
			    },
			    dockedItems:itms
			}]
		});
		this.callParent(arguments);
	}
});

﻿/**
 * 个人用户的main
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
        'Rich.model.Companys',
        'Ext.form.FieldSet',
        'Ext.form.field.Checkbox',
        'Rich.view.tuser.AuthWindow',
        'Rich.view.tuser.UserDetailWindow',
        'Rich.widget.CrudWindow',
        'Rich.component.DicComboBox'
    ],
    extend: 'Ext.panel.Panel',
    alias:'widget.tusermain',
    type:null,
    ty:null,
	initComponent:function(){
		var me = this;
		var Udetail = Rich.RightManager.hasRight(Rich.V.users_detail);//详情权限
		var Ucheck = Rich.RightManager.hasRight(Rich.V.user_userId_status);//禁启用权限
		var pass = Rich.RightManager.hasRight(Rich.V.user_reset);//改密码权限
		var action = [{
            icon: Rich.Url.icons_6,
            iconCls:'actioncolumn-margin',
            altText:'查看详情',
            tooltip: '查看详情',
            text:'详细',
            disabled:!Udetail,
            handler: function(gridview, rowIndex, colIndex) {
                var rec = gridview.getStore().getAt(rowIndex);
                this.up('gridpanel').lookDetail(rec);
            }
	     },{
            altText:'启用/禁用',
            tooltip: '启用/禁用',
            disabled:!Ucheck,
            handler: function(gridview, rowIndex, colIndex) {
                var rec = gridview.getStore().getAt(rowIndex);
                this.up('gridpanel').upDataStatu(rec);
            },
            getClass:function(metadata,rowIndex,colIndex,store){
            	var st = colIndex.data.status
            	if(st == 1){
            		return 'actioncolumn-img16';
            	}else{
            		return 'actioncolumn-img8';
            		
            	}
            }
        },{
            icon:Rich.Url.icons_1,
            iconCls:'actioncolumn-margin',
            altText:'重置密码',
            tooltip: '重置密码',
            disabled:!pass,
            handler: function(gridview, rowIndex, colIndex) {
                var rec = gridview.getStore().getAt(rowIndex);
                this.up('gridpanel').restPassword(rec);
            }
        }]
        
        var states = Ext.create('Ext.data.Store', {
		    fields: ['abbr', 'name'],
		    data : [
		        {'abbr':"2", 'name':"不可用"},
		        {'abbr':"1", 'name':"可用"}
		    ]
		});
        
		var state1 = Ext.create('Ext.data.Store', {
		    fields: ['abbr', 'name'],
		    data : [
		        {'abbr':"1", 'name':"未认证"},
		        {'abbr':"2", 'name':"待认证"},
		        {'abbr':"3", 'name':"已认证"},
		        {'abbr':"4", 'name':"认证失败"}
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
					xtype:'textfield',
					fieldLabel:'姓名',
					name:'name'
				},{
			    	xtype:'combobox',
			    	fieldLabel:'用户状态',
			    	store: states,
				    queryMode: 'local',
				    displayField: 'name',
				    valueField: 'abbr',
			    	name:'status'
			    },{
			    	xtype:'combobox',
			    	fieldLabel:'认证状态',
			    	store: state1,
				    queryMode: 'local',
				    displayField: 'name',
				    valueField: 'abbr',
			    	name:'authStatus'
			    },{
		            xtype: 'fieldcontainer',
		            fieldLabel : '注册时间',
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
			    	cls:'r-btn-font-normal',
			    	style:{float:"right",margin:"5px 10px 5px 0"},
			    	handler:function(btn){
			    		this.up('form').getForm().reset();
			    	}
			    },{
			    	xtype:'button',
			    	text:'搜索',
			    	cls:'r-btn-font-normal',
			    	style:{float:"right",margin:"5px 10px 5px 10px"},
			    	handler:function(btn){
			    		var vs = this.up('form').getForm().getValues();
			    		var sto = this.up('gridpanel').getStore();
			    		sto.getProxy().extraParams = vs;
			    		sto.loadPage(1);
			    	}
			    }]
		}];
		var store = Ext.create('Ext.data.Store', {
			model:'Rich.model.Companys',
	        pageSize: 50,
	        autoLoad:true,
	        proxy: {
		        type: 'ajax',
		        getMethod:function(){return "get"},
		        url:Rich.Url.personalPath,
		        reader: {
		            type: 'json',
		            root: 'data.content',
		            totalProperty: 'data.totalElements',
		            idProperty: 'id'
		        }
		    }
		    
		});
		Ext.apply(me,{
			layout:'fit',
			items:[{
				margin:'5 0 0 0',
				xtype:'gridpanel',
				itemId:'list',
				id:'use',
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
		        viewConfig:{
				    stripeRows:true,//在表格中显示斑马线
				    enableTextSelection:true //可以复制单元格文字
				},
			   	columnLines: true,
			   	columns: {
				   	defaults:{
				        sortable:false,
				        draggable:false//,
				        //enableColumnHide:false,
				        //menuDisabled:true
				   	},
				   	items:[
				        { text: '用户名', dataIndex: 'userName',flex: 1 },
				        { text: '姓名', dataIndex: 'name',flex: 1 },
				        { text: '身份证号', dataIndex: 'idCard',flex: 1 },
				       	{ text: '电话号码', dataIndex: 'phone',flex: 1},
				        { text: '用户状态', dataIndex: 'status',width:140,renderer:function(v){
				        	if(v == 1){
				        		return '可用';
				        	}else{
				        		return '不可用';
				        	}
				        }},
				        { text: '认证状态', dataIndex: 'authStatus',width:140,renderer:function(v){
				        	if(v == 1){
				        		return '未认证';
				        	}else if( v == 2){
				        		return '待认证';
				        	}else if(v == 3){
				        		return '已认证';
				        	}else{
				        		return '认证失败';
				        	}
				        }},
				        { text: '注册时间', dataIndex: 'createTime',width:140,renderer:function(v){
				        	if(v != null && v != ''){
				       			var date = new Date(v);
								Y = date.getFullYear() + '-';
								M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
								D = date.getDate() + ' ';
								h = date.getHours() + ':';
								m = date.getMinutes();
				       			return Y+M+D+h+m
				       		}}
				        },{
				        	text:'管理',
				            xtype:'actioncolumn',
				            items: action
						}
				  	 ]},
				 lookDetail:function(record){
				 	Ext.create('Rich.view.tuser.UserDetailWindow',{
				 		rec:record.raw
				 	}).showFor(this.upStateBack,this);
			    },
			    
			    upDataStatu:function(rec){
			    	var ty = rec.get('status');//1启用 2禁用
			    	var msg;
			    	if(ty == 1){
			    		msg = '你确定禁用吗?';
			    		this.ty = 0;
			    	}else{
			    		msg = '你确定启用吗?';
			    		this.ty = 1;
			    	}
					Ext.Msg.confirm('提示',msg,function(btn){
			        	if (btn == 'yes'){
		        			Rich.JsonAjax.request({
			        			method:'PUT',
				        		url:Rich.Url.personChePath+rec.data.id+'/status',
				        		params:{
				        			status:this.ty
				        		},
				        		callback:this.upStateBack,
				        		scope:this
			        		})
			        	}
		    		},this);
			    },
				restPassword:function(rec){
					Ext.Msg.prompt('重置密码','新密码:',function(btn,text){
						if(text && text !=''){
			        		if (btn == 'ok'){
			        			var vas = {};
			        			vas.password = text;
			        			Rich.JsonAjax.request({
				        			method:'PUT',
					        		url:Rich.Url.personChePath+rec.get('id')+'/reset',
					        		params:vas,
					        		callback:this.upStateBack,
					        		scope:this
				        		})
			        		}
			        	}
		    		},this);
				},
				 upStateBack:function(o,f,r){
				 	//debugger
				 	if(f){
				 		Rich.Msg.alert('消息','修改成功');
				 		this.getStore().reload();
				 	}
				},
				dockedItems:itms
			}]
		});
		this.callParent(arguments);
	}
});

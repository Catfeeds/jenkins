/**
 * 平台内容管理
 */
Ext.define('Rich.view.tickling.TerraceMgr', {
	requires:[
		'Rich.widget.Message',
		'Ext.grid.Panel',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.field.Trigger',
        'Ext.form.field.ComboBox',
        'Ext.grid.column.Action',
        'Rich.model.Content',
        'Ext.form.FieldSet',
        'Ext.layout.component.FieldSet',
        'Ext.form.field.Checkbox',
        'Rich.view.tickling.TerraceTree',
        'Rich.view.tickling.TerraceDetail',
        'Rich.view.tickling.TerraceAddPlate',
        'Rich.component.DicComboBox'
    ],
    uses:'Rich.view.sys.OrgSelectorWindow',
    extend: 'Ext.panel.Panel',
    alias:'widget.terracemgr',
    
    getCrudForms:function(){
    	return this.lookupI('crudforms');
    },
	initComponent:function(){
		var me = this;
		var ul = me.ul;
		var detail = Rich.RightManager.hasRight(Rich.V.portal_page_pageid);//详情权限
		var al = Rich.RightManager.hasRight(Rich.V.portal_menus_menuid_content);//分页权限
		var del = Rich.RightManager.hasRight(Rich.V.portal_page_delete);//详情权限
		var actionItems = [{
            icon:Rich.Url.icons_6,
            altText:'查看',
            tooltip: '查看',
            disabled:!detail,
            handler: function(grid, rowIndex, colIndex) {
            	var rec = grid.getStore().getAt(rowIndex);
            	var a  = rec.get('id');
            	Ext.create('Rich.view.tickling.TerraceDetail').showById(a,1);
            }
        },'',{
            icon: Rich.Url.icons_2,
            text:'删除',
            tooltip: '删除',
            disabled:!del,
            handler: function(grid, rowIndex, colIndex) {
                var rec = grid.getStore().getAt(rowIndex);
                this.up('gridpanel').doUpdateState(rec);
            }
        }];
		var store = Ext.create('Ext.data.Store', {
			model:'Rich.model.Content',
	        pageSize: 50,
	        autoLoad:al,
	        proxy: {
		        type: 'ajax',
		        getMethod:function(){return "get"},
		        url:Rich.Url.portalMenusPath,
		        reader: {
		            type: 'json',
		            root: 'data.content',
		            totalProperty: 'data.totalRows'
		        }
		    }
		});
		Ext.apply(me,{
			layout: {
		        type: 'hbox',
		        align: 'stretch'
		    },
			items:[{
				xtype:'terracetree',
				itemId:'terracetree',
				frame:true,
				title:'菜单',
				margin: '10 10 10 10',
				flex:2,
				tools:[{
				    type:'refresh',
				    tooltip: '刷新菜单',
				    handler: function(event, toolEl, panelHeader) {
				        this.up('terracetree').refresh();
				    }
				}],
				listeners:{ 
	                'select':function(me0, record, index, eOpts){
	                	var id = record.data.id;
	                	var ty = record.data.type;
	                	if(record.get('type') == '2'){
	                		Ext.create('Rich.view.tickling.TerraceDetail').showById(id,ty);
	                	}else if(record.get('type') == '1'){
	                		var gid = record.get('id');
	                		var gName = record.get('name');
	                		var url = Rich.Url.portalMenusPath+gid+'/content';
	                		var grid = this.up('terracemgr').lookupI('list');
	                		var sto = grid.getStore();
	                		sto.getProxy().url=url;
	                		//sto.gid = gid;
	                		
	                		var btn = grid.lookupI("addPaperBtn",true);
				    		sto.loadPage(1,{
							    scope: btn,
							    gid:gid,
							    gName:gName,
							    callback: function(records, operation, success) {
							        if(success){
							        	this.gid = operation.gid;
							        	this.gName = operation.gName;
							        	this.up('gridpanel').setTitle("详情  > "+operation.gName);
							        }
							    }
							});
	                	}
                		
					}
            	}
			},{
				xtype:'panel',
				itemId:'crudforms',
				frame:true,
				margin: '10 10 10 0',
				layout:'card',
				flex:5,
				defaults:{
					xtype:'form',
					bodyStyle:'padding-top:10px',
					padding:'10 10 10 10',
					trackResetOnLoad:true
				},
				items:[{
					xtype:'gridpanel',
					itemId:'list',
					id:'lt',
					title:'详请',
					padding:'0',
			    	forceFit:true,
			    	selType: 'checkboxmodel',
			    	store:store,
				   	columnLines: true,
				   	columns: {
					   	defaults:{
					        sortable:false,
					        draggable:false,
					        enableColumnHide:false,
					        menuDisabled:true
					   	},
					   	items:[
					        {text: '标题',dataIndex:'title',flex:1.5},
			             	{text: '排序', dataIndex: 'sortOrder',width: 100},
			             	{text: '是否是父类', dataIndex: 'isParent',width: 100,renderer:function(v){
					       		if(v == 0){
					       			return '不是'
					       		}else if(v == 1){
					       			return '是'
					       		}
					       	}},
					       	{text: '状态', dataIndex: 'status',flex: 1,renderer:function(v){
					       		if(v == 0){
					       			return '禁用'
					       		}else if(v == 1){
					       			return '正常'
					       		}else{
					       			return '删除'
					       		}
					       	}},
			             	{text: '创建人', dataIndex: 'creator',flex: 1},
			             	{text: '创建时间', dataIndex: 'createTime',flex: 1.5,renderer:function(v){
					       		if(v != null && v != ''){
					       			var date = new Date(v);
									Y = date.getFullYear() + '-';
									M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
									D = date.getDate() + ' ';
									h = date.getHours() + ':';
									m = date.getMinutes();
					       			return Y+M+D+h+m
					       		}
					       	}},
					        {
					        	text:'操作',
					            xtype:'actioncolumn',
					            width:70,
					            items:actionItems
					        }
					]},
					doUpdateState:function(recs){
						var msg = '你确定删除吗?';
						Ext.Msg.confirm('提示',msg,function(btn){
			        	if (btn == 'yes'){
				        		this.UpdateState(recs);
				        	}
			    		},this);
			    	},
				    UpdateState:function(rec){
		        		Rich.JsonAjax.request({
		        			method:'DELETE',
			        		url:Rich.Url.portaldeelPath+rec.data.id,
			        		callback:this.upStateBack,
			        		scope:this
		        		});
				    },
			    
				     upStateBack:function(o,f,r){
				    	this.el.unmask();
	    	        	if(f){
	    	        		Rich.Msg.alert('消息','删除成功');
	    	        		this.getStore().reload();
	    	        	}
				    },
				    refresh:function(){
				    	if(this.orgId){
				    		this.getOwnerCt('crudforms').doRequest('list',{'orgId':this.orgId,'orgName':this.orgName});
				    	}
				    },
			    dockedItems:[{
			    	xtype:'toolbar',
			    	ui:'footer',
			    	items:[{
				    	text:'新增稿件',
				    	itemId:'addPaperBtn',
				    	rightId:Rich.V.portal_page,
				    	handler:function(btn){
				    		if(btn.gid){
				    			var tit = "新增稿件 > "+btn.gName;
				    			Ext.create('Rich.view.tickling.TerraceAddPlate',{
				    				title:tit
				    			}).showById(btn.gid);
				    		}else{
				    			Rich.Msg.alert('提示','请选择');
				    		}
				    		
				    		
				    	}
				    }]
				}]
				}]
			}]
		});
		this.callParent(arguments);
	}
});

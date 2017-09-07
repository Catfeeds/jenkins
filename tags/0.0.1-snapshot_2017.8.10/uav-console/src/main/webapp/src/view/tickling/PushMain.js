/**
 * 消息推送main
 */
Ext.define('Rich.view.tickling.PushMain', {
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
        'Rich.view.channel.ChannelForm',
        'Rich.view.channel.ChannelDetWindow',
        'Rich.component.DicComboBox',
        'Ext.grid.feature.Grouping',
        'Rich.model.Push',
		'Ext.form.field.ComboBox'
    ],
    uses:['Rich.widget.CrudWindow'],
    extend: 'Ext.panel.Panel',
    alias:'widget.ticklingpushmain',
   
	initComponent:function(){		
		var me = this;
		var actionItems = [{
                icon: Rich.Url.icons_8,
                iconCls:'actioncolumn-margin',
                altText:'开启',
                tooltip: '开启',
                handler: function(gridview, rowIndex, colIndex) {
                    var rec = gridview.getStore().getAt(rowIndex);
                    this.up('gridpanel').doUpdateState(rec);
                }
            },{
                icon: Rich.Url.icons_16,
                iconCls:'actioncolumn-margin',
                altText:'失效',
                tooltip: '失效',
                handler: function(gridview, rowIndex, colIndex) {
                    var rec = gridview.getStore().getAt(rowIndex);
                    this.up('gridpanel').toUpdateState(rec);
                }
            }];
       
		var itms = [{
	    	xtype:'form',
	    	style:{padding:"10px"},
	    	docked:'top',
	    	defaults:{
	    		xtype:'textfield',
	    		anchor:'100%',
	    		style:{float:"left",margin:"5px 0  5px 10px"}
	    	},
	    	layout:'auto',//switch_type
		    items: [{
			        fieldLabel: '标题',
	    			labelWidth:50,
			        name: 'confName'
			    },{
			    	xtype:'diccombo',
			        fieldLabel: '启用状态',
			        itemId:'confValue',
			        name: 'confValue',
			        editable:false,
			        typeName:'switch_type'
			    },{
			    	xtype:'diccombo',
			        fieldLabel: '推送类型',
			        itemId:'confType',
			        name: 'confType',
			        editable:false,
			        typeName:'push_conf_type'
			    },{
			    	xtype:'button',
			    	text:'搜索',
			    	cls:'r-btn-font-normal',
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
			    	handler:function(btn){
			    		this.up('form').getForm().reset();
			    	}
			    }]
		}];
		
		var store = Ext.create('Ext.data.Store', {
			model:'Rich.model.Push',
	        pageSize: 50,
	        autoLoad:true,
	        proxy: {
		        type: 'ajax',
		        getMethod:function(){return "post"},
		        url: Rich.Url.PushPagePath,
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
				        { text: 'ID', dataIndex: 'id',width: 80 },
				        { text: '推送方式', dataIndex: 'confTypeDesc',width: 200 },
				        { text: '标题', dataIndex: 'confName',width: 300 },
				        { text: '内容', dataIndex: 'confDesc',flex: 1 },
				        { text: '推送开关', dataIndex: 'confValueDesc',width: 200 },
				        {
				        	text:'操作',
				            xtype:'actioncolumn',
				            width:150,
				            items: actionItems
					      }
					]},
					
					doUpdateState:function(record){
						Ext.Msg.confirm('提示','确定要启用这条推送?',function(btn){
				        	if (btn == 'yes'){
				        		var cf = record.get('confValue');
				        		if(cf != 1){
					        		Rich.JsonAjax.request({
			    	        			method:'post',
				    	        		url:Rich.Url.PushUsedPath,
				    	        		params:{
				    	        			ids:record.get('id'),
				    	        			value:1
							        	},
				    	        		callback:this.authBack,
				    	        		scope:this
			    	        		});
					        	}else{
					        		Rich.Msg.error('错误','这条推送已启用，无需重复操作!');
					        	}
				        	}
				    	},this);
					},
					
					toUpdateState:function(record){
						Ext.Msg.confirm('提示','确定要禁用这条推送?',function(btn){
				        	if (btn == 'yes'){
				        		var cf = record.get('confValue');
				        		if(cf != 0){
				        			Rich.JsonAjax.request({
			    	        			method:'post',
				    	        		url:Rich.Url.PushUsedPath,
				    	        		params:{
				    	        			ids:record.get('id'),
				    	        			value:0
							        	},
				    	        		callback:this.authBack,
				    	        		scope:this
		    	        			});
				        		}else{
				        			Rich.Msg.error('错误','这条推送已禁用，无需重复操作!');
				        		}
				        	}
				    	},this);
					},
					
					authBack:function(f){
						if(f){
							this.up('pushmain').lookupI('grid').getStore().reload();
						}
				    },
			    dockedItems:itms
			}]
		});
		this.callParent(arguments);
	}
});

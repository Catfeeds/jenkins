/**
 * 系统消息main
 */
Ext.define('Rich.view.leavemessage.SysMain', {
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
        'Rich.model.SysNews',
        'Ext.form.field.File',
        'Ext.form.FieldSet',
        'Ext.form.field.HtmlEditor',
        'Ext.layout.component.FieldSet',
        'Rich.view.leavemessage.SysDetail',
        'Rich.view.leavemessage.SysAdd',
        'Ext.form.field.Checkbox',
        'Ext.toolbar.Paging'
    ],
   
    uses:['Rich.widget.CrudWindow'],
    extend: 'Ext.panel.Panel',
    alias:'widget.leavemessagesysMain是',
     getShipId:function(){
    	return this.ship.get('id');
    },
     
	initComponent:function(){
		var me = this;
		var detail = Rich.RightManager.hasRight(Rich.V.announcement_announcementid);//详情权限
		var del = Rich.RightManager.hasRight(Rich.V.Delannouncement_announcementid);//删除权限
		var al = Rich.RightManager.hasRight(Rich.V.announcements);
		var actionItems = [];
		actionItems = actionItems.concat([{
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
            icon: Rich.Url.icons_2,
            iconCls:'actioncolumn-margin',
            altText:'删除',
            tooltip: '删除',
            disabled:!del,
            handler: function(gridview, rowIndex, colIndex) {
                var rec = gridview.getStore().getAt(rowIndex);
                this.up('gridpanel').removeMem(rec);
            }
        }]);
        
		var store = Ext.create('Ext.data.Store', {
			model:'Rich.model.SysNews',
	        pageSize: 50,
	        autoLoad:al,	        
	        proxy: {
		        type: 'ajax',
		        getMethod:function(){return "get"},		       
		       	url: Rich.Url.sysPagePath,	
		        reader: {
		            type: 'json',
		            root: 'data.content',
		            totalProperty: 'data.totalElements',
		            idProperty: 'id'
		        }
		    }
		});
		
        var states = Ext.create('Ext.data.Store', {
		    fields: ['abbr', 'name'],
		    data : [
		        {'abbr':"0", 'name':"未读取"},
		        {'abbr':"1", 'name':"读取"}
		    ]
		});
		
		var itms = [/*{
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
		    	xtype:'combobox',
		    	fieldLabel:'读取状态',
		    	store: states,
			    queryMode: 'local',
			    displayField: 'name',
			    valueField: 'abbr',
		    	name:'status'
		    },{
	            xtype: 'fieldcontainer',
	            fieldLabel : '留言日期',
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
		}*/];
		itms.unshift({
			xtype:'toolbar',
			ui:'footer',
			items:[{
				xtype:'button',
				text:'添加公告',
				rightId:Rich.V.Addannouncement,
				handler:function(btn){
					Ext.create('Rich.view.leavemessage.SysAdd').showById(11);
				}
			}]
		});
		Ext.apply(me,{
			layout:'fit',
			items:[{
				xtype:'gridpanel',
				itemId:'grid',
				loadMask: true,					
				id:'sys',
				bodyStyle:'',
				selModel:{selType:'checkboxmodel',mode:'MULTI'},
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
				        { text: '标题', dataIndex: 'title',flex: 1 },
				        { text: '子标题', dataIndex: 'subTitle',flex: 1 },
				        { text: '查看次数', dataIndex: 'checkNumber',flex: 1},
				        { text: '创建人',    dataIndex: 'creator',flex: 1 },
				        { text: '创建时间',    dataIndex: 'createTime',flex: 1,renderer:function(v){
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
				            width:110,
				            items: actionItems
				        }
				]},
				lookDetail:function(record){
					Ext.create('Rich.view.leavemessage.SysDetail').showById(record.get('id'));
				 },
			    removeMem:function(rec){
					if(rec){
					Ext.Msg.confirm('删除','确定删除这条公告吗?',function(btn){
					    if (btn == 'yes'){
					        this.el.mask('删除中...');
	    	        		Rich.JsonAjax.request({
	    	        			method:'DELETE',
		    	        		url:Rich.Url.sysUpdatePath+rec.get('id'),
		    	        		callback:this.removeBack,
		    	        		scope:this
	    	        		});
					    }
					},this);
						
					}
			    },
			    removeBack:function(o,f,r){
    	        	this.el.unmask();
    	        	if(f){
    	        		Rich.Msg.alert('消息','删除成功！');
    	        		this.getStore().reload();
    	        	}
    	        },
    	        listeners:{
			    	"itemdblclick0":function(gd,record, item, index, e, eOpts){
			    		this.lookDetail(record.get('userName'));
			    	}
			    },
			   dockedItems:itms
			}]
		});
		me.callParent(arguments);
	}
});

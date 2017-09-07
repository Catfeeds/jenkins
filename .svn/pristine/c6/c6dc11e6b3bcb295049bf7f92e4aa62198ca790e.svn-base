/**
 * 留言main
 */
Ext.define('Rich.view.leavemessage.Main', {
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
        'Rich.model.Tickling',
        'Ext.form.field.File',
        'Ext.form.FieldSet',
        'Ext.form.field.HtmlEditor',
        'Ext.layout.component.FieldSet',
        'Rich.view.leavemessage.MessageDetail',
        'Ext.form.field.Checkbox',
        'Ext.toolbar.Paging'
    ],
   
    uses:['Rich.widget.CrudWindow'],
    extend: 'Ext.panel.Panel',
    alias:'widget.leavemessagemain',
   
	initComponent:function(){
		var me = this;
		/*var detail = Rich.RightManager.hasRight(Rich.V.advert_banner_queryDetail);//详情权限
		var used = Rich.RightManager.hasRight(Rich.V.advert_banner_acfor);//启用/禁用权限
		var del = Rich.RightManager.hasRight(Rich.V.advert_banner_del);//删除权限
		var al = Rich.RightManager.hasRight(Rich.V.advert_banner_page);*/
		var actionItems = [];
		actionItems = actionItems.concat([{
            icon: Rich.Url.icons_6,
            iconCls:'actioncolumn-margin',
            altText:'详情',
            tooltip: '详情',
            handler: function(gridview, rowIndex, colIndex) {
                var rec = gridview.getStore().getAt(rowIndex);
                this.up('gridpanel').lookDetail(rec);
            }
		},{
            icon: Rich.Url.icons_47,
            iconCls:'actioncolumn-margin',
            altText:'已读',
            tooltip: '已读',
            handler: function(gridview, rowIndex, colIndex) {
                var rec = gridview.getStore().getAt(rowIndex);
                this.up('gridpanel').hadRead(rec);
            }
        },{
            icon: Rich.Url.icons_2,
            iconCls:'actioncolumn-margin',
            altText:'删除',
            tooltip: '删除',
            handler: function(gridview, rowIndex, colIndex) {
                var rec = gridview.getStore().getAt(rowIndex);
                this.up('gridpanel').removeMem(rec);
            }
        },{
            icon: Rich.Url.icons_49,
            iconCls:'actioncolumn-margin',
            altText:'回复',
            tooltip: '回复',
            handler: function(gridview, rowIndex, colIndex) {
                var rec = gridview.getStore().getAt(rowIndex);
                this.up('gridpanel').replyMail(rec);
            }
        }]);
        
		var store = Ext.create('Ext.data.Store', {
			model:'Rich.model.Tickling',
	        pageSize: 50,
	        autoLoad:true,	        
	        proxy: {
		        type: 'ajax',
		        getMethod:function(){return "get"},		       
		       	url: Rich.Url.leaveMessagePath,	
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
		        {'abbr':"1", 'name':"未读取"},
		        {'abbr':"0", 'name':"读取"}
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
		}];
		
		Ext.apply(me,{
			layout:'fit',
			items:[{
				xtype:'gridpanel',
				itemId:'grid',
				loadMask: true,					
				padding:'0',
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
				        { text: '留言人', dataIndex: 'name',flex: 1 },
				        { text: '手机号', dataIndex: 'phone',flex: 1 },
				        { text: '留言人邮箱', dataIndex: 'email',flex: 1},
				        { text: '评论内容', dataIndex: 'content',flex: 1},
				        { text: '留言日期',    dataIndex: 'createTime',flex: 1,renderer:function(v){
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
				        { text: '状态',    dataIndex: 'status',flex: 1,renderer:function(v){
				        	if(v == 0){
				        		return '已读';
				        	}else if(v == 1){
				        		return '未读';
				        	}else{
				        		return '已删除';
				        	}
				        }},{
				        	text:'操作',
				            xtype:'actioncolumn',
				            width:110,
				            items: actionItems
				        }
				]},
				
				lookDetail:function(rec){
					Ext.create('Rich.view.leavemessage.MessageDetail').showById(rec.data);
				},
				
			    removeMem:function(rec){
					Ext.Msg.confirm('删除','确定删除这条留言吗?',function(btn){
					    if (btn == 'yes'){
					        this.el.mask('删除中...');
	    	        		Rich.JsonAjax.request({
	    	        			method:'DELETE',
		    	        		url:Rich.Url.messageDeletePath+rec.get('id'),
		    	        		callback:this.removeBack,
		    	        		scope:this
	    	        		});
					    }
					},this);
			    },
			    
			    replyMail:function(rec){
			    	var mail = rec.data.email
			    	var url = 'mailto:'+mail;
			    	window.location = url;
			    },
			    
			    hadRead:function(rec){
					if(rec){
						Ext.Msg.confirm('提示','是否标记为已读?',function(btn){
						    if (btn == 'yes'){
		    	        		Rich.JsonAjax.request({
		    	        			method:'put',
			    	        		url:Rich.Url.messageDeletePath+rec.get('id'),
			    	        		callback:this.readBack,
			    	        		scope:this
		    	        		});
						    }
						},this);
					}
			    },
			    readBack:function(o,f,r){
    	        	this.el.unmask();
    	        	if(f){
    	        		Rich.Msg.alert('消息','操作成功！');
    	        		this.getStore().reload();
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

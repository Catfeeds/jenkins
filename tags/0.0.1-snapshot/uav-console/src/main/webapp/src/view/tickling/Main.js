/**
 * 广告轮播图
 */
Ext.define('Rich.view.tickling.Main', {
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
        'Rich.model.Banner',
        'Ext.form.field.File',
        'Ext.form.FieldSet',
        'Ext.layout.component.FieldSet',
        'Ext.form.field.Checkbox',
        'Ext.toolbar.Paging'
    ],
   
    uses:['Rich.widget.CrudWindow'],
    extend: 'Ext.panel.Panel',
    alias:'widget.ticklingmain',
    
     getShipId:function(){
    	return this.ship.get('id');
    },
     
	initComponent:function(){
		var me = this;
		/*var detail = Rich.RightManager.hasRight(Rich.V.advert_banner_queryDetail);//详情权限
		var used = Rich.RightManager.hasRight(Rich.V.advert_banner_acfor);//启用/禁用权限
		var del = Rich.RightManager.hasRight(Rich.V.advert_banner_del);//删除权限
		var al = Rich.RightManager.hasRight(Rich.V.advert_banner_page);*/
		var actionItems = [];
		actionItems = actionItems.concat([{
            icon:  Rich.Url.icons_6,
            iconCls:'actioncolumn-margin',
            altText:'详情',
            tooltip: '详情',
            disabled:!detail,
            handler: function(gridview, rowIndex, colIndex) {
                var rec = gridview.getStore().getAt(rowIndex);
                this.up('gridpanel').lookDetail(rec);
            }
        },{
            icon: Rich.Url.icons_8,
            iconCls:'actioncolumn-margin',
            altText:'启用',
            tooltip: '启用',
            disabled:!used,
            handler: function(gridview, rowIndex, colIndex) {
                var rec = gridview.getStore().getAt(rowIndex);
                this.up('gridpanel').doChangeStar(rec);
            }
        },{
            icon: Rich.Url.icons_16,
            iconCls:'actioncolumn-margin',
            altText:'失效',
            tooltip: '失效',
            disabled:!used,
            handler: function(gridview, rowIndex, colIndex) {
                var rec = gridview.getStore().getAt(rowIndex);
                this.up('gridpanel').doChangeEnd(rec);
            }
        },{
            icon: Rich.Url.icons_2,
            iconCls:'actioncolumn-margin',
            altText:'删除',
            tooltip: '删除',
            disabled:!del,
            handler: function(gridview, rowIndex, colIndex) {
                var rec = gridview.getStore().getAt(rowIndex);
                this.up('gridpanel').doDelete(rec);
            }
        }]);
        
		var store = Ext.create('Ext.data.Store', {
			model:'Rich.model.Banner',
	        pageSize: 50,
	        autoLoad:al,	        
	        proxy: {
		        type: 'ajax',
		        getMethod:function(){return "post"},		       
		       	url: Rich.Url.bannerPagePath,	
		        reader: {
		            type: 'json',
		            root: 'data.pageData',
		            totalProperty: 'data.totalRows',
		            idProperty: 'id'
		        }
		    }
		});
		var itms = [];
		itms.unshift({
			xtype:'toolbar',
			ui:'footer',
			items:[{
				xtype:'button',
				text:'新建广告位',
				rightId:Rich.V.advert_banner_add,
				handler:function(btn){
					Ext.create('Rich.widget.CrudWindow',{
						title:this.text,
						width:800,
						height:600,
						crudForm:{
							xtype:'bannerform',
							tid:1,
							buttons:['c']
						}
					}).showFor(btn.returnFn,btn);
				},
				returnFn:function(){
					this.up('pagemain').lookupI('grid').getStore().reload();
				}
			}]
		});
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
				        { text: '评论人', dataIndex: 'userName',flex: 1 },
				        { text: '总评分', dataIndex: 'raty',flex: 1 },
				        { text: '评分日期', dataIndex: 'time',flex: 1},
				        { text: '评论内容', dataIndex: 'content',flex: 1},
				        { text: '回复内容',    dataIndex: 'replyContent',flex: 1 },
				       	{
				        	text:'操作',
				            xtype:'actioncolumn',
				            width:110,
				            items: actionItems
				        }
				]},
				lookDetail:function(rec){
					var id = rec.get('id');
					Ext.create('Rich.view.journey.JourneyEvaluateDetail').showByJourneyId(id);
			    },
			    reply:function(rec){
					if(rec){
						Ext.Msg.prompt('回复', '请输入回复:',function(btn, text){
						    if(text && text !=''){
							    if (btn == 'ok'){
							        this.el.mask('回复...');
			    	        		Rich.JsonAjax.request({
			    	        			method:'post',
				    	        		url:Rich.Url.journeyEvaluateReplyPath,
				    	        		params:{
				    	        			content:text,
							        		evaluateId:rec.get('id')
							        	},
				    	        		callback:this.removeBack,
				    	        		scope:this
			    	        		});
							    }
						    }else{
						    	Rich.Msg.alert('提示','你取消了回复.');
						    	return false;
						    }
						},this);
					}
			    },
			    removeMem:function(rec){
					if(rec){
					Ext.Msg.prompt('删除', '确定删除这条评论吗？\n请输入原因:',function(btn, text){
					    if(text && text !=''){
						    if (btn == 'ok'){
						        this.el.mask('删除中...');
		    	        		Rich.JsonAjax.request({
		    	        			method:'post',
			    	        		url:Rich.Url.journeyEvaluateDeletePath,
			    	        		params:{
						        		id:rec.get('id')
						        	},
			    	        		callback:this.removeBack,
			    	        		scope:this
		    	        		});
						    }
					    }else{
					    	Rich.Msg.alert('提示','原因是必须输入的.');
					    	return false;
					    }
					},this);
						
					}
			    },
			    removeBack:function(o,f,r){
    	        	this.el.unmask();
    	        	if(f){
    	        		this.getStore().reload();
    	        	}
    	        },
    	        listeners:{
			    	"itemdblclick0":function(gd,record, item, index, e, eOpts){
			    		this.lookDetail(record.get('userName'));
			    	}
			    }
			}]
		});
		me.callParent(arguments);
	}
});

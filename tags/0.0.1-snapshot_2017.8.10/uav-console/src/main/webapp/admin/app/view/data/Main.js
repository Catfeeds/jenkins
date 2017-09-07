/**
 * 资料管理
 */
Ext.define('Rich.view.data.Main', {
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
        'Ext.layout.component.FieldSet',
        'Ext.form.field.Checkbox',
        'Ext.toolbar.Paging',
        'Rich.model.Document',
        'Rich.view.flight.AuthWindow',
        'Rich.view.data.DataAdd',
        'Rich.view.data.DataDetail',
        'Rich.view.flight.ExportDetail',
        'Rich.widget.CrudWindow',
        'Rich.component.DicComboBox'
    ],
    extend: 'Ext.panel.Panel',
    alias:'widget.datatmain',
	initComponent:function(){
		var me = this;	
		var action = [{
            icon: 'resources/images/icons/6.png',
            iconCls:'actioncolumn-margin',
            altText:'查看详情',
            tooltip: '查看详情',
            handler: function(gridview, rowIndex, colIndex) {
                var rec = gridview.getStore().getAt(rowIndex);
                this.up('gridpanel').lookDetail(rec);
            }
       	},{
            icon: 'resources/images/icons/48.png',
            iconCls:'actioncolumn-margin',
            altText:'查看文档内容',
            tooltip: '查看文档内容',
            handler: function(gridview, rowIndex, colIndex) {
                var rec = gridview.getStore().getAt(rowIndex);
                this.up('gridpanel').toload(rec);
            }
       	},{
            icon: 'resources/images/icons/2.png',
            iconCls:'actioncolumn-margin',
            altText:'删除',
            tooltip: '删除',
            handler: function(gridview, rowIndex, colIndex) {
                var rec = gridview.getStore().getAt(rowIndex);
                this.up('gridpanel').toDel(rec);
            }
       	}];
		
		var store = Ext.create('Ext.data.Store', {
			model:'Rich.model.Document',
	        pageSize: 50,
	        autoLoad:true,
	        proxy: {
		        type: 'ajax',
		        getMethod:function(){return "get"},
		        url: Rich.Url.dataListPath,
		        reader: {
		            type: 'json',
		            root: 'data.content',
		            totalProperty: 'data.totalElements',
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
				text:'添加资料',
				//rightId:Rich.V.Addannouncement,
				handler:function(btn){
					Ext.create('Rich.view.data.DataAdd').showById(11);
				}
			}]
		});
		Ext.apply(me,{
			layout:'fit',
			items:[{
				xtype:'gridpanel',
				itemId:'list',
				id:'dat',
				loadMask: true,
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
				        draggable:false
				   	},
				   	items:[
				   		
				        { text: '文件名', dataIndex: 'name',width: 150 },
				        { text: '文件地址', dataIndex: 'url',flex: 1 },
				        { text: '创建人', dataIndex: 'creator',flex: 1},
				        { text: '创建时间', dataIndex: 'createTime',flex: 1 },
				        { text: '修改时间', dataIndex: 'updateTime',flex: 1 },
				        {
				        	text:'操作',
				            xtype:'actioncolumn',
				            width:70,
				            items: action
				        }
				]},
				lookDetail:function(rec){
					Ext.create('Rich.view.data.DataDetail').showById(rec.data);
				},
				
			    toDel:function(record){
			    	Ext.Msg.confirm('提示','确定删掉这份文档吗?',function(btn){
			        	if (btn == 'yes'){
			        		Rich.JsonAjax.request({
			        			method:'DELETE',
				        		url:Rich.Url.dataListPath+'/'+record.data.id,
				        		callback:this.upStateBack,
				        		scope:this
		        			});
				        }
			    	},this);
				 },
				 toload:function(rec){
				 	Ext.Msg.confirm('提示','确定下载这份文档吗?',function(btn){
				 		var url = rec.data.url.replace('"','');
			        	var downHistoryHref = url;
						window.location = downHistoryHref;
			    	},this);
				 },
				 upStateBack:function(f){
				 	if(f){
				 		Rich.Msg.alert('消息','删除成功！');//无需关闭的提示
				 		this.getStore().reload();
				 	}
				 },
			  	 dockedItems:itms
			}]
		});
		this.callParent(arguments);
	}
});

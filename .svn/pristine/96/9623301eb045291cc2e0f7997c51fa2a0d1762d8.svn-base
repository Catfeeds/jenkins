/**
 * 空域管理
 */
Ext.define('Rich.view.airplace.Main', {
	requires:[
		'Rich.widget.Message',
		'Ext.grid.Panel',
		'Ext.form.Panel',
		'Ext.form.field.Date',
		'Ext.form.FieldContainer',
        'Ext.form.field.ComboBox',
        'Ext.form.CheckboxGroup',
        'Ext.grid.column.Action',
        'Rich.model.Airplace',
        'Rich.widget.CrudWindow',
        'Rich.view.airplace.AirplaceForm',
        'Rich.component.DicComboBox'
    ],
    extend: 'Ext.panel.Panel',
    alias:'widget.airplacemain',
    
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
			model:'Rich.model.Airplace',
	        pageSize: 50,
	        autoLoad:true,
	        proxy: {
		        type: 'ajax',
		        getMethod:function(){return "get"},
		        url: Rich.Url.airplacePath,
		        reader: {
		            type: 'json',
		            root: 'data',
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
				text:'新增空域',
				handler:function(btn){
					Ext.create('Rich.widget.CrudWindow',{
						title:this.text,
						width:0.7,
						height:0.8,
						crudForm:{
							xtype:'airplaceform',
							tid:1,
							buttons:['c']
						}
					}).showFor(btn.returnFn,btn);
				},
				returnFn:function(f){
					if(f){
						Rich.Msg.alert('消息','新增成功!')
						this.up('airplacemain').lookupI('list').getStore().reload();
					}
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
				   		
				        { text: '空域名称', dataIndex: 'name',flex:1 },
				        { text: '空域类型', dataIndex: 'type',flex: 1 ,renderer:function(v){
				        	switch(v){
				        		case 0:
				        			return '管制空域';
				        		case 1:
				        			return '一类报告空域';
				        		case 2:
				        			return '二类报告空域';
				        	}
				        }},
				        { text: '位置描述', dataIndex: 'location',flex: 1},
				        { text: '所属行政辖区', dataIndex: 'provinceName',flex: 1 ,renderer:function(v,record){
				        	var data = record.record.data;
				        	return v+data.cityName+data.areaName;
				        }},
				        { text: '所属空域辖区', dataIndex: 'territorial',flex: 1 },
				        { text: '空域范围类型', dataIndex: 'scopeType',flex: 1 ,renderer:function(v){
				        	if(v == 1){
				        		return '圆形';
				        	}else{
				        		return '多边形';
				        	}
				        }},
				        { text: '真高', dataIndex: 'high',flex: 1 ,renderer:function(v){
				        	return '真高'+v+'米(含)以下'
				        }},
				        {
				        	text:'操作',
				            xtype:'actioncolumn',
				            width:70,
				            items: action
				        }
				]},
				lookDetail:function(rec){
					Ext.create('Rich.widget.CrudWindow',{
						title:'空域详情',
						width:0.7,
						height:0.8,
						crudForm:{
							xtype:'airplaceform',
							tid:rec.get('id'),
							buttons:['u']
						}
					}).showByParams({id:rec.get('id')},this.upStateBack(),this);
				},
				
			    toDel:function(record){
			    	Ext.Msg.confirm('提示','确定删掉这个空域吗?',function(btn){
			        	if (btn == 'yes'){
			        		Rich.JsonAjax.request({
			        			method:'DELETE',
				        		url:Rich.Url.airplacedelPath+record.get('id'),
				        		headers:{'Content-Type':'application/json;charset=UTF-8'},
				        		callback:this.upStateBack,
				        		scope:this
		        			});
				        }
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

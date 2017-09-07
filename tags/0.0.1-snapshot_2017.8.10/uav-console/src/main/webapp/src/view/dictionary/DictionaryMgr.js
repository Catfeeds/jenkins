Ext.define('Rich.view.dictionary.DictionaryMgr', {
	requires:[
		'Rich.widget.Message',
		'Ext.grid.Panel',
        'Rich.view.dictionary.DictionaryTree',
        'Ext.form.Panel',
        'Ext.form.field.Hidden',
        'Ext.form.field.Trigger',
        'Ext.form.field.ComboBox',
        'Ext.grid.column.Action',
        'Rich.model.Dictionary',
        'Ext.form.FieldSet',
        'Ext.layout.component.FieldSet',
        'Ext.form.field.Checkbox',
        'Rich.view.dictionary.DictionaryAdd',
        'Rich.view.dictionary.DictionaryAddNode',
        'Rich.component.DicComboBox'
    ],
    uses:'Rich.view.sys.OrgSelectorWindow',
    extend: 'Ext.panel.Panel',
    alias:'widget.dictionarymgr',
    getCrudForms:function(){
    	return this.lookupI('crudforms');
    },
	initComponent:function(){
		var me = this;
		
		var actionItems = [{
            icon:Rich.Url.icons_6,
            altText:'修改',
            disabled:!Rich.RightManager.hasRight('admin-user-update'),
            tooltip: '修改',
            handler: function(grid, rowIndex, colIndex) {
                var rec = grid.getStore().getAt(rowIndex);
                Ext.create('Rich.view.dictionary.DictionaryUpdate').showById(
                rec.get('typeName'),rec.get('typeDesc'),rec.get('isParent'),rec.get('parentCode'),
                rec.get('itemDesc'),rec.get('itemCode'),rec.get('id'));
            }
        },'->',{
            icon: Rich.Url.icons_2,
            text:'删除',
            tooltip: '删除',
            handler: function(grid, rowIndex, colIndex) {
                var rec = grid.getStore().getAt(rowIndex);
                this.up('gridpanel').doUpdateState([rec]);
            }
        }];
		var store1 = Ext.create('Ext.data.TreeStore', {
			model: 'Task',
			proxy: {
                    type: 'ajax',
                    actionMethods: {
                        create: "POST", read: "POST", update: "POST", destroy: "POST"
                    },
                    url: Rich.Url.dictionaryPagePath
			},
			//fields: ["qcId", "claName", "pId", "lowScroe", "topScore"],
            folderSort: true
		   /* root: {
		        expanded: true,
		        children: [
		            { text: "detention", leaf: true },
		            { text: "homework", expanded: true, children: [
		                { text: "book report", leaf: true },
		                { text: "algebra", leaf: true}
		            ] },
		            { text: "buy lottery tickets", leaf: true }
		        ]
		    }*/
		});

		var store = Ext.create('Ext.data.Store', {
			model:'Rich.model.Dictionary',
	        pageSize: 50,
	        autoLoad:false,
	        proxy: {
		        type: 'ajax',
		        getMethod:function(){return "post"},
		        url:Rich.Url.dictionaryPagePath,
		        reader: {
		            type: 'json',
		            root: 'data',
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
				xtype:'dictionarytree',
				itemId:'dictionarytree',
				frame:true,
				//title:'数据',
				margin: '10 10 10 10',
				flex:2,
				tools:[{
				    type:'refresh',
				    tooltip: '刷新组织',
				    handler: function(event, toolEl, panelHeader) {
				        this.up('dictionarytree').refresh();
				    }
				}],
				listeners:{ 
	                'select':function(me0, record, index, eOpts){
	                	debugger;
                		var vs = [];
                		vs.typeName = record.get('typeName');
                		vs.parentCode = record.get('itemCode')
                		var sto = this.up('dictionarymgr').lookupI('list').getStore();
			    		sto.getProxy().extraParams = vs;
			    		sto.loadPage(1);
					}
            	}/*,
				tbar:[{
				    	text:'数据字典'
				    }]*/
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
					title:'详细',
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
					        {text: '名称描述',dataIndex:'typeDesc',flex:1},
					        {text: '序号',dataIndex:'id',flex:1},
		            	 	{text: '类型名称', dataIndex: 'typeName',flex: 1 },
			             	{text: '对应字段', dataIndex: 'itemCode',flex: 1 },
			             	{text: '字段描述', dataIndex: 'itemDesc',flex: 1},
			             	{text: '是否是父类', dataIndex: 'isParent',flex: 1,renderer:function(v){
					       		if(v == 0){
					       			return '不是'
					       		}else if(v == 1){
					       			return '是'
					       		}
					       	}},
			             	{text: '父节点为', dataIndex: 'parentCode',flex: 1},
					        {
					        	text:'操作',
					            xtype:'actioncolumn',
					            width:70,
					            items:actionItems
					        }
					]},
					doUpdateState:function(recs){
						if(!recs || recs.length < 1){
							Rich.Msg.alert('提示','需要至少选择一项进行操作.');
							return;
						}
						var msg = '你确定批量删除?';
						Ext.Msg.confirm('提示',msg,function(btn){
			        	if (btn == 'yes'){
				        		this.UpdateState(recs);
				        	}
			    		},this);
			    },
			    UpdateState:function(recs){
			    	var idss = [];
			    	for(var i=0;i<recs.length;i++){
			    		idss.push(recs[i].get('id'));
			    	}
			    	this.el.mask('...');
	        		Rich.JsonAjax.request({
	        			method:'post',
    	        		url:Rich.Url.dictionaryDelPath,
    	        		params:{
    	        			ids:idss
			        	},
    	        		callback:this.upStateBack,
    	        		scope:this
	        		});
			    },
			    
			     upStateBack:function(o,f,r){
			    	this.el.unmask();
    	        	if(f){
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
				    	text:'新增节点',
				    	handler:function(btn){
				    		var type = 1;
				    		Ext.create('Rich.view.dictionary.DictionaryAddNode').showById(type);
				    	}
				    },{
				    	text:'新增数据',
				    	handler:function(btn){
				    		var dt = this.up('dictionarymgr').lookupI('list').getStore().getAt(0);
				    		var typeName = dt.data.typeName;
				    		var typeDesc = dt.data.typeDesc;
				    		var isParent = dt.data.isParent;	
				    		var parentCode = dt.data.parentCode;
				    		var itemCode = dt.data.itemCode;
				    		Ext.create('Rich.view.dictionary.DictionaryAdd').showById(
				    		typeName,typeDesc,isParent,parentCode);
				    	}
				    }]
				},{
			    	xtype:'toolbar',
			    	docked:'top',
			    	items:[{
				    	text:'批量删除',
				    	handler:function(btn){
				    		var gd = this.up('gridpanel');
				            var rec = gd.getSelectionModel().getSelection();
				            gd.doUpdateState(rec);
				    	}
				    }]
				}]
				}]
			}]
		});
		this.callParent(arguments);
	}
});

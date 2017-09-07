/**
 * 图片轮播main
 */
Ext.define('Rich.view.content.Main', {
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
        'Rich.model.Picture',
        'Rich.view.content.PictureForm',
        'Rich.view.content.PictureDetail',
        'Ext.form.field.Checkbox',
        'Ext.toolbar.Paging',
        'Rich.widget.CrudWindow',
        'Rich.component.DicComboBox'
    ],
    extend: 'Ext.panel.Panel',
    alias:'widget.contenttmain',
	initComponent:function(){
		var me = this;	
		var detail = Rich.RightManager.hasRight(Rich.V.banners_detail);//详情权限
		var al = Rich.RightManager.hasRight(Rich.V.banners);//分页权限
		var del = Rich.RightManager.hasRight(Rich.V.banner_bannerId);//详情权限
		var action = [{
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
                this.up('gridpanel').DeletePic(rec);
            }
        }]
		var store = Ext.create('Ext.data.Store', {
			model:'Rich.model.Picture',
	        pageSize: 50,
	        autoLoad:al,
	        proxy: {
		        type: 'ajax',
		        getMethod:function(){return "get"},
		        url: Rich.Url.bannersPicPath,
		        reader: {
		            type: 'json',
		            root: 'data.content',
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
		        fieldLabel: '标题描述',
		        name: 'titleDesc'
		    },{
		    	fieldLabel:'排序',
		    	name:'sortOrder'
		    },{
	            xtype      : 'fieldcontainer',
	            fieldLabel : '创建时间',
	            width:300,
	            labelWidth:60, 
	            defaults: {
	                hideLabel: true
	            },
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
		    	rightId:Rich.V.banners,
		    	handler:function(btn){
		    		this.up('form').getForm().reset();
		    	}
		    },{
		    	xtype:'button',
		    	text:'搜索',
		    	cls:'r-btn-font-normal',
		    	style:{float:"right",margin:"5px 10px 5px 10px"},
		    	rightId:Rich.V.banners,
		    	handler:function(btn){
		    		var vs = this.up('form').getForm().getValues();
		    		var sto = this.up('gridpanel').getStore();
		    		sto.getProxy().extraParams = vs;
		    		sto.loadPage(1);
		    	}
		    }]
		}];
		itms.unshift({
			xtype:'toolbar',
			ui:'footer',
			items:[{
				xtype:'button',
				text:'新建轮播图',
				rightId:Rich.V.Putbanners,
				handler:function(btn){
					Ext.create('Rich.widget.CrudWindow',{
						title:this.text,
						width:0.5,
						crudForm:{
							xtype:'pictureform',
							tid:1,
							buttons:['c']
						}
					}).showFor(btn.returnFn,btn);
				},
				returnFn:function(){
					Rich.Msg.alert('消息','新增成功!')
					this.up('contenttmain').lookupI('list').getStore().reload();
				}
			}]
		});
		Ext.apply(me,{
			layout:'fit',
			items:[{
				xtype:'gridpanel',
				itemId:'list',
				id:'pic',
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
				        draggable:false,
				        //enableColumnHide:false,
				        menuDisabled:true
				   	},
				   	items:[
				   		{ text: '序号', dataIndex: 'id',flex: 1 },
				   		{ text: '标题描述', dataIndex: 'titleDesc',flex: 1 },
				        { text: '图片链接', dataIndex: 'imgLink',flex: 1 },
				        { text: '排序', dataIndex: 'sortOrder',flex: 1 },
				       	{ text: '是否隐藏', dataIndex: 'isHidden',flex: 1,renderer:function(v){
				        	if(v == '0'){
				        		return '显示';
				        	}else{
				        		return '隐藏';
				        	}
				        }},
				        { text: '创建时间', dataIndex: 'createTime',flex: 1,renderer:function(v){
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
				        { text: '创建人', dataIndex: 'creator',flex: 1},
				        {
				        	text:'操作',
				            xtype:'actioncolumn',
				            width:70,
				            items: action
				        }
				]},
				lookDetail:function(record){
					Ext.create('Rich.view.content.PictureDetail').showById(record.data);
				},
				
				DeletePic:function(rec){
					Ext.Msg.confirm('提示','确定删除轮播图?',function(btn){
			        	if (btn == 'yes'){
			        		Rich.JsonAjax.request({
			        			method:'DELETE',
				        		url:Rich.Url.bannerDetailPath+rec.get('id'),
				        		callback:this._submitBack,
				        		scope:this
	        				});
				        }
			    	},this);
				},
				
				_submitBack:function(o,f,r){
					if(f){
						Rich.Msg.alert('提示','删除成功！');
						this.getStore().reload();
					}
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

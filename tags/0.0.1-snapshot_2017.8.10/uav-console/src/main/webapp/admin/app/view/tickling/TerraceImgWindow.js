﻿/*
 * 订单详情窗体
 * */
Ext.define('Rich.view.tickling.TerraceImgWindow',{
	requires:['Ext.form.Panel',
	'Rich.component.FileUpload',
	'Ext.form.field.ComboBox'
	],
    extend: 'Rich.widget.Window',
    alias:'widget.terraceimgwindow',
    width:260,
    height:260,
    autoScroll:true,
    title:'图片展示',
 	
    callback:null,
    scope:null,
    multiSelect:false,
    
    initComponent:function(){
    	var me = this;   
    	Ext.apply(me,{
    		layout:'fit',
    		dockedItems:{
    			docked:'top',
    			itemId:'topToolbar',
    			xtype:'toolbar',
    			items:['->']
    		},
    		items:[{
				xtype:'form',
				itemId:'form',
				cls:'r-highlight-disabled',
		        defaults: {
		            border: false,
		            xtype: 'panel',
		            flex: 1,
		            layout: 'anchor',
		            defaults:{
		            	anchor: '100%'			            
		            }
		        },
		        layout: 'vbox',
		        items: [{
					itemId:'cover',
					margin:'10 10 10 10',
					cls:'x-fieldset',
			    	xtype:'fileupload',
					//flex:0.99,
					//width:200,
					name:'img',
					labelHidden:true,
					fieldLabel:'照片展示',
					buttonText: '上传图片'
		        }],
		        buttons:['->',{
	    	    	text:'保存',
	    	    	itemId:'btnSave',
	    	    	handler:function(btn){
	    	    		var win = btn.up('terraceimgwindow');
	    				win.close(true);
	    				var cv = win.lookupI('cover').src;
    					win.returnValue(cv);
	    			}
		    	}]
    		}]
    	});
    	me.callParent(arguments);
    }
});
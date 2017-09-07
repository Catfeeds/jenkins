/**
 *字典增加页面
 */
Ext.define('Rich.view.leavemessage.MessageDetail',{
	requires:['Ext.form.Panel',
	'Ext.form.field.HtmlEditor',
	'Rich.view.tickling.TerraceImgWindow',
	'Ext.form.field.ComboBox'
	],
    extend: 'Rich.widget.Window',
    alias:'widget.messagedetail',
    mixWidth:400,
    mixHeight:600,
    width:0.5,
    autoScroll:true,
    title:'留言详情',
 
    showById:function(rec){
    	this.lookupI('id').setValue(rec.id);
    	this.lookupI('name').setValue(rec.name);
    	this.lookupI('phone').setValue(rec.phone);
    	this.lookupI('email').setValue(rec.email);
    	this.lookupI('content').setValue(rec.content);
    	this.lookupI('form').setDisabled(true);
    	this.show();
    },
    
    initComponent:function(){
    	var me = this;   
    	Ext.apply(me,{
    		layout:'fit',
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
		            	padding:'5 5 5 5',
		            	anchor: '100%'			            
		            }
		        },
		        layout: 'hbox',
		        items: [{
		            items: [{
		            	xtype:'hiddenfield',
		            	name:'id',
		            	itemId:'id'
		            },{
		            	xtype:'textfield',
		            	fieldLabel:'留言人姓名',
		            	name:'name',
		            	itemId:'name'
		            },{
		                xtype:'textfield',
		                itemId:'phone',
		                fieldLabel:'手机号',
		                name: 'phone'
		            },{
		                xtype:'textfield',
		                fieldLabel: '邮箱',
		                itemId:'email',
		                name: 'email'
		            },{
		                xtype:'textarea',
		                fieldLabel: '内容',
		                itemId:'content',
		                height:300,
		                name: 'content'
		            }]
		        }]
    		}]
    	    	
    	});
    	me.callParent(arguments);
    }
});
Ext.define('Rich.view.Viewport', {
    extend: 'Ext.container.Viewport',
    requires:[
    	'Rich.util.Relax',
    	'Rich.widget.SectionTabpanel',
        'Ext.layout.container.Border',
        'Ext.tree.Panel',
        'Rich.RightManager',
        'Rich.JsonAjax',
        'Ext.ux.layout.Center',
        'Rich.view.ReceiveMessage',
        'Rich.view.Person',
        'Ext.form.Label',
        'Ext.PagingToolbar',
        'Ext.util.Cookies',
        'Rich.view.UserLabel'
    ],
    layout: 'border',
    createTabByRecord:function(record){
    	var dis = record.get('cls');
    	if(dis.indexOf('x-treecolumn-disabled') != -1){
    		return;
    	}
    	var id = record.get('id');
    	var tab = this.getComponent('tabpanel');
    	var comp = tab.getComponent(id);
    	if(comp){
    		tab.setActiveTab(comp);
    	}else{
    		tab.addSection({
    			closable:true,
    			itemId:id,
    			ctype:record.get('ctype'),
    			title:record.get('text')
    		});
    	}
    },
	initComponent:function(){
		var cd = [
			
			{id:'user',rightId:Rich.V.number,text:"用户管理",leaf:false, children:[
			    {id:'number',rightId:Rich.V.number,text:"账号管理",leaf:false,children:[
			    	{id:'personal',rightId:Rich.V.personal,text:"个人用户",leaf:true,ctype:'Rich.view.tuser.Main'},
     				{id:'company',rightId:Rich.V.companys,text:"企业用户",leaf:true,ctype:'Rich.view.tuser.CMain'}
			    ]},
			    {id:'power',rightId:Rich.V.power,text:"权限管理",leaf:false,children:[
			    	{id:'users',rightId:Rich.V.admin_user,text:"用户管理",leaf:true,ctype:'Rich.view.admin.Users'},
		        	{id:'userrole',rightId:Rich.V.admin_userrole,text:"用户角色管理",leaf:true,ctype:'Rich.view.admin.UserRole'},
		        	{id:'org',rightId:Rich.V.admin_org,text:"组织管理",leaf:true,ctype:'Rich.view.admin.Organization'},
		        	{id:'orgrole',rightId:Rich.V.admin_orgrole,text:"组织角色管理",leaf:true,ctype:'Rich.view.admin.OrgRole'},
		        	{id:'roleresource',rightId:Rich.V.admin_role_resource,text:"角色权限管理",leaf:true,ctype:'Rich.view.admin.RoleResource'}
			    ]}
	         ]},
			{id:'flightPlan',rightId:Rich.V.fly,text:"飞行计划管理",leaf:true,ctype:'Rich.view.flight.Main'},
			{id:'airspace',rightId:Rich.V.apply,text:"空域申请管理",leaf:true,ctype:'Rich.view.flight.AirMain'},
	        {id:'airplace',rightId:Rich.V.message,text:"空域管理",leaf:true,ctype:'Rich.view.airplace.Main'},
			{id:'content',rightId:Rich.V.content,text:"内容管理",leaf:false, children:[
             	{id:'Carousel',rightId:Rich.V.banner,text:"轮播管理",leaf:true,ctype:'Rich.view.content.Main'},
     			{id:'new',rightId:Rich.V.paper,text:"门户内容",leaf:true,ctype:'Rich.view.tickling.TerraceMgr'}
             ]}, 
            {id:'leave',rightId:Rich.V.message,text:"留言反馈",leaf:true,ctype:'Rich.view.leavemessage.Main'},
            
            {id:'data',rightId:Rich.V.document,text:"资料管理",leaf:true,ctype:'Rich.view.data.Main'},
             
	        {id:'tickling',rightId:Rich.V.news,text:"消息管理",leaf:false, children:[
				{id:'message',rightId:Rich.V.ship_query_audit,text:"系统公告",leaf:true,ctype:'Rich.view.leavemessage.SysMain'}
			 ]}
	]
		var store = Ext.create('Ext.data.TreeStore',{
			fields:[
				{name:'id',type:'string'},
				{name:'ctype',type:'string'},
				{name: 'text', type: 'string'},
				{name: 'rightId', type: 'auto'}
			],
		    root: {
		        expanded: true,
		        children: cd
		    }
		});
		var me = this;
    	Ext.apply(me,{
    		cls:'admin-viewport',
		    items: [{
		    	xtype:'container',
		        region: 'north',
		        height:50,
		        layout: {
			        type: 'hbox',
			        align: 'middle'
			    },
		        items:[{
		        	xtype:'box',
		        	cls:'admin-logo'
		        },{xtype:'box',flex:1},{
	            	xtype:'userlabel',
	            	logout2Reload:true
	            },{
	    	    	text:'消息',
	    	    	tooltip:'历史消息',
	    	    	style:'<span  color:#FFFFFF;text-decoration:underline;',
	                xtype:'button',
	                cls:'r-btn-transparent-linefocus',
	    	    	handler:function(btn){
	    	    		btn.up('container').lookDetail();
	    	    	}
	          	}],
	          	lookDetail:function(record){
					Ext.create('Rich.view.ReceiveMessage',{
						}).show();	
	          	},
	          	lookUserDetail:function(record){
					Ext.create('Rich.view.Person').showById();	
	          	}
		    },{
		        region: 'west',
		        xtype: 'treepanel',
		        itemId:'treepanel',
        		rootVisible: false,
        		split:true,
        		store:store,
		        width: 250,
		        minWidth: 200,
		        resizable:{
		        	handles:'e'
		        },
		        collapsible: true,
		        listeners:{
		        	'itemclick':function(me,record,item,index,e,eOpts){
	            		if(!Rich.view.Viewport.linkTab){
		            		if(record.get('leaf')){
		            			var vp = this.up('viewport');
		            			Rich.Relax.lazy(200,vp,'createTabByRecord',record);
		            		}
	            		}
	            	},
	            	'itemdblclick':function(me,record,item,index,e,eOpts){
	            		if(Rich.view.Viewport.linkTab){
		            		if(record.get('leaf')){
		            			var vp = this.up('viewport');
		            			vp.createTabByRecord(record);
		            		}
	            		}
	            	}
		        }
		    }, {
		        region: 'center',
		        xtype: 'sectiontabpanel',
		        frame:true,
		        hasSection:true,
		        itemId:'tabpanel',
		        showFn:function(){
		        },
		        items:[{
		        	closable:false,
		        	ctype:'Rich.view.flight.Main',
		        	itemId:'flightPlan',
		        	title:'飞行计划管理'
		        }]
		    },{
		    	xtype:'toolbar',
		    	cls:'r-transparent',
		        region: 'south',
		        height:25,
		        items:['->',{
		        	xtype:'label',
		        	style:'font-size:12px;color:#157FCC;',
		        	height:20
		        },'->']
		        
		    }]
    	});
    	me.callParent(arguments);
	}
});
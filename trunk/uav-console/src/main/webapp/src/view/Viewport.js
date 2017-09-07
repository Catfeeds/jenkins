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
			{id:'flight',rightId:Rich.V.admin_user,text:"飞行管理",leaf:false, children:[
			    {id:'flightPlan',rightId:Rich.V.user_query_audit,text:"飞行计划",leaf:true,ctype:'Rich.view.flight.Main'},                                                           
	         	{id:'flightCheck',rightId:Rich.V.user_query_audit,text:"飞行审核",leaf:true,ctype:'Rich.view.check.Main'}
	         ]},
	         
			{id:'content',rightId:Rich.V.admin_user,text:"内容管理",leaf:false, children:[
             	{id:'Carousel',rightId:Rich.V.user_query_audit,text:"图片轮播",leaf:true,ctype:'Rich.view.content.Main',},
     			{id:'new',rightId:Rich.V.ship_query_audit,text:"新闻中心",leaf:true,ctype:'Rich.view.check.ShipCheckMain'},
     			{id:'terrace',rightId:Rich.V.route_select_audit,text:"平台介绍",leaf:true,ctype:'Rich.view.check.RouteSHMain'},
             	{id:'law',rightId:Rich.V.addition_auditPage,text:"法律法规",leaf:true,ctype:'Rich.view.check.ShipProjectSHMain'}
             ]}, 
	         
	        {id:'tickling',rightId:Rich.V.admin_user,text:"反馈管理",leaf:false, children:[
				{id:'leave',rightId:Rich.V.user_query_audit,text:"留言反馈",leaf:true,ctype:'Rich.view.check.Main'},
				{id:'message',rightId:Rich.V.ship_query_audit,text:"消息中心",leaf:true,ctype:'Rich.view.tickling.PushMain'}
			 ]},
			 
			{id:'user',rightId:Rich.V.admin_user,text:"用户管理",leaf:false, children:[
             	{id:'personal',rightId:Rich.V.user_query_audit,text:"个人用户",leaf:true,ctype:'Rich.view.tuser.Main'},
     			{id:'company',rightId:Rich.V.ship_query_audit,text:"企业用户",leaf:true,ctype:'Rich.view.tuser.Main'}
             ]},
             
          {id:'rights',text:"系统管理",rightId:Rich.V.admin_,leaf:false, children:[
          	{id:'users',rightId:Rich.V.admin_user,text:"用户管理",leaf:true,ctype:'Rich.view.admin.Users'},
        	{id:'userrole',rightId:Rich.V.admin_userrole,text:"用户角色管理",leaf:true,ctype:'Rich.view.admin.UserRole'},
        	{id:'org',rightId:Rich.V.admin_org,text:"组织管理",leaf:true,ctype:'Rich.view.admin.Organization'},
        	{id:'orgrole',rightId:Rich.V.admin_orgrole,text:"组织角色管理",leaf:true,ctype:'Rich.view.admin.OrgRole'},
        	{id:'roleresource',rightId:Rich.V.admin_role_resource,text:"角色权限管理",leaf:true,ctype:'Rich.view.admin.RoleResource'}
        ]}
      	];
		
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
		        	bodyCls:'admin-square',
		        	ctype:'Rich.view.homepage.Main',
		        	title:'首  页'
		        }],
		        listeners:{
		        	'tabchange':function(tb, newC, oldC, e){
		        		if(!Rich.view.Viewport.linkTab){
		        			var tp = tb.up('viewport').lookupI('treepanel');
		        			var rc = tp.getStore().getById(newC.getItemId());
		        			if(rc){
		        				tp.expandNode(rc,true);
		        				tp.getSelectionModel().select(rc);
		        			}
		        		}
		        	}
		        }
		    },{
		    	xtype:'toolbar',
		    	cls:'r-transparent',
		        region: 'south',
		        height:25,
		        items:['->',{
		        	xtype:'label',
		        	style:'font-size:12px;color:#157FCC;',
		        	height:20,
		        	text:Ext.COPYRIGHT
		        },'->']
		        
		    }]
    	});
    	me.callParent(arguments);
	}
});
﻿/*
Ext.define('Rich.model.Org',{
    extend: 'Ext.data.TreeModel',
    idProperty: 'orgId',
    parentIdProperty:'parentId',
    fields:[{
		name: "orgId",
		type:'string'
	},{
		name: "seq",
		type:'string'
	},{
		name: "orgName",
	 	type:'string'
	},{
		name: "parentId",
		type:'string'
	}]
});
*/
Ext.define('Rich.view.admin.OrgTree', {
	requires:['Rich.JsonAjax','Rich.model.Org'],
    extend: 'Ext.tree.Panel',
    alias:'widget.orgtree',
    uses:[],
	rootVisible: false,//是否显示根组织
	//autoScroll:true,
	bodyStyle:'overflow:hidden',
    lines: true,
    //useArrows: true,
    singleCheck:false,//单checked模式
    customColumn:false,
    displayField:'orgName',
    initComponent:function(){
    	var me = this;
    	var store = me.store;
    	if (!store || !store.isStore){
           store = me.store = new Ext.data.TreeStore(Ext.apply({
            	//parentIdProperty:'parentId',
            	model:'Rich.model.Org',
            	proxy:{type: 'memory'},
                folderSort: true,
                sorters: [{
                    property: 'seq',
                    direction: 'ASC'
                }]
            }, store));
        }
        
    	me.callParent(arguments);
    	if(this.rightId){
			var hasR = Rich.RightManager.hasRight(this.rightId);
			if(!Rich.RightManager.hasRight(this.rightId)){
				return;
			}
		}
    	setTimeout(function(){
    		me.refresh();
    	},50);
    },
    refresh:function(){
		this.setLoading(' ');
		Rich.JsonAjax.request({
			url:Rich.Url.orgTreePath,
			callback:this.refreshBack,
			scope:this
		});
	},
	refreshBack:function(o,f,r){
		this.setLoading(false);
		if(f){
			var res = r.responseJson;
			var oks = [];
			this.makeTree(oks,null,res.data,'orgId','parentId');
			/*
			var nodeData = {
					orgId:'admin',
					orgName:'组织',
					//children:oks,
					expanded:true,//根节点不显示,所以一定要展开
					type:1
			};*/
			//Ext.apply(oks[0],nodeData);
			//var nodeData = oks[0];
			var nodeData = {children:oks};
			var dirtys = this.getOperatedNodes();//获得节点状态
		    this._decodeNodesData(nodeData,(dirtys.length==0?undefined:dirtys));
		    nodeData.expend = true;
			this.setRootNode(nodeData);
		}
	},
	    /**
     * 创建树形数据，返回到数组
     * @param oks 树形完成之后存放的位置
     * @param node 起点节点 默认为 null
     * @param items
     * @returns {Array} 返回孤立节点
     */
    makeTree:function(oks,node,items,id,parentId){
    	if(oks){
    		if(!node){
	    		node = items.pop();
	    		if(!node){return;}
	    	}
	    	if(node[parentId]){
	    		var pnod = null,i = 0,len=items.length,itm;
	    		for(;i<len;i++)
	        	{
	        		itm = items[i];
	        		if(itm[id] == node[parentId])
	        		{
	        			pnod = Ext.apply({},itm);break;
	        		}
	        	}
	    		if(pnod){
	    			items = Ext.Array.erase(items,i,1);
	    			if(!pnod.children){
	    				pnod.children = [];
	    			}
	    			pnod.children.unshift(node);
	    			arguments.callee.call(this,oks,pnod,items,id,parentId);return;
	    		}else{
	    			items = arguments.callee.call(this,null,node,items,id,parentId);
	    			oks.unshift(node);
	    		}
	    	}else{
	    		items = arguments.callee.call(this,null,node,items,id,parentId);
	    		oks.unshift(node);
	    	}
	    	if(items.length > 0){
	    		arguments.callee.call(this,oks,null,items,id,parentId)
	    	}
    	}else{
    		if(!Ext.isArray(node.children)){
	    		node.children = [];
	    	}
	    	var els = [],chids = node.children,itm;
	    	for(var i=0,len=items.length;i<len;i++)
	    	{
	    		itm = items[i];
	    		if(itm[parentId] == node[id])
	    		{
	    			chids.push(itm);
	    		}else{
	    			els.push(itm);
	    		}
	    	}
	    	len=chids.length;
	    	if(len > 0){
	    		for(i=0;i<len;i++)
		    	{
		    		itm = chids[i];
	    			els = arguments.callee.call(this,null,itm,els,id,parentId);
		    	}
	    	}else{
	    		delete node.children;
	    		node.leaf = true;
	    	}
	    	return els;
    	}
    },
        /**
     * @public  获得操作过的节点，展开以及选中 expanded,checked
     */
    getOperatedNodes:function(){
    	var root = this.getRootNode();
    	var res = [];
    	if(root){
    		this._getOperatedNodes(root, res);
    	}
    	return res;
    },
    _getOperatedNodes:function(node,pag) {
        var childNodes = node.childNodes,len = childNodes.length,i,cn;
        for (i = 0; i < len; i++){
        	cn = childNodes[i];
        	if (cn.get('leaf')){//如果是子节点，获得checked状态
        		/*
        		if(cn.get('checked'))
        		{
        			pag.push(cn.get('id'));
        		}
        		*/
        	}else{//如果是父节点，获得expanded状态
        		if(cn.get('expanded'))
        		{
        			pag.push(cn.get('id'));
        		}
        		arguments.callee.call(this,cn,pag);
        	}
        }
    },
    /**
     * @private
     * @param node
     * @param dirtys
     * @return Boolean 节点是否是checked的状态
     */
    _decodeNodesData:function(node,dirtys){
    	var isLeaf = node.children == undefined;//使用children属性的有无作为是否是子节点的标示
    	if(dirtys && dirtys.length > 0)//先设置状态
    	{
    		for(var j=0;j<dirtys.length;j++)
    		{
    			if(dirtys[j] == node.id)
    			{
    				if(isLeaf)
    		    	{
    		    		//node.checked = true;//如果是子节点，添加checked属性
    		    	}else{
    		    		node.expanded = true;//如果是父节点，添加expanded属性
    		    	}
    				Ext.Array.remove(dirtys,dirtys[j]);break;
    			}
    		}
    	}
    	if(isLeaf)//如果是子节点，添加checked，leaf属性,否则处理checked,leaf属性
    	{
    		//node.checked = (node.checked === true)?true:false;
    		node.leaf = true;
    		return node.checked;
    	}else{
    		if(node.leaf != undefined){node.leaf = false;}
    		//node.checked = (node.checked === true)?true:false;
    		//if(node.checked != undefined){delete node.checked;}//本来是父节点不出checkbox的。
    		
    		var chs = node.children,len = chs.length;
    		var checked = false;
            for (var i=0; i < len; i++){
            	checked = arguments.callee.call(this,chs[i],dirtys) && checked;
            }
            node.checked = checked;
            return checked;
    	}
    }
});
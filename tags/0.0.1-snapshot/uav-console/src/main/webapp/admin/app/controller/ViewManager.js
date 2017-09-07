Ext.define('Rich.controller.ViewManager', {
    extend: 'Ext.app.Controller',
    //requires: [],
    //stores: [],
    refs: [{
		    ref: 'rheader',
		    selector: 'rheader'
		},{
            ref: 'viewport',
            selector: 'viewport'
        },{
            ref: 'navigation',
            selector: 'navigation'
        },{
            ref: 'center',
            selector: 'center'
        }
    ],
    init: function() {
        this.control({
            'navigation': {
            	tabchange: 'onNavTabChange'
            },
            'viewport': {
                afterlayout: 'afterViewportLayout'
            },
            'center': {
                resize: 'centerContent'
            },
            'tool[regionTool]': {
                click: 'onSetRegion'
            }
        });
        
        this.getTerminalTree = function(){
        	return this.getNavigation().getItem('terminaltree');
        };
        this.getLogCt = function(){
        	return this.getCenter().getComponent('monitorcenter').getComponent('log_tabp');
        };
        Rich.ViewManager = this;
        
        
        
        window.doStructHidden = function(t,r,b,l){
        	var tp = "boolean";
        	if(typeof t == tp){
        		Rich.ViewManager.getRheader().setVisible(!t);
        	}
        	if(typeof b == tp){
        		Rich.ViewManager.getLogCt().setVisible(!b);
        	}
        	if(typeof l == tp){
        		Rich.ViewManager.getNavigation().setVisible(!l);
        	}
        };
        //
        window.doStrutsCollapsed = function(t,r,b,l){
        	var tp = "boolean";
        	if(t === true){
        		Rich.ViewManager.getRheader().collapse();
        	}else if(t === false){
        		Rich.ViewManager.getRheader().expand();
        	}
        	if(b === true){
        		Rich.ViewManager.getLogCt().collapse();
        	}else if(b === false){
        		Rich.ViewManager.getLogCt().expand();
        	}
        	if(l === true){
        		Rich.ViewManager.getNavigation().collapse();
        	}else if(l === false){
        		Rich.ViewManager.getNavigation().expand();
        	}
        };
      //给地图注册全屏事件
        window.doFullScreen = function(){
        	var header = Rich.ViewManager.getRheader();
        	var ng = Rich.ViewManager.getNavigation();
        	var lc = Rich.ViewManager.getLogCt();
        	if(header.hidden){
        		header.show();
        		ng.show();
        		lc.show();
        		return true;
        	}else{
        		header.hide();
        		ng.hide();
        		lc.hide();
        		return false;
        	}
        };

    },
    onNavTabChange : function(){
    	//console.info('选项卡有变化');
    },
    onSetRegion: function (tool) {
        var panel = tool.toolOwner;

        var regionMenu = panel.regionMenu || (panel.regionMenu =
                Ext.widget({
                    xtype: 'menu',
                    items: [{
                        text: '右边',
                        checked: panel.region === 'east',
                        group: 'mainregion',
                        handler: function () {
                            panel.setBorderRegion('east');
                        }
                    },{
                        text: '左边',
                        checked: panel.region === 'west',
                        group: 'mainregion',
                        handler: function () {
                            panel.setBorderRegion('west');
                        }
                    }]
                }));

        regionMenu.showBy(tool.el);
    },

    afterViewportLayout: function() {
    	//console.info('viewport在调整布局.');
    	/*
        if (!this.navigationSelected) {
            var id = location.hash.substring(1),
                navigation = this.getNavigation(),
                tree = navigation.lookupI('lefttree'),
                store = tree.getStore(),
                node;

            node = id ? store.getNodeById(id) : store.getRootNode().firstChild.firstChild;

            tree.getSelectionModel().select(node);
            tree.getView().focusNode(node);
            this.navigationSelected = true;
        }
        */
    },
    centerContent:function(cntr, w, h, ow, oh, e){
    	/*if(w != ow){
    		
    	}*/
    	if(h != oh){
    		var lc = this.getLogCt();
    		if(lc && !lc.hidden){
    			var nh = h-33;
    			lc.resizer.resizeTracker.maxHeight = nh;
    			if(lc.getHeight() > nh){
    				lc.setHeight(nh);
    			}
    		}
    	}
    }
});

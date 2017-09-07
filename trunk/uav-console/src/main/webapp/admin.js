Ext.SYSTEM_NAME = "无人机后台管理系统";
Ext.COPYRIGHT = "版权所有 ©2017 海约互联 粤ICP备 17036117号-1";//"©2017 海约互联 CO.,LTD all rights reserved";
Ext.onReady(function(){
	Ext.require(['Rich.RightManager','Rich.view.Viewport','Ext.util.CSS','Ext.util.Cookies'],function(){
		Rich.RightManager.onReady(function(mgr,success){
			Ext.removeNode(document.getElementById('loading'));
			//if(window.isDevelop){//如果是开发环境，始终通过
			//	success = true;
			//}
			//success = true;
			if(success){
				//if(Rich.RightManager.hasRight('admin-login')){
					//Ext.removeNode(document.getElementById('loading'));
					Ext.require('Rich.Application',function(){
						Ext.app.Application.instance = new Rich.Application();
					});
					return;
				//}else{
				//	Ext.Msg.alert("提示","您无权访问后台管理,请联系管理员.");
				//}
			}
			Ext.require('Rich.view.LoginViewport',function(){
				new Rich.view.LoginViewport();
			});
		});
	});
});
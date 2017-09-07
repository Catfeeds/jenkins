<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
//String ExtBasePath = "http://haiyuexing.oss-cn-shenzhen.aliyuncs.com/pub/ext/";
String ExtBasePath = "../ext/";
%>
<%  
	String path = request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); 
    String srcPath = request.getContextPath() + "/admin/";
%>
<!DOCTYPE HTML>
<html class="x-viewport">
<head>

	<meta name="renderer" content="webkit" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
    <title>无人机后台管理系统</title>
    <link rel="shortcut icon" href="../favicon.ico" />
    <link rel="stylesheet" type="text/css" href="<%=ExtBasePath%>resources/ext-theme-neptune/ext-theme-neptune-all-debug.css">
    <link rel="stylesheet" type="text/css" href="../extlib3/calendar/resources/css/calendar.css">
    
    <!-- 
    <link rel="stylesheet" type="text/css" href="resources/override.css">
    -->
    <link rel="stylesheet" type="text/css" href="<%=srcPath%>resources/icons.css">
    <link rel="stylesheet" type="text/css" href="<%=srcPath%>resources/public.css">
    <link rel="stylesheet" type="text/css" href="<%=srcPath%>resources/login.css">
    <link rel="stylesheet" type="text/css" href="<%=srcPath%>resources/admin.css">
    <!-- 
    <link href="resources/skin/skin.css" rel="stylesheet" type="text/css" />
	<link href="" rel="stylesheet" type="text/css" id="skinCss"/>
	 -->
 	<%
 		if(ExtBasePath.equals("../ext/")){
			out.println("<script type=\"text/javascript\" src=\""+ExtBasePath+"ext-dev.js\"></script>");
		}else{
			out.println("<script type=\"text/javascript\" src=\""+ExtBasePath+"ext-all.js\"></script>");
		}
    %>
    <script type="text/javascript" src="<%=ExtBasePath%>ext-theme-neptune.js"></script>
    <script type="text/javascript" src="<%=ExtBasePath%>locale/ext-lang-zh_CN.js"></script>
    <script type="text/javascript">
    Ext.ExtBasePath = '<%=ExtBasePath%>';
    Ext.ServerPath = '<%=path%>';
    </script>
    <script type="text/javascript" src="<%=srcPath%>config.js"></script>
    <script type="text/javascript" src="<%=srcPath%>admin.js"></script>
</head>
<body>

<div id="loading" style="border:/*1px solid #157fcc;*/border-radius:3px;-moz-border-radius:3px;/*background:#FFF;*/position:absolute;left:50%;top:50%;width:150px;height:50px;margin-left:-75px;margin-top:-25px;z-index:30000;">
	<img src="<%=srcPath%>resources/images/loading/large-loading.gif" style="margin:10px;float:left;vertical-align:top;"/>
	<br/>
	<span id="loading-msg" style="display:block;margin:5px;color:#d1d1d1;">加载中...</span>
</div>
</body>
</html>
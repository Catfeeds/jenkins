var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = [
    new HtmlWebpackPlugin({
        template : "public/template/index.ejs",
        title : "微信首页",
        filename : "../index.html",
        chunks : ['shared','home'],
    }),
 //    new HtmlWebpackPlugin({
 //        template : "public/template/index.ejs",
 //        title : "飞行计划备案", 
 //        filename : "views/apply-record/index.html",
 //        chunks : ['vendor','applyRecord'],
 //    }),
 //    new HtmlWebpackPlugin({
 //        template : "public/template/index.ejs",
 //        title : "我的申请动态", 
 //        filename : "views/dynamic/apply.html",
 //        chunks : ['vendor','applyDynamic'],
 //    }),
 //    new HtmlWebpackPlugin({
 //        template : "public/template/index.ejs",
 //        title : "计划实施动态", 
 //        filename : "views/dynamic/implement.html",
 //        chunks : ['vendor','implementDynamic'],
 //    }),
 //    new HtmlWebpackPlugin({
 //        template : "public/template/index.ejs",
 //        title : "飞行计划详情", 
 //        filename : "views/dynamic/detail.html",
 //        chunks : ['vendor','detailDynamic'],
 //    }),
 //    new HtmlWebpackPlugin({
 //        template : "public/template/index.ejs",
 //        title : "我的消息", 
 //        filename : "views/notice/index.html",
 //        chunks : ['vendor','notice'],
 //    }),
 //    new HtmlWebpackPlugin({
 //        template : "public/template/index.ejs",
 //        title : "用户注册", 
 //        filename : "views/login/index.html",
 //        chunks : ['vendor','login'],
 //    }),
	// new HtmlWebpackPlugin({
 //        template : "public/template/index.ejs",
 //        title : "业务咨询", 
 //        filename : "views/service/consult.html",
 //        chunks : ['vendor','consultService'],
 //    }),
]
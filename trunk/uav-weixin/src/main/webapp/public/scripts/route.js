import React, { Component } from 'react'
import Tool from 'utils/tool'
import WrapComponent from './wrap-component'
import { Route, Switch } from 'react-router'
import NotFoundApp from 'scripts/not-found'
import ConsultServiceApp from 'scripts/service/consult'
import ConsultSuccessApp from 'scripts/service/consult-success'
import ConcatUsServiceApp from 'scripts/service/contactus'
import QQGroupServiceApp from 'scripts/service/qqgroup'
import SigninApp from 'scripts/signin'
import SignupApp from 'scripts/signup'
import NoticeListApp from 'scripts/notice/list'
import NoticeDetailApp from 'scripts/notice/detail'
import DynamicDetailApp from 'scripts/dynamic/detail'
import ApplyDynamicApp from 'scripts/dynamic/apply'
import ImplementDynamicApp from 'scripts/dynamic/implement'
import CheckCodeDynamicApp from 'scripts/dynamic/check-code'
import ApplyRecordApp from 'scripts/apply-record'
import ARAddAirspaceApp from 'scripts/apply-record/add-airspace'
import ARAddLandingApp from 'scripts/apply-record/add-landing'
import ApplyRecordSuccessApp from 'scripts/apply-record/success'
import UserCenterApp from 'scripts/user/center'
import UserAuthApp from 'scripts/user/auth'
import UserModifyPhoneApp from 'scripts/user/modifyPhone'
import UserInfoApp from 'scripts/user/info'
import UserEditApp from 'scripts/user/edit'
import UserMessageApp from 'scripts/user/message'
import UserStatisticsApp from 'scripts/user/statistics'
import AirspaceApplyApp from 'scripts/airspace/apply'
import AirspaceAddAirApp from 'scripts/airspace/add-airspace'
import AirspaceAddLandingApp from 'scripts/airspace/add-landing'
import AirApplySuccessApp from 'scripts/airspace/success'
import NewsListApp from 'scripts/news/list'
import NewsDetailApp from 'scripts/news/detail'
// test
// import TestQRCodeApp from 'scripts/test/qrcode'
// import TestBDMapApp from 'scripts/test/bdmap'


// import isEmpty from 'lodash/isEmpty'
// let signinBeforeUrl;
const routes = [
    {
        path: '/signin',
        component: SigninApp,
        title : "登陆"
    },
    {
        path: '/signup/:step',
        component: SignupApp,
        title : "用户注册"
    },
    {
        path: '/notice/list',
        component: NoticeListApp,
        title : '通知公告'
    },
	{
        path: '/notice/detail/:id',
        component: NoticeDetailApp,
        title : '通知公告'
    },
    {
        verify : true,
        path: '/dynamic/detail/:code',
        component: DynamicDetailApp,
        title : '动态详情'
    },
    // {
    //     verify : true,
    //     path: '/dynamic/token-detail/:code/token',
    //     component: DynamicDetailApp,
    //     title : '动态详情'
    // },
	{
        verify : true,
        path: '/dynamic/apply/:page?',
        component: ApplyDynamicApp,
        title : '我的申请动态'
    },
    {
        verify : true,
        path: '/dynamic/implement/:page?',
        component: ImplementDynamicApp,
        title : '计划实时动态'
    },
	{
        verify : true,
        path: '/dynamic/check-code/:code',
        component: CheckCodeDynamicApp,
        title : '查看凭证'
    },
    {
        verify : true,
        path: '/apply-record/:tabIndex?',
        component: ApplyRecordApp,
        title : '飞行计划备案'
    },
    {
        verify : true,
        path: '/add-airspace/:_index?',
        component: ARAddAirspaceApp,
        title : '新增空域'
    },
    {
        verify : true,
        path: '/add-landing/:_index?',
        component: ARAddLandingApp,
        title : '新增起降场'
    },
	{
        verify : true,
        path: '/apply-record-form/success',
        component: ApplyRecordSuccessApp,
        title : '备案成功'
    },
    {
        path: '/service/consult',
        component: ConsultServiceApp,
        title : '业务咨询'
    },
    {
        path: '/service/consult/success',
        component: ConsultSuccessApp,
        title : '成功页面'
    },
    {
        path: '/service/contactus',
        component: ConcatUsServiceApp,
        title : '联系我们'
    },
	{
        path: '/service/qqgroup',
        component: QQGroupServiceApp,
        title : '加群交流'
    },
    {
        verify : true,
        path : '/user/center',
        component : UserCenterApp,
        title : '个人中心'
    },
    {
        verify : true,
        path : '/user/auth/:type',
        component : UserAuthApp,
        title : '身份认证'
    },
    {
        verify : true,
        path : '/user/modifyphone',
        component : UserModifyPhoneApp,
        title : '更换手机号码'
    },
    {
        verify : true,
        path : '/user/info',
        component : UserInfoApp,
        title : '我的资料'
    },
    {
        verify : true,
        path : '/user/edit',
        component : UserEditApp,
        title : '修改个人信息'
    },
    {
        verify : true,
        path : '/user/message',
        component : UserMessageApp,
        title : '我的消息'
    },
    {
        verify : true,
        path : '/user/statistics/:flyTimes/:flyHour/:flyOvertime',
        component : UserStatisticsApp,
        title : '飞行统计'
    },
    {
        verify : true,
        path : '/airspace/apply',
        component : AirspaceApplyApp,
        title : '空域申请'
    },
    {
        verify : true,
        path : '/airspace/add-airspace/:_index?',
        component : AirspaceAddAirApp,
        title : '新增空域'
    },
    {
        verify : true,
        path : '/airspace/add-landing/:_index?',
        component : AirspaceAddLandingApp,
        title : '新增起降场'
    },
    {
        path : '/airspace/apply-form/success',
        component : AirApplySuccessApp,
        title : '操作成功'
    },
    {
        path : '/custom-service',
        component : NotFoundApp,
        title : '订制服务'
    },
    {
        verify : true,
        path : '/airspace/list',
        component : NotFoundApp,
        title : '我的空域'
    },
    {
        path : '/news/list',
        component : NewsListApp,
        title : '行业资讯',
    },
    {
        path : '/news/detail/:id',
        component : NewsDetailApp,
        title : '行业资讯',
    },
    // {
    //     path : '/test/qrcode',
    //     component : TestQRCodeApp,
    //     title : '二维码测试'
    // },
    // {
    //     path : '/test/bdmap',
    //     component : TestBDMapApp,
    //     title : '百度地图测试'
    // },
    {
        component : NotFoundApp,
        // title : ''
    }
]
function RouteWithSubRoutes( route, key ){
    return <Route key = { key } path = { route.path } exact = { true } render={ props => {
        document.title = route.title || 'e飞服务';
        var userInfo = Tool.getLocalData("__user")
        // if( route.verify && !userInfo ){
        //     Tool.saveLocalData( "signinBeforeUrl", location.pathname.replace(config.basePath,"") )
        //     Tool.fetchCodeTo("/signin");
        //     return null
        // }
        // debugger;
        // if( route.verify && !/(\/login)|(\/signup)/.test(route.path) && !Tool.getLocalData("__user") ){
        //     let path = props.location.pathname;
        //     if( !/(\/login)|(\/signup)/.test( path ) ) signinBeforeUrl = path;
        //     Tool.autoSigninByCode();
        //     // props.history.replace('/signin');
        //     return null;
        // }else return <route.component __title = { route.title } signinBeforeUrl = { signinBeforeUrl } { ...props }/>
        return <route.component __title = { route.title } { ...props }/>
        // if( Array.isArray( route.routes ) && route.routes.length > 0 ){
        //     return (
        //         <route.component { ...props }>
        //             <Switch>
        //                 {
        //                     route.routes.map( ( v, k ) => RouteWithSubRoutes( v, k ) )
        //                 }
        //             </Switch>
        //         </route.component>
        //     )
        // }else{
        //     document.title = route.title;
        //     if( !!signinBeforeUrl ){
        //         let url = signinBeforeUrl;
        //         signinBeforeUrl = undefined;
        //         return <route.component signinBeforeUrl = { url } { ...props }/>
        //     }else
        //         return <route.component { ...props }/>
        // }
    } }/>
}

export default (
    <WrapComponent>
        <Switch>
            {
                routes.map( ( v, k ) => RouteWithSubRoutes( v, k ) )
            }
        </Switch>
    </WrapComponent>
);
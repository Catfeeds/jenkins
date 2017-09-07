import App from '../App'

/**
 * 系统路由
 */
export default [{
        path: '/',
        component: App,
        children: [{
            path: '',
            component: r => require.ensure([], () => r(require('../page/home')), 'home'),
            children: [{
                    path: '/',
                    component: r => require.ensure([], () => r(require('../page/login')), 'login')
                },
                {
                    path: '/news',
                    component: r => require.ensure([], () => r(require('../page/new')), 'new'),
                    children: [{
                            path: '/news/',
                            component: r => require.ensure([], () => r(require('../page/new/informNew')), 'informNew')
                        },
                        {
                            path: '/informNew',
                            component: r => require.ensure([], () => r(require('../page/new/informNew')), 'informNew')
                        },

                        {
                            path: '/allnew/:id',
                            component: r => require.ensure([], () => r(require('../page/new/allnew')), 'allnew')
                        },
                        {
                            path: '/newDetail/:id',
                            component: r => require.ensure([], () => r(require('../page/new/newDetail')), 'newDetail')
                        },
                        {
                            path: '/allNewDetail/:id',
                            component: r => require.ensure([], () => r(require('../page/new/allNewDetail')), 'allNewDetail')
                        },
                    ]
                },
                {
                    path: '/flyplan',
                    component: r => require.ensure([], () => r(require('../page/flyplan')), 'flyplan'),
                    children: [{
                        path: '/',
                        component: r => require.ensure([], () => r(require('../page/flyplan/myplan')), 'myplan')
                    },
                        {
                            path: '/updatePer',
                            component: r => require.ensure([], () => r(require('../page/flyplan/updatePer')), 'updatePer')
                        },
                        {
                            path: '/updateCom',
                            component: r => require.ensure([], () => r(require('../page/flyplan/updateCom')), 'updateCom')
                        },
                        {
                            path: '/flyplan1/:flyCode',
                            component: r => require.ensure([], () => r(require('../page/flyplan/plan1Detail')), 'flyplan1Detail')
                        },
                        {
                            path: '/flyplan2/:flyCode',
                            component: r => require.ensure([], () => r(require('../page/flyplan/plan2Detail')), 'flyplan2Detail')
                        },
                        {
                            path: '/flyplan0/:flyCode',
                            component: r => require.ensure([], () => r(require('../page/flyplan/plan0Detail')), 'flyplan0Detail')
                        },
                        {
                            path: '/myplan',
                            component: r => require.ensure([], () => r(require('../page/flyplan/myplan')), 'myplan')
                        },
                        {
                            path: '/applyDetail/:airApplyCode',
                            component: r => require.ensure([], () => r(require('../page/flyplan/applyDetail')), 'applyDetail')
                        },
                        {
                            path: '/createPlan',
                            component: r => require.ensure([], () => r(require('../page/flyplan/createPlan')), 'createPlan')
                        },
                        {
                            path: '/apply',
                            component: r => require.ensure([], () => r(require('../page/flyplan/apply')), 'apply')
                        },
                        {
                            path: '/document',
                            component: r => require.ensure([], () => r(require('../page/flyplan/document')), 'document')
                        },
                        {
                            path: '/notification',
                            component: r => require.ensure([], () => r(require('../page/flyplan/notification')), 'notification')
                        },{
                            path: '/notification/:notificationId',
                            component: r => require.ensure([], () => r(require('../page/flyplan/notificationDetail')), 'notificationDetail')
                        },
                        {
                            path: '/createPlan1',
                            component: r => require.ensure([], () => r(require('../page/flyplan/createPlan1')), 'createPlan1')
                        },
                        {
                            path: '/tmpl/flyplan1/:tmplCode',
                            component: r => require.ensure([], () => r(require('../page/flyplan/createPlan1')), 'flyPlan1Template')
                        },
                        {
                            path: '/createPlan2',
                            component: r => require.ensure([], () => r(require('../page/flyplan/createPlan2')), 'createPlan2')
                        },{
                            path: '/tmpl/flyplan2/:tmplCode',
                            component: r => require.ensure([], () => r(require('../page/flyplan/createPlan2')), 'flyPlan2Template')
                        },
                        {
                            path: '/createPlan0',
                            component: r => require.ensure([], () => r(require('../page/flyplan/createPlan0')), 'createPlan0')
                        },{
                            path: '/tmpl/flyplan0/:tmplCode',
                            component: r => require.ensure([], () => r(require('../page/flyplan/createPlan0')), 'flyPlan0Template')
                        }, {
                            path: '/airApply',
                            component: r => require.ensure([], () => r(require('../page/flyplan/airApply')), 'airApply')
                        }
                    ]
                },
                {
                    path: '/intro',
                    component: r => require.ensure([], () => r(require('../page/introduction')), 'introduction'),
                    children: [{
                            path: '/intro',
                            component: r => require.ensure([], () => r(require('../page/introduction/allIntroduction')), 'allIntroduction')
                        },
                        {
                            path: '/allIntroduction/:id',
                            component: r => require.ensure([], () => r(require('../page/introduction/allIntroduction')), 'allIntroduction')
                        },
                    ]
                },
                {
                    path: '/detail/:id',
                    component: r => require.ensure([], () => r(require('../page/home/detail')), 'detail')
                },
                {
                    path: '/fingerpost',
                    component: r => require.ensure([], () => r(require('../page/home/fingerpost')), 'fingerpost')
                },
                {
                    path: '/law',
                    component: r => require.ensure([], () => r(require('../page/home/law')), 'law')
                },
                {
                    path: '/contact',
                    component: r => require.ensure([], () => r(require('../page/home/contact')), 'contact')
                },
                {
                    path: '/message',
                    component: r => require.ensure([], () => r(require('../page/home/message')), 'message')
                },
                {
                    path: '/register/personage',
                    component: r => require.ensure([], () => r(require('../page/register/personage')), 'personage')
                },
                {
                    path: '/register/firm',
                    component: r => require.ensure([], () => r(require('../page/register/firm')), 'firm')
                },
                {
                    path: '/register/password',
                    component: r => require.ensure([], () => r(require('../page/register/password')), 'password')
                },
                {
                    path: '/login',
                    component: r => require.ensure([], () => r(require('../page/login')), 'login')
                },
            ]
        }]
    }
]
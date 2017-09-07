<template>
    <div>
        <div id="header">
            <div class="clear" style="margin:0 20px;">
                <div id="title" class="left"><a href="#/" @click="clicked(1)">广州飞行管制分区无人机服务平台</a>
                </div>
                <div id="nav" class="left">
                    <el-menu :default-active="activeMenuIndex" class="el-menu-demo" mode="horizontal">
                            <el-menu-item v-for="menu in navmenus" :index="menu.index">
                                <a :href="menu.url" style="display:block;height:100%;">{{menu.name}}</a>
                            </el-menu-item>
                    </el-menu>
                </div>
                <ul id="nav-menu">
                    <div v-if="hasLogin">
                        <a href="#/myplan">
                            <li><i class="fa fa-user" aria-hidden="true"></i> {{user.name}}</li>
                        </a>
                        <a @click="logout">
                            <li><i class="fa fa fa-sign-out"></i> 退出</li>
                        </a>
                    </div>
                    <div v-if="!hasLogin">
                        <a href="#/login">
                            <li> 登陆</li>
                        </a>
                        <a href="#/register/personage">
                            <li> 个人注册</li>
                        </a>
                        <a href="#/register/firm">
                            <li> 企业注册</li>
                        </a>
                    </div>
                </ul>
            </div>
        </div>
        <div id="content">
            <!--轮播图-->

            <div id="viewer">
                <router-view></router-view>
            </div>
        </div>

        <div id="foot">
            <div class="foot-link">
                <ul class="clear">
                    <li class="left" style="width:14.5%;margin-left:3%;">
                        <div class="foot_title">网站导航</div>
                        <ul class="foot_nav clear">
                            <li class="left"><a href="#/news">新闻中心</a></li>
                            <li><a href="#/message">在线留言</a></li>
                            <li class="left"><a href="#/intro">平台介绍</a></li>
                            <li><a href="#/login">用户登录</a></li>
                            <li class="left"><a href="#/law">法律法规</a></li>
                            <li><a href="#/register/personage">个人注册</a></li>
                            <li class="left" href="#/myplan"><a >我的飞行</a></li>
                            <li><a href="#/register/firm" >企业注册</a></li>
                            <li class="left"><a href="#/contact" >联系我们</a></li>
                            <li><a href="#/register/password">找回密码</a></li>
                        </ul>
                    </li>
                    <li class="left borderLR">
                        <div class="foot_title">友情链接</div>
                        <ul class="left" style="margin-right:20px;">
                            <li style="margin-top:5px;"><a href="http://zn.caac.gov.cn/index.html" target="_blank">中国民用航空中南地区管理局</a>
                            </li>
                            <li><a href="http://www.jeatcs.com" target="_blank">广州嘉恩航空技术服务有限公司</a></li>
                        </ul>
                        <ul class="left">
                            <li style="margin-top:5px;"><a href="http://www.cma.gov.cn/" target="_blank">中国气象局</a></li>
                            <li><a href="http://www.gdmo.cn/weather-gdmo/" target="_blank">广东省气象台</a></li>
                            <li><a href="http://www.gdga.gov.cn/" target="_blank">广东省公安厅</a></li>
                        </ul>
                    </li>
                    <li class="left " style="width:25%;">
                        <div class="foot_title">公司信息</div>
                        <ul>
                            <li>固    话：020-82258167</li>
                            <li>手机号码：18903006535</li>
                            <li>公司邮箱：service@efly-atc.com</li>
                            <li>公司地址：广州市越秀区永福8号605室
                                <!--<a >查看地图</a>-->
                            </li>
                        </ul>
                    </li>
                    <li class="right" style="margin-right:3%;width:11%;">
                        <p class="center">微信公众号</p>
                        <p class="center">e飞服务</p>
                        <p style="margin-top:10px;">
                            <img style="width:120px;height:120px;" src="../../images/qrcode-wx.jpg">
                        </p>
                    </li>
                </ul>
            </div>
            <div class="foot_ICP">Copyright ©2017 广州嘉恩航空技术服务有限公司 版权所有
                <a style='color:white;' href="http://www.hfocean.com/" target="_blank">| 技术支持单位：广东华风海洋信息系统服务有限公司</a>
                <div>
                    <a href="http://www.miitbeian.gov.cn/" target="_blank">粤ICP备17015394号-2</a>
                    <a target="_blank" href="http://www.beian.gov.cn/portal/index"><img src="../../images/gongan.png"
                                                                                        style="vertical-align:bottom;padding:0 5px;width:15px;height:15px;"/>
                        粤公网安备 44010402000939号</a>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import Vue from 'vue'
    import urls from '../../components/urls'
    import '../../images/logo.png'
    import img01 from '../../images/cut/4.jpg'
    import ElementUI from 'element-ui'
    import 'element-ui/lib/theme-default/index.css'
    import '../../style/animate.css'
    import '../../style/main.css'
    import '../../images/qrcode-wx.jpg'
    import '../../images/gongan.png'

    Vue.use(ElementUI)
    export default{
        methods: {
            logout(){
                this.$store.dispatch('removeSession')
                $.ajax({
                    type: 'GET',
                    url: urls.GET_OUT,
                    success: function (data, textStatus, jqXHR) {
                        window.location.href = '#/login';
                    },
                });
            },
            //激活当前菜单
            activeCurrentMenu: function(){
                var path = this.$route.path;
                var activeMenu = this.navmenus.find(function (value) {
                    return path.indexOf(value.key) != -1;
                });
                if (activeMenu != null) {
                    this.activeMenuIndex = activeMenu.index;
                }
            }
        },
        data(){
            return {
                //激活的菜单顺序
                activeMenuIndex: '',
                //轮播图
                banners: [],
                //导航菜单
                navmenus: [{
                    index: '1',
                    url: '#/news',
                    name: '新闻中心',
                    key: 'news'
                }, {
                    index: '2',
                    url: '#/intro',
                    name: '平台介绍',
                    key: 'intro'
                }, {
                    index: '3',
                    url: '#/law',
                    name: '法律法规',
                    key: 'law'
                }, {
                    index: '4',
                    url: '#/myplan',
                    name: '我要飞行',
                    key: 'flyplan'
                }, {
                    index: '5',
                    url: '#/contact',
                    name: '联系我们',
                    key: 'contact'
                }, {
                    index: '6',
                    url: '#/message',
                    name: '在线留言',
                    key: 'message'
                }]
            }
        },
        computed :{
            //是否已经登录
            hasLogin:function(){
                return this.$store.getters.hasSession;
            },
            //登录用户信息
            user:function(){
                return this.$store.getters.getSession;
            }
        },
        mounted(){
            this.activeCurrentMenu();
        },
        watch: {
            '$route': function (to, from) {
                this.activeCurrentMenu();
            }
        }
    }

</script>


<style lang="less">
    .el-menu-item {
        padding: 0 10px;
    }

    #nav-menu a {
        cursor: pointer;
    }

    .el-menu--horizontal .el-menu-item {
        height: 50px;
        line-height: 50px;
    }

    .el-menu {
        background: white;
    }

    #nav-menu {
        float: right;
    }

    #nav-menu li a {
        color: black;
        font-size: 12px;
        padding: 15px 10px;
    }

    #nav-menu li {

        padding: 0 10px;
        float: left;
    }

    .el-menu-item {
        color: black;
    }

    #nav {
        margin-left: 20px;
        float: left;
    }

    #nav a li {
        color: blue;

    }

    #header {
        box-shadow: 0px 2px 10px #C9CDD2;
        height: 50px;
        line-height: 50px;
        background: white;
    }

    #foot {
        padding-top: 15px;
        background: #f2f2f2;
    }

    #foot .borderLR {
        margin: 0 3%;
        padding: 5px 4%;
        border-left: 1px solid #e4e4e4;
        border-right: 1px solid #e4e4e4;
    }

    .foot_nav li:nth-child(2n-2) {
        margin-left: 80px;
    }

    .foot-link {
        max-width: 1024px;
        margin: auto;
    }

    .foot-link li {
        color: #666666;
        padding: 5px 0;
        font-size: 12px;
    }

    .foot_ICP {
        text-align: center;
        margin-top: 15px;
        padding: 3px 6%;
        font-size: 12px;
        color: white;
        background: #3876C4;
        line-height: 15px;
    }
</style>
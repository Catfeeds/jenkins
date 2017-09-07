<template>
    <div>
        <div style="margin-top:20px;" id="content" class="maxW">
            <el-row type="flex" class="row-bg" justify="center">
                <el-col :span="4">
                    <div class="user-info bg-purple">
                        <div class="img"><img :src="defaultUserImg"></div>
                        <p class="name">
                            <span>{{user.name}}</span>
                            <span v-if="this.user.authStatus==1"><el-tag>未认证</el-tag></span>
                            <span v-if="this.user.authStatus==2"><el-tag type="warning">待认证</el-tag></span>
                            <span v-if="this.user.authStatus==3"><el-tag type="success">已验证</el-tag></span>
                            <span v-if="this.user.authStatus==4"><el-tag type="danger">认证失败</el-tag></span>
                        </p>
                        <div class="center flyplan_mes">
                            <a v-if="user.type==1" href="#updatePer">完善个人资料</a>
                            <a v-if="user.type==2" href="#updateCom">完善个人资料</a>
                        </div>
                        <ul class="fly-info">
                            <li><i class="fa fa-plane" style="color:#0ba3ff"></i><span>飞行次数</span><span
                                    class="value">{{user.flyTimes}}</span></li>
                            <li><i class="fa fa-clock-o" style="color:#0aae91"></i><span>飞行时间</span><span class="value">{{user.flyHour}}</span>
                            </li>
                            <li><i class="fa fa-exclamation-triangle" style="color:#ff0000"></i><span>违规次数</span><span
                                    class="value">{{user.flyOvertime}}</span></li>
                        </ul>
                    </div>
                </el-col>
                <el-col class="fly_nav" :span="18">
                    <div class="grid-content bg-purple-light">
                        <div>
                            <el-menu :default-active="flyIndex" class="fly_nav_nav clear" mode="horizontal">
                                <a href="#/myplan" class="left clear">
                                    <el-menu-item index="1">我的计划
                                        <el-badge class="mark" :value="0"/>
                                    </el-menu-item>
                                </a>
                                <a href="#/apply" class="left clear">
                                    <el-menu-item index="2">我的申请空域
                                        <el-badge class="mark" :value="0"/>
                                    </el-menu-item>
                                </a>
                                <a href="#/document"class="left clear">
                                    <el-menu-item index="3">资料下载
                                        <el-badge class="mark"/>
                                    </el-menu-item>
                                </a>
                                <a href="#/notification" class="left clear">
                                    <el-menu-item index="4">通知公告
                                        <el-badge class="mark" :value="0"/>
                                    </el-menu-item>
                                </a>
                            </el-menu>
                        </div>
                    </div>
                    <div id="viewer">
                        <router-view></router-view>
                    </div>
                </el-col>
            </el-row>
        </div>


    </div>
</template>

<script>
    import Vue from 'vue'
    import ElementUI from 'element-ui'
    import $ from 'jquery'
    import IMG_HEADER_DEFAULT from '../../images/header-default.jpg'
    import urls from '../../components/urls'
    import 'element-ui/lib/theme-default/index.css'
    import '../../style/animate.css'
    import '../../style/main.css'


    Vue.use(ElementUI)
    export default{
        methods: {
            logout(){
                this.$store.dispatch('removeSession');
                $.ajax({
                    type: 'GET',
                    url: urls.GET_OUT,
                    success: function (data, textStatus, jqXHR) {
                        window.location.href = '#/login';
                    },
                });
            }
        },
        data(){
            return {
                //默认用户头像
                defaultUserImg: IMG_HEADER_DEFAULT,
                activeMenuIndex: '4',
                flyIndex: '1',
                user: {}
            }
        },
        created(){
            if (!this.$store.getters.hasSession) {
                this.$message({
                    message: '请登录',
                    type: 'error',
                    duration:3000
                })
                window.location.href = "#/login";
                return false;
            }
            this.user = this.$store.getters.getSession;
            this.$store.dispatch('initAirSpaces');
            this.$store.dispatch('initLandings');
        },
        mounted(){

        }
    }
</script>


<style lang="less">
    .fly_nav {
        margin-left: 15px;

    }

    .fly_nav .is-active {
        font-weight: 600;
        color: #fff;
        background-color: #7dcdfd;
    }

    .fly_nav .el-menu--horizontal .el-menu-item:hover {
        background-color: #7dcdfd;
        color: #fff;
    }

    .fly_nav .fly_nav_nav {
        border-bottom: 1px solid #cccccc;
        background-color: #ecfcff;
    }

    .fly_nav .el-menu-item {
        padding: 0 20px !important;
    }

    /*左侧用户信息栏*/
    .user-info {
        padding: 15px 10px;
        background-color: #ecfcff;
        text-align: center;
    }

    .user-info img {
        border: solid 10px #fff;
        width: 120px;
        height: 120px;
        margin-bottom: 10px;
    }

    .user-info .name {
        color: #0ba3ff;
        font-size: 14px;
        margin-bottom: 10px;
    }

    .user-info .fly-info {
        text-align: left;
        border: solid 1px #d9f1ff;
        background: #fff;
        color: #717171;
        padding: 15px;
        font-size: 14px;
    }

    .user-info .fly-info li {
        margin-bottom: 15px;
    }

    .user-info .fly-info li:last-child {
        margin-bottom: 0px;
    }

    .user-info .fly-info i,
    .user-info .fly-info span {
        margin-right: 8px;
    }

    .user-info .fly-info .value {
        color: #0ba3ff;
    }

</style>
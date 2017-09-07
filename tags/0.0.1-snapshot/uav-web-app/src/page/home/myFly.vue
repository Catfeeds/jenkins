<template>
    <div>
        <div class="loginBg">
            <div class="mengceng">
                <div class="maxW" style="position: relative;height:500px;" id="login">
                    <div class="loginWindowWin slideInRight animated">
                        <el-tabs activeName="first" type="card">
                            <el-tab-pane label="用户登录" name="first">
                                <div>
                                    <el-form label-width="80px" label-position="right">
                                        <el-form-item label="用户名：">
                                            <el-input v-model="loginUser.username" style="width:80%;"></el-input>
                                        </el-form-item>
                                        <el-form-item label="密       码：">
                                            <el-input type="password" v-model="loginUser.password"
                                                      style="width:80%;"></el-input>
                                        </el-form-item>
                                        <el-form-item label="">
                                            <el-radio class="radio" v-model="loginUser.type" label="1">个人帐号</el-radio>
                                            <el-radio class="radio" v-model="loginUser.type" label="2">企业帐号</el-radio>
                                        </el-form-item>
                                    </el-form>
                                </div>
                            </el-tab-pane>

                            <div>
                                <div id="captcha">
                                </div>
                                <p v-if="showCaptcha">正在加载验证码......</p>
                            </div>
                            <div class="clear" style="padding-left:20px;padding-right:20px;margin-top:20px;">
                                <el-checkbox class="left" style="margin-left:55px;" v-model="checked"
                                             @change="rememberME">记住我
                                </el-checkbox>
                                <div class="clear"><a href="#/register/password" class="underline right"
                                                      style="margin-right:55px;">忘记密码？</a></div>
                            </div>

                            <div class="loginbtn">
                                <el-button type="primary" @click="login" style="width:67%;" :disabled="disabled">登录
                                </el-button>
                            </div>
                            <div style="padding:20px 20px 50px ;">
                                <h3 style="margin-bottom:10px;margin-right:25px;margin-top:5px;" class="left">没有帐号？</h3>
                                <ul class="clear left">
                                    <a href="#/register/personage" class="left register1">
                                        <li> 个人注册</li>
                                    </a>
                                    <a href="#/register/firm" class="left register1">
                                        <li> 企业注册</li>
                                    </a>
                                </ul>
                            </div>
                        </el-tabs>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import urls from '../../components/urls'
    export default{
        methods: {
            rememberME(event){
                if (event.target.checked == true) {
                    localStorage.setItem('remember', this.loginUser.username);
                }
            },
            //登录
            login(){
                var result = this.captchaObj.getValidate();
                if (!result) {
                    this.$message('请完成验证');
                    return;
                }
                if (this.checked == true) {
                    localStorage.setItem('remember', this.loginUser.username);
                }
                $.ajax({
                    url: urls.GET_LOGIN,
                    type: 'POST',
                    data: {
                        username: this.loginUser.username,
                        password: this.loginUser.password,
                        type: this.loginUser.type,
                        geetest_challenge: result.geetest_challenge,
                        geetest_validate: result.geetest_validate,
                        geetest_seccode: result.geetest_seccode,
                    },
                    success: function (data, textStatus, jqXHR) {
                        if (jqXHR.status === 200) {
                            this.$store.dispatch('setSession',data.data);
                            window.location.href = "#/flyplan";
                        }
                    }.bind(this),
                    error: function (xhr) {
                        if (xhr.response != "") {
                            this.captchaObj.reset();
                            this.disabled = true;
                        }
                    }.bind(this)
                });
            }

        },
        data(){
            return {
                checked: false,
                loginUser: {
                    username: '',
                    password: '',
                    type: '1'
                },
                //是否显示验证码
                showCaptcha: true,
                //输入框是否可用
                disabled: true,

                activeName2: 'first'
            }
        },
        mounted(){
            if (localStorage.getItem('remember')) {
                this.loginUser.username = localStorage.getItem('remember');
            }
            var self = this;
            var handler = function (captchaObj) {
                if(self.captchaObj == null){
                    self.captchaObj = captchaObj;
                    console.log('append captchaObj');
                    captchaObj.appendTo('#captcha');
                    captchaObj.onReady(function () {
                        self.showCaptcha = false;
                    });
                    captchaObj.onSuccess(function () {
                        var result = captchaObj.getValidate();
                        self.disabled = false;
                    });
                }
            };

            $.ajax({
                // 获取id，challenge，success（是否启用failback）
                url: urls.GET_GT + "?t=" + (new Date()).getTime(), // 加随机数防止缓存
                type: "get",
                dataType: 'json',
                success: function (data) {
                    initGeetest({
                        gt: data.gt,
                        challenge: data.challenge,
                        new_captcha: data.new_captcha,
                        product: "embed", // 产品形式，包括：float，embed，popup。注意只对PC版验证码有效
                        offline: !data.success // 表示用户后台检测极验服务器是否宕机，一般不需要关注
                        // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
                    }, handler);
                },

            });


        }
    }
</script>

<style lang="less" scoped>

    .loginbtn {
        text-align: center;
        padding: 20px 0;
        border-bottom: 1px dashed #cccccc;
    }

    .register1 {
        margin-right: 20px;
        padding: 5px 20px;
        border: 1px #ccc solid;
    }

    #captcha {
        width: 300px;
        margin: 0 auto;
    }

    .show {
        display: block;
    }

    .hide {
        display: none;
    }

</style>
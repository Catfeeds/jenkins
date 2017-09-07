<template>
    <div>
        <div class="zaixianliuyan">

        </div>
        <el-form class="me" ref="messa" :model="messa" :rules="rules" label-width="80px">
            <h3 style="color:#999;">在线留言</h3>
            <el-form-item prop="name" style="margin-top:20px;" label="姓名：">
                <el-input v-model="messa.name" placeholder="姓名"></el-input>
            </el-form-item>
            <el-form-item prop="mail" label="邮箱：">
                <el-input v-model="messa.mail" placeholder="邮箱"></el-input>
            </el-form-item>
            <el-form-item prop="num" label="电话：">
                <el-input v-model="messa.num" placeholder="电话号码"></el-input>
            </el-form-item>
            <el-form-item style="width:60%;" prop="message" label="留言：">
                <el-input type="textarea" :rows='7' v-model="messa.message" placeholder="输入留言信息，最多输入200字"></el-input>
            </el-form-item>
            <div>
                <div id="captcha" style="margin-bottom:25px;margin-left:50px;"></div>
                <p id="wait" class="show">正在加载验证码......</p>
            </div>
            <el-form-item>
                <el-button type="primary" style="padding:15px 70px;" id="btn" :disabled="disabled">提交</el-button>
            </el-form-item>
        </el-form>

    </div>
</template>

<script>
    import urls from '../../components/urls'
    import '../../images/cut/7.jpg'
    export default{
        methods: {
            onSubmit(forName){

                var _this = this;
                this.$refs[forName].validate((valid) => {

                    if (valid) {

                        $.ajax({
                            type: 'POST',
                            url: urls.GET_LIUYAN,
                            data: {
                                name: _this.messa.name,
                                email: _this.messa.mail,
                                phone: _this.messa.num,
                                content: _this.messa.message
                            },
                            success: function (data, textStatus, jqXHR) {
                                _this.$message({
                                    message: '您的在线留言已成功提交，我们客服将在7个工作日内及时回复您，请留意您的邮箱和手机信息，感谢您的关注',
                                    type: 'success'
                                });
                            }
                        });
                    } else {
                        this.$message('请填写完整的留言信息');
                        return false;
                    }
                });


            }
        },
        data(){
            var validatePass = (rule, value, callback) => {
                var res = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;

                if (res.test(value)) {
                    callback();
                } else {
                    callback(new Error('邮箱格式错误'));
                }

            };
             var Phone = (rule, value, callback) => {
            	var myreg =/^1[34578]\d{9}$/;
            	if(!myreg.test(this.messa.num)){
            		return callback(new Error(rule.message));
            	}
                callback();
            };
            return {
                disabled: true,
                messa: {
                    name: '',
                    mail: '',
                    num: '',
                    message: ''
                },
                rules: {
                    name: [
                        {required: true, message: '请输入姓名', trigger: 'blur'},
                        {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'}
                    ],
                    mail: [
                        {required: true, message: '请输入邮箱', trigger: 'blur'},
                        {validator: validatePass, trigger: 'blur'}
                    ],
                    num: [
                        {required: true, message: '请输入联系方式', trigger: 'blur'},
                        {min: 11, max: 11, message: '请正确输入联系方式', trigger: 'blur'},
                        {validator: Phone,trigger: 'blur',message:"请对照手机号码格式"}
                    ],
                    message: [
                        {required: true, message: '留言内容不能为空', trigger: 'blur'},
                        {required: true,max: 200, message: '留言最多只能输入200字', trigger: 'blur'}
                        ]
                }

            }
        },
        mounted(){
            sessionStorage.setItem("kjkey", "5");
            var _this = this;
            var handler = function (captchaObj) {
                captchaObj.appendTo('#captcha');
                captchaObj.onReady(function () {
                    $("#wait")[0].className = "hide";
                });
                captchaObj.onSuccess(function () {
                    var result = captchaObj.getValidate();
                    _this.disabled = false;
                });
                $('#btn').click(function (e) {
                    var result = captchaObj.getValidate();
                    
                    if (!result) {
                        return alert('请完成验证');
                    }


                    $.ajax({
                        type: 'POST',
                        url: urls.GET_LIUYAN,
                        data: {
                            name: _this.messa.name,
                            email: _this.messa.mail,
                            phone: _this.messa.num,
                            content: _this.messa.message,
                            geetest_challenge: result.geetest_challenge,
                            geetest_validate: result.geetest_validate,
                            geetest_seccode: result.geetest_seccode,
                        },
                        success: function (data, textStatus, jqXHR) {
                            _this.$message({
                                message: '您的线留言已成功提交，我们客服将在7个工作内及时回复您，请留意您的邮箱和手机信息，感谢您的关注',
                                type: 'success'
                               
                            });
            
                          setTimeout(function(){
                          	window.location.reload();
                          },2000);

                        },
                        error: function (xhr) {
                            if (xhr.response != "") {
                                _this.$message({
                                    message: '留言失败',
                                    type: 'warning'
                                });
                                captchaObj.reset();
                                _this.disabled = true;

                            }

                        }

                  
                    });

               
                });
                  

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

    .me {

        max-width: 1024px;
        margin: 20px auto;
    }

    .el-input__inner {
        width: 30%;
    }
</style>
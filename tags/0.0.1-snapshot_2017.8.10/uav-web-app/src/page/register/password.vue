<template>
    <div>
        <div class="zhuce">
            <div class="maxW">
                <ul class=" clear  register">
                    <a><li class="left" >找回密码</li></a>
                </ul>
                
            </div>
            
        </div>
        <div class="maxW">
                <el-form style="margin-top:25px;" ref="form" :model="form" label-width="120px" :rules="rules" label-position="left">
                    <el-form-item label="手机号码："  prop="tel">
                        <el-input v-model="form.tel" style="width:30%;" placeholder="注册的手机号码"></el-input>
                        <el-button type="primary" @click="send" :disabled="asd">{{timeback}}</el-button>
                     
                    </el-form-item>
                    <el-form-item  label="验证码："  prop="code" >
                        <el-input type="primary" v-model="form.code" placeholder="请输入验证码" style="width:30%;"></el-input>
                    </el-form-item>
                    <el-form-item  label="新密码："  prop="password" auto-complete="off" >
                        <el-input type="password" v-model="form.password" style="width:30%;" placeholder="请输入新密码"></el-input>
                    </el-form-item>
                    <el-form-item  label="确认密码："  prop="pwsAgin" auto-complete="off">
                        <el-input type="password" v-model="form.pwsAgin" style="width:30%;" placeholder="请再次输入密码"></el-input>
                    </el-form-item>
                   
                    <div><el-button type="primary" @click="submit('form')">提交</el-button></div>
                </el-form>
            </div>
        
        
     </div>
</template>

<script>
import urls from '../../components/urls'
import '../../images/cut/2.jpg'
    export default{
        methods:{
           submit(form){
               var _this=this;
                this.$refs[form].validate((valid) => {
                    if (valid) {
                        $.ajax({
                        type: 'POST',
                        url: urls.UPDATE_PASSWORD,
                        data:{
                            newPassword:_this.form.password,
                            code:_this.form.code
                        },
                        success:function(data,textStatus,jqXHR){
                            _this.$message({
                                message:'密码重置成功',
                                type:'warning'
                            });
                            window.location.href="#/login";
                        },
                        
                    });
                    }
                });

           },
           send(){
               var _this=this;

               this.asd=true;

               clearInterval(timer);
               var timer=null;

               timer=setInterval(time,1000);
                    var a=60;
                    function time(){
                        a--;
                        _this.timeback=a;

                        if(a<=0){
                            _this.asd=false;
                            _this.timeback='点击发送短信';
                            clearInterval(timer);
                        }
                        
                    };
                    $.ajax({
                        type: 'GET',
                        url: urls.GET_PASSWORDMESSAGE ,
                        data:{
                            phone:_this.form.tel
                        },
                        success:function(data,textStatus,jqXHR){
                        },
                        error:function(){
                            clearInterval(timer);
                            _this.asd=false;
                            _this.timeback='点击发送短信';
                        }
                        
                    });
           }
        },
        data(){
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                callback(new Error('请输入密码'));
                } else {
                if (this.form.password !== '') {
                    this.$refs.form.validateField('pwsAgin');
                }
                if(this.form.password.length<6){
                    callback(new Error('密码长度不能小于6'));
                }
                callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                callback(new Error('请再次输入密码'));
                } else if (value !== this.form.password) {
                callback(new Error('两次输入密码不一致!'));
                } else {
                callback();
                }
            };
            return{ 
                asd:false,
                timeback:'点击发送短信',
                 form:{
                     tel:'',
                     password:'',
                     pwsAgin:''
                 },
                 rules:{
                    
                    tel:[
                        {required: true, message: '请输入电话号码', trigger: 'blur'},
                        { min: 11, max: 11, message: '电话号码长度为11位', trigger: 'blur' }
                        ],
                    code:[
                        {required: true, message: '请输入验证码', trigger: 'blur'},
                    ],
                    password:[
                            {required: true, message: '请输入密码', trigger: 'blur'},
                            { validator: validatePass, trigger: 'blur' },
                             { min: 6, message: '密码长度至少6位', trigger: 'blur' }
                    ],
                    pwsAgin:[
                            {required: true, message: '请再次输入密码', trigger: 'blur'},
                            { validator: validatePass2, trigger: 'blur' },
                             { min: 6, message: '密码长度至少6位', trigger: 'blur' }
                    ],
                   
                },
               
            }
        },
         mounted(){}
           
    }
</script>

<style lang="less" >

</style>
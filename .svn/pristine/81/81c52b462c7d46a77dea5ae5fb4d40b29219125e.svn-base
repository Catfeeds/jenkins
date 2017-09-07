<template>
    <div>
        <div class="plan">
            <div class="title clear" style="padding:15px 15px;">
                <p class="left" style="font-size:18px"><span>企业信息编辑</span></p>
                <div class="right">
                    <el-button ><a href="#/myplan">返回</a></el-button>
                </div>
            </div>

            <div class="plan-form" >
                <el-form  label-position="left"  label-width="120px" :model="company" ref="company" :rules="rules">
                    <div class="plan-form-title">
                        <div class="devider"></div>
                        <p class="text">基础信息</p>
                    </div>
                    <div class="plan-form-content" style="padding-left:20px;">
                        <el-form-item label="用户名">
                            {{company.userName}}
                        </el-form-item>
                        <el-form-item label="单位名称" >
                            {{company.companyName}}
                        </el-form-item>
                        <el-form-item label="单位联系人">
                            {{company.contactName}}
                        </el-form-item>
                        <el-form-item label="注册手机号码" >
                            {{company.contactPhone}}
                        </el-form-item>
                        <el-form-item label="社会统一信用代码">
                            {{company.licenseNumber}}
                        </el-form-item>
                        <el-form-item label="营业执照">
                            <img :src="company.licensePic" style="width:100px;height:100px;margin-right:10px;"/>
                        </el-form-item>
                        <el-form-item label="单位地址" style="width:60%;">
                            <el-select style="width:100px;" v-model="company.province"   @input="gg1" placeholder="请选择">
                                <el-option v-for="Sheng in place.sheng"   :label="Sheng.name" :value="Sheng"></el-option>
                            </el-select>
                            <el-select style="width:100px;" v-model="company.city" @input="gg2"  placeholder="请选择">
                                <el-option v-for="Shi in place.shi"   :label="Shi.name" :value="Shi"></el-option>
                            </el-select>
                            <el-select style="width:100px;" v-model="company.are"  placeholder="请选择">
                                <el-option v-for="shi1 in place.shi"   :label="shi1.name" :value="shi1.name"></el-option>
                            </el-select>
                          
                            <el-input style="margin-top:15px;"  type="textarea" :rows="7" v-model="xian" placeholder="详细地址"></el-input>
                        </el-form-item>
                        <el-form-item label="公司邮箱" prop="email" style="width:40%;">
                            <el-input v-model="company.email" ></el-input>
                        </el-form-item>
                        <el-form-item label="公司法人"  style="width:40%;">
                            <el-input v-model="company.companyLegal" ></el-input>
                        </el-form-item>
                        
                    </div>
                    <div class="plan-form-title">
                        <div class="devider"></div>
                        <p class="text">紧急联系人</p>
                    </div>
                    <div class="plan-form-content" style="padding-left:20px;">
                        <el-form-item label="姓名" prop="emergencyContactName" style="width:40%;">
                            <el-input v-model="company.emergencyContactName" ></el-input>
                        </el-form-item>
                        <el-form-item label="手机号码" prop="emergencyContactPhone" style="width:40%;">
                            <el-input v-model="company.emergencyContactPhone"></el-input>
                        </el-form-item>
                    </div>
                    <el-form-item >
                            <el-button type="primary" @click="updateCom">提交修改</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
    import urls from '../../components/urls'
    import Vue from 'vue'
    import util from '../../components/util'

    export default{
        methods: {
            updateCom(){
                var _this=this;
                
                this.company.address=this.company.province+" "+this.company.city+" "+this.company.are+" "+this.xian;
                $.ajax({
                    type: 'PUT',
                    url: urls.UPDATE_COMINFORMATION ,
                    data:  {
                        companyAddress:_this.company.address,
                        email:_this.company.email,
                        companyLegal:_this.company.companyLegal,
                        emergencyContactName:_this.company.emergencyContactName,
                        emergencyContactPhone:_this.company.emergencyContactPhone
                    },
                    success: function(){
                        _this.$message({
                            message:'修改成功',
                            type:'success'
                        })
                        window.location.href="#/myplan";
                    } ,
                    dataType: 'json'

                });
            },
            gg1(value){
               
                var _this=this;
               
              
                    this.company.city="";
                    this.company.are="";
                    this.xian="";
                   
                    this.company.province=value.name;
            
                  var URL=urls.GET_PROVINCE+"?id="+value.id;
                  $.ajax({
                    type: 'GEt',
                    url: URL,
                
                    success: function(data){
                        
                        _this.place.shi=data.data;
                        

                    },
                    dataType: 'json'
                });
               
           },
           gg2(value){
               var _this=this;
               this.company.are="";
               this.xian="";
                    
                this.company.city=value.name;
               
                var URL=urls.GET_PROVINCE+"?id="+value.id;
                  $.ajax({
                    type: 'GEt',
                    url: URL,
                
                    success: function(data){
                        
                        _this.place.qu=data.data;
                     
                    },
                    dataType: 'json'
                    });
           },
           gg3(value){
               this.xian="";
              
           }
        },
        data(){
            var validatePass4 = (rule, value, callback) => {
                var res=/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
               if(value!=""){
                   if(res.test(value)){
                    callback();
                }else{
                    callback(new Error('请邮箱格式错误'));
                }
               }else{
                   callback();
               }
                
                
            };
            return {
                rules:{
                    emergencyContactName:[
                        { required: true, message: '请输入紧急联系人名字', trigger: 'blur' },
                        
                        { min: 2, message: '长度在 2 到 10 个字符', trigger: 'blur' }
                    ],
                   
                    emergencyContactPhone:[
                        {required: true, message: '请输入紧急联系人电话', trigger: 'blur'},
                      
                        { min: 11, max: 11, message: '电话号码长度为11位', trigger: 'blur' }
                    ],
                     email:[ 
                        {required: true, message: '请再次输入邮箱', trigger: 'blur'},
                        {validator: validatePass4, trigger: 'blur'}
                        ],
                   
                },
                place:
                    {
                        sheng:'',
                       shi:'',
                        
                    },
                xian:'',
                region:[
                    
                    {
                        label:'飞行员证书',
                        value:1
                    },
                ],
                xian:'',
                company:{
                    emergencyContactName:'',
                    emergencyContactPhone:'',
                    address:'',
                    email:'',
                    city:'',
                    email:'',
                    province:'',
                    are:'',
                },                
            }
        },
        mounted(){


            var _this=this;

            $.ajax({
                type: 'GET',
                url: urls.GET_UPDATEMESSAGE ,
                
                success: function(res){
                    _this.company = Vue.util.extend(_this.company,res.data);
                    console.log(_this.company);
                    _this.company.address=_this.company.companyAddress.split(" ");
                    
                    _this.company.province=_this.company.address[0];
                    _this.company.city=_this.company.address[1];
                    _this.company.are=_this.company.address[2];
                    _this.xian=_this.company.address[3];
                   
                } ,
                dataType: 'json'

            });

             $.ajax({
                type: 'GET',
                url: urls.GET_PROVINCE ,
               
                success: function(data){
                    _this.place.sheng=data.data;
                    
                } ,
                dataType: 'json'
            });
        }
    }
</script>

<style lang="less" scoped>

</style>
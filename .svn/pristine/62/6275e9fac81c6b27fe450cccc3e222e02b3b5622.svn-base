<template>
    <div>
        <div class="plan">
            <div class="title clear" style="padding:15px 15px;">
                <p class="left" style="font-size:18px"><span>个人信息编辑</span></p>
                <div class="right">
                    <el-button ><a href="#/myplan">返回</a></el-button>
                </div>
            </div>

            <div class="plan-form" >
                <el-form  label-position="left"  label-width="120px" :model="person" :rules="rules" ref="person">
                    <div class="plan-form-title">
                        <div class="devider"></div>
                        <p class="text">基础信息</p>
                    </div>
                    <div class="plan-form-content" style="padding-left:20px;">
                        <el-form-item label="用户名">
                            {{person.userName}}
                        </el-form-item>
                        <el-form-item label="姓名" >
                            {{person.name}}
                        </el-form-item>
                        <el-form-item label="注册手机号码" >
                            {{person.phone}}
                        </el-form-item>
                        <el-form-item label="出生日期" prop="birthday">
                            {{person.birthday}}
                        </el-form-item>
                        <el-form-item label="性别" >
                            <span v-if="person.sex=='M'">男</span>
                            <span v-if="person.sex=='F'">女</span>
                        </el-form-item>
                        <el-form-item label="身份证号">
                            {{person.idCard}}
                        </el-form-item>
                        <el-form-item label="身份证照片">
                            <img :src="person.idcardPic1" style="width:100px;height:100px;margin-right:10px;"/>
                            <img :src="person.idcardPic2" style="width:100px;height:100px;margin-right:10px;"/>
                            <img :src="person.idcardPic3" style="width:100px;height:100px;margin-right:10px;"/>
                        </el-form-item>
                        <el-form-item label="证件编号" class="clear">
                            <el-input placeholder="请输入内容" v-model="person.certificateNumber" style="width:280px;">
                                <el-select v-model="person.certificateType" slot="prepend" placeholder="请选择" style="width:150px;">
                                    <el-option v-for="type in this.$store.state.certTypes" :label="type.label" :value="type.value"></el-option>
                                </el-select>
                            </el-input>
                        </el-form-item>
                        
                    </div>
                    <div class="plan-form-title">
                        <div class="devider"></div>
                        <p class="text">其他联系方式</p>
                    </div>
                    <div class="plan-form-content" style="padding-left:20px;">
                        <el-form-item label="QQ" style="width:40%;">
                            <el-input v-model="person.qq"></el-input>
                        </el-form-item>
                        <el-form-item label="邮箱" prop="email" style="width:40%;">
                            <el-input v-model="person.email" ></el-input>
                        </el-form-item>
                        <el-form-item label="现居住地" style="width:60%;">
                            <el-select style="width:100px;" v-model="person.province"   @input="gg1" placeholder="请选择">
                                <el-option v-for="Sheng in place.sheng"   :label="Sheng.name" :value="Sheng"></el-option>
                            </el-select>
                            <el-select style="width:100px;" v-model="person.city" @input="gg2"  placeholder="请选择">
                                <el-option v-for="Shi in place.shi"   :label="Shi.name" :value="Shi"></el-option>
                            </el-select>
                            <el-select style="width:100px;" v-model="person.are"  placeholder="请选择">
                                <el-option v-for="qu in place.qu"   :label="qu.name" :value="qu.name"></el-option>
                            </el-select>
                          
                            <el-input style="margin-top:15px;"  type="textarea" :rows="7" v-model="xian" placeholder="详细地址"></el-input>
                        </el-form-item>
                    </div>
                    <div class="plan-form-title">
                        <div class="devider"></div>
                        <p class="text">紧急联系人</p>
                    </div>
                    <div class="plan-form-content" style="padding-left:20px;">
                        <el-form-item label="姓名" prop="emergencyContactName" style="width:40%;">
                            <el-input v-model="person.emergencyContactName" ></el-input>
                        </el-form-item>
                        <el-form-item label="手机号码" prop="emergencyContactPhone" style="width:40%;">
                            <el-input v-model="person.emergencyContactPhone"></el-input>
                        </el-form-item>
                    </div>
                    <el-form-item >
                            <el-button type="primary" @click="update">提交修改</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
    import urls from '../../components/urls'
    import util from '../../components/util'
    import Vue from 'vue'

    export default{
        methods: {
            update(){
                var _this=this;
                this.address=this.person.province+" "+this.person.city+" "+this.person.are+" "+this.xian;
                $.ajax({
                    type: 'PUT',
                    url: urls.UPDATE_PERINFORMATION ,
                    data: {
                        certificateType:_this.person.certificateType,
                        certificateNumber:_this.person.certificateNumber,
                        qq: _this.person.qq,
                        email:_this.person.email,
                        address:_this.address,
                        emergencyContactName:_this.person.emergencyContactName,
                        emergencyContactPhone:_this.person.emergencyContactPhone
                    } ,
                    success: function(){ 
                        _this.$message({
                            message:'修改成功',
                            type:'success'
                        })
                        window.location.href="#/myplan";
                    } ,
                    dataType:'json'
                });
            },
            gg1(value){
               
                var _this=this;
               
              
                    this.person.city="";
                    this.person.are="";
                    this.xian="";
                   
                    this.person.province=value.name;
            
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
               this.person.are="";
               this.xian="";
                    
                this.person.city=value.name;
               
                var URL=urls.GET_PROVINCE+"?id="+value.id;
                  $.ajax({
                    type: 'GEt',
                    url: URL,
                
                    success: function(data){
                        
                        _this.place.qu=data.data;
                        console.log(_this.place.qu)
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
                        qu:''
                    },
                xian:'',
                address:'',
                person:{
                    userName:'',
                    name:'',
                    sex:'',
                    birthday:'',
                    idCard:'',
                    idcardPic1:'',
                    idcardPic2:'',
                    idcardPic3:'',
                    emergencyContactName:'',
                    emergencyContactPhone:'',
                    email:'',
                    city:'',
                    province:'',
                    are:'',
                    certificateType:this.$store.state.idCardTypes[0].label,
                    certificateNumber:'',
                },
               
            }
        },
        mounted(){
             
            var _this=this;
            var a,b,c=null;
            $.ajax({
                type: 'GET',
                url: urls.GET_UPDATEMESSAGE,
               
                success: function(res){
                    _this.person = Vue.util.extend(_this.person,res.data)
                    _this.person.address=_this.person.address.split(" ");
                    
                    _this.person.province=_this.person.address[0];
                    _this.person.city=_this.person.address[1];
                    _this.person.are=_this.person.address[2];
                    _this.xian=_this.person.address[3];

                    _this.place.sheng.forEach(function(item,index){
                        if(_this.place.sheng[index].name==_this.person.province){
                            a=_this.place.sheng[index].id;
                    }});
                    if(a!=null){
                        var URL=urls.GET_PROVINCE+"?id="+a;
                        $.ajax({
                            type: 'GEt',
                            url: URL,
                            success: function(data){
                                
                                _this.place.shi=data.data;
                                _this.place.shi.forEach(function(item,index){
                                    if(_this.place.shi[index].name==_this.person.city){
                                        b=_this.place.shi[index].id;
                                }});
                                if(b!=null){
                                    var URL=urls.GET_PROVINCE+"?id="+b;
                                    $.ajax({
                                        type: 'GEt',
                                        url: URL,
                                    
                                        success: function(data){
                                            
                                            _this.place.qu=data.data;
                                            console.log(_this.place.qu);

                                        },
                                        dataType: 'json'
                                    });
                                }

                            },
                            dataType: 'json'
                        });
                    }
                } ,
                dataType: 'json'
            });

        },

         created(){
             var _this=this;
             $.ajax({
                type: 'GET',
                url: urls.GET_PROVINCE ,
                success: function(data){
                    _this.place.sheng=data.data;
                } ,
                dataType: 'json'
            });
      
      },
    }
</script>

<style lang="less" scoped>

</style>
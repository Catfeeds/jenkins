<template>
    <div class="plan" v-loading="isLoading"
         element-loading-text="数据提交中，请稍候...">
        <div class="title clear">
            <p class="left" style="font-size:18px">创建I类报告空域飞行计划</p>
            <div class="right">
                <el-button type="primary" @click="submit('flyplan')">提交</el-button>
                <el-button @click="back">返回</el-button>
            </div>
        </div>
        <div class="tips">提示:带<span color="red">*</span>号为必填项</div>
        <div class="plan-form">
            <el-form ref="flyplan" label-position="left" :model="flyplan" :rules="rules"
                     label-width="120px">
                <div class="plan-form-title">
                    <div class="devider"></div>
                    <p class="text">基础信息</p>
                </div>
                <div class="plan-form-content">
                    <el-form-item label="申请个人/单位">
                        {{user.name}}
                    </el-form-item>            
                    <!--计划类型-->
                    <el-form-item label="计划类型" prop="planType" auto-complete="off">
                        <el-radio-group v-model="flyplan.planType" @change="flyTypeChange">
                            <el-radio v-for="type in planTypes" :label="type.label"></el-radio>
                        </el-radio-group>           
                        <el-input style="margin-left:10px;width:150px;" :disabled="planTypeInputDisable"
                                  v-model="flyplan.planTypeName"></el-input>
                    </el-form-item>
                    <!--使用机型-->
                    <el-form-item label="使用机型" class="clear" prop="planes" auto-complete="off">
                        <el-checkbox-group v-for="plane in flyplan.planes" class="left" style="margin-bottom:5px;"
                                           v-model="plane.checked">
                            <el-checkbox style="margin-right:11px;" :label="plane.name"></el-checkbox>
                            <el-input v-if="plane.name=='其他'" :disabled="!plane.checked" style="width:150px;"
                                      v-model="plane.desc" placeholder="请输入内容"></el-input>
                            <el-input-number :disabled="!plane.checked" style="width:120px;margin-right:10px;"
                                             v-model="plane.number" :min="0" :max="99"></el-input-number>
                        </el-checkbox-group>
                    </el-form-item>
                    <el-form-item label="使用时间" prop="planTime">
                        <el-date-picker
                                v-model="flyplan.planTime.date"
                                type="date"
                                placeholder="选择日期"
                               :picker-options="pickerOptions" style="z-index: -1;">
                        </el-date-picker>
                            <el-select style="width:90px;" v-model="flyplan.planTime.beginHour" placeholder="请选择">
                                <el-option v-for="hour in selectableFlyBeginHours" :label="hour.label"
                                           :value="hour.label"></el-option>
                            </el-select>
                            <el-select style="width:90px;" v-model="flyplan.planTime.beginMinute" placeholder="请选择">
                                <el-option v-for="minute in selectableFlyBeginMinutes" :label="minute.label"
                                           :value="minute.label"></el-option>
                            </el-select>
                            <strong>至</strong>
                            <el-select style="width:90px;" v-model="flyplan.planTime.endHour" placeholder="请选择">
                                <el-option v-for="hour in selectableFlyEndHours" :label="hour.label"
                                           :value="hour.label"></el-option>
                            </el-select>
                            <el-select style="width:90px;" v-model="flyplan.planTime.endMinute" placeholder="请选择">
                                <el-option v-for="minute in selectableFlyEndMinutes" :label="minute.label"
                                           :value="minute.label"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="计划空域及高度" prop="airInfo" >
                        <div>
                            <el-select style="width:100px;" v-model="selectedProvince" placeholder="请选择" @change="handleChangePro">
                                <el-option v-for="province in selectableProvince" :label="province.provinceName"
                                           :value="province.provinceCode"  ></el-option>
                            </el-select>
                            <el-select style="width:100px;" v-model="selectedCity" placeholder="请选择"  @change="handleChangeCity">
                                <el-option v-for="city in selectableCities" :label="city.cityName"
                                           :value="city.cityCode"></el-option>
                            </el-select>

                            <el-select style="width:150px;" v-model="flyplan.airInfo[0]" placeholder="请选择" ></i>
                                <el-option v-for="airspace in selectableAirSpaces" :label="airspace.location"
                                           :value="airspace" @change="handleChangeCity"></el-option>
                            </el-select>
                            <el-popover
                                    v-if="flyplan.airInfo[0]!=null"
                                    ref="airInfoMapPopover"
                                    placement="right"
                                    trigger="click">
                                <img style="width:400px;height:400px;" :src="flyplan.airInfo[0].sketchMap">
                            </el-popover>
                            <el-button v-if="flyplan.airInfo[0]!=null" type="text" class="underline" v-popover:airInfoMapPopover>查看空域位置</el-button>
                        </div>
                          <div style="color:red;"><span>{{flyplan.airInfo[0].high}}</span></div>
                    </el-form-item>

                    <el-form-item label="起降场" prop="landingInfo.location">
                        <el-select style="width:100px;" v-model="flyplan.landingInfo" placeholder="请选择">
                            <el-option v-for="landing in this.$store.state.landings" :label="landing.name"
                                       :value="landing"></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="起降场位置描述" prop="landingInfo.location" v-if="flyplan.landingInfo.id==-1">
                        <el-input style="width:400px;" v-model="flyplan.landingInfo.location"
                                  placeholder="请输入内容"></el-input>
                        <div style="font-style:italic;color:#3366ff;">请以选择空域内起降场附近的地标或建设物为参考</div>
                    </el-form-item>

                    <el-form-item label="起降场坐标" v-if="flyplan.landingInfo.id==-1">
                        <div class="clear">
                            <div class="left">
                                <span style="background:#169bd5;">经</span>
                                <el-input-number :controls="false" :debounce="0" style="margin-right:10px;width:50px;"
                                                 v-model="flyplan.landingInfo.lng0" :min="-180"
                                                 :max="180"></el-input-number>
                                <span>度</span>
                                <el-input-number :controls="false" :debounce="0" style="margin-right:10px;width:50px;"
                                                 v-model="flyplan.landingInfo.lng1" :min="0"
                                                 :max="60"></el-input-number>
                                <span>分</span>
                                <el-input-number :controls="false" :debounce="0" style="margin-right:10px;width:50px;"
                                                 v-model="flyplan.landingInfo.lng2" :min="0"
                                                 :max="60"></el-input-number>
                                <span>秒</span>
                            </div>
                            <div class="left" style="margin-left:25px;">
                                <span style="background:#169bd5;">纬</span>
                                <el-input-number :controls="false" :debounce="0" style="margin-right:10px;width:50px;"
                                                 v-model="flyplan.landingInfo.lat0" :min="-90"
                                                 :max="90"></el-input-number>
                                <span>度</span>
                                <el-input-number :controls="false" :debounce="0" style="margin-right:10px;width:50px;"
                                                 v-model="flyplan.landingInfo.lat1" :min="0"
                                                 :max="60"></el-input-number>
                                <span>分</span>
                                <el-input-number :controls="false" :debounce="0" style="margin-right:10px;width:50px;"
                                                 v-model="flyplan.landingInfo.lat2" :min="0"
                                                 :max="60"></el-input-number>
                                <span>秒</span>
                            </div>
                        </div>
                        <div style="font-style:italic;color:#3366ff;">仅使用自选起降场时填写</div>
                    </el-form-item>
                </div>
                <div class="plan-form-title">
                    <div class="devider"></div>
                    <p class="text">联系方式</p>
                </div>
                <div class="plan-form-content clear">
                        <el-form-item label="教练员/飞行员姓名" prop="contactInfo.trainer">
                            <div style="border: 1px solid #bfcbd9;padding:5px;border-radius: 5px;">
                                <el-tag style="margin-right:8px;" :key="t" v-for="t in flyplan.contactInfo.trainer"
                                        :closable="true" :close-transition="false" @close="handleRemoveTrainer(t)">{{t}}
                                </el-tag>
                                <el-input style="width:100px;" v-if="trainerInputVisible" v-model="trainerInputValue"
                                          ref="addTrainerInput" size="mini" @keyup.enter.native="handleAddTrainer"
                                          @blur="handleAddTrainer"></el-input>
                                <el-button v-else style="width:70px;" class="button-new-tag" size="small"
                                           @click="showTrainerInput">+ 添加
                                </el-button>
                            </div>
                        </el-form-item>

                        <el-form-item label="学员数量" style="width:100%;" prop="contactInfo.traineeCount" >
                            <el-input-number  style="margin-right:10px;" :debounce="0"
                                             v-model="flyplan.contactInfo.traineeCount" :min="0" :disabled="planTypeInputW"></el-input-number>
                            <div style="font-style:italic;color:#bcc4c6;">除技能培训外该项不填</div>
                        </el-form-item>
                        <el-form-item label="现场联系人" style="width:61%;" prop="contactInfo.fieldContactName">
                            <el-input v-model="flyplan.contactInfo.fieldContactName" placeholder="请输入内容"></el-input>
                        </el-form-item>
                        <el-form-item label="手机号码" style="width:61%;" prop="contactInfo.fieldContactPhone">
                            <el-input v-model="flyplan.contactInfo.fieldContactPhone" placeholder="请输入内容"></el-input>
                        </el-form-item>

                        <el-form-item label="紧急联系人" style="width:61%;" prop="contactInfo.emergencyContactName">
                            <el-input v-model="flyplan.contactInfo.emergencyContactName" placeholder="请输入内容"></el-input>
                        </el-form-item>
                        <el-form-item label="手机号码" style="width:61%;" prop="contactInfo.emergencyContactPhone">
                            <el-input v-model="flyplan.contactInfo.emergencyContactPhone"
                                      placeholder="请输入内容"></el-input>
                        </el-form-item>
                </div>

                <div class="plan-form-title">
                    <div class="devider"></div>
                    <p class="text">其他</p>
                </div>
                <div class="plan-form-content clear">
                    <el-form-item label="气象条件" style="width:80%;">
                        <el-input v-model="flyplan.weatherCondition" placeholder="天气状态（如晴天）、风速（南风四级）、能见度（1000-4000米）"></el-input>
                    </el-form-item>
                    <el-form-item label="其他说明事项 " style="width:80%;"  prop="remark" >
                        <el-input type="textarea"  v-model="flyplan.remark" placeholder="请输入内容，最大允许填写100个字" @input="descInput()" :maxlength="100"></el-input>
                        <span style="color: #CCCCCC; font-size: 12px;" >可输入数字：{{remnant}}/100</span>
                    </el-form-item>
                </div>
                <el-form-item>
                    <el-button type="primary" @click="submit" style="padding:10px 50px;cursor: pointer;" >提交
                    </el-button>
                </el-form-item>
            </el-form>
        </div>


    </div>
</template>

<script>
	 
    import '../../style/plan.css'
    import util from '../../components/util'
    import urls from '../../components/urls'
    import Vue from 'vue'

    export default{
		
        methods: {
            handleChangePro:function(){
            	this.selectedCity = '';
            	this.flyplan.airInfo[0] = {};
            },            
            handleChangeCity:function(){
            	this.flyplan.airInfo[0] = {};            	
            },
            //处理增加教练动作
            handleAddTrainer: function (name) {
                var trainerInputValue = this.trainerInputValue;
                if (trainerInputValue) {
                    this.flyplan.contactInfo.trainer.push(trainerInputValue);
                }
                this.trainerInputVisible = false;
                this.trainerInputValue = '';
            },
            //处理删除教练动作
            handleRemoveTrainer: function (name) {
                var trainers = this.flyplan.contactInfo.trainer;
                trainers.splice(trainers.indexOf(name), 1);
            },
            //显示添加教练输入框
            showTrainerInput() {
                this.trainerInputVisible = true;
                this.$nextTick(_ => {
                    this.$refs.addTrainerInput.$refs.input.focus();
                });
            },
            //输入框控制字数长度
             descInput(){          
			 var txtVal = this.flyplan.remark.length;
			 this.remnant = 100 - txtVal;	
			
			  if (txtVal>=100) {
			   return false
			  }
			 },
            /**
             * 飞行计划改变的触发事件
             */
            flyTypeChange(value){
                if (value != "其他") {
                    this.planTypeInputDisable = true;
                } else {
                    this.planTypeInputDisable = false;
                }
                 if (value != "技能培训") {
                    this.planTypeInputW = true;
                } else {
                    this.planTypeInputW = false;
                }
         
         
            },
           
            back(){
                this.$router.go(-1);
            },
            /**
             * 提交表单
             * @param flyplan
             */
            submit(){
                var flyplanForm = this.$refs['flyplan'];
                var validate = true;
                for(var prop in this.rules){
                    flyplanForm.validateField(prop,function(errorMessage){
                        //如果field使用了validator，此处的errorMessage为空，目前暂时使用判空的解决办法
                        if(errorMessage != ''){
                            this.$message.error(errorMessage,5000);
                            validate = false;
                        }
                    }.bind(this));
                }

                if(!validate) return;

                this.isLoading = true;
                $.ajax({
                    url: urls.ADD_FLYPLANS,
                    method: 'post',
                    contentType: 'application/json',
                    data: this.getRequestBody(),
                    success: function (res) {

                        window.location.href = '#/myplan';
                    },
                    complete: function () {
                        this.isLoading = false;
                    }.bind(this)
                });
            },
            /**
             * 处理飞行计划表单
             */
            getRequestBody(){
                var flyplan = this.flyplan;
                //空域信息
                var airInfo = flyplan.airInfo.map(function(value){
                     return {
                         id:value.id,
                         name:value.name,
                         type:value.type,
                         provinceCode:value.provinceCode,
                         provinceName:value.provinceName,
                         cityCode:value.cityCode,
                         cityName:value.cityName,
                         areaCode: value.areaCode,                        
                         location:value.location,
                         high:value.high,
                         scopeType:2,
                         centers:[]
                     }
                });
                //起降场信息
                var tempLandingInfo = flyplan.landingInfo;
                var landingInfo = [Vue.util.extend({},{
                    id:tempLandingInfo.id,
                    name:tempLandingInfo.name,
                    location:tempLandingInfo.location
                })];

                //自选起降场才填写经纬
                if(tempLandingInfo.id == -1){
                    landingInfo[0].lng = tempLandingInfo.lng0 + "," + tempLandingInfo.lng1 + "," + tempLandingInfo.lng2;
                    landingInfo[0].lat = tempLandingInfo.lat0 + "," + tempLandingInfo.lat1 + "," + tempLandingInfo.lat2;
                }

                //联系方式
                var contactInfo = Vue.util.extend({},flyplan.contactInfo);
                contactInfo.trainer = contactInfo.trainer.join(",");


                var planes = flyplan.planes.filter(function(plane){
                    return plane.number > 0;
                }).map(function (plane) {
                    return {
                        name:plane.name=='其他'?plane.desc:plane.name,
                        number: plane.number
                    }
                });

                var request = {
                    type: flyplan.type,
                    planType: flyplan.planType == '其他' ? flyplan.planTypeName : flyplan.planType,
                    planes: planes,
                    beginTime:util.formatDate(flyplan.planTime.date, 'yyyy-MM-dd')  + " "
                    + flyplan.planTime.beginHour +":" +flyplan.planTime.beginMinute ,
                    endTime: util.formatDate(flyplan.planTime.date, 'yyyy-MM-dd') + " "
                    + flyplan.planTime.endHour +":" +flyplan.planTime.endMinute ,
                    //空域信息
                    airInfo: airInfo,
                    landingInfo: landingInfo,
                    contactInfo: contactInfo,
                    weatherCondition:flyplan.weatherCondition,
                    remark:flyplan.remark
                }
                return JSON.stringify(request);
            }
        },
        data(){
        	
            var planTypeValidator = (rule, value, callback) => {
                if (this.flyplan.planType == "其他" && this.flyplan.planTypeName == "") {
                    return callback(new Error(rule.message));
                }
                return callback();
            };
            var planesValidator = (rule, value, callback) => {
                var hasPlane = this.flyplan.planes.some(function (plane) {
                    return plane.checked == true && plane.number > 0;
                });
                var has0NumberPlane = this.flyplan.planes.some(function (plane) {
                    return plane.checked == true && plane.number == 0;
                });
                if (!hasPlane || has0NumberPlane) {
                    return callback(new Error(rule.message));
                }
                return callback();
            };
            var planTimeValidator = (rule, value, callback) => {
                if (this.flyplan.planTime.beginHour == ''|| this.flyplan.planTime.beginMinute ==
                ''|| this.flyplan.planTime.endHour  == '' || this.flyplan.planTime.endMinute == '') {
                    callback(new Error(rule.message));
                }
               return callback();
            };
            var airInfoValidator = (rule, value, callback) => {
                var airSpaceId = this.flyplan.airInfo[0].id;
                if (airSpaceId == null || airSpaceId == ''){
                    callback(new Error(rule.message));
                }
                callback();
            };
            var emergencyContactNameValidator = (rule, value, callback) =>{
                if (this.flyplan.contactInfo.fieldContactName == this.flyplan.contactInfo.emergencyContactName) {
                    callback(new Error(rule.message));
                }
                callback();
            };
            var planPhoneValidator = (rule, value, callback) => {
            	var myreg =/^1[34578]\d{9}$/;
            	if(!myreg.test(this.flyplan.contactInfo.fieldContactPhone)){
            		return callback(new Error("请填写正确手机号"));
            	}
                callback();
            };
            var planPhoneEmergency = (rule, value, callback) => {
            	var myreg =/^1[34578]\d{9}$/;
            	if(!myreg.test(this.flyplan.contactInfo.emergencyContactPhone)){
            		return callback(new Error("请填写正确手机号"));
            	}
                callback();
            };
            var emergencyContactPhoneValidator = (rule, value, callback) =>{
                if (this.flyplan.contactInfo.fieldContactPhone == this.flyplan.contactInfo.emergencyContactPhone) {
                    callback(new Error(rule.message));
                }
                callback();
            };

            var trainerValidator = (rule, value, callback) =>{
                if (this.flyplan.contactInfo.trainer.length == 0) {
                    callback(new Error(rule.message));
                }
                callback();
            };

            var traineeCountValidator = (rule, value, callback) =>{
                if (this.flyplan.planType == '技能培训' && this.flyplan.contactInfo.traineeCount == 0) {
                    callback(new Error(rule.message));
                }
                callback();
            };

            //机型及其数量
            var planes = this.$store.state.planeTypes.map(function (value) {
                return {
                    name: value.label,
                    number: 0,
                    checked: false,
                }
            });

            return {
            	
                //表单验证器
                rules: {
                    'planType': [{validator: planTypeValidator, message:'请填写计划类型',trigger: 'blur', required: true,}],
                    'planes': [{validator: planesValidator, message:'请至少选择一种机型，且机型数量不能为0',trigger: 'blur', required: true,}],
                    'planTime': [{validator: planTimeValidator, required: true, message: '请输入完整的计划开始时间和结束时间', trigger: 'blur'}],
                    'airInfo': [{validator: airInfoValidator, required: true, message: '请选择一个计划空域', trigger: 'blur'}],
                    'landingInfo.location': [{required: true, message: '请输入起降场', trigger: 'blur'}],
                    'contactInfo.fieldContactName': [
                        {required: true, message: '请输入现场联系人名字', trigger: 'blur'},
                        {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'}
                    ],
                    'contactInfo.fieldContactPhone': [
                        {required: true, message: '请输入现场联系人手机号码', trigger: 'blur'},
                        {min: 11, max: 11, validator:planPhoneValidator, trigger: 'blur'}
                    ],
                    'contactInfo.emergencyContactName': [
                        {required: true, message: '请输入紧急联系人名字', trigger: 'blur'},
                        {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'},
                        {validator: emergencyContactNameValidator, message: '现场联系人不能与紧急联系人为同一人',trigger: 'blur'}
                    ],
                    'contactInfo.emergencyContactPhone': [
                        {required: true, message: '请输入紧急联系人手机号码', trigger: 'blur'},
                        {min: 11, max: 11, validator: planPhoneEmergency, trigger: 'blur'},
                        {validator: emergencyContactPhoneValidator,message:'单位联系人的手机号码不能与紧急联系人的手机号码相同',trigger: 'blur'}
                    ],
                    'contactInfo.trainer':[
                        {validator: trainerValidator,message:'请添加教练员或飞行员姓名',trigger: 'blur',required: true}
                    ],
                    'contactInfo.traineeCount':[
                        {validator: traineeCountValidator,message:'当前计划类型为技能培训，请填写学员数量',trigger: 'blur',required: true}
                    ]
                },
                //是否显示教练员输入框
                trainerInputVisible: false,
                //教练员输入框值
                trainerInputValue: '',
                //是否加载中
                isLoading: false,
                remnant: 100,
                //日期选择器配置
                pickerOptions: {
                    disabledDate(time) {
                        return time.getTime() < Date.now();
                    }
                },
                //选中省
                selectedProvince: '',
                //选中市
                selectedCity: '',
                //飞行计划输入框是否可用
                planTypeInputDisable: true,
                 planTypeInputW: false,
                //飞行计划
                flyplan: {
                    type: 1,//报告类型
                    planType: this.$store.state.planTypes.plan1[0].label,
                    planTypeName: '',//其他飞行类型名称,
                    planes: planes,
                    planTime: {
                        //只能申请下一天的计划
                        date: new Date(new Date().getTime()+24*3600*1000),
                        beginHour: '',
                        beginMinute: '',
                        endHour: '',
                        endMinute: '',
                    },
                    //空域信息
                    airInfo: [{
                        provinceCode: '',
                        cityCode: '',
                        areaCode: '',
                        id: '',
                        location: '',
                        high: '0米'
                    }],
                    landingInfo: {
                            location: '',
                            lat0: 0,lat1:0,lat2:0,
                            lng0: 0,lng1:0,lng2:0
                    },
                    contactInfo: {
                        trainer: [],
                        traineeCount: 0,
                        fieldContactName: '',
                        fieldContactPhone: '',
                        emergencyContactName: '',
                        emergencyContactPhone: '',
                    },
                    weatherCondition:'',
                    remark:''
                },
            }
        },
        computed: {
            //计划类型
            planTypes: function () {
                var planTypes = this.$store.state.planTypes.plan1;
                return planTypes;
            },
            province: function () {
                return this.$store.state.airInfo.map(function (value) {
                    return {
                        label: value.provinceName,
                        value: value.provinceCode
                    }
                })
            },
            airspaces:function(){
            	return this.$store.state.airspaces.filter(function(airspace){
            		return airspace.type==1;
            	});
            },
            //可选空域省份
            selectableProvince: function () {
                var provinces = [];
                this.airspaces.forEach(function (airspace, i) {
                    var index = provinces.findIndex(function (province) {
                        return airspace.provinceCode == province.provinceCode;
                    });
                    if (index == -1) {
                        provinces.push({
                            provinceCode: airspace.provinceCode,
                            provinceName: airspace.provinceName
                        })
                    }
                });
                return provinces;
            },
            //可选城市
            selectableCities: function () {
                var parentCode = this.selectedProvince;
                var cities = [];
                this.airspaces.forEach(function (v, i) {
                    var index = cities.findIndex(function (city) {
                        return v.cityCode == city.cityCode;
                    });
                    if (index == -1 && parentCode == v.provinceCode) {
                        cities.push({
                            cityCode: v.cityCode,
                            cityName: v.cityName,
                          
                        })
                    }
                });
                return cities;
            },
            //可选空域
            selectableAirSpaces(){
                var parentCode = this.selectedCity;
                var airspacesLocation = [];
                this.airspaces.forEach(function (v, i) {
                    if (v.cityCode != parentCode) {
                        return;
                    }
                    airspacesLocation.push(v);
                });
                return airspacesLocation;
            },
            //可选开始飞行时间-小时
            selectableFlyBeginHours(){
                var hours = this.$store.state.flyHours;
               return hours;
            },
            //可选开始飞行时间-分钟
            selectableFlyBeginMinutes(){
                var minutes = this.$store.state.flyMinutes;
                return minutes;
            },
            //可选结束飞行时间-小时
            selectableFlyEndHours(){
                var hours = this.$store.state.flyHours;
                return hours.filter(function(value){
                    if(this.flyplan.planTime.beginMinute < 50){
                        return parseInt(value.label) >= parseInt(this.flyplan.planTime.beginHour);
                    }else {
                        return parseInt(value.label) >= parseInt(this.flyplan.planTime.beginHour) + 1;
                    }
                }.bind(this));
            },
            //可选结束飞行时间-分钟
            selectableFlyEndMinutes(){
                var minutes = this.$store.state.flyMinutes;
                return minutes.filter(function(value){
                    if(parseInt(this.flyplan.planTime.beginHour) == parseInt(this.flyplan.planTime.endHour)){
                        return parseInt(value.label) > parseInt(this.flyplan.planTime.beginMinute);
                    }
                    return true;
                }.bind(this));
            }
        },
        created(){
            this.user = this.$store.getters.getSession;
        },
        mounted(){
            if(this.user.type == 1){
                this.$alert('该页面内容仅企业用户填报有效，个人用户填报暂时不予审核', '温馨提示', {
                    confirmButtonText: '确定',
                    type: 'warning'
                });
            }
             
            //将模板数据插入到data中
            var flyCode = this.$route.params.tmplCode;              
            if(flyCode != undefined){
            $.ajax({
                url: urls.GET_FLYPLAN + "/" + flyCode,
                method: 'get',
                dataType: 'json',
                success: function (res) {
                    //格式化计划类型数据
              
                    var planType = res.data.planType;
                    var planTypeIndex = this.$store.state.planTypes.plan1.findIndex(function(value){
                        return planType == value.label;
                    });
                    
                    if(planTypeIndex == -1){
                        this.flyplan.planType = '其他';
                        this.flyplan.planTypeName = planType;
                    }else {
                        this.flyplan.planType = planType;
                    }
                    //格式化机型数据
                    res.data.planes = JSON.parse(res.data.planes);
                    
                    
                    //过滤数据为0的机型
                    res.data.planes = res.data.planes.filter(function(plane){
                        return plane.number > 0;
                    })
                
                    this.flyplan.planes.forEach(function(plane){
                        var index = res.data.planes.findIndex(function(resPlane){
                               return resPlane.name == plane.name;
                        })
                        if(index !== -1){
                            plane.checked = true;
                            plane.number = res.data.planes[index].number;
                            res.data.planes.splice(index,1)
                        }
                    });
                
                    console.log( this.flyplan.type)
                    if(res.data.planes.length > 0){
                        //剩下的无人机类型被认为是“其他”选项
                        var resOtherPlane = res.data.planes[0];
                        var otherPlane = this.flyplan.planes.find(function(plane){
                            return plane.name == '其他' ;
                        });
                        otherPlane.desc = resOtherPlane.name;
                        otherPlane.number = resOtherPlane.number;
                        otherPlane.checked = resOtherPlane.number > 0 ? true : false;
                    }
                    //格式化空域数据
                    var airInfo = JSON.parse(res.data.airInfo)[0];
                    this.selectedProvince = airInfo.provinceCode;
                    this.selectedCity = airInfo.cityCode;
                    var airspace = this.$store.state.airspaces.find(function (airspace) {
                        return airspace.id == airInfo.id;
                    });
                    this.flyplan.airInfo[0] = airspace;

                    //格式化起降场数据
                    res.data.landingInfo = JSON.parse(res.data.landingInfo);
                    var landingInfo = res.data.landingInfo[0];
                    this.flyplan.landingInfo = this.$store.state.landings.find(function(landing){
                        return landing.id == landingInfo.id;
                    });
                    //自选机场
                    if(this.flyplan.landingInfo.id == -1){
                        var lngArray = landingInfo.lng.split(',');
                        var latArray = landingInfo.lat.split(',');
                        Vue.util.extend(this.flyplan.landingInfo,{
                            lng0 : lngArray[0],
                            lng1 : lngArray[1],
                            lng2 : lngArray[2],
                            lat0 : latArray[0],
                            lat1 : latArray[1],
                            lat2 : latArray[2],
                            location : landingInfo.location
                        })
                    }

                    //格式化联系人数据
                    res.data.contactInfo = JSON.parse(res.data.contactInfo);
                    res.data.contactInfo.trainer = res.data.contactInfo.trainer.split(",");
                    this.flyplan.contactInfo =  res.data.contactInfo;
                         
                    //其他
                    this.flyplan.weatherCondition = res.data.weatherCondition;
                    this.flyplan.remark = res.data.remark;
                     
                }.bind(this)
               
            })
             console.log(res.data)
            }
        }
    }
</script>

<style lang="less" scoped="">

	* { 
		/*兼容360下的闪屏*/
    -webkit-transform-style: preserve-3d; 
    -webkit-backface-visibility: hidden; 
    -webkit-perspective: 1000; 
	} 
	.el-date-editor--date{
   	width: 150px;
   }
</style>
<template>
    <div class="plan" v-loading="isLoading"
         element-loading-text="数据提交中，请稍候...">
        <div class="title clear">
            <p class="left" style="font-size:18px">创建II类报告空域飞行计划</p>
            <div class="right">
                <el-button type="primary" @click="submit('flyplan')"  :disabled="true">提交</el-button>
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
                        <el-radio-group v-model="flyplan.planType">
                            <el-radio v-for="type in planTypes" :label="type.label"></el-radio>
                        </el-radio-group>
                        <el-input style="margin-left:10px;width:150px;" :disabled="flyplan.planType!='其他'"
                                  v-model="flyplan.planTypeName"></el-input>
                    </el-form-item>
                    <!--使用机型-->
                    <el-form-item label="使用机型" class="clear" prop="planes" auto-complete="off">
                        <el-radio-group v-model="flyplan.planes.label">
                            <el-radio-button v-model="flyplan.planes.label"
                                             v-for="plane in this.$store.state.planeTypes"
                                             :label="plane.label"></el-radio-button>
                        </el-radio-group>
                        <el-input :disabled="flyplan.planes.label!='其他'" style="width:150px;"
                                  v-model="flyplan.planes.desc" placeholder="请输入内容"></el-input>
                    </el-form-item>

                    <el-form-item label="使用时间" prop="planTime">
                        <el-date-picker
                                v-model="flyplan.planTime.date"
                                type="date"
                                placeholder="选择日期"
                                 readonly
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
                    <el-form-item label="计划空域及高度" prop="airInfo">
                        <div>
                            <el-select style="width:100px;" v-model="selectedProvince" placeholder="请选择" @change="handleChangePro">
                                <el-option v-for="province in seletableProvince" :label="province.provinceName"
                                           :value="province.provinceCode" ></el-option>
                            </el-select>
                            <el-select style="width:100px;" v-model="selectedCity"  placeholder="请选择" @change="handleChangeCity">
                                <el-option v-for="city in seletableCities" :label="city.cityName"
                                           :value="city.cityCode" ></el-option>
                            </el-select>

                            <el-select style="width:100px;" v-model="selectedArea"  placeholder="请选择" @change="handleChangeArea">
                                <el-option v-for="area in seletableAreas" :label="area.areaName"
                                           :value="area.areaCode" ></el-option>
                            </el-select>

                            <el-select style="width:150px;" v-model="flyplan.airInfo[0]"  placeholder="请选择" >
                                <el-option v-for="airspace in seletableAirSpaces" :label="airspace.location"
                                           :value="airspace"></el-option>
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
                    <el-form-item label="使用起降场" prop="landingInfo.location">
                        <el-input style="width:400px;" v-model="flyplan.landingInfo.location"
                                  placeholder="请输入内容"></el-input>
                        <div style="font-style:italic;color:#c6c6c6;">请以选择空域内起降场附近的地标或建设物为参考</div>
                    </el-form-item>

                </div>

                <div class="plan-form-title">
                    <div class="devider"></div>
                    <p class="text">联系方式</p>
                </div>

                <div class="plan-form-content clear">
                    <el-form-item label="无人机操控人" style="width:61%;" prop="contactInfo.controller">
                        <el-input v-model="flyplan.contactInfo.controller" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item label="手机号码" style="width:61%;" prop="contactInfo.controllerContact">
                        <el-input v-model="flyplan.contactInfo.controllerContact" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item label="证件号" prop="contactInfo.idCardNo">
                        <el-select style="width:15%;" v-model="flyplan.contactInfo.idCardType"
                                   placeholder="请选择">
                            <el-option v-for="type in this.$store.state.idCardTypes" :label="type.label"
                                       :value="type.label"></el-option>
                        </el-select>
                        <el-input style="width:38%;" v-model="flyplan.contactInfo.idCardNo"
                                  placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item label="紧急联系人" style="width:61%;" prop="contactInfo.emergencyContactName">
                        <el-input v-model="flyplan.contactInfo.emergencyContactName" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item label="手机号码" style="width:61%;" prop="contactInfo.emergencyContactPhone">
                        <el-input v-model="flyplan.contactInfo.emergencyContactPhone" placeholder="请输入内容"></el-input>
                    </el-form-item>
                </div>
                <el-form-item>
                    <el-button type="primary" @click="submit" style="padding:10px 50px;cursor: pointer;" :disabled="true">提交
                    </el-button>
                </el-form-item>
            </el-form>

        </div>
    </div>
</template>
<script src="jquery-3.2.1.js"></script>

<script>
    import '../../style/plan.css'
    import util from '../../components/util'
    import urls from '../../components/urls'
    import Vue from 'vue'
    import cardUtil from '../../components/valid-card'

    export default{
        methods: {
        	//清空地区选项
            handleChangePro:function(){
            	this.selectedCity = '';
            	this.selectedArea='';
            	this.flyplan.airInfo[0] = {};
            },       
            handleChangeCity:function(){
            	this.selectedArea='';
            	this.flyplan.airInfo[0] = {};
            	
            }, 
            handleChangeArea:function(){
            	this.flyplan.airInfo[0] = {};
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
                for (var prop in this.rules) {
                    flyplanForm.validateField(prop, function (errorMessage) {
                        //如果field使用了validator，此处的errorMessage为空，目前暂时使用判空的解决办法
                        if (errorMessage != '') {
                            this.$message.error(errorMessage, 5000);
                            validate = false;
                        }
                    }.bind(this));
                }

                if (!validate) return;

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
             * 处理飞行计划表表单
             */
            getRequestBody(){
                var flyplan = this.flyplan;
                //空域信息
                var airInfo = flyplan.airInfo.map(function (value) {
                    return {
                        id: value.id,
                        name: value.name,
                        type: value.type,
                        provinceCode: value.provinceCode,
                        provinceName: value.provinceName,
                        cityCode: value.cityCode,
                        cityName: value.cityName,
                        areaCode: value.areaCode,
                        areaName: value.areaName,
                        location: value.location,
                        high: value.high,
                        scopeType: 2,
                        centers: []
                    }
                });

                var request = {
                    type: flyplan.type,
                    //如果是其他类型，使用：隔开
                    planType: flyplan.planType == '其他' ? flyplan.planTypeName : flyplan.planType,
                    planes: [{
                        name: flyplan.planes.label == '其他' ? flyplan.planes.desc : flyplan.planes.label,
                        number: 1
                    }],
                    beginTime: util.formatDate(flyplan.planTime.date, 'yyyy-MM-dd') + " "
                    + flyplan.planTime.beginHour + ":" + flyplan.planTime.beginMinute,
                    endTime: util.formatDate(flyplan.planTime.date, 'yyyy-MM-dd') + " "
                    + flyplan.planTime.endHour + ":" + flyplan.planTime.endMinute,
                    //空域信息
                    airInfo: airInfo,
                    landingInfo: [flyplan.landingInfo],
                    contactInfo: flyplan.contactInfo
                }
                return JSON.stringify(request);
            }
        },
        data(){
            var planTypeValidator = (rule, value, callback) => {
                if (this.flyplan.planType == "其他" && this.flyplan.planTypeName == "") {
                    callback(new Error('计划类型不能为空'));
                }
                callback();
            };
            var planesValidator = (rule, value, callback) => {
                if (this.flyplan.planes.label == '其他' && (this.flyplan.planes.desc == undefined || this.flyplan.planes.desc == '')) {
                    callback(new Error('请填写机型'));
                }
                callback();
            };
            var planTimeValidator = (rule, value, callback) => {
                if (this.flyplan.planTime.beginHour == '' || this.flyplan.planTime.beginMinute == ''
                    || this.flyplan.planTime.endHour == '' || this.flyplan.planTime.endMinute == '') {
                    callback(new Error(rule.message));
                }
                callback();
            };
            var airInfoValidator = (rule, value, callback) => {
                var airSpaceId = this.flyplan.airInfo[0].id;
                if (airSpaceId == null || airSpaceId == ''){
                    callback(new Error(rule.message));
                }
                callback();
            };
            var emergencyContactNameValidator = (rule, value, callback) => {
                if (this.flyplan.contactInfo.controller == this.flyplan.contactInfo.emergencyContactName) {
                    callback(new Error(rule.message));
                }
                callback();
            };
             var planPhoneValidator = (rule, value, callback) => {
            	var myreg =/^1[34578]\d{9}$/;
            	if(!myreg.test(this.flyplan.contactInfo.controllerContact)){
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
            var emergencyContactPhoneValidator = (rule, value, callback) => {
                if (this.flyplan.contactInfo.controllerContact == this.flyplan.contactInfo.emergencyContactPhone) {
                    callback(new Error(rule.message));
                }
                callback();
            };
            var idCardValidator = (rule, value, callback) => {
                if (this.flyplan.contactInfo.idCardType == '身份证' && !cardUtil.checkCard(this.flyplan.contactInfo.idCardNo)) {
                    callback(new Error(rule.message));
                } else {
                    callback();
                }
            };

            return {
                //表单验证器
                rules: {
                    'planType': [{validator: planTypeValidator, trigger: 'blur', required: true}],
                    'planes': [{validator: planesValidator, trigger: 'blur', required: true}],
                    'planTime': [{
                        required: true,
                        validator: planTimeValidator,
                        message: '请输入完整的计划开始时间和结束时间',
                        trigger: 'blur'
                    }],
                    'airInfo': [{validator: airInfoValidator, required: true, message: '请选择一个计划空域', trigger: 'blur'}],
                    'landingInfo.location': [{required: true, message: '请输入起降场信息', trigger: 'blur'}],
                    'contactInfo.controller': [
                        {required: true, message: '请输入操控人姓名', trigger: 'blur'},
                        {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'}
                    ],
                    'contactInfo.controllerContact': [
                        {required: true, message: '请输入操控人手机号码', trigger: 'blur'},
                        {min: 11, max: 11, validator: planPhoneValidator, trigger: 'blur'}
                    ],
                    'contactInfo.idCardNo': [
                        {required: true, message: '请输入证件号', trigger: 'blur'},
                        {required: true, validator: idCardValidator, trigger: 'blur', message: '请输入正确的身份证号',}
                    ],
                    'contactInfo.emergencyContactName': [
                        {required: true, message: '请输入紧急联系人姓名', trigger: 'blur'},
                        {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'},
                        {validator: emergencyContactNameValidator, message: '操控人不能与紧急联系人为同一人', trigger: 'blur'}
                    ],
                    'contactInfo.emergencyContactPhone': [
                        {required: true, message: '请输入紧急联系人手机号码', trigger: 'blur'},
                        {min: 11, max: 11, validator: planPhoneEmergency, trigger: 'blur'},
                        {validator: emergencyContactPhoneValidator, message: '操控人的手机号码不能与紧急联系人的手机号码相同', trigger: 'blur'}
                    ],
                },
                //当前登录用户
                user: {},
                //是否加载中
                isLoading: false,
                //日期选择器配置
                pickerOptions: {
                    disabledDate(time) {
                        return time.getTime() < Date.now() - 8.64e7 || time.getTime() > Date.now();
                    }
                },
                //选中省
                selectedProvince: '',
                //选中市
                selectedCity: '',
                //选中区
                selectedArea: '',
                //飞行计划
                flyplan: {
                    type: 2,//报告类型
                    planType: this.$store.state.planTypes.plan2[0].label,
                    planTypeName: '',//其他飞行类型名称,
                    planes: {
                        label: this.$store.state.planeTypes[0].label
                    },
                    planTime: {
                        date: new Date(),
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
                        location: ''
                    },
                    contactInfo: {
                        controller: '',
                        controllerContact: '',
                        idCardType: '身份证',
                        idCardNo: '',
                        emergencyContactName: '',
                        emergencyContactPhone: '',
                    }
                },
            }
        },
        computed: {
            planTypes: function () {
                var planTypes = this.$store.state.planTypes.plan2;
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
            		return airspace.type==2;
            	});
            },
            //可选空域省份
            seletableProvince: function () {
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
            seletableCities: function () {
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
                            clearable:true
                        })
                    }
                });
                return cities;
            },
            //可选地区
            seletableAreas(){
                var parentCode = this.selectedCity;
                var areas = [];
                this.airspaces.forEach(function (v, i) {
                    var index = areas.findIndex(function (area) {
                        return v.areaCode == area.areaCode;
                    });
                    if (index == -1 && parentCode == v.cityCode) {                  
                        areas.push({                        	
                            areaCode: v.areaCode,
                            areaName: v.areaName                        
                        })
                    }
                });
                return areas;
            },

            //可选空域
            seletableAirSpaces(){
                var parentCode = this.selectedArea;
                var airspacesLocation = [];
                this.airspaces.forEach(function (v, i) {
                    if (v.areaCode != parentCode) {
                        return;
                    }
                    airspacesLocation.push(v);
                });
                return airspacesLocation;
            },
            //可选开始飞行时间-小时
            selectableFlyBeginHours(){
                var hours = this.$store.state.flyHours;
                var date = new Date();
                var currentHour = date.getHours();
                return hours.filter(function (value) {
                    return parseInt(value.label) >= currentHour + 1;               
                });
            },
            //可选开始飞行时间-分钟
            selectableFlyBeginMinutes(){
                var minutes = this.$store.state.flyMinutes;
                var date = new Date();
                var currentHour = date.getHours();
                var currentMinute = date.getMinutes();
                return minutes.filter(function (value) {
                    return this.flyplan.planTime.beginHour * 60 + parseInt(value.label) - 60 >= currentHour * 60 + currentMinute;
                }.bind(this));
            },
            //可选结束飞行时间-小时
            selectableFlyEndHours(){
                var hours = this.$store.state.flyHours;
                return hours.filter(function (value) {
                    if (this.flyplan.planTime.beginMinute < 50) {
                        return parseInt(value.label) >= parseInt(this.flyplan.planTime.beginHour);
                    } else {
                        return parseInt(value.label) >= parseInt(this.flyplan.planTime.beginHour) + 1;
                    }
                }.bind(this));
            },
            //可选结束飞行时间-分钟
            selectableFlyEndMinutes(){
                var minutes = this.$store.state.flyMinutes;
                return minutes.filter(function (value) {
                    if (parseInt(this.flyplan.planTime.beginHour) == parseInt(this.flyplan.planTime.endHour)) {
                        return parseInt(value.label) > parseInt(this.flyplan.planTime.beginMinute);
                    }
                    return true;
                }.bind(this));
            }
        },
        created() {
            this.user = this.$store.getters.getSession;
        },
        mounted()
        {
            if (this.user.type == 1||this.user.type == 2) {
                this.$alert('该模块暂未开放', '温馨提示', {
                    confirmButtonText: '确定',
                    type: 'warning'
                });
            }
                  
            //将模板数据插入到data中
            var flyCode = this.$route.params.tmplCode;
            if (flyCode != undefined) {
                $.ajax({
                    url: urls.GET_FLYPLAN + "/" + flyCode,
                    method: 'get',
                    dataType: 'json',
                    success: function (res) {
                        //格式化计划类型数据
                        
                        var planType = res.data.planType;
                        var planTypeIndex = this.$store.state.planTypes.plan2.findIndex(function (value) {
                            return planType == value.label;
                        });
                     

                        if (planTypeIndex == -1) {
                            this.flyplan.planType = '其他';
                            this.flyplan.planTypeName = planType;
                        } else {
                            this.flyplan.planType = planType;
                        }

                        //格式化机型数据
                        res.data.planes = JSON.parse(res.data.planes);
                        //默认选择第一个机型
                        var plane = res.data.planes[0];
                        var planeIndex = this.$store.state.planeTypes.findIndex(function (planeType) {
                            return plane.label == planeType.label;
                        });
                        this.flyplan.planes == planeIndex == -1 ? {label: '其他', desc: plane.name} : plane;

                        //格式化空域数据
                        var airInfo = JSON.parse(res.data.airInfo)[0];
                        this.selectedProvince = airInfo.provinceCode;
                        this.selectedCity = airInfo.cityCode;
                        this.selectedArea = airInfo.areaCode;
                        var airspace = this.airspaces.find(function (value) {
                            return value.id == airInfo.id;
                        });
                        this.flyplan.airInfo[0] = airspace;

                        //格式化起降场数据
                        res.data.landingInfo = JSON.parse(res.data.landingInfo);
                        this.flyplan.landingInfo.location = res.data.landingInfo[0].location;

                        //格式化联系人数据
                        res.data.contactInfo = JSON.parse(res.data.contactInfo);
                        this.flyplan.contactInfo = res.data.contactInfo;

                    }.bind(this)
                })
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
<template>
    <div class="plan" v-loading="isLoading"
         element-loading-text="数据提交中，请稍候...">
        <div class="title clear">
            <p class="left" style="font-size:18px">创建管制空域飞行计划</p>
            <div class="right">
                <el-button type="primary" @click="submit('flyplan')" :disabled="true">提交</el-button>
                <el-button @click="back">返回</el-button>
            </div>
        </div>
        <div class="tips">提示:带<span color="red">*</span>号为必填项</div>
        <div class="plan-form">
            <el-form ref="flyplan" label-position="left" :model="flyplan" :rules="rules"
                     label-width="130px">
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
                            <el-input style="margin-left:10px;width:150px;" :disabled="planTypeInputDisable"
                                  v-model="flyplan.planTypeName"></el-input>
                        </el-radio-group>
                        
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
                        <el-select style="width:90;" v-model="flyplan.planTime.endMinute" placeholder="请选择">
                            <el-option v-for="minute in selectableFlyEndMinutes" :label="minute.label"
                                       :value="minute.label"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="空域批文编号" prop="applyOfficialNo">
                        <el-input style="width:150px;" v-model="flyplan.applyOfficialNo"></el-input>
                    </el-form-item>
                    <el-form-item label="计划空域及高度" prop="airInfo">
                        <div v-for="(airInfo,index) in flyplan.airInfo"
                             style="border-bottom:dashed 1px #f0f0f0;margin-bottom:10px;">
                            <el-form-item label="位置描述" class="airinfo_item">
                                <el-input style="width:300px;" v-model="airInfo.location"
                                          placeholder="请输入内容"></el-input>
                                <el-button type="text" @click="removeAirInfo(index)"
                                           style="color:#ff0000;float:right;margin-right:100px;">删除
                                </el-button>
                            </el-form-item>
                            <el-form-item label="真    高" class="airinfo_item">
                                <el-input-number :controls="false" :debounce="0" style="margin-right:10px;width:50px;"v-model="airInfo.high" :min="0" >
                                	
                                </el-input-number>
                                米
                            </el-form-item>
                            <el-form-item label="飞行范围" class="airinfo_item">
                                <el-select style="width:120px;" v-model="airInfo.scopeType" placeholder="请选择"
                                           class="airinfo_item">
                                    <el-option :value=1
                                                label="圆形空域"  ></el-option>
                                    <el-option label="多边形空域"
                                               :value=2></el-option>
                                </el-select>
                                
                                <div v-if="airInfo.scopeType=='1'">
                                    <div class="airinfo_item clear">
                                        <div class="left" style="margin-left: 114px; ">以</div>
                                        <div class="left" style="margin-right:10px;margin-bottom: 10px;">
                                            <span style="background:#169bd5;padding:5px;">经</span>
                                            <el-input-number :controls="false" :debounce="0" style="margin-right:10px;width:50px;"
                                                             v-model="airInfo.lng0" :min="-180"
                                                             :max="180"></el-input-number>
                                            <span>度</span>
                                            <el-input-number :controls="false" :debounce="0" style="margin-right:10px;width:50px;"
                                                             v-model="airInfo.lng1" :min="0"
                                                             :max="60"></el-input-number>
                                            <span>分</span>
                                            <el-input-number :controls="false" :debounce="0" style="margin-right:10px;width:50px;"
                                                             v-model="airInfo.lng2" :min="0"
                                                             :max="60"></el-input-number>
                                            <span>秒</span>
                                        </div>
                                        <div class="left" style="margin-left: 130px; ">
                                            <span style="background:#169bd5;padding:5px;">纬</span>
                                            <el-input-number :controls="false" :debounce="0" style="margin-right:10px;width:50px;"
                                                             v-model="airInfo.lat0" :min="-90"
                                                             :max="90"></el-input-number>
                                            <span>度</span>
                                            <el-input-number :controls="false" :debounce="0" style="margin-right:10px;width:50px;"
                                                             v-model="airInfo.lat1" :min="0"
                                                             :max="60"></el-input-number>
                                            <span>分</span>
                                            <el-input-number :controls="false" :debounce="0" style="margin-right:10px;width:50px;"
                                                             v-model="airInfo.lat2" :min="0"
                                                             :max="60"></el-input-number>
                                            <span>秒</span>
                                        </div>
                                        <div class="left">为中心</div>
                                    </div>
                                    <div>
                                        <div class="left" style="margin-right:10px;">半径为 </div>
                                        <el-input-number :controls="false" :debounce="0" style="width:80px;margin-left: 80px;"
                                                         v-model="airInfo.radius" :min="0"
                                        ></el-input-number>
                                        米。
                                    </div>
                                </div>
                                <div v-if="airInfo.scopeType=='2'">
                                    <div class="airinfo_item">
                                        <span>范围为以下</span>
                                        <span style="font-size:18px;font-weight: 700;">{{airInfo.centers.length}}</span>
                                        <el-button-group>
                                            <el-button :disabled="airInfo.centers.length<4" icon="minus" size="small" @click="removeLastCenter(airInfo.centers)"></el-button>
                                            <el-button :disabled="airInfo.centers.length>=10" icon="plus" size="small" @click="addCenter(airInfo.centers)"></el-button>
                                        </el-button-group>
                                        <span>点连线范围内，拐点坐标分别为：</span>
                                    </div>

                                    <div v-for="(center,index) in airInfo.centers" class="airinfo_item">
                                        <div class="left" style="margin-right:10px;">
                                            <span style="background:#169bd5;padding:5px;">经</span>
                                            <el-input-number :controls="false" :debounce="0" style="width:60px;"
                                                             v-model="center.lng0" :min="-180"
                                                             :max="180"></el-input-number>
                                            <span>度</span>
                                            <el-input-number :controls="false" :debounce="0" style="width:60px;"
                                                             v-model="center.lng1" :min="0"
                                                             :max="60"></el-input-number>
                                            <span>分</span>
                                            <el-input-number :controls="false" :debounce="0" style="width:60px;"
                                                             v-model="center.lng2" :min="0"
                                                             :max="60"></el-input-number>
                                            <span>秒</span>
                                        </div>
                                        <div class="left" style="margin-right:10px;">
                                            <span style="background:#169bd5;padding:5px;">纬</span>
                                            <el-input-number :controls="false" :debounce="0" style="width:60px;"
                                                             v-model="center.lat0" :min="-90"
                                                             :max="90"></el-input-number>
                                            <span>度</span>
                                            <el-input-number :controls="false" :debounce="0" style="width:60px;"
                                                             v-model="center.lat1" :min="0"
                                                             :max="60"></el-input-number>
                                            <span>分</span>
                                            <el-input-number :controls="false" :debounce="0" style="width:60px;"
                                                             v-model="center.lat2" :min="0"
                                                             :max="60"></el-input-number>
                                            <span>秒</span>
                                        </div>
                                        <el-button type="text" @click="removeCenter(airInfo.centers,index)" style="color:red">删除</el-button>
                                    </div>
                                </div>
                            </el-form-item>

                        </div>
                        <el-button type="text" @click="addAirInfo">+ 添加空域</el-button>
                    </el-form-item>

                    <el-form-item label="主起降场" prop="landingInfo">
                        <div v-for="(landingInfo,index) in flyplan.landingInfo"
                             style="border-bottom:dashed 1px #f0f0f0;margin-bottom:50px;">
                            <el-form-item label="名称" class="landing_item">
                                <el-select style="width:120px;" v-model="landingInfo.id" placeholder="请选择" @change="handleChangeLanding(landingInfo)">
                                    <el-option v-for="landing in landings" :label="landing.name"
                                               :value="landing.id" ></el-option>
                                </el-select>
                                <el-button type="text" @click="removeLanding(index)"
                                           style="color:#ff0000;float:right;margin-right:100px;">删除
                                </el-button>
                            </el-form-item>
                            <el-form-item label="起降场位置描述" v-if="landingInfo.id==-1" class="landing_item">
                                <div style="margin-left:0px;">
                                    <el-input style="width:400px;" v-model="landingInfo.location"
                                              placeholder="请输入内容"></el-input>
                                    <div style="color:#3366ff;"><i>请以选择空域内起降场附近的地标或建设物为参考</i></div>
                                </div>
                            </el-form-item>

                            <el-form-item label="起降场坐标" v-if="landingInfo.id==-1" class="landing_item">
                                <div style="margin-left:0px;">
                                    <div class="left">
                                        <span style="background:#169bd5;padding:5px;">经</span>
                                        <el-input-number :controls="false" :debounce="0"
                                                         style="margin-right:10px;width:50px;"
                                                         v-model="landingInfo.lng0" :min="-180"
                                                         :max="180"></el-input-number>
                                        <span>度</span>
                                        <el-input-number :controls="false" :debounce="0"
                                                         style="margin-right:10px;width:50px;"
                                                         v-model="landingInfo.lng1" :min="0"
                                                         :max="60"></el-input-number>
                                        <span>分</span>
                                        <el-input-number :controls="false" :debounce="0"
                                                         style="margin-right:10px;width:50px;"
                                                         v-model="landingInfo.lng2" :min="0"
                                                         :max="60"></el-input-number>
                                        <span>秒</span>
                                    </div>
                                    <div class="left" style="margin-top:10px;margin-left:129px ;">
                                        <span style="background:#169bd5;padding:5px;">纬</span>
                                        <el-input-number :controls="false" :debounce="0"
                                                         style="margin-right:10px;width:50px;"
                                                         v-model="landingInfo.lat0" :min="-90"
                                                         :max="90"></el-input-number>
                                        <span>度</span>
                                        <el-input-number :controls="false" :debounce="0"
                                                         style="margin-right:10px;width:50px;"
                                                         v-model="landingInfo.lat1" :min="0"
                                                         :max="60"></el-input-number>
                                        <span>分</span>
                                        <el-input-number :controls="false" :debounce="0"
                                                         style="margin-right:10px;width:50px;"
                                                         v-model="landingInfo.lat2" :min="0"
                                                         :max="60"></el-input-number>
                                        <span>秒</span>
                                    </div>
                                </div>
                            </el-form-item>
                        </div>
                     <el-form-item label="备用起降场" prop="" style="width: 300px;position: absolute;top: 230px;left: -136px;" >
                        <el-button type="text" @click="addLanding">+ 添加起降场</el-button>
                     </el-form-item>
                    </el-form-item>                     
                </div>
                <div class="plan-form-title">
                    <div class="devider"></div>
                    <p class="text">联系方式</p>
                </div>
                <div class="plan-form-content clear">
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="单位联系人" prop="contactInfo.unitContactName">
                                <el-input style="width:150px;" v-model="flyplan.contactInfo.unitContactName"
                                          placeholder="请输入内容"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="联系电话" prop="contactInfo.unitContactPhone">
                                <el-input  style="width:150px;" v-model="flyplan.contactInfo.unitContactPhone"
                                          placeholder="请输入内容"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="现场联系人" prop="contactInfo.fieldContactName">
                                <el-input style="width:150px;" v-model="flyplan.contactInfo.fieldContactName"
                                          placeholder="请输入内容"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="联系号码" prop="contactInfo.fieldContactPhone">
                                <el-input style="width:150px" v-model="flyplan.contactInfo.fieldContactPhone"
                                          placeholder="请输入内容"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <!--
                <el-form-item label="紧急联系人" style="width:180px;" prop="contactInfo.emergencyContactName">
                    <el-input v-model="flyplan.contactInfo.emergencyContactName" placeholder="请输入内容"></el-input>
                </el-form-item>
                <el-form-item label="手机号码" style="width:180px;" prop="contactInfo.emergencyContactPhone">
                    <el-input v-model="flyplan.contactInfo.emergencyContactPhone"
                              placeholder="请输入内容"></el-input>

                </el-form-item>-->

                </div>

                <div class="plan-form-title">
                    <div class="devider"></div>
                    <p class="text">其他</p>
                </div>
                <div class="plan-form-content clear">
                    <el-form-item label="气象条件" style="width:80%;">
                        <el-input v-model="flyplan.weatherCondition"
                                  placeholder="天气状态（如晴天）、风速（南风四级）、能见度（1000-4000米）"></el-input>
                    </el-form-item>
                    <el-form-item label="其他说明事项 " style="width:80%;">
                        <el-input type="textarea"  v-model="flyplan.remark" placeholder="请输入内容，最大允许填写100个字" @input="descInput()" :maxlength="100"></el-input>
                        <span style="color: #CCCCCC; font-size: 12px;" >可输入数字：{{remnant}}/100</span>
                    </el-form-item>
                </div>
                <el-form-item>
                    <el-button type="primary" @click="submit" style="padding:10px 50px;" :disabled="true">提交
                    </el-button>
                </el-form-item>
            </el-form>
        </div>

          <!--<div class="center" style="margin-top:30px;">
            <el-pagination layout="prev, pager, next" :total="totalElements"
                           @current-change="fetchFiles"></el-pagination>
        </div>-->
    </div>
</template>

<script>
    import '../../style/plan.css'
    import util from '../../components/util'
    import urls from '../../components/urls'
    import Vue from 'vue'

    export default{
        methods: {

            /**
             * 添加一个起降场
             */
            addLanding: function () {
                var landingInfo = {
                    id: '-1',
                    name:'自选起降场',
                    location: '',
                    lng0: 0, lng1: 0, lng1: 0,
                    lat0: 0, lat1: 0, lat2: 0,
                };
                this.flyplan.landingInfo.push(landingInfo);
            },
            /**
             * 删除一个起降场
             */
            removeLanding: function (index) {
                if (this.flyplan.landingInfo.length < 2) {
                    return;
                }
                this.flyplan.landingInfo.splice(index, 1);
            },
            /**
             * 添加一个空域
             */
            addAirInfo: function () {
                var airInfo = {
                    location: '',
                    high: '0',
                    scopeType:1,
                    circleScope: {
                        lng0: 0, lng1: 0, lng2: 0, lat0: 0, lat1: 0, lat2: 0,
                        radius: 0
                    },
                    centers: [{lng0: 0, lng1: 0, lng2: 0, lat0: 0, lat1: 0, lat2: 0},
                        {lng0: 0, lng1: 0, lng2: 0, lat0: 0, lat1: 0, lat2: 0},
                        {lng0: 0, lng1: 0, lng2: 0, lat0: 0, lat1: 0, lat2: 0}
                    ]
                }
                this.flyplan.airInfo.push(airInfo);
            },
            /**
             * 删除一个空域
             */
            removeAirInfo: function (index) {
                if (this.flyplan.airInfo.length < 2) {
                    return;
                }
                this.flyplan.airInfo.splice(index, 1);
            },
            //增加一个空域转向点
            addCenter: function(centers){
                centers.push({lng0: 0, lng1: 0, lng2: 0, lat0: 0, lat1: 0, lat2: 0});
            },
            //删除最后一个空域转向点
            removeLastCenter: function(centers){
                centers.splice(centers.length-1,1);
            },
            removeCenter: function(centers,index){
                if(centers.length < 4){
                    this.$message('请至少填写3个拐点坐标');
                    return;
                }
                centers.splice(index,1);
            },
            /**
             * 处理起降场变更
             * @param targetLanding 目标起降场对象
             */
            handleChangeLanding: function(targetLanding){
                var selectedLanding = this.landings.find(function(value){
                    return value.id == targetLanding.id;
                });
                Vue.util.extend(targetLanding,selectedLanding);
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
            },
            /**
             * 返回 上一页
             */
            back(){
                this.$router.go(-1);
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
             * 处理飞行计划表单
             */
            getRequestBody(){
                var flyplan = this.flyplan;
                //空域信息
                var airInfo = flyplan.airInfo.map(function (value) {
                    var airInfoMap = {
                        location: value.location,
                        high:"真高"+value.high+"米",
                        scopeType: value.scopeType
                    }
                    if(value.scopeType == '1'){
                        var circleScope = {
                            lng: value.lng0 + ',' + value.lng1 + ',' + value.lng2,
                            lat: value.lat0 + ',' + value.lat1 + ',' + value.lat2,
                            radius : value.radius
                        };
                        airInfoMap.circleScope = circleScope;
                    }else if(value.scopeType == '2'){
                        var centers  = value.centers.map(function(center){
                            return {
                                lng: center.lng0 + ',' + center.lng1 + ',' + center.lng2,
                                lat: center.lat0 + ',' + center.lat1 + ',' + center.lat2,
                            }
                        });
                        airInfoMap.centers = centers;
                    }

                    return airInfoMap;
                });
                //起降场信息
                var landingInfo = flyplan.landingInfo.map(function(landing){
                    var tempLanding = {
                        id: landing.id,
                        name: landing.name,
                        location: landing.location
                    }
                    //自选起降场才填写经纬
                    if (tempLanding.id == -1) {
                        tempLanding.lng = landing.lng0 + "," + landing.lng1 + "," + landing.lng2;
                        tempLanding.lat = landing.lat0 + "," + landing.lat1 + "," + landing.lat2;
                    }
                    return tempLanding;
                })

                //联系方式
                var contactInfo = Vue.util.extend({}, flyplan.contactInfo);

                var planes = flyplan.planes.filter(function (plane) {
                    return plane.number > 0;
                }).map(function (plane) {
                    return {
                        name: plane.name == '其他' ? plane.desc : plane.name,
                        number: plane.number
                    }
                });

                var request = {
                    type: flyplan.type,
                    planType: flyplan.planType == '其他' ? flyplan.planTypeName : flyplan.planType,
                    planes: planes,
                    beginTime: util.formatDate(flyplan.planTime.date, 'yyyy-MM-dd') + " "
                    + flyplan.planTime.beginHour + ":" + flyplan.planTime.beginMinute,
                    endTime: util.formatDate(flyplan.planTime.date, 'yyyy-MM-dd') + " "
                    + flyplan.planTime.endHour + ":" + flyplan.planTime.endMinute,
                    //空域信息
                    airInfo: airInfo,
                    landingInfo: landingInfo,
                    contactInfo: contactInfo,
                    weatherCondition: flyplan.weatherCondition,
                    remark: flyplan.remark,
                    applyOfficialNo: flyplan.applyOfficialNo
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
                if (this.flyplan.planTime.beginHour == '' || this.flyplan.planTime.beginMinute == ''
                    || this.flyplan.planTime.endHour == '' || this.flyplan.planTime.endMinute == '') {
                    return callback(new Error(rule.message));                   
                }
                callback();
            };
            var planPhoneValidator = (rule, value, callback) => {
            	var myreg =/^1[34578]\d{9}$/;
            	if(!myreg.test(this.flyplan.contactInfo.unitContactPhone)){
            		return callback(new Error("请填写正确手机号"));
            	}
                callback();
            };
            var planContactPhone = (rule, value, callback) => {
            	var myreg =/^1[34578]\d{9}$/;
            	if(!myreg.test(this.flyplan.contactInfo.fieldContactPhone)){
            		return callback(new Error("请填写正确手机号"));
            	}
                callback();
            };
            var emergencyContactPhoneValidator = (rule, value, callback) => {
                if (this.flyplan.contactInfo.fieldContactPhone == this.flyplan.contactInfo.unitContactPhone) {
                    return callback(new Error(rule.message));
                }
                callback();
            };
            var airInfoValidator = (rule, value, callback) => {
                var index = this.flyplan.airInfo.findIndex(function(value){
                    return value.location == '';
                });
                if(index != -1){
                    return callback(new Error(rule.message));
                }
                callback();
            };
            var landingInfoValidator = (rule, value, callback) => {
                var index = this.flyplan.landingInfo.findIndex(function(value){
                    return value.location == '';
                });
                if(index != -1){
                    return callback(new Error(rule.message));
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
                    'planType': [{validator: planTypeValidator, message: '请填写计划类型', trigger: 'blur', required: true,}],
                    'planes': [{
                        validator: planesValidator,
                        message: '请至少选择一种机型，且机型数量不能为0',
                        trigger: 'blur',
                        required: true,
                    }],
                    'planTime': [{
                        validator: planTimeValidator,
                        required: true,
                        message: '请输入完整的计划开始时间和结束时间',
                        trigger: 'blur'
                    }],
                    'applyOfficialNo': [{required: true, message: '空域批文编号不能为空', trigger: 'blur'}],
                    'airInfo': [{required: true, message: '空域位置不能为空', trigger: 'blur',validator:airInfoValidator}],
                    'landingInfo': [{required: true, message: '起降场位置不能为空', trigger: 'blur',validator:landingInfoValidator}],
                    'contactInfo.fieldContactName': [
                        {required: true, message: '请输入现场联系人名字', trigger: 'blur'},
                        {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'}
                    ],
                    'contactInfo.fieldContactPhone': [
                        {required: true, message: '请输入现场联系人手机号码', trigger: 'blur'},
                        {validator: planContactPhone,min: 11, max: 11, trigger: 'blur'},
                         {validator: emergencyContactPhoneValidator, message: '单位联系人的电话号码不能与现场联系人的电话号码一样', trigger: 'blur'}
                    ],
                    'contactInfo.unitContactName': [
                        {required: true, message: '请输入单位联系人名字', trigger: 'blur'},
                        {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'}
                    ],
                    'contactInfo.unitContactPhone': [
                        {required: true, message: '请输入单位联系人手机号码', trigger: 'blur'},
                        {min: 11, max: 11,validator:planPhoneValidator, trigger: 'blur'}
                       
                    ]
//                    'contactInfo.emergencyContactName': [
//                        {required: true, message: '请输入紧急联系人名字', trigger: 'blur'},
//                        {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'}
//                    ],
//                    'contactInfo.emergencyContactPhone': [
//                        {required: true, message: '请输入紧急联系人手机号码', trigger: 'blur'},
//                        {min: 11, max: 11, message: '电话号码长度为11位', trigger: 'blur'},
//                        {validator: emergencyContactPhoneValidator, message: '操控人的电话号码不能与紧急联系人的电话号码一样', trigger: 'blur'}
//                    ],

                },
                //是否加载中
                isLoading: false,
                remnant: 100,
                //日期选择器配置
                pickerOptions: {
                    disabledDate(time) {
                        return time.getTime() < Date.now();
                    }
                },
                //飞行计划输入框是否可用
                planTypeInputDisable: true,
                //飞行计划
                flyplan: {
                    type: 0,//报告类型
                    applyOfficialNo:'',//空域批文号
                    planType: this.$store.state.planTypes.plan0[0].label,
                    planTypeName: '',//其他飞行类型名称,
                    planes: planes,
                    planTime: {
                        date: new Date(new Date().getTime() + 24 * 3600 * 1000), //只能申请下一天的计划
                        beginHour: '',
                        beginMinute: '',
                        endHour: '',
                        endMinute: '',
                    },
                    //空域信息
                    airInfo: [{
                        location: '',
                        high: '0',
                        scopeType: 1,
                        circleScope: {
                            lng0: 0, lng1: 0, lng2: 0, lat0: 0, lat1: 0, lat2: 0,
                            radius: 0
                        },
                        centersLength: 3,//默认中心点数量
                        centers: [{lng0: 0, lng1: 0, lng2: 0, lat0: 0, lat1: 0, lat2: 0},
                            {lng0: 0, lng1: 0, lng2: 0, lat0: 0, lat1: 0, lat2: 0},
                            {lng0: 0, lng1: 0, lng2: 0, lat0: 0, lat1: 0, lat2: 0}
                        ]
                    }],
                    landingInfo: [{
                        id: '-1',
                        location: '',
                        name:'自选起降场',
                        lat0: 0, lat1: 0, lat2: 0,
                        lng0: 0, lng1: 0, lng2: 0
                    }],
                    contactInfo: {
                        unitContactName: '',
                        unitContact: '',
                        emergencyContactName: '',
                        emergencyContactPhone: '',
                    },
                    weatherCondition: '',
                    remark: ''
                },
            }
        },
        computed: {
            //计划类型
            planTypes: function () {
                var planTypes = this.$store.state.planTypes.plan0;
                return planTypes;
            },
            //起降场
            landings: function () {
                var landings = this.$store.state.landings;
                return landings;
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
        created(){
            this.user = this.$store.getters.getSession;
        },
        mounted(){
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
                        var planTypeIndex = this.$store.state.planTypes.plan0.findIndex(function (value) {
                            return planType == value.label;
                        });
                        debugger
                        if (planTypeIndex == -1) {
                            this.flyplan.planType = '其他';
                            this.flyplan.planTypeName = planType;
                        } else {
                            this.flyplan.planType = planType;
                        }

                        //格式化机型数据
                        res.data.planes = JSON.parse(res.data.planes);
                        //过滤数据为0的机型
                        res.data.planes = res.data.planes.filter(function (plane) {
                            return plane.number > 0;
                        })
                        this.flyplan.planes.forEach(function (plane) {
                            var index = res.data.planes.findIndex(function (resPlane) {
                                return resPlane.name == plane.name;
                            })
                            if (index !== -1) {
                                plane.checked = true;
                                plane.number = res.data.planes[index].number;
                                res.data.planes.splice(index, 1)
                            }
                        });
                        if (res.data.planes.length > 0) {
                            //剩下的无人机类型被认为是“其他”选项
                            var resOtherPlane = res.data.planes[0];
                            var otherPlane = this.flyplan.planes.find(function (plane) {
                                return plane.name == '其他';
                            });
                            otherPlane.desc = resOtherPlane.name;
                            otherPlane.number = resOtherPlane.number;
                            otherPlane.checked = resOtherPlane.number > 0 ? true : false;
                        }

                        //格式化空域数据
                        var airInfo = JSON.parse(res.data.airInfo);
                        airInfo.forEach(function(value){
                             if(value.scopeType == '1'){
                                 var lngArray = value.circleScope.lng.split(',');
                                 var latArray = value.circleScope.lat.split(',');
                                 Vue.util.extend(value, {
                                     lng0: lngArray[0],
                                     lng1: lngArray[1],
                                     lng2: lngArray[2],
                                     lat0: latArray[0],
                                     lat1: latArray[1],
                                     lat2: latArray[2],
                                 })
                             }else if(value.scopeType == '2'){
                                 value.centers.forEach(function(center){
                                     var lngArray = center.lng.split(',');
                                     var latArray = center.lat.split(',');
                                     Vue.util.extend(center, {
                                         lng0: lngArray[0],
                                         lng1: lngArray[1],
                                         lng2: lngArray[2],
                                         lat0: latArray[0],
                                         lat1: latArray[1],
                                         lat2: latArray[2],
                                     })
                                 })
                             }
                        });

                        this.flyplan.airInfo = airInfo;

                        //格式化起降场数据
                        res.data.landingInfo = JSON.parse(res.data.landingInfo);
                        var landingInfo = res.data.landingInfo;
                        landingInfo.map(function(value){
                            var map = this.$store.state.landings.find(function (landing) {
                                return landing.id == value.id;
                            });
                            //自选机场
                            if (map.id == -1) {
                                var lngArray = value.lng.split(',');
                                var latArray = value.lat.split(',');
                                Vue.util.extend(map, {
                                    lng0: lngArray[0],
                                    lng1: lngArray[1],
                                    lng2: lngArray[2],
                                    lat0: latArray[0],
                                    lat1: latArray[1],
                                    lat2: latArray[2],
                                    location: value.location
                                })
                            }
                            return map;
                        }.bind(this))
                        this.flyplan.landingInfo = landingInfo;

                        //格式化联系人数据
                        res.data.contactInfo = JSON.parse(res.data.contactInfo);
                        this.flyplan.contactInfo = res.data.contactInfo;
                        
                        //其他
                        this.flyplan.applyOfficialNo = res.data.applyOfficialNo;
                        this.flyplan.weatherCondition = res.data.weatherCondition;
                        this.flyplan.remark = res.data.remark;

                    }.bind(this)
                })
            }
        }
    }
</script>

<style lang="less" scoped>
	* { 
	/*兼容360下的闪屏*/
    -webkit-transform-style: preserve-3d; 
    -webkit-backface-visibility: hidden; 
    -webkit-perspective: 1000; 
	} 	
    .airinfo_item, .landing_item {
        margin-bottom: 10px;
        
    }
    
    .el-radio{   
	
	float: left;
	margin: 4px 4px;
	padding-right: 15px;
	}
	.el-radio-group{
		float: left;
		
	}
   .el-select{
   	width: 90px;
   }
   .el-date-editor--date{
   	width: 150px;
   }
</style>
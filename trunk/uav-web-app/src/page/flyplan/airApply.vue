<template>
    <div class="plan" v-loading="isLoading"
         element-loading-text="数据提交中，请稍候...">
        <div class="title clear">
            <p class="left" style="font-size:18px">创建空域申请</p>
            <div class="right">
                <el-button type="primary" @click="submit('airapply')" >提交</el-button>
                <el-button @click="back">返回</el-button>
            </div>
        </div>
        <div class="tips">提示:带<span color="red">*</span>号为必填项</div>
        <div class="plan-form">
            <el-form ref="airapply" label-position="left" :model="airapply" :rules="rules"
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
                    <el-form-item label="计划类型" prop="planType" auto-complete="off" style="position: relative;">
                        <el-radio-group v-model="airapply.planType" @change="flyTypeChange">
                            <el-radio v-for="type in planTypes" :label="type.label"></el-radio>
                            <el-input style="margin-left:10px;width:150px;" :disabled="planTypeInputDisable"
                            v-model="airapply.planTypeName"></el-input>
                        </el-radio-group>
                        
                    </el-form-item>
                    <!--使用机型-->
                    <el-form-item label="使用机型" class="clear" prop="planes" auto-complete="off">
                        <el-checkbox-group v-for="plane in airapply.planes" class="left" style="margin-bottom:5px;"
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
                                v-model="airapply.daterange"
                                type="daterange"
                                :picker-options="pickerOptions"
                                placeholder="选择日期范围">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="计划空域及高度" prop="airInfo">
                        <div v-for="(airInfo,index) in airapply.airInfo"
                             style="border-bottom:dashed 1px #f0f0f0;margin-bottom:10px;">
                            <el-form-item label="位置描述" class="airinfo_item">
                                <el-input style="width:300px;" v-model="airInfo.location"
                                          placeholder="请输入内容"></el-input>
                                <el-button type="text" @click="removeAirInfo(index)"
                                           style="color:#ff0000;float:right;margin-right:100px;">删除
                                </el-button>
                            </el-form-item>
                            <el-form-item label="真    高" class="airinfo_item">
                                <el-input-number :controls="false" :debounce="0" style="margin-right:10px;width:50px;"
                                                 v-model="airInfo.high" :min="0"  ></el-input-number>  米
                            </el-form-item>
                            <el-form-item label="飞行范围" class="airinfo_item">
                                <el-select style="width:120px;" v-model="airInfo.scopeType" placeholder="请选择"
                                           class="airinfo_item">
                                    <el-option :value=1
                                               label="圆形空域"></el-option>
                                    <el-option label="多边形空域"
                                               :value=2></el-option>
                                </el-select>

                                <div v-if="airInfo.scopeType==1">
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
                                <div v-if="airInfo.scopeType==2">
                                    <div class="airinfo_item">
                                        <span>范围为以下</span>
                                        <span style="font-size:18px;font-weight: 700;">{{airInfo.centers.length}}</span>
                                        <el-button-group>
                                            <el-button :disabled="airInfo.centers.length<4" icon="minus" size="small"
                                                       @click="removeLastCenter(airInfo.centers)"></el-button>
                                            <el-button :disabled="airInfo.centers.length>=10" icon="plus" size="small"
                                                       @click="addCenter(airInfo.centers)"></el-button>
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
                                        <el-button type="text" @click="removeCenter(airInfo.centers,index)"
                                                   style="color:red">删除
                                        </el-button>
                                    </div>
                                </div>
                            </el-form-item>

                        </div>
                        <el-button type="text" @click="addAirInfo">+ 添加空域</el-button>

                    </el-form-item>

                    <el-form-item label="主起降场" prop="landingInfo.master">
                        <div>
                            <el-form-item label="名称" class="landing_item">
                                <el-select style="width:120px;" v-model="airapply.landingInfo.master.id"
                                           placeholder="请选择" @change="handleChangeLanding(airapply.landingInfo.master)">
                                    <el-option v-for="landing in landings" :label="landing.name"
                                               :value="landing.id"></el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="起降场位置描述" v-if="airapply.landingInfo.master.id==-1"
                                          class="landing_item">
                                <div style="margin-left:0px;">
                                    <el-input style="width:400px;" v-model="airapply.landingInfo.master.location"
                                              placeholder="请输入内容"></el-input>
                                    <div style="color:#3366ff;"><i>请以选择空域内起降场附近的地标或建设物为参考</i></div>
                                </div>
                            </el-form-item>
                            <el-form-item label="起降场坐标" v-if="airapply.landingInfo.master.id==-1" class="landing_item">
                                <div style="margin-left:130px;">
                                    <div class="left">
                                        <span style="background:#169bd5;padding:5px;">经</span>
                                        <el-input-number :controls="false" :debounce="0"
                                                         style="margin-right:10px;width:50px;"
                                                         v-model="airapply.landingInfo.master.lng0" :min="-180"
                                                         :max="180"></el-input-number>
                                        <span>度</span>
                                        <el-input-number :controls="false" :debounce="0"
                                                         style="margin-right:10px;width:50px;"
                                                         v-model="airapply.landingInfo.master.lng1" :min="0"
                                                         :max="60"></el-input-number>
                                        <span>分</span>
                                        <el-input-number :controls="false" :debounce="0"
                                                         style="margin-right:10px;width:50px;"
                                                         v-model="airapply.landingInfo.master.lng2" :min="0"
                                                         :max="60"></el-input-number>
                                        <span>秒</span>
                                    </div>
                                    <div class="left" style="margin-top:10px;">
                                        <span style="background:#169bd5;padding:5px;">纬</span>
                                        <el-input-number :controls="false" :debounce="0"
                                                         style="margin-right:10px;width:50px;"
                                                         v-model="airapply.landingInfo.master.lat0" :min="-90"
                                                         :max="90"></el-input-number>
                                        <span>度</span>
                                        <el-input-number :controls="false" :debounce="0"
                                                         style="margin-right:10px;width:50px;"
                                                         v-model="airapply.landingInfo.master.lat1" :min="0"
                                                         :max="60"></el-input-number>
                                        <span>分</span>
                                        <el-input-number :controls="false" :debounce="0"
                                                         style="margin-right:10px;width:50px;"
                                                         v-model="airapply.landingInfo.master.lat2" :min="0"
                                                         :max="60"></el-input-number>
                                        <span>秒</span>
                                    </div>
                                </div>
                            </el-form-item>
                        </div>
                    </el-form-item>

                    <el-form-item label="备用起降场" prop="landingInfo.slave">
                        <el-button v-if="airapply.landingInfo.slave==null" type="text" @click="addSlaveLanding">+ 添加起降场</el-button>
                        <div v-if="airapply.landingInfo.slave!=null">
                            <el-form-item label="名称" class="landing_item">
                                <el-select style="width:130px;" v-model="airapply.landingInfo.slave.id"
                                           placeholder="请选择" @change="handleChangeLanding(airapply.landingInfo.slave)">
                                    <el-option v-for="landing in landings" :label="landing.name"
                                               :value="landing.id"></el-option>
                                </el-select>
                                <el-button type="text" @click="removeSlaveLanding" style="color:red;">- 删除起降场</el-button>
                            </el-form-item>
                            <el-form-item label="起降场位置描述" v-if="airapply.landingInfo.slave.id==-1" class="landing_item">
                                <div style="margin-left:130px;">
                                    <el-input style="width:400px;" v-model="airapply.landingInfo.slave.location"
                                              placeholder="请输入内容"></el-input>
                                    <div style="color:#3366ff;"><i>请以选择空域内起降场附近的地标或建设物为参考</i></div>
                                </div>
                            </el-form-item>
                            <el-form-item label="起降场坐标" v-if="airapply.landingInfo.slave.id==-1" class="landing_item">
                                <div style="margin-left:130px;">
                                    <div class="left">
                                        <span style="background:#169bd5;padding:5px;">经</span>
                                        <el-input-number :controls="false" :debounce="0"
                                                         style="margin-right:10px;width:50px;"
                                                         v-model="airapply.landingInfo.slave.lng0" :min="-180"
                                                         :max="180"></el-input-number>
                                        <span>度</span>
                                        <el-input-number :controls="false" :debounce="0"
                                                         style="margin-right:10px;width:50px;"
                                                         v-model="airapply.landingInfo.slave.lng1" :min="0"
                                                         :max="60"></el-input-number>
                                        <span>分</span>
                                        <el-input-number :controls="false" :debounce="0"
                                                         style="margin-right:10px;width:50px;"
                                                         v-model="airapply.landingInfo.slave.lng2" :min="0"
                                                         :max="60"></el-input-number>
                                        <span>秒</span>
                                    </div>
                                    <div class="left" style="margin-top:10px;">
                                        <span style="background:#169bd5;padding:5px;">纬</span>
                                        <el-input-number :controls="false" :debounce="0"
                                                         style="margin-right:10px;width:50px;"
                                                         v-model="airapply.landingInfo.slave.lat0" :min="-90"
                                                         :max="90"></el-input-number>
                                        <span>度</span>
                                        <el-input-number :controls="false" :debounce="0"
                                                         style="margin-right:10px;width:50px;"
                                                         v-model="airapply.landingInfo.slave.lat1" :min="0"
                                                         :max="60"></el-input-number>
                                        <span>分</span>
                                        <el-input-number :controls="false" :debounce="0"
                                                         style="margin-right:10px;width:50px;"
                                                         v-model="airapply.landingInfo.slave.lat2" :min="0"
                                                         :max="60"></el-input-number>
                                        <span>秒</span>
                                    </div>
                                </div>
                            </el-form-item>
                        </div>
                    </el-form-item>

                    <el-form-item label="飞行规则">
                        <el-select v-model="airapply.flyRule" placeholder="请选择" prop="flyRules">
                            <el-option v-for="item in flyrules" :key="item.label" :label="item.label"
                                       :value="item.label"></el-option>
                        </el-select>
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
                                <el-input style="width:150px;" v-model="airapply.contactInfo.unitContactName"
                                          placeholder="请输入内容"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="联系电话" prop="contactInfo.unitContact">
                                <el-input style="width:150px" v-model="airapply.contactInfo.unitContact"
                                          placeholder="请输入内容"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="紧急联系人" prop="contactInfo.emergencyContactName">
                                <el-input style="width:150px;"
                                          v-model="airapply.contactInfo.emergencyContactName"
                                          placeholder="请输入内容"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="联系手机" prop="contactInfo.emergencyContactPhone">
                                <el-input style="width:150px;"
                                          v-model="airapply.contactInfo.emergencyContactPhone"
                                          placeholder="请输入内容"></el-input>
                            </el-form-item>
                        </el-col>

                    </el-row>
                </div>

                <div class="plan-form-title">
                    <div class="devider"></div>
                    <p class="text">其他信息</p>
                </div>
                <div class="plan-form-content clear">
                    <el-form-item label="上传附件" prop="attachment">
                        <el-upload :action="uploadFileUrl" :before-upload="beforeFileUpload" :multiple="false" accept=".pdf"
                                   :on-success="handleFileUploadSuccess" :on-error="handleFileUploadFail"
                                   :on-change="handleFileChange" :file-list="airapply.file">
                            <el-button size="small" type="primary">点击上传</el-button>
                            <div slot="tip" class="el-upload__tip">
                                附件包括：申请函、任务托书（任务飞行必需提供）、公司资质及情况说明、无人机驾驶员资料、飞行器资料等内容，相关模板可以在本网站下载。所有上传格式为*.pdf ,大小不超过5MB
                            </div>
                        </el-upload>
                    </el-form-item>
                    <el-form-item label="其他说明事项">
                        <el-input type="textarea" :rows="7" v-model="airapply.remark"></el-input>
                    </el-form-item>
                </div>

                <el-form-item>
                    <el-button type="primary" @click="submit" style="width:130px;" >提交
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

            /**
             * 添加一个备用起降场
             */
            addSlaveLanding: function () {
                var landingInfo = {
                    id: '-1',
                    name: '自选起降场',
                    location: '',
                    lng0: 0, lng1: 0, lng1: 0,
                    lat0: 0, lat1: 0, lat2: 0
                };
                this.airapply.landingInfo.slave = landingInfo;
            },
            /**
             * 删除一个备用起降场
             */
            removeSlaveLanding: function () {
                this.airapply.landingInfo.slave = null;
            },
            /**
             * 添加一个空域
             */
            addAirInfo: function () {
                var airInfo = {
                    location: '',
                    high: '0',
                    scopeType: 1,
                    circleScope: {
                        lng0: 0, lng1: 0, lng2: 0, lat0: 0, lat1: 0, lat2: 0,
                        radius: 0
                    },
                    centers: [{lng0: 0, lng1: 0, lng2: 0, lat0: 0, lat1: 0, lat2: 0},
                        {lng0: 0, lng1: 0, lng2: 0, lat0: 0, lat1: 0, lat2: 0},
                        {lng0: 0, lng1: 0, lng2: 0, lat0: 0, lat1: 0, lat2: 0}
                    ]
                }
                this.airapply.airInfo.push(airInfo);
            },
            /**
             * 删除一个空域
             */
            removeAirInfo: function (index) {
                if (this.airapply.airInfo.length < 2) {
                    return;
                }
                this.airapply.airInfo.splice(index, 1);
            },
            //增加一个空域转向点
            addCenter: function (centers) {
                centers.push({lng0: 0, lng1: 0, lng2: 0, lat0: 0, lat1: 0, lat2: 0});
            },
            //删除最后一个空域转向点
            removeLastCenter: function (centers) {
                centers.splice(centers.length - 1, 1);
            },
            removeCenter: function (centers, index) {
                if (centers.length < 4) {
                    this.$message('请至少填写3个拐点坐标');
                    return;
                }
                centers.splice(index, 1);
            },
            /**
             * 处理起降场变更
             * @param targetLanding 目标起降场对象
             */
            handleChangeLanding: function (targetLanding) {
                var selectedLanding = this.landings.find(function (value) {
                    return value.id == targetLanding.id;
                });
                Vue.util.extend(targetLanding, selectedLanding);
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
            handleFileChange(file, fileList) {
                this.airapply.file = fileList.slice(-1);
            },
            /**
             * 处理文档上传成功
             */
            handleFileUploadSuccess(response, file, fileList){
                this.airapply.attachInfo = {
                    name: response.data[0].srcName,
                    url: response.data[0].fullUrl
                };
            },
            /**
             * 处理文档上传失败
             */
            handleFileUploadFail(err, file, fileList){
                this.$message.error('附件上传失败，请重试');
            },
            /**
             * 上传文件前对文件进行校检
             */
            beforeFileUpload(file) {
                const isPDF = file.type == 'application/pdf';
                const isLt2M = file.size / 1024 / 1024 < 5;
                if (!isPDF) {
                    this.$message.error('只允许上传pdf文件');
                    return false;
                }
                if (!isLt2M) {
                    this.$message.error('文件大小不能超过10M');
                    return false;
                }
                return true;
            },
            /**
             * 返回 上一页
             */
            back(){
                this.$router.go(-1);
            },
            /**
             * 提交表单
             * @param airapply
             */
            submit(){
                var airapplyForm = this.$refs['airapply'];
                var validate = true;
                for (var prop in this.rules) {
                    airapplyForm.validateField(prop, function (errorMessage) {
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
                    url: urls.AIRPLAN,
                    method: 'post',
                    contentType: 'application/json',
                    data: this.getRequestBody(),
                    success: function (res) {
                        window.location.href = '#/apply';
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
                var airapply = this.airapply;
                //时间
                var beginTime = util.formatDate(airapply.daterange[0], 'yyyy-MM-dd');
                var endTime = util.formatDate(airapply.daterange[1], 'yyyy-MM-dd');
                //空域信息
                var airInfo = airapply.airInfo.map(function (value) {
                    var airInfoMap = {
                        location: value.location,
                        high: value.high,
                        scopeType: value.scopeType
                    }
                    if (value.scopeType == 1) {
                        var circleScope = {
                            lng: value.lng0 + ',' + value.lng1 + ',' + value.lng2,
                            lat: value.lat0 + ',' + value.lat1 + ',' + value.lat2,
                            radius: value.radius
                        };
                        airInfoMap.circleScope = circleScope;
                    } else if (value.scopeType == 2) {
                        var centers = value.centers.map(function (center) {
                            return {
                                lng: center.lng0 + ',' + center.lng1 + ',' + center.lng2,
                                lat: center.lat0 + ',' + center.lat1 + ',' + center.lat2,
                            }
                        });
                        airInfoMap.centers = centers;
                    }

                    return airInfoMap;
                });
                //主起降场信息
                var masterlandingInfo = airapply.landingInfo.master;
                var tempMasterlandingInfo = {
                    id: masterlandingInfo.id,
                    name: masterlandingInfo.name,
                    location: masterlandingInfo.location
                }
                //自选起降场才填写经纬
                if (tempMasterlandingInfo.id == -1) {
                    tempMasterlandingInfo.lng = masterlandingInfo.lng0 + "," + masterlandingInfo.lng1 + "," + masterlandingInfo.lng2;
                    tempMasterlandingInfo.lat = masterlandingInfo.lat0 + "," + masterlandingInfo.lat1 + "," + masterlandingInfo.lat2;
                }

                //备起降场信息
                var slavelandingInfo = airapply.landingInfo.slave;
                if(slavelandingInfo != null){
                    var tempSlavelandingInfo = {
                        id: slavelandingInfo.id,
                        name: slavelandingInfo.name,
                        location: slavelandingInfo.location
                    }
                    //自选起降场才填写经纬
                    if (tempSlavelandingInfo.id == -1) {
                        tempSlavelandingInfo.lng = slavelandingInfo.lng0 + "," + slavelandingInfo.lng1 + "," + slavelandingInfo.lng2;
                        tempSlavelandingInfo.lat = slavelandingInfo.lat0 + "," + slavelandingInfo.lat1 + "," + slavelandingInfo.lat2;
                    }
                    slavelandingInfo = tempSlavelandingInfo;
                }


                //联系方式
                var contactInfo = Vue.util.extend({}, airapply.contactInfo);

                var planes = airapply.planes.filter(function (plane) {
                    return plane.number > 0;
                }).map(function (plane) {
                    return {
                        name: plane.name == '其他' ? plane.desc : plane.name,
                        number: plane.number
                    }
                });
                var request = {
                    type: airapply.type,
                    planType: airapply.planType == '其他' ? airapply.planTypeName : airapply.planType,
                    planes: planes,
                    beginTime: beginTime,
                    endTime: endTime,
                    //空域信息
                    airInfo: airInfo,
                    landingInfo: {
                        master: tempMasterlandingInfo,
                        slave: tempSlavelandingInfo
                    },
                    contactInfo: contactInfo,
                    weatherCondition: airapply.weatherCondition,
                    flyRule: airapply.flyRule,
                    attachInfo: airapply.attachInfo,
                    remark: airapply.remark
                }
                return JSON.stringify(request);
            }
        },
        data(){
            var planTypeValidator = (rule, value, callback) => {
                if (this.airapply.planType == "其他" && this.airapply.planTypeName == "") {
                    return callback(new Error(rule.message));
                }
                return callback();
            };
            var planesValidator = (rule, value, callback) => {
                var hasPlane = this.airapply.planes.some(function (plane) {
                    return plane.checked == true && plane.number > 0;
                });
                var has0NumberPlane = this.airapply.planes.some(function (plane) {
                    return plane.checked == true && plane.number == 0;
                });
                if (!hasPlane || has0NumberPlane) {
                    return callback(new Error(rule.message));
                }
                return callback();
            };
            var planTimeValidator = (rule, value, callback) => {
                if (this.airapply.daterange == '' || this.airapply.daterange == null) {
                    return callback(new Error(rule.message));
                }
                callback();
            };
            var emergencyContactNameValidator = (rule, value, callback) => {
                if (this.airapply.contactInfo.unitContact == this.airapply.contactInfo.emergencyContactName) {
                    return callback(new Error(rule.message));
                }
                callback();
            };
            var planPhoneValidator = (rule, value, callback) => {
            	var myreg =/^1[34578]\d{9}$/;
            	if(!myreg.test(this.airapply.contactInfo.unitContact)){
            		return callback(new Error("请填写正确手机号"));
            	}
                callback();
            };
            var planContactPhone = (rule, value, callback) => {
            	var myreg =/^1[34578]\d{9}$/;
            	if(!myreg.test(this.airapply.contactInfo.emergencyContactPhone)){
            		return callback(new Error("请填写正确手机号"));
            	}
                callback();
            };
         
            var emergencyContactPhoneValidator = (rule, value, callback) => {
                if (this.airapply.contactInfo.unitContact == this.airapply.contactInfo.emergencyContactPhone) {
                    return callback(new Error(rule.message));
                }
                callback();
            };
            var airInfoValidator = (rule, value, callback) => {
                var index = this.airapply.airInfo.findIndex(function (value) {
                    return value.location == '';
                });
                if (index != -1) {
                    return callback(new Error(rule.message));
                }
                callback();
            };
            var masterLandingInfoValidator = (rule, value, callback) => {
                var location = this.airapply.landingInfo.master.location;
                if (location == '' || location == null) {
                    return callback(new Error(rule.message));
                }
                callback();
            };
            var slaveLandingInfoValidator = (rule, value, callback) => {
                var slaveLanding = this.airapply.landingInfo.slave;
                if(slaveLanding != null){
                    var location = slaveLanding.location;
                    if ( location == '' || location == null) {
                        return callback(new Error(rule.message));
                    }
                }
                callback();
            };
            var attachmentValidator = (rule, value, callback) => {
                var attachInfo = this.airapply.attachInfo;
                if (attachInfo.length > 0 && attachInfo[0].status == 'finished') {
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
                    'airInfo': [{required: true, message: '空域位置不能为空', trigger: 'blur', validator: airInfoValidator}],
                    'landingInfo.master': [{
                        required: true,
                        message: '主起降场位置不能为空',
                        trigger: 'blur',
                        validator: masterLandingInfoValidator
                    }],
                    'landingInfo.slave': [{
                        message: '备起降场位置不能为空',
                        trigger: 'blur',
                        validator: slaveLandingInfoValidator
                    }],
                    'contactInfo.unitContactName': [
                        {required: true, message: '请输入单位联系人名字', trigger: 'blur'},
                        {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'}
                    ],
                    'contactInfo.unitContact': [
                        {required: true, message: '请输入单位联系人手机号码', trigger: 'blur'},
                        {min: 11, max: 11, validator: planPhoneValidator, trigger: 'blur'}
                    ],
                    'contactInfo.emergencyContactName': [
                        {required: true, message: '请输入紧急联系人名字', trigger: 'blur'},
                        {min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur'},
                        {validator: emergencyContactNameValidator, message: '单位联系人不能与紧急联系人为同一人', trigger: 'blur'}
                    ],
                    'contactInfo.emergencyContactPhone': [
                        {required: true, message: '请输入紧急联系人手机号码', trigger: 'blur'},
                        {min: 11, max: 11, validator: planContactPhone, trigger: 'blur'},
                        {
                            validator: emergencyContactPhoneValidator,
                            message: '单位联系人的手机号码不能与紧急联系人的手机号码相同',
                            trigger: 'blur'
                        }
                    ],
                    'attachment': [{
                        required: true,
                        message: '附件不能为空',
                        trigger: 'blur',
                        validator: attachmentValidator
                    }]

                },
                uploadFileUrl: urls.GET_UPLOAD,
                //飞行类型
                flyrules: [{label: '目视飞行'}, {label: '仪表飞行'}],
                //是否加载中
                isLoading: false,
                //日期选择器配置
                pickerOptions: {
                    disabledDate(time) {
                        return time.getTime() < Date.now();
                    }
                },

                //飞行计划输入框是否可用
                planTypeInputDisable: true,
                //飞行计划
                airapply: {
                    planType: this.$store.state.planTypes.plan0[0].label,
                    planTypeName: '',//其他飞行类型名称,
                    planes: planes,
                    daterange: '',
                    planTime: {
                        date: new Date(new Date().getTime() + 24 * 3600 * 1000) //只能申请下一天的计划
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
                    landingInfo: {
                        master: {
                            id: '-1',
                            location: '',
                            name: '自选起降场',
                            lat0: 0, lat1: 0, lat2: 0,
                            lng0: 0, lng1: 0, lng2: 0
                        }, slave: null
                    },
                    contactInfo: {
                        unitContactName: '',
                        unitContact: '',
                        emergencyContactName: '',
                        emergencyContactPhone: '',
                    },
                    file: [],
                    flyRule: '目视飞行',
                    remark: '',
                    attachInfo: {
                        name: '',
                        url: ''
                    }
                },
            }
        },
        computed: {
            //计划类型
            planTypes: function () {
                var planTypes = this.$store.state.planTypes.airapply;
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
                    if (this.airapply.planTime.beginMinute < 50) {
                        return parseInt(value.label) >= parseInt(this.airapply.planTime.beginHour);
                    } else {
                        return parseInt(value.label) >= parseInt(this.airapply.planTime.beginHour) + 1;
                    }
                }.bind(this));
            },
            //可选结束飞行时间-分钟
            selectableFlyEndMinutes(){
                var minutes = this.$store.state.flyMinutes;
                return minutes.filter(function (value) {
                    if (parseInt(this.airapply.planTime.beginHour) == parseInt(this.airapply.planTime.endHour)) {
                        return parseInt(value.label) > parseInt(this.airapply.planTime.beginMinute);
                    }
                    return true;
                }.bind(this));
            }
        },
        created(){
            this.user = this.$store.getters.getSession;
        },
        mounted(){
            if (this.user.type == 1) {
                this.$alert('该页面内容仅企业用户填报有效，个人用户填报暂时不予审核', '温馨提示', {
                    confirmButtonText: '确定',
                    type: 'warning'
                });
            }

            //将模板数据插入到data中
            var flyCode = this.$route.params.tmplCode;
            if (flyCode != undefined) {
                $.ajax({
                    url: urls.AIRPLAN + "/" + flyCode,
                    method: 'get',
                    dataType: 'json',
                    success: function (res) {
                        //格式化计划类型数据
                        var planType = res.data.planType;
                        var planTypeIndex = this.$store.state.planTypes.plan0.findIndex(function (planType) {
                            return planType == planType.label;
                        });
                        if (planTypeIndex == -1) {
                            this.airapply.planType = '其他';
                            this.planTypeName = planType;
                        } else {
                            this.airapply.planType = planType;
                        }

                        //格式化机型数据
                        res.data.planes = JSON.parse(res.data.planes);
                        //过滤数据为0的机型
                        res.data.planes = res.data.planes.filter(function (plane) {
                            return plane.number > 0;
                        })
                        this.airapply.planes.forEach(function (plane) {
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
                            var otherPlane = this.airapply.planes.find(function (plane) {
                                return plane.name == '其他';
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
                        this.airapply.airInfo[0] = airspace;

                        //格式化主起降场数据
                        res.data.landingInfo = JSON.parse(res.data.landingInfo);
                        var masterLandingInfo = res.data.landingInfo.master;
                        //自选机场
                        if (masterLandingInfo.id == -1) {
                            var lngArray = masterLandingInfo.lng.split(',');
                            var latArray = masterLandingInfo.lat.split(',');
                            Vue.util.extend(masterLandingInfo, {
                                lng0: lngArray[0],
                                lng1: lngArray[1],
                                lng2: lngArray[2],
                                lat0: latArray[0],
                                lat1: latArray[1],
                                lat2: latArray[2]
                            })
                        }
                        this.airapply.landingInfo.master = masterLandingInfo;

                        //格式化备起降场数据
                        var slaveLandingInfo = res.data.landingInfo.slave;
                        //自选机场
                        if (slaveLandingInfo.id == -1 && slaveLandingInfo != null) {
                            var lngArray = slaveLandingInfo.lng.split(',');
                            var latArray = slaveLandingInfo.lat.split(',');
                            Vue.util.extend(slaveLandingInfo, {
                                lng0: lngArray[0],
                                lng1: lngArray[1],
                                lng2: lngArray[2],
                                lat0: latArray[0],
                                lat1: latArray[1],
                                lat2: latArray[2]
                            })
                        }
                        this.airapply.landingInfo.slave = slaveLandingInfo;


                        //格式化联系人数据
                        res.data.contactInfo = JSON.parse(res.data.contactInfo);
                        res.data.contactInfo.trainer = res.data.contactInfo.trainer.split(",");
                        this.airapply.contactInfo = res.data.contactInfo;

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
    .el-input__inner{    	
    }
    
.el-radio{
 
	float: left;
	
	margin: 4px 4px;
	padding-right: 15px;
}
.el-radio-group{
	float: left;
	
}

</style>
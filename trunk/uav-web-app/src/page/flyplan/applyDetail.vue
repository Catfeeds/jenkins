<!-- 一类飞行计划明细 -->
<template>
    <div class="plan">
        <div class="title clear">
            <p class="left" style="font-size:18px">
            <span>空域申请 ，编号 </span>
                <strong>{{airapply.number}}</strong>
            </p>
            <div class="right">
                <el-button ><a href="#apply">返回</a></el-button>
            </div>
        </div>

        <div class="plan-form">
            <el-form  label-position="left"  label-width="120px">
                <div class="plan-form-title">
                    <div class="devider"></div>
                    <p class="text">基础信息</p>
                </div>
                <div class="plan-form-content">
                    <el-form-item label="申请个人/单位">
                        {{user.name}}
                    </el-form-item>
                    <el-form-item label="计划类型">
                       {{airapply.planType}}
                    </el-form-item>
                    <el-form-item label="使用机型">
                        <span style="margin-right:15px;" v-for="item in airapply.planes">{{item.name}}:{{item.number}}架</span>
                    </el-form-item>
                    <!--parseInt-->
                    <el-form-item label="使用时间">{{parseInt(airapply.beginTime)}} ~ {{parseInt(airapply.endTime)}}</el-form-item>
                    <el-form-item label="空域范围及高度">
                        <table style="width:100%;border-collapse:collapse;" border="1">
                            <tr v-for="(item,$index) in airapply.airInfo" style="border-right:1px solid #999999;padding:5px;">
                                <td>空域 {{$index+1}}</td>
                                <td style="width:90%;padding:5px;">
                                    <p>位置描述：{{item.location}}</p>
                                    <div v-if="item.scopeType==1">
                                        <p><span>中&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;心：({{item.circleScope.lng}},{{item.circleScope.lat}})</span></p>
                                        <p><span>半&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;径：{{item.circleScope.radius}}米</span></p>
                                        <p>真&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;高：{{item.high}} 米(含以下)</p>
                                    </div>
                                    <div v-if="item.scopeType==2">
                                        <p><span>中&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;心：<span :item="item" v-for="(lnglat,value) in item.centers"> ({{lnglat.lng}},{{lnglat.lat}})</span></span></p>
                                        <p>真&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;高：{{item.high}} 米(含以下)</p>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </el-form-item>

                    <el-form-item label="主用起降场" prop="landingInfo.master">
                        <div>
                            <el-form-item label="名称" class="landing_item">
                                {{airapply.landingInfo.master.name}}
                            </el-form-item>
                            <el-form-item label="起降场位置描述" v-if="airapply.landingInfo.master.id==-1"
                                          class="landing_item">
                                {{airapply.landingInfo.master.location}}
                            </el-form-item>
                            <el-form-item label="起降场坐标" v-if="airapply.landingInfo.master.id==-1" class="landing_item">
                                    <span style="color:#169bd5;">经</span>
                                    ：{{airapply.landingInfo.master.lng}}
                                    <span style="color:#169bd5;margin-left:25px;">纬</span>
                                    ：{{airapply.landingInfo.master.lat}}
                            </el-form-item>
                        </div>
                    </el-form-item>

                    <el-form-item label="备用起降场" prop="landingInfo.slave">
                        <div v-if="airapply.landingInfo.slave != null">
                            <el-form-item label="名称" class="landing_item">
                                {{airapply.landingInfo.slave.name}}
                            </el-form-item>
                            <el-form-item label="起降场位置描述" v-if="airapply.landingInfo.slave.id==-1"
                                          class="landing_item">
                                {{airapply.landingInfo.slave.location}}
                            </el-form-item>
                            <el-form-item label="起降场坐标" v-if="airapply.landingInfo.slave.id==-1" class="landing_item">
                                <span style="color:#169bd5;">经</span>
                                ：{{airapply.landingInfo.slave.lng}}
                                <span style="color:#169bd5;margin-left:25px;">纬</span>
                                ：{{airapply.landingInfo.slave.lat}}
                            </el-form-item>
                        </div>
                    </el-form-item>

                </div>
                    <div class="plan-form-title">
                        <div class="devider"></div>
                        <p class="text">操控员或负责人信息</p>
                    </div>
                <div class="plan-form-content">
                    <div class="clear">
                        <el-form-item label="单位联系人" class="left" style="width:45%;" prop="captainName">
                            {{airapply.contatInfo.unitContactName}}
                        </el-form-item>
                        <el-form-item label="单位联系方式" class="left" style="width:50%;" prop="captainTel">
                            {{airapply.contatInfo.unitContact}}
                        </el-form-item>
                        <el-form-item label="现场联系人" class="left" style="width:45%;" prop="urgencyMan">
                            {{airapply.contatInfo.emergencyContactName}}
                        </el-form-item>
                        <el-form-item label="联系方式" class="left" style="width:50%;" prop="urgencyTel">
                            {{airapply.contatInfo.emergencyContactPhone}}
                        </el-form-item>  
                    </div>
                </div>
                    <div class="plan-form-title">
                        <div class="devider"></div>
                        <p class="text">其他</p>
                    </div>
                <div class="plan-form-content">
                    <el-form-item label="其他说明事项">
                        {{airapply.remark}}
                    </el-form-item>
                </div>
            </el-form>
        </div>


    </div>
</template>

<script>
    import '../../style/plan.css'
    import urls from '../../components/urls'
    import Vue from 'vue'

    export default{
        methods: {
            //将一个形如130,23,32的坐标转换成130度23分32秒的显示方式
            transformCoordinate: function (coordinate) {
                var coordinateArray = coordinate.split(',');
                return coordinateArray[0] + '度' +
                    coordinateArray[1] + '分' +
                    coordinateArray[2] + '秒';
            }
        },
        data(){
            return {
                //飞行计划
                airapply: {
                        beginTime: '',
                        endTime: '',
                        number: '',
                        planes: '',
                        planType: '',
                        landingInfo: {
                            master: {
                                id: '',
                                name: null,
                                lng: '',
                                lat: '',
                                location: ''
                            },
                            slave: null
                        },
                        airInfo: [
                            {
                                location: '',
                                scopeType: '',
                                circleScope: {
                                    lng: '',
                                    lat: "",
                                    radius: ""
                                },
                                centers: null,
                                high: ''
                            },
                           
                        ],
                        contatInfo: {
                            unitContactName: '',
                            unitContact: '',
                            emergencyContactName: '',
                            emergencyContactPhone: ''
                        },
                        remark: '',
                    }
                }
            
        },
        computed: {

        },
        created(){
            this.user = this.$store.getters.getSession;
        },
        mounted(){
            var airapplyCode  = this.$route.params.airApplyCode;
            var URL = urls.AIRPLAN+"/"+airapplyCode;
            $.ajax({
                type: 'GET',
                url: URL ,
                success: function(res){
                    this.airapply=res.data;
                    this.airapply.airInfo=JSON.parse(res.data.airInfo);
                    this.airapply.contatInfo=JSON.parse(res.data.contatInfo);
                    this.airapply.landingInfo=JSON.parse(res.data.landingInfo);
                    this.airapply.planes=JSON.parse(res.data.planes);
                    //格式化起降场数据
                    if(this.airapply.landingInfo.master.lng != undefined){
                        this.airapply.landingInfo.master.lng = this.transformCoordinate(this.airapply.landingInfo.master.lng);
                        this.airapply.landingInfo.master.lat = this.transformCoordinate(this.airapply.landingInfo.master.lat);
                    }
                    if(this.airapply.landingInfo.slave != null && this.airapply.landingInfo.slave.lng != undefined){
                        this.airapply.landingInfo.slave.lng = this.transformCoordinate(this.airapply.landingInfo.slave.lng);
                        this.airapply.landingInfo.slave.lat = this.transformCoordinate(this.airapply.landingInfo.slave.lat);
                    }
                    //格式化空域数据
                    this.airapply.airInfo.forEach(function (value) {
                        if(value.scopeType==1){
                            value.circleScope.lng = this.transformCoordinate(value.circleScope.lng);
                            value.circleScope.lat = this.transformCoordinate(value.circleScope.lat);
                        }
                        else if(value.scopeType==2){
                            value.centers.forEach(function(lnglat){
                                lnglat.lng = this.transformCoordinate(lnglat.lng);
                                lnglat.lat = this.transformCoordinate(lnglat.lat);
                            }.bind(this))
                        }
                    }.bind(this))
                }.bind(this) ,
                dataType: 'json'

            });
        }
    }
</script>

<style lang="less" scoped="">

</style>
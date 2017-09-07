<template>
    <div class="plan">
        <div class="title clear">
            <p class="left" style="font-size:18px">
                <span>管制空域报告 ，编号 </span>
                <strong>{{flyplan.number}}</strong>
            </p>
            <div class="right">
                <el-button @click="back">返回</el-button>
            </div>
        </div>
        <div class="plan-form">

            <el-form ref="flyplan" label-position="left" label-width="120px">
                <div class="plan-form-title">
                    <div class="devider"></div>
                    <p class="text">基础信息</p>
                </div>
                <div class="plan-form-content">
                    <el-form-item label="申请单位">
                        {{user.name}}
                    </el-form-item>
                    <el-form-item label="空域批文编号">
                        {{flyplan.applyOfficialNo}}
                    </el-form-item>
                    <el-form-item label="计划类型">
                        {{flyplan.planType}}
                    </el-form-item>
                    <el-form-item label="使用机型" class="clear" prop="useType" auto-complete="off">
                       <span v-for="plane in flyplan.planes" style="margin-right:5px;">
                            {{plane.name}} : {{plane.number}}架
                        </span>
                    </el-form-item>
                    <el-form-item label="使用时间" prop="planTime">
                        {{flyplan.beginTime}} - {{flyplan.endTime}}
                    </el-form-item>
                    <el-form-item label="空域范围及高度">
                        <div v-for="(airInfo,index) in flyplan.airInfo"
                             style="border-bottom:1px dashed #6b6b6b;padding-bottom:20px">
                            <el-form-item label="位置描述">
                                {{airInfo.location}}
                            </el-form-item>
                            <el-form-item label="范      围">
                                <div v-if="airInfo.scopeType==1">
                                    飞行半径{{airInfo.circleScope.radius}}米，中心点坐标：
                                    <span style="color:#3399ff;">经</span>
                                    {{airInfo.circleScope.lng}}
                                    <span style="color:#3399ff;">纬</span>
                                    {{airInfo.circleScope.lat}}
                                </div>
                                <div v-if="airInfo.scopeType==2">
                                    {{airInfo.centers.length}}点连线范围内，拐点坐标分别为：
                                    <div v-for="lngLat in airInfo.centers" style="margin-left: 120px;">
                                        <span style="color:#3399ff;">经</span>
                                        {{lngLat.lng}}
                                        <span style="color:#3399ff;">纬</span>
                                        {{lngLat.lat}}
                                    </div>
                                </div>
                            </el-form-item>
                            <el-form-item label="真高">
                                {{airInfo.high}}米（含以下）
                            </el-form-item>
                        </div>
                    </el-form-item>

                    <el-form-item label="使用起降场" style="margin-top:50px;">
                        <div v-for="(landingInfo,index) in flyplan.landingInfo"
                             style="border-bottom:1px dashed #6b6b6b;padding-bottom:35px;">
                            <strong></strong>
                            <el-form-item label="名称">
                                {{landingInfo.name}}
                            </el-form-item>
                            <el-form-item label="位置描述" v-if="landingInfo.id==-1">
                                {{landingInfo.location}}
                            </el-form-item>
                            <el-form-item label="坐标" v-if="landingInfo.id==-1">
                                <span style="color:#3399ff;">经</span>
                                {{landingInfo.lng}}
                                <span style="color:#3399ff;">纬</span>
                                {{landingInfo.lat}}
                            </el-form-item>
                        </div>
                    </el-form-item>
                </div>

                <div class="plan-form-title">
                    <div class="devider"></div>
                    <p class="text">操控员或负责人信息</p>
                </div>
                <div class="plan-form-content">
                    <el-form-item label="单位联系人">
                        {{flyplan.contactInfo.unitContactName}}
                    </el-form-item>
                    <el-form-item label="单位联系方式">
                        {{flyplan.contactInfo.unitContact}}
                    </el-form-item>

                    <el-form-item label="现场联系人">
                        {{flyplan.contactInfo.fieldContactName}}
                    </el-form-item>
                    <el-form-item label="联系方式">
                        {{flyplan.contactInfo.fieldContactPhone}}
                    </el-form-item>
                </div>

                <div class="plan-form-title">
                    <div class="devider"></div>
                    <p class="text">其他信息</p>
                </div>
                <div class="plan-form-content">
                    <el-form-item label="气象条件">
                        {{flyplan.weatherCondition}}
                    </el-form-item>
                    <el-form-item label="其他说明事项">
                        {{flyplan.remark}}
                    </el-form-item>
                </div>
            </el-form>
        </div>


    </div>
</template>

<script>
    import '../../style/plan.css'
    import urls from '../../components/urls'
    export default{

        methods: {
            //返回上一级
            back(){
                window.location.href = '#/myplan';
            },
            //将一个形如130,23,32的坐标转换成130度23分32秒的显示方式
            transformCoordinate: function (coordinate) {
                var coordinateArray = coordinate.split(',');
                return coordinateArray[0] + '度' +
                    coordinateArray[1] + '分' +
                    coordinateArray[2] + '秒';
            }
        },
        computed: {},
        data(){
            return {
                user: {},
                flyplan: {
                    contactInfo: {},
                    landingInfo: [{}],
                    airInfo: [{}],
                    planes: []
                }
            }
        },
        created(){
            this.user = this.$store.getters.getSession;
            var flyCode = this.$route.params.flyCode;

            $.ajax({
                url: urls.GET_FLYPLAN + "/" + flyCode,
                method: 'get',
                dataType: 'json',
                success: function (res) {
                    this.flyplan = res.data;
                    this.flyplan.planes = JSON.parse(this.flyplan.planes);
                    this.flyplan.airInfo = JSON.parse(this.flyplan.airInfo);
                    this.flyplan.landingInfo = JSON.parse(this.flyplan.landingInfo);
                    this.flyplan.contactInfo = JSON.parse(this.flyplan.contactInfo);
                    this.flyplan.landingInfo.forEach(function (value) {
                        if(value.lng){
                            //转换起降场坐标显示方式
                            value.lng = this.transformCoordinate(value.lng);
                            value.lat = this.transformCoordinate(value.lat);
                        }
                    }.bind(this))

                    this.flyplan.airInfo.forEach(function (value) {
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

                }.bind(this)
            })
        }
    }
</script>

<style lang="less">
    .myplan_sel .el-input__icon + .el-input__inner {
        width: 150px;
    }
</style>
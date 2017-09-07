<!-- 一类飞行计划明细 -->
<template>
    <div class="plan">
        <div class="title clear">
            <p class="left" style="font-size:18px">
                <span>Ⅱ类空域报告 ，编号 </span>
                <strong>{{flyplan.number}}</strong>
            </p>
            <div class="right">
                <el-button @click="back">返回</el-button>
            </div>
        </div>

        <div class="plan-form">
            <el-form ref="flyplan" label-position="left" :model="flyplan"
                     label-width="120px">
                <div class="plan-form-title">
                    <div class="devider"></div>
                    <p class="text">基础信息</p>
                </div>
                <div class="plan-form-content">
                    <el-form-item label="申请个人/单位">
                        {{user.name==undefined ? user.companyName:user.name}}
                    </el-form-item>
                    <!--计划类型-->
                    <el-form-item label="计划类型" prop="planType" auto-complete="off">
                        {{flyplan.planType}}
                    </el-form-item>
                    <!--使用机型-->
                    <el-form-item label="使用机型" class="clear" prop="planes" auto-complete="off">
                        <span v-for="plane in flyplan.planes" style="margin-right:5px;">
                            {{plane.name}} : {{plane.number}}架
                        </span>
                    </el-form-item>
                    <el-form-item label="使用时间" prop="planTime">
                        {{flyplan.beginTime}} - {{flyplan.endTime}}
                    </el-form-item>
                    <el-form-item label="计划空域及高度">
                        <p>
                            {{flyplan.airInfo[0].provinceName}} {{flyplan.airInfo[0].cityName}} {{flyplan.airInfo[0].areaName}}</p>
                        <p>{{flyplan.airInfo[0].location}}</p>
                        <p>真高：<span>{{flyplan.airInfo[0].high}}</span>米（含）以下</p>
                    </el-form-item>
                    <el-form-item label="起降场">
                        {{flyplan.landingInfo[0].location}}
                    </el-form-item>
                </div>
                <div class="plan-form-title">
                    <div class="devider"></div>
                    <p class="text">操控员或负责人信息</p>
                </div>
                <div class="plan-form-content clear">
                    <el-form-item label="无人机操控人">
                        {{flyplan.contactInfo.controller}}
                    </el-form-item>
                    <el-form-item label="手机号码" style="width:100%;">
                        {{flyplan.contactInfo.controllerContact}}
                    </el-form-item>
                    <el-form-item label="证件号">
                        <el-select style="width:15%;" v-model="flyplan.contactInfo.idCardType" :disabled="true"
                        ></el-select>
                        <span>{{flyplan.contactInfo.idCardNo}}</span>
                    </el-form-item>
                    <el-form-item label="紧急联系人" style="width:60%;">
                        {{flyplan.contactInfo.emergencyContactName}}
                    </el-form-item>
                    <el-form-item label="手机号码" style="width:60%;">
                        {{flyplan.contactInfo.emergencyContactPhone}}
                    </el-form-item>
                </div>
            </el-form>
        </div>


    </div>
</template>

<script>
    import '../../style/plan.css'
    import Vue from 'vue'
    import urls from  '../../components/urls'

    export default{
        methods: {
            //返回上一级
            back(){
                window.location.href = '#/myplan';
            },
        },
        data(){
            return {
                //飞行计划
                flyplan: {
                    contactInfo: {},
                    landingInfo: [{}],
                    airInfo: [{}],
                    planes: []
                }
            }
        },
        computed: {},
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
                }.bind(this)
            })
        },
        mounted(){
        }
    }
</script>

<style lang="less" scoped="">

</style>
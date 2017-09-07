<template>
    <div>
        <div class="clear myplan_sel">
            <span class="create_title">创建飞行计划</span>
            <el-button @click="back" class="right">返回</el-button>
        </div>
        <div class="fly_create_type">
            <ul class="clear">
                <a href="#/createPlan0" class="left">
                    <li>
                        <p><strong>管制空域飞行计划</strong></p>
                        <p>适用于企业用户申请无人机作业飞行计划信息的填报，要求用户已取得管制空域临时使用许可。</p>
                    </li>
                </a>
                <a href="#/createPlan1" class="left">
                    <li>
                        <p><strong>I 类报告空域飞行计划</strong></p>
                        <p>适用于企业用户开展技能培训、产品试飞等无人机长期性、固定性飞行活动信息的备案。</p>
                    </li>
                </a>
                <a href="#/createPlan2" class="left">
                    <li>
                        <p><strong>II 类报告空域飞行计划</strong></p>
                        <p>适用于个人用户开展消费级无人机非经营性娱乐飞行活动信息的备案，要求无人机重量、尺寸必须符合规定。</p>
                    </li>
                </a>
            </ul>
        </div>
    </div>
</template>

<script>
    export default{
        methods:{
            back(){
                window.location.href = '#/myplan';
                
            },
            

        },
        data(){
            return{


            }
        },
        mounted(){

        }
    }
</script>

<style lang="less" scoped>
  
    .fly_create_type{
        margin:30px 0;
    }

    .fly_create_type a{
        cursor: pointer;
        width:33%;
        padding:0 10px;
        font-size:14px;
        color:#fff;
    }

    .fly_create_type li p:nth-child(1){
        margin-bottom:10px;
        font-size:16px;
        text-align: center;
    }

    .fly_create_type li{
        padding:10px 10px;
        min-height:140px;
        border-radius:3px;
    }

    .fly_create_type a:nth-child(1) li{
        background:#ff6363;
    }
    .fly_create_type a:nth-child(2) li{
        background:#336fd3;
    }
    .fly_create_type a:nth-child(3) li{
        background:#32aa6d;
    }
</style>
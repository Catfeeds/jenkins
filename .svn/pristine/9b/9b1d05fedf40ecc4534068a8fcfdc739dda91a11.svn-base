<template>
    <div>
        <div class="myplan_sel clear">
            <div class="left">
                <span style="margin-right:10px;">时间:</span>
                <el-select v-model="filter.period" placeholder="请选择" @change="search">
                    <el-option v-for="item in times" :key="item.value" :label="item.label"
                               :value="item.value"></el-option>
                </el-select>
            </div>
            <div class="left" style="margin-left:20px;">
                <span style="margin-right:10px;">计划类型:</span>
                <el-select v-model="filter.planType" placeholder="请选择" @change="search">
                    <el-option v-for="item in planTypes"  :label="item.label"
                               :value="item.label"></el-option>
                </el-select>
            </div>
            <div class="right">
                <el-button type="primary" @click="addarea">+&nbsp新增</el-button>
            </div>
        </div>

        <el-table :data="computedAirapplys" style="width: 100%; margin-top:15px;">
            <el-table-column prop="number" label="申请任务编号">
                <template scope="scope">
                    <a :href="'#/applyDetail/'+scope.row.number"
                       style="text-decoration: underline;color:#219 ">{{scope.row.number}}</a>
                </template>
            </el-table-column>
            <el-table-column prop="createTime" label="申请时间"></el-table-column>

            <el-table-column prop="airInfo" label="申请空域名称">

            </el-table-column>
            <el-table-column prop="time" label="任务时间">
                <template scope="scope">
                    <div>{{scope.row.time.beginTime}}</div>
                    <div>{{scope.row.time.endTime}}</div>
                </template>
            </el-table-column>
            <el-table-column label-width="50" label="状态">
                <template scope="scope">
                    <div v-if="scope.row.state==3" style="color:red">已撤销申请</div>
                    <div v-if="scope.row.state==2" style="color:red">未通过审核</div>
                    <div v-if="scope.row.state==1" style="color:#008000">通过审核</div>
                    <div v-if="scope.row.state==0" style="color:#3399ff">审核中</div>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template scope="scope">
                    <div v-if="scope.row.state==0">
                        <el-button @click="deleteApply(scope.row.number)">撤销申请</el-button>
                    </div>
                </template>
            </el-table-column>
        </el-table>
        <div class="center None">
            <el-pagination layout="prev, pager, next" :total="1000" :page-size="5"></el-pagination>
        </div>
    </div>
</template>

<script>
    import urls from '../../components/urls'
    export default{
        methods: {
            /**
             *  撤消申请
             */
            deleteApply(airApplyCode){
                this.$confirm('是否撤销编号'+ airApplyCode+'的空域申请?', '提示',  {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    var URL = urls.AIRPLAN + '/' + airApplyCode;
                    $.ajax({
                        type: 'delete',
                        url: URL,
                        dataType: 'json',
                        success: function (res, textStatus, jqXHR) {
                            this.search();
                            this.$message({
                                type: 'success',
                                message: '撤销成功'
                            });
                        }.bind(this)
                    });
                })

            },
            /**
             *  跳转至新增空域
             */
            addarea(){
                var user = this.$store.getters.getSession;
                if (user.authStatus != 3) {
                    this.$message.error('提示：您的帐号未通过认证，您暂时无法进行飞行报备和空域申请操作，请耐心等待');
                    return;
                }
                window.location.href = "#/airApply";
            },
            /**
             * 获取飞行计划
             * @param planType
             *     飞行计划类型，如果此值为'全部'，不加此参数
             * @param period
             *     查询时长
             */
            fetchAirApply(planType,period){
                var url = urls.GET_AIRPLAN + "?period=" + period;
                if(planType != '全部'){
                    url+= "&type="+ planType;
                }
                $.ajax({
                    url: url,
                    method:'get',
                    dataType: 'json',
                    success: function (res) {
                        this.airApply = res.data.map(function(value){
                            value.planes = JSON.parse(value.planes);
                            value.airInfo = JSON.parse(value.airInfo);
                            value.landingInfo = JSON.parse(value.landingInfo);
                            value.contactInfo = JSON.parse(value.contactInfo);
                            return value;
                        });
                    }.bind(this)
                })
            },
            //查询飞行计划
            search(){
                this.fetchAirApply(this.filter.planType,this.filter.period);
            }
        },
        data(){
            return {
                //筛选器
                filter: {
                    //当前选择的天数
                    period: 30,
                    //当前选择的飞行计划
                    planType: '全部',
                },
                times: [
                    {
                        label: '最近7天',
                        value: 7
                    }, {
                        label: '最近两周',
                        value: 14
                    },
                    {
                        label: '最近一个月',
                        value: 30
                    },
                    {
                        label: '最近半年',
                        value: 183
                    },
                ],
                airApply: []
            }
        },
        computed: {
            computedAirapplys: function () {
                var computedAirapplys = this.airApply.map(function (value) {
                    var time = {
                        beginTime: value.beginTime.substring(0, 10),
                        endTime: value.endTime.substring(0, 10)
                    }
                    return {
                        number: value.number,
                        time: time,
                        airInfo: value.airInfo.length > 0 ? value.airInfo[0].location : '',
                        createTime: value.createTime,
                        state: value.status
                    }
                })
                return computedAirapplys;
            },
            planTypes:function(){
                var planTypes = [];
                planTypes.push({
                    label: '全部'
                });
                this.$store.state.planTypes.airapply.forEach(function(value){
                    planTypes.push(value)
                });
                return planTypes;
            }
        },
        mounted(){
            this.search();
        }
    }
</script>

<style lang="less">
    .myplan_sel .el-input__icon + .el-input__inner {
        width: 150px;
    }

</style>
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
                    <el-option v-for="item in planTypes" :key="item.label" :label="item.label"
                               :value="item.label"></el-option>
                </el-select>
            </div>
            <div class="right">
                <el-button type="primary" @click="addPlan" width="70">+&nbsp新增</el-button>
            </div>
        </div>

        <el-table :data="computedFlyplans" style="width: 100%; margin-top:15px;">
            <el-table-column prop="number" label="计划号">
                <template scope="scope">
                    <a v-if="scope.row.type=='0'" :href="'#flyplan0/'+scope.row.number" style="text-decoration: underline;color:#219 ">{{scope.row.number}}</a>
                    <a v-if="scope.row.type=='1'" :href="'#flyplan1/'+scope.row.number" style="text-decoration: underline;color:#219 ">{{scope.row.number}}</a>
                    <a v-if="scope.row.type=='2'" :href="'#flyplan2/'+scope.row.number" style="text-decoration: underline;color:#219 ">{{scope.row.number}}</a>
                </template>
            </el-table-column>
            <el-table-column prop="type" label="空域类别">
                <template scope="scope">
                    <div v-if="scope.row.type=='0'">管制空域</div>
                    <div v-if="scope.row.type=='1'">Ⅰ类报告空域</div>
                    <div v-if="scope.row.type=='2'">Ⅱ类报告空域</div>
                </template>
            </el-table-column>
            <el-table-column prop="airInfo" label="飞行区域"></el-table-column>
            <el-table-column prop="time" label="飞行时间">
                <template scope="scope">
                    <div >{{scope.row.time.date}}</div>
                    <div >{{scope.row.time.hour}}</div>
                </template>
            </el-table-column>
            <el-table-column width="100" label="状态">
                <template scope="scope">
                    <div v-if="scope.row.status==0" style="color:black">审核中</div>
                    <div v-if="scope.row.status==1" style="color:black">未开始</div>
                    <div v-if="scope.row.status==2" style="color:red">未通过审核</div>
                    <div v-if="scope.row.status==3" style="color:#3399ff">执行中</div>
                    <div v-if="scope.row.status==4" style="color:#3399ff">已完成</div>
                    <div v-if="scope.row.status==5" style="color:red">超时</div>
                    <div v-if="scope.row.status==6" style="color:red">已撤销</div>
                    <div v-if="scope.row.status==7" style="color:red">已关闭</div>
                </template>
            </el-table-column>
            <el-table-column width="100"  label="操作" >
                <template scope="scope">
                    <i v-if="scope.row.status==1" class="fa-play fa action"  @click="start(scope.row.number)" title="开始"></i>
                    <i v-if="scope.row.status==1" class="fa-undo fa action"  @click="deletePlan(scope.row.number)" title="撤消申请"></i>
                    <i v-if="scope.row.status==3" class="fa-stop fa action"  @click="finish(scope.row.number)" title="结束"></i>
                    <i v-if="scope.row.status==0" class="fa-undo fa action"  @click="deletePlan(scope.row.number)" title="撤消申请"></i>
                    <!-- 已完成、超时、撤消可以另建模板提交 -->
                    <i v-if="scope.row.status==2 || scope.row.status==4 || scope.row.status==6 || scope.row.status==7" class="fa fa-pencil-square-o action"
                       title="以此作为模板提交" @click="createPlan(scope.row)"></i>
                </template>
            </el-table-column>
        </el-table>   
      
    </div>
</template>

<script>

    import $ from 'jquery';
    import urls from '../../components/urls'

    export default{
        data(){
            return {          	
                filter: {
                    //当前选择的天数
                    period: 30,
                    //当前选择的飞行计划
                    planType: '全部',
                },
                //飞行计划列表
                flyplans: [],
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
                ]

            }
        },
        computed: {
            planTypes: function(){
                var planTypes = this.$store.state.planTypes;
                var planTypesArray = planTypes.plan0.concat(planTypes.plan1).concat(planTypes.plan0);
                planTypesArray = planTypesArray.filter(function(type){
                     return type.label != '其他';
                 });
                planTypesArray.push({label:'全部'});
                return planTypesArray;
            },
            computedFlyplans: function () {
                var computedFlyplans = this.flyplans.map(function (value) {
                    //计划飞行时间
                    var time = {
                        date:value.beginTime.substring(0,10),
                        hour:value.beginTime.substring(11,value.endTime.length) + '-' + value.endTime.substring(11,value.endTime.length)
                    }

                    return {
                        number: value.number,
                        type: value.type,
                        airInfo: value.airInfo.length > 0 ? value.airInfo[0].location : '',
                        time: time,
                        status: value.status
                    }
                })
                return computedFlyplans;
            }
        },
        mounted(){
            this.search();
        },
        methods: {
            addPlan(){
                var user = this.$store.getters.getSession;
                if(user.authStatus!=3){
                    this.$message.error('提示：您的帐号未通过认证，您暂时无法进行飞行报备和空域申请操作，请耐心等待');
                    return;
                }
                window.location.href = '#/createPlan';
            },
            /**
             * 获取飞行计划
             * @param planType
             *     飞行计划类型，如果此值为'全部'，不加此参数
             * @param period
             *     查询时长
             */
            fetchFlyplan(planType,period,val){
                var url = urls.GET_FLYPLANS + "?period=" + period;
                if(planType != '全部'){
                    url+= "&type="+ planType;
                }
                $.ajax({
                    url: url,
                    method:'get',
                    dataType: 'json',
                    success: function (res) {    
                    	this.files = res.data.content;
                        this.totalElements = res.data.totalElements;                 
                        this.flyplans = res.data.map(function(flyplan){
                            flyplan.planes = JSON.parse(flyplan.planes);
                            flyplan.airInfo = JSON.parse(flyplan.airInfo);
                            flyplan.landingInfo = JSON.parse(flyplan.landingInfo);
                            flyplan.contactInfo = JSON.parse(flyplan.contactInfo);
                            return flyplan;
                        });
                    }.bind(this)
                })
            },
            //查询飞行计划
            search(){
                this.fetchFlyplan(this.filter.planType,this.filter.period);
            },
            //开始计划
            start(flyplanCode){
                var url = urls.API_PATH + "console/flyplan/" + flyplanCode + "/status/start";
                $.ajax({
                    url: url,
                    method:'put',
                    dataType: 'json',
                    success: function (res) {
                        this.search();
                        this.$message("编号为"+flyplanCode+"的飞行计划已开始")
                    }.bind(this)
                })
            },
            //结束计划
            finish(flyplanCode){
                var url = urls.API_PATH + "console/flyplan/"+ flyplanCode + "/status/stop";
                $.ajax({
                    url: url,
                    method:'put',
                    dataType: 'json',
                    success: function (res) {
                        this.search();
                        this.$message("编号为"+flyplanCode+"的飞行计划已结束")
                    }.bind(this)
                })
            },
            deletePlan(flyplanCode){
                this.$confirm('是否撤销编号'+ flyplanCode+'的飞行计划?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function(){
                    var url = urls.API_PATH + "console/flyplan/"+ flyplanCode;
                    $.ajax({
                        url: url,
                        method:'delete',
                        dataType: 'json',
                        success: function (res) {
                            this.search();
                           
                        }.bind(this)
                    })
                }.bind(this))
            },
            //以此页作为模板创建一个飞行计划
            createPlan(flyplan){
                if(flyplan.type == '0'){
                    window.location.href = '#tmpl/flyplan0/'+flyplan.number;
                }else if(flyplan.type == '1'){
                    window.location.href = '#tmpl/flyplan1/'+flyplan.number;
                }else if(flyplan.type == '2'){
                    window.location.href = '#tmpl/flyplan2/'+flyplan.number;
                }
            }
        }
    }
</script>

<style lang="less">
    .myplan_sel .el-input__icon + .el-input__inner {
        width: 150px;
    }
</style>

<style lang="less" scoped="">
    .action{
        font-size:18px;color:#0ba3ff;cursor:pointer;
    }

    .scope.row.number{
    	
    }
</style>
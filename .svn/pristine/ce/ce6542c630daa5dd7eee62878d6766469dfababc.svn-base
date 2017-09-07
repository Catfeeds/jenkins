<template>
    <div class="maxW">
        <el-row type="flex" class="row-bg" justify="center">
            <el-col :span="18">
                <div class="grid-content bg-purple-light">
                    <div >
                       <div class="introduction_title">{{detail.attribute.title}}</div>
                       <div class="introduction_author clear">
                            <span class="left">发布于:{{detail.attribute.createTime}}</span>
                            <span class="left">作者:{{detail.attribute.creator}}</span>
                            <span class="right">{{detail.attribute.checkNumber}}次浏览</span>
                       </div>
                       <div class="introduction_content" v-html="detail.content"></div>
                       <div style="text-align:center;margin-bottom:20px;display:none;">

                            <el-button type="primary" v-if="this.pre=='true'">上一篇</el-button>
                            <el-button type="primary" v-else-if="this.pre=='false'" :disabled="true">上一篇</el-button>
                            <el-button type="primary" v-if="this.next=='true'">下一篇</el-button>
                            <el-button type="primary" v-else-if="this.next=='false'" :disabled="true">下一篇</el-button>
                       </div>
                    </div>
                </div>
            </el-col>
           
        </el-row>
    </div>
</template>

<script>
import urls from '../../components/urls'
    export default{
        methods:{

        },
        data(){
            return{
                pre:'',
                next:'',
                del:'',
                createTime:'',
                detail:{
                        time:'2016-08-09',
                        author:'小锦阳-2009',
                        liulan:'100',
                        content:'<img src="images/06.jpg"><p>广州嘉恩航空技术服务有限公司(以下简称“广州嘉恩”)创立于2016年，是一家具有独立法人资格的航空类服务型企业，主要从事空域申请、空域使用方案优化、通航项目策划、无人机培训、无人机销售代理、航空测绘等业务。</p><p>广州嘉恩创办人来自空军自主择业干部，从事区域、分区级航空管制工作20年，精于飞行调配、飞行指挥和空域管理使用，熟悉通航任务申报审批流程和空域使用运行保障。创始团队成员90%来自于部队转业军人，具有令行禁止、踏实可靠、雷厉风行和简洁高效的优良军队传统作风，整个团队充满活力和朝气。</p><p>我们的目标是：遵照国家空域管理制度，推动建设便捷有效的计划申报和运行管理平台，促进通用航空空域使用正规有序，为通用航空提供更加正规化、专业化、精细化的服务，努力打造优质服务的桥梁和纽带作用，力争让“管理者放心，让用户省心！”</p><p>我们的宗旨是：全心全意为您服务！</p><p>我们的口号是：我们不是最优秀的，但我们是最用心的！</p><p>广州嘉恩目前已经成功在广州、深圳、佛山、东莞、中山和珠海等城市申请了无人机培训专用临时空域，与多家无人机企业和培训单位建立了战略合作关系，为其解决了无人机测试、培训飞行不合法的安全隐患，嬴得了管理部门、协会、客户的认可和好评。</p>'
               }
            }
        },
         
        mounted(){
            this.pre="false";
            this.next="true";
            sessionStorage.setItem("kjkey","1");
            var str = document.location.href;
            var index = str .lastIndexOf("\/");  
            str  = str .substring(index + 1, str .length);
            var UYL=urls.GET_PICDETAIL+str+"?type=1";
           
            var _this=this;
            $.ajax({
                type: 'Get',
                url: UYL,
                success:function(data){
                    
                    _this.detail=data.data;
                   
                    
                },
                
               
            });
        }
    }
</script>

<style lang="less">

.notice_check{
    margin-top:15px;
}
.notice_check span{
    color:#949494;
}
</style>
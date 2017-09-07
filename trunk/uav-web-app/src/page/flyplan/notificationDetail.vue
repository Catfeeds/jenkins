<!-- 通知公告详情 -->
<template>
    <div style="margin-top:20px;" class="maxW">
        <el-row type="flex" class="row-bg" justify="center">
            
                <div class="grid-content bg-purple-light" style="width:100%">
                    <div >
                       <div class="introduction_title">{{notification.attribute.title}}</div>
                       <div class="introduction_author clear">
                            <span class="left">发布于：{{notification.attribute.createTime}}</span>
                            <span class="left">作者：{{notification.attribute.creator}}</span>
                       </div>
                       <div class="introduction_content" v-html="notification.content"></div>

                    </div>
                </div>
        </el-row>
        <div style="text-align: center;margin-bottom:20px;"><el-button ><a href="#/notification" style="width:100%;display: block">返回</a></el-button></div>
    </div>
</template>

<script>
import urls from '../../components/urls'
    export default{
        methods:{

        },
        data(){
            return{
               notification:{
                   attribute:{}
               }
            }
        },
        created(){
            var notificationId = this.$route.params.notificationId;
            var url=urls.GET_ANNOUNCEMENT + "/" + notificationId;
            $.ajax({
                type: 'get',
                url: url,
                success:function(res){
                    this.notification=res.data;
                }.bind(this),
                dataType: 'json'
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
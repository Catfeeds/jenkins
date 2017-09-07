<template>
    <div style="margin-top:20px;" class="maxW">
        <el-row type="flex" class="row-bg" justify="center">
            
                <div class="grid-content bg-purple-light" style="width:100%">
                    <div >
                       <div class="introduction_title">{{detail.title}}</div>
                       <div class="introduction_author clear">
                            <span class="left">发布于:{{detail.createTime}}</span>
                            <span class="left">作者:{{detail.creator}}</span>
                            <span class="right">{{detail.checkNumber}}次浏览</span>
                       </div>
                       <div class="introduction_content" v-html="content"></div>
                       <div style="text-align:center;margin-bottom:20px;display:none;" >

                            <el-button type="primary" v-if="this.pre=='true'">上一篇</el-button>
                            <el-button type="primary" v-else-if="this.pre=='false'" :disabled="true">上一篇</el-button>
                            <el-button type="primary" v-if="this.next=='true'">下一篇</el-button>
                            <el-button type="primary" v-else-if="this.next=='false'" :disabled="true">下一篇</el-button>
                       </div>
                    </div>
                </div>
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
               createTime:'',
               detail:'',
               content:'',
            }
        },
        
        mounted(){
            this.pre="false";
            this.next="true";
            sessionStorage.setItem("kjkey","1");
            var str = document.location.href;
            var index = str .lastIndexOf("\/");  
            str  = str .substring(index + 1, str .length);
            var UYL=urls.GET_ANNOUNCEMENT+"/"+str;
            var _this=this;
            $.ajax({
                type: 'Get',
                url: UYL,
                
                success:function(data){
                    
                    _this.detail=data.data.attribute;
                    _this.content=data.data.content;
                     
                    
                    // _this.detail.id=data.id;
                    // _this.detail.content=data.content;
                    // _this.detail.title=data.attribute.title;
                    // _this.detail.time=data.attribute.createTime;
                    // _this.detail.author=data.attribute.creator;
                    // _this.detail.liulan=data.attribute.checkNumber;
                },
                
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
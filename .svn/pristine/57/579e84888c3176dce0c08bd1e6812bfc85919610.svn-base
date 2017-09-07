<template>
    <div>
        <div>
            <ul class="notifi">
                <li class="clear" v-for="item in fly_notification">
                    <div class="left noti_icon" ><i class="fa fa-bell fa-2x"></i></div>
                    <div class="left">
                        <div class="no_title">{{item.title}}</div>
                        <div class="no_content clear">
                            <div class="left">{{item.titleDesc}}</div>
                            <div class="right None" :class="item.state=='已读'?'blue':'red'">{{item.state}}</div>
                        </div>
                        <a class="underline" :href="'#/notification/'+item.id">[查看详情]</a>
                        <div style="margin-top:10px; color:#949494;">发布于:{{item.createTime}}</div>
                    </div>
                </li>
            </ul>
            <ul class="notifi">
                <li class="clear" v-for="item in message">
                    <div class="left noti_icon" ><i class="fa fa-twitch fa-2x"></i></div>
                    <div class="left">
                        <div class="no_title">{{item.title}}</div>
                        <div class="no_content clear">
                            <div class="left">{{item.content}}</div>
                            <div class="right None" :class="item.state=='已读'?'blue':'red'">{{item.state}}</div>
                        </div>
                        <div style="margin-top:10px; color:#949494;">发布于:{{item.createTime}}</div>
                    </div>
                </li>
            </ul>
            <div class="center" style="margin-top:30px;">
                <el-pagination layout="prev, pager, next" :total="totalElements" @current-change="handleCurrentChange" :page-size="5"></el-pagination>
            </div>
        </div>
        
    </div>
</template>

<script>

 import urls from '../../components/urls'
    export default{
        methods:{
           handleCurrentChange(val){
                var _this=this;
                $.ajax({
                    type: 'GET',
                    url: urls.GET_NOTICE+"?page=1&size=5",
                
                    success: function(data){
                       
                        _this.message=data.data;
                    } 
                    });
           }

        },
        data(){
            return{
                value:'全部',
               fly_notification:null,
               message:null,
                
                totalElements:1,
                
            }
        },
         mounted(){
        var _this=this;
          $.ajax({
            type: 'GET',
            url: urls.GET_ANNOUNCEMENTS,
           
            success: function(data){
                
                _this.fly_notification=data.data;
                
            },
            
            });


          $.ajax({
            type: 'GET',
            url: urls.GET_NOTICE+"?page=1&size=5",
           
            success: function(data){
               
                _this.message=data.data.content;
                _this.totalElements=data.data.totalElements;
            },
            
            });
        }
    }
</script>

<style lang="less">
.myplan_sel .el-input__icon+ .el-input__inner{
    width:150px;
}

.notifi li{
    padding:25px 15px 10px;
    color:black;
    border-bottom:1px dashed  #cecece;
}

.red{
    color:red;
}
.blue{
    color:#57b3de;
}

.notifi .no_title{
    margin-bottom:10px;
    font-size:16px;
    font-weight:bolder;
}
.notifi .noti_icon{
    margin-right:15px;
}
.notifi .no_content{
    width:100%;
}
.notifi .no_content div:nth-child(1){
    max-width:550px;
}
.notifi .no_content div:nth-child(2){
    margin-left:50px;
}
</style>
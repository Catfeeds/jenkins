<template>
    <div class="grid-content bg-purple-light">
        <div>
            <ul style="margin-top:20px;">
                <li v-for="(item,index) in New" class="notice" >
                    <!--<img width="100%" max-height="220px" :src="item.coverPic">-->
                    <div class="notice_title">{{item.title}}</div>
                    <div style="max-width:800px">{{item.titleDesc}} <a class="underline" :href="Ahref(item.id)">[查看详情]</a></div>
                    <div class="notice_check"><span class="left">{{item.createTime}}</span><span style="margin-right:20px;" class="right">查看次数：{{item.checkNumber}}次</span></div>
                </li>
            </ul>
            <el-pagination layout="prev, pager, next" :page-size="5" :total="totalElements" @current-change="pageChange"></el-pagination>
        </div>
    </div>
</template>

<script>
import urls from '../../components/urls'
    export default{
        methods:{
            Ahref(index){
               return "#/allNewDetail/"+index;
           },
           pageChange(val){
                var str = document.location.href;
                var index = str .lastIndexOf("\/");
                str  = str .substring(index + 1, str .length);
                var URL=urls.GET_PICMENU+str+"/content?page="+val+"&size=5";
                var _this=this;
                $.ajax({
                    type: 'Get',
                    url: URL,
                    success: function(data){
                        _this.totalElements=data.data.totalElements;
                    // _this.totalElements=data.totalElements
                    _this.New=data.data.content;
                    
                       
                    } ,
                    dataType: 'json'
                });
           }
        },
        data(){
            return{
               New:null,
               time:[],
                totalElements:null,
            }
        },
        watch: {
            '$route' (to, from) {
                var str = document.location.href;
                var index = str .lastIndexOf("\/");
                str  = str .substring(index + 1, str .length);
                var URL=urls.GET_PICMENU+str+"/content?page=1&size=5";
                var _this=this;
                $.ajax({
                    type: 'Get',
                    url: URL,
                    success: function(data){
                        
                        _this.totalElements=data.data.totalElements;
                    // _this.totalElements=data.totalElements
                    _this.New=data.data.content;
                    
                    } ,
                    dataType: 'json'
                });
            }
        },
        mounted(){
            
            var str = document.location.href;
            var index = str .lastIndexOf("\/");
            str  = str .substring(index + 1, str .length);
            var URL=urls.GET_PICMENU+str+"/content?page=1&size=5";
            var _this=this;
            $.ajax({
                type: 'Get',
                url: URL,
                
                success: function(data){
                    _this.totalElements=data.data.totalElements;
                    // _this.totalElements=data.totalElements
                    _this.New=data.data.content;
                    
                } ,
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
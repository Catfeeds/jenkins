<template>
    <div>
       
        <div class="law"></div>
    
       <ul style="max-width:1024px;margin:20px auto 0px;" >
            <li v-for="(item,index) in New" class="notice clear" >
                <div class="left" style="margin-right:20px;"></div>
                    <div class="left">
                    <div class="notice_title">{{item.title}}</div>
                    <div style="max-width:910px">{{item.titleDesc}} <a class="underline" :href="Aherf(item.id)">[查看详情]</a></div>
                    <div class="notice_check"><span class="left">{{item.createTime}}</span><span style="margin-right:20px;" class="right">查看次数：{{item.checkNumber}}次</span></div>
                </div>
            </li>
                           
        </ul>
         <el-pagination layout="prev, pager, next" :page-size="5" :total="totalElements" @current-change="pageChange"></el-pagination>
     </div>
</template>

<script>
import urls from '../../components/urls'
import '../../images/cut/1.jpg'
    export default{
        methods:{
            Aherf(index){
                return '#/detail/'+index;
            },
            pageChange(val){
               var _this=this;
               var ULL=urls.GET_PICMENU+"cate3/content?page="+val+"&size=5";
                $.ajax({
                    type: 'GET',
                    url: ULL,
                    success: function(data){
                        
                        _this.New=data.data.content;
                        
                       
                        _this.totalElements=data.data.totalElements;
                        
                    } ,
                    dataType: 'json'
                });
           }
        },
        data(){
            return{
                createTime:[],
                New:null,
                totalElements:null,     
            }
        },
         mounted(){
            sessionStorage.setItem("kjkey","3");
            var _this=this;
            $.ajax({
                type: 'GET',
                url: urls.GET_PICMENU+"cate3/content?page=1&size=5",
                
                success: function(data){
                   
                    _this.New=data.data.content;
                   
                    _this.totalElements=data.data.totalElements;
                    
                } ,
                dataType: 'json'
            });
        }
    }
</script>

<style lang="less" >

.notice_check{
    margin-top:15px;
}
.notice_check span{
    color:#949494;
}
</style>
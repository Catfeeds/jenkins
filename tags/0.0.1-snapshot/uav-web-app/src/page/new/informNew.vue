<template>
    <div>
        <div class="grid-content bg-purple-light">
            <div>
                <ul style="margin-top:20px;">
                    <a v-for="(item,index) in New" :href="Ahref(item.id)">
                        <li class="notice clear" >
                            <div class="left" style="margin-right:20px;"></div>
                                <div class="left" style="width:90%;">
                                    <div class="notice_title">{{item.title}}</div>
                                    <div style="max-width:650px">{{item.titleDesc}} <a class="underline" :href="Ahref(item.id)">[查看详情]</a></div>
                                    <div class="notice_check"><span class="left">{{item.createTime}}</span><span style="margin-right:20px;" class="right">查看次数：{{item.checkNumber}}次</span></div>
                                </div>
                        </li>
                    </a>         
                </ul>
                <!--<el-pagination layout="prev, pager, next" :page-size="5" :total="2" @current-change="pageChange"></el-pagination>-->
            </div>
        </div>
    </div>
</template>

<script>
import urls from '../../components/urls'
    export default{
        methods:{
           Ahref(index){
               return "#/newDetail/"+index;
           },
        //    pageChange(val){
        //        $.ajax({
        //         type: 'POST',
        //         url: url ,
        //         data: data ,
        //         success: success ,
        //         dataType: dataType
        //         });
        //    },
        },
        data(){
            return{
                New:null,
                time:[],
            }
        },
         mounted(){
            sessionStorage.setItem("detail","1");
            sessionStorage.setItem("kjkey","1");
           var _this=this;
           $.ajax({
                type: 'GET',
                url: urls.GET_ANNOUNCEMENTS,
                success:function(data){
                    _this.New=data.data;
                  
               
                },
                
                dataType: 'json'
         });
        }
    }
</script>

<style lang="less">

</style>
<template>
    <div>
        <div class="grid-content bg-purple-light" style="margin-top:20px;">
            <div >
                    <div class="introduction_title">{{serviceGuide.title}}</div>
                    <div class="introduction_author clear">
                        <span class="left">发布于:{{serviceGuide.createTime}}</span>
                        <span class="left">作者:{{serviceGuide.creator}}</span>
                    <span class="right">{{serviceGuide.checkNumber}}次浏览</span>
                </div>
                <div class="introduction_content" v-html="content"></div>
            </div>
        </div>
    </div>
</template>

<script>
import urls from '../../components/urls'
    export default{
        methods:{
           
        },
        data(){
            return{
                serviceGuide:'',
                content:null,
                createTime:''
            }
        },
        watch: {
            '$route' (to, from) {
                var str = document.location.href;
            var index = str .lastIndexOf("\/");  
            str  = str .substring(index + 1, str .length);
            var UL=urls.GET_PICDETAIL+str+"?type=2";
            var _this=this;
            $.ajax({
                type: 'GET',
                url: UL ,
                success: function(data){
                    
                    _this.serviceGuide=data.data.attribute;
                     
                    _this.content=data.data.content;
                } ,
                dataType: 'json'
            });
            }
        },
         mounted(){
             var str = document.location.href;
            var index = str .lastIndexOf("\/");  
            str  = str .substring(index + 1, str .length);
            var UL=urls.GET_PICDETAIL+str+"?type=2";
            var _this=this;
            $.ajax({
                type: 'GET',
                url: UL ,
                success: function(data){
                    
                   _this.serviceGuide=data.data.attribute;
                    
                    _this.content=data.data.content;
                } ,
                dataType: 'json'
            });
        },
        
    }
</script>

<style lang="less">

</style>
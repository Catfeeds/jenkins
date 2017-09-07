<template>
<div>
    <el-carousel>
         <el-carousel-item v-for="item in $store.state.banners">
            <a :href="item.imgLink"><img :src="item.imgUrl"></a>
        </el-carousel-item>
    </el-carousel>
    <div class="maxW" style="margin-top:20px;">
        <el-row type="flex" class="row-bg" justify="center">
            <el-col :span="6">
                <div class="grid-content bg-purple">
                    <p class="content_title">新闻中心</p>
                    <ul class="content_title_li">
                        <!--<a href="#/newNotice" class="light"><li >通知公告</li></a>
                        <a href="#/pictrueNew"><li style="border-top:none;">图片新闻</li></a>
                        <a href="#/business"><li class="content_title_li_none">行业资讯</li></a>
                        <a href="#/companyDynamic"><li>公司动态</li></a>-->
                        
                    </ul>
                    <el-menu  id="newList" :default-active="new_active" class="el-menu-demo" mode="horizontal" @select="handleSelect">
                        <a class="clear" href="#/informNew"><el-menu-item  index="a">通知公告</el-menu-item></a>
                        <a class="clear" v-for="(item,$index) in newList" :href="Ahref($index)" ><el-menu-item  :index="item.id">{{item.name}}</el-menu-item></a>
                        
                    </el-menu>
                </div>
            </el-col>
            




            <el-col :span="18" >
                <div id="viewer">
                    <router-view></router-view>
                </div>
            </el-col>      
        </el-row>
    </div>
</div>
</template>

<script>
import urls from '../../components/urls'
    export default{
        methods:{
            handleSelect(key,keyPath){
                 sessionStorage.setItem("new_active",key);
            },
            Ahref(index){
                return '#/allnew/'+this.newList[index].id;
            },
           
        },
        data(){
            return{
                newList:null,
                new_active:'a',
                
            }
        },
         mounted(){
          
           

            var _this=this;
            $.ajax({
                type: 'GET',
                url: urls.GET_MENUSNEW,
                dataType: 'json',
                success: function(data){
                    _this.newList=data.data;
                   
                 },
            });
            sessionStorage.setItem("detail","1");
            sessionStorage.setItem("kjkey","1");
            if(sessionStorage.getItem("new_active")){
               
                this.new_active=sessionStorage.getItem("new_active");
            }


        },
        
    }
</script>

<style lang="less">
#newList .el-menu-item{
    line-height:35px;
    padding-left:20px !important;
    width:240px;
    height:35px;
    
}
#newList li{
    border:1px solid #cccccc;
}
#newList .is-active{
    color:white;
    border:1px solid #20a0ff;
    background:#20a0ff;
}

#newList .el-menu-item:hover{
    color:white;
    border:1px solid #20a0ff !important;
    background:#20a0ff !important;
}

.notice_check{
    margin-top:15px;
}
.notice_check span{
    color:#949494;
}
</style>
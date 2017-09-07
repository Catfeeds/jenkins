<template>
<div>
    <el-carousel>
        <el-carousel-item v-for="item in $store.state.banners">
            <a :href="item.imgLink"><img :src="item.imgUrl"></a>
        </el-carousel-item>
    </el-carousel>
    <div class="maxW" style="margin-top:15px;">
        <el-row type="flex" class="row-bg" justify="center">
            <el-col :span="6">
                <div class="grid-content bg-purple" style="margin-top:20px;">
                    <p class="content_title">平台介绍</p>
                    
                    <el-menu  id="newList" :default-active="iN_active" class="el-menu-demo" mode="horizontal" @select="handleSelect">
                        
                        <a v-for="(item,$index) in newList" :href="Ahref($index)"><el-menu-item  :index="item.id">{{item.name}}</el-menu-item></a>
                        
                    </el-menu>
                </div>
            </el-col>


            <el-col :span="18">
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
                 sessionStorage.setItem("iN_active",key);
            },
            Ahref(index){
                
                return '#/allIntroduction/'+this.newList[index].id;
            }
            
        },
        data(){
            return{
                newList:null,
                iN_active:'cate7',
                
            }
        },
         mounted(){
             
            sessionStorage.setItem("detail","1");
            sessionStorage.setItem("kjkey",2);
            if(sessionStorage.getItem("iN_active")){
                this.iN_active=sessionStorage.getItem("iN_active");
            }
            var _this=this;
            $.ajax({
                type: 'GET',
                url: urls.GET_FUWUMENU,
                
                success: function(data){
                    _this.newList=data.data;
                   
                } ,
                dataType: 'json'
            });
            window.location.href="#/allIntroduction/cate7";
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
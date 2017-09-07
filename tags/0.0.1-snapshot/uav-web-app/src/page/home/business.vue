<template>
    <div class="maxW">
        <el-row type="flex" class="row-bg" justify="center">
            <el-col :span="3">
                <div class="grid-content bg-purple">
                    <p class="content_title">新闻中心</p>
                    <ul class="content_title_li">
                        <a href="#/newNotice" ><li >通知公告</li></a>
                        <a href="#/pictrueNew"><li style="border-top:none;">图片新闻</li></a>
                        <a href="#/business" class="light"><li class="content_title_li_none">行业资讯</li></a>
                        <a href="#/companyDynamic"><li>公司动态</li></a>
                    </ul>
                </div>
            </el-col>




            <el-col :span="18">
                <div class="grid-content bg-purple-light">
                    <div>
                        <ul style="margin-top:20px;">
                             <li v-for="item in New" class="notice" >
                                <img width="100%" max-height="220px" :src="item.img">
                                <div class="notice_title">{{item.title}}</div>
                                 <div style="max-width:800px">{{item.content}} <a class="underline" href="#/detail">[查看详情]</a></div>
                                <div class="notice_check"><span class="left">{{item.time}}</span><span style="margin-right:20px;" class="right">查看次数：{{item.num}}次</span></div>
                            </li>
                           
                        </ul>
                        <el-pagination layout="prev, pager, next" :total="2"> </el-pagination>
                    </div>
                </div>
            </el-col>
           
        </el-row>
    </div>
</template>

<script>
    export default{
        methods:{

        },
        data(){
            return{
                
                New:[
                    {
                        img:'../../images/add.png',
                        title:'深圳市要求无人机生产必须假装飞行控制芯片',
                        content:'近日，深圳市经贸信委发出通知，强调要加强无人驾驶航空器管理，确保民航飞行安全，保障无人机产业健康发展。 2017年以来，全国多地多次发生无人驾驶航空器在机场附近违法违规飞行、干扰民航航班正常飞行事件，严重威胁公共安全和航空安全，引发舆论和社会高度关注，造成了恶劣影响。对此，国家领导人多次作出重要批示，要求对...',
                        time:'2016/02/02',
                        num:'150'
                    }, {
                        img:'../../images/add.png',
                        title:'深圳市要求无人机生产必须假装飞行控制芯片',
                        content:'近日，深圳市经贸信委发出通知，强调要加强无人驾驶航空器管理，确保民航飞行安全，保障无人机产业健康发展。 2017年以来，全国多地多次发生无人驾驶航空器在机场附近违法违规飞行、干扰民航航班正常飞行事件，严重威胁公共安全和航空安全，引发舆论和社会高度关注，造成了恶劣影响。对此，国家领导人多次作出重要批示，要求对...',
                        time:'2016/02/02',
                        num:'150'
                    }
                    ]
            }
        },
         mounted(){
            sessionStorage.setItem("detail","3");
            sessionStorage.setItem("kjkey","1");
           
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
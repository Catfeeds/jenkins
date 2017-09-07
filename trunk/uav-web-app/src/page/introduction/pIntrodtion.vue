<template>
    <div>
        <div class="grid-content bg-purple-light">
            <div >
                    <div class="introduction_title">平台介绍</div>
                    <div class="introduction_author clear">
                        <span class="left">发布于:{{fingerpost.time}}</span>
                        <span class="left">作者:{{fingerpost.author}}</span>
                    <span class="right">{{fingerpost.liulan}}次浏览</span>
                </div>
                <div class="introduction_content" v-html="fingerpost.content"></div>
            </div>
        </div>
    </div>
</template>

<script>
    export default{
        methods:{
           
        },
        data(){
            return{
                 fingerpost:{
                        time:'2016-08-09',
                        author:'小锦阳-2009',
                        liulan:'100',
                        content:'<p>一、用户注册</p><p>用户注册时，请注意以下事项：</p><p>1）用户名可使用汉字、字母、符号、数字或多种组合的方式，使用6-12位字符。</p><p>2）密码可使用汉字、字母、符号、数字或多种组合的方式，使用6-30位字符。</p> <p>3）个人用户注册时，请填写正确的身份信息。</p><p>4）企业用户注册时，请填写正确的公司名称。</p><p>5）其它标“*”的内容务必填写。</p><p>提示：用户注册成功后可登录“会员中心”，正常浏览“计划申报、空域申请、合同下载、通知公告“4个模块的内容。如需进行“计划申报”或“空域申请”填报操作，请联系服务平台管理员，授权后方可使用，联系电话：020-82258167。</p><p>二 、计划申报</p><p>该模块主要分为“有人机飞行计划申请”和“无人机飞行计划申请”两大类，其下又分为“飞行训练”、“航空作业”和“调机转场”等计划类型，“调机转场”为“有人机飞行计划申请”专有类别。用户请根据计划选择不同航空器所对应的计划类型填报。</p><p>提醒：计划申报应当在预计飞行时间的前一天15时前完成填报，否则计划申报平台将自动关闭无法填报。</p><p>下面通过无人机飞行训练，简单绍介填报过程中的注意事项。</p><p>1）计划类型，该栏可通过下拉菜单进行选择。</p><p>2）机型、架数，该栏填报训练使用的机型和对应数量，当有多种机型时，可点击后面的按钮增加填写栏目。</p><p>3）使用空域</p><p>该项根据使用空域形状有以下3种：圆形、多边形、以上两种均有。</p><p>A、当空域为圆形时，请按下图完成填报，并点击前面填充成。填报说明：在“位置描述”栏填写中心点位置名称，如：广州体育中心，同时按格式填写准确的坐标信息。如申请的计划有多个圆形空域时，可点击下图左下角的按钮增加填报数量。</p><img src="images/06.jpg">'
            }
        }},
         mounted(){
            
            
        },
    }
</script>

<style lang="less">

</style>
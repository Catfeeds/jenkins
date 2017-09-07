<template>
    <div>
        <div class="zhuce">
            <div class="maxW" style=" -webkit-transform-style:flat;" >
                <ul class=" clear  register">
                    <a href="#/register/personage">
                        <li class="left  register_nav">个人注册</li>
                    </a>
                    <a>
                        <li class="left ">企业注册</li>
                    </a>
                </ul>
            </div>
        </div>
        <div class="clear maxW">
            <el-form class="reg" :model="company" ref="company" :rules="rules" label-width="120px"
                     label-position="left">

                <p class="reg_title">基本信息*</p>

                <el-form-item label="用户名" placeholder="请输入登录名" prop="username" style="width:40%;">
                    <el-input v-model="company.username" @blur="checkName"></el-input>
                </el-form-item>

                <el-form-item label="密码" placeholder="请输入密码" prop="pws" auto-complete="off" style="width:40%;">
                    <el-input type="password" v-model="company.pws"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" placeholder="请再次输入密码" prop="pwsAgin" auto-complete="off" style="width:40%;">
                    <el-input type="password" v-model="company.pwsAgin"></el-input>
                </el-form-item>
                <el-form-item label="单位名称" placeholder="请输入单位名称" prop="name" style="width:40%;">
                    <el-input v-model="company.name" @blur="checkCompanyName"></el-input>
                </el-form-item>
                <el-form-item label="社会统一信用代码" placeholder="请输入执行编号" prop="licenseId" style="width:40%;">
                    <el-input v-model="company.licenseId" @blur="checkLicenseId"></el-input>
                </el-form-item>

                <el-form-item label="营业执照" prop="licenseImageUrl">
                    <el-upload class="avatar-uploader" :action="fileUpload" :before-upload="beforeFileUpload" :multiple="false"
                               :on-success="handleFileUploadSuccess" :on-error="handleFileUploadFail" :show-file-list="false">
                        <img v-if="company.licenseImageUrl" :src="company.licenseImageUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-form-item>
                <el-form-item label="单位地址" prop="detailAdress" style="width:44%;">
                    <el-select style="width:100px;" v-model="company.sheng" @change="gg1" placeholder="请选择">
                        <el-option v-for="Sheng in place.sheng" :label="Sheng.name" :value="Sheng"></el-option>
                    </el-select>
                    <el-select style="width:100px;" v-model="company.shi" @change="gg2" placeholder="请选择">
                        <el-option v-for="Shi in place.shi" :label="Shi.name" :value="Shi"></el-option>
                    </el-select>
                    <el-select style="width:100px;" v-model="company.qu" placeholder="请选择">
                        <el-option v-for="qu in place.qu" :label="qu.name" :value="qu.name"></el-option>
                    </el-select>
                    <el-input style="margin-top:15px;" type="textarea" :rows="7" v-model="company.detailAdress"
                              placeholder="详细地址"></el-input>
                </el-form-item>


                <p class="reg_title">主要联系人</p>
                <el-form-item label="联系人姓名" prop="reMan" style="width:40%;">
                    <el-input v-model="company.reMan"></el-input>
                </el-form-item>
                <el-form-item label="手机号码" class="clear" prop="tel" style="width:40%;">
                    <el-input class="left NNN" v-model="company.tel" placeholder="验证短信将会发送到此手机上"
                              @blur="checkTel"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email" style="width:40%;">
                    <el-input v-model="company.email"></el-input>
                </el-form-item>
                <el-form-item label="公司法人" style="width:40%;">
                    <el-input v-model="company.Man"></el-input>
                </el-form-item>

                <p class="reg_title">紧急联系人</p>
                <el-form-item label="姓名" prop="urName" style="width:40%;">
                    <el-input v-model="company.urName"></el-input>
                </el-form-item>
                <el-form-item label="手机号码" prop="urNum" style="width:40%;">
                    <el-input v-model="company.urNum"></el-input>
                </el-form-item>
                <!--<el-radio class="radio" v-model="radio" label="1"><a @click="xieyi">用户注册协议</a></el-radio>-->
                <div style="text-align:center;margin-bottom:20px;">
                    <el-button type="primary" @click="check('company')">提交审核</el-button>
                </div>
            </el-form>
        </div>
        <el-dialog title="手机认证" :visible.sync="dialogFormVisible" :before-close="phoneWindowClose">
            <el-form class="center">
                <p class="center">我们已将验证码发送至你{{this.company.tel}}的手机上</p>
                <el-input style="width:50%;margin-top:15px;" v-model="companyPhoneCheck"></el-input>
            </el-form>
            <div slot="footer" class="dialog-footer center">
                <el-button type="primary" @click="companycheck()">提交验证</el-button>
            </div>
        </el-dialog>
        <el-dialog :visible.sync="dialogVisible" size="large" :before-close="handleClose">
            <div>
                <h2 class="center">用户注册协议</h2>
                <p>请您仔细阅读本用户注册协议内容，只有接受本协议才能继续进行注册。</p>
                <p>在以下条款中，“用户”是指在广州管制分区无人机管理服务平台（以下简称“e飞服务”平台）注册成为会员的个人或者单位。</p>
                <p>用户同意此在线注册协议条款之效力如同用户亲自签字、盖章的书面条款一样，对用户具有法律约束力。本注册协议条款在您注册成为“e飞服务”平台正式用户后生效，对用户和平台具有同等的法津效力。</p>
                <h4>第一条  平台介绍及服务内容</h4>
                <p>
                    为引导无人驾驶航空器产业良性健康发展，规范无人驾驶航空器飞行活动，保证军民航飞行安全和国家、军队重要目标安全。在认真落实国家空管委《无人机驾驶航空器专项整治方案》的指示精神的基础上，根据《广州飞行管制区无人驾驶航空器飞行管制试行办法》要求，采取航空管制部门监管指导，社会和市场承接主体服务的创新模式。由广州嘉恩航空技术服务有限公司与华风海洋联合研制了“广州管制分区无人机管理服务平台”。</p>
                <p>
                    本平台服务的具体内容由“e飞服务”平台根据《中华人民共和国飞行基本规则》、《通用航空飞行管制工作条例》、《无人机飞行航空管制规定》、《民用无人驾驶航空器系统空中交通管理办法》等相关法律法规提供。具体内容包括无人机空域使用申请、飞行计划备案、飞行动态馈报、飞行历史查寻、资料查录及下载、行业新闻咨询等服务，详细服务参见网站平台提供的平台服务指南和服务介绍。</p>
                <h4>第二条  用户身份保证</h4>
                <p>
                    2.1 用户承诺并保证自己是具有完全民事行为能力和完全民事权利能力的自然人、法人、实体和组织。用户在此保证所填写的用户信息是真实、准确、完整、及时的，并且没有任何引人误解或者虚假的陈述，且保证用户注册时填写的联系方式和通信地址正确有效。</p>
                <p>2.2 用户应根据“e飞服务”平台要求及时提供相应的身份证明等资料，否则平台有权拒绝向该用户提供相关服务。</p>
                <p>2.3 用户承诺将及时更新其用户信息以维持该等信息的有效性。</p>
                <p>2.4 如果用户提供的资料或信息包含有不正确、不真实的信息，“e飞服务”平台保留取消用户会员资格并随时结束为该用户提供服务的权利。</p>
                <p>
                    2.5 以代理人身份代理其他自然人或者单位进行注册用户时，必须向“e飞服务”平台提供代理人和被代理人的详细资料和信息及授权书面文件，未向本平台提供上述资料信息及文件的，本平台将视注册者为真正用户。</p>
                <h4>第三条  信息通知</h4>
                <p>用户充分理解“e飞服务”平台通过电子邮件或移动通信的方式与注册用户保持联络及沟通，其内容包括但不限于用户验证信息、飞行服务信息、平台通知公告等在内的相关业务联络信息。</p>
                <h4>第四条  用户的帐号、密码和安全性</h4>
                <p>4.1 一个证照（企业执照或身份证）只能申请唯一账号。</p>
                <p>4.2 用户注册成功后将得到一个用户名和密码，用户凭用户名和密码享受“e飞服务”平台提供的服务项目，并承担以用户帐号身份开展活动的法律责任。</p>
                <p>4.3 用户对自己的帐号和密码安全负责，且有权根据“e飞服务”平台规定的程序修改密码。如因用户未能保管好自己的帐号和密码，造成的后果由用户自己承担。</p>
                <p>4.4 未经“e飞服务”平台书面同意，用户名和密码不得擅自转让或者授权他人使用，否则用户应承担由此造成的一切后果。</p>
                <p>4.5 用户若发现任何非法使用用户帐号或存在安全漏洞的情况，请立即通告“e飞服务”平台运营商，以确保对用户账号采取有效的防范措施。</p>

                <h4>第五条  服务条款的修改和服务体系修订</h4>
                <p>
                    本平台有权在必要时修改服务条款，服务条款一旦发生变动，将会在重要页面上提示修改内容。如果不同意所改动的内容，用户可以主动取消获得的网络服务。如果用户继续享用本平台网络服务，则视为接受服务条款的变动。</p>

                <h4>第六条  平台升级与服务中断</h4>
                <p>
                    为提升平台服务质量，系统将不定时地进行升级维护，为保证用户飞行备案等业务的正常办理，“e飞服务”平台会提前通过网站发布升级公告和应对措施。因用户个人原因未收到服务中断公告而造成损失和责任的，其损失和责任由用户个人承担。因网络故障等不可抗因素造成的服务中断，按照本注册协议第十一条相关规定执行，同时请用户主动联系平台运营商及时解决。</p>
                <h4>第七条  用户的权利和义务</h4>
                <p>7.1 用户有权利拥有自己在“e飞服务”平台的用户名和密码，并有权利使用自己的用户名和密码随时登录本平台网站的用户专区。</p>
                <p>7.2 用户有权利使用“e飞服务”平台提供的无人机计划申报备案和动态馈报等功能，并有权利在接受本平台提供的服务时获得平台技术支持、咨询等服务，服务内容详见平台介绍和服务指南。</p>
                <p>7.3 用户在支付相应费用的前提下，有权向“e飞服务”平台申请提供的空域评估等特色服务，收费标准和特色服务内容参见平台公布相关消息。</p>
                <p>7.4 “e飞服务”平台仅提供用户空域使用申请、飞行计划备案、飞行动态馈报、特色服务等线上项目，用户在线下与飞行活动相关的场地使用、组织实施、安全保障等事宜均由用户负责，与“e飞服务”平台无关。</p>
                <p>7.5 用户不得利用技术或其他手段破坏及扰乱“e飞服务”平台网站以及本平台其他用户的正常使用。</p>
                <p>7.6 用户应尊重“e飞服务”平台及其他第三方的知识产权和其他合法权利，平台运营商保留用户侵犯本平台知识产权时终止向该用户提供服务的权利。</p>
                <p>
                    7.7 对由于用户在“e飞服务”平台注册时提供的联络方式有误以及用户用于接受本平台邮件的电子邮箱安全性、稳定性不佳而导致的一切后果，用户应自行承担责任，包括但不限于因用户未能及时收到本平台的相关通知而导致的后果和损失。</p>
                <p>7.8 用户承诺其使用“e飞服务”平台时遵从国家、地方法律法规、行业惯例和社会公共道德，不利用本平台提供的服务发布、传播如下信息和内容：</p>
                <p>1）违反国家法律法规政策的任何内容（信息）；</p>
                <p>2）违反国家规定的政治宣传和/或新闻信息；</p>
                <p>3）涉及国家秘密和/或安全的信息；</p>
                <p>4）封建迷信和/或淫秽、色情、下流的信息或教唆犯罪的信息；</p>
                <p>5）博彩有奖、赌博游戏；违反国家民族和宗教政策的信息；</p>
                <p>6）防碍互联网运行安全的信息；</p>
                <p>7）侵害他人合法权益的信息和/或其他有损于社会秩序、社会治安、公共道德的信息或内容。</p>
                <p>7.9 用户承诺不为他人发布不符合国家规定和/或本服务条款约定的信息内容提供任何便利，包括但不限于设置URL、BANNER链接等。</p>
                <h4>第八条  本平台的权利和义务</h4>
                <p>8.1 “e飞服务”平台根据用户选择的服务以及交纳款项情况向用户提供约定的技术和信息服务。</p>
                <p>8.2 “e飞服务”平台承诺对用户资料采取对外保密措施，不向第三方披露用户资料，不授权第三方使用用户资料，下列情况除外：</p>
                <p>1） 依据本协议条款或者用户与本平台之间其他服务协议、合同、在线条款等规定可以提供；</p>
                <p>2）依据法律法规的规定应当提供；</p>

                <p>3）行政、司法等有关执法部门按法律法规要求本平台提供；</p>
                <p>4）用户同意本平台向第三方提供；</p>
                <p>5）本平台解决举报事件、提起诉讼而向司法部门提供；</p>
                <p>6）本平台为防止严重违法行为或涉嫌犯罪行为发生而采取必要合理行动时所必须提供的；</p>
                <p>7） 本平台为向用户提供产品、服务、信息而向第三方提供，包括本平台通过第三方的技术及服务向用户提供产品、服务、信息的情况。</p>
                <p>8.3 “e飞服务”平台有权在不泄露用户信息的前提下使用用户资料。</p>
                <p>8.4 “e飞服务”平台有权对用户信息进行审查，并决定是否接受用户成为本平台会员或是否为用户提供服务。</p>
                <p>8.5 “e飞服务”平台保留在用户违反国家、地方法律法规或违反本在线注册协议条款的情况下终止服务并注销用户帐号的权利。</p>
                <h4>第九条  服务的终止</h4>
                <p>9.1 用户有权随时注销“e飞服务”平台账户或终止在本平台申请的一项或多项服务，但已交纳款项不得要求退还。</p>
                <p>9.2 “e飞服务”平台有权根据实际情况决定取消为用户提供的一项或多项服务，但应退还用户为该服务所交纳款项的剩余部分，除此之外本平台不承担任何责任，因不可抗素因造成服务中断终目的不受此条款约束。</p>
                <p>9.3 用户申请成为“e飞服务”平台用户后应当遵守平台使用规定，不得将本平台提供的服务咨询用于非法活动，否则本平台将取消用户的资格，并保留向用户追究法律责和损失索赔的权利。</p>
                <h4>第十条  解释权及争议解决</h4>
                <p>10.1用户在享受“e飞服务”平台提供的服务过程中发生争议时，用户与平台运营商双方通过友好协商的方式解决。如果协商未成，双方同意向本平台主要经营地的人民法院起诉。</p>
                <p>10.2 本注册协议的解释权归“e飞服务”平台所有，如果其中有任何条款与国家的有关法律相抵触，则相关条款完全按国家法律规定重新解释，其他条款则仍然保持法律效力。</p>
                <h4>第十一条  不可抗力</h4>
                <p>11.1 因不可抗力或者其他意外事件，使得本协议履行不可能、不必要或者无意义的，遭受不可抗力、意外事件的一方不承担责任。</p>
                <p>11.2 不可抗力、意外事件是指不能预见、不能克服并不能避免且对一方或双方当事人造成重大影响的客观事件，包括但不限于自然灾害如洪水、地震、瘟疫流行和风暴等以及社会事件如战争、动乱、政府行为等。</p>
                <p>11.3 用户同意鉴于互联网的特殊性，黑客攻击、互联网连通中断或者系统故障等属于不可抗力，由此给用户或者第三方造成的损失不应由“e飞服务”平台承担。</p>
                <p>用户在此再次保证已经完全阅读并理解了上述会员注册条款并自愿正式进入“e飞服务”平台在线注册程序，接受上述所有条款的约束。</p>

            </div>


            <div slot="footer" class="dialog-footer clear center">
                <el-button type="primary" style="margin:0 20px;" class="right" @click="refuse">不同意</el-button>
                <el-button type="primary" :class="tongyi?'None':'Block'" class="right">{{timeback}}</el-button>
                <el-button type="primary" :class="tongyi?'Block':'None'" class="right" @click="agree">同意</el-button>

            </div>
        </el-dialog>
    </div>
</template>

<script>
    import urls from '../../components/urls'
    import checkCompanyId from '../../components/valid-company'
    import '../../images/cut/2.jpg'

    export default{
        methods: {
            checkLicenseId(){
                if (this.company.licenseId != "") {
                    var _this = this;
                    $.ajax({
                        type: 'POST',
                        url: urls.CHECK_USERNAME,
                        data: {
                            data: this.company.licenseId,
                            type: 5
                        },
                        success: function () {

                        },
                        error: function () {
                            _this.company.licenseId = '';
                        },
                        dataType: 'json'
                    });
                }
            },
            checkCompanyName(){
                if (this.company.name != "") {
                    var _this = this;
                    $.ajax({
                        type: 'POST',
                        url: urls.CHECK_USERNAME,
                        data: {
                            data: this.company.name,
                            type: 4
                        },
                        success: function () {

                        },
                        error: function () {
                            _this.company.name = '';
                        },
                        dataType: 'json'
                    });
                }
            },
            checkName(){
                if (this.company.username != "" && this.company.username.length > 1 && this.company.username.length < 11) {
                    var _this = this;
                    $.ajax({
                        type: 'POST',
                        url: urls.CHECK_USERNAME,
                        data: {
                            data: this.company.username,
                            type: 2
                        },
                        success: function () {

                        },
                        error: function () {
                            _this.company.username = '';
                        },
                        dataType: 'json'
                    });
                }

            },

            //检测手机号是否已注册
            checkTel(){
                if (this.company.tel != "" && this.company.tel.length == 11) {
                    var _this = this;
                    $.ajax({
                        type: 'POST',
                        url: urls.CHECK_USERNAME,
                        data: {
                            data: this.company.tel,
                            type: 1
                        },
                        success: function () {

                        },
                        error: function () {
                            _this.company.tel = '';
                        },
                        dataType: 'json'
                    });
                }

            },

            //选择了省之后触发
            gg1(value){
                this.company.city = "",
                    this.company.qu = "",
                    this.company.detailAdress = "",
                    this.company.province = value.name;

                var _this = this;
                var URL = urls.GET_PROVINCE + "?id=" + value.id;
                $.ajax({
                    type: 'GEt',
                    url: URL,
                    success: function (data) {
                        _this.place.shi = data.data;
                    },
                    dataType: 'json'
                });

            },
            //选择市之后触发
            gg2(value){
                this.company.detailAdress = ""
                this.company.qu = ""
                this.company.city = value.name;
                var _this = this;
                var URL = urls.GET_PROVINCE + "?id=" + value.id;
                $.ajax({
                    type: 'get',
                    url: URL,
                    success: function (data) {
                        _this.place.qu = data.data;
                    },
                    dataType: 'json'
                });
            },

            phoneWindowClose(done){
                this.$confirm('确认关闭？关闭后将等待一分钟才可以重新发送短信!')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {
                    });
            },

            //关闭协议
            handleClose(done) {
                this.$confirm('确认关闭？关闭将重新计时!')
                    .then(_ => {
                        done();
                    })
                    .catch(_ => {
                    });
            },
            //同意协议
            agree(){
                var _this = this;
                var URL = urls.GET_MESSAGW + _this.company.tel;
                $.ajax({
                    type: 'GET',
                    url: URL,
                    success: function (data, textStatus, jqXHR) {
                        _this.dialogVisible = false;
                        _this.dialogFormVisible = true;

                    },

                });


            },

            //拒绝协议
            refuse(){

                this.dialogVisible = false;

            },

            //检测信息是否完整，并且打开协议
            check(company){
                clearInterval(timer);
                var timer = null;
                var _this = this;
                this.$refs[company].validate((valid) => {
                    if (valid) {
                        this.dialogVisible = true;
                        timer = setInterval(time, 1000);
                        var a = 10;

                        function time() {
                            a--;
                            _this.timeback = a;

                            if (a <= 0) {
                                _this.tongyi = true;
                                clearInterval(timer);
                            }

                        }
                    }
                });
            },

            //提交注册表单
            companycheck(){

                if (this.companyPhoneCheck == "") {
                    this.$message({
                        message: '验证码不能为空',
                        type: 'warning'
                    });
                    return
                }
                var _this = this;
                $.ajax({
                    type: 'POST',
                    url: urls.GET_MESSAGWCHECK,
                    data: {
                        code: _this.companyPhoneCheck
                    },
                    success: function (data, textStatus, jqXHR) {


                        var ad = _this.company.province + " " + _this.company.city + " " + _this.company.qu + " " + _this.company.detailAdress;

                        $.ajax({
                            type: 'POST',
                            url: urls.GET_REGISTERCOM,
                            data: {
                                userName: _this.company.username,
                                password: _this.company.pws,
                                companyName: _this.company.name,
                                licenseNumber: _this.company.licenseId,
                                licensePic: _this.company.licensePhotoUrl,
                                companyAddress: ad,
                                email: _this.company.email,
                                contactName: _this.company.reMan,
                                contactPhone: _this.company.tel,
                                companyLegal: _this.company.Man,
                                emergencyContactName: _this.company.urName,
                                emergencyContactPhone: _this.company.urNum
                            },
                            success: function (data, textStatus, jqXHR) {

                                _this.$message({
                                    message: '注册成功',
                                    type: 'success'
                                });
                                this.dialogFormVisible = false;
                                window.location.href = "#/login";
                            }

                        });
                    }


                });
            },
            /**
             * 处理文档上传成功
             */
            handleFileUploadSuccess(res, file, fileList){
                this.company.licenseImageUrl = URL.createObjectURL(file.raw);
                this.company.licensePhotoUrl = res.data[0].fullUrl;
            },
            /**
             * 处理文档上传失败
             */
            handleFileUploadFail(err, file, fileList){
                this.$message.error('附件上传失败，请重试');
            },
            /**
             * 上传文件前对文件进行校检
             */
            beforeFileUpload(file) {
                const isJPG = file.type === 'image/jpeg' || 'image/jpg' || 'image/png';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG) {
                    this.$message.error('上传头像图片只能是 JPG/PNG 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M;
            }
        },
        data(){
            var urName = (rule, value, callback) => {
                if (value == this.company.reMan) {
                    callback(new Error('紧急联系人姓名不能与主要联系人姓名相同'))
                } else {
                    callback();
                }
            };

            var urNum = (rule, value, callback) => {
                if (value == this.company.tel) {
                    callback(new Error('紧急联系人号码不能与主要联系人号码相同'))
                } else {
                    callback();
                }
            };

            var validatePass = (rule, value, callback) => {
                if (value.length < 6) {
                    callback(new Error('密码长度不能小于6'))
                } else {
                    if (this.company.pws !== '') {
                        this.$refs.company.validateField('pwsAgin');
                    }
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value !== this.company.pws) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            var validatePass4 = (rule, value, callback) => {
                var res = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
                if (value != "") {
                    if (res.test(value)) {
                        callback();
                    } else {
                        callback(new Error('请邮箱格式错误'));
                    }
                } else {
                    callback();
                }
            };
            var validateName=(rule, value, callback)=>{          
            	var res= /^[a-zA-Z0-9_]{5,16}$/;
            	 if (value != "") {
                    if (res.test(value)) {
                        callback();
                    } else {
                        callback(new Error(rule.message));
                    }
                } else {
                    callback();
                }
            };
            var licenseIdValidator = (rule, value, callback) => {
                if (!checkCompanyId(value)) {
                    callback(new Error(rule.message));
                    return;
                }
                callback();
            };
            return {
                timeback: 10,
                dialogVisible: false,
                tongyi: false,
                companyPhoneCheck: '',
                dialogFormVisible: false,
                fileUpload: '1',
                sheng: '',
                shi: '',
                city: '',
                rules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min:5, message: '长度不能小于 5 个字符', trigger: 'blur'},
                        {validator: validateName, message: '请填写正确用户名，不能含有中文，不能输入空格', trigger: 'blur'}
                    ],
                    tel: [
                        {required: true, message: '请输入电话号码', trigger: 'blur'},
                        {min: 11, max: 11, message: '电话号码长度为11位', trigger: 'blur'}
                    ],
                    reMan: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 2, message: '长度不能小于 2 个字符', trigger: 'blur'}
                    ],
                    email: [
                        {required: true, message: '请再次输入邮箱', trigger: 'blur'},
                        {validator: validatePass4, trigger: 'blur'}
                    ],
                    pws: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {validator: validatePass, trigger: 'blur'}
                    ],
                    pwsAgin: [
                        {required: true, message: '请再次输入密码', trigger: 'blur'},
                        {validator: validatePass2, trigger: 'blur'}
                    ],
                    name: [{required: true, message: '请输入单位名称', trigger: 'blur'}],
                    province: [{required: true, message: '请选择省份', trigger: 'blur'}],
                    detailAdress: [{required: true, message: '请输入详细地址', trigger: 'blur'}],
                    city: [{required: true, message: '请选择城市', trigger: 'blur'}],
                    urName: [{required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 2, message: '长度不能小于 2 个字符', trigger: 'blur'},
                        {validator: urName, trigger: 'blur'}],

                    urNum: [{required: true, message: '请输入电话号码', trigger: 'blur'},
                        {min: 11, max: 11, message: '电话号码长度为11位', trigger: 'blur'},
                        {validator: urNum, trigger: 'blur'}],

                    licenseId: [
                        {required: true,validator: licenseIdValidator,message: '请输入有效的社会统一信用代码', trigger: 'blur'}
                    ],
                    licenseImageUrl: [{required: true, message: '请上传图片', trigger: 'blur'}],


                },
                place: {
                    sheng: '',
                    shi: '',
                    qu: ''
                },
                company: {
                    username: '',
                    pws: '',
                    pwsAgin: '',
                    name: '',
                    licenseId: '',
                    licenseImageUrl: '',
                    licensePhotoUrl: '',
                    province: '',
                    sheng: '',
                    shi: '',
                    qu: '',
                    city: '',
                    detailAdress: '',
                    tel: '',
                    reMan: '',
                    email: '',
                    Man: '',
                    urName: '',
                    urNum: ''
                },
            }
        },
        mounted(){
            var _this = this;
            $.ajax({
                type: 'GET',
                url: urls.GET_PROVINCE,

                success: function (data) {

                    _this.place.sheng = data.data;

                },
                dataType: 'json'
            });
            this.fileUpload = urls.GET_UPLOAD;
            sessionStorage.setItem("kjkey", "0");

        }
    }
</script>

<style lang="less">
body,p,div,input,el-form { 
	/*兼容360下的闪屏*/
    -webkit-transform-style: preserve-3d; 
}
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }

    .avatar-uploader .el-upload:hover {
        border-color: #20a0ff;
    }

    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 100px;
        height: 100px;
        line-height: 100px;
        text-align: center;
    }

    .avatar {
        width: 100px;
        height: 100px;
        display: block;
    }

</style>
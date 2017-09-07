<template>
	<div>
		<div class="plan">
			<div class="title clear" style="padding:15px 15px;">

				<el-tabs v-model="activeName">
					<el-tab-pane label="企业信息编辑" name="first">
						<div class="plan-form">
							<el-form label-position="left" label-width="120px" :model="company" ref="baseComForm" :rules="rules1">
								<div class="plan-form-title">
									<div class="devider"></div>
									<p class="text">基本信息</p>
								</div>
								<div class="plan-form-content" style="padding-left:20px;">
									<el-form-item label="用户名" style="width:40%;">
										{{company.userName}}
									</el-form-item>

									<el-form-item label="单位名称" style="width:40%;">
										{{company.companyName}}
									</el-form-item>
									<el-form-item label="单位联系人" style="width:40%;" prop='contactName'>
										<el-input v-model="company.contactName" :disabled=planTypeInputDisable></el-input>
									</el-form-item>
									<el-form-item label="注册手机号码">
										{{company.contactPhone}}
									</el-form-item>

									<el-form-item label="单位地址" style="width:60%;" prop="companyAddress">
										<el-select style="width:90px;" v-model="company.province" @input="comProvince" placeholder="请选择">
											<el-option v-for="Sheng in place.sheng" :label="Sheng.name" :value="Sheng"></el-option>
										</el-select>
										<el-select style="width:90px;" v-model="company.city" @input="comCity" placeholder="请选择">
											<el-option v-for="Shi in place.shi" :label="Shi.name" :value="Shi"></el-option>
										</el-select>
										<el-select style="width:90px;" v-model="company.are" placeholder="请选择">
											<el-option v-for="qu in place.qu" :label="qu.name" :value="qu.name"></el-option>
										</el-select>

										<el-input style="margin-top:15px;" type="textarea" :rows="7" v-model="xian" placeholder="详细地址"></el-input>
									</el-form-item>

									<el-form-item label="公司邮箱" prop="email" style="width:40%;">
										<el-input v-model="company.email"></el-input>
									</el-form-item>

								</div>
								<div class="plan-form-title">
									<div class="devider"></div>
									<p class="text">紧急联系人</p>
								</div>
								<div class="plan-form-content" style="padding-left:20px;">
									<el-form-item label="姓名" prop="emergencyContactName" style="width:40%;">
										<el-input v-model="company.emergencyContactName"></el-input>
									</el-form-item>
									<el-form-item label="手机号码" prop="emergencyContactPhone" style="width:40%;">
										<el-input v-model="company.emergencyContactPhone"></el-input>
									</el-form-item>
								</div>
								<el-form-item style="margin-top: 30px;">
									<el-button type="primary" @click="updateComPage1"  style="margin-right: 8px; float: left;" >提交修改</el-button>
									<a href="#/myplan" ><el-button style="z-index: 1000;" class="left" >返回</el-button></a>	
 							</el-form-item>
							</el-form>
						</div>
					</el-tab-pane>
					<el-tab-pane label="认证信息" name="second">
						<div class="plan-form">
							<el-form label-position="left" label-width="120px" :model="company" ref="vertifyComForm" :rules="rules2">
								<div class="plan-form-title">
									<div class="devider"></div>
									<p class="text">认证信息</p>
								</div>
								<div class="plan-form-content" style="padding-left:20px;">
									<el-form-item label="认证状态">
										<div v-if="company.authStatus==1" style="color:red">未认证</div>
										<div v-if="company.authStatus==2" style="color:#3399ff">待认证</div>
										<div v-if="company.authStatus==3" style="color:green">已认证</div>
										<div v-if="company.authStatus==4" style="color:red">认证失败</div>
									</el-form-item>

									<el-form-item label="失败原因" style="width:50%;" v-show=block>
										<div style="color: red;">{{company.reason}}</div>
									</el-form-item>
									<el-form-item label="单位名称" style="width:50%;" prop='companyName'>
										<el-input v-model="company.companyName" :disabled=planTypeInputDisable></el-input>
									</el-form-item>
									<el-form-item label="公司法人" style="width:50%;" prop='companyLegal'>
										<el-input v-model="company.companyLegal" :disabled=planTypeInputDisable></el-input>
									</el-form-item>

									<!--<el-form-item label="社会统一信用代码" style="width:50%;">
										<el-input v-model="company.licenseNumber"></el-input>
									</el-form-item>-->
									<el-form-item label="社会统一信用代码" placeholder="请输入执行编号" prop="licenseNumber" style="width:50%;">
										<el-input v-model="company.licenseNumber" @blur="checklicenseNumber" :disabled=planTypeInputDisable></el-input>
									</el-form-item>
                                    
									<el-form-item label="营业执照" prop='licensePic'>
										<img :src="company.licensePic" style="width:100px;height:100px;margin-right:10px;" />
										<el-upload :disabled=planTypeInputDisable class="avatar-uploader" :action="fileUpload" :before-upload="beforeFileUpload" :multiple="false" :on-success="handleFileUploadSuccess" :on-error="handleFileUploadFail" :show-file-list="false">
											<el-button size="small" :disabled=planTypeInputDisable>重新上传</el-button>
										</el-upload>
									</el-form-item>
								</div>

								<el-form-item>
									<el-button type="primary" @click="updateComPage2" :disabled=planTypeInputDisable style="margin-right: 8px; float: left;" >提交修改</el-button>
									<a href="#/myplan" ><el-button style="z-index: 1000;" class="left" >返回</el-button></a>	
								</el-form-item>
							</el-form>
						</div>
					</el-tab-pane>
					<el-tab-pane label="修改注册手机" name="third">
						<div class="plan-form">
							<el-form label-position="left" label-width="120px" :model="company" ref="phoneComForm" :rules="rules3">
								<div class="plan-form-content">
									<el-form-item label="联系人">
										{{company.contactPhone}}
									</el-form-item>
								</div>
								<div class="plan-form-content">
									<el-form-item label="新注册手机号" prop="newPhone" style="width:46%;" placeholder="验证短信将会发送到此手机上">
										<el-input v-model="company.newPhone"></el-input>
									</el-form-item>
								</div>
								<div class="plan-form-content">
									<el-form-item label="验证码" style="width:46%;" prop="PhoneCheck">
										<el-input v-model="personPhoneCheck"></el-input>
									</el-form-item>
								</div>
								<el-button type="primary" :class="tongyi?'None':'Block'" style="top: 58px;left:354px;position: absolute;">{{timeback}}</el-button>
								<el-button type="primary" :class="tongyi?'Block':'None'" style="top: 58px;left:350px;position: absolute;" @click=checkTel>发送验证码</el-button>
								<el-form-item>
									<el-button type="primary" @click="updateComPage3" style="margin-right: 8px; float: left;" >提交修改</el-button>
									<a href="#/myplan">
										<el-button style="z-index: 1000;" class="left">返回</el-button>
									</a>
								</el-form-item>
							</el-form>
						</div>
					</el-tab-pane>

				</el-tabs>

			</div>

		</div>
	</div>
</template>

<script>
	import urls from '../../components/urls'
	import Vue from 'vue'
	import util from '../../components/util'
	import checkCompanyId from '../../components/valid-company'

	export default {
		methods: {
			//检测社会统一信用代码
			checklicenseNumber() {
				if(this.company.licenseNumber != "") {
					var _this = this;
					$.ajax({
						type: 'POST',
						url: urls.CHECK_USERNAME,
						data: {
							data: this.company.licenseNumber,
							type: 5
						},
						success: function() {

						},
						error: function() {
							_this.company.licenseNumber = '';
						},
						dataType: 'json'
					});
				}
			},
			//检测手机号是否已注册
			checkTel() {
				var _this = this;
				if(this.company.newPhone != "" && this.company.newPhone.length == 11) {
					var _this = this;
					$.ajax({
						type: 'POST',
						url: urls.CHECK_USERNAME,
						data: {
							data: this.company.newPhone,
							type: 1
						},
						success: function() {
							clearInterval(timer);
							var timer = null

							_this.tongyi = false;
							timer = setInterval(time, 1000);
							var a = 60;

							function time() {
								a--;
								_this.timeback = a;
								if(a <= 0) {
									_this.tongyi = true;
									clearInterval(timer);
								}

							}
						},
						error: function() {
							_this.company.newPhone = '';

						},
						dataType: 'json'
					})
				} else {
					return this.$message.error('请填写正确手机号');
				}
				var URL = urls.GET_MESSAGW + _this.company.newPhone;
				$.ajax({
					type: 'GET',
					url: URL,
					success: function(data, textStatus, jqXHR) {},
				});

			},
			updateComPage1() {
				var validate = true;
				for(var prop in this.rules1) {
					this.$refs['baseComForm'].validateField(prop, function(errorMessage) {
						//如果field使用了validator，此处的errorMessage为空，目前暂时使用判空的解决办法
						if(errorMessage != '') {
							this.$message.error(errorMessage, 5000);
							validate = false;
						}
					}.bind(this));
				}
				if(!validate) return;
				this.isLoading = true;
				var _this = this;
				if(this.company.contactName == '') {
					return this.$message.error('请将相关信息补充完整');
				}
				this.company.address = this.company.province + " " + this.company.city + " " + this.company.are + " " + this.xian;
				$.ajax({
					type: 'PUT',
					url: urls.UPDATE_COMINFORMATION,
					data: {
						companyAddress: _this.company.address,
						email: _this.company.email,
						companyLegal: _this.company.companyLegal,
						emergencyContactName: _this.company.emergencyContactName,
						emergencyContactPhone: _this.company.emergencyContactPhone,
						contactName: _this.company.contactName,
						companyName: _this.company.companyName,
						licenseNumber: _this.company.licenseNumber,
						licensePic: _this.company.licensePic,
					},
					success: function() {
						_this.$message({
							message: '修改成功',
							type: 'success',
						})
						window.location.href = "#/myplan";
					},
					dataType: 'json'
				});
			},
			updateComPage2() {
				var validate = true;
				for(var prop in this.rules2) {
					this.$refs['vertifyComForm'].validateField(prop, function(errorMessage) {
						//如果field使用了validator，此处的errorMessage为空，目前暂时使用判空的解决办法
						if(errorMessage != '') {
							this.$message.error(errorMessage, 5000);
							validate = false;
						}
					}.bind(this));
				}
				if(!validate) return;
				this.isLoading = true;
				var _this = this;
				$.ajax({
					type: 'PUT',
					url: urls.UPDATE_COMINFORDATA,
					data: {
						licenseNumber: _this.company.licenseNumber,
						companyLegal: _this.company.companyLegal,
						companyName: _this.company.companyName,
						licensePic: _this.company.licensePic,
					},
					success: function() {
						_this.$message({
							message: '修改成功',
							type: 'success',
						})
						window.location.href = "#/myplan";
					},
					dataType: 'json'
				});
			},
			updateComPage3() {
				var validate = true;
				for(var prop in this.rules3) {
					this.$refs['phoneComForm'].validateField(prop, function(errorMessage) {
						//如果field使用了validator，此处的errorMessage为空，目前暂时使用判空的解决办法
						if(errorMessage != '') {
							this.$message.error(errorMessage, 5000);
							validate = false;
						}
					}.bind(this));
				}
				var _this = this;
				if(this.personPhoneCheck == "") {
					this.$message({
						message: '验证码不能为空',
						type: 'warning'
					});
					return
				} else {
					$.ajax({
						type: 'PUT',
						url: urls.UPDATE_USERPHONE,
						data: { code: _this.personPhoneCheck /*验证码*/ },
						success: function() {
							_this.$message({
								message: '修改成功',
								type: 'success'
							})
							window.location.href = "#/myplan";
						},
						error: function() {
							return
						}
					});

				}
			},
			comProvince(value) {

				var _this = this;

				this.company.city = "";
				this.company.are = "";
				this.xian = "";

				this.company.province = value.name;

				var URL = urls.GET_PROVINCE + "?id=" + value.id;
				$.ajax({
					type: 'GEt',
					url: URL,

					success: function(data) {

						_this.place.shi = data.data;

					},
					dataType: 'json'
				});

			},
			comCity(value) {
				var _this = this;
				this.company.are = "";
				this.xian = "";

				this.company.city = value.name;

				var URL = urls.GET_PROVINCE + "?id=" + value.id;
				$.ajax({
					type: 'GEt',
					url: URL,

					success: function(data) {

						_this.place.qu = data.data;

					},
					dataType: 'json'
				});
			},
			comXian(value) {
				his.xian = "";			
						},
			/**
			 * 处理文档上传成功
			 */
			handleFileUploadSuccess(res, file, fileList) {
				this.company.licensePic = URL.createObjectURL(file.raw);
				this.company.licensePhotoUrl = res.data[0].fullUrl;
			},
			/**
			 * 处理文档上传失败
			 */
			handleFileUploadFail(err, file, fileList) {
				this.$message.error('附件上传失败，请重试');
			},
			/**
			 * 上传文件前对文件进行校检
			 */
			beforeFileUpload(file) {
				const isJPG = file.type === 'image/jpeg' || 'image/jpg' || 'image/png';
				const isLt2M = file.size / 1024 / 1024 < 2;

				if(!isJPG) {
					this.$message.error('上传头像图片只能是 JPG/PNG 格式!');
				}
				if(!isLt2M) {
					this.$message.error('上传头像图片大小不能超过 2MB!');
				}
				return isJPG && isLt2M;
			}

		},

		data() {
			var validatePass4 = (rule, value, callback) => {
				var res = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
				if(value != "") {
					if(res.test(value)) {
						callback();
					} else {
						callback(new Error('请邮箱格式错误'));
					}
				} else {
					callback();
				}

			};
			var emergencyContactPhoneValidator = (rule, value, callback) => {
				if(this.company.emergencyContactPhone== this.company.newPhone) {
					return callback(new Error(rule.message));
				}
				callback();
			};
			var ContactPhoneValidator = (rule, value, callback) => {
				if(this.company.contactPhone == this.company.emergencyContactPhone) {
					return callback(new Error(rule.message));
				}
				callback();
			};
			
			var licenseNumberValidator = (rule, value, callback) => {
				if(!checkCompanyId(value)) {
					callback(new Error(rule.message));
					return;
				}
				callback();
			};
			var planPhoneValidator = (rule, value, callback) => {
				var myreg = /^1[34578]\d{9}$/;
				if(!myreg.test(this.company.newPhone)) {
					return callback(new Error(rule.message));
				}
				callback();
			};
			
			var addressValidator = (rule, value, callback) => {
				if(this.xian == '') {
					return callback(new Error(rule.message));
				}
				callback();
			};
			var licensePicValidator = (rule, value, callback) => {
				if(this.company.licensePic == '') {
					return callback(new Error(rule.message));
				}
				callback();
			}
			
			return {
				rules1: {
					emergencyContactName: [
						{ required: true, message: '请输入紧急联系人名字', trigger: 'blur' },
						{ min: 2, message: '长度在 2 到 10 个字符', trigger: 'blur' }
					],					
					emergencyContactPhone: [
						{ required: true, message: '请输入紧急联系人电话', trigger: 'blur' },                         
						{ min: 11, max: 11, validator: ContactPhoneValidator,message: '紧急联系人和联系人的号码不能一样', trigger: 'blur' }
					],
					email: [
						{ required: true, message: '请再次输入邮箱', trigger: 'blur' },
						{ validator: validatePass4, trigger: 'blur' }
					],					
					companyAddress: [
						{ required: true, validator: addressValidator, message: '请输入详细地址', trigger: 'blur' }
					],
					contactName:[
						{ required: true, message: '请填写单位联系人', trigger: 'blur' },	
					],
				},
				rules2:{
					licenseNumber: [
						{ required: true, validator: licenseNumberValidator, message: '请输入有效的社会统一信用代码', trigger: 'blur' }
					],
					companyName: [
						{ required: true, message: '单位名称不能为空', trigger: 'blur' }
					],
					companyLegal: [
						{ required: true, message: '公司法人不能为空', trigger: 'blur' }
					],
					licensePic: [
						{ required: true,validator: licensePicValidator, message: '请上传营业执照', trigger: 'blur' }
					],
				},
				rules3:{
					newPhone: [
						{ required: true, message: '请输入新手机号', trigger: 'blur' },
						{ min: 11, max: 11, validator: planPhoneValidator, message: '请填写正确的手机号', trigger: 'blur' },
						{ validator: emergencyContactPhoneValidator, message: '新注册号码不能与紧急联系人号码一致', trigger: 'blur' }
					],
					PhoneCheck: [
						{ required: true, message: '验证码不能为空', trigger: 'blur' }
					],
				},
				imageUrl: '',
				fileUpload: '1',
				block: false,
				timeback: 60,
				tongyi: true,
				personPhoneCheck: '',
				planTypeInputDisable: false,
				activeName: 'first',
				place: {
					sheng: '',
					shi: '',
					qu:'',

				},
				xian: '',
				region: [{
					label: '飞行员证书',
					value: 1
				}, ],
				xian: '',
				company: {
					contactPhone: '',
					newPhone: '',
					emergencyContactName: '',
					emergencyContactPhone: '',
					address: '',
					email: '',
					city: '',
					province: '',
					are: '',
					licensePic: '',
					licensePhotoUrl: '',
					licenseNumber: '',
					companyAddress: '',
					companyName: '',
					companyLegal:'',
				},
			}
		},
		mounted() {

			var _this = this;

			$.ajax({
				type: 'GET',
				url: urls.GET_UPDATEMESSAGE,
				success: function(res) {
					_this.company = Vue.util.extend(_this.company, res.data);
					_this.company.address = _this.company.companyAddress.split(" ");
					_this.company.province = _this.company.address[0];
					_this.company.city = _this.company.address[1];
					_this.company.are = _this.company.address[2];
					_this.xian = _this.company.address[3];
					_this.fileUpload = urls.GET_UPLOAD;
					if(_this.company.authStatus == 3) {
						_this.planTypeInputDisable = true;

					}
					if(_this.company.authStatus == 4) {
						_this.block = true;

					}

				},
				dataType: 'json'

			});

			$.ajax({
				type: 'GET',
				url: urls.GET_PROVINCE,

				success: function(data) {
					_this.place.sheng = data.data;

				},
				dataType: 'json'
			});
		}
	}
</script>

<style lang="less" scoped>
	* {
		-webkit-transform-style: preserve-3d;
	}
</style>
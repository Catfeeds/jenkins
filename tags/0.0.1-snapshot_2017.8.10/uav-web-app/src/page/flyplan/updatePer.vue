<template>
	<div>
		<div class="plan">
			<div class="title clear" style="padding:15px 15px;">
				<el-tabs v-model="activeName">
					<el-tab-pane label="基本信息" name="first">
						<div class="plan-form">
							<el-form label-position="left" label-width="120px" :model="person" :rules="rules1" ref="baseForm">
								<div class="plan-form-title">
									<div class="devider"></div>
									<p class="text">基本信息</p>
								</div>
								<div class="plan-form-content" style="padding-left:20px;">
									<el-form-item label="用户名">
										{{person.userName}}
									</el-form-item>
									<el-form-item label="姓名" prop="name" style="width:40%;">
										<el-input v-model="person.name" :disabled=planTypeInputDisable></el-input>
									</el-form-item>

									<el-form-item label="注册手机号码">
										{{person.phone}}
									</el-form-item>
									<el-form-item label="证件编号" class="clear" prop="ertificateNumber">
										<el-input placeholder="请输入内容" v-model="person.certificateNumber" style="width:380px;">
											<el-select v-model="person.certificateType" slot="prepend" placeholder="请选择" style="width:200px;"  >
												<!--@change="ChangeCertificateType"-->
												<el-option v-for="type in this.$store.state.certTypes" :label="type.label" :value="type.value"></el-option>
											</el-select>
										</el-input>
									</el-form-item>

								</div>
								<div class="plan-form-title">
									<div class="devider"></div>
									<p class="text">其他联系方式</p>
								</div>
								<div class="plan-form-content" style="padding-left:20px;">
									<el-form-item label="QQ" style="width:40%;">
										<el-input v-model="person.qq"></el-input>
									</el-form-item>
									<el-form-item label="邮箱" prop="email" style="width:40%;">
										<el-input v-model="person.email"></el-input>
									</el-form-item>
									<el-form-item label="现居住地" prop="address" style="width:60%;">
										<el-select style="width:90px;" v-model="person.province" @input="perProvince" placeholder="请选择">
											<el-option v-for="Sheng in place.sheng" :label="Sheng.name" :value="Sheng"></el-option>
										</el-select>
										<el-select style="width:90px;" v-model="person.city" @input="perCity" placeholder="请选择">
											<el-option v-for="Shi in place.shi" :label="Shi.name" :value="Shi"></el-option>
										</el-select>
										<el-select style="width:90px;" v-model="person.are" placeholder="请选择">
											<el-option v-for="qu in place.qu" :label="qu.name" :value="qu.name"></el-option>
										</el-select>
										<el-input style="margin-top:15px;" type="textarea" :rows="7" v-model="xian" placeholder="详细地址"></el-input>
									</el-form-item>
								</div>
								<div class="plan-form-title">
									<div class="devider"></div>
									<p class="text">紧急联系人</p>
								</div>
								<div class="plan-form-content" style="padding-left:20px;">
									<el-form-item label="姓名" prop="emergencyContactName" style="width:40%;">
										<el-input v-model="person.emergencyContactName"></el-input>
									</el-form-item>
									<el-form-item label="手机号码" prop="emergencyContactPhone" style="width:40%;">
										<el-input v-model="person.emergencyContactPhone"></el-input>
									</el-form-item>
								</div>
								<el-form-item style="margin-top: 30px;">
									<el-button type="primary" @click="updatePerson1" class="left" style="margin-right: 8px;">提交修改</el-button>									
									<a href="#/myplan" ><el-button style="z-index: 1000;" class="left" >返回</el-button></a>									
								</el-form-item>

							</el-form>
						</div>
					</el-tab-pane>
					<el-tab-pane label="认证信息" name="second">
						<div class="plan-form">
							<el-form label-position="left" label-width="120px" :model="person" :rules="rules2" ref="vertifyForm">
								<div class="plan-form-title">
									<div class="devider"></div>
									<p class="text">认证信息</p>
								</div>
								<div class="plan-form-content" style="padding-left:20px;">
									<el-form-item label="认证状态">
										<div v-if="person.authStatus==1" style="color:red">未认证</div>
										<div v-if="person.authStatus==2" style="color:#3399ff">待认证</div>
										<div v-if="person.authStatus==3" style="color:green">已认证</div>
										<div v-if="person.authStatus==4" style="color:red">认证失败</div>
									</el-form-item>
									<el-form-item label="原因" v-show=block>
										<div style="color: red;">{{person.reason}}</div>
									</el-form-item>

									<el-form-item label="身份证号" placeholder="请输入身份证号" prop="idCard" style="width:40%;">
										<el-input v-model="person.idCard" @blur="checkID" :disabled=planTypeInputDisable></el-input>
									</el-form-item>
									<el-form-item label="出生日期" prop="birthday" style="width:40%;">
										<el-input v-model="person.birthday" @blur="checkID" :disabled='true'></el-input>
									</el-form-item>

									<el-form-item label="性别">
										<span v-if="person.sex=='M'">男</span>
										<span v-if="person.sex=='F'">女</span>
									</el-form-item>
									<el-form-item label="身份证照片" style="height: 140px;line-height: 140px;" prop="idcardPic">
										<div class="photo-box">
											<img :src="person.idcardPic1" style="width:100px;height:100px;margin-right:10px;" />
											<el-upload :disabled=planTypeInputDisable class="avatar-uploader left" :action="fileUpload" :show-file-list="false" :on-success="handleAvatarSuccess1" :before-upload="beforeAvatarUpload">
												<el-button size="small" class="move-box" :disabled=planTypeInputDisable>重新上传</el-button>
											</el-upload>
										</div>

										<div class="photo-box">
											<img :src="person.idcardPic2" style="width:100px;height:100px;margin-right:10px;" />
											<el-upload :disabled=planTypeInputDisable class="avatar-uploader left" style="margin-left:6px;" :action="fileUpload" :show-file-list="false" :on-success="handleAvatarSuccess2" :before-upload="beforeAvatarUpload">
												<el-button size="small" :disabled=planTypeInputDisable>重新上传</el-button>
											</el-upload>
										</div>
										<div class="photo-box">
											<img :src="person.idcardPic3" style="width:100px;height:100px;margin-right:10px;" />
											<el-upload :disabled=planTypeInputDisable class="avatar-uploader left" style="margin-left:10px;" :action="fileUpload" :show-file-list="false" :on-success="handleAvatarSuccess3" :before-upload="beforeAvatarUpload">
												<el-button size="small" :disabled=planTypeInputDisable>重新上传</el-button>
											</el-upload>
										</div>

									</el-form-item>
								</div>
								<el-form-item style="margin-top: 18px;" class="left">
									<el-button type="primary" @click="updatePerson2" :disabled=planTypeInputDisable class="left" style="margin-right: 8px;">提交修改</el-button>
									<a href="#/myplan" ><el-button style="z-index: 1000;" class="left" >返回</el-button></a>
								</el-form-item>
							</el-form>
						</div>
					</el-tab-pane>
					<el-tab-pane label="修改注册手机" name="third">
						<div class="plan-form">
							<el-form label-position="left" label-width="120px" :model="person" :rules="rules3" ref="phoneForm">
								<div class="plan-form-title">
									<div class="devider"></div>
									<p class="text">修改注册手机</p>
								</div>
								<div class="plan-form-content" style="padding-left:20px;">
									<el-form-item label="联系人">
										{{person.phone}}
									</el-form-item>
								</div>
								<div class="plan-form-content" style="padding-left:20px;">
									<el-form-item label="新注册手机号" prop="newPhone" style="width:46%;" placeholder="验证短信将会发送到此手机上">
										<el-input v-model="person.newPhone"></el-input>
									</el-form-item>
								</div>
								<div class="plan-form-content" style="padding-left:20px;">
									<el-form-item label="验证码" style="width:46%;" prop="PhoneCheck">
										<el-input v-model="personPhoneCheck"></el-input>
									</el-form-item>
								</div>
								<el-button type="primary" :class="tongyi?'None':'Block'" style="top: 78px;left:354px;position: absolute;">{{timeback}}</el-button>
								<el-button type="primary" class="right" style="top: 78px;left:350px;position: absolute;" @click=checkTel :class="tongyi?'Block':'None'">发送验证码</el-button>
								<el-form-item>
									<el-button type="primary" @click="updatePerson3" class="left" style="margin-right: 8px;">提交修改</el-button>
									<a href="#/myplan" ><el-button style="z-index: 1000;" class="left" >返回</el-button></a>
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
	import util from '../../components/util'
	import cardUtil from '../../components/valid-card'
	import Vue from 'vue'

	export default {
		methods: {

			//检测手机号是否已注册
			checkTel() {
				if(this.person.newPhone != "" && this.person.newPhone.length == 11) {
					var _this = this;
					$.ajax({
						type: 'POST',
						url: urls.CHECK_USERNAME,
						data: {
							data: this.person.newPhone,
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
							_this.person.newPhone = '';

						},
						dataType: 'json'
					})
				} else {
					return this.$message.error('请填写正确手机号');
				}
				var _this = this;
				var URL = urls.GET_MESSAGW + _this.person.newPhone;
				$.ajax({
					type: 'GET',
					url: URL,
					success: function(data, textStatus, jqXHR) {

					},
				});

			},
			ChangeCertificateType() {
				this.person.certificateNumber = '';
			},
			updatePerson1() {
				var validate = true;
				for(var prop in this.rules1) {
					this.$refs['baseForm'].validateField(prop, function(errorMessage) {
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
				this.address = this.person.province + " " + this.person.city + " " + this.person.are + " " + this.xian;
				$.ajax({
					type: 'PUT',
					url: urls.UPDATE_PERINFORMATION,
					data: {
						name: _this.person.name,
						sex: _this.person.sex,
						birthday: _this.bb,
						certificateType: _this.person.certificateType,
						certificateNumber: _this.person.certificateNumber,
						qq: _this.person.qq,
						email: _this.person.email,
						address: _this.address,
						emergencyContactName: _this.person.emergencyContactName,
						emergencyContactPhone: _this.person.emergencyContactPhone,
						idCard: _this.person.idCard,
						idcardPic1: _this.person.idcardPic1,
						idcardPic2: _this.person.idcardPic2,
						idcardPic3: _this.person.idcardPic3,
					},
					success: function() {
						_this.$message({
							message: '修改成功',
							type: 'success'
						})
						window.location.href = "#/myplan";
					},
					dataType: 'json'
				});
			},
			updatePerson2() {
				var validate = true;
				for(var prop in this.rules2) {
					this.$refs['vertifyForm'].validateField(prop, function(errorMessage) {
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
					url: urls.UPDATE_PERINFORDATA,
					data: {
						idCard: _this.person.idCard,
						idcardPic1: _this.person.idcardPic1,
						idcardPic2: _this.person.idcardPic2,
						idcardPic3: _this.person.idcardPic3,
						sex: _this.person.sex,
						birthday: _this.person.birthday,
					},
					success: function() {
						_this.$message({
							message: '修改成功',
							type: 'success'
						})
						window.location.href = "#/myplan";
					},
					dataType: 'json'
				});
			},
			updatePerson3() {
				var validate = true;
				for(var prop in this.rules3) {
					this.$refs['phoneForm'].validateField(prop, function(errorMessage) {
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
				if(this.person.newPhone != "") {
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

							}
						});

					}

				}

			},
			//检测身份证是否已注册
			checkID() {
				if(cardUtil.checkCard(this.person.idCard)) {
					$.ajax({
						type: 'POST',
						url: urls.CHECK_USERNAME,
						data: {
							data: this.person.idCard,
							type: 3
						},
						success: function() {
							var dateStr = cardUtil.getBirthday(this.person.idCard);
							if(dateStr != null) {
								this.person.birthday = dateStr.year + '-' + dateStr.month + '-' + dateStr.day;
							}
						}.bind(this),
						error: function() {
							this.$message.error('该身份证号已被注册')
						}.bind(this),
						dataType: 'json'
					});
				}
			},
			perProvince(value) {
				var _this = this;
				this.person.city = "";
				this.person.are = "";
				this.xian = "";

				this.person.province = value.name;

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
			perCity(value) {
				var _this = this;
				this.person.are = "";
				this.xian = "";

				this.person.city = value.name;

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
			gg3(value) {
				this.xian = "";

			},
			/**
			 * 处理文档上传成功
			 */
			handleAvatarSuccess1(res, file) {

				this.person.idcardPic1 = URL.createObjectURL(file.raw);
				this.person.idPhoto1 = res.data[0].fullUrl;

			},
			handleAvatarSuccess2(res, file) {
				this.person.idcardPic2 = URL.createObjectURL(file.raw);
				this.person.idPhoto2 = res.data[0].fullUrl;

			},
			handleAvatarSuccess3(res, file) {
				this.person.idcardPic3 = URL.createObjectURL(file.raw);
				this.person.idPhoto3 = res.data[0].fullUrl;

			},
			beforeAvatarUpload(file) {
				const isJPG = file.type === 'image/jpg' || 'image/png';
				const isLt2M = file.size / 1024 / 1024 < 2;

				if(!isJPG) {
					this.$message.error('上传头像图片只能是 JPG和PNG 格式!');
				}
				if(!isLt2M) {
					this.$message.error('上传头像图片大小不能超过 2MB!');
				}
				return isJPG && isLt2M;
				return true;
			}
		},
		data() {
			var validatePass4 = (rule, value, callback) => {
				var res = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
				if(res.test(value)) {
					callback()
				} else {
					callback(new Error('邮箱格式错误'));
				}
			};
			var idCardValidator = (rule, value, callback) => {
				if(!cardUtil.checkCard(this.person.idCard)) {
					callback(new Error(rule.message));
				} else {
					callback();
				}
			};

			var emergencyContactPhoneValidator = (rule, value, callback) => {
				if(this.person.emergencyContactPhone==this.person.newPhone) {
					callback(new Error(rule.message));
				}
				callback();
			};
			var ContactPhoneValidator = (rule, value, callback) => {
				if(this.person.phone == this.person.emergencyContactPhone) {
					return callback(new Error(rule.message));
				}
				callback();
			};
			var planPhoneValidator = (rule, value, callback) => {
				var myreg = /^1[34578]\d{9}$/;
				if(!myreg.test(this.person.newPhone)) {
					callback(new Error(rule.message));
				}
				callback();
			};
			var addressValidator = (rule, value, callback) => {
				if(this.xian == '') {
					callback(new Error(rule.message));
				}
				callback();
			};
			var ertNumberValidator = (rule, value, callback) => {
				if(this.person.certificateNumber == '') {
					callback(new Error(rule.message));
				}
				callback();
			};
			var idcardPicValidator = (rule, value, callback) => {
				if(this.person.idcardPic1 == ''||this.person.idcardPic1 == ''||this.person.idcardPic1 == '') {
					callback(new Error(rule.message));
				}
				callback();
			};
			return {
				rules1: {
					email: [
						{ required: true, message: '请输入邮箱', trigger: 'blur' },
						{ required: true, validator: validatePass4, trigger: 'blur' }
					],

					name: [
						{ required: true, message: '请输入联系人姓名', trigger: 'blur' },
					],
					address: [
						{ required: true, message: '详细地址不能为空', trigger: 'blur', validator: addressValidator },
					],
					emergencyContactName: [
						{ required: true, message: '请输入紧急联系人名字', trigger: 'blur' },
						{ required: true, min: 2, message: '长度在 2 到 10 个字符', trigger: 'blur' }
					],
					emergencyContactPhone: [
						{ required: true, message: '请输入紧急联系人电话', trigger: 'blur' },
						{ min: 11, max: 11, validator: ContactPhoneValidator, message: '紧急联系人和联系人的号码不能一样', trigger: 'blur' }
					],
					ertificateNumber:[
					   	{ required: true, validator: ertNumberValidator,message: '证件编号不能为空', trigger: 'blur' },
					],
				},
				rules2: {
					idCard: [
						{ required: true, message: '请输入身份证号码', trigger: 'blur' },
						{ required: true, validator: idCardValidator, trigger: 'blur', message: '请输入合法的身份证号码' },
					],
					idcardPic: [
						{ required: true, validator: idcardPicValidator, trigger: 'blur', message: '请上传您的身份证照片' },
					],
					birthday: [
						{ required: true, message: '请输入您的生日', trigger: 'blur' },
					],
				},
				rules3: {
					newPhone: [
						{ required: true, message: '请输入新手机号', trigger: 'blur' },
						{ min: 11, max: 11, validator: planPhoneValidator, message: '请填写正确手机号', trigger: 'blur' },
						{ required: true, validator: emergencyContactPhoneValidator, message: '新注册号码不能与紧急联系人号码一致', trigger: 'blur' },
					],
					PhoneCheck: [
						{ required: true, message: '验证码不能为空', trigger: 'blur' },
					],
				},
				place: {
					sheng: '',
					shi: '',
					qu: ''
				},
				bb: '2017-01-03 00:00:00',
				fileUpload: '1',
				timeback: 60,
				tongyi: true,
				xian: '',
				personPhoneCheck: '',
				planTypeInputDisable: false,
				block: false,
				activeName: 'first',
				person: {
					userName: '',
					name: '',
					sex: '',
					idCard: '',
					idcardPic1: '',
					idcardPic2: '',
					idcardPic3: '',
					emergencyContactName: '',
					emergencyContactPhone: '',
					birthday: '',
					email: '',
					city: '',
					province: '',
					are: '',
					certificateType: this.$store.state.idCardTypes[0].label,
					certificateNumber: '',
					newPhone: '',
					phone: '',
					address: '',
				},

			}
		},
		mounted() {

			var _this = this;

			var a, b, c = null;
			$.ajax({
				type: 'GET',
				url: urls.GET_UPDATEMESSAGE,
				success: function(res) {
					_this.person = Vue.util.extend(_this.person, res.data);
					_this.person.address = _this.person.address.split(" ");
					_this.person.province = _this.person.address[0];
					_this.person.city = _this.person.address[1];
					_this.person.are = _this.person.address[2];
					_this.xian = _this.person.address[3];
					_this.fileUpload = urls.GET_UPLOAD;
					_this.place.sheng.forEach(function(item, index) {
						if(_this.place.sheng[index].name == _this.person.province) {
							a = _this.place.sheng[index].id;
						}
					});

					if(a != null) {
						var URL = urls.GET_PROVINCE + "?id=" + a;
						$.ajax({
							type: 'GEt',
							url: URL,
							success: function(data) {

								_this.place.shi = data.data;
								_this.place.shi.forEach(function(item, index) {
									if(_this.place.shi[index].name == _this.person.city) {
										b = _this.place.shi[index].id;
									}
								});
								if(b != null) {
									var URL = urls.GET_PROVINCE + "?id=" + b;
									$.ajax({
										type: 'GEt',
										url: URL,

										success: function(data) {

											_this.place.qu = data.data;
										},
										dataType: 'json'
									});
								}

							},
							dataType: 'json'
						});
					}
					if(_this.person.authStatus == 3) {
						_this.planTypeInputDisable = true;

					}
					if(_this.person.authStatus == 4) {
						_this.block = true;
					}
				},
				dataType: 'json'
			});

		},

		created() {
			var _this = this;
			$.ajax({
				type: 'GET',
				url: urls.GET_PROVINCE,
				success: function(data) {
					_this.place.sheng = data.data;
				},
				dataType: 'json'
			});

		},
	}
</script>

<style lang="less" scoped>
	* {
		/*兼容360下的闪屏*/
		-webkit-transform-style: preserve-3d;
	}
	
	.el-menu-demo {
		float: left;
		width: 400px;
	}
	
	.photo-box button {
		margin-left: 8px;
	}
	
	.photo-box {
		float: left;
		width: 100px;
		height: 130px;
		margin-right: 10px;
	}
</style>
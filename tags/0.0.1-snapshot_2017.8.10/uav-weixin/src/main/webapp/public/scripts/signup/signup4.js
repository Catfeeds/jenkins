import React from 'react'
import Tool from 'utils/tool'
import ReactDOM from 'react-dom'
import Promise from 'promise'
import checkCard from 'utils/valid-card'
import Agreement from './agreement'
import { Gallery, GalleryDelete, Panel, PanelBody, MediaBox, Form, FormCell, CellHeader, CellBody, CellFooter, Label, Input, Uploader, ButtonArea, Button, Select, Flex, FlexItem } from 'react-weui'
import CityPicker from 'common/weui/city-picker'

export default class Signup2App extends React.Component{
	constructor(props) {
		super(props);
		this.bl_uploader = null;
		this.idc1_uploader = null;
		this.idc2_uploader = null;
		this.idc3_uploader = null;
		this.state = {
			showAgreement : false,
			idCardUrl1 : "",
			idCardUrl2 : "",
			idCardUrl3 : "",
			busLicenseUrl : "",
			showProvincePicker : false,
			showCityPicker : false,
			showAreaPicker : false,
			provinceData : [],
			cityData : [],
			areaData : [],
			showGallery : false,
			gallery : null,
			showCityPicker : false,
			genders : [{ label : "男", value : 0 },{ label : "女", value : 1 }],
		};
	}
	componentDidMount(){
		if( this.props.signupType === undefined )
			return Tool.to('/signup/1')
		else if( this.props.signupType ){
			Tool.get(`${ config.ajaxPath }/province`)
			.then( json => json.data.map( ( v, k ) => ({ name : v.name, id : v.id}) ) )
			.then( cityData => {
				this.setState( Object.assign( {}, this.state, { provinceData : cityData } ) )
			} )
		}
	}
	redirectNextStep(){
		Tool.to("/signup/4");
	}
	togglePicker( name ){
		let _name;
		switch( name ){
			case "province":
				_name = "showProvincePicker";
				break;
			case "city":
				_name = "showCityPicker";
				break;
			case "area":
				_name = "showAreaPicker";
				break;
		}
		_name && this.setState( Object.assign( {}, this.state, {  [ _name ] : !this.state[_name] } ) )
	}
	changeHandleByPicker( name, text, selected, groups ){
		let _name,
			stateName,
			id,
			fetchType = 0;
		switch( name ){
			case "province":
				stateName = "companyProvince";
				_name = "showProvincePicker";
				id = groups[0].items[selected[0]].id;
				fetchType = 1;
				break;
			case "city":
				stateName = "companyCity";
				_name = "showCityPicker";
				id = groups[0].items[selected[0]].id;
				fetchType = 2;
				break;
			case "area":
				stateName = "companyArea";
				_name = "showAreaPicker";
				break;
		}
		this.props.changeHandle( stateName, groups[0].items[ selected[ 0 ] ].name )
		this.setState( Object.assign( {}, this.state, { [_name] : !this.state[_name]}),()=>{
			if( fetchType ){
				Tool.get(`${ config.ajaxPath }/province`,{ id })
				.then( json => json.data.map( ( v, k ) => ( { name : v.name, id : v.id } ) ) )
				.then( data => this.setState( Object.assign( {}, this.state, (fetchType === 1 ? ({ "cityData" : data, showCityPicker : true }) : ({ "areaData" : data, showAreaPicker : true }) ) ) ) )
			}
		})
	}
	changeHandleByFile( name, file, uploader ){
		switch( name ){
			case "busLicenseUrl":
				this.bl_uploader = uploader.files[0]
				break;
			case "idCardUrl1":
				this.idc1_uploader = uploader.files[0]
				break;
			case "idCardUrl2":
				this.idc2_uploader = uploader.files[0]
				break;
			case "idCardUrl3":
				this.idc3_uploader = uploader.files[0]
				break;
		}
		this.setState(Object.assign( {}, this.state, { [name] : { url : file.data } } ) )
	}
	uploadError( e ){
		Tool.alert("只能上传一张图片哦");
	}
	handleFileClick( name, file ){
		this.setState( Object.assign( {}, this.state, { showGallery : true, gallery : { url : file.url, name } } ) );
	}
	removeFile( name ){
		this.setState( Object.assign( {}, this.state, { showGallery : false, [name] : "" } ) );
	}
	hideGallery(){
		setTimeout(()=>{
			if( this.state.showGallery )
				this.setState( Object.assign( {}, this.state, { showGallery : false } ) )
		},100)
	}
	validParams(){
		let {
			signupType,
			username,
			password1,
			companyName,
			licenseCode,
			companyProvince,
			companyCity,
			companyArea,
			compnayAddr,
			gender,
			idCard,
			realname,
		} = this.props,
		{
			busLicenseUrl,
			idCardUrl1,
			idCardUrl2,
			idCardUrl3,
		} = this.state;

		// 企业注册
		if( signupType ){
			let message = (function(){
				if( !/^\S+$/.test( companyName ) ) return "请输入单位名称"
				if( !/^\S+$/.test( realname ) ) return "请输入联系人姓名"
				if( !/^\S+$/.test( licenseCode ) ) return "请输入营业执照号/社会统一信用代码"
				if( !busLicenseUrl ) return "请输入上传营业执照"

			})()
			if( message )
				return Tool.alert( message )
			this.toggleAgreement()
		// 个人注册
		}else{
			let message = (function(){
				if( !/^\S+$/.test( realname ) ) return "请输入姓名"
				if( !/^\S+$/.test( idCard ) || !checkCard( idCard ) ) return "身份证输入错误"
				if( !idCardUrl1 ) return "请输入上传证件正面照片"
				if( !idCardUrl2 ) return "请输入上传证件背面照片"
				if( !idCardUrl3 ) return "请输入上传手持身份证自拍照"

			})()
			if( message )
				return Tool.alert( message )
			this.toggleAgreement()
		}
	}
	submitHandle( agree ){
		debugger;
		this.setState( Object.assign( {}, this.state, { showAgreement : false } ) )
		if( !agree ) return;
		let {
			mobile,
			signupType,
			username,
			password1,
			companyName,
			licenseCode,
			companyProvince,
			companyCity,
			companyArea,
			compnayAddr,
			gender,
			idCard,
			realname,
		} = this.props,
		_idcard1Url = "",
		_idcard2Url = "",
		_idcard3Url = "",
		url = `${ config.ajaxPath }/oss/uploadFile`,
		promise;
		if( signupType ){
			promise = Tool.post( url, { file : this.bl_uploader })
			.then( json => Tool.post( `${ config.ajaxPath }/auth/register/com`,{
				userName : username,
				password : password1,
				companyName : companyName,
				licenseNumber : licenseCode,
				licensePic : json.data[0].fullUrl,
				companyAddress : companyProvince + companyCity + companyArea + compnayAddr,
				contactName : realname,
			}))
		// 个人注册
		}else{
			promise = Tool.post( url, { file : this.idc1_uploader })
			.then( json => {
				_idcard1Url = json.data[0].fullUrl;
				return Tool.post( url, { file : this.idc2_uploader }) 
			})
			.then( json => {
				_idcard2Url = json.data[0].fullUrl;
				return Tool.post( url, { file : this.idc3_uploader }) 
			})
			.then( json => {
				_idcard3Url = json.data[0].fullUrl;
			})
			.then( () => Tool.post( `${ config.ajaxPath }/auth/register/per`,{
				phone : mobile,
				userName : username,
				password : password1,
				name : realname,
				sex : gender ? "F" : "M",
				idCard : idCard,
				idcardPic1 : _idcard1Url,
				idcardPic2 : _idcard2Url,
				idcardPic3 : _idcard3Url,
			}))
		}
		promise.then( json => {
			if( json.data ){
				Tool.saveLocalData( "__user", json.data )
				Tool.to( Tool.getLocalData("signinBeforeUrl") || "/user/center")
			}else Tool.alert("注册失败")
		})
		// .catch( e => {
		// 	this.setState( Object.assign( {}, this.state, { showAgreement : false } ) )
		// })
	}
	signupHandle(){
		let {
			mobile,
			signupType,
			username,
			password1,
			companyName,
			licenseCode,
			companyProvince,
			companyCity,
			companyArea,
			compnayAddr,
			gender,
			idCard,
			realname,
		} = this.props,
		{
			busLicenseUrl,
			idCardUrl1,
			idCardUrl2,
			idCardUrl3,
		} = this.state,
		_idcard1Url = "",
		_idcard2Url = "",
		_idcard3Url = "",
		url = `${ config.ajaxPath }/oss/uploadFile`,
		promise;

		// 企业注册
		if( signupType ){
			let message = (function(){
				if( !/^\S+$/.test( companyName ) ) return "请输入单位名称"
				if( !/^\S+$/.test( realname ) ) return "请输入联系人姓名"
				if( !/^\S+$/.test( licenseCode ) ) return "请输入营业执照号/社会统一信用代码"
				if( !busLicenseUrl ) return "请输入上传营业执照"

			})()
			if( message )
				return Tool.alert( message )
			this.toggleAgreement()
			promise = Tool.post( url, { file : this.bl_uploader })
			.then( json => Tool.post( `${ config.ajaxPath }/auth/register/com`,{
				userName : username,
				password : password1,
				companyName : companyName,
				licenseNumber : licenseCode,
				licensePic : json.data[0].fullUrl,
				companyAddress : companyProvince + companyCity + companyArea + compnayAddr,
				contactName : realname,
			}))
		// 个人注册
		}else{
			let message = (function(){
				if( !/^\S+$/.test( realname ) ) return "请输入姓名"
				if( !/^\S+$/.test( idCard ) || !checkCard( idCard ) ) return "身份证输入错误"
				if( !idCardUrl1 ) return "请输入上传证件正面照片"
				if( !idCardUrl2 ) return "请输入上传证件背面照片"
				if( !idCardUrl3 ) return "请输入上传手持身份证自拍照"

			})()
			if( message )
				return Tool.alert( message )
			this.toggleAgreement()
			promise = Tool.post( url, { file : this.idc1_uploader })
			.then( json => {
				_idcard1Url = json.data[0].fullUrl;
				return Tool.post( url, { file : this.idc2_uploader }) 
			})
			.then( json => {
				_idcard2Url = json.data[0].fullUrl;
				return Tool.post( url, { file : this.idc3_uploader }) 
			})
			.then( json => {
				_idcard3Url = json.data[0].fullUrl;
			})
			.then( () => Tool.post( `${ config.ajaxPath }/auth/register/per`,{
				phone : mobile,
				userName : username,
				password : password1,
				name : realname,
				sex : gender ? "F" : "M",
				idCard : idCard,
				idcardPic1 : _idcard1Url,
				idcardPic2 : _idcard2Url,
				idcardPic3 : _idcard3Url,
			}))
		}
		promise.then( json => {
			let openId = json.data;
			if( openId )
				Tool.saveLocalData( "__user", { openId } )
			Tool.to( Tool.getLocalData("signinBeforeUrl") || "/user/center")
		})
	}
	toggleAgreement(){
		this.setState( Object.assign( {}, this.state, { showAgreement : !this.state.showAgreement } ) )
	}
	render(){
			let {
					signupType,
					realname,
					gender,
					idCard,
					companyName,
					companyProvince,
					companyCity,
					companyArea,
					compnayAddr,
					licenseCode,
					changeHandle,
				} = this.props,
				{
					busLicenseUrl,
					idCardUrl1,
					idCardUrl2,
					idCardUrl3,
					showGallery,
					gallery,
					// showCityPicker,
					showProvincePicker,
					showCityPicker,
					showAreaPicker,
					provinceData,
					cityData,
					areaData,
					genders,
					showAgreement,
				} = this.state,
				form;

			if( !signupType ){
				form = (
					<Form>
						<FormCell>
							<CellHeader><Label>姓名</Label></CellHeader>
							<CellBody><Input value = { realname } onChange = { e => changeHandle( "realname", e ) } placeholder = "请输入真实姓名" type = "text"/></CellBody>
						</FormCell>
						<FormCell select selectPos = "after">
							<CellHeader><Label>性别</Label></CellHeader>
							<CellBody><Select onChange = { e => changeHandle( "gender", e ) } value = { gender } data = { genders }/></CellBody>
						</FormCell>
						<FormCell>
							<CellHeader><Label>身份证</Label></CellHeader>
							<CellBody><Input maxLength = { 18 } value = { idCard } onChange = { e => changeHandle( "idCard", e ) } placeholder = "请输入身份证号码" type = "text"/></CellBody>
						</FormCell>
						<Panel>
							<PanelBody>
								<MediaBox>
									<Uploader name = "id_card1" className = { idCardUrl1 && "hideupload"} onFileClick = { ( e, file, i ) => this.handleFileClick( "idCardUrl1", file ) } files = { idCardUrl1 ? [idCardUrl1] : [] } onChange = { ( file, uploader ) => this.changeHandleByFile( "idCardUrl1", file, uploader ) } onError = { e => this.uploadError( e )} maxCount = { 1 } title = { "点击上传证件正面照片" }/>
								</MediaBox>
								<MediaBox>
									<Uploader name = "id_card2" className = { idCardUrl2 && "hideupload"} onFileClick = { ( e, file, i ) => this.handleFileClick(  "idCardUrl2", file ) } files = { idCardUrl2 ? [idCardUrl2] : [] } onChange = { ( file, uploader ) => this.changeHandleByFile( "idCardUrl2", file, uploader ) } onError = { e => this.uploadError( e )} maxCount = { 1 } title = { "点击上传证件背面照片" }/>
								</MediaBox>
								<MediaBox>
									<Uploader name = "id_card3" className = { idCardUrl3 && "hideupload"} onFileClick = { ( e, file, i ) => this.handleFileClick(  "idCardUrl3", file ) } files = { idCardUrl3 ? [idCardUrl3] : [] } onChange = { ( file, uploader ) => this.changeHandleByFile( "idCardUrl3", file, uploader ) } onError = { e => this.uploadError( e )} maxCount = { 1 } title = { "手持身份证自拍照" }/>
								</MediaBox>
							</PanelBody>
						</Panel>
					</Form>
				)
			}else{
				form = (
					<Form>
						<FormCell>
							<CellHeader><Label>单位名称</Label></CellHeader>
							<CellBody><Input value = { companyName } onChange = { e => changeHandle( "companyName", e ) } placeholder = "请输入单位名称" type = "text"/></CellBody>
						</FormCell>
						<FormCell>
							<CellHeader><Label>联系人姓名</Label></CellHeader>
							<CellBody><Input value = { realname } onChange = { e => changeHandle( "realname", e ) } placeholder = "请输入联系人姓名" type = "text"/></CellBody>
						</FormCell>
						<FormCell>
							<CellHeader><Label>单位地址</Label></CellHeader>
							<CellBody>
								<Flex>
									<FlexItem>
										<Input value = { companyProvince } readOnly placeholder = "省市" onClick = { () => this.togglePicker("province") } type = "text"/>
										{
											showProvincePicker && provinceData.length > 0 && <CityPicker dataMap = {{ id : "name", items : "items"}} animation = { false } show = { true } onChange = { ( text, selected, groups ) => this.changeHandleByPicker( "province", text, selected, groups ) } onCancel = { () => this.togglePicker("province") } data = { provinceData }/>
										}
									</FlexItem>
									<FlexItem>
										<Input value = { companyCity } readOnly placeholder = "市区" onClick = { () => this.togglePicker("city") } type = "text"/>
										{
											showCityPicker && cityData.length > 0 && <CityPicker dataMap = {{ id : "name", items : "items"}} animation = { false } show = { true } onChange = { ( text, selected, groups ) => this.changeHandleByPicker( "city", text, selected, groups ) } onCancel = { () => this.togglePicker("city") } data = { cityData }/>
										}
									</FlexItem>
									<FlexItem>
										<Input value = { companyArea } readOnly placeholder = "区域" onClick = { () => this.togglePicker("area") } type = "text"/>
										{
											showAreaPicker && areaData.length > 0 && <CityPicker dataMap = {{ id : "name", items : "items"}} animation = { false } show = { true } onChange = { ( text, selected, groups ) => this.changeHandleByPicker( "area", text, selected, groups ) } onCancel = { () => this.togglePicker("area") } data = { areaData }/>
										}
									</FlexItem>
								</Flex>
							</CellBody>
						</FormCell>
						<FormCell>
							<CellHeader><Label>详细地址</Label></CellHeader>
							<CellBody><Input value = { compnayAddr } onChange = { e => changeHandle( "compnayAddr", e ) } placeholder = "请输入单位详细地址" type = "text"/></CellBody>							
						</FormCell>
						<FormCell>
							<CellHeader><Label>社会统一信用代码</Label></CellHeader>
							<CellBody><Input value = { licenseCode } onChange = { e => changeHandle( "licenseCode", e ) } placeholder = "请输入内容" type = "text"/></CellBody>
						</FormCell>
						<Panel>
							<PanelBody>
								<MediaBox>
										<Uploader name = "bl_uploader" className = { busLicenseUrl && "hideupload"} onFileClick = { ( e, file  ) => this.handleFileClick( "busLicenseUrl", file ) } files = { busLicenseUrl ? [busLicenseUrl] : [] } onChange = { ( file, uploader ) => this.changeHandleByFile( "busLicenseUrl", file, uploader ) } onError = { e => this.uploadError( e )} maxCount = { 1 } title = { "点击上传营业执照" }/>
								</MediaBox>
							</PanelBody>
						</Panel>
					</Form>
				)
			}
		return (
			<div id="sign4_app">
				<form ref = "form">
					{ form }
					{ gallery && 
						<Gallery onClick = { () => this.hideGallery() } show = { showGallery } src = { gallery.url }>
							<GalleryDelete onClick = { () => this.removeFile( gallery.name ) }></GalleryDelete>
						</Gallery>
					}
				</form>
				{ showAgreement && <Agreement onClick = { agree => this.submitHandle( agree ) }/>}
				<ButtonArea>
					<Button onClick = { () => this.validParams() }>完成注册</Button>
				</ButtonArea>
			</div>
		)
	}
}
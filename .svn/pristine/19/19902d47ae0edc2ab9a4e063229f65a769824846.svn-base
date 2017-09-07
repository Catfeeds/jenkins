import React from 'react'
import Tool from 'utils/tool'
import { 
	Panel,
	PanelBody,
	MediaBox,
	MediaBoxDescription,
	MediaBoxHeader,
	MediaBoxBody,
	Uploader,
	Cells,
	FormCell,
	CellHeader,
	CellBody,
	Label,
	Input,
	Select,
	Gallery,
	GalleryDelete,
	ButtonArea,
	Button,
} from 'react-weui'
import checkCard from 'utils/valid-card'
import { genders, auth_status } from 'common/constant'
import Base64Auth2Image from 'images/user/auth_2'
import Base64Auth3Image from 'images/user/auth_3'
import Base64Auth4Image from 'images/user/auth_4'
import "styles/user/auth.less"

export default class UserCenterApp extends React.Component{
	constructor(props) {
		super(props);

		this.state = {
			perInfo : {
				name : "",
				sex : genders[0].value,
				idCard : "",
				birthday : "",
			},
			comInfo : {
				companyName : "",
				licenseNumber : "",
				companyLegal : "",
			}, 
			idCardUrl1 : "",
			idCardUrl2 : "",
			idCardUrl3 : "",
			licensePic : "",
			showGallery : false,
			gallery : {},
			authStatus : 0,
			reason : "",
			type : 1,
		};
	}
	componentDidMount(){
		Tool.get(`${ config.ajaxPath }/user/auth`)
		.then( json => {
			let type = parseInt( this.props.match.params.type ),
				{
					authInfo,
					reason,
					authStatus,
				} = json.data,
				{
					name,
					sex,
					idCard,
					birthday,
					idcardPic1,
					idcardPic2,
					idcardPic3,
					companyName,
					licenseNumber,
					companyLegal,
					licensePic,
				}= authInfo;

			if( type === 1 ){
				this.setState({
					perInfo : {
						name : name,
						sex : sex,
						idCard :  idCard,
						birthday :  birthday,
					},
					idCardUrl1 : idcardPic1 ? { url : idcardPic1 } : "",
					idCardUrl2 : idcardPic2 ? { url : idcardPic2 } : "",
					idCardUrl3 : idcardPic3 ? { url : idcardPic3 } : "",
					type,
					reason : reason,
					authStatus : authStatus,
				})
			}else if( type === 2 ){
				this.setState({
					comInfo : {
						companyName : companyName,
						licenseNumber : licenseNumber,
						companyLegal : companyLegal,
					},
					licensePic : licensePic ? { url : licensePic } : "",
					type,
					reason : reason,
					authStatus : authStatus,
				})
			}
		})
	}
	handleChange( name, e, type ){
		let value,
			valid = true;
		if( !!e && !!e.target ){
			e.persist();
			value = e.target.value
		}else value = e
		switch( type ){
			case "number":
				valid = RegxOnlyNumber.test( value )
				valid && value && ( value = parseInt( value ) )
			break;
		}
		valid && this.setState( Tool.updateIn( this.state, name, value ) )
	}
	handleChangeByFile( name, file, uploader ){
		switch( name ){
			case "licensePic":
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
		this.setState(Tool.updateIn( this.state, name, { url : file.data } ))
	}
	uploadError( e ){
		Tool.alert("只能上传一张图片哦");
	}
	handleFileClick( name, file ){
		this.setState( { showGallery : true, gallery : { url : file.url, name } } );
	}
	removeFile( name ){
		this.setState( Tool.updateIn( Tool.updateIn( this.state, name, "" ), "showGallery", false ) );
	}
	hideGallery(){
		setTimeout(()=>{
			if( this.state.showGallery )
				this.setState( Object.assign( {}, this.state, { showGallery : false } ) )
		},100)
	}
	validParams(){
		let {
			showGallery,
			gallery,
			perInfo,
			comInfo,
			type,
			authStatus,
			idCardUrl1,
			idCardUrl2,
			idCardUrl3,
			licensePic,
		} = this.state,
		{
			name,
			sex,
			idCard,
			birthday,
		} = perInfo,
		{
			companyName,
			licenseNumber,
			companyLegal,
		} = comInfo,
		message;
		if( type === 1 ){
			message = (function(){
				if( !/^\S+$/.test( name ) ) return "姓名输入错误"
				if( !/^\S+$/.test( birthday ) ) return "出生日期输入错误"
				if( !/^\S+$/.test( idCard ) || !checkCard( idCard ) ) return "身份证输入错误"
				if( !idCardUrl1 ) return "请输入上传证件正面照片"
				if( !idCardUrl2 ) return "请输入上传证件背面照片"
				if( !idCardUrl3 ) return "请输入上传手持身份证自拍照"
			})()
		}else if( type === 2 ){
			message = (function(){
				if( !/^\S+$/.test( companyName ) ) return "单位名称输入错误"
				if( !/^\S+$/.test( companyLegal ) ) return "公司法人输入错误"
				if( !/^\S+$/.test( licenseNumber ) ) return "社会统一信用代码输入错误"
				if( !licensePic ) return "请输入上传营业执照"
			})()
		}
		if( message ) return Tool.alert( message )
		this.submitHandle()
	}
	submitHandle(){
		let {
			showGallery,
			gallery,
			perInfo,
			comInfo,
			type,
			authStatus,
			idCardUrl1,
			idCardUrl2,
			idCardUrl3,
			licensePic,
		} = this.state,
		{
			name,
			sex,
			idCard,
			birthday,
		} = perInfo,
		{
			companyName,
			licenseNumber,
			companyLegal,
		} = comInfo,
		url = `${ config.ajaxPath }/oss/uploadFile`,
		promise,
		_idcard1Url,
		_idcard2Url,
		_idcard3Url;
		if( type === 2 ){
			promise = licensePic && this.bl_uploader ? Tool.post( url, { file : this.bl_uploader }) : new Promise( resolve => resolve( { data : [ { fullUrl: licensePic.url  } ] } ))
			.then( json => {
				Tool.fetch("put", `${ config.ajaxPath }/user/com/auth`,{
					companyName,
					companyLegal,
					licenseNumber,
					licensePic : json.data[0].fullUrl,
				})
		})
		// 个人注册
		}else if( type === 1 ){
			
			promise = idCardUrl1 && this.idc1_uploader ? Tool.post( url, { file : this.idc1_uploader }) : new Promise( resolve => resolve( { data : [ { fullUrl: idCardUrl1.url  } ] } ))

			promise.then( json => {
				_idcard1Url = json.data[0].fullUrl;
				// return Tool.post( url, { file : this.idc2_uploader }) 
				return idCardUrl2 && this.idc2_uploader ? Tool.post( url, { file : this.idc2_uploader }) : new Promise( resolve => resolve( { data : [ { fullUrl: idCardUrl2.url  } ] } ))
			})
			.then( json => {
				_idcard2Url = json.data[0].fullUrl;
				// return Tool.post( url, { file : this.idc3_uploader }) 
				return idCardUrl3 && this.idc3_uploader ? Tool.post( url, { file : this.idc3_uploader }) : new Promise( resolve => resolve( { data : [ { fullUrl: idCardUrl3.url  } ] } ))
			})
			.then( json => {
				_idcard3Url = json.data[0].fullUrl;
			})
			.then( () => Tool.fetch("put", `${ config.ajaxPath }/user/per/auth`,{
				birthday,
				name,
				sex,
				idCard,
				idcardPic1 : _idcard1Url,
				idcardPic2 : _idcard2Url,
				idcardPic3 : _idcard3Url,
			}))
			.catch( e => console.info( e ))
		}
	}
	render(){
		let {
				showGallery,
				gallery,
				perInfo,
				comInfo,
				type,
				reason,
				authStatus,
				licensePic,
				idCardUrl1,
				idCardUrl2,
				idCardUrl3,
			} = this.state,
			{
				name,
				sex,
				idCard,
				birthday,
			} = perInfo,
			{
				companyName,
				licenseNumber,
				companyLegal,
			} = comInfo,
			content,
			_authStatus,
			imageSource;

		// 个人用户
		if( type === 1 ){
			content = (
				<Cells>
					<FormCell>
						<CellHeader>
							<Label>姓名</Label>
						</CellHeader>
						<CellBody>
							<Input onChange = { e => this.handleChange( [ "perInfo","name" ], e ) } value = { name } placeholder = "请输入姓名"/>
						</CellBody>
					</FormCell>
					<FormCell select selectPos="after" className = "mutat">
						<CellHeader>
							<Label>性别</Label>
						</CellHeader>
						<CellBody>
							<Select data = { genders } value = { sex } onChange = { e => this.handleChange( [ "perInfo" , "sex" ], e ) }/>
						</CellBody>
					</FormCell>
					<FormCell>
						<CellHeader><Label>出日日期</Label></CellHeader>
						<CellBody><Input onChange = { e => this.handleChange( [ "perInfo" , "birthday" ], e ) } value = { perInfo.birthday || "" } type="date"/></CellBody>
					</FormCell>
					<FormCell>
						<CellHeader>
							<Label>身份证</Label>
						</CellHeader>
						<CellBody>
							<Input onChange = { e => this.handleChange( [ "perInfo","idCard" ], e ) } value = { idCard } placeholder = "请输入身份证号"/>
						</CellBody>
					</FormCell>
					<Panel>
						<PanelBody>
							<MediaBox>
								{
									authStatus !== 1 && authStatus !== 4 && <MediaBoxHeader style = {{ padding: "10px 0" }}>证件正面照片</MediaBoxHeader>
								}
								<MediaBoxBody>
									{
										( authStatus === 1 || authStatus === 4 ) ?
											<Uploader name = "id_card1" className = { idCardUrl1 && "hideupload"} onFileClick = { ( e, file, i ) => this.handleFileClick( "idCardUrl1" , file ) } files = { idCardUrl1 ? [idCardUrl1] : [] } onChange = { ( file, uploader ) => this.handleChangeByFile( "idCardUrl1", file, uploader ) } onError = { e => this.uploadError( e )} maxCount = { 1 } title = { "点击上传证件正面照片" }/>
										:
											<img style = {{ height: "80px" }} src = { idCardUrl1.url }/>
									}
								</MediaBoxBody>
							</MediaBox>
							<MediaBox>
								{
									authStatus !== 1 && authStatus !== 4 && <MediaBoxHeader style = {{ padding: "10px 0" }}>证件背面照片</MediaBoxHeader>
								}
								<MediaBoxBody>
									{
										( authStatus === 1 || authStatus === 4 ) ?
											<Uploader name = "id_card2" className = { idCardUrl2 && "hideupload"} onFileClick = { ( e, file, i ) => this.handleFileClick( "idCardUrl2" , file ) } files = { idCardUrl2 ? [idCardUrl2] : [] } onChange = { ( file, uploader ) => this.handleChangeByFile( "idCardUrl2", file, uploader ) } onError = { e => this.uploadError( e )} maxCount = { 1 } title = { "点击上传证件背面照片" }/>
										:
											<img style = {{ height: "80px" }} src = { idCardUrl2.url }/>
									}
								</MediaBoxBody>
							</MediaBox>
							<MediaBox>
								{
									authStatus !== 1 && authStatus !== 4 && <MediaBoxHeader style = {{ padding: "10px 0" }}>手持身份证自拍照</MediaBoxHeader>
								}
								<MediaBoxBody>
									{
										( authStatus === 1 || authStatus === 4 ) ?
											<Uploader name = "id_card3" className = { idCardUrl3 && "hideupload"} onFileClick = { ( e, file, i ) => this.handleFileClick( "idCardUrl3" , file ) } files = { idCardUrl3 ? [idCardUrl3] : [] } onChange = { ( file, uploader ) => this.handleChangeByFile( "idCardUrl3", file, uploader ) } onError = { e => this.uploadError( e )} maxCount = { 1 } title = { "点击上传手持身份证自拍照" }/>
										:
											<img style = {{ height: "80px" }} src = { idCardUrl3.url }/>
									}
								</MediaBoxBody>
							</MediaBox>
						</PanelBody>
					</Panel>
				</Cells>
			)
		//企业用户
		}else if( type === 2 ){
			content = (
				<Cells>
					<FormCell>
						<CellHeader>
							<Label>单位名称</Label>
						</CellHeader>
						<CellBody>
							<Input value = { companyName } onChange = { e => this.handleChange( [ "comInfo","companyName" ], e ) } placeholder = "请输入单位名称"/>
						</CellBody>
					</FormCell>
					<FormCell>
						<CellHeader>
							<Label>公司法人</Label>
						</CellHeader>
						<CellBody>
							<Input value = { companyLegal } onChange = { e => this.handleChange( [ "comInfo","companyLegal" ], e ) } placeholder = "请输入公司法人"/>
						</CellBody>
					</FormCell>
					<FormCell>
						<CellHeader>
							<Label>社会统一信用代码</Label>
						</CellHeader>
						<CellBody>
							<Input value = { licenseNumber } onChange = { e => this.handleChange( [ "comInfo","licenseNumber" ], e ) } placeholder = "请输入内容"/>
						</CellBody>
					</FormCell>
					<Panel>
						<PanelBody>
							<MediaBox>
								{
									authStatus !== 1 && authStatus !== 4 && <MediaBoxHeader style = {{ padding: "10px 0" }}>营业执照</MediaBoxHeader>
								}
								<MediaBoxBody>
									{
										( authStatus === 1 || authStatus === 4 ) ?
											<Uploader name = "bl_uploader" className = { licensePic && "hideupload"} onFileClick = { ( e, file  ) => this.handleFileClick( "licensePic", file ) } files = { licensePic ? [licensePic] : [] } onChange = { ( file, uploader ) => this.handleChangeByFile( "licensePic", file, uploader ) } onError = { e => this.uploadError( e )} maxCount = { 1 } title = { "点击上传营业执照" }/>
										:
											<img style = {{ height: "80px" }} src = { licensePic.url }/>
									}
								</MediaBoxBody>
							</MediaBox>
						</PanelBody>
					</Panel>
				</Cells>
			)
		}

		switch( authStatus ){
			case 1:
				// _authStatus = <span>{ auth_status[authStatus] }</span>
				break;
			case 2:
				// _authStatus = <span style = {{ color : "#31b0d5"}}>{ auth_status[authStatus] }</span>
				imageSource = Base64Auth2Image
				break;
			case 3:
				// _authStatus = <span style = {{ color : "#5cb85c"}}>{ auth_status[authStatus] }</span>
				imageSource = Base64Auth3Image
				break;
			case 4:
				// _authStatus = <span style = {{ color : "#e64340"}}>{ auth_status[authStatus] }</span>
				imageSource = Base64Auth4Image
				break;
		}
		return (
			<div id="user_auth_app">
				{
					authStatus !== 1 && <Panel>
							<PanelBody>
								<MediaBox>
									<MediaBoxHeader style = {{ textAlign : "center" }}>
										<img style = {{ width: "100px" }} src={ imageSource } />
									</MediaBoxHeader>
									{/*<MediaBoxBody style = {{ textAlign : "center" }}>
										{ _authStatus }
									</MediaBoxBody>*/}
									{ authStatus === 4 && <MediaBoxDescription style = {{ textAlign : "center", color : "#e64340", marginTop : 3 }}>
											失败原因：{ reason }
										</MediaBoxDescription>
									}
								</MediaBox>
							</PanelBody>
						</Panel>
					}
				{ content }
				{ gallery && 
					<Gallery onClick = { () => this.hideGallery() } show = { showGallery } src = { gallery.url }>
						<GalleryDelete onClick = { () => this.removeFile( gallery.name ) }></GalleryDelete>
					</Gallery>
				}
				{
					( authStatus === 1 || authStatus === 4 ) && <ButtonArea>
						<Button onClick = { () => this.validParams() }>提交认证</Button>
					</ButtonArea>
				}
			</div>
		)
	}
}
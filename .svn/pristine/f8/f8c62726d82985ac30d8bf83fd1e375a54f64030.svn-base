import React from 'react'
import Tool from 'utils/tool'
import { Uploader, Gallery, GalleryDelete, Panel, PanelHeader, PanelBody, MediaBoxDescription, MediaBox, MediaBoxHeader, MediaBoxBody, Form, CellsTitle, Cells, Button, ButtonArea, FormCell, CellHeader, CellBody, CellFooter, Label, Input, Flex, FlexItem, Select, TextArea, Icon } from 'react-weui'
import { planeTypes, projectTypes } from 'common/constant'
import CityPicker from 'common/weui/city-picker'
import { loadLanding, loadAirspace, handleChange, resetForm } from './action'
import { connect } from 'react-redux'
import "styles/airspace/apply.less"
let RegxOnlyNumber = /^\d{0,}$/,
	flyrules = [
		{
			label : "目视飞行",
			value : 0
		},
		{
			label : "仪表飞行",
			value : 1
		}
	]

class AirspaceApplyApp extends React.Component{
	constructor(props) {
		super(props);
		this.tomorrow = this.getTomorrow();
		this.state = {
			gallery : null,
			showGallery : false,
		};
	}
	getTomorrow(){
		let date = new Date(),
			month,
			day;
		date.setTime( date.getTime() + 1 * 24 * 60 * 60 * 1000 )
		month = ( month = date.getMonth() + 1 ) > 9 ? month : `0${ month }`
		day = ( day = date.getDate() ) > 9 ? day : `0${ day }`
		return `${ date.getFullYear() }-${ month }-${ day }`;
	}
	componentDidMount(){
		Tool.get(`${ config.ajaxPath }/user/info`)
		.then( json => {
			if( json.data.authStatus !== 3 ){
	            Tool.alert(`用户未通过认证，无法使用空域申请功能` )
	            Tool.to(`/user/center`)
			}else Tool.alert("此模块暂时未开放")
		})
		let {
			asData,
			landingData,
			planes,
			dispatch,
		} = this.props;
		if( planes.length === 0 ) dispatch( handleChange( "planes", planeTypes.slice() ) ) 

		if( asData.length === 0 ) dispatch( loadAirspace() )
			
		if( landingData.length === 0 ) dispatch( loadLanding() )
	}
	handleChangeByPlaneType( index, e ){
		e.persist();
		let {
				planes,
				dispatch,
			} = this.props,
			value = e.target.value;
		if( !RegxOnlyNumber.test( value ) ) return;

		let number = e.target.value;
		let newPlanes = planes.slice();
		newPlanes[index] = Object.assign({}, newPlanes[index], { number : parseInt( number ) });
		dispatch( handleChange( "planes", newPlanes ) )
		// this.setState( Object.assign( {}, this.state, { planeTypes } ) )
	}
	handleChange( name, e, type ){
		let value = !!e && !!e.target ? e.target.value : e,
			valid = true,
			dispatch = this.props.dispatch;
		e.persist();
		switch( type ){
			case "number":
				valid = RegxOnlyNumber.test( value )
				valid && value && ( value = parseInt( value ) )
			break;
		}
		valid && dispatch( handleChange( name, value ) )
		// valid && this.setState( Object.assign( {}, this.state, { [name] : value } ) )
	}
	projectTypeSelectHandle( index ){
		this.setState( Object.assign( {}, this.state, { projectType : index } ) )
	}
	togglePicker( name ){
		this.setState( Object.assign( {}, this.state, { [name] : !this.state[name] } ) )
	}
	submitHandle(){
		let {
				asData,
				landingData,
				projectType,
				projectDesc,
				planes,
				planeOtherName,
				beginTime,
				endTime,
				flyRule,
				airInfo,
				landingInfo,
				companyContactName,
				companyContactPhone,
				jjContactName,
				jjContactPhone,
				attachInfo,
				remark,
				dispatch,
			} = this.props,
			projectTypeStr = projectType > -1 ? projectTypes[3][projectType].label : "",
			_planes = (function(){
				let array = []
				planes.forEach( v => {
					if( !v.number ) return;
					if( v.name === '其他' ) return array.push( Object.assign( {}, v, { name : `其他:${ planeOtherName }`, number : v.number } ) )
					array.push( Object.assign( {}, v, { number : v.number } ) )
				})
				return array;
			})(),
			_airInfo = (function(){
				return airInfo.map( v => {
					if( !v.radius ){
						return {
							centers : v.centers.map( v => ({ lng : v.lng.join(','), lat : v.lat.join(',')})),
							high : v.high,
							location : v.location,
							// radius : v.radius,
							scopeType : v.scopeType,
						}
					}else{
						return {
							circleScope : {
								lng : v.centers[0].lng.join(','),
								lat : v.centers[0].lat.join(','),
								radius : v.radius,
							},
							high : v.high,
							location : v.location,
							scopeType : v.scopeType,
						}
					}
				})
				
			})(),
			_landingInfo = (function(){
				var obj = {
					master : landingInfo[0].id != "-1" ? landingInfo[0] : Object.assign({},landingInfo[0],{ lng : landingInfo[0].lng.join(','), lat : landingInfo[0].lat.join(',') }) ,
					// slave : landingInfo[1],
				}
				if( landingInfo[1].id == "-1" && /^\d+\,\d+\,\d+$/.test( landingInfo[1].lng.join(',') ) ){
					obj.slave = Object.assign({},landingInfo[1],{ lng : landingInfo[1].lng.join(','), lat : landingInfo[1].lat.join(',') })
				}
				if( landingInfo[1].id != "-1" ) obj.slave = landingInfo[1]
				return obj;
			})(),
			params = {
				planes : _planes,
				planType : projectTypeStr === "其他" ? `其他:${ projectDesc }` : projectTypes[3][projectType].label,
				beginTime,
				endTime,
				flyRule : flyrules[flyRule].label,
				landingInfo : _landingInfo,
				airInfo : _airInfo,
				contactInfo : {
				    unitContactName : companyContactName,
				    unitContact : companyContactPhone,
				    emergencyContactName : jjContactName,
				    emergencyContactPhone : jjContactPhone,
				},
				attachInfo,
				remark,
			};
		let message = (function(){
			if( params.landingInfo.master.id == "-1" && !/^\d+\,\d+\,\d+$/.test( params.landingInfo.master.lng ) ) return "请选择主起降场"
			// if( params.landingInfo.slave.id == "-1" &&  !/^\d+\,\d+\,\d+$/.test( params.landingInfo.slave.lng ) ) return "请选择备起降场"
			if( projectTypes[3][projectType].label === "其他" && !/^其他:\S+$/.test( params.planType ) ) return "请输入其他计划类型描述"
			if( params.planes.length === 0 ) return "请输入需要备案的机型数量"
			for( let i = params.planes.length;i--;){
				if( !params.planes[i].number || !/^\d+$/.test( params.planes[i].number ) )
					return `机型数量不能为${params.planes[i].number}`
				if( /^其他:$/.test( params.planes[i].name ) && !/^其他:\S+$/.test( params.planes[i].name ) )
					return "其他机型输入错误"
			}
			// if( !startDate ) return "请选择使用日期"
			if( !beginTime ) return "请选择开始日期"
			if( !endTime ) return "请选择结束日期"
			if( !params.flyRule ) return "请选择飞行规则"
			if( airInfo.length === 0 ) return "请选择空域及范围"
			if( !companyContactName )
				return "请输入单位联系人"
			if( !companyContactPhone || !/^1\d{10}$/.test( companyContactPhone ) )
				return "单位联系人手机号码错误"
			if( !attachInfo.url )
				return "请上传有效证件拍照"
			if( !jjContactName )
				return "请输入紧急联系人"
			if( !jjContactPhone || !/^1\d{10}$/.test( jjContactPhone ) )
				return "紧急联系人手机号码错误"
			if( jjContactName === companyContactName )
				return "紧急联系人与单位联系人重复"
			if( jjContactPhone === companyContactPhone )
				return "紧急联系人手机号码与单位联系人手机号码重复"
		})();
		if( message )
			return Tool.alert( message )
		Tool.post(`${ config.ajaxPath }/airplan`, params, 3 )
		.then( json => {
			dispatch( resetForm() )
			Tool.to('/airspace/apply-form/success')
		})
	}
	removeAirspace( index ){
		let {
			airInfo,
			dispatch,
		} = this.props,
		_airInfo = airInfo.slice();
		_airInfo.splice(index,1)
		dispatch( handleChange( "airInfo", _airInfo ) )
	}
	changeHandleByFile( file, uploader ){
		Tool.post( `${ config.ajaxPath }/oss/uploadFile`, { file : uploader.files[0] })
		.then( json => this.props.dispatch( handleChange( [ "attachInfo", "url" ], json.data[0].fullUrl ) ) )
	}
	uploadError( e ){
		Tool.alert("只能上传一张图片哦");
	}
	handleFileClick( file ){
		this.setState( Object.assign( {}, this.state, { showGallery : true, gallery : { url : file.url } } ) );
	}
	removeFile(){
		this.props.dispatch( handleChange( [ "attachInfo", "url" ], "" ) )
		// this.setState( Object.assign( {}, this.state, { showGallery : false, [name] : "" } ) );
	}
	hideGallery(){
		setTimeout(()=>{
			if( this.state.showGallery )
				this.setState( Object.assign( {}, this.state, { showGallery : false } ) )
		},100)
	}
	render(){
		let {
				showGallery,
				gallery,
			} = this.state,
			{
				asData,
				landingData,
				projectType,
				projectDesc,
				planes,
				planeOtherName,
				beginTime,
				endTime,
				flyRule,
				airInfo,
				landingInfo,
				companyContactName,
				companyContactPhone,
				jjContactName,
				jjContactPhone,
				remark,
				attachInfo,
			} = this.props,
			projectTypeStr = projectType > -1 ? projectTypes[3][projectType].label : "";

		return (
			<div id="air_apply_app">
				{ gallery && 
					<Gallery onClick = { () => this.hideGallery() } show = { showGallery } src = { gallery.url }>
						<GalleryDelete onClick = { () => this.removeFile( gallery.name ) }></GalleryDelete>
					</Gallery>
				}
				<Cells>
					<FormCell select selectPos = "after">
						<CellHeader>
							<Label>计划类型</Label>
						</CellHeader>
						<CellBody>
							<Select value = { projectType } onChange = { e => this.handleChange( "projectType", e, "number" ) } data = { projectTypes[3] }/>
						</CellBody>
					</FormCell>
					{
						projectTypeStr === "其他" && (
							<FormCell>
								<CellBody>
									<Input placeholder = "请输入类型描述" value = { projectDesc } onChange = { e => this.handleChange( "projectDesc", e ) } type = "text" />
								</CellBody>
							</FormCell>
						)
					}
					<FormCell style = {{ padding : "0 15px" }}>
						<CellHeader>
							<Label>使用机型</Label>
						</CellHeader>
						<CellBody>
							{
								Array.isArray( planes ) && planes.map( ( v, k ) => {
									return (
										<FormCell key = { `pt_${k}` } className = "plane_type">
											<CellBody>
												<Flex>
													<FlexItem>
														{
															v.name === '其他' ? <Input value = { planeOtherName } onChange = { e => this.handleChange( "planeOtherName", e ) } placeholder = "其他机型"/> : <Label>{ v.name }</Label>
														}
													</FlexItem>
													<FlexItem>
														<Input value = { v.number || "" } maxLength = { 2 } placeholder = "0" onChange = { e => this.handleChangeByPlaneType( k, e )}/>
													</FlexItem>
												</Flex>
											</CellBody>
										</FormCell>
									)
								})
							}
						</CellBody>
					</FormCell>
					<FormCell>
						<CellHeader>
							<Label>开始日期</Label>
						</CellHeader>
						<CellBody>
							<Input min = { this.tomorrow } value = { beginTime } type = "date" onChange = { e => this.handleChange( "beginTime", e ) }/>
						</CellBody>
					</FormCell>
					<FormCell>
						<CellHeader>
							<Label>结束日期</Label>
						</CellHeader>
						<CellBody>
							<Input min = { beginTime || this.tomorrow } value = { endTime } type = "date" onChange = { e => this.handleChange( "endTime", e ) }/>
						</CellBody>
					</FormCell>
					<FormCell select selectPos = "after">
						<CellHeader>
							<Label>飞行规则</Label>
						</CellHeader>
						<CellBody>
							<Select value = { flyRule } onChange = { e => this.handleChange( "flyRule", e, "number" ) } data = { flyrules }/>
						</CellBody>
					</FormCell>
				</Cells>
				<CellsTitle>空域及范围</CellsTitle>
				<Cells>
					{
						Array.isArray( airInfo ) && airInfo.map( ( v, k ) => {
							return (
								<Panel key = { `air_info_${ k }`} className = "list_panel blue_block">
									<PanelBody>
										<MediaBox type = "appmsg" onClick = { () => Tool.to(`/airspace/add-airspace/${ k }`)}>
											<MediaBoxHeader>{ k + 1 }</MediaBoxHeader>
											<MediaBoxBody>
												<CellBody>{ v.location }</CellBody>
												<CellBody>真高{ v.high }米</CellBody>
											</MediaBoxBody>
										</MediaBox>
										<Icon onClick = { () => this.removeAirspace( k )} className = "del_icon" value="cancel"/>
									</PanelBody>
								</Panel>
							)
						})
					}
					<Button onClick = { () => Tool.to('/airspace/add-airspace')} className = "add_btn" type = "default">+&nbsp;添加空域</Button>
				</Cells>
				<CellsTitle>使用起降场</CellsTitle>
				<Cells>
					<Panel className = "list_panel red_block">
						<PanelBody onClick = { () => Tool.to(`/airspace/add-landing/0`)}>
							<MediaBox type = "appmsg" >
								<MediaBoxHeader>主</MediaBoxHeader>
								<MediaBoxBody>
									<CellBody style = {{ color : "#999" }}>{ landingInfo[0].name }</CellBody>
									<CellBody>{ landingInfo[0].location }</CellBody>
									<CellBody>{ landingInfo[0].name ? ( landingInfo[0].id == -1 ? `坐标 ${ landingInfo[0].lng }，${ landingInfo[0].lat }` : "" ) : "请选择起降场" }</CellBody>
								</MediaBoxBody>
							</MediaBox>
						</PanelBody>
					</Panel>
					<Panel className = "list_panel red_block">
						<PanelBody onClick = { () => Tool.to(`/airspace/add-landing/1`)}>
							<MediaBox type = "appmsg" >
								<MediaBoxHeader>备</MediaBoxHeader>
								<MediaBoxBody>
									<CellBody style = {{ color : "#999" }}>{ landingInfo[1].name }</CellBody>
									<CellBody>{ landingInfo[1].location }</CellBody>
									<CellBody>{ landingInfo[1].name ? ( landingInfo[1].id == -1 ? `坐标 ${ landingInfo[1].lng }，${ landingInfo[1].lat }` : "" ) : "请选择起降场" }</CellBody>
									{/*<CellBody>{ landingInfo[1].name ? `坐标 ${ landingInfo[1].lng }，${ landingInfo[1].lat }` : "请选择起降场" }</CellBody>*/}
								</MediaBoxBody>
							</MediaBox>
						</PanelBody>
					</Panel>
				</Cells>
				<CellsTitle>操控员或负责人信息</CellsTitle>
				<Cells>
					<FormCell>
						<CellHeader>
							<Label>单位联系人</Label>
						</CellHeader>
						<CellBody>
							<Input value = { companyContactName } placeholder = "请输入单位联系人" onChange = { e => this.handleChange( "companyContactName", e ) } type = "text"/>
						</CellBody>
					</FormCell>
					<FormCell>
						<CellHeader>
							<Label>手机号码</Label>
						</CellHeader>
						<CellBody>
							<Input value = { companyContactPhone } placeholder = "请输入单位联系人手机号码" maxLength = { 11 } onChange = { e => this.handleChange( "companyContactPhone", e, "number" ) } type = "text"/>
						</CellBody>
					</FormCell>
				</Cells>
				<Panel>
					<PanelBody>
						<MediaBox>
							<Uploader name = "uploader"
								className = { !!attachInfo.url && "hideupload"}
								onFileClick = { ( e, file  ) => this.handleFileClick( file ) } 
								files = { attachInfo.url ? [{ url : attachInfo.url }] : [] } 
								onChange = { ( file, uploader ) => this.changeHandleByFile( file, uploader ) } 
								onError = { e => this.uploadError( e )} maxCount = { 1 } title = { "上传有效证件拍照" }/>
						</MediaBox>
					</PanelBody>
					<PanelBody>
						<MediaBox>
							<MediaBoxDescription>内容包括：委托书、申请函、承诺书、营业执照等正面照片，大小不超过10MB</MediaBoxDescription>
						</MediaBox>
					</PanelBody>
				</Panel>

				<CellsTitle>紧急联系人信息</CellsTitle>
				<Cells>
					<FormCell>
						<CellHeader>
							<Label>联系人</Label>
						</CellHeader>
						<CellBody>
							<Input value = { jjContactName } placeholder = "请输入紧急联系人" onChange = { e => this.handleChange( "jjContactName", e ) } type = "text"/>
						</CellBody>
					</FormCell>
					<FormCell>
						<CellHeader>
							<Label>手机号码</Label>
						</CellHeader>
						<CellBody>
							<Input value = { jjContactPhone } placeholder = "请输入紧急联系人手机号码" maxLength = { 11 } onChange = { e => this.handleChange( "jjContactPhone", e, "number" ) } type = "text"/>
						</CellBody>
					</FormCell>
				</Cells>

				<CellsTitle>其他信息</CellsTitle>
				<Cells>
					<FormCell>
						<CellBody>
							<TextArea placeholder = "其他说明事项" value = { remark } onChange = { e => this.handleChange( "remark", e ) } maxlength = { 200 }/>
						</CellBody>
					</FormCell>
				</Cells>
				<ButtonArea>
					<Button disabled type = "primary">提交</Button>
					{/*<Button onClick = { () => this.submitHandle() } type = "primary">提交</Button>*/}
				</ButtonArea>
			</div>
		)
	}
}

export default connect( state => ({...state.airApply}))(AirspaceApplyApp)
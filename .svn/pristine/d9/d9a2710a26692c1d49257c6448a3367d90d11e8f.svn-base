import React from 'react'
import Tool from 'utils/tool'
import { Panel, PanelHeader, PanelBody, MediaBox, MediaBoxHeader, MediaBoxBody, Form, CellsTitle, Cells, Button, ButtonArea, FormCell, CellHeader, CellBody, CellFooter, Label, Input, Flex, FlexItem, Select, TextArea, Icon } from 'react-weui'
import { planeTypes, projectTypes, timeList } from 'common/constant'
import CityPicker from 'common/weui/city-picker'
import { loadLanding, loadAirspace, handleChange, resetForm } from './action'
import { connect } from 'react-redux'
let RegxOnlyNumber = /^\d{0,}$/

class Form3 extends React.Component{
	constructor(props) {
		super(props);
		this.__temp_obj;
		this.__temp_picker_obj;
		this.tomorrow = props.getTomorrow();
		this.state = {
			showASPicker : false,
			showLPicker : false,
			showSTPicker : false,
			showETPicker :false,
			// asData : [],
			// landingData : [],
		};
	}
	componentDidMount(){
		// alert("此模块暂时未开放")
		let {
			asData,
			landingData,
			planes,
			dispatch,
			startDate,
		} = this.props;
		if( !startDate ) dispatch( handleChange( "startDate", this.tomorrow ) )
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
	handleChangeByLL( name, e ){
		e.persist();

		if( !RegxOnlyNumber.test( e.target.value ) ) return;

		let lng = this.state.lng.slice(),
			lat = this.state.lat.slice(),
			value = e.target.value;

		switch( name ){
			case "lng0":
				lng[0] = value;
			break;
			case "lng1":
				lng[1] = value;
			break;
			case "lng2":
				lng[2] = value;
			break;
			case "lat0":
				lat[0] = value;
			break;
			case "lat1":
				lat[1] = value;
			break;
			case "lat2":
				lat[2] = value;
			break;
		}
		this.setState( Object.assign( {}, this.state, { lng, lat } ) )
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
				applyOfficialNo,
				planes,
				planeOtherName,
				startDate,
				startTime,
				endTime,
				airInfo,
				landingInfo,
				companyContactName,
				companyContactPhone,
				fieldContactName,
				fieldContactPhone,
				jjContactName,
				jjContactPhone,
				weatherCondition,
				remark,
				dispatch,
			} = this.props,
			startTimeStr = startTime ? ( timeList[startTime[0]].value + ":" + timeList[startTime[0]].items[startTime[1]].value ) : "",
			endTimeStr = endTime ? ( timeList[endTime[0]].value + ":" + timeList[endTime[0]].items[endTime[1]].value ) : "",
			projectTypeStr = projectType > -1 ? projectTypes[0][projectType].label : "",
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
			_landingInfo = landingInfo.map( v => (v.id != "-1" ? v : {
				id : v.id,
				name : v.name,
				location : v.location,
				lng : v.lng.join(','),
				lat : v.lat.join(','),
			})),
			params = {
				type : 0,
				planes : _planes,
				planType : projectTypeStr === "其他" ? `其他:${ projectDesc }` : projectTypes[0][projectType].label,
				beginTime : startDate + " " + startTimeStr,
				endTime : startDate + " " + endTimeStr,
				landingInfo : _landingInfo,
				airInfo : _airInfo,
				contactInfo : {
				    emergencyContactName : jjContactName,
				    emergencyContactPhone : jjContactPhone,
					fieldContactName :  fieldContactName,
				    fieldContactPhone : fieldContactPhone,
				    unitContact : companyContactName,
				    unitContactName : companyContactPhone,

				},
				applyOfficialNo,
				weatherCondition,
				remark,
			};
		planes
		let message = (function(){
			if( projectTypes[0][projectType].label === "其他" && !/^其他:\S+$/.test( params.planType ) ) return "请输入其他计划类型描述"
			if( params.planes.length === 0 ) return "请输入需要备案的机型数量"
			for( let i = params.planes.length;i--;){
				if( !params.planes[i].number || !/^\d+$/.test( params.planes[i].number ) )
					return `机型数量不能为${params.planes[i].number}`
				if( /^其他:$/.test( params.planes[i].name ) && !/^其他:\S+$/.test( params.planes[i].name ) )
					return "其他机型输入错误"
			}
			// if( !startDate ) return "请选择使用日期"
			if( !startTime ) return "请选择开始时间段"
			if( !endTime ) return "请选择结束时间段"
			if( !applyOfficialNo ) return "请输入空域批件号"
			if( airInfo.length === 0 ) return "请选择空域及范围"
			if( params.landingInfo.length === 0 ) return "请选择起降场"
			if( !companyContactName )
				return "请输入单位联系人"
			if( !companyContactPhone || !/^1\d{10}$/.test( companyContactPhone ) )
				return "单位联系人手机号码错误"
			if( !fieldContactName )
				return "请输入现场联系人"
			if( !fieldContactPhone || !/^1\d{10}$/.test( fieldContactPhone ) )
				return "现场联系人手机号码输入错误"
			if( !jjContactName )
				return '请输入紧急联系人'
			if( !jjContactPhone || !/^1\d{10}$/.test( jjContactPhone ) )
				return "紧急联系人手机号码输入错误"
			if( jjContactName == fieldContactName )
				return "紧急联系人与现场联系人重复"
			if( jjContactPhone == fieldContactPhone )
				return "紧急联系人手机号码与现场联系人手机号码重复"
		})();
		if( message )
			return Tool.alert( message )
		Tool.post(`${ config.ajaxPath }/flyplan`, params, 3 )
		.then( json => {
			dispatch( resetForm() )
			Tool.to('/apply-record-form/success')
		})
	}
	changeHandleByPicker( name, text, selected, groups ){
		let {
				startTime,
				endTime,
				dispatch,
			} = this.props,
			startTimeStr,
			endTimeStr;
		switch( name ){
			case "startTime":
				startTimeStr = groups[0].items[selected[0]].value + ":" + groups[1].items[selected[1]].value;
				endTimeStr = !endTime ? "" : ( timeList[ endTime[0] ].value + ":" + timeList[ endTime[0] ].items[ endTime[1] ].value )
				if( this.compareTime( startTimeStr, endTimeStr ) ){
					dispatch( handleChange( "startTime", selected.slice() ))
					this.setState( Object.assign( {}, this.state, {  showSTPicker : false, showETPicker : true } ) )
				}
				else
					Tool.alert("结束时间不能小于开始时间");
				break;
			case "endTime":
				startTimeStr = !startTime ? "" : ( groups[0].items[startTime[0]].value + ":" + groups[1].items[startTime[1]].value );
				endTimeStr = groups[0].items[selected[0]].value + ":" + groups[1].items[selected[1]].value;
				if( this.compareTime( startTimeStr, endTimeStr ) ){
					dispatch( handleChange( "endTime", selected.slice() ))
					this.setState( Object.assign( {}, this.state, { showETPicker : false } ) )
				}
				else Tool.alert("结束时间不能小于开始时间");
				break;
		}
	}
	compareTime( st, et ){
		if( !st || !et ) return true;
		let _st = st.split( ':' ),
			_et = et.split( ':' );
		return parseInt( _et[0] ) > parseInt( _st[0] ) || ( parseInt( _et[0] ) === parseInt( _st[0] ) && parseInt( _et[1] ) > parseInt( _st[1] ) )
	}
	removeLanding( index ){
		let {
			landingInfo,
			dispatch,
		} = this.props,
		_landingInfo = landingInfo.slice();
		_landingInfo.splice(index,1)
		dispatch( handleChange( "landingInfo", _landingInfo ) )
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
	render(){
		let {
				showASPicker,
				showLPicker,
				showSTPicker,
				showETPicker,
			} = this.state,
			{
				asData,
				landingData,
				projectType,
				projectDesc,
				applyOfficialNo,
				planes,
				planeOtherName,
				startDate,
				startTime,
				endTime,
				airInfo,
				landingInfo,
				companyContactName,
				companyContactPhone,
				fieldContactName,
				fieldContactPhone,
				jjContactName,
				jjContactPhone,
				weatherCondition,
				remark,
			} = this.props,
			startTimeStr = startTime ? ( timeList[startTime[0]].value + ":" + timeList[startTime[0]].items[startTime[1]].value ) : "",
			endTimeStr = endTime ? ( timeList[endTime[0]].value + ":" + timeList[endTime[0]].items[endTime[1]].value ) : "",
			projectTypeStr = projectType > -1 ? projectTypes[0][projectType].label : "";

		return (
			<div id="ar_form3">
				<Cells>
					<FormCell select selectPos = "after">
						<CellHeader>
							<Label>计划类型</Label>
						</CellHeader>
						<CellBody>
							<Select value = { projectType } onChange = { e => this.handleChange( "projectType", e, "number" ) } data = { projectTypes[0] }/>
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
					<FormCell>
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
							<Label>使用日期</Label>
						</CellHeader>
						<CellBody>
							{/*<Input min = { this.today } value = { startDate } onChange = { e => this.handleChange( "startDate", e ) } type = "date"/>*/}
							<Input value = { startDate } readOnly type = "text"/>
						</CellBody>
					</FormCell>
					<FormCell>
						<CellHeader>
							<Label>使用时间段</Label>
						</CellHeader>
						<CellBody>
							<Flex>
								<FlexItem>
									<Input value = { startTimeStr } readOnly onClick = { () => this.togglePicker("showSTPicker") } placeholder = "开始时间"/>
								</FlexItem>
								<FlexItem>
									<Input value = { endTimeStr } readOnly onClick = { () => this.togglePicker("showETPicker") } placeholder = "结束时间"/>
								</FlexItem>
							</Flex>
							<CityPicker animation = { false } show = { showSTPicker } onChange = { ( text, selected, groups ) => this.changeHandleByPicker( "startTime", text, selected, groups ) } onCancel = { () => this.togglePicker("showSTPicker") } data = { timeList }/>
							<CityPicker animation = { false } show = { showETPicker } onChange = { ( text, selected, groups ) => this.changeHandleByPicker( "endTime", text, selected, groups ) } onCancel = { () => this.togglePicker("showETPicker") } data = { timeList }/>
						</CellBody>
					</FormCell>
				</Cells>
				<CellsTitle>空域及范围</CellsTitle>
				<Cells>
					<FormCell>
						<CellHeader><Label>空域批件号</Label></CellHeader>
						<CellBody>
							<Input value = { applyOfficialNo } onChange = { e => this.handleChange( "applyOfficialNo", e ) } placeholder = "请输入空域批文编号" type = "text"/>
						</CellBody>
					</FormCell>
					{
						Array.isArray( airInfo ) && airInfo.map( ( v, k ) => {
							return (
								<Panel key = { `air_info_${ k }`} className = "list_panel blue_block">
									<PanelBody>
										<MediaBox type = "appmsg" onClick = { () => Tool.to(`/add-airspace/${ k }`)}>
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
					<Button onClick = { () => Tool.to('/add-airspace')} className = "add_btn" type = "default">+&nbsp;添加空域</Button>
				</Cells>
				<CellsTitle>使用起降场</CellsTitle>
				<Cells>
					{
						Array.isArray( landingInfo ) && landingInfo.map( ( v, k ) => {
							return (
								<Panel key = { `landing_info_${k}` } className = "list_panel red_block">
									<PanelBody>
										<MediaBox type = "appmsg" >
											<MediaBoxHeader onClick = { () => Tool.to(`/add-landing/${ k }`)}>{ k + 1 }</MediaBoxHeader>
											<MediaBoxBody>
												<CellBody style = {{ color : "#999" }}>{ v.name }</CellBody>
												<CellBody>{ v.location }</CellBody>
												{ v.id == -1 && <CellBody>坐标 { v.lng }，{ v.lat }</CellBody>}
											</MediaBoxBody>
										</MediaBox>
									</PanelBody>
									<Icon onClick = { () => this.removeLanding( k ) } className = "del_icon" value="cancel"/>
								</Panel>
							)
						})
					}
					<Button onClick = { () => Tool.to('/add-landing')} className = "add_btn" type = "default">+&nbsp;添加起降场</Button>
				</Cells>
				<CellsTitle>操控员/负责人信息</CellsTitle>
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
					<FormCell>
						<CellHeader>
							<Label>现场联系人</Label>
						</CellHeader>
						<CellBody>
							<Input value = { fieldContactName } placeholder = "请输入现场联系人" onChange = { e => this.handleChange( "fieldContactName", e ) } type = "text"/>
						</CellBody>
					</FormCell>
					<FormCell>
						<CellHeader>
							<Label>手机号码</Label>
						</CellHeader>
						<CellBody>
							<Input value = { fieldContactPhone } placeholder = "请输入现场联系人手机号码" maxLength = { 11 } onChange = { e => this.handleChange( "fieldContactPhone", e, "number" ) } type = "text"/>
						</CellBody>
					</FormCell>
				</Cells>
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
				<CellsTitle>其他</CellsTitle>
				<Cells>
					<FormCell>
						<CellHeader>
							<Label>气象条件</Label>
						</CellHeader>
						<CellBody>
							<Input value = { weatherCondition } placeholder = "请输入气象条件" maxLength = { 11 } onChange = { e => this.handleChange( "weatherCondition", e, "number" ) } type = "text"/>
						</CellBody>
					</FormCell>
					<FormCell>
						<CellHeader>
							<Label>其他说明事项</Label>
						</CellHeader>
						<CellBody>
							<TextArea placeholder = "其他说明事项" value = { remark } onChange = { e => this.handleChange( "remark", e ) } maxlength = { 200 }/>
						</CellBody>
					</FormCell>
				</Cells>
				<ButtonArea>
					{/*<Button disabled type = "primary">提交</Button>*/}
					<Button onClick = { () => this.submitHandle() } type = "primary">提交</Button>
				</ButtonArea>
			</div>
		)
	}
}

export default connect( state => ({...state.applyRecord}))(Form3)
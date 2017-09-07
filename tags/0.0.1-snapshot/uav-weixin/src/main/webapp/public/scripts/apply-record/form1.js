import React from 'react'
import Tool from 'utils/tool'
import { Form, CellsTitle, Cells, Cell, Button, ButtonArea, FormCell, CellHeader, CellBody, CellFooter, Label, Input, Flex, FlexItem, Select, TextArea } from 'react-weui'
import CityPicker from 'common/weui/city-picker'
import { planeTypes, projectTypes, timeList } from 'common/constant'
let RegxOnlyNumber = /^\d{0,}$/;

export default class Form1 extends React.Component{
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
			projectType : 0, //计划类型
			projectDesc : "",
			planeTypes : planeTypes.slice(),
			startDate : this.tomorrow,
			startTime : "",
			endTime : "",
			typeDesc : "", //计划类型说明
			captainName : "", //操控员姓名
			studentNumber : "", //学员数量
			contactName : "", //现场联系人
			contactPhone : "",//联系方式
			remark : "", //其他说明事项
			provinceCode : "",
			provinceName : "",
			cityCode : "",
			cityName : "",
			// areaCode : "",
			// areaName : "",
			location : "",
			high : "",
			airspace : "",
			airspaceId : 0,
			asData : [],
			locationData : [],
			landingData : [],
			landing : -1,
			jjcontactName : "", //紧急联系人
			jjcontactPhone : "", //紧急联系人手机号码
			weatherCondition : "",
			lfLocation : "",
			lng : ["","",""],
			lat : ["","",""],
			planeName : "",
		};
	}
	componentDidMount(){
		Tool.get(`${ config.ajaxPath }/metadata/airspace`,{ type : 1 })
		.then( json => {
			let asData = {};
			json.data.forEach( v => {
				if( asData[v.provinceCode] ){
					if( asData[v.provinceCode]["items"][v.cityCode] ){
						if( !asData[v.provinceCode]["items"][v.cityCode]["_items"][v.id] ){
							asData[v.provinceCode]["items"][v.cityCode]["_items"][v.id] = {
								break : true,
								name : v.location,
								high : v.high,
								scopeType : v.scopeType,
								id : v.id,
								text : `${ v.location }（真高：${ v.high }（含）以下）`
							}
						}
					}else{
						asData[v.provinceCode]["items"][v.cityCode] = {
							name : v.cityName,
							code : v.cityCode,
							_items : {
								[v.id] : {
									break : true,
									name : v.location,
									high : v.high,
									scopeType : v.scopeType,
									id : v.id,
									text : `${ v.location }（真高：${ v.high }（含）以下）`
								}
							}
						}
					}
				}else{
					asData[v.provinceCode] = {
							name : v.provinceName,
							code : v.provinceCode,
							items : {
								[v.cityCode] : {
									name : v.cityName,
									code : v.cityCode,
									_items : {
										[v.id] : {
											break : true,
											name : v.location,
											high : v.high,
											scopeType : v.scopeType,
											id : v.id,
											text : `${ v.location }（真高：${ v.high }（含）以下）`
										}
									}
								}
							}
						}
				}
			})
			function parse( obj ){
				let _obj,
					items = [];
				Object.keys(obj).forEach( key => {
					if( key === "items" || key === "items" )
						return;
					_obj = {
						name : obj[key].name,
						code : obj[key].code,
					};
					if( typeof obj[key].items === "object" && !obj[key].items.break ){
						_obj.items = parse( obj[key].items )
					}else if( typeof obj[key]._items === "object" )
						_obj._items = parse( obj[key]._items )
					else
						Object.assign( _obj, obj[key] )
					items.push(_obj);
				})
				return items;
			}
			this.setState( Object.assign( {}, this.state, { asData : parse( asData ) } ) )
		})
		Tool.get(`${ config.ajaxPath}/metadata/landings`)
		.then( json => this.setState( Object.assign( {}, this.state, { landingData : json.data.map( v => ({
			label : v.name,
			value : v.id,
			location : v.location,
			lat : v.lat,
			lng : v.lng,
		} ) ) } ) ) )
	}
	handleChangeByPlaneType( index, e ){
		e.persist();
		let value = e.target.value;
		if( !RegxOnlyNumber.test( value ) ) return;

		let number = e.target.value;
		let planeTypes = this.state.planeTypes.slice();
		planeTypes[index] = Object.assign({}, planeTypes[index], { number : parseInt( number ) });
		this.setState( Object.assign( {}, this.state, { planeTypes } ) )
	}
	handleChange( name, e, type ){
		let value = !!e && !!e.target ? e.target.value : e,
			valid = true;
		e.persist();
		switch( type ){
			case "number":
				valid = RegxOnlyNumber.test( value )
				valid && value && ( value = parseInt( value ) )
			break;
		}
		valid && this.setState( Object.assign( {}, this.state, { [name] : value } ) )
	}
	handleChangeByLL( name, e ){
		e.persist();

		if( !RegxOnlyNumber.test( e.target.value ) ) return;

		let lng = this.state.lng.slice(),
			lat = this.state.lat.slice(),
			value = parseInt( e.target.value ) || 0;

		switch( name ){
			case "lng0":
				lng[0] = value > 180 ? 180 : value;
			break;
			case "lng1":
				lng[1] = value > 60 ? 60 : value;
			break;
			case "lng2":
				lng[2] = value > 60 ? 60 : value;
			break;
			case "lat0":
				lat[0] = value > 90 ? 90 : value;
			break;
			case "lat1":
				lat[1] = value > 60 ? 60 : value;
			break;
			case "lat2":
				lat[2] = value > 60 ? 60 : value;
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
				landingData,
				planeTypes,
				projectType,
				projectDesc,
				startDate,
				startTime,
				endTime,
				landing,
				provinceCode,
				provinceName,
				cityCode,
				cityName,
				// areaCode,
				// areaName,
				airspaceL,
				airspaceHigh,
				airspaceId,
				airspaceScopeType,
				remark,
				captainName,
				contactName,
				contactPhone,
				studentNumber,
				jjcontactName,
				jjcontactPhone,
				weatherCondition,
				lfLocation,
				lng,
				lat,
				planeName,
			} = this.state,
			landingObj = (function(){
				let index = 0,
					len = landingData.length;
				while( len > index && landingData[ index ].value != landing ) index++;
				if( landing == -1 ){
					return Object.assign( {}, landingData[ index ], {
						location : lfLocation,
						lng : lng.join(','),
						lat : lat.join(','),
					})
				}
				return landingData[ index ];
			})(),
			planes = (function(){
				let array = []
				planeTypes.forEach( v => {
					if( !v.number ) return;
					if( v.name === '其他' ) return array.push( Object.assign( {}, v, { name : `其他:${ planeName }`, number : v.number } ) )
					array.push( Object.assign( {}, v, { number : v.number } ) )
				})
				return array;
			})(),
			params = {
				type : 1,
				planes,
				planType : projectTypes[1][projectType].label === "其他" ? `其他:${ projectDesc }` : projectTypes[1][projectType].label,
				beginTime : startDate + " " + startTime,
				endTime : startDate + " " + endTime,
				landingInfo : [
					{
						id : landingObj.value,
						name : landingObj.label,
						location : landingObj.location,
						lat : landingObj.lat,
						lng : landingObj.lng,
					}
				],
				airInfo : [
					{
						id : airspaceId,
						type : 1, //0-管制 1-一类 2二类
						provinceCode,
						provinceName,
						cityCode,
						cityName,
						// areaCode,
						// areaName,
						location : airspaceL,
						high : airspaceHigh,
						scopeType : airspaceScopeType,
						centers: [],
					}
				],
				contactInfo : {
				    "trainer": captainName,
				    "traineeCount":studentNumber,
				    "emergencyContactName":jjcontactName,
				    "emergencyContactPhone":jjcontactPhone,
					"fieldContactName": contactName,
				    "fieldContactPhone":contactPhone

				},
				weatherCondition,
				remark,
			};
		let message = (function(){
			if( projectTypes[1][projectType].label === "其他" && !/^其他:\S+$/.test( params.planType ) ) return "请输入其他计划类型描述"
			if( params.planes.length === 0 ) return "请输入需要备案的机型数量"
			for( let i = params.planes.length;i--;){
				if( !params.planes[i].number || !/^\d+$/.test( params.planes[i].number ) )
					return `机型数量不能为${params.planes[i].number}`
				if( /^其他:$/.test( params.planes[i].name ) && !/^其他:\S+$/.test( params.planes[i].name ) )
					return "其他机型输入错误"
			}
			if( !startDate ) return "请选择使用日期"
			if( !startTime ) return "请选择开始时间段"
			if( !endTime ) return "请选择结束时间段"
			if( !provinceCode || !cityCode || !airspaceL ) return "请选择计划空域"
			if( landing == -1 ){
				if( !params.landingInfo[0].location ) return "请输入起降场具体位置"
				if( !/^\d+\,\d+\,\d+$/.test( params.landingInfo[0].lng ) || !/^\d+\,\d+\,\d+$/.test( params.landingInfo[0].lat ) ) return "经纬度输入错误"
			}
			if( !captainName )
				return "请输入教练/飞行员姓名"
			if( projectTypes[1][projectType].label === "技能培训" && !studentNumber )
				return "学员数量输入错误"
			if( !contactName )
				return "请输入现场联系人"
			if( !contactPhone || !/^1\d{10}$/.test( contactPhone ) )
				return "现场联系人手机号码输入错误"
			if( !jjcontactName )
				return '请输入紧急联系人'
			if( !jjcontactPhone || !/^1\d{10}$/.test( jjcontactPhone ) )
				return "紧急联系人手机号码输入错误"
			if( jjcontactName == contactName )
				return "紧急联系人与现场联系人重复"
			if( jjcontactPhone == contactPhone )
				return "紧急联系人手机号码与现场联系人手机号码重复"
		})();
		if( message )
			return Tool.alert( message )
		Tool.post(`${ config.ajaxPath }/flyplan`, params, 3 )
		.then( json => {
			Tool.to('/apply-record-form/success')
			// Tool.to('/apply-record/success')
		})
	}
	changeHandleByPicker( name, text, selected, groups ){
		let startTime,
			endTime;
		switch( name ){
			case "city":
				text = "";
				text += groups[0]['items'][selected[0]].name
				text += groups[1]['items'][selected[1]].name
				// text += groups[2]['items'][selected[2]].name
				this.__temp_city = text;
				this.__temp_picker_obj = {
					provinceCode : groups[0]['items'][selected[0]].code,
					provinceName : groups[0]['items'][selected[0]].name,
					cityCode : groups[1]['items'][selected[1]].code,
					cityName : groups[1]['items'][selected[1]].name,
				};
				this.setState( Object.assign( {}, this.state, { 
					showLPicker : true, 
					locationData : groups[1]['items'][selected[1]]["_items"],
					showASPicker : !this.state.showASPicker,
				} ) )
				break;
			case "location":
				this.setState( Object.assign( {}, this.state, { 
					airspace : this.__temp_city + groups[0]["items"][selected[0]].text,
					airspaceId : groups[0]["items"][selected[0]].id,
					airspaceL : groups[0]["items"][selected[0]].name,
					airspaceHigh : groups[0]["items"][selected[0]].high,
					airspaceScopeType : groups[0]["items"][selected[0]].scopeType,
					showLPicker : false, 
				}, this.__temp_picker_obj ) )
				break;
			case "startTime":
				startTime = groups[0].items[selected[0]].value + ":" + groups[1].items[selected[1]].value;
				if( this.compareTime( startTime, this.state.endTime ) )
					this.setState( Object.assign( {}, this.state, { startTime , showSTPicker : false, showETPicker : true } ) )
				else
					Tool.alert("结束时间不能小于开始时间");
				break;
			case "endTime":
				endTime = groups[0].items[selected[0]].value + ":" + groups[1].items[selected[1]].value;
				if( this.compareTime( this.state.startTime, endTime ) )
					this.setState( Object.assign( {}, this.state, { endTime, showETPicker : false } ) )
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
	render(){
		let {
				showASPicker,
				showLPicker,
				showSTPicker,
				showETPicker,
				asData,
				locationData,
				landingData,
				landing,
				projectType,
				projectDesc,
				planeTypes,
				typeDesc,
				startDate,
				startTime,
				endTime,
				airspace,
				lfLocation,
				captainName, //操控员姓名
				studentNumber, //学员数量
				contactName, //现场联系人
				contactPhone,//联系方式
				weatherCondition,
				remark, //其他说明事项
				jjcontactName,
				jjcontactPhone,
				lng,
				lat,
				planeName,
			} = this.state,
			landingArray = [];

		if( landing == -1 ){
			landingArray.push(
				<FormCell key = { 'landing_1' }>
					<CellBody>
						<Input value = { lfLocation } onChange = { e => this.handleChange( "lfLocation", e ) } type = "text" placeholder = "请输入起降场具体位置"/>
					</CellBody>
				</FormCell>
			);
			landingArray.push(
				<FormCell key = { 'landing_2' }>
						<CellHeader>
							<Label>自选起降场经度</Label>
						</CellHeader>
						<CellBody>
							<Flex>
								<FlexItem>
									<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { lng[0] } onChange = { e => this.handleChangeByLL( "lng0", e ) } type = "text"/>
								</FlexItem>
								<div>度</div>
								<FlexItem>
									<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { lng[1] } onChange = { e => this.handleChangeByLL( "lng1", e ) } type = "text"/>
								</FlexItem>
								<div>分</div>
								<FlexItem>
									<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { lng[2] } onChange = { e => this.handleChangeByLL( "lng2", e ) } type = "text"/>
								</FlexItem>
								<div>秒</div>
							</Flex>
						</CellBody>
					</FormCell>
			)
			landingArray.push(
				<FormCell key = { 'landing_3' }>
					<CellHeader>
						<Label>自选起降场纬度</Label>
					</CellHeader>
					<CellBody>
						<Flex>
							<FlexItem>
								<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { lat[0] } onChange = { e => this.handleChangeByLL( "lat0", e ) } type = "text"/>
							</FlexItem>
							<div>度</div>
							<FlexItem>
								<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { lat[1] } onChange = { e => this.handleChangeByLL( "lat1", e ) } type = "text"/>
							</FlexItem>
							<div>分</div>
							<FlexItem>
								<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { lat[2] } onChange = { e => this.handleChangeByLL( "lat2", e ) } type = "text"/>
							</FlexItem>
							<div>秒</div>
						</Flex>
					</CellBody>
				</FormCell>
			)
		}

		return (
			<div>
				<Cells>
					<FormCell select selectPos = "after">
						<CellHeader>
							<Label>计划类型</Label>
						</CellHeader>
						<CellBody>
							<Select value = { projectType } onChange = { e => this.handleChange( "projectType", e ) } data = { projectTypes[1] }/>
						</CellBody>
					</FormCell>
					{
						projectTypes[1][projectType].label === "其他" && (
							<FormCell>
								<CellBody>
									<Input placeholder = "请输入类型描述" value = { projectDesc } onChange = { e => this.handleChange( "projectDesc", e ) } type = "text" />
								</CellBody>
							</FormCell>
						)
					}
					<FormCell style = {{ padding: "0 15px" }}>
						<CellHeader>
							<Label>使用机型</Label>
						</CellHeader>
						<CellBody>
							{
								Array.isArray( planeTypes ) && planeTypes.map( ( v, k ) => {
									return (
										<FormCell key = { `pt_${k}` } className = "plane_type">
											<CellBody>
												<Flex>
													<FlexItem>
														{
															v.name === '其他' ? <Input value = { planeName} onChange = { e => this.handleChange( "planeName", e ) } placeholder = "其他机型"/> : <Label>{ v.name }</Label>
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
									<Input value = { startTime } readOnly onClick = { () => this.togglePicker("showSTPicker") } placeholder = "开始时间"/>
								</FlexItem>
								<FlexItem>
									<Input value = { endTime } readOnly onClick = { () => this.togglePicker("showETPicker") } placeholder = "结束时间"/>
								</FlexItem>
							</Flex>
							<CityPicker animation = { false } show = { showSTPicker } onChange = { ( text, selected, groups ) => this.changeHandleByPicker( "startTime", text, selected, groups ) } onCancel = { () => this.togglePicker("showSTPicker") } data = { timeList }/>
							<CityPicker animation = { false } show = { showETPicker } onChange = { ( text, selected, groups ) => this.changeHandleByPicker( "endTime", text, selected, groups ) } onCancel = { () => this.togglePicker("showETPicker") } data = { timeList }/>
						</CellBody>
					</FormCell>
					<Cell access onClick = { () => this.togglePicker("showASPicker") }>
						<CellHeader>
							<Label>计划空域</Label>
						</CellHeader>
						<CellBody>{ airspace }</CellBody>
						<CellFooter></CellFooter>
					</Cell>
					{ 
						asData.length > 0 && <CityPicker animation = { false } show = { showASPicker } onChange = { ( text, selected, groups ) => this.changeHandleByPicker( "city", text, selected, groups ) } onCancel = { () => this.togglePicker("showASPicker") } data = { asData }/>
					}
					{ 
						showLPicker && locationData.length > 0 && <CityPicker dataMap = { { id : "text", items : "items" } } animation = { false } show = { true } onChange = { ( text, selected, groups ) => this.changeHandleByPicker( "location", text, selected, groups ) } onCancel = { () => this.togglePicker("showLPicker") } data = { locationData }/>
					}
					<FormCell select selectPos = "after">
						<CellHeader>
							<Label>使用起降场</Label>
						</CellHeader>
						<CellBody>
							<Select value = { landing } onChange = { e => this.handleChange( "landing", e ) } data = { landingData }/>
						</CellBody>
					</FormCell>
					{ landingArray }
				</Cells>
				<CellsTitle>教练/飞行员或负责人信息</CellsTitle>
				<Cells>
					<FormCell>
						<CellHeader>
							<Label>教练/飞行员</Label>
						</CellHeader>
						<CellBody>
							<Input value = { captainName } placeholder = "多个姓名如：张三，李四，王五" onChange = { e => this.handleChange( "captainName", e ) } type = "text"/>
						</CellBody>
					</FormCell>
					<FormCell>
						<CellHeader>
							<Label>学员数量</Label>
						</CellHeader>
						<CellBody>
							<Input value = { studentNumber } placeholder = "请输入学员数量" maxLength = { 3 } onChange = { e => this.handleChange( "studentNumber", e, "number" ) } type = "text"/>
						</CellBody>
					</FormCell>
					<FormCell>
						<CellHeader>
							<Label>现场联系人</Label>
						</CellHeader>
						<CellBody>
							<Input value = { contactName } placeholder = "请输入现场联系人" onChange = { e => this.handleChange( "contactName", e ) } type = "text"/>
						</CellBody>
					</FormCell>
					<FormCell>
						<CellHeader>
							<Label>手机号码</Label>
						</CellHeader>
						<CellBody>
							<Input value = { contactPhone } placeholder = "请输入现场联系人手机号码" maxLength = { 11 } onChange = { e => this.handleChange( "contactPhone", e, "number" ) } type = "text"/>
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
							<Input value = { jjcontactName } placeholder = "请输入紧急联系人" onChange = { e => this.handleChange( "jjcontactName", e ) } type = "text"/>
						</CellBody>
					</FormCell>
					<FormCell>
						<CellHeader>
							<Label>手机号码</Label>
						</CellHeader>
						<CellBody>
							<Input value = { jjcontactPhone } placeholder = "请输入紧急联系人手机号码" maxLength = { 11 } onChange = { e => this.handleChange( "jjcontactPhone", e, "number" ) } type = "text"/>
						</CellBody>
					</FormCell>
				</Cells>
				<CellsTitle>其他</CellsTitle>
				<Cells>
					<FormCell>
						<CellHeader><Label>气象条件</Label></CellHeader>
						<CellBody>
							<Input value = { weatherCondition } placeholder = "请输入气象条件" onChange = { e => this.handleChange("weatherCondition", e) } type = "text"/>
						</CellBody>
					</FormCell>
					<FormCell>
						<CellHeader><Label>其他说明事项</Label></CellHeader>
						<CellBody>
							<TextArea placeholder = "请输入其他说明事项" value = { remark } onChange = { e => this.handleChange( "remark", e ) } maxlength = { 200 }/>
						</CellBody>
					</FormCell>
				</Cells>
				<ButtonArea>
					<Button onClick = { () => this.submitHandle() } type = "primary">提交</Button>
				</ButtonArea>
			</div>
		)
	}
}
import React from 'react'
import Tool from 'utils/tool'
import checkCard from 'utils/valid-card'
import { Form, CellsTitle, Cells, Cell, Button, ButtonArea, FormCell, CellHeader, CellBody, CellFooter, Label, Input, Flex, FlexItem, Select, TextArea } from 'react-weui'
import { card_type, planeTypes, projectTypes, timeList } from 'common/constant'
import CityPicker from 'common/weui/city-picker'
let RegxOnlyNumber = /^\d{0,}$/;

export default class Form2 extends React.Component{
	constructor(props) {
		super(props);
		this.__temp_obj;
		this.__temp_picker_obj;
		this.today = props.getToday();		
		this.state = {
			showASPicker : false,
			showLPicker : false,
			showSTPicker : false,
			showETPicker :false,
			projectType : 0, //计划类型
			projectDesc : "",
			planeTypes : planeTypes.map( ( v, k ) =>({ label : v.name, value : k }) ),
			startDate : this.today,
			startTime : "",
			endTime : "",
			captainName : "", //操控员姓名
			contactPhone : "",//联系方式
			provinceCode : "",
			provinceName : "",
			cityCode : "",
			cityName : "",
			areaCode : "",
			areaName : "",
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
			lfLocation : "",
			cardType : 0,
			cardNo : "",
			planeType : 0,
			planeNumber : 1,
			planeName : "",
			timeList : [],
		};
	}
	filterTimeList(){
		let date = new Date(),
			hours = date.getHours(),
			minutes = date.getMinutes(),
			newTimeList = timeList.filter( v => hours < parseInt( v.name ) )
		newTimeList[0] = {
			name : newTimeList[0].name,
			value : newTimeList[0].value,
			items : newTimeList[0].items.filter( v => minutes <= parseInt( v.name ) )
		}
		return newTimeList
	}
	componentDidMount(){
		alert("此模块暂时未开放")
		Tool.get(`${ config.ajaxPath }/metadata/airspace`,{ type : 2 })
		.then( json => {
			let asData = {};
			json.data.forEach( v => {
				if( asData[v.provinceCode] ){
					if( asData[v.provinceCode]["items"][v.cityCode] ){
						if( asData[v.provinceCode]["items"][v.cityCode]["items"][v.areaCode] ){
							if( !asData[v.provinceCode]["items"][v.cityCode]["items"][v.areaCode]["_items"][v.id] ){
								asData[v.provinceCode]["items"][v.cityCode]["items"][v.areaCode]["_items"][v.id] = {
									break : true,
									name : v.location,
									high : v.high,
									scopeType : v.scopeType,
									id : v.id,
									text : `${ v.location }（真高：${ v.high }（含）以下）`
								}
							}
						}else{
							asData[v.provinceCode]["items"][v.cityCode]["items"][v.areaCode] = { 
								name : v.areaName,
								code : v.areaCode,
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
						asData[v.provinceCode]["items"][v.cityCode] = {
							name : v.cityName,
							code : v.cityCode,
							items : {
								[v.areaCode] : {
									name : v.areaName,
									code : v.areaCode,
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
				}else{
					asData[v.provinceCode] = {
							name : v.provinceName,
							code : v.provinceCode,
							items : {
								[v.cityCode] : {
									name : v.cityName,
									code : v.cityCode,
									items : {
										[v.areaCode] : {
											name : v.areaName,
											code : v.areaCode,
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
		this.setState( Object.assign( {}, this.state, { timeList : this.filterTimeList() } ) )
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
				areaCode,
				areaName,
				airspaceL,
				airspaceHigh,
				airspaceId,
				airspaceScopeType,
				remark,
				captainName,
				contactPhone,
				studentNumber,
				jjcontactName,
				jjcontactPhone,
				lfLocation,
				cardType,
				cardNo,
				planeType,
				planeNumber,
				planeName,
			} = this.state,
			params = {
				type : 2,
				planes : [{
					name : planeTypes[planeType].label === "其他" ? `其他:${planeName}` : planeTypes[planeType].label,
					number : planeNumber,
				}],
				planType : projectTypes[2][projectType].label === "其他" ? `其他:${ projectDesc }` : projectTypes[2][projectType].label,
				beginTime : startDate + " " + startTime,
				endTime : startDate + " " + endTime,
				landingInfo : [
					{
						location : lfLocation,
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
						areaCode,
						areaName,
						location : airspaceL,
						high : airspaceHigh,
						scopeType : airspaceScopeType,
						centers: [],
					}
				],
				contactInfo : {
					"controller": captainName,
				    "controllerContact":contactPhone,
				    "idCardType" : card_type[ cardType ].label,
				    "idCardNo" : cardNo,
				    "emergencyContactName":jjcontactName,
				    "emergencyContactPhone":jjcontactPhone,

				},
			};
		let message = (function(){
			if( projectTypes[2][projectType].label === "其他" && !/^其他:\S+$/.test( params.planType ) ) return "请输入其他计划类型描述"
			if( params.planes.length === 0 ) return "请输入机型数量"
			for( let i = params.planes.length;i--;){
				if( !params.planes[i].number || !/^\d+$/.test( params.planes[i].number ) )
					return `机型数量不能为${params.planes[i].number}`
				if( /^其他:$/.test( params.planes[i].name ) && !/^其他:\S+$/.test( params.planes[i].name ) )
					return "其他机型输入错误"
			}
			if( !startDate ) return "请选择使用日期"
			if( !startTime ) return "请选择开始时间段"
			if( !endTime ) return "请选择结束时间段"
			if( !provinceCode || !cityCode || !areaCode || !airspaceL) return "请选择计划空域"
			if( !params.landingInfo[0].location ) return "请输入起降场具体位置"
			if( !captainName )
				return "请输入操控员名称"
			if( !cardNo || ( card_type[ cardType ].label === '身份证' && !checkCard( cardNo ) ) )
				return "证件输入错误"
			if( !contactPhone || !/^1\d{10}$/.test( contactPhone ) )
				return "操控员或负责人手机号码输入错误"
			if( !jjcontactName )
				return '请输入紧急联系人'
			if( !jjcontactPhone || !/^1\d{10}$/.test( jjcontactPhone ) )
				return "紧急联系人手机号码输入错误"
			if( jjcontactName == captainName )
				return "紧急联系人与操控员重复"
			if( jjcontactPhone == contactPhone )
				return "紧急联系人手机号码与操控员手机号码重复"
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
				text += groups[2]['items'][selected[2]].name
				this.__temp_city = text;
				this.__temp_picker_obj = {
					provinceCode : groups[0]['items'][selected[0]].code,
					provinceName : groups[0]['items'][selected[0]].name,
					cityCode : groups[1]['items'][selected[1]].code,
					cityName : groups[1]['items'][selected[1]].name,
					areaCode : groups[2]['items'][selected[2]].code,
					areaName : groups[2]['items'][selected[2]].name,
				};
				this.setState( Object.assign( {}, this.state, { 
					showLPicker : true, 
					locationData : groups[2]['items'][selected[2]]["_items"],
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
				landingData,
				landing,
				projectType,
				projectDesc,
				planeTypes,
				startDate,
				startTime,
				endTime,
				airspace,
				lfLocation,
				captainName, //操控员姓名
				contactPhone,//联系方式
				remark, //其他说明事项
				jjcontactName,
				jjcontactPhone,
				locationData,
				cardType,
				cardNo,
				planeType,
				planeNumber,
				planeName,
			} = this.state;

		return (
			<div>
				<Cells>
					<FormCell select selectPos = "after">
						<CellHeader>
							<Label>计划类型</Label>
						</CellHeader>
						<CellBody>
							<Select value = { projectType } onChange = { e => this.handleChange( "projectType", e ) } data = { projectTypes[2] }/>
						</CellBody>
					</FormCell>
					{
						projectTypes[2][projectType].label === "其他" && (
							<FormCell>
								<CellBody>
									<Input placeholder = "请输入类型描述" value = { projectDesc } onChange = { e => this.handleChange( "projectDesc", e ) } type = "text" />
								</CellBody>
							</FormCell>
						)
					}
					<FormCell select selectPos = "after">
						<CellHeader>
							<Label>使用机型</Label>
						</CellHeader>
						<CellBody>
							<Select value = { planeType } onChange = { e => this.handleChange( "planeType", e ) } data = { planeTypes }/>
						</CellBody>
					</FormCell>
					{
						planeTypes[planeType].label === "其他" && <FormCell><CellHeader><Input value = { planeName } onChange = { e=> this.handleChange( "planeName", e ) } placeholder = "其他机型"/></CellHeader></FormCell>
					}
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
							{ timeList.length > 0 && <CityPicker animation = { false } show = { showSTPicker } onChange = { ( text, selected, groups ) => this.changeHandleByPicker( "startTime", text, selected, groups ) } onCancel = { () => this.togglePicker("showSTPicker") } data = { timeList }/> }
							{ timeList.length > 0 && <CityPicker animation = { false } show = { showETPicker } onChange = { ( text, selected, groups ) => this.changeHandleByPicker( "endTime", text, selected, groups ) } onCancel = { () => this.togglePicker("showETPicker") } data = { timeList }/> }
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
					<FormCell>
						<CellHeader>
							<Label>使用起降场</Label>
						</CellHeader>
						<CellBody>
							<Input value = { lfLocation } onChange = { e => this.handleChange( "lfLocation", e ) } placeholder = "请输入起降场具体位置"/>
						</CellBody>
					</FormCell>
				</Cells>
				<CellsTitle>操控员或负责人信息</CellsTitle>
				<Cells>
					<FormCell>
						<CellHeader>
							<Label>操控员/负责人</Label>
						</CellHeader>
						<CellBody>
							<Input value = { captainName } placeholder = "请输入操控员姓名" onChange = { e => this.handleChange( "captainName", e ) } type = "text"/>
						</CellBody>
					</FormCell>
					<FormCell select selectPos = "before">
						<CellHeader>
							<Label><Select value = { cardType } onChange = { e => this.handleChange( "cardType", e ) } data = { card_type } style = {{ height: "41px", lineHeight : "41px" }}/></Label>
						</CellHeader>
						<CellBody>
							<Input value = { cardNo } placeholder = "请输入证件号码" onChange = { e => this.handleChange( "cardNo", e ) } type = "text"/>
						</CellBody>
					</FormCell>
					<FormCell>
						<CellHeader>
							<Label>手机号码</Label>
						</CellHeader>
						<CellBody>
							<Input value = { contactPhone } placeholder = "请输入操控员/负责人手机号码" maxLength = { 11 } onChange = { e => this.handleChange( "contactPhone", e, "number" ) } type = "text"/>
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
				<ButtonArea>
					{/*<Button disabled onClick = { () => this.submitHandle() } type = "primary">提交</Button>*/}
					<Button disabled type = "primary">提交</Button>
				</ButtonArea>
			</div>
		)
	}
}
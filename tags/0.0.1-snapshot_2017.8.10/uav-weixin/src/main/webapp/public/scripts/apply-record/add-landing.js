import React from 'react'
import Tool from 'utils/tool'
import { connect } from 'react-redux'
import { handleChange, loadLanding } from './action'
import { Cells, CellsTitle, FormCell, CellHeader, CellBody, Label, Select, ButtonArea, Button, Input, Flex, FlexItem } from 'react-weui'
let RegxOnlyNumber = /^\d{0,}$/

class AddLanding extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			location : "",
			id : -1,
			lng : ["","",""],
			lat : ["","",""],
		};
	}
	componentDidMount(){
		let {
			landingData,
			landingInfo,
			dispatch,
			match,
		} = this.props,
		_index = match.params._index;

		if( landingData.length === 0 ) dispatch( loadLanding() )

		if( typeof _index !== "undefined" ){
			this.setState( Object.assign( {}, this.state, landingInfo[parseInt(_index)] ) )
		}

	}
	handleChange( name, e, type ){
		let value = !!e && !!e.target ? e.target.value : e,
			valid = true;
		e.persist();
		switch( type ){
			case "number":
				valid = RegxOnlyNumber.test( value )
			break;
		}
		valid && this.setState( Tool.updateIn( this.state, name, value ) )
	}

	handleChangeById( e ){
		let value = !!e && !!e.target ? e.target.value : e;
		this.setState( Object.assign( {}, this.state, { id : value, location : "", lng : ["","",""], lat : ["","",""] } ) )
	}
	handleChangeByLL( name, e ){
		e.persist();

		if( !RegxOnlyNumber.test( e.target.value ) ) return;

		let value = parseInt( e.target.value ) || 0,
			state;
		// value && ( value = parseInt( value ) > 180 ? 180 : value )
		switch( name ){
			case "lng0":
				state = Tool.updateIn( this.state, ["lng",0], value > 180 ? 180 : value )
			break;
			case "lng1":
				state = Tool.updateIn( this.state, ["lng",1], value > 60 ? 60 : value )
			break;
			case "lng2":
				state = Tool.updateIn( this.state, ["lng",2], value > 60 ? 60 : value )
			break;
			case "lat0":
				state = Tool.updateIn( this.state, ["lat",0], value > 90 ? 90 : value )
			break;
			case "lat1":
				state = Tool.updateIn( this.state, ["lat",1], value > 60 ? 60 : value )
			break;
			case "lat2":
				state = Tool.updateIn( this.state, ["lat",2], value > 60 ? 60 : value )
			break;
		}
		// console.info( Array.of(_centers), centers )
		this.setState( state )
	}
	submit(){
		let {
				id,
				location,
				lng,
				lat,
			} = this.state,
			{
				dispatch,
				landingData,
				landingInfo,
				match,
			} = this.props,
			_index = match.params._index,
			_landingInfo = landingInfo.slice(),
			landing;
		if( id == -1 ){
			let message = (function(){
				if( !location ) return "请输入起降场具体位置"
				if( !/^\d+\,\d+\,\d+$/.test( lng.join(',') ) || !/^\d+\,\d+\,\d+$/.test( lat.join(',') ) ) return "经纬度输入错误"
			})()
			if( message ) return Tool.alert(message)
			landing = {
				id,
				lng : lng,
				lat : lat,
				location,
				name : "自选起降场",
			}
		}else{
			let loop = landingData.length;
			while( loop ){
				if( landingData[--loop].value == id ){
					landing = {
						id,
						lng : landingData[loop].lng,
						lat : landingData[loop].lat,
						location : landingData[loop].location,
						name : landingData[loop].label,
					}
					loop = 0;
				}
			}
		}
		if( typeof _index !== "undefined" && _landingInfo[parseInt(_index)] ){
			_landingInfo[parseInt(_index)] = landing
		}else{
			_landingInfo.push( landing )
		}
		dispatch( handleChange( "landingInfo", _landingInfo ) )
		// Tool.to("/apply-record/3")
		history.back();
	}
	render(){
		let {
				landingData
			} = this.props,
			{
				id,
				location,
				lng,
				lat,
			} = this.state,
			landingArray = [];
		if( id == -1 ){
			landingArray.push(
				<FormCell key = { 'landing_1' }>
					<CellBody>
						<Input value = { location } onChange = { e => this.handleChange( "location", e ) } type = "text" placeholder = "请输入起降场具体位置"/>
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
									<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { lng[0] } onChange = { e => this.handleChangeByLL( "lng0", e, "number" ) } type = "text"/>
								</FlexItem>
								<div>度</div>
								<FlexItem>
									<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { lng[1] } onChange = { e => this.handleChangeByLL( "lng1", e, "number" ) } type = "text"/>
								</FlexItem>
								<div>分</div>
								<FlexItem>
									<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { lng[2] } onChange = { e => this.handleChangeByLL( "lng2", e, "number" ) } type = "text"/>
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
								<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { lat[0] } onChange = { e => this.handleChangeByLL( "lat0", e, "number" ) } type = "text"/>
							</FlexItem>
							<div>度</div>
							<FlexItem>
								<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { lat[1] } onChange = { e => this.handleChangeByLL( "lat1", e, "number" ) } type = "text"/>
							</FlexItem>
							<div>分</div>
							<FlexItem>
								<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { lat[2] } onChange = { e => this.handleChangeByLL( "lat2", e, "number" ) } type = "text"/>
							</FlexItem>
							<div>秒</div>
						</Flex>
					</CellBody>
				</FormCell>
			)
		}
		return (
			<div>
				<CellsTitle>选择起降场</CellsTitle>
				<Cells>
					<FormCell select selectPos = "after">
						<CellHeader>
							<Label>使用起降场</Label>
						</CellHeader>
						<CellBody>
							<Select value = { id } onChange = { e => this.handleChangeById( e ) } data = { landingData }/>
						</CellBody>
					</FormCell>
					{ landingArray }
				</Cells>
				<ButtonArea>
					<Button onClick = { () => this.submit() }>确认</Button>
				</ButtonArea>
			</div>
		)
	}
}
export default connect( state =>({
	landingData : state.applyRecord.landingData,
	landingInfo : state.applyRecord.landingInfo
}))( AddLanding )
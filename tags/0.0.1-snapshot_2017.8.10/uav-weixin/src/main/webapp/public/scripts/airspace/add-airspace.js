import React from 'react'
import Tool from 'utils/tool'
import SwitchView from 'common/tab/switch-view'
import { Panel, PanelBody, MediaBox, MediaBoxBody, FormCell, Cell, Flex, FlexItem, Input, Label, Cells, CellHeader, CellBody, CellFooter, Icon, CellsTitle, TextArea, ButtonArea, Button } from 'react-weui'
import Tab from 'common/tab'
import { handleChange } from './action'
import { connect } from 'react-redux'
let RegxOnlyNumber = /^\d{0,}$/
import "styles/add-airspace.less"

class AddAirSpace extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			tabIndex : 1,
		};
		this.__oldcenters;
	}
	componentDidMount(){
		let {
			match,
			airInfo,
		} = this.props,
		_index = match.params._index,
		state,
		emptyInfo = {
			centers : [
				{
					lng : ["","",""],
					lat : ["","",""]
				}
			],
			high : "",
			location : "",
			scopeType : 1,
			radius : "",
		},
		_airinfo = typeof _index === "undefined" ? emptyInfo : airInfo[_index];
		switch( _airinfo.scopeType ){
			case 2:
				state = Object.assign( {}, { tabIndex : 2 }, _airinfo )
				break;
			case 1:
			default:
				state = Object.assign( {}, { tabIndex : 1 }, _airinfo )
				break;
		}
		this.setState( state )
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
	handleChangeByHigh( name, e ){
		let value = !!e && !!e.target ? e.target.value : e,
			valid = RegxOnlyNumber.test( value ),
			_value;
		e.persist();
		if( !valid ) return
		_value = parseInt( value )
		this.setState( Tool.updateIn( this.state, name, _value ) )
	}
	handleChangeByLL( index, name, e ){
		e.persist();

		if( !RegxOnlyNumber.test( e.target.value ) ) return;

		let centers = this.state.centers,
			value = parseInt( e.target.value ) || 0,
			_centers;
		switch( name ){
			case "lng0":
				_centers = Tool.updateIn( centers, [index,"lng",0], value > 180 ? 180 : value )
			break;
			case "lng1":
				_centers = Tool.updateIn( centers, [index,"lng",1], value > 60 ? 60 : value )
			break;
			case "lng2":
				_centers = Tool.updateIn( centers, [index,"lng",2], value > 60 ? 60 : value )
			break;
			case "lat0":
				_centers = Tool.updateIn( centers, [index,"lat",0], value > 90 ? 90 : value )
			break;
			case "lat1":
				_centers = Tool.updateIn( centers, [index,"lat",1], value > 60 ? 60 : value )
			break;
			case "lat2":
				_centers = Tool.updateIn( centers, [index,"lat",2], value > 60 ? 60 : value )
			break;
		}
		// console.info( Array.of(_centers), centers )
		this.setState( Object.assign( {}, this.state, { centers : _centers } ) )
	}
	switchTab( tabIndex ){
		let state = { 
			centers : [
				{
					lng : ["","",""],
					lat : ["","",""]
				}
			],
			high : "",
			location : "",
			radius : "",
			scopeType : 1 
		};
		if( tabIndex === 2 ){
			state.centers.push({
				lng : ["","",""],
				lat : ["","",""]
			})
			state.centers.push({
				lng : ["","",""],
				lat : ["","",""]
			})
			state.scopeType = 2;
		}
		this.setState( Object.assign( {}, this.state, { tabIndex }, state ) )
	}
	submit(){
		let {
			dispatch,
			match,
			airInfo,
		} = this.props,
		{
			centers,
			location,
			high,
			scopeType,
			radius,
		} = this.state,
		_index = match.params._index,
		_airinfo = airInfo.slice();

		let message = (function(){
			if( !location ) return "请输入位置描述"
			if( !high ) return "请输入真高"
			if( scopeType === 1 && !radius ) return "请输入半径"
			if( scopeType === 2 && centers.length < 3 ) return "至少输入三个坐标"
			for( let i = 0; i< centers.length; i++ ){
				if( !/^\d+\,\d+\,\d+$/.test( centers[i].lng.join(',') ) || !/^\d+\,\d+\,\d+$/.test( centers[i].lat.join(',') ) ) return `经纬度${i+1}输入错误`
			}
		})()
		if( message ) return Tool.alert( message )
		if( typeof _index === "undefined" ){
			_airinfo.push({
				centers,
				location,
				high,
				scopeType,
				radius,
			})
		}else{
			_airinfo[_index] = {
				centers,
				location,
				high,
				scopeType,
				radius,
			}
		}
		dispatch( handleChange( ["airInfo"], _airinfo ) )
		// Tool.to("/apply-record/3")
		history.back()
	}
	addCoord(){
		let {
			centers
		} = this.state,
		_centers = centers.slice();
		_centers.push({
			lng : ["","",""],
			lat : ["","",""],
		})
		this.setState( Object.assign( {}, this.state, { centers : _centers } ) )
	}
	removeCoord( index ){
		let {
			centers
		} = this.state,
		_centers = centers.slice();
		if( centers.length < 4 ) return Tool.alert("至少输入三个坐标")
		_centers.splice(index,1)
		this.setState( Object.assign( {}, this.state, { centers : _centers } ) )
	}
	render(){
		let {
			tabIndex,
			centers,
			high,
			location,
			radius,
		} = this.state;
		return (
			<div id="add_as_app">
				<SwitchView>
					<Tab label = "圆形空域" active = { tabIndex === 1 } handlerActive = { () => this.switchTab(1) }>
						<Cells>
							<FormCell>
								<CellHeader><Label>位置描述</Label></CellHeader>
								<CellBody>
									<Input type = "text" value = { location || "" } onChange = { e => this.handleChange( "location", e ) } placeholder = "请输入位置描述"/>
								</CellBody>
							</FormCell>
							<FormCell>
								<CellHeader><Label>真高</Label></CellHeader>
								<CellBody>
									<Input type = "text" value = { high || "" } maxLength = { 4 } onChange = { e => this.handleChangeByHigh( "high", e, "number" ) } placeholder = "请输入真高"/>
								</CellBody>
							</FormCell>
						</Cells>
						<CellsTitle>范围</CellsTitle>
						<Cells>
							<FormCell>
								<CellHeader>
									<Label>以坐标：</Label>
								</CellHeader>
							</FormCell>
							{
								Array.isArray( centers ) && centers.map( ( v, k ) => {
									return (
										<Panel className = "coord" key = { `center_${k}`}>
											<Cell>
												<CellHeader>
													<Label>经度</Label>
												</CellHeader>
												<CellBody>
													<Flex>
														<FlexItem>
															<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { v.lng[0] } onChange = { e => this.handleChangeByLL( k, "lng0", e ) } type = "text"/>
														</FlexItem>
														<div>度</div>
														<FlexItem>
															<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { v.lng[1] } onChange = { e => this.handleChangeByLL( k, "lng1", e ) } type = "text"/>
														</FlexItem>
														<div>分</div>
														<FlexItem>
															<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { v.lng[2] } onChange = { e => this.handleChangeByLL( k, "lng2", e ) } type = "text"/>
														</FlexItem>
														<div>秒</div>
													</Flex>
												</CellBody>
											</Cell>
											<Cell>
												<CellHeader>
													<Label>纬度</Label>
												</CellHeader>
												<CellBody>
													<Flex>
														<FlexItem>
															<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { v.lat[0] } onChange = { e => this.handleChangeByLL( k, "lat0", e ) } type = "text"/>
														</FlexItem>
														<div>度</div>
														<FlexItem>
															<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { v.lat[1] } onChange = { e => this.handleChangeByLL( k, "lat1", e ) } type = "text"/>
														</FlexItem>
														<div>分</div>
														<FlexItem>
															<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { v.lat[2] } onChange = { e => this.handleChangeByLL( k, "lat2", e ) } type = "text"/>
														</FlexItem>
														<div>秒</div>
													</Flex>
												</CellBody>
											</Cell>
										</Panel>
									)
								})
							}
							<FormCell>
								<CellHeader>
									<Label>为中心，半径：</Label>
								</CellHeader>
								<CellBody>
									<Input value = { radius || "" } onChange = { e => this.handleChange( "radius", e, "number" ) } placeholder = "米" type = "text"/>
								</CellBody>
							</FormCell>
						</Cells>
						<ButtonArea>
							<Button onClick = { () => this.submit() }>确认</Button>
						</ButtonArea>
					</Tab>
					<Tab label = "多边形空域" active = { tabIndex === 2 } handlerActive = { () => this.switchTab(2) }>
						<Cells>
							<FormCell>
								<CellHeader><Label>位置描述</Label></CellHeader>
								<CellBody>
									<Input type = "text" value = { location || "" } onChange = { e => this.handleChange( "location", e ) } placeholder = "请输入位置描述"/>
								</CellBody>
							</FormCell>
							<FormCell>
								<CellHeader><Label>真高</Label></CellHeader>
								<CellBody>
									<Input maxLength = { 4 } type = "text" value = { high || "" } onChange = { e => this.handleChange( "high", e, "number" ) } placeholder = "请输入真高"/>
								</CellBody>
							</FormCell>
						</Cells>
						<CellsTitle>范围</CellsTitle>
						<Cells>
							<FormCell>
								<CellHeader>
									为以下{ centers && centers.length }点连线范围内坐标，坐标依次为：
								</CellHeader>
							</FormCell>
							{
								Array.isArray( centers ) && centers.map( ( v, k ) => {
									return (
										<Panel className = "coord" key = { `mutilp_${k}`}>
											<PanelBody>
												<FormCell>
													<CellHeader>
														<Label>经度</Label>
													</CellHeader>
													<CellBody>
														<Flex>
															<FlexItem>
																<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { v.lng[0] } onChange = { e => this.handleChangeByLL( k, "lng0", e ) } type = "text"/>
															</FlexItem>
															<div>度</div>
															<FlexItem>
																<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { v.lng[1] } onChange = { e => this.handleChangeByLL( k, "lng1", e ) } type = "text"/>
															</FlexItem>
															<div>分</div>
															<FlexItem>
																<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { v.lng[2] } onChange = { e => this.handleChangeByLL( k, "lng2", e ) } type = "text"/>
															</FlexItem>
															<div>秒</div>
														</Flex>
													</CellBody>
													<CellFooter>
														&nbsp;
														<Icon onClick = { () => this.removeCoord( k ) } value = "cancel" className = "del_icon"/>
													</CellFooter>
												</FormCell>
												<FormCell className = "no_border">
													<CellHeader>
														<Label>纬度</Label>
													</CellHeader>
													<CellBody>
														<Flex>
															<FlexItem>
																<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { v.lat[0] } onChange = { e => this.handleChangeByLL( k, "lat0", e ) } type = "text"/>
															</FlexItem>
															<div>度</div>
															<FlexItem>
																<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { v.lat[1] } onChange = { e => this.handleChangeByLL( k, "lat1", e ) } type = "text"/>
															</FlexItem>
															<div>分</div>
															<FlexItem>
																<Input style = {{ textAlign : "center" }} maxLength = { 3 } value = { v.lat[2] } onChange = { e => this.handleChangeByLL( k, "lat2", e ) } type = "text"/>
															</FlexItem>
															<div>秒</div>
														</Flex>
													</CellBody>
													<CellFooter>
														&nbsp;
													</CellFooter>
												</FormCell>
											</PanelBody>
										</Panel>
									)
								})
							}
						</Cells>
						<Button onClick = { () => this.addCoord() } className = "add_btn" type = "default">+&nbsp;添加坐标</Button>
						<ButtonArea>
							<Button onClick = { () => this.submit() }>确认</Button>
						</ButtonArea>
					</Tab>
				</SwitchView>
			</div>
		)
	}
}

export default connect( state => ({
	airInfo : state.airApply.airInfo
}))(AddAirSpace)
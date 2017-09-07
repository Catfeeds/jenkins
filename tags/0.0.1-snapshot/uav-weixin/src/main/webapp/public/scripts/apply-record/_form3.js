import React from 'react'
// import { Button, FormControl, FormGroup, Label, Panel, HelpBlock } from 'react-bootstrap/lib'
import InputList from 'common/weui/input-list'
import TextArea from 'common/weui/textarea'
import { Panel, PanelHeader, PanelBody, MediaBox, FormCell, CellHeader, CellBody, CellsTitle, Cells, ButtonArea, Button, Label, Cell, Icon } from 'react-weui'

export default class Form1 extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			airspaces : [
				{
					no : "",
				}
			],
			captainName : "",
			companyPhone : "",
			contactName : "",
			phone : "",
			mc : "",
			remark : "",
		};
	}
	handleChange( name, e ){
		e.persist();
		let value = e.target.value;
		this.setState(Object.assign( {}, this.state, { [name] : value } ) )
		console.info( this.state )
	}
	handleChangeByAirspaces( index, name, e ){
		e.persist();
		let {
			airspaces
		} = this.state,
		value = e.target.value,
		copyAirspaces = airspaces.slice();
		copyAirspaces[index] = Object.assign( {}, copyAirspaces[index], { [name] : e.target.value } );
		this.setState(Object.assign( {}, this.state, { airspaces : copyAirspaces } ) )	
	}
	addAirspace(){
		let {
			airspaces
		} = this.state,
		copyAirspaces = airspaces.slice();
		copyAirspaces.push( { 
			no : "" 
		});
		this.setState(Object.assign( {}, this.state, { airspaces : copyAirspaces } ) );
	}
	removeAirspaceByIndex( index ){
		let {
			airspaces
		} = this.state,
		copyAirspaces = airspaces.slice();
		copyAirspaces.splice( index, 1 );
		this.setState(Object.assign( {}, this.state, { airspaces : copyAirspaces } ) );
	}
	render(){
		let {
			airspaces
		} = this.state;
		let airspaceArray = [];
		Array.isArray( airspaces ) && airspaces.map( ( v, k ) => {
			airspaceArray.push(
				<Panel key = { `panel_${k}` } className = "panel_area">
					<PanelHeader style = {{ position: "relative" }} >飞行空域{ k + 1 }<Icon onClick = { () => this.removeAirspaceByIndex( k ) } style = {{ position: "absolute", right : "15px" }} value = "cancel" size = "small"/></PanelHeader>
					<PanelBody>
						<InputList label = "空域批文编号" placeholder = "请输入内容" onChange={ e => this.handleChangeByAirspaces( k, "no", e ) }/>
						<FormCell>
							<CellHeader><Label>计划类型</Label></CellHeader>
							<CellBody>电力巡线</CellBody>
						</FormCell>
						<FormCell>
							<CellHeader><Label>使用机型</Label></CellHeader>
							<CellBody>直升机、多旋翼</CellBody>
						</FormCell>
						<FormCell>
							<CellHeader><Label>使用时间</Label></CellHeader>
							<CellBody>20160511 ~ 20160512</CellBody>
						</FormCell>
						<FormCell>
							<CellHeader><Label>空域范围及高度</Label></CellHeader>
							<CellBody>广州黄埔体育中心附近范围</CellBody>
						</FormCell>
					</PanelBody>
				</Panel>
			)
		})
		return (
			<div>
				<Panel>
					<PanelBody>
						<MediaBox style = {{ color : "#F44336" }}>
							提示：创建管制空域计划前需申请空域，当多空域内飞行计划中的“计划类型、使用机型、使用时间”相同时，可填选多个空域，否则请另行填写1个新的飞行计划
						</MediaBox>
					</PanelBody>
				</Panel>
				<CellsTitle>
					关联空域<Button style = {{ marginLeft : "20px" }} type = "default" size = "small" onClick = { () => this.addAirspace() }>添加</Button>
				</CellsTitle>
				{
					airspaceArray
				}
				<CellsTitle>操控员或负责人信息</CellsTitle>
				<Cells>
					<InputList label = "机长姓名"/>
					<InputList label = "单位联系电话"/>
					<InputList label = "联系联系人"/>
					<InputList label = "联系电话"/>
				</Cells>
				<CellsTitle>其他</CellsTitle>
				<Cells>
					<InputList label = "气象条件"/>
				</Cells>
				<CellsTitle>其他说明事项</CellsTitle>
				<Cells>
					<TextArea onChange = { e => this.handleChange( "remake", e ) } maxlength = { 200 }/>
				</Cells>
				<ButtonArea>
					<Button type = "primary">提交</Button>
				</ButtonArea>
			</div>
		)
	}
}
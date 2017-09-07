import React from 'react'
import Tool from 'utils/tool'
import { dynamic_status, dynamic_type } from 'common/constant'
import { Panel, PanelHeader, PanelBody, MediaBox, MediaBoxHeader, MediaBoxBody, Cells, Cell, CellsTitle, CellBody, CellHeader, Label, TextArea } from 'react-weui'
import "styles/dynamic/detail.less"

export default class DetailDynamicApp extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			data : {}
		};
	}
	componentDidMount(){
		let {
				code,
			} = this.props.match.params,
			token = Tool.getQueryString("token");
		Tool.get( !!token ? `${ config.ajaxPath }/flyplan/token?token=${ token }` : `${ config.ajaxPath }/flyplan/${ code }` )
		.then( json => this.setState( { data : json.data } ) )
	}
	render(){
		let {
			data
		} = this.state,
		locationStr,
		content = [],
		status = dynamic_status[data.status] || {},
		type = dynamic_type[data.type] || {},
		contactInfo = data.contactInfo ? JSON.parse( data.contactInfo ) : {},
		landingInfo = data.landingInfo ? JSON.parse( data.landingInfo ) : {},
		airInfo = data.airInfo ? JSON.parse( data.airInfo ) : {};
		if( data.type == 1 ){
			content.push(
				<CellsTitle key = "content_1">操控员或负责人信息</CellsTitle>
			)
			content.push(
				<Cells key = "content_2">
					<Cell>
						<CellHeader><Label>教练/飞行员</Label></CellHeader>
						<CellBody>{ contactInfo.trainer }</CellBody>
					</Cell>
					<Cell>
						<CellHeader><Label>学员数量</Label></CellHeader>
						<CellBody>{ contactInfo.traineeCount }</CellBody>
					</Cell>
					<Cell>
						<CellHeader><Label>现场联系人</Label></CellHeader>
						<CellBody>{ contactInfo.fieldContactName }<a className = "tel" href = { `tel:${ contactInfo.fieldContactPhone }` }>{ contactInfo.fieldContactPhone }</a></CellBody>
					</Cell>
				</Cells>
			)
			content.push(
				<CellsTitle key = "content_3">其他</CellsTitle>
			)
			content.push(
				<Cells key = "content_4">
					<CellBody>
						<TextArea value = { data.remark } style = {{ height : "70px" }} showCounter = { false } readOnly/>
					</CellBody>
				</Cells>
			)
		}else if( data.type == 2 ){
			content.push(
				<CellsTitle key = "content_5">操控员或负责人信息</CellsTitle>
			)
			content.push(
				<Cells key = "content_6">
					<Cell>
						<CellHeader><Label>操控员姓名</Label></CellHeader>
						<CellBody>{ contactInfo.controller }</CellBody>
					</Cell>
					<Cell>
						<CellHeader><Label>证件号</Label></CellHeader>
						<CellBody>{ contactInfo.idCardType }&nbsp;{contactInfo.idCardNo}</CellBody>
					</Cell>
					<Cell>
						<CellHeader><Label>手机号码</Label></CellHeader>
						<CellBody>{ contactInfo.controllerContact }</CellBody>
					</Cell>
				</Cells>
			)
		}
		return (
			<div id = "detail_dynamic_app">
				<Cells>
					<Cell>
						<CellBody><span className = { status.style }>{ data.number }</span><span className = { status.style }>{ status.text }</span></CellBody>
					</Cell>
				</Cells>
				<Cells>
					<Cell>
						<CellHeader><Label>空域类型</Label></CellHeader>
						<CellBody><span className = {`i ${type.style}`}>{ type.text }报告空域</span></CellBody>
					</Cell>
					<Cell>
						<CellHeader><Label>飞行时间</Label></CellHeader>
						<CellBody>{ data.beginTime }~{ data.endTime ? data.endTime.split(/\s/)[1] : "" }</CellBody>
					</Cell>
					<Cell>
						<CellHeader><Label>计划类型</Label></CellHeader>
						<CellBody>{ data.planType }</CellBody>
					</Cell>
					<Cell>
						<CellHeader><Label>计划机型</Label></CellHeader>
						<CellBody>
							{ !!data.planes && JSON.parse( data.planes ).map( v => `${v.name} ${v.number}架`).join("、")  }
						</CellBody>
					</Cell>
				</Cells>
				<CellsTitle>空域及范围</CellsTitle>
				<Panel className = "list_panel blue">
					{
						Array.isArray( airInfo ) && airInfo.map( ( v, k ) => {
							switch( v.type ){
								case 0:
									locationStr = v.location
								break;
								case 1:
									locationStr = v.provinceName + v.cityName + v.location
								break;
								case 2:
									locationStr = v.provinceName + v.cityName + v.areaName + v.location
								break;
								default:
									locationStr = ""
							}
							return (
								<PanelBody key = { `air_info_${ k }`}>
									<MediaBox type = "appmsg" >
										<MediaBoxHeader>{ k + 1 }</MediaBoxHeader>
										<MediaBoxBody>
											<CellBody>{ locationStr }</CellBody>
											<CellBody>真高{ v.high }米</CellBody>
										</MediaBoxBody>
									</MediaBox>
								</PanelBody>
							)
						})
					}
				</Panel>
				<CellsTitle>起降场</CellsTitle>
				<Panel className = "list_panel red">
					{
						Array.isArray( landingInfo ) && landingInfo.map( ( v, k ) => {
							return (
								<PanelBody key = { `lf_${k}` }>
									<MediaBox type = "appmsg" >
										<MediaBoxHeader>{ k + 1 }</MediaBoxHeader>
										<MediaBoxBody>
											<CellBody style = {{ color: "#999" }}>{ !v.id || v.id == -1  ? "自选起降场" : v.name }</CellBody>
											<CellBody>{ v.location }</CellBody>
											{ v.id == -1 && data.type !== 2 && <CellBody>坐标{ v.lng }，{ v.lat }</CellBody> }
										</MediaBoxBody>
									</MediaBox>
								</PanelBody>
							)
						})
					}
				</Panel>
				{ content }
			</div>
		)
	}
}
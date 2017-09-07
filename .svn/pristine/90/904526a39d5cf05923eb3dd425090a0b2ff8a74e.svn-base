import React from 'react'
import Tool from 'utils/tool'
import { Panel, PanelBody, MediaBox, MediaBoxHeader, MediaBoxBody, Cells, Cell, CellHeader, CellBody, CellFooter, Flex, FlexItem, Badge } from 'react-weui'
import applyBS64 from 'images/user/apply'
import airspaceBS64 from 'images/user/airspace'
import contactusBS64 from 'images/user/contactus'
import consultBS64 from 'images/user/consult'
import dataBS64 from 'images/user/data'
import dynamicBS64 from 'images/user/dynamic'
import infoBS64 from 'images/user/info'
import qqBS64 from 'images/user/qq'
import statiBS64 from 'images/user/stati'
import editBS64 from 'images/edit'
import defaultHeadBS64 from 'images/default-head'
import { auth_status } from 'common/constant'
import "styles/user/center.less"

export default class UserCenterApp extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			name : "",
			headPic : "",
			phone : "",
			authStatus : 1,
		};
	}
	componentDidMount(){
		Tool.get(`${ config.ajaxPath }/user/info`)
		.then( json => {
			let {
				name,
				phone,
				headPic,
				authStatus,
				contactPhone,
				contactName,
				flyTimes,
				flyHour,
				flyOvertime,
			} = json.data;
			this.setState( Object.assign( {}, this.state, {
				name : name || contactName,
				phone : phone || contactPhone,
				headPic,
				authStatus,
				flyTimes,
				flyHour,
				flyOvertime,
			} ) )
		})
	}
	redirectApplyDynamic(){
		Tool.to("/dynamic/apply")
	}
	redirectImplDynamic(){
		Tool.to("/dynamic/implement")
	}
	redirectAirspace(){
		Tool.to("/airspace/list")
	}
	redirectInfo(){
		Tool.to("/user/info")
	}
	redirectNotice(){
		Tool.to("/user/message")
	}
	redirectStati(){
		let {
			flyTimes,
			flyHour,
			flyOvertime,
		} = this.state;
		Tool.to(`/user/statistics/${flyTimes}/${flyHour}/${flyOvertime}`)
	}
	redirectConsult(){
		Tool.to("/service/consult")
	}
	redirectQQGroup(){
		Tool.to("/service/qqgroup")
	}
	redirectConcatUs(){
		Tool.to("/service/contactus")
	}
	redirectEdit(){
		Tool.to("/user/edit")
	}
	render(){
		let {
			authStatus,
			name,
			headPic,
			phone,
		} = this.state;
		return(
			<div id="user_center_app">
				<Panel>
					<PanelBody>
						<MediaBox type = "appmsg" onClick = { this.redirectEdit }>
							<MediaBoxHeader><img src={ headPic || defaultHeadBS64 }/></MediaBoxHeader>
							<MediaBoxBody>
								<CellBody className = "username">{ name } <img src = { editBS64 }/></CellBody>
								<CellBody className = "mobile">{ phone }</CellBody>
								<CellBody className = "cer"><span>{ auth_status[ authStatus ] }</span></CellBody>
							</MediaBoxBody>
						</MediaBox>
					</PanelBody>
				</Panel>
				<Panel className = "nav">
					<PanelBody>
						<Flex>
							<FlexItem>
								<dl onClick = { () => this.redirectApplyDynamic() }>
									<dd><img src={ applyBS64 }/></dd>
									<dt>
										<span style = {{ marginRight : "3px"}}>我的申请</span>{/*<Badge>1</Badge>*/}
									</dt>
								</dl>
							</FlexItem>
							<FlexItem>
								<dl onClick = { () => this.redirectImplDynamic() }>
									<dd><img src={ dynamicBS64 }/></dd>
									<dt>我的动态</dt>
								</dl>
							</FlexItem>
							<FlexItem>
								<dl onClick = { () => this.redirectAirspace() }>
									<dd><img src={ airspaceBS64 }/></dd>
									<dt>我的空域</dt>
								</dl>
							</FlexItem>
						</Flex>
					</PanelBody>
				</Panel>
				<Cells className = "_list_group">
					<Cell access onClick = { () => this.redirectInfo() }>
						<CellHeader><img src={ dataBS64 }/></CellHeader>
						<CellBody>我的资料</CellBody>
						<CellFooter></CellFooter>
					</Cell>
					<Cell access onClick = { () => this.redirectNotice() }>
						<CellHeader><img src={ infoBS64 }/></CellHeader>
						<CellBody>我的信息</CellBody>
						<CellFooter></CellFooter>
					</Cell>
					<Cell access onClick = { () => this.redirectStati() }>
						<CellHeader><img src={ statiBS64 }/></CellHeader>
						<CellBody>飞行统计</CellBody>
						<CellFooter></CellFooter>
					</Cell>
					<Cell access onClick = { () => this.redirectConsult() }>
						<CellHeader><img src={ consultBS64 }/></CellHeader>
						<CellBody>业务咨询</CellBody>
						<CellFooter></CellFooter>
					</Cell>
					<Cell access onClick = { () => this.redirectQQGroup() }>
						<CellHeader><img src={ qqBS64 }/></CellHeader>
						<CellBody>加群交流</CellBody>
						<CellFooter></CellFooter>
					</Cell>
					<Cell access onClick = { () => this.redirectConcatUs() }>
						<CellHeader><img src={ contactusBS64 }/></CellHeader>
						<CellBody>联系我们</CellBody>
						<CellFooter></CellFooter>
					</Cell>
				</Cells>
			</div>
		)
	}
}
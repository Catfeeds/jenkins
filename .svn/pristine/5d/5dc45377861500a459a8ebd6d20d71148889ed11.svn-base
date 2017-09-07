import React from 'react'
import Tool from 'utils/tool'
import { auth_status } from 'common/constant'
import { Cells, CellsTitle, Cell, CellHeader, CellBody, CellFooter, Label } from 'react-weui'
import "styles/user/info.less"
// import example1BS64 from 'images/example1';
import defaultHeadBS64 from 'images/default-head'

export default class UserInfoApp extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			perInfo : null,
			comInfo : null,
		};
	}	
	componentDidMount(){
		Tool.get(`${ config.ajaxPath }/user/info`)
		.then( json => {
			let stateName = json.data.companyName ? "comInfo" : "perInfo";
			this.setState( Object.assign( {}, this.state, { [stateName] : json.data } ) )
		})
	}
	render(){
		let {
				perInfo,
				comInfo
			} = this.state,
			contentInfo;
		if( perInfo ){
			contentInfo = (
				<div>
					<Cells>
						<Cell>
							<CellHeader><Label>头像</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter><img src={ perInfo.headPic || defaultHeadBS64 }/></CellFooter>
						</Cell>
						<Cell>
							<CellHeader><Label>姓名</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ perInfo.name }</CellFooter>
						</Cell>
						<Cell>
							<CellHeader><Label>性别</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ perInfo.sex === "M" ? "男" : "女" }</CellFooter>
						</Cell>
						<Cell>
							<CellHeader><Label>身份证号</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ perInfo.idCard }</CellFooter>
						</Cell>
						<Cell>
							<CellHeader><Label>证书编号</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ perInfo.certificateNumber }</CellFooter>
						</Cell>
						<Cell>
							<CellHeader><Label>出日日期</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ perInfo.birthday }</CellFooter>
						</Cell>
						<Cell>
							<CellHeader><Label>认证状态</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ auth_status[ perInfo.authStatus ] }</CellFooter>
						</Cell>
					</Cells>
					<CellsTitle>联系方式</CellsTitle>
					<Cells>
						<Cell>
							<CellHeader><Label>手机号</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ perInfo.phone }</CellFooter>
						</Cell>
						<Cell>
							<CellHeader><Label>邮箱</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ perInfo.email }</CellFooter>
						</Cell>
						<Cell>
							<CellHeader><Label>QQ</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ perInfo.qq }</CellFooter>
						</Cell>
						<Cell>
							<CellHeader><Label>现居住地</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ perInfo.address }</CellFooter>
						</Cell>
					</Cells>
					<CellsTitle>紧急联系人</CellsTitle>
					<Cells>
						<Cell>
							<CellHeader><Label>姓名</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ perInfo.emergencyContactName }</CellFooter>
						</Cell>
						<Cell>
							<CellHeader><Label>手机号</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ perInfo.emergencyContactPhone }</CellFooter>
						</Cell>
					</Cells>
				</div>
			)
		}else if( comInfo ){
			contentInfo = (
				<div>
					<Cells>
						<Cell>
							<CellHeader><Label>头像</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter><img src={ comInfo.headPic || defaultHeadBS64 }/></CellFooter>
						</Cell>
						<Cell>
							<CellHeader><Label>单位名称</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ comInfo.companyName }</CellFooter>
						</Cell>
						<Cell>
							<CellHeader><Label>社会统一信用代码</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ comInfo.licenseNumber }</CellFooter>
						</Cell>
						<Cell>
							<CellHeader><Label>单位地址</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ comInfo.companyAddress }</CellFooter>
						</Cell>
						<Cell>
							<CellHeader><Label>认证状态</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ auth_status[ comInfo.authStatus ] }</CellFooter>
						</Cell>
					</Cells>
					<CellsTitle>主要联系人</CellsTitle>
					<Cells>
						<Cell>
							<CellHeader><Label>姓名</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ comInfo.contactName }</CellFooter>
						</Cell>
						<Cell>
							<CellHeader><Label>手机号</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ comInfo.contactPhone }</CellFooter>
						</Cell>
						<Cell>
							<CellHeader><Label>邮箱</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ comInfo.email }</CellFooter>
						</Cell>
						<Cell>
							<CellHeader><Label>公司法人</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ comInfo.companyLegal }</CellFooter>
						</Cell>
					</Cells>
					<CellsTitle>紧急联系人</CellsTitle>
					<Cells>
						<Cell>
							<CellHeader><Label>姓名</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ comInfo.emergencyContactName }</CellFooter>
						</Cell>
						<Cell>
							<CellHeader><Label>手机号</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter>{ comInfo.emergencyContactPhone }</CellFooter>
						</Cell>
					</Cells>
				</div>
			)
		}
		return (
			<div id="user_info_app"> { contentInfo } </div>
		)
	}
}
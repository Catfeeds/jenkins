import React from 'react'
import Tool from 'utils/tool'
import { Cells, CellsTitle, FormCell, CellHeader, CellBody, CellFooter, Label, ButtonArea, Button, Select, Input } from 'react-weui'
import { auth_status } from 'common/constant'
import defaultHeadBS64 from 'images/default-head'
import "styles/user/info.less"
let RegxOnlyNumber = /^\d{0,}$/;

let card_type = [{
	    value: 0,
	    label: '未取得无人机相关证件'
	}, {
	    value: 1,
	    label: '民用无人驾驶航空器系统驾驶员合格证'
	}, {
	    value: 2,
	    label: '民用无人驾驶航空器系统操作手合格证'
	}, {
	    value: 3,
	    label: '无人机专业技能证'
	}, {
	    value: 4,
	    label: '无人机操作资格证'
	}, {
	    value: 5,
	    label: 'ASFC证书'
	}]

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
	handleChange( name, e, type ){
		let value = !!e && !!e.target ? e.target.value : e,
			valid = true;
		e.persist();
		switch( type ){
			case "number":
				valid = RegxOnlyNumber.test( value )
				// valid && value && ( value = parseInt( value ) )
			break;
		}
		valid && this.setState( Tool.updateIn( this.state, Array.isArray( name ) ? name : [name], value ))
	}
	handleSubmit(){
		let {
			perInfo,
			comInfo,
		} = this.state,
		params,
		message;
		if( perInfo ){
			params = {
				sex : perInfo.sex || "",
				birthday : perInfo.birthday || "",
				certificateType : perInfo.certificateType,
				certificateNumber : perInfo.certificateNumber || "",
				qq : perInfo.qq || "",
				email : perInfo.email || "",//必填
				address : perInfo.address || "",
				emergencyContactName : perInfo.emergencyContactName || "",
				emergencyContactPhone : perInfo.emergencyContactPhone || "" //必填
			}
			message = (function(){
				if( !/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test( params.email ) ) return "邮箱格式输入错误"
				if( !/\S{2,}/.test( params.emergencyContactName ) ) return "紧急联系人姓名输入错误"
				if( !/1\d{10}/.test( params.emergencyContactPhone ) ) return "紧急联系人手机号码输入错误"
			})()
			if( message ) return Tool.alert(message);

			// Tool.fetch("PUT",`${ config.ajaxPath }/user/per/update`, params)
			Tool.post(`${ config.ajaxPath }/user/per/update`, params)
			.then( json => {
				Tool.alert("个人资料修改成功","",[{
		            type : "default",
		            label : "确定",
		            onClick : () => {
		            	Tool.hideAlert()
		            	Tool.to("/user/center")
		            }
		        }])
			})
		}else if( comInfo ){
			params = {
				companyAddress : comInfo.companyAddress || "", //必填
				email : comInfo.email || "", //必填
				companyLegal : comInfo.companyLegal || "",
				emergencyContactName : comInfo.emergencyContactName || "",
				emergencyContactPhone : comInfo.emergencyContactPhone || "",
			}
			message = (function(){
				if( !params.companyAddress ) return "单位地址输入错误"
				if( !/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test( params.email ) ) return "邮箱格式输入错误"
			})()
			if( message ) return Tool.alert(message);

			Tool.post(`${ config.ajaxPath }/user/com/update`, params)
			.then( json => {
				Tool.alert("个人资料修改成功","",[{
		            type : "default",
		            label : "确定",
		            onClick : () => {
		            	Tool.hideAlert()
		            	Tool.to("/user/center")
		            }
		        }])
			})
		}
	}
	render(){
		let {
				perInfo,
				comInfo
			} = this.state,
			contentInfo;

		/*if( perInfo ){
			contentInfo = (
				<div>
					<Cells>
						<FormCell select selectPos = "after">
							<CellHeader><Label>性别</Label></CellHeader>
							<CellBody><Select value = { perInfo.sex } data = { [{label:"男",value:"M"},{label:"女",value:"F"}] } onChange = { e => this.handleChange( [ "perInfo" , "sex" ], e ) }/></CellBody>
						</FormCell>
						<FormCell>
							<CellHeader><Label>出日日期</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "perInfo" , "birthday" ], e ) } value = { perInfo.birthday || "" } type="date"/></CellBody>
						</FormCell>
						<FormCell select selectPos="before">
							<CellHeader><Select style = {{ width: "125px" }} value = { perInfo.certificateType || 0 } onChange = { e => this.handleChange([ "perInfo", "certificateType" ], e)} data = { card_type }/></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "perInfo" , "certificateNumber" ], e ) } placeholder="请输入证书编号" value={ perInfo.certificateNumber || "" } type = "text"/></CellBody>
						</FormCell>
					</Cells>
					<CellsTitle>联系方式</CellsTitle>
					<Cells>
						<FormCell>
							<CellHeader><Label>邮箱</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "perInfo" , "email" ], e ) } placeholder="请输入邮箱" value={ perInfo.email || ""} type = "text"/></CellBody>
						</FormCell>
						<FormCell>
							<CellHeader><Label>QQ</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "perInfo" , "qq" ], e ) } placeholder="请输入QQ" value={ perInfo.qq || ""} type = "text"/></CellBody>
						</FormCell>
						<FormCell>
							<CellHeader><Label>现居住地</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "perInfo" , "address" ], e ) } placeholder="请输入现居住地" value={ perInfo.address || "" } type = "text"/></CellBody>
						</FormCell>
					</Cells>
					<CellsTitle>紧急联系人</CellsTitle>
					<Cells>
						<FormCell>
							<CellHeader><Label>姓名</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "perInfo" , "emergencyContactName" ], e ) } placeholder="请输入姓名" value={ perInfo.emergencyContactName || "" } type = "text"/></CellBody>
						</FormCell>
						<FormCell>
							<CellHeader><Label>手机号</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "perInfo" , "emergencyContactPhone" ], e ) } placeholder="请输入手机号" value={ perInfo.emergencyContactPhone || "" } type = "text"/></CellBody>
						</FormCell>
					</Cells>
					<ButtonArea>
						<Button onClick = { () => this.handleSubmit() }>提交修改</Button>
					</ButtonArea>
				</div>
			)
		}else if( comInfo ){
			contentInfo = (
				<div>
					<Cells>
						<FormCell>
							<CellHeader><Label>单位地址</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "comInfo" , "companyAddress" ], e ) } placeholder="请输入单位地址" value={ comInfo.companyAddress || "" } type = "text"/></CellBody>
						</FormCell>
					</Cells>
					<CellsTitle>主要联系人</CellsTitle>
					<Cells>
						<FormCell>
							<CellHeader><Label>邮箱</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "comInfo" , "email" ], e ) } placeholder="请输入邮箱" value={ comInfo.email || "" } type = "text"/></CellBody>
						</FormCell>
						<FormCell>
							<CellHeader><Label>公司法人</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "comInfo" , "companyLegal" ], e ) } placeholder="请输入公司法人" value={ comInfo.companyLegal || "" } type = "text"/></CellBody>
						</FormCell>
					</Cells>
					<CellsTitle>紧急联系人</CellsTitle>
					<Cells>
						<FormCell>
							<CellHeader><Label>姓名</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "comInfo" , "emergencyContactName" ], e ) } placeholder="请输入姓名" value={ comInfo.emergencyContactName || "" } type = "text"/></CellBody>
						</FormCell>
						<FormCell>
							<CellHeader><Label>手机号</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "comInfo" , "emergencyContactPhone" ], e ) } placeholder="请输入手机号" value={ comInfo.emergencyContactPhone || "" } type = "text"/></CellBody>
						</FormCell>
					</Cells>
					<ButtonArea>
						<Button onClick = { () => this.handleSubmit() }>提交修改</Button>
					</ButtonArea>
				</div>
			)
		}*/
		if( perInfo ){
			contentInfo = (
				<div className = "mutat_edit">
					<Cells>
						<FormCell className = "head_pic">
							<CellHeader><Label>头像</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter><img src={ perInfo.headPic || defaultHeadBS64 }/></CellFooter>
						</FormCell>
						<FormCell>
							<CellHeader><Label>用户名</Label></CellHeader>
							<CellBody>{ perInfo.userName }</CellBody>
						</FormCell>
						<FormCell>
							<CellHeader><Label>姓名</Label></CellHeader>
							<CellBody>{ perInfo.name }</CellBody>
						</FormCell>
						<FormCell select selectPos="after" className = "mutat">
							<CellHeader><Label>性别</Label></CellHeader>
							<CellBody><Select value = { perInfo.sex } data = { [{label:"男",value:"M"},{label:"女",value:"F"}] } onChange = { e => this.handleChange( [ "perInfo" , "sex" ], e ) }/></CellBody>
							<CellBody></CellBody>
						</FormCell>
						<FormCell>
							<CellHeader><Label>身份证号</Label></CellHeader>
							<CellBody>{ perInfo.idCard }</CellBody>
						</FormCell>
						{/*<FormCell>
							<CellHeader><Label>证书编号</Label></CellHeader>
							<CellBody>{ perInfo.certificateNumber }</CellBody>
						</FormCell>*/}
						<FormCell select selectPos="before" className = "mutat">
							<CellHeader><Select style = {{ width: "125px" }} value = { perInfo.certificateType || 0 } onChange = { e => this.handleChange([ "perInfo", "certificateType" ], e)} data = { card_type }/></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "perInfo" , "certificateNumber" ], e ) } placeholder="请输入证书编号" value={ perInfo.certificateNumber || "" } type = "text"/></CellBody>
						</FormCell>
						<FormCell className = "mutat">
							<CellHeader><Label>出日日期</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "perInfo" , "birthday" ], e ) } value = { perInfo.birthday || "" } type="date"/></CellBody>
							{/*<CellBody>{ perInfo.birthday }</CellBody>*/}
						</FormCell>
						<FormCell>
							<CellHeader><Label>认证状态</Label></CellHeader>
							<CellBody>{ auth_status[ perInfo.authStatus ] }</CellBody>
						</FormCell>
					</Cells>
					<CellsTitle>联系方式</CellsTitle>
					<Cells>
						<FormCell>
							<CellHeader><Label>手机号</Label></CellHeader>
							<CellBody>{ perInfo.phone }</CellBody>
						</FormCell>
						<FormCell className = "mutat">
							<CellHeader><Label>邮箱</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "perInfo" , "email" ], e ) } placeholder="请输入邮箱" value={ perInfo.email || ""} type = "text"/></CellBody>
							{/*<CellBody>{ perInfo.email }</CellBody>*/}
						</FormCell>
						<FormCell className = "mutat">
							<CellHeader><Label>QQ</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "perInfo" , "qq" ], e ) } placeholder="请输入QQ" value={ perInfo.qq || ""} type = "text"/></CellBody>
							{/*<CellBody>{ perInfo.qq }</CellBody>*/}
						</FormCell>
						<FormCell className = "mutat">
							<CellHeader><Label>现居住地</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "perInfo" , "address" ], e ) } placeholder="请输入现居住地" value={ perInfo.address || "" } type = "text"/></CellBody>
							{/*<CellBody>{ perInfo.address }</CellBody>*/}
						</FormCell>
					</Cells>
					<CellsTitle>紧急联系人</CellsTitle>
					<Cells>
						<FormCell className = "mutat">
							<CellHeader><Label>姓名</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "perInfo" , "emergencyContactName" ], e ) } placeholder="请输入姓名" value={ perInfo.emergencyContactName || "" } type = "text"/></CellBody>
							{/*<CellBody>{ perInfo.emergencyContactName }</CellBody>*/}
						</FormCell>
						<FormCell className = "mutat">
							<CellHeader><Label>手机号</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "perInfo" , "emergencyContactPhone" ], e ) } placeholder="请输入手机号" value={ perInfo.emergencyContactPhone || "" } type = "text"/></CellBody>
							{/*<CellBody>{ perInfo.emergencyContactPhone }</CellBody>*/}
						</FormCell>
					</Cells>
					<ButtonArea>
						<Button onClick = { () => this.handleSubmit() }>提交修改</Button>
					</ButtonArea>
				</div>
			)
		}else if( comInfo ){
			contentInfo = (
				<div className = "mutat_edit">
					<Cells>
						<FormCell className = "head_pic">
							<CellHeader><Label>头像</Label></CellHeader>
							<CellBody></CellBody>
							<CellFooter><img src={ comInfo.headPic || defaultHeadBS64 }/></CellFooter>
						</FormCell>
						<FormCell>
							<CellHeader><Label>用户名</Label></CellHeader>
							<CellBody>{ comInfo.userName }</CellBody>
						</FormCell>
						<FormCell>
							<CellHeader><Label>单位名称</Label></CellHeader>
							<CellBody>{ comInfo.companyName }</CellBody>
						</FormCell>
						<FormCell>
							<CellHeader><Label>社会统一信用代码</Label></CellHeader>
							<CellBody>{ comInfo.licenseNumber }</CellBody>
						</FormCell>
						<FormCell className = "mutat">
							<CellHeader><Label>单位地址</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "comInfo" , "companyAddress" ], e ) } placeholder="请输入单位地址" value={ comInfo.companyAddress || "" } type = "text"/></CellBody>
						</FormCell>
						<FormCell>
							<CellHeader><Label>认证状态</Label></CellHeader>
							<CellBody>{ auth_status[ comInfo.authStatus ] }</CellBody>
						</FormCell>
					</Cells>
					<CellsTitle>主要联系人</CellsTitle>
					<Cells>
						<FormCell>
							<CellHeader><Label>姓名</Label></CellHeader>
							<CellBody>{ comInfo.contactName }</CellBody>
						</FormCell>
						<FormCell>
							<CellHeader><Label>手机号</Label></CellHeader>
							<CellBody>{ comInfo.contactPhone }</CellBody>
						</FormCell>
						<FormCell className = "mutat">
							<CellHeader><Label>邮箱</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "comInfo" , "email" ], e ) } placeholder="请输入邮箱" value={ comInfo.email || "" } type = "text"/></CellBody>
							{/*<CellBody>{ comInfo.email }</CellBody>*/}
						</FormCell>
						<FormCell className = "mutat">
							<CellHeader><Label>公司法人</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "comInfo" , "companyLegal" ], e ) } placeholder="请输入公司法人" value={ comInfo.companyLegal || "" } type = "text"/></CellBody>
							{/*<CellBody>{ comInfo.companyLegal }</CellBody>*/}
						</FormCell>
					</Cells>
					<CellsTitle>紧急联系人</CellsTitle>
					<Cells>
						<FormCell className = "mutat">
							<CellHeader><Label>姓名</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "comInfo" , "emergencyContactName" ], e ) } placeholder="请输入姓名" value={ comInfo.emergencyContactName || "" } type = "text"/></CellBody>
							{/*<CellBody>{ comInfo.emergencyContactName }</CellBody>*/}
						</FormCell>
						<FormCell className = "mutat">
							<CellHeader><Label>手机号</Label></CellHeader>
							<CellBody><Input onChange = { e => this.handleChange( [ "comInfo" , "emergencyContactPhone" ], e ) } placeholder="请输入手机号" value={ comInfo.emergencyContactPhone || "" } type = "text"/></CellBody>
							{/*<CellBody>{ comInfo.emergencyContactPhone }</CellBody>*/}
						</FormCell>
					</Cells>
					<ButtonArea>
						<Button onClick = { () => this.handleSubmit() }>提交修改</Button>
					</ButtonArea>
				</div>
			)
		}
		return (
			<div id="user_info_app"> 
				{ contentInfo } 
			</div>
		)
	}
}